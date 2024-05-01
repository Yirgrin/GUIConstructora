
package Actividades;
import ConexionBD.OracleDBManager;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Melvin
 */
public class verActividad extends javax.swing.JPanel {
    OracleDBManager conexion = new OracleDBManager();
    JDateChooser dateActividad = new JDateChooser();
    DefaultTableModel modelo = new DefaultTableModel();

    public verActividad() {
        initComponents();
        // Crear el modelo de tabla y configurarlo para la tabla  
        modelo = new DefaultTableModel();
        jtable.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Ubicación");
        modelo.addColumn("Descripción");
        modelo.addColumn("Participantes");
    }
    
    public void mostrarActividad() {
    // Sentencia SQL para llamar al procedimiento almacenado
    String sql = "{call sp_obtener_actividad(?)}";

    try {
        Connection conn = conexion.conectar();
        CallableStatement stmt = conn.prepareCall(sql);

        DefaultTableModel model = (DefaultTableModel) jtable.getModel();

        model.setRowCount(0);

        stmt.registerOutParameter(1, OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (rs != null) {
            while (rs.next()) {
                Object[] row = new Object[7]; 
                row[0] = rs.getObject(1); 
                row[1] = rs.getObject(2);
                row[2] = dateFormat.format(rs.getDate(3)); 
                row[3] = rs.getObject(4);
                row[4] = rs.getObject(5);
                row[5] = rs.getObject(6);
                row[6] =rs.getObject(7);
                model.addRow(row);
            }
            rs.close();
        }
        stmt.close();
        conexion.desconectar();

    } catch (SQLException e) {
        System.out.println("Error al mostrar actividades: " + e.getMessage());
    }
}

    public void borrarActividad() {
    int selectedRow = jtable.getSelectedRow();
    int actividadId;
    if (selectedRow != -1) {
        actividadId = ((BigDecimal) jtable.getValueAt(selectedRow, 0)).intValue();
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
        int selectedRow = jtable.getSelectedRow();
        if (selectedRow != -1) {
            int actividadId = ((BigDecimal) jtable.getValueAt(selectedRow, 0)).intValue();
            String nombre = (String) jtable.getValueAt(selectedRow, 1); 
            String strFecha = (String) jtable.getValueAt(selectedRow, 2);
            String hora = (String) jtable.getValueAt(selectedRow, 3);
            String ubicacion = (String) jtable.getValueAt(selectedRow, 4);
            String descripcion = (String) jtable.getValueAt(selectedRow, 5); 
            String participantes = (String) jtable.getValueAt(selectedRow, 6);

            // Convertir la fecha de String a java.util.Date
            Date fechaUtil = null;
            try {
                SimpleDateFormat dateFormatTabla = new SimpleDateFormat("yyyy-MM-dd");
                fechaUtil = dateFormatTabla.parse(strFecha);
            } catch (ParseException e) {
            }

            // Configurar el JDateChooser con la fecha convertida
            JDateChooser dateActividad = new JDateChooser(fechaUtil);
            dateActividad.setDateFormatString("yyyy-MM-dd");

            JPanel panel = new JPanel(new GridLayout(6, 2));
            panel.add(new JLabel("Nombre:"));
            JTextField nombreField = new JTextField(nombre);
            panel.add(nombreField);
            panel.add(new JLabel("Fecha:"));
            panel.add(dateActividad);
            panel.add(new JLabel("Hora (HH:mm):"));
            JTextField horaField = new JTextField(hora);
            panel.add(horaField);
            panel.add(new JLabel("Ubicación:"));
            JTextField ubicacionField = new JTextField(ubicacion);
            panel.add(ubicacionField);
            panel.add(new JLabel("Descripción:"));
            JTextField descripcionField = new JTextField(descripcion);
            panel.add(descripcionField);
            panel.add(new JLabel("Participantes:"));
            JTextField participantesField = new JTextField(participantes);
            panel.add(participantesField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Editar Actividad", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String nuevoNombre = nombreField.getText();
                Date nuevaFecha = dateActividad.getDate();
                String nuevaHora = horaField.getText();
                String nuevaUbicacion = ubicacionField.getText();
                String nuevaDescripcion = descripcionField.getText();
                String nuevosParticipantes = participantesField.getText();

                try {
                    Connection conn = conexion.conectar();
                    String sql = "{call sp_actualizar_actividad(?, ?, ?, ?, ?, ?, ?)}";
                    CallableStatement stmt = conn.prepareCall(sql);
                    stmt.setInt(1, actividadId);
                    stmt.setString(2, nuevoNombre);
                    stmt.setDate(3, new java.sql.Date(nuevaFecha.getTime()));
                    stmt.setString(4, nuevaHora);
                    stmt.setString(5, nuevaUbicacion);
                    stmt.setString(6, nuevaDescripcion);
                    stmt.setString(7, nuevosParticipantes);

                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Actividad editada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    if (e.getMessage().contains("ORA-20003") && e.getMessage().contains("La hora de la actividad no puede ser negativa.")) {
                        JOptionPane.showMessageDialog(null, "La hora de la actividad no puede ser negativa. No se completó la edición.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al insertar la actividad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }       
        }
    }
}
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable = new javax.swing.JTable();
        Detalles = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 102));
        setPreferredSize(new java.awt.Dimension(772, 547));

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Actividades");

        jtable.setFont(new java.awt.Font("Eras Medium ITC", 0, 17)); // NOI18N
        jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtable.setShowGrid(true);
        jScrollPane1.setViewportView(jtable);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Detalles, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(Detalles)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetallesActionPerformed
        int selectedRow = jtable.getSelectedRow();
        int actividadId = ((BigDecimal) jtable.getValueAt(selectedRow, 0)).intValue();
        String nombre = (String) jtable.getValueAt(selectedRow, 1);
        String fechaStr = (String) jtable.getValueAt(selectedRow, 2);
        Date fecha = null;
        try {
           fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        String hora = (String) jtable.getValueAt(selectedRow, 3);
        String ubicacion = (String) jtable.getValueAt(selectedRow, 4);
        String descripcion = (String) jtable.getValueAt(selectedRow, 5);
        String participantes = (String) jtable.getValueAt(selectedRow, 6);
        
        SimpleDateFormat dateFormatOutput = new SimpleDateFormat("dd-MM-yyyy");
        String fechaFormateada = dateFormatOutput.format(fecha); 
        String s = "Identificación: " + actividadId 
                + "\nNombre: " + nombre
                + "\nFecha: " + fechaFormateada
                + "\nHora: " + hora
                + "\nUbicación: " + ubicacion
                + "\nDescripción: " + descripcion 
                + "\nParticipantes: " + participantes;
        JOptionPane.showMessageDialog(null, s, "Detalles", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_DetallesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Detalles;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtable;
    // End of variables declaration//GEN-END:variables
}
