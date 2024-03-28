/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Actividades;
import ConexionBD.OracleDBManager;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Melvin
 */
public class verActividad extends javax.swing.JPanel {
    OracleDBManager conexion = new OracleDBManager();
    JDateChooser dateActividad = new JDateChooser();
    /**
     * Creates new form verActividad
     */
    public verActividad() {
        initComponents();
        dateActividad.setDateFormatString("yyyy-MM-dd");
    }
    
    public void mostrarActividad() {
        // Sentencia SQL para obtener todas las actividades
    String sql = "SELECT actividad_id, nombre, fecha, hora, ubicacion, descripcion, participantes FROM Actividades";

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
            Object[] row = new Object[7];
            for (int i = 0; i < 7; i++) {
                row[i] = rs.getObject(i + 1);
            }
            model.addRow(row);
        }
    } catch (SQLException e) {
        System.out.println("Error al mostrar actividades: " + e.getMessage());
    }
}
    
    public void borrarActividad(){
    int selectedRow = jTable1.getSelectedRow();
    int actividadId;
    if (selectedRow != -1) {
        actividadId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();
        try {
            Connection conn = conexion.conectar();
            String sql = "DELETE FROM Actividades WHERE actividad_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, actividadId);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Actividad eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "La actividad no existe o ya ha sido eliminada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la actividad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarActividad();
}
    
    public void editActividad(){
        int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        int actividadId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();

        String nuevoNombre = JOptionPane.showInputDialog(null, "Nuevo Nombre:", "Editar Actividad", JOptionPane.QUESTION_MESSAGE);
        JPanel panelFecha = new JPanel(new GridLayout(2, 2));
        panelFecha.add(new JLabel("Nueva Fecha:"));
        panelFecha.add(dateActividad);
        JOptionPane.showConfirmDialog(null, panelFecha, "Editar Actividad", JOptionPane.OK_CANCEL_OPTION);
        String nuevaHora = JOptionPane.showInputDialog(null, "Nueva Hora (HH:mm):", "Editar Actividad", JOptionPane.QUESTION_MESSAGE);
        String nuevaUbicacion = JOptionPane.showInputDialog(null, "Nueva Ubicación:", "Editar Actividad", JOptionPane.QUESTION_MESSAGE);
        String nuevaDescripcion = JOptionPane.showInputDialog(null, "Nueva Descripción:", "Editar Actividad", JOptionPane.QUESTION_MESSAGE);
        String nuevosParticipantes = JOptionPane.showInputDialog(null, "Nuevos Participantes:", "Editar Actividad", JOptionPane.QUESTION_MESSAGE);
        
        Date fechaUtil = dateActividad.getDate();
        java.sql.Date nuevaFecha = new java.sql.Date(fechaUtil.getTime());

        try {
            Connection conn = conexion.conectar();
            String sql = "UPDATE Actividades SET nombre = ?, fecha = ?, hora = ?, ubicacion = ?, descripcion = ?, participantes = ? WHERE actividad_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoNombre);
            pstmt.setDate(2, nuevaFecha);
            pstmt.setString(3, nuevaHora);
            pstmt.setString(4, nuevaUbicacion);
            pstmt.setString(5, nuevaDescripcion);
            pstmt.setString(6, nuevosParticipantes);
            pstmt.setInt(7, actividadId);

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Actividad editada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al editar la actividad.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar la actividad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarActividad();
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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Actividad", "Nombre", "Fecha", "Hora", "Ubicación", "Descripción", "Participantes"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
