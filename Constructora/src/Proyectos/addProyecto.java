/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Proyectos;

import ConexionBD.OracleDBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Melvin
 */
public class addProyecto extends javax.swing.JPanel {
    OracleDBManager conexion = new OracleDBManager();
    /**
     * Creates new form addProyecto
     */
    public addProyecto() {
        initComponents();
    }
    
    private void insertarProyecto() {
        int idproyectoValue = Integer.parseInt(idProyecto.getText());
        String codProyectoValue = codProyecto.getText();
        String nameProyectoValue = nameProyecto.getText();
        String descProyectoValue = descProyecto.getText();
        Date inicioProyectoValue = inicioProyecto.getDate();
        Date finProyectoValue = finProyecto.getDate();
        String dataClienteValue = dataCliente.getText();

        // Sentencia SQL para insertar un nuevo Proyecto
        String sql = "INSERT INTO PROYECTOS (proyecto_id, codigo_proyecto, nombre, descripcion, fecha_inicio, fecha_finalizacion, cliente_datos) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Obtener la conexión desde OracleDBManager
            Connection conn = conexion.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Establecer los valores de los parámetros
            pstmt.setInt(1, idproyectoValue);
            pstmt.setString(2, codProyectoValue);
            pstmt.setString(3, nameProyectoValue);
            pstmt.setString(4, descProyectoValue);
            pstmt.setDate(5, new java.sql.Date(inicioProyectoValue.getTime()));
            pstmt.setDate(6, new java.sql.Date(finProyectoValue.getTime()));
            pstmt.setString(7, dataClienteValue);

            // Ejecutar la inserción
            pstmt.executeUpdate();
            System.out.println("Proyecto insertado correctamente.");
            conexion.desconectar();
        } catch (SQLException e) {
            System.out.println("Error al insertar proyecto: " + e.getMessage());
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
        finProyecto = new com.toedter.calendar.JDateChooser();
        inicioProyecto = new com.toedter.calendar.JDateChooser();
        dataCliente = new javax.swing.JTextField();
        nameProyecto = new javax.swing.JTextField();
        codProyecto = new javax.swing.JTextField();
        idProyecto = new javax.swing.JTextField();
        Enviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        descProyecto = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 102));
        setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Identificación del Proyecto:");

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Código del Presupuesto:");

        jLabel3.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Descripción:");

        jLabel5.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha de Inicio:");
        jLabel5.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Fecha de Finalización:");

        jLabel7.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Datos del Cliente:");

        finProyecto.setBackground(new java.awt.Color(187, 187, 187));

        inicioProyecto.setBackground(new java.awt.Color(187, 187, 187));

        dataCliente.setBackground(new java.awt.Color(187, 187, 187));

        nameProyecto.setBackground(new java.awt.Color(187, 187, 187));

        codProyecto.setBackground(new java.awt.Color(187, 187, 187));

        idProyecto.setBackground(new java.awt.Color(187, 187, 187));

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

        descProyecto.setBackground(new java.awt.Color(187, 187, 187));
        descProyecto.setColumns(20);
        descProyecto.setRows(5);
        jScrollPane1.setViewportView(descProyecto);

        jLabel8.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nuevo Proyecto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4))
                                .addGap(108, 108, 108)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(finProyecto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inicioProyecto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dataCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(idProyecto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                        .addComponent(codProyecto, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nameProyecto, javax.swing.GroupLayout.Alignment.LEADING))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(inicioProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(28, 28, 28)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(finProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dataCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(222, 222, 222)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarActionPerformed
        insertarProyecto();
        nameProyecto.setText("");
        descProyecto.setText("");
        idProyecto.setText("");
        codProyecto.setText("");
        dataCliente.setText("");
        inicioProyecto.setDate(new Date());
        finProyecto.setDate(new Date());
    }//GEN-LAST:event_EnviarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Enviar;
    private javax.swing.JTextField codProyecto;
    private javax.swing.JTextField dataCliente;
    private javax.swing.JTextArea descProyecto;
    private com.toedter.calendar.JDateChooser finProyecto;
    private javax.swing.JTextField idProyecto;
    private com.toedter.calendar.JDateChooser inicioProyecto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameProyecto;
    // End of variables declaration//GEN-END:variables
}
