
package Presupuestos;

import java.awt.GridLayout;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author prisi
 */
public final class VerListaPresupuestos extends javax.swing.JPanel {
    private final PresupuestoDAO presupuestosDAO = new PresupuestoDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public VerListaPresupuestos() {
        initComponents();
        
        // Crear el modelo de tabla y configurarlo para la tabla  
        modelo = new DefaultTableModel();
        tablaDatos.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Costo Materiales");
        modelo.addColumn("Costo Mano de Obra");
        modelo.addColumn("Otros Gastos");
        actualizarDatos();
        
    }

    public void actualizarDatos() {
        ResultSet resultSet = presupuestosDAO.obtenerPresupuestos();
        if (resultSet != null) {
            try {
                // Limpiar la tabla antes de agregar los datos
                modelo.setRowCount(0);

                // Llenar la tabla con los resultados de la consulta
                while (resultSet.next()) {
                    Object[] row = new Object[4]; // 4 columnas en total
                    row[0] = resultSet.getObject(1); // ID
                    row[1] = resultSet.getObject(2); // costo materiales
                    row[2] = resultSet.getObject(3); // costo_mano_obra
                    row[3] = resultSet.getObject(4); // otros_gastos
                    modelo.addRow(row);
                }
            } catch (SQLException ex) {
                Logger.getLogger(VerListaPresupuestos.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VerListaPresupuestos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }
    
    public void eliminarDatos() {
        tablaDatos.requestFocus();
        int filaSeleccionada = tablaDatos.getSelectedRow();   
        int row;
        if (filaSeleccionada != -1) {
            row = ((BigDecimal) tablaDatos.getValueAt(filaSeleccionada, 0)).intValue();
            presupuestosDAO.eliminarPresupuesto(row);
            JOptionPane.showMessageDialog(null, "Presupuesto eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarDatos() {
        tablaDatos.requestFocus();
        int filaSeleccionada = tablaDatos.getSelectedRow();   
        if (filaSeleccionada != -1) {
            int row = ((BigDecimal) tablaDatos.getValueAt(filaSeleccionada, 0)).intValue();

            // Obtener los datos actuales de la fila seleccionada en la tabla
            int costoMateriales = ((BigDecimal) tablaDatos.getValueAt(filaSeleccionada, 1)).intValue();
            int manoObra = ((BigDecimal) tablaDatos.getValueAt(filaSeleccionada, 2)).intValue();
            int otrosGastos = ((BigDecimal) tablaDatos.getValueAt(filaSeleccionada, 3)).intValue();

            // Crear y mostrar el panel de edición
            JTextField costoMaterialesField = new JTextField(String.valueOf(costoMateriales));
            JTextField manoObraField = new JTextField(String.valueOf(manoObra));
            JTextField otrosGastosField = new JTextField(String.valueOf(otrosGastos));

            JPanel panelPresupuesto = new JPanel(new GridLayout(3, 2));
            panelPresupuesto.add(new JLabel("Costo Materiales:"));
            panelPresupuesto.add(costoMaterialesField);
            panelPresupuesto.add(new JLabel("Costo Mano de Obra:"));
            panelPresupuesto.add(manoObraField);
            panelPresupuesto.add(new JLabel("Otros Gastos:"));
            panelPresupuesto.add(otrosGastosField);

            int result = JOptionPane.showConfirmDialog(null, panelPresupuesto, "Editar Presupuesto", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    // Obtener los nuevos valores de los campos
                    int nuevoCostoMateriales = Integer.parseInt(costoMaterialesField.getText());
                    int nuevaManoObra = Integer.parseInt(manoObraField.getText());
                    int nuevoOtrosGastos = Integer.parseInt(otrosGastosField.getText());

                    // Llamar al método para editar el presupuesto
                    presupuestosDAO.editarPresupuesto(row, nuevoCostoMateriales, nuevaManoObra, nuevoOtrosGastos);
                    JOptionPane.showMessageDialog(null, "Presupuesto editado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("El usuario canceló la edición del presupuesto.");
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
        tablaDatos = new javax.swing.JTable();
        CalcularTotal = new javax.swing.JButton();
        Detalles = new javax.swing.JButton();

        CentralFrame1.setBackground(new java.awt.Color(102, 102, 102));
        CentralFrame1.setRequestFocusEnabled(false);

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Presupuestos");

        tablaDatos.setFont(new java.awt.Font("Eras Medium ITC", 0, 17)); // NOI18N
        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaDatos.setShowGrid(true);
        jScrollPane1.setViewportView(tablaDatos);

        CalcularTotal.setBackground(new java.awt.Color(57, 57, 57));
        CalcularTotal.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        CalcularTotal.setForeground(new java.awt.Color(255, 255, 255));
        CalcularTotal.setText("Calcular Total");
        CalcularTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalcularTotalActionPerformed(evt);
            }
        });

        Detalles.setBackground(new java.awt.Color(57, 57, 57));
        Detalles.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        Detalles.setForeground(new java.awt.Color(255, 255, 255));
        Detalles.setText("Detalles");
        Detalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetallesActionPerformed(evt);
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
                                .addComponent(Detalles)
                                .addGap(18, 18, 18)
                                .addComponent(CalcularTotal)
                                .addGap(32, 32, 32))))))
        );
        CentralFrame1Layout.setVerticalGroup(
            CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CentralFrame1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CalcularTotal)
                    .addComponent(Detalles))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CentralFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CentralFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CalcularTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalcularTotalActionPerformed

        tablaDatos.requestFocus();
        int filaSeleccionada = tablaDatos.getSelectedRow();
        if (filaSeleccionada != -1) {
            int presupuestoId = ((BigDecimal) tablaDatos.getValueAt(filaSeleccionada, 0)).intValue();

            // Llama al método del DAO para calcular el salario semanal
            int totalPresupuesto = PresupuestoDAO.CalcularPresupuesto(presupuestoId);

            
            String s = "Cálculo total de gastos del presupuesto: " + totalPresupuesto + " colones";
            JOptionPane.showMessageDialog(null, s, "Detalles", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para calcular el salario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_CalcularTotalActionPerformed

    private void DetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetallesActionPerformed
        ResultSet resultSet = PresupuestoDAO.MontoMaxMin();
    if (resultSet != null) {
        try {
            StringBuilder mensaje = new StringBuilder();
            while (resultSet.next()) {
                // Obtener los datos del presupuesto
                String tipo = resultSet.getString("tipo");
                int presupuesto_id = resultSet.getInt("presupuesto_id");
                int monto_total = resultSet.getInt("monto_total");
                String nombre_proyecto = resultSet.getString("proyecto");

                // Construir el mensaje para cada presupuesto
                String detalle = "Presupuesto: " + tipo + "\n" +
                                 "ID de presupuesto: " + presupuesto_id + "\n" +
                                 "Nombre del proyecto: " + nombre_proyecto + "\n" +
                                 "Monto total: " + monto_total + " colones.\n\n";
                mensaje.append(detalle);
            }
            // Mostrar los detalles de los presupuestos en un JOptionPane
            if (mensaje.length() > 0) {
                JOptionPane.showMessageDialog(null, mensaje.toString(), "Detalles de los Presupuestos", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron presupuestos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener los detalles de los presupuestos.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "No se encontraron resultados.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_DetallesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CalcularTotal;
    private javax.swing.JPanel CentralFrame1;
    private javax.swing.JButton Detalles;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables
}
