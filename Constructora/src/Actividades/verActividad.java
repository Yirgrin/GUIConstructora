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
import oracle.jdbc.OracleTypes;

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
    // Sentencia SQL para llamar al procedimiento almacenado
    String sql = "{call sp_obtener_actividad(?)}";

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
        // Llenar la tabla con los resultados de la consulta
        while (rs.next()) {
            Object[] row = new Object[7];
            for (int i = 0; i < 7; i++) {
                row[i] = rs.getObject(i + 1);
            }
            model.addRow(row);
        }
        // Cerrar recursos
        rs.close();
        stmt.close();
        conexion.desconectar();

    } catch (SQLException e) {
        System.out.println("Error al mostrar actividades: " + e.getMessage());
    }
}

 
    public void borrarActividad() {
    int selectedRow = jTable1.getSelectedRow();
    int actividadId;
    if (selectedRow != -1) {
        actividadId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();
        try {
            Connection conn = conexion.conectar();
            String sql = "{call sp_eliminar_actividad(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, actividadId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actividad eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la actividad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarActividad();
}

    
    public void editActividad() {
    dateActividad.setDateFormatString("yyyy-MM-dd");
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
            String sql = "{call sp_actualizar_actividad(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, actividadId);
            stmt.setString(2, nuevoNombre);
            stmt.setDate(3, nuevaFecha);
            stmt.setString(4, nuevaHora);
            stmt.setString(5, nuevaUbicacion);
            stmt.setString(6, nuevaDescripcion);
            stmt.setString(7, nuevosParticipantes);

            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Actividad editada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            stmt.close();
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
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 102));
        setPreferredSize(new java.awt.Dimension(772, 547));

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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Actividades");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(276, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(287, 287, 287))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
