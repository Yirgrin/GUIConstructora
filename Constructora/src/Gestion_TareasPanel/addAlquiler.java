/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Gestion_TareasPanel;

import AlquileresPanel.*;
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
    
    private void insertarAlquiler() {
        int idAlquilerValue = Integer.parseInt(idAlquiler.getText());
        int idMaquinaValue = Integer.parseInt(idMaquina.getText());
        String codProveedorValue = codProveedor.getText();
        String direccionValue = direccion.getText();
        String telefonoValue = telefono.getText();
        Date fechaAlquilerValue = dateAlquiler.getDate();
        Date fechaDevolucionValue = dateDevolucion.getDate();

        // Sentencia SQL para insertar un nuevo alquiler
        String sql = "INSERT INTO Alquileres (alquiler_id, maquina_id, codigo_proveedor, direccion, telefono_contacto, fecha_alquiler, fecha_devolucion) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Obtener la conexión desde OracleDBManager
            Connection conn = conexion.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql);
   
            // Establecer los valores de los parámetros
            pstmt.setInt(1, idAlquilerValue);
            pstmt.setInt(2, idMaquinaValue);
            pstmt.setString(3, codProveedorValue);
            pstmt.setString(4, direccionValue);
            pstmt.setString(5, telefonoValue);
            pstmt.setDate(6, new java.sql.Date(fechaAlquilerValue.getTime()));
            pstmt.setDate(7, new java.sql.Date(fechaDevolucionValue.getTime()));

            // Ejecutar la inserción
            pstmt.executeUpdate();
            System.out.println("Alquiler insertado correctamente.");
            conexion.desconectar();
        } catch (SQLException e) {
            System.out.println("Error al insertar alquiler: " + e.getMessage());
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

        jLabel1 = new javax.swing.JLabel();
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
        idAlquiler = new javax.swing.JTextField();
        Enviar = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        jLabel1.setText("Identificación del Alquiler");

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        jLabel2.setText("Indentificación de la Maquina");

        jLabel3.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        jLabel3.setText("Código del Proveedor");

        jLabel4.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        jLabel4.setText("Dirección");

        jLabel5.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        jLabel5.setText("Telefóno de Contacto");

        jLabel6.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        jLabel6.setText("Fecha de Alquiler");

        jLabel7.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        jLabel7.setText("Fecha de Devolución");

        Enviar.setBackground(new java.awt.Color(57, 57, 57));
        Enviar.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        Enviar.setForeground(new java.awt.Color(255, 255, 255));
        Enviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/enviar (1).png"))); // NOI18N
        Enviar.setText("Enviar");
        Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addComponent(Enviar, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dateAlquiler, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                    .addComponent(codProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(direccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateDevolucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(idAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(idMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(codProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addComponent(dateAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(dateDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Enviar)
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarActionPerformed
        insertarAlquiler();
        codProveedor.setText("");
        direccion.setText("");
        idAlquiler.setText("");
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
    private javax.swing.JTextField idAlquiler;
    private javax.swing.JTextField idMaquina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables
}
