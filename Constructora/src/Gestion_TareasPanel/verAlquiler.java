/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Gestion_TareasPanel;
import AlquileresPanel.*;
import ConexionBD.OracleDBManager;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
        // Sentencia SQL para obtener todos los alquileres
        String sql = "SELECT alquiler_id, maquina_id, codigo_proveedor, direccion, telefono_contacto, fecha_alquiler, fecha_devolucion FROM Alquileres";

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
            System.out.println("Error al mostrar alquileres: " + e.getMessage());
        }
    }
    
    public void borrarAlquileres(){
    int selectedRow = jTable1.getSelectedRow();
    int alquilerId;
    if (selectedRow != -1) {
         alquilerId = (int) jTable1.getValueAt(selectedRow, 0);
        try {
            Connection conn = conexion.conectar();
            String sql = "DELETE FROM Alquileres WHERE alquiler_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, alquilerId);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Alquiler eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "El alquiler no existe o ya ha sido eliminado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el alquiler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    public void editAlquiler(){
        int selectedRow = jTable1.getSelectedRow();
    
    if (selectedRow != -1) {
        int alquilerId = (int) jTable1.getValueAt(selectedRow, 0);

        String nuevoMaquinaId = JOptionPane.showInputDialog(null, "Nuevo ID de Máquina:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevoCodigoProveedor = JOptionPane.showInputDialog(null, "Nuevo Código de Proveedor:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevaDireccion = JOptionPane.showInputDialog(null, "Nueva Dirección:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevoTelefono = JOptionPane.showInputDialog(null, "Nuevo Teléfono de Contacto:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevaFechaAlquiler = JOptionPane.showInputDialog(null, "Nueva Fecha de Alquiler (YYYY-MM-DD):", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevaFechaDevolucion = JOptionPane.showInputDialog(null, "Nueva Fecha de Devolución (YYYY-MM-DD):", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);

        try {
            Connection conn = conexion.conectar();
            String sql = "UPDATE Alquileres SET maquina_id = ?, codigo_proveedor = ?, direccion = ?, telefono_contacto = ?, fecha_alquiler = ?, fecha_devolucion = ? WHERE alquiler_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoMaquinaId);
            pstmt.setString(2, nuevoCodigoProveedor);
            pstmt.setString(3, nuevaDireccion);
            pstmt.setString(4, nuevoTelefono);
            pstmt.setString(5, nuevaFechaAlquiler);
            pstmt.setString(6, nuevaFechaDevolucion);
            pstmt.setInt(7, alquilerId);
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Alquiler editado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al editar el alquiler.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar el alquiler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
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
                "ID Alquiler", "ID Maquina", "Código de Proveedor", "Dirección", "Telefóno", "Fecha de Alquiler", "Fecha de Devolución"
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
