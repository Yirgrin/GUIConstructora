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

/**
 *
 * @author Melvin
 */
public class verTarea extends javax.swing.JPanel {
    OracleDBManager conexion = new OracleDBManager();
    JDateChooser dateTarea = new JDateChooser();
    public verTarea() {
        initComponents();
        dateTarea.setDateFormatString("yyyy-MM-dd");
    }
    
    public void mostrarTarea() {
         // Sentencia SQL para obtener todas las asignaciones
    String sql = "SELECT asignacion_id, empleado_id, proyecto_id, fecha_vencimiento, descripcion FROM Asignaciones";

    try {
        // Obtener la conexión desde OracleDBManager
        Connection conn = conexion.conectar();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        // Crear un modelo de tabla para la jTable1
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        // Limpiar la tabla antes de agregar los datos
        model.setRowCount(0);

        // Llenar la tabla con los resultados de la consulta
        while (rs.next()) {
            Object[] row = new Object[5];
            for (int i = 0; i < 5; i++) {
                row[i] = rs.getObject(i + 1);
            }
            model.addRow(row);
        }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(102, 102, 102));

        jTable1.setBackground(new java.awt.Color(102, 102, 102));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Asignación", "ID Empleado", "ID Proyecto", "Fecha Vencimiento", "Descripción", "Fecha de Devolución"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
