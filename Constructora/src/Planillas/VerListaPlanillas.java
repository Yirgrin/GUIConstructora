
package Planillas;

import ConexionBD.OracleDBManager;
import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;

public class VerListaPlanillas extends javax.swing.JPanel {
    private final PlanillaDAO planillaDAO = new PlanillaDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    OracleDBManager conexion = new OracleDBManager();
    
    public VerListaPlanillas() {
        initComponents();
        
        // Crear el modelo de tabla y configurarlo para la tabla  
        modelo = new DefaultTableModel();
        tablaDatos.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Empleado");
        modelo.addColumn("Fecha Ingreso");
        modelo.addColumn("Fecha Salida");
        modelo.addColumn("Horas Semana");
        modelo.addColumn("Salario Hora");
        
        actualizarDatos();
        
    }

    public void actualizarDatos() {
        ResultSet resultSet = planillaDAO.obtenerPlanillas();
        if (resultSet != null) {
            try {
                // Limpiar la tabla antes de agregar los datos
                modelo.setRowCount(0);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                // Llenar la tabla con los resultados de la consulta
                while (resultSet.next()) {
                    Object[] row = new Object[6]; // 4 columnas en total
                    row[0] = resultSet.getObject(1); // ID
                    row[1] = resultSet.getObject(2); // Id user
                    row[2] = dateFormat.format(resultSet.getDate(3)); // fecha ingreso formateada
                    row[3] = dateFormat.format(resultSet.getDate(4)); // fecha fin formateada
                    row[4] = resultSet.getObject(5); // horas sem
                    row[5] = resultSet.getObject(6); // salario hora
                    modelo.addRow(row);
                }
            } catch (SQLException ex) {
                Logger.getLogger(VerListaPlanillas.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VerListaPlanillas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CentralFrame1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        CalcularSalario = new javax.swing.JButton();

        CentralFrame1.setBackground(new java.awt.Color(102, 102, 102));
        CentralFrame1.setRequestFocusEnabled(false);

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Planillas");

        tablaDatos.setFont(new java.awt.Font("Eras Medium ITC", 0, 17)); // NOI18N
        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaDatos.setShowGrid(true);
        jScrollPane1.setViewportView(tablaDatos);

        CalcularSalario.setBackground(new java.awt.Color(57, 57, 57));
        CalcularSalario.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        CalcularSalario.setForeground(new java.awt.Color(255, 255, 255));
        CalcularSalario.setText("Calcular Salario");
        CalcularSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalcularSalarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CentralFrame1Layout = new javax.swing.GroupLayout(CentralFrame1);
        CentralFrame1.setLayout(CentralFrame1Layout);
        CentralFrame1Layout.setHorizontalGroup(
            CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CentralFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CentralFrame1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CentralFrame1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CentralFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(255, 255, 255))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CentralFrame1Layout.createSequentialGroup()
                                .addComponent(CalcularSalario)
                                .addGap(21, 21, 21))))))
        );
        CentralFrame1Layout.setVerticalGroup(
            CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CentralFrame1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CalcularSalario)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CentralFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CentralFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CalcularSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalcularSalarioActionPerformed
                                     
        tablaDatos.requestFocus();
        int filaSeleccionada = tablaDatos.getSelectedRow();           
        if (filaSeleccionada != -1) {
             int usuarioId = ((BigDecimal) tablaDatos.getValueAt(filaSeleccionada, 0)).intValue();

            // Llama al método del DAO para calcular el salario semanal
            int salarioSemanal = planillaDAO.CalcularPlanilla(usuarioId);
            
            String empleado = (String) tablaDatos.getValueAt(filaSeleccionada, 1);
            String fechaInicioStr = (String) tablaDatos.getValueAt(filaSeleccionada, 2); // Obtiene la fecha como String
            String fechaFincioStr = (String) tablaDatos.getValueAt(filaSeleccionada, 3); // Obtiene la fecha como String
            Date fechaIni = null;
            Date fechaFin = null;
            try {
               fechaIni = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicioStr);
               fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFincioStr);
            } catch (ParseException ex) {
            }
            SimpleDateFormat dateFormatOutput = new SimpleDateFormat("dd-MM-yyyy");
            String fechaIniForm= dateFormatOutput.format(fechaIni); 
            String fechaFinForm = dateFormatOutput.format(fechaFin); 

            String s = "Cálculo de salario semanal" +
                    "\nCorrespondiente al empleado: " + empleado 
                    + "\nDesde el: " + fechaIniForm
                    + "\nHasta el: " + fechaFinForm
                    + "\nPor un total de: " + salarioSemanal + " colones";
            JOptionPane.showMessageDialog(null, s, "Detalles", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para calcular el salario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_CalcularSalarioActionPerformed

    public void eliminarDatos() {
        tablaDatos.requestFocus();
        int filaSeleccionada = tablaDatos.getSelectedRow();   
        int row;
        if (filaSeleccionada != -1) {
            row = ((BigDecimal) tablaDatos.getValueAt(filaSeleccionada, 0)).intValue();
            planillaDAO.eliminaPlanilla(row);
            JOptionPane.showMessageDialog(null, "Planilla eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void editarDatos() {
        tablaDatos.requestFocus();
        int filaSeleccionada = tablaDatos.getSelectedRow();
        if (filaSeleccionada != -1) {
            int row = ((BigDecimal) tablaDatos.getValueAt(filaSeleccionada, 0)).intValue();

            try {
                // Llamar al procedimiento almacenado para obtener los datos de la planilla
                java.sql.Connection conn = conexion.conectar();
                String sql = "{ call sp_obtener_planilla_id(?, ?) }";
                CallableStatement stmt = conn.prepareCall(sql);
                stmt.setInt(1, row);
                stmt.registerOutParameter(2, OracleTypes.CURSOR);
                stmt.execute();

                // Obtener los resultados del cursor
                ResultSet rs = (ResultSet) stmt.getObject(2);
                if (rs.next()) {
                    // Obtener los datos de la planilla
                    int idPlanilla = rs.getInt("id_planilla");
                    int idUsuario = rs.getInt("usuario_id");
                    Date fechaIngreso = rs.getDate("fecha_ingreso");
                    Date fechaSalida = rs.getDate("fecha_salida");
                    int horasSemana = rs.getInt("horas_semanales");
                    int salarioHora = rs.getInt("salario_hora");

                    // Mostrar el formulario para editar los datos de la planilla
                    JTextField idUsuarioField = new JTextField(String.valueOf(idUsuario));
                    JDateChooser fechaIngresoChooser = new JDateChooser();
                    fechaIngresoChooser.setDate(fechaIngreso);
                    JDateChooser fechaSalidaChooser = new JDateChooser();
                    fechaSalidaChooser.setDate(fechaSalida);
                    JTextField horasSemanaField = new JTextField(String.valueOf(horasSemana));
                    JTextField salarioHoraField = new JTextField(String.valueOf(salarioHora));

                    JPanel panelPlanilla = new JPanel(new GridLayout(8, 2));
                    panelPlanilla.add(new JLabel("ID Usuario:"));
                    panelPlanilla.add(idUsuarioField);
                    panelPlanilla.add(new JLabel("Fecha de ingreso:"));
                    panelPlanilla.add(fechaIngresoChooser);
                    panelPlanilla.add(new JLabel("Fecha de salida:"));
                    panelPlanilla.add(fechaSalidaChooser);
                    panelPlanilla.add(new JLabel("Horas Semanales:"));
                    panelPlanilla.add(horasSemanaField);
                    panelPlanilla.add(new JLabel("Salario por hora:"));
                    panelPlanilla.add(salarioHoraField);

                    int result = JOptionPane.showConfirmDialog(null, panelPlanilla, "Editar Planilla", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        try {
                            int nuevoIdUsuario = Integer.parseInt(idUsuarioField.getText());
                            Date nuevaFechaIngreso = fechaIngresoChooser.getDate();
                            Date nuevaFechaSalida = fechaSalidaChooser.getDate();
                            int nuevasHorasSemana = Integer.parseInt(horasSemanaField.getText());
                            int nuevoSalarioHora = Integer.parseInt(salarioHoraField.getText());

                           if (PlanillaDAO.editarPlanilla(idPlanilla, nuevoIdUsuario, nuevaFechaIngreso, nuevaFechaSalida, nuevasHorasSemana, nuevoSalarioHora)) {
                            JOptionPane.showMessageDialog(null, "Planilla editada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                             JOptionPane.showMessageDialog(null, "No se completó la edición.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    System.out.println("El usuario canceló la edición de la planilla.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna planilla con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los datos de la planilla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
 }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CalcularSalario;
    private javax.swing.JPanel CentralFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables
}
