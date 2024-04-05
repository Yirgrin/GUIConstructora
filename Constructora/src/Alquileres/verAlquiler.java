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
    // Sentencia SQL para llamar al procedimiento almacenado
    String sql = "{call sp_obtener_alquiler(?)}";

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
        System.out.println("Error al mostrar alquileres: " + e.getMessage());
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
    /*
    FALTA DE EDITAR
    */
    public void editAlquiler() {
    int selectedRow = jTable1.getSelectedRow();

    if (selectedRow != -1) {
        int alquilerId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();

        String nuevoMaquinaId = JOptionPane.showInputDialog(null, "Nuevo ID de Máquina:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevoCodigoProveedor = JOptionPane.showInputDialog(null, "Nuevo Código de Proveedor:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevaDireccion = JOptionPane.showInputDialog(null, "Nueva Dirección:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevoTelefono = JOptionPane.showInputDialog(null, "Nuevo Teléfono de Contacto:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        JPanel panelAlquiler = new JPanel(new GridLayout(2, 2));
        JDateChooser dateAlquiler = new JDateChooser();
        JDateChooser dateDevolucion = new JDateChooser();
        panelAlquiler.add(new JLabel("Nueva Fecha de Alquiler:"));
        panelAlquiler.add(dateAlquiler);
        panelAlquiler.add(new JLabel("Nueva Fecha de Devolución:"));
        panelAlquiler.add(dateDevolucion);
        JOptionPane.showConfirmDialog(null, panelAlquiler, "Editar Alquiler", JOptionPane.OK_CANCEL_OPTION);
        Date fechaAlquilerUtil = dateAlquiler.getDate();
        Date fechaDevolucionUtil = dateDevolucion.getDate();

        try {
            Connection conn = conexion.conectar();
            String sql = "UPDATE Alquileres SET maquina_id = ?, codigo_proveedor = ?, direccion = ?, telefono_contacto = ?, fecha_alquiler = ?, fecha_devolucion = ? WHERE alquiler_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoMaquinaId);
            pstmt.setString(2, nuevoCodigoProveedor);
            pstmt.setString(3, nuevaDireccion);
            pstmt.setString(4, nuevoTelefono);
            pstmt.setDate(5, new java.sql.Date(fechaAlquilerUtil.getTime()));
            pstmt.setDate(6, new java.sql.Date(fechaDevolucionUtil.getTime()));
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
