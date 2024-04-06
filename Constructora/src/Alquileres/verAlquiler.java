/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Alquileres;
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
public class verAlquiler extends javax.swing.JPanel {
    OracleDBManager conexion = new OracleDBManager();
    /**
     * Creates new form verAlquiler
     */
    public verAlquiler() {
        initComponents();
    }
    
    public void mostrarAlquileres() {
    String sql = "{call sp_obtener_alquiler(?)}";

    try (Connection conn = conexion.conectar();
         CallableStatement stmt = conn.prepareCall(sql)) {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        stmt.registerOutParameter(1, OracleTypes.CURSOR);
        stmt.execute();

        ResultSet rs = (ResultSet) stmt.getObject(1);
        if (rs != null) { // Verificar si el cursor de salida no es nulo
            while (rs.next()) {
                Object[] row = new Object[7];
                for (int i = 0; i < 7; i++) { // Cambiar de <= a <
                    row[i] = rs.getObject(i + 1);
                }
                model.addRow(row);
            }
            rs.close();
        }

    } catch (SQLException e) {
        System.out.println("Error al mostrar alquileres: " + e.getMessage());
    } finally {
        conexion.desconectar();
    }
}



    public void borrarAlquileres() {
    int selectedRow = jTable1.getSelectedRow();
    int alquilerId;
    if (selectedRow != -1) {
        alquilerId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();
        try {
            Connection conn = conexion.conectar();
            String sql = "{call sp_eliminar_alquiler(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, alquilerId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alquiler eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el alquiler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarAlquileres();
}
    public void editAlquiler() {
    int selectedRow = jTable1.getSelectedRow();

    if (selectedRow != -1) {
        int alquilerId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();

        String nuevoMaquinaId = JOptionPane.showInputDialog(null, "Nuevo ID de Máquina:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevoCodigoProveedor = JOptionPane.showInputDialog(null, "Nuevo Código de Proveedor:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevaDireccion = JOptionPane.showInputDialog(null, "Nueva Dirección:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevoTelefono = JOptionPane.showInputDialog(null, "Nuevo Teléfono de Contacto:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        JDateChooser dateAlquiler = new JDateChooser();
        JDateChooser dateDevolucion = new JDateChooser();
        JPanel panelAlquiler = new JPanel(new GridLayout(2, 2));
        panelAlquiler.add(new JLabel("Nueva Fecha de Alquiler:"));
        panelAlquiler.add(dateAlquiler);
        panelAlquiler.add(new JLabel("Nueva Fecha de Devolución:"));
        panelAlquiler.add(dateDevolucion);
        JOptionPane.showConfirmDialog(null, panelAlquiler, "Editar Alquiler", JOptionPane.OK_CANCEL_OPTION);
        Date fechaAlquilerUtil = dateAlquiler.getDate();
        Date fechaDevolucionUtil = dateDevolucion.getDate();

        try {
            Connection conn = conexion.conectar();
            String sql = "{call sp_actualizar_alquiler(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, alquilerId);
            stmt.setString(2, nuevoMaquinaId);
            stmt.setString(3, nuevoCodigoProveedor);
            stmt.setString(4, nuevaDireccion);
            stmt.setString(5, nuevoTelefono);
            stmt.setDate(6, new java.sql.Date(fechaAlquilerUtil.getTime()));
            stmt.setDate(7, new java.sql.Date(fechaDevolucionUtil.getTime()));

            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Alquiler editado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar el alquiler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarAlquileres();
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
                "ID Alquiler", "ID Maquina", "Código de Proveedor", "Dirección", "Telefóno", "Fecha Alquiler", "Fecha Devolución"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(12);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(12);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(35);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(35);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(30);
        }

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Alquileres");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
