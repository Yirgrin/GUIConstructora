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
    // Sentencia SQL para llamar al procedimiento almacenado
    String sql = "{call sp_obtener_proyecto(?)}";

    try {
        // Obtener la conexión desde OracleDBManager
        Connection conn = conexion.conectar();
        CallableStatement stmt = conn.prepareCall(sql);

        // Crear un modelo de tabla para la jTable1
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        // Limpiar la tabla antes de agregar los datos
        model.setRowCount(0);

        // Parámetro de salida para los resultados del procedimiento almacenado
        stmt.registerOutParameter(1, OracleTypes.CURSOR);
        // Ejecutar el procedimiento almacenado
        stmt.execute();
        // Obtener el cursor de salida
        ResultSet rs = (ResultSet) stmt.getObject(1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Llenar la tabla con los resultados de la consulta
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
        int nuevoCodigoPresupuesto= Integer.parseInt(JOptionPane.showInputDialog(null, "Código de Presupuesto:", "Editar Proyecto", JOptionPane.QUESTION_MESSAGE));
        String nuevoNombre = JOptionPane.showInputDialog(null, "Nombre:", "Editar Proyecto", JOptionPane.QUESTION_MESSAGE);
        String nuevaDescripcion = JOptionPane.showInputDialog(null, "Descripción:", "Editar Proyecto", JOptionPane.QUESTION_MESSAGE);
        JPanel panelInicio = new JPanel(new GridLayout(2, 2));
        panelInicio.add(new JLabel("Fecha de Inicio:"));
        panelInicio.add(dateInicio);
        JOptionPane.showConfirmDialog(null, panelInicio, "Editar Proyecto", JOptionPane.OK_CANCEL_OPTION);
        JPanel panelFinalizacion = new JPanel(new GridLayout(2, 2));
        panelFinalizacion.add(new JLabel("Fecha de Finalización:"));
        panelFinalizacion.add(dateFinalizacion);
        JOptionPane.showConfirmDialog(null, panelFinalizacion, "Editar Proyecto",JOptionPane.OK_CANCEL_OPTION);
        String nuevoClienteDatos = JOptionPane.showInputDialog(null, "Datos del Cliente:", "Editar Proyecto", JOptionPane.QUESTION_MESSAGE);
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

        String sql = "{ call sp_proyecto_id(?, ?) }";
        try {
            // Obtener la conexión desde OracleDBManager
            Connection conn = conexion.conectar();
            CallableStatement stmt = conn.prepareCall(sql);

            // Configurar los parámetros del procedimiento almacenado
            stmt.setInt(1, proyecto_id);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            // Ejecutar el procedimiento almacenado
            stmt.execute();
            // Obtener los resultados del cursor
            ResultSet rs = (ResultSet) stmt.getObject(2);
            if (rs.next()) {
                // Obtener los datos del proyecto
                int presupuesto_id = rs.getInt("presupuesto_id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Date fechaInicio = rs.getDate("fecha_inicio");
                Date fechaFin = rs.getDate("fecha_finalizacion");
                String cliente_datos = rs.getString("cliente_datos");

                // Construir el mensaje
                String mensaje = "ID de Proyecto: " + proyecto_id
                    + "\nPresupuesto asociado: " + presupuesto_id
                    + "\nNombre: " + nombre
                    + "\nDescripción: " + descripcion
                    + "\nFecha Inicio: " + fechaInicio
                    + "\nFecha Fin: " + fechaFin
                    + "\nDatos Cliente: " + cliente_datos;

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
            JOptionPane.showMessageDialog(null, "Error al ejecutar el procedimiento almacenado.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_DetallesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Detalles;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
