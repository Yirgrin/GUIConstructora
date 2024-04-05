/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Alquileres;

import ConexionBD.OracleDBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Melvin
 */
public class addAlquiler extends javax.swing.JPanel {
    OracleDBManager conexion = new OracleDBManager();
    /**
     * Creates new form addAlquiler
     */
    public addAlquiler() {
        initComponents();
    }
    /*
    FALTA DE EDITAR
    YA QUE NO ESTA FUNCIONANDO POR LA FOREIGN KEY
    */
    public static void insertarAlquiler(int idMaquina, String codProveedorValue, String direccionValue, String telefonoValue, Date fechaAlquilerValue, Date fechaDevolucionValue) {
        OracleDBManager dbManager = new OracleDBManager(); 
        try (Connection conexion = dbManager.conectar()) {
            // Llama al procedimiento almacenado
            String sql = "{call sp_insertar_alquiler(?, ?, ?, ?, ?, ?)}";
            try (PreparedStatement statement = conexion.prepareCall(sql)) {
                java.sql.Date sqlFechaAlquiler = new java.sql.Date(fechaAlquilerValue.getTime());
                java.sql.Date sqlFechaDevolucion = new java.sql.Date(fechaDevolucionValue.getTime());
                statement.setInt(1, idMaquina);
                statement.setString(2, codProveedorValue);
                statement.setString(3, direccionValue);
                statement.setString(4, telefonoValue);
                statement.setDate(5, sqlFechaAlquiler);
                statement.setDate(6, sqlFechaDevolucion);
                statement.executeUpdate();
                System.out.println("El alquiler se insertó correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar el alquiler: " + e.getMessage());
        } finally {
            dbManager.desconectar();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dateDevolucion = new com.toedter.calendar.JDateChooser();
        dateAlquiler = new com.toedter.calendar.JDateChooser();
        telefono = new javax.swing.JTextField();
        direccion = new javax.swing.JTextField();
        codProveedor = new javax.swing.JTextField();
        idMaquina = new javax.swing.JTextField();
        Enviar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Identificación de la Máquina:");

        jLabel3.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Código del Proveedor:");

        jLabel4.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Dirección:");

        jLabel5.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Teléfono de Contacto:");

        jLabel6.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Fecha de Alquiler:");

        jLabel7.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Fecha de Devolución:");

        dateDevolucion.setBackground(new java.awt.Color(187, 187, 187));

        dateAlquiler.setBackground(new java.awt.Color(187, 187, 187));

        telefono.setBackground(new java.awt.Color(187, 187, 187));

        direccion.setBackground(new java.awt.Color(187, 187, 187));

        codProveedor.setBackground(new java.awt.Color(187, 187, 187));

        idMaquina.setBackground(new java.awt.Color(187, 187, 187));

        Enviar.setBackground(new java.awt.Color(57, 57, 57));
        Enviar.setFont(new java.awt.Font("Eras Medium ITC", 1, 18)); // NOI18N
        Enviar.setForeground(new java.awt.Color(255, 255, 255));
        Enviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/enviar (1).png"))); // NOI18N
        Enviar.setText("Enviar");
        Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nuevo Alquiler");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(Enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(idMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(Enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarActionPerformed
        insertarAlquiler(Integer.parseInt(idMaquina.getText()), codProveedor.getText(), direccion.getText(), telefono.getText(), dateAlquiler.getDate(), dateDevolucion.getDate());
        codProveedor.setText("");
        direccion.setText("");
        idMaquina.setText("");
        telefono.setText("");
        dateAlquiler.setDate(new Date());
        dateDevolucion.setDate(new Date());
    }//GEN-LAST:event_EnviarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Enviar;
    private javax.swing.JTextField codProveedor;
    private com.toedter.calendar.JDateChooser dateAlquiler;
    private com.toedter.calendar.JDateChooser dateDevolucion;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField idMaquina;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables
}
