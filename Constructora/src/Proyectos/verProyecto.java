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
        int actualCodigoPresupuesto = ((BigDecimal) jTable1.getValueAt(selectedRow, 1)).intValue();
        String actualNombre = (String) jTable1.getValueAt(selectedRow, 2);
        String actualDescripcion = (String) jTable1.getValueAt(selectedRow, 3);

        JPanel panelProyecto = new JPanel(new GridLayout(6, 2));
        panelProyecto.add(new JLabel("Código de Presupuesto:"));
        JTextField codigoPresupuestoField = new JTextField(String.valueOf(actualCodigoPresupuesto));
        panelProyecto.add(codigoPresupuestoField);
        panelProyecto.add(new JLabel("Nombre:"));
        JTextField nombreField = new JTextField(actualNombre);
        panelProyecto.add(nombreField);
        panelProyecto.add(new JLabel("Descripción:"));
        JTextField descripcionField = new JTextField(actualDescripcion);
        panelProyecto.add(descripcionField);
        panelProyecto.add(new JLabel("Fecha de Inicio:"));
        panelProyecto.add(dateInicio);
        panelProyecto.add(new JLabel("Fecha de Finalización:"));
        panelProyecto.add(dateFinalizacion);
        panelProyecto.add(new JLabel("Datos del Cliente:"));
        JTextField clienteDatosField = new JTextField();
        panelProyecto.add(clienteDatosField);

        int result = JOptionPane.showConfirmDialog(null, panelProyecto, "Editar Proyecto", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int nuevoCodigoPresupuesto = Integer.parseInt(codigoPresupuestoField.getText());
            String nuevoNombre = nombreField.getText();
            String nuevaDescripcion = descripcionField.getText();
            String nuevoClienteDatos = clienteDatosField.getText();
            Date fechaInicioUtil = dateInicio.getDate();
            Date fechaFinalizacionUtil = dateFinalizacion.getDate();

            java.sql.Date nuevaFechaInicio = new java.sql.Date(fechaInicioUtil.getTime());
            java.sql.Date nuevaFechaFinalizacion = new java.sql.Date(fechaFinalizacionUtil.getTime());

            try {
                Connection conn = conexion.conectar();
                String sql = "{call sp_actualizar_proyecto(?, ?, ?, ?, ?, ?, ?)}";
                CallableStatement stmt = conn.prepareCall(sql);
                stmt.setInt(1, proyectoId);
                stmt.setInt(2, nuevoCodigoPresupuesto); 
                stmt.setString(3, nuevoNombre);
                stmt.setString(4, nuevaDescripcion);
                stmt.setDate(5, nuevaFechaInicio);
                stmt.setDate(6, nuevaFechaFinalizacion);
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

        String sql = "SELECT * FROM vw_proyectos_asignaciones WHERE proyecto_id = ?";
        try {
            // Obtener la conexión desde OracleDBManager
            Connection conn = conexion.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Configurar el parámetro
            stmt.setInt(1, proyecto_id);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Obtener los datos del proyecto
                int presupuesto_id = rs.getInt("presupuesto_id");
                String nombre = rs.getString("nombre_proyecto");
                String descripcion = rs.getString("descripcion");
                Date fechaInicio = rs.getDate("fecha_inicio");
                Date fechaFin = rs.getDate("fecha_finalizacion");
                String cliente_datos = rs.getString("cliente_datos");
                int total_asignaciones = rs.getInt("cantidad_asignaciones");
                int total_presupuesto = rs.getInt("total_presupuesto");

                // Construir el mensaje
                String mensaje = "ID de Proyecto: " + proyecto_id 
                    + "\n"    
                    + "\nNombre: " + nombre
                    + "\nDescripción: " + descripcion
                    + "\nFecha Inicio: " + fechaInicio
                    + "\nFecha Fin: " + fechaFin
                    + "\nDatos del cliente: " + cliente_datos
                    + "\n" 
                    + "\nTotal de asignaciones del proyecto: " + total_asignaciones
                    + "\n" 
                    + "\nCódigo de presupuesto asociado: " + presupuesto_id
                    + "\nTotal del presupuesto: " + total_presupuesto + " colones. ";

                // Mostrar los detalles del proyecto en un JOptionPane
                JOptionPane.showMessageDialog(null, mensaje, "Detalles del Proyecto", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún proyecto con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Cerrar la conexión y liberar los recursos
            rs.close();
            stmt.close();
            conn.close();
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
