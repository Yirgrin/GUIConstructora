/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Proyectos;
import ConexionBD.OracleDBManager;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.util.Date;

/**
 *
 * @author Melvin
 */
public class verProyecto extends javax.swing.JPanel {
    OracleDBManager conexion = new OracleDBManager();
    JDateChooser dateInicio = new JDateChooser();
    JDateChooser dateFinalizacion = new JDateChooser();
    
    
    
    /**
     * Creates new form verProyecto
     */
    public verProyecto() {
        initComponents();
        dateInicio.setDateFormatString("yyyy-MM-dd");
        dateFinalizacion.setDateFormatString("yyyy-MM-dd");
        
    }
    
    public void mostrarProyecto() {
        // Sentencia SQL para obtener todos los proyectos
        String sql = "SELECT proyecto_id, codigo_proyecto, nombre, descripcion, fecha_inicio, fecha_finalizacion, cliente_datos FROM PROYECTOS";

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
            System.out.println("Error al mostrar proyectos: " + e.getMessage());
        }
    }
    
    public void borrarProyecto(){
    int selectedRow = jTable1.getSelectedRow();
    int proyectoId;
    if (selectedRow != -1) {
         proyectoId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();
        try {
            Connection conn = conexion.conectar();
            String sql = "DELETE FROM PROYECTOS WHERE PROYECTO_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, proyectoId);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Proyecto eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "El Proyecto no existe o ya ha sido eliminado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el Proyecto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarProyecto();
    }
    
    public void editProyecto(){
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        int proyectoId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();

        String nuevoCodigoProyecto = JOptionPane.showInputDialog(null, "Nuevo Código de Proyecto:", "Editar Proyecto", JOptionPane.QUESTION_MESSAGE);
        String nuevoNombre = JOptionPane.showInputDialog(null, "Nuevo Nombre:", "Editar Proyecto", JOptionPane.QUESTION_MESSAGE);
        String nuevaDescripcion = JOptionPane.showInputDialog(null, "Nueva Descripción:", "Editar Proyecto", JOptionPane.QUESTION_MESSAGE);
        JPanel panelInicio = new JPanel(new GridLayout(2, 2));
        panelInicio.add(new JLabel("Nueva Fecha de Inicio:"));
        panelInicio.add(dateInicio);
        JOptionPane.showConfirmDialog(null, panelInicio, "Editar Proyecto", JOptionPane.OK_CANCEL_OPTION);
        JPanel panelFinalizacion = new JPanel(new GridLayout(2, 2));
        panelFinalizacion.add(new JLabel("Nueva Fecha de Finalización:"));
        panelFinalizacion.add(dateFinalizacion);
        JOptionPane.showConfirmDialog(null, panelFinalizacion, "Editar Proyecto",JOptionPane.OK_CANCEL_OPTION);
        String nuevoClienteDatos = JOptionPane.showInputDialog(null, "Nuevos Datos del Cliente:", "Editar Proyecto", JOptionPane.QUESTION_MESSAGE);
        Date fechaInicioUtil = dateInicio.getDate();
        Date fechaFinalizacionUtil = dateFinalizacion.getDate();
             
        java.sql.Date nuevaFechaInicio = new java.sql.Date(fechaInicioUtil.getTime());
        java.sql.Date nuevaFechaFinalizacion = new java.sql.Date(fechaFinalizacionUtil.getTime());

        try {
            Connection conn = conexion.conectar();
            String sql = "UPDATE Proyectos SET codigo_proyecto = ?, nombre = ?, descripcion = ?, fecha_inicio = ?, fecha_finalizacion = ?, cliente_datos = ? WHERE proyecto_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoCodigoProyecto);
            pstmt.setString(2, nuevoNombre);
            pstmt.setString(3, nuevaDescripcion);
            pstmt.setDate(4, nuevaFechaInicio);
            pstmt.setDate(5, nuevaFechaFinalizacion);
            pstmt.setString(6, nuevoClienteDatos);
            pstmt.setInt(7, proyectoId);

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Proyecto editado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al editar el proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            pstmt.close();
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
                "ID_Proyecto", "Cod_Proyecto", "Nombre", "Descripcion", "Fecha Inicio", "Fecha Finalizacion", "Datos del cliente"
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
