
package Alquileres;
import ConexionBD.OracleDBManager;
import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Melvin
 */
public class verAlquiler extends javax.swing.JPanel {
    OracleDBManager conexion = new OracleDBManager();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public verAlquiler() {
        initComponents();
        
        // Crear el modelo de tabla y configurarlo para la tabla  
        modelo = new DefaultTableModel();
        jtable.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Máquina");
        modelo.addColumn("Proveedor");
        modelo.addColumn("Dirección");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Fecha Alquiler");
        modelo.addColumn("Fecha Devolución");

    }
   
public void mostrarAlquileres() {
    String sql = "SELECT * FROM vw_vista_alquileres";

    try (Connection conn = conexion.conectar();
         PreparedStatement statement = conn.prepareStatement(sql);
         ResultSet rs = statement.executeQuery()) {

        DefaultTableModel model = (DefaultTableModel) jtable.getModel();
        model.setRowCount(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        while (rs.next()) {
            Object[] row = new Object[7];
            row[0] = rs.getObject(1);
            row[1] = rs.getObject(2);
            row[2] = rs.getObject(3);
            row[3] = rs.getObject(4);
            row[4] = rs.getObject(5);
            row[5] = dateFormat.format(rs.getDate(6));
            row[6] = dateFormat.format(rs.getDate(7));
            model.addRow(row);
        }
    } catch (SQLException e) {
        System.out.println("Error al mostrar alquileres: " + e.getMessage());
    } finally {
        conexion.desconectar();
    }
}

    public void borrarAlquileres() {
    int selectedRow = jtable.getSelectedRow();
    int alquilerId;
    if (selectedRow != -1) {
        alquilerId = ((BigDecimal) jtable.getValueAt(selectedRow, 0)).intValue();
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
    int selectedRow = jtable.getSelectedRow();

    if (selectedRow != -1) {
        int alquilerId = ((BigDecimal) jtable.getValueAt(selectedRow, 0)).intValue();

        String nuevoMaquinaId = JOptionPane.showInputDialog(null, "ID de Máquina:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevoCodigoProveedor = JOptionPane.showInputDialog(null, "Código de Proveedor:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevaDireccion = JOptionPane.showInputDialog(null, "Dirección:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        String nuevoTelefono = JOptionPane.showInputDialog(null, "Teléfono de Contacto:", "Editar Alquiler", JOptionPane.QUESTION_MESSAGE);
        JDateChooser dateAlquiler = new JDateChooser();
        JDateChooser dateDevolucion = new JDateChooser();
        JPanel panelAlquiler = new JPanel(new GridLayout(2, 2));
        panelAlquiler.add(new JLabel("Fecha de Alquiler:"));
        panelAlquiler.add(dateAlquiler);
        panelAlquiler.add(new JLabel("echa de Devolución:"));
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable = new javax.swing.JTable();

        setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Alquileres");

        jtable.setFont(new java.awt.Font("Eras Medium ITC", 0, 17)); // NOI18N
        jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtable.setShowGrid(true);
        jScrollPane1.setViewportView(jtable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(301, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(282, 282, 282))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addContainerGap(478, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(41, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtable;
    // End of variables declaration//GEN-END:variables
}
