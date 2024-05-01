
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
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
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
    String sql = "SELECT * FROM vw_vista_alquileres";

    try (Connection conn = conexion.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        ResultSet rs = stmt.executeQuery();

        // Obtiene el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) jtable.getModel();
        model.setRowCount(0); // Limpia la tabla antes de agregar nuevos datos

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        while (rs.next()) {
          
            Object[] row = new Object[7];
            row[0] = rs.getObject(1);
            row[1] = rs.getObject(2);
            row[2] = rs.getObject(3);
            row[3] = rs.getObject(4);
            row[4] = rs.getObject(5);
            row[5] = dateFormat.format(rs.getDate(6)); // Formatea la fecha de alquiler
            row[6] = dateFormat.format(rs.getDate(7)); // Formatea la fecha de devolución
            model.addRow(row);
        }

        rs.close(); // Cierra el ResultSet
    } catch (SQLException e) {
        System.out.println("Error al mostrar alquileres: " + e.getMessage());
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

        try (Connection conn = conexion.conectar()) {
            // Llamar al procedimiento almacenado para obtener los datos del alquiler por su ID
            String sql = "{ call sp_alquiler_id(?, ?) }";
            try (CallableStatement stmt = conn.prepareCall(sql)) {
                stmt.setInt(1, alquilerId);
                stmt.registerOutParameter(2, OracleTypes.CURSOR);
                stmt.execute();

                // Obtener los resultados del cursor
                ResultSet rs = (ResultSet) stmt.getObject(2);
                if (rs.next()) {
                    // Obtener los datos del alquiler
                    int actualMaquinaId = rs.getInt("MAQUINA_ID");
                    String actualCodigoProveedor = rs.getString("CODIGO_PROVEEDOR");
                    String actualDireccion = rs.getString("DIRECCION");
                    String actualTelefono = rs.getString("TELEFONO_CONTACTO");
                    Date fechaAlquiler = rs.getDate("FECHA_ALQUILER");
                    Date fechaDevolucion = rs.getDate("FECHA_DEVOLUCION");

                    // Crear JDateChooser y configurarlo con las fechas correspondientes
                    JDateChooser fechaAlquilerChooser = new JDateChooser(fechaAlquiler);
                    JDateChooser fechaDevolucionChooser = new JDateChooser(fechaDevolucion);

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
                        try {
                            sql = "{call sp_actualizar_alquiler(?, ?, ?, ?, ?, ?, ?)}";
                            try (CallableStatement updateStmt = conn.prepareCall(sql)) {
                                updateStmt.setInt(1, alquilerId);
                                updateStmt.setInt(2, nuevoMaquinaId);
                                updateStmt.setString(3, nuevoCodigoProveedor);
                                updateStmt.setString(4, nuevaDireccion);
                                updateStmt.setString(5, nuevoTelefono);
                                updateStmt.setDate(6, new java.sql.Date(nuevaFechaAlquiler.getTime()));
                                updateStmt.setDate(7, new java.sql.Date(nuevaFechaDevolucion.getTime()));

                                updateStmt.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Alquiler editado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (SQLException e) {
                            if (e.getMessage().contains("ORA-20002") && e.getMessage().contains("La fecha de alquiler no puede ser posterior a la fecha de devolución.")) {
                                JOptionPane.showMessageDialog(null, "La fecha de alquiler no puede ser posterior a la fecha de devolución. No se completó la edición.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error al insertar el alquiler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } 
                    } else {
                        System.out.println("El usuario canceló la edición del alquiler.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún alquiler con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                rs.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los datos del alquiler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        calcularAlquiler = new javax.swing.JButton();

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

        calcularAlquiler.setBackground(new java.awt.Color(57, 57, 57));
        calcularAlquiler.setFont(new java.awt.Font("Eras Medium ITC", 1, 18)); // NOI18N
        calcularAlquiler.setForeground(new java.awt.Color(255, 255, 255));
        calcularAlquiler.setText("Alquiler restante");
        calcularAlquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularAlquilerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(313, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(282, 282, 282))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(calcularAlquiler)
                        .addGap(20, 20, 20))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 391, Short.MAX_VALUE)
                .addComponent(calcularAlquiler)
                .addGap(14, 14, 14))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(59, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void calcularAlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularAlquilerActionPerformed
         jtable.requestFocus();
        int filaSeleccionada = jtable.getSelectedRow();           
        if (filaSeleccionada != -1) {
        int alquiler_id = ((BigDecimal) jtable.getValueAt(filaSeleccionada, 0)).intValue();

        try (Connection conn = conexion.conectar();
             CallableStatement statement = conn.prepareCall("{ ? = call fn_calcular_alquiler_restante(?) }")) {

            statement.registerOutParameter(1, Types.VARCHAR);
            statement.setInt(2, alquiler_id);
            statement.execute();

            // Obtener el resultado de la función
            String resultado = statement.getString(1);

            // Dividir el resultado en partes individuales
            String[] partes = resultado.split(",");

            // Recorrer cada parte e imprimir
            StringBuilder mensaje = new StringBuilder();
            for (String parte : partes) {
                mensaje.append(parte.trim()).append("\n");
            }

            // Mostrar el resultado en un JOptionPane
            JOptionPane.showMessageDialog(null, mensaje.toString(), "Información de Alquiler", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            System.out.println("Error al calcular el alquiler restante: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para calcular el alquiler.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_calcularAlquilerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calcularAlquiler;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtable;
    // End of variables declaration//GEN-END:variables
}
