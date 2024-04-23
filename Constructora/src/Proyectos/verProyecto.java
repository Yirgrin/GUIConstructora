/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Proyectos;
import ConexionBD.OracleDBManager;
import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Melvin
 */
public class verProyecto extends javax.swing.JPanel {
    OracleDBManager conexion = new OracleDBManager();
    JDateChooser dateInicio = new JDateChooser();
    JDateChooser dateFinalizacion = new JDateChooser();
    DefaultTableModel modelo = new DefaultTableModel();

    public verProyecto() {
        initComponents();
        dateInicio.setDateFormatString("yyyy-MM-dd");
        dateFinalizacion.setDateFormatString("yyyy-MM-dd");
        
        // Crear el modelo de tabla y configurarlo para la tabla  
        modelo = new DefaultTableModel();
        jTable1.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        modelo.addColumn("Fecha Inicio");
        modelo.addColumn("Fecha Fin");
 
    }
    
    public void mostrarProyecto() {
    String sql = "{call sp_obtener_proyecto(?)}";

    try {
        Connection conn = conexion.conectar();
        CallableStatement stmt = conn.prepareCall(sql);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        stmt.registerOutParameter(1, OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (rs != null) {
            while (rs.next()) {
                Object[] row = new Object[5]; 
                row[0] = rs.getObject(1); 
                row[1] = rs.getObject(3);
                row[2] = rs.getObject(4);     
                row[3] = dateFormat.format(rs.getDate(5));
                row[4] = dateFormat.format(rs.getDate(6));
                model.addRow(row);
            }
            rs.close();
        }
        stmt.close();
        conexion.desconectar();

    } catch (SQLException e) {
        System.out.println("Error al mostrar proyectos: " + e.getMessage());
    }
}

    public void borrarProyecto() {
    int selectedRow = jTable1.getSelectedRow();
    int proyectoId;
    if (selectedRow != -1) {
         proyectoId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();
        try {
            Connection conn = conexion.conectar();
            String sql = "{call sp_eliminar_proyecto(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, proyectoId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Proyecto eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el Proyecto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarProyecto();
}

    public void editProyecto() {
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        int proyectoId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();

        JTextField presupuestoIdField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField descripcionField = new JTextField();
        JDateChooser fechaInicioChooser = new JDateChooser();
        JDateChooser fechaFinalizacionChooser = new JDateChooser();
        JTextField clienteDatosField = new JTextField();

        try {
            Connection conn = conexion.conectar();
            String sql = "{call sp_obtener_proyecto_por_id(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, proyectoId);
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.registerOutParameter(6, Types.DATE);
            stmt.registerOutParameter(7, Types.DATE);
            stmt.execute();

            int actualPresupuestoId = stmt.getInt(2);
            String actualNombre = stmt.getString(3);
            String actualDescripcion = stmt.getString(4);
            Date actualFechaInicio = stmt.getDate(5);
            Date actualFechaFinalizacion = stmt.getDate(6);
            String actualClienteDatos = stmt.getString(7);

            presupuestoIdField.setText(String.valueOf(actualPresupuestoId));
            nombreField.setText(actualNombre);
            descripcionField.setText(actualDescripcion);
            fechaInicioChooser.setDate(actualFechaInicio);
            fechaFinalizacionChooser.setDate(actualFechaFinalizacion);
            clienteDatosField.setText(actualClienteDatos);

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el proyecto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JPanel panelProyecto = new JPanel(new GridLayout(6, 2));
        panelProyecto.add(new JLabel("Código de Presupuesto:"));
        panelProyecto.add(presupuestoIdField);
        panelProyecto.add(new JLabel("Nombre:"));
        panelProyecto.add(nombreField);
        panelProyecto.add(new JLabel("Descripción:"));
        panelProyecto.add(descripcionField);
        panelProyecto.add(new JLabel("Fecha de Inicio:"));
        panelProyecto.add(fechaInicioChooser);
        panelProyecto.add(new JLabel("Fecha de Finalización:"));
        panelProyecto.add(fechaFinalizacionChooser);
        panelProyecto.add(new JLabel("Datos del Cliente:"));
        panelProyecto.add(clienteDatosField);

        int result = JOptionPane.showConfirmDialog(null, panelProyecto, "Editar Proyecto", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int nuevoPresupuestoId = Integer.parseInt(presupuestoIdField.getText());
            String nuevoNombre = nombreField.getText();
            String nuevaDescripcion = descripcionField.getText();
            Date nuevaFechaInicio = fechaInicioChooser.getDate();
            Date nuevaFechaFinalizacion = fechaFinalizacionChooser.getDate();
            String nuevoClienteDatos = clienteDatosField.getText();

            try {
                Connection conn = conexion.conectar();
                String sql = "{call sp_actualizar_proyecto(?, ?, ?, ?, ?, ?, ?)}";
                CallableStatement stmt = conn.prepareCall(sql);
                stmt.setInt(1, proyectoId);
                stmt.setInt(2, nuevoPresupuestoId);
                stmt.setString(3, nuevoNombre);
                stmt.setString(4, nuevaDescripcion);
                stmt.setDate(5, new java.sql.Date(nuevaFechaInicio.getTime()));
                stmt.setDate(6, new java.sql.Date(nuevaFechaFinalizacion.getTime()));
                stmt.setString(7, nuevoClienteDatos);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Proyecto editado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                stmt.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al editar el proyecto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("El usuario canceló la edición del proyecto.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarProyecto();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Detalles = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Proyectos");

        jTable1.setFont(new java.awt.Font("Eras Medium ITC", 0, 17)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        Detalles.setBackground(new java.awt.Color(57, 57, 57));
        Detalles.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        Detalles.setForeground(new java.awt.Color(255, 255, 255));
        Detalles.setText("Ver Detalles");
        Detalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetallesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 324, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(250, 250, 250))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Detalles)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Detalles)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetallesActionPerformed
        int selectedRow = jTable1.getSelectedRow();         
        int proyecto_id = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();

        String sql = "SELECT * FROM Vista_Proyectos WHERE PROYECTO_ID = ?";
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, proyecto_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int presupuesto_id = rs.getInt("PRESUPUESTO_ID");
                    String nombre = rs.getString("NOMBRE_PROYECTO");
                    String descripcion = rs.getString("DESCRIPCION_PROYECTO");
                    Date fechaInicio = rs.getDate("FECHA_INICIO");
                    Date fechaFin = rs.getDate("FECHA_FINALIZACION");
                    String cliente_datos = rs.getString("CLIENTE_DATOS");
                    String estado_proyecto = rs.getString("ESTADO_PROYECTO");
                    int duracion_en_dias = rs.getInt("DURACION_EN_DIAS");
                    double suma_presupuesto = rs.getDouble("SUMA_PRESUPUESTO");

                    StringBuilder mensaje = new StringBuilder();
                    mensaje.append("ID de Proyecto: ").append(proyecto_id).append("\n");
                    mensaje.append("\nNombre: ").append(nombre).append("\n");
                    mensaje.append("Descripción: ").append(descripcion).append("\n");
                    mensaje.append("Fecha Inicio: ").append(fechaInicio).append("\n");
                    mensaje.append("Fecha Fin: ").append(fechaFin).append("\n");
                    mensaje.append("Estado del proyecto: ").append(estado_proyecto).append("\n");
                    mensaje.append("Duración estimada en días: ").append(duracion_en_dias).append("\n");
                    mensaje.append("Datos del cliente: ").append(cliente_datos).append("\n");
                    mensaje.append("\nCódigo de presupuesto asociado: ").append(presupuesto_id).append("\n\n");

                    mensaje.append("Suma del Presupuesto: ").append(suma_presupuesto).append("\n\n");

                    do {
                        int asignacion_id = rs.getInt("ASIGNACION_ID");
                        int empleado_id = rs.getInt("EMPLEADO_ID");
                        Date fechaVencimiento = rs.getDate("FECHA_VENCIMIENTO");
                        String descripcionAsignacion = rs.getString("DESCRIPCION_ASIGNACION");
                        String nombreEmpleado = rs.getString("NOMBRE_EMPLEADO");

                        mensaje.append("Detalles de la asignación: \n");
                        mensaje.append("ID de Asignación: ").append(asignacion_id).append("\n");
                        mensaje.append("ID de Empleado: ").append(empleado_id).append("\n");
                        mensaje.append("Nombre de Empleado: ").append(nombreEmpleado).append("\n");
                        mensaje.append("Fecha de Vencimiento: ").append(fechaVencimiento).append("\n");
                        mensaje.append("Descripción de la Asignación: ").append(descripcionAsignacion).append("\n\n");
                    } while (rs.next());

                    JOptionPane.showMessageDialog(null, mensaje.toString(), "Detalles del Proyecto y Asignaciones", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún proyecto con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta SQL.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DetallesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Detalles;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
