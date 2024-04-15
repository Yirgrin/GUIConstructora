
package Planillas;

import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class VerListaPlanillas extends javax.swing.JPanel {
    private final PlanillaDAO planillaDAO = new PlanillaDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    
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

        javax.swing.GroupLayout CentralFrame1Layout = new javax.swing.GroupLayout(CentralFrame1);
        CentralFrame1.setLayout(CentralFrame1Layout);
        CentralFrame1Layout.setHorizontalGroup(
            CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CentralFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CentralFrame1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(255, 255, 255))
        );
        CentralFrame1Layout.setVerticalGroup(
            CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CentralFrame1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
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
        int row;
        if (filaSeleccionada != -1) {
            row = ((BigDecimal) tablaDatos.getValueAt(filaSeleccionada, 0)).intValue();
            
            int idUsuario = Integer.parseInt(JOptionPane.showInputDialog(null, "Empleado:", "Editar Planilla", JOptionPane.QUESTION_MESSAGE));
            JPanel panelPlanilla = new JPanel(new GridLayout(4, 2));
            panelPlanilla.add(new JLabel("Fecha de ingreso:"));
            JDateChooser dateIngreso = new JDateChooser();
            panelPlanilla.add(dateIngreso);
            panelPlanilla.add(new JLabel("Fecha de salida:"));
            JDateChooser dateSalida = new JDateChooser();
            panelPlanilla.add(dateSalida);
            int horasSem = Integer.parseInt(JOptionPane.showInputDialog(null, "Horas Semanales:", "Editar Planilla", JOptionPane.QUESTION_MESSAGE));
            int salarioHora = Integer.parseInt(JOptionPane.showInputDialog(null, "Salario por hora:", "Editar Planilla", JOptionPane.QUESTION_MESSAGE));

            JOptionPane.showConfirmDialog(null, panelPlanilla, "Editar Planilla", JOptionPane.OK_CANCEL_OPTION);
            
            Date fechaIngreso = dateIngreso.getDate();
            Date fechaSalida = dateSalida.getDate();
            
            PlanillaDAO.editarPlanilla(row, idUsuario, fechaIngreso, fechaSalida, horasSem, salarioHora);
            JOptionPane.showMessageDialog(null, "Planilla editada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CentralFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables
}
