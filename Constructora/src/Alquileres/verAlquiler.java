
package Alquileres;
import ConexionBD.OracleDBManager;
import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import oracle.jdbc.OracleTypes;

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
    String sql = "{call sp_obtener_alquiler(?)}";

    try (Connection conn = conexion.conectar();
         CallableStatement stmt = conn.prepareCall(sql)) {

        // Registro del parámetro de salida
        stmt.registerOutParameter(1, OracleTypes.CURSOR);

        // Ejecutar el procedimiento almacenado
        stmt.execute();

        // Obtener el cursor de salida
        ResultSet rs = (ResultSet) stmt.getObject(1);

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

        rs.close();
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
        int actualMaquinaId = ((BigDecimal) jtable.getValueAt(selectedRow, 1)).intValue();
        String actualCodigoProveedor = (String) jtable.getValueAt(selectedRow, 2);
        String actualDireccion = (String) jtable.getValueAt(selectedRow, 3);
        String actualTelefono = (String) jtable.getValueAt(selectedRow, 4);
        String strFechaAlquiler = (String) jtable.getValueAt(selectedRow, 5);
        String strFechaDevolucion = (String) jtable.getValueAt(selectedRow, 6);

        // Crear JDateChooser y configurarlo con la fecha correspondiente
        JDateChooser fechaAlquilerChooser = new JDateChooser(parseFecha(strFechaAlquiler));
        JDateChooser fechaDevolucionChooser = new JDateChooser(parseFecha(strFechaDevolucion));

        // Crear el panel para mostrar los datos y las fechas
        JPanel panelAlquiler = new JPanel(new GridLayout(7, 2));
        panelAlquiler.add(new JLabel("ID de Máquina:"));
        JTextField maquinaIdField = new JTextField(String.valueOf(actualMaquinaId));
        panelAlquiler.add(maquinaIdField);
        panelAlquiler.add(new JLabel("Código de Proveedor:"));
        JTextField codigoProveedorField = new JTextField(actualCodigoProveedor);
        panelAlquiler.add(codigoProveedorField);
        panelAlquiler.add(new JLabel("Dirección:"));
        JTextField direccionField = new JTextField(actualDireccion);
        panelAlquiler.add(direccionField);
        panelAlquiler.add(new JLabel("Teléfono de Contacto:"));
        JTextField telefonoField = new JTextField(actualTelefono);
        panelAlquiler.add(telefonoField);
        panelAlquiler.add(new JLabel("Fecha de Alquiler:"));
        panelAlquiler.add(fechaAlquilerChooser);
        panelAlquiler.add(new JLabel("Fecha de Devolución:"));
        panelAlquiler.add(fechaDevolucionChooser);

        // Mostrar el diálogo de edición
        int result = JOptionPane.showConfirmDialog(null, panelAlquiler, "Editar Alquiler", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int nuevoMaquinaId = Integer.parseInt(maquinaIdField.getText());
            String nuevoCodigoProveedor = codigoProveedorField.getText();
            String nuevaDireccion = direccionField.getText();
            String nuevoTelefono = telefonoField.getText();
            Date nuevaFechaAlquiler = fechaAlquilerChooser.getDate();
            Date nuevaFechaDevolucion = fechaDevolucionChooser.getDate();

            // Realizar la actualización en la base de datos
            try (Connection conn = conexion.conectar()) {
                String sql = "{call sp_actualizar_alquiler(?, ?, ?, ?, ?, ?, ?)}";
                try (CallableStatement stmt = conn.prepareCall(sql)) {
                    stmt.setInt(1, alquilerId);
                    stmt.setInt(2, nuevoMaquinaId);
                    stmt.setString(3, nuevoCodigoProveedor);
                    stmt.setString(4, nuevaDireccion);
                    stmt.setString(5, nuevoTelefono);
                    stmt.setDate(6, new java.sql.Date(nuevaFechaAlquiler.getTime()));
                    stmt.setDate(7, new java.sql.Date(nuevaFechaDevolucion.getTime()));

                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Alquiler editado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al editar el alquiler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("El usuario canceló la edición del alquiler.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarAlquileres();
}

// Método para convertir la fecha de String a Date
private Date parseFecha(String strFecha) {
    try {
        return new SimpleDateFormat("yyyy-MM-dd").parse(strFecha);
    } catch (ParseException e) {
        e.printStackTrace();
        return null;
    }
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
