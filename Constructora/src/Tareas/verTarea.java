
package Tareas;
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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Melvin
 */
public class verTarea extends javax.swing.JPanel {
    OracleDBManager conexion = new OracleDBManager();
    JDateChooser dateTarea = new JDateChooser();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public verTarea() {
        initComponents();
        
        // Crear el modelo de tabla y configurarlo para la tabla  
        modelo = new DefaultTableModel();
        jTable1.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Empleado");
        modelo.addColumn("Proyecto");
        modelo.addColumn("Fecha Vencimiento");
        modelo.addColumn("Descripción");
        modelo.addColumn("Estado");
    }
    
    public void mostrarTarea() {  
    String sql = "SELECT * FROM vw_vista_asignaciones";

    try (Connection conn = conexion.conectar();
         PreparedStatement statement = conn.prepareStatement(sql);
         ResultSet rs = statement.executeQuery()) {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        while (rs.next()) {
            Object[] row = new Object[6]; 
                row[0] = rs.getObject(1); 
                row[1] = rs.getObject(2);
                row[2] = rs.getObject(3);
                row[3] = dateFormat.format(rs.getDate(4));
                row[4] = rs.getObject(5);
                row[5] = rs.getObject(6);
                
                model.addRow(row);
        }
    } catch (SQLException e) {
        System.out.println("Error al mostrar tareas: " + e.getMessage());
    } finally {
        conexion.desconectar();
    }
}
    
    public void borrarTarea(){
    int selectedRow = jTable1.getSelectedRow();
    int asignacionId;
    if (selectedRow != -1) {
        asignacionId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();
        try {
            Connection conn = conexion.conectar();
            String sql = "{call sp_eliminar_asignacion(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, asignacionId);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Asignación eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "La asignación no existe o ya ha sido eliminada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la asignación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarTarea();
}
  
    public void editTarea() {
    dateTarea.setDateFormatString("yyyy-MM-dd");
    int selectedRow = jTable1.getSelectedRow();

    if (selectedRow != -1) {
        int asignacionId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();

        JTextField idEmpleadoField = new JTextField();
        JTextField idProyectoField = new JTextField();
        JTextField descripcionField = new JTextField();
        JDateChooser fechaTareaChooser = new JDateChooser();

        try {
            Connection conn = conexion.conectar();
            String sql = "{call sp_obtener_asignacion_por_id(?, ?, ?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, asignacionId);
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.registerOutParameter(4, Types.DATE);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.execute();

            // Obtener los valores devueltos por el stored procedure
            int actualIdEmpleado = stmt.getInt(2);
            int actualIdProyecto = stmt.getInt(3);
            Date actualFechaTarea = stmt.getDate(4);
            String actualDescripcion = stmt.getString(5);

            idEmpleadoField.setText(String.valueOf(actualIdEmpleado));
            idProyectoField.setText(String.valueOf(actualIdProyecto));
            descripcionField.setText(actualDescripcion);
            fechaTareaChooser.setDate(actualFechaTarea);

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la asignación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("ID de Empleado:"));
        panel.add(idEmpleadoField);
        panel.add(new JLabel("ID de Proyecto:"));
        panel.add(idProyectoField);
        panel.add(new JLabel("Fecha de Tarea:"));
        panel.add(fechaTareaChooser);
        panel.add(new JLabel("Descripción:"));
        panel.add(descripcionField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Editar Asignación", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            int nuevoIdEmpleado = Integer.parseInt(idEmpleadoField.getText());
            int nuevoIdProyecto = Integer.parseInt(idProyectoField.getText());
            String nuevaDescripcion = descripcionField.getText();
            Date nuevaFechaTarea = fechaTareaChooser.getDate();

            try {
                Connection conn = conexion.conectar();
                String sql = "{call sp_actualizar_asignacion(?, ?, ?, ?, ?)}";
                CallableStatement stmt = conn.prepareCall(sql);
                stmt.setInt(1, asignacionId);
                stmt.setInt(2, nuevoIdEmpleado);
                stmt.setInt(3, nuevoIdProyecto);
                stmt.setDate(4, new java.sql.Date(nuevaFechaTarea.getTime()));
                stmt.setString(5, nuevaDescripcion);
                stmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Asignación editada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al editar la asignación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("El usuario canceló la edición de la tarea.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarTarea();
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Detalles = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Asignaciones");

        jTable1.setFont(new java.awt.Font("Eras Medium ITC", 0, 17)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        Detalles.setBackground(new java.awt.Color(57, 57, 57));
        Detalles.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        Detalles.setForeground(new java.awt.Color(255, 255, 255));
        Detalles.setText("Ver Detalles");
        Detalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetallesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(269, 269, 269))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Detalles)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Detalles)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetallesActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        int actividadId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();
        String idEmpleado = (String) jTable1.getValueAt(selectedRow, 1);
        String idProyecto = (String) jTable1.getValueAt(selectedRow, 2);
        String fechaVencimiento = (String) jTable1.getValueAt(selectedRow, 3); // Obtiene la fecha como String
        Date fecha = null;
        try {
            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaVencimiento);
        } catch (ParseException ex) {
            Logger.getLogger(verTarea.class.getName()).log(Level.SEVERE, null, ex);
        }
        String descripcion = (String) jTable1.getValueAt(selectedRow, 4);
        SimpleDateFormat dateFormatOutput = new SimpleDateFormat("dd-MM-yyyy");
        String fechaFormateada = dateFormatOutput.format(fecha); 
        
        
        String s = "Identificación: " + actividadId
        + "\nEmpleado: " + idEmpleado
        + "\nProyecto: " + idProyecto
        + "\nFecha Vencimiento: " + fechaFormateada
        + "\nDescripción: " + descripcion;
        JOptionPane.showMessageDialog(null, s, "Detalles", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_DetallesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Detalles;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
