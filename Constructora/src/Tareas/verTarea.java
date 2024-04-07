/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Tareas;
import ConexionBD.OracleDBManager;
import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.util.Date;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Melvin
 */
public class verTarea extends javax.swing.JPanel {
    OracleDBManager conexion = new OracleDBManager();
    JDateChooser dateTarea = new JDateChooser();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public verTarea() {
        initComponents();
        
        // Crear el modelo de tabla y configurarlo para la tabla  
        modelo = new DefaultTableModel();
        jTable1.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("ID Empleado");
        modelo.addColumn("ID Proyecto");
        modelo.addColumn("Fecha Vencimiento");
        modelo.addColumn("Descripción");
        modelo.addColumn("Fecha Devolución");
        dateTarea.setDateFormatString("yyyy-MM-dd");
    }
    
    public void mostrarTarea() {
    // Sentencia SQL para llamar al procedimiento almacenado
    String sql = "{call sp_obtener_asignacion(?)}";

    try {
        // Obtener la conexión desde OracleDBManager
        Connection conn = conexion.conectar();
        CallableStatement stmt = conn.prepareCall(sql);

        // Crear un modelo de tabla para la jTable1
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        // Limpiar la tabla antes de agregar los datos
        model.setRowCount(0);

        // Parámetro de entrada para el procedimiento almacenado
        stmt.setInt(1, 0); // Si se desea obtener todas las asignaciones, se puede pasar un valor arbitrario o incluso null

        // Parámetro de salida para los resultados del procedimiento almacenado
        stmt.registerOutParameter(2, OracleTypes.CURSOR);

        // Ejecutar el procedimiento almacenado
        stmt.execute();

        // Obtener el cursor de salida
        ResultSet rs = (ResultSet) stmt.getObject(2);

        // Llenar la tabla con los resultados de la consulta
        while (rs.next()) {
            Object[] row = new Object[5];
            for (int i = 0; i < 5; i++) {
                row[i] = rs.getObject(i + 1);
            }
            model.addRow(row);
        }

        // Cerrar recursos
        rs.close();
        stmt.close();
        conexion.desconectar();

    } catch (SQLException e) {
        System.out.println("Error al mostrar asignaciones: " + e.getMessage());
    }
}

    
    public void borrarTarea(){
    int selectedRow = jTable1.getSelectedRow();
    int asignacionId;
    if (selectedRow != -1) {
        asignacionId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();
        try {
            Connection conn = conexion.conectar();
            String sql = "DELETE FROM Asignaciones WHERE asignacion_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, asignacionId);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Asignación eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "La asignación no existe o ya ha sido eliminada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la asignación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarTarea();
}
    
    public void editTarea(){
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        int asignacionId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();

        String nuevoIdEmpleado = JOptionPane.showInputDialog(null, "Nuevo ID de Empleado:", "Editar Asignación", JOptionPane.QUESTION_MESSAGE);
        String nuevoIdProyecto = JOptionPane.showInputDialog(null, "Nuevo ID de Proyecto:", "Editar Asignación", JOptionPane.QUESTION_MESSAGE);
        JPanel panelFecha = new JPanel(new GridLayout(2, 2));
        panelFecha.add(new JLabel("Nueva Fecha de Tarea:"));
        panelFecha.add(dateTarea);
        JOptionPane.showConfirmDialog(null, panelFecha, "Editar Asignación", JOptionPane.OK_CANCEL_OPTION);
        String nuevaDescripcion = JOptionPane.showInputDialog(null, "Nueva Descripción:", "Editar Asignación", JOptionPane.QUESTION_MESSAGE);
        
        Date fechaUtil = dateTarea.getDate();
        java.sql.Date nuevaFechaTarea = new java.sql.Date(fechaUtil.getTime());

        try {
            Connection conn = conexion.conectar();
            String sql = "UPDATE Asignaciones SET empleado_id = ?, proyecto_id = ?, fecha_vencimiento = ?, descripcion = ? WHERE asignacion_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoIdEmpleado);
            pstmt.setString(2, nuevoIdProyecto);
            pstmt.setDate(3, nuevaFechaTarea);
            pstmt.setString(4, nuevaDescripcion);
            pstmt.setInt(5, asignacionId);

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Asignación editada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al editar la asignación.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar la asignación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarTarea();
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

        setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Asignaciones");

        jTable1.setFont(new java.awt.Font("Eras Medium ITC", 0, 17)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(269, 269, 269))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
