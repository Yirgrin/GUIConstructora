
package Usuarios;

import ConexionBD.OracleDBManager;
import Maquinaria.MaquinariaDAO;
import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.math.BigDecimal;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import oracle.jdbc.OracleTypes;

public class VerListaUsuarios extends javax.swing.JPanel {
    OracleDBManager conexion = new OracleDBManager();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public VerListaUsuarios() {
        initComponents();
        // Crear el modelo de tabla y configurarlo para la tabla  
        modelo = new DefaultTableModel();
        tablaClientes.setModel(modelo);
        modelo.addColumn("Cédula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Teléfono");

        actualizarDatos();  
    }

    public void actualizarDatos() {
        ResultSet resultSet = usuarioDAO.obtenerUsuarios();
        if (resultSet != null) {
            try {
                // Limpiar la tabla antes de agregar los datos
                modelo.setRowCount(0);

                // Llenar la tabla con los resultados de la consulta
                while (resultSet.next()) {
                    Object[] row = new Object[4]; // 4 columnas en total
                    row[0] = resultSet.getObject(1); // Cédula
                    row[1] = resultSet.getObject(2); // Nombre
                    row[2] = resultSet.getObject(3); // Apellido
                    row[3] = resultSet.getObject(4); // Teléfono
                    modelo.addRow(row);
                }
            } catch (SQLException ex) {
                Logger.getLogger(VerListaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VerListaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }

    public void eliminarDatos() {
        tablaClientes.requestFocus();
        int filaSeleccionada = tablaClientes.getSelectedRow();   
        int row;
        if (filaSeleccionada != -1) {
            row = ((BigDecimal) tablaClientes.getValueAt(filaSeleccionada, 0)).intValue();
            usuarioDAO.eliminarUsuario(row);
            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   public void editarDatos() {
        tablaClientes.requestFocus();
        int filaSeleccionada = tablaClientes.getSelectedRow();   
        int row;
        if (filaSeleccionada != -1) {
            row = ((BigDecimal) tablaClientes.getValueAt(filaSeleccionada, 0)).intValue();

            try {
                // Llamar al procedimiento almacenado para obtener los datos del usuario
                java.sql.Connection conn = conexion.conectar();
                String sql = "{ call sp_mostrar_usuario_por_id(?, ?) }";
                CallableStatement stmt = conn.prepareCall(sql);
                stmt.setInt(1, row);
                stmt.registerOutParameter(2, OracleTypes.CURSOR);
                stmt.execute();

                // Obtener los resultados del cursor
                ResultSet rs = (ResultSet) stmt.getObject(2);
                if (rs.next()) {
                    // Obtener los datos del usuario
                    String nombre = rs.getString("nombre");
                    String apellidos = rs.getString("apellidos");
                    String telefono = rs.getString("telefono");
                    String correoElectronico = rs.getString("correo_electronico");
                    String cargo = rs.getString("cargo");
                    Date fechaContratacion = rs.getDate("fecha_contratacion");

                    // Mostrar el formulario para editar los datos del usuario
                    JTextField nuevoNombreField = new JTextField(nombre);
                    JTextField nuevoApellidoField = new JTextField(apellidos);
                    JTextField nuevoTelefonoField = new JTextField(telefono);
                    JTextField nuevoCorreoField = new JTextField(correoElectronico);
                    JTextField nuevoCargoField = new JTextField(cargo);
                    JDateChooser dateContratacion = new JDateChooser(fechaContratacion);

                    JPanel panelUsuario = new JPanel(new GridLayout(6, 2));
                    panelUsuario.add(new JLabel("Nombre:"));
                    panelUsuario.add(nuevoNombreField);
                    panelUsuario.add(new JLabel("Apellidos:"));
                    panelUsuario.add(nuevoApellidoField);
                    panelUsuario.add(new JLabel("Teléfono:"));
                    panelUsuario.add(nuevoTelefonoField);
                    panelUsuario.add(new JLabel("Correo electrónico:"));
                    panelUsuario.add(nuevoCorreoField);
                    panelUsuario.add(new JLabel("Cargo:"));
                    panelUsuario.add(nuevoCargoField);
                    panelUsuario.add(new JLabel("Fecha de contratación:"));
                    panelUsuario.add(dateContratacion);

                    int result = JOptionPane.showConfirmDialog(null, panelUsuario, "Editar Usuario", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        // Obtener los nuevos valores de los campos
                        String nuevoNombre = nuevoNombreField.getText();
                        String nuevoApellido = nuevoApellidoField.getText();
                        String nuevoTelefono = nuevoTelefonoField.getText();
                        String nuevoCorreo = nuevoCorreoField.getText();
                        String nuevoCargo = nuevoCargoField.getText();
                        Date nuevaFechaContratacion = dateContratacion.getDate();

                        if  (usuarioDAO.editarUsuario(row, nuevoNombre, nuevoApellido, nuevoTelefono, nuevoCorreo, nuevoCargo, nuevaFechaContratacion)) {
                           JOptionPane.showMessageDialog(null, "Usuario editado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se completó la edición.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        System.out.println("El usuario canceló la edición de datos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al obtener los datos del usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CentralFrame1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        Detalles = new javax.swing.JButton();
        antEmpleado = new javax.swing.JButton();

        CentralFrame1.setBackground(new java.awt.Color(102, 102, 102));
        CentralFrame1.setRequestFocusEnabled(false);

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuarios");

        tablaClientes.setAutoCreateRowSorter(true);
        tablaClientes.setFont(new java.awt.Font("Eras Medium ITC", 0, 17)); // NOI18N
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaClientes.setShowGrid(true);
        jScrollPane1.setViewportView(tablaClientes);

        Detalles.setBackground(new java.awt.Color(57, 57, 57));
        Detalles.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        Detalles.setForeground(new java.awt.Color(255, 255, 255));
        Detalles.setText("Ver Detalles");
        Detalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetallesActionPerformed(evt);
            }
        });

        antEmpleado.setBackground(new java.awt.Color(57, 57, 57));
        antEmpleado.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        antEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        antEmpleado.setText("Antiguedad de empleado");
        antEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                antEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CentralFrame1Layout = new javax.swing.GroupLayout(CentralFrame1);
        CentralFrame1.setLayout(CentralFrame1Layout);
        CentralFrame1Layout.setHorizontalGroup(
            CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CentralFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CentralFrame1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(255, 255, 255))
                    .addGroup(CentralFrame1Layout.createSequentialGroup()
                        .addGroup(CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(CentralFrame1Layout.createSequentialGroup()
                                .addComponent(antEmpleado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Detalles))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(31, Short.MAX_VALUE))))
        );
        CentralFrame1Layout.setVerticalGroup(
            CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CentralFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Detalles)
                    .addComponent(antEmpleado))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CentralFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CentralFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetallesActionPerformed
        int selectedRow = tablaClientes.getSelectedRow();
        int usuarioId = ((BigDecimal) tablaClientes.getValueAt(selectedRow, 0)).intValue();
        String sql = "{ call sp_mostrar_usuario_por_id(?, ?) }";
        try {
            // Obtener la conexión desde OracleDBManager
            java.sql.Connection conn = conexion.conectar();
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, usuarioId);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            // Ejecutar el procedimiento almacenado
            stmt.execute();

            // Obtener los resultados del cursor
            ResultSet rs = (ResultSet) stmt.getObject(2);
            if (rs.next()) {
                // Obtener los datos del usuario
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String correoElectronico = rs.getString("correo_electronico");
                String cargo = rs.getString("cargo");
                Date fechaContratacion = rs.getDate("fecha_contratacion");

                // Construir el mensaje
                String mensaje = "ID de Usuario: " + usuarioId +
                    "\nNombre: " + nombre +
                    "\nApellidos: " + apellidos +
                    "\nTeléfono: " + telefono +
                    "\nCorreo Electrónico: " + correoElectronico +
                    "\nCargo: " + cargo +
                    "\nFecha de Contratación: " + fechaContratacion;

                // Mostrar los detalles del usuario en un JOptionPane
                JOptionPane.showMessageDialog(null, mensaje, "Detalles del Usuario", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Cerrar la conexión y liberar los recursos
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al ejecutar el procedimiento almacenado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DetallesActionPerformed

    private void antEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_antEmpleadoActionPerformed
        
        tablaClientes.requestFocus();
        int filaSeleccionada = tablaClientes.getSelectedRow();
        if (filaSeleccionada != -1) {
            int usuarioId = ((BigDecimal) tablaClientes.getValueAt(filaSeleccionada, 0)).intValue();

            // Llama al método del DAO para calcular el salario semanal
            String antiguedad = UsuarioDAO.CalcAntiguedad(usuarioId);

            
            String s = "Antiguedad del empleado en la empresa: " + antiguedad;
            JOptionPane.showMessageDialog(null, s, "Detalles", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para calcular el salario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_antEmpleadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CentralFrame1;
    private javax.swing.JButton Detalles;
    private javax.swing.JButton antEmpleado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
}
