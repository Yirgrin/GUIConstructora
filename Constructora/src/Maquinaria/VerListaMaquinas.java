
package Maquinaria;

import java.awt.GridLayout;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

/**
 *
 * @author prisi
 */
public class VerListaMaquinas extends javax.swing.JPanel {
    private final MaquinariaDAO maquinariaDAO = new MaquinariaDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public VerListaMaquinas() {
        initComponents();
        
        // Crear el modelo de tabla y configurarlo para la tabla  
        modelo = new DefaultTableModel();
        tablaDatos.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Máquina");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio");
        modelo.addColumn("Unidades");
        
        actualizarDatos();
    }

    public void actualizarDatos() {
        ResultSet resultSet = maquinariaDAO.obtenerMaquinas();
        if (resultSet != null) {
            try {
                // Limpiar la tabla antes de agregar los datos
                modelo.setRowCount(0);

                // Llenar la tabla con los resultados de la consulta
                while (resultSet.next()) {
                    Object[] row = new Object[5]; // 4 columnas en total
                    row[0] = resultSet.getObject(1); // ID
                    row[1] = resultSet.getObject(2); // Nombre
                    row[2] = resultSet.getObject(3); // Descripcion
                    row[3] = resultSet.getObject(4); // Precio
                    row[4] = resultSet.getObject(5); // Unidades
                    modelo.addRow(row);
                }
            } catch (SQLException ex) {
                Logger.getLogger(VerListaMaquinas.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VerListaMaquinas.class.getName()).log(Level.SEVERE, null, ex);
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
            maquinariaDAO.eliminarMaquina(row);
            JOptionPane.showMessageDialog(null, "Máquina eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public void editarDatos() {
        tablaDatos.requestFocus();
        int filaSeleccionada = tablaDatos.getSelectedRow();   
        if (filaSeleccionada != -1) {
            int row = ((BigDecimal) tablaDatos.getValueAt(filaSeleccionada, 0)).intValue();

            String nombreMaquina = (String) tablaDatos.getValueAt(filaSeleccionada, 1);
            String descripcion = (String) tablaDatos.getValueAt(filaSeleccionada, 2);
            int precio = ((BigDecimal) tablaDatos.getValueAt(filaSeleccionada, 3)).intValue();
            int unidadesTotales = ((BigDecimal) tablaDatos.getValueAt(filaSeleccionada, 4)).intValue();

            JTextField nombreField = new JTextField(nombreMaquina);
            JTextField descripcionField = new JTextField(descripcion);
            JTextField precioField = new JTextField(String.valueOf(precio));
            JTextField unidadesField = new JTextField(String.valueOf(unidadesTotales));

            JPanel panelMaquina = new JPanel(new GridLayout(4, 2));
            panelMaquina.add(new JLabel("Nombre Máquina:"));
            panelMaquina.add(nombreField);
            panelMaquina.add(new JLabel("Descripción:"));
            panelMaquina.add(descripcionField);
            panelMaquina.add(new JLabel("Precio:"));
            panelMaquina.add(precioField);
            panelMaquina.add(new JLabel("Unidades Totales:"));
            panelMaquina.add(unidadesField);

            int result = JOptionPane.showConfirmDialog(null, panelMaquina, "Editar Máquina", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    String nuevoNombre = nombreField.getText();
                    String nuevaDescripcion = descripcionField.getText();
                    int nuevoPrecio = Integer.parseInt(precioField.getText());
                    int nuevasUnidades = Integer.parseInt(unidadesField.getText());

                    // Llamar al método para editar la máquina
                    MaquinariaDAO.editarMaquina(row, nuevoNombre, nuevaDescripcion, nuevoPrecio, nuevasUnidades);
                    JOptionPane.showMessageDialog(null, "Máquina editada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("El usuario canceló la edición de la máquina.");
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

        CentralFrame1.setBackground(new java.awt.Color(102, 102, 102));
        CentralFrame1.setRequestFocusEnabled(false);

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Máquinas");

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
                .addContainerGap(37, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CentralFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables
}
