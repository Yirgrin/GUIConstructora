
package Visual;

public class Inicio extends javax.swing.JFrame {

    public Inicio() {
        initComponents();
        
        //Yir. Instrucciones para que aparezca en el centro y no se pueda ajustar la pantalla. 
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CentralFrame = new javax.swing.JPanel();
        btnvolver2 = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        btninscribir = new javax.swing.JButton();
        btnlistaclientes = new javax.swing.JButton();
        btnmensualidad = new javax.swing.JButton();
        btnagendar = new javax.swing.JButton();
        btnvender = new javax.swing.JButton();
        btnvolver = new javax.swing.JButton();
        btnlistaEntrenadores = new javax.swing.JButton();
        btnvolver1 = new javax.swing.JButton();
        btndesplegar = new javax.swing.JButton();
        CentralFrame1 = new javax.swing.JPanel();

        CentralFrame.setBackground(new java.awt.Color(102, 102, 102));
        CentralFrame.setRequestFocusEnabled(false);

        javax.swing.GroupLayout CentralFrameLayout = new javax.swing.GroupLayout(CentralFrame);
        CentralFrame.setLayout(CentralFrameLayout);
        CentralFrameLayout.setHorizontalGroup(
            CentralFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 764, Short.MAX_VALUE)
        );
        CentralFrameLayout.setVerticalGroup(
            CentralFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        btnvolver2.setBackground(new java.awt.Color(57, 57, 57));
        btnvolver2.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        btnvolver2.setForeground(new java.awt.Color(255, 255, 255));
        btnvolver2.setText("Volver                             ");
        btnvolver2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnvolver2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnvolver2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnvolver2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolver2ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(102, 102, 102));
        panelPrincipal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelPrincipal.setForeground(new java.awt.Color(102, 102, 102));

        panelMenu.setBackground(new java.awt.Color(57, 57, 57));

        btninscribir.setBackground(new java.awt.Color(57, 57, 57));
        btninscribir.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        btninscribir.setForeground(new java.awt.Color(255, 255, 255));
        btninscribir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/users.png"))); // NOI18N
        btninscribir.setText("Gestión de Usuarios             ");
        btninscribir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btninscribir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btninscribir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btninscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btniGestionUsuariosActionPerformed(evt);
            }
        });

        btnlistaclientes.setBackground(new java.awt.Color(57, 57, 57));
        btnlistaclientes.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        btnlistaclientes.setForeground(new java.awt.Color(255, 255, 255));
        btnlistaclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cash-outline (1).png"))); // NOI18N
        btnlistaclientes.setText("Gestión de Planillas              ");
        btnlistaclientes.setActionCommand("Gestión de Planillas");
        btnlistaclientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnlistaclientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnlistaclientes.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnlistaclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlanillasActionPerformed(evt);
            }
        });

        btnmensualidad.setBackground(new java.awt.Color(57, 57, 57));
        btnmensualidad.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        btnmensualidad.setForeground(new java.awt.Color(255, 255, 255));
        btnmensualidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/calculator-outline.png"))); // NOI18N
        btnmensualidad.setText("Presupuestos                         ");
        btnmensualidad.setToolTipText("");
        btnmensualidad.setActionCommand("Presupuestos       ");
        btnmensualidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnmensualidad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnmensualidad.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnmensualidad.setMaximumSize(new java.awt.Dimension(638, 520));
        btnmensualidad.setMinimumSize(new java.awt.Dimension(638, 520));
        btnmensualidad.setPreferredSize(new java.awt.Dimension(638, 520));
        btnmensualidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPresupuestosActionPerformed(evt);
            }
        });

        btnagendar.setBackground(new java.awt.Color(57, 57, 57));
        btnagendar.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        btnagendar.setForeground(new java.awt.Color(255, 255, 255));
        btnagendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/construct-outline (1).png"))); // NOI18N
        btnagendar.setText("Gestión de Maquinaria        ");
        btnagendar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnagendar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnagendar.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnagendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaquinariaActionPerformed(evt);
            }
        });

        btnvender.setBackground(new java.awt.Color(57, 57, 57));
        btnvender.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        btnvender.setForeground(new java.awt.Color(255, 255, 255));
        btnvender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/truck.png"))); // NOI18N
        btnvender.setText("Alquileres                               ");
        btnvender.setToolTipText("");
        btnvender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnvender.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnvender.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnvender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnalquileresActionPerformed(evt);
            }
        });

        btnvolver.setBackground(new java.awt.Color(57, 57, 57));
        btnvolver.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        btnvolver.setForeground(new java.awt.Color(255, 255, 255));
        btnvolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/calendar-outline.png"))); // NOI18N
        btnvolver.setText("Actividades                            ");
        btnvolver.setToolTipText("");
        btnvolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnvolver.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnvolver.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActividadesActionPerformed(evt);
            }
        });

        btnlistaEntrenadores.setBackground(new java.awt.Color(57, 57, 57));
        btnlistaEntrenadores.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        btnlistaEntrenadores.setForeground(new java.awt.Color(255, 255, 255));
        btnlistaEntrenadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/home-outline.png"))); // NOI18N
        btnlistaEntrenadores.setText("Proyectos                                ");
        btnlistaEntrenadores.setToolTipText("");
        btnlistaEntrenadores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnlistaEntrenadores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnlistaEntrenadores.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnlistaEntrenadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProyectosActionPerformed(evt);
            }
        });

        btnvolver1.setBackground(new java.awt.Color(57, 57, 57));
        btnvolver1.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        btnvolver1.setForeground(new java.awt.Color(255, 255, 255));
        btnvolver1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard.png"))); // NOI18N
        btnvolver1.setText("Gestión de Tareas                 ");
        btnvolver1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnvolver1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnvolver1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnvolver1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTareasActionPerformed(evt);
            }
        });

        btndesplegar.setBackground(new java.awt.Color(57, 57, 57));
        btndesplegar.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        btndesplegar.setForeground(new java.awt.Color(255, 255, 255));
        btndesplegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/return-down-back-outline.png"))); // NOI18N
        btndesplegar.setText(" Volver                                    ");
        btndesplegar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btndesplegar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btndesplegar.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btndesplegar.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                btndesplegarComponentAdded(evt);
            }
        });
        btndesplegar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndesplegarMouseClicked(evt);
            }
        });
        btndesplegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btninscribir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnlistaclientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmensualidad, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnagendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnvender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnlistaEntrenadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnvolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnvolver1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndesplegar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btninscribir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnlistaclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnmensualidad, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnagendar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnvender, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnlistaEntrenadores, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnvolver, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnvolver1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btndesplegar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnlistaEntrenadores.getAccessibleContext().setAccessibleName("Actividades");

        CentralFrame1.setBackground(new java.awt.Color(102, 102, 102));
        CentralFrame1.setRequestFocusEnabled(false);

        javax.swing.GroupLayout CentralFrame1Layout = new javax.swing.GroupLayout(CentralFrame1);
        CentralFrame1.setLayout(CentralFrame1Layout);
        CentralFrame1Layout.setHorizontalGroup(
            CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 774, Short.MAX_VALUE)
        );
        CentralFrame1Layout.setVerticalGroup(
            CentralFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CentralFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CentralFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btndesplegarComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_btndesplegarComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_btndesplegarComponentAdded

    private void btndesplegarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndesplegarMouseClicked

    }//GEN-LAST:event_btndesplegarMouseClicked

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
    
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btniGestionUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btniGestionUsuariosActionPerformed
       
    }//GEN-LAST:event_btniGestionUsuariosActionPerformed

    private void btnPlanillasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlanillasActionPerformed
       
    }//GEN-LAST:event_btnPlanillasActionPerformed

    private void btnPresupuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPresupuestosActionPerformed
        
    }//GEN-LAST:event_btnPresupuestosActionPerformed

    private void btnMaquinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaquinariaActionPerformed
      
    }//GEN-LAST:event_btnMaquinariaActionPerformed

    private void btnalquileresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnalquileresActionPerformed
        
    }//GEN-LAST:event_btnalquileresActionPerformed

    private void btnActividadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActividadesActionPerformed
        
    }//GEN-LAST:event_btnActividadesActionPerformed

    private void btnProyectosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProyectosActionPerformed
        
    }//GEN-LAST:event_btnProyectosActionPerformed

    private void btnTareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTareasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTareasActionPerformed

    private void btnvolver2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolver2ActionPerformed
       
    }//GEN-LAST:event_btnvolver2ActionPerformed

  
    public static void main(String args[]) {
     java.awt.EventQueue.invokeLater(() -> {
         new Inicio().setVisible(true);
     });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CentralFrame;
    private javax.swing.JPanel CentralFrame1;
    private javax.swing.JButton btnagendar;
    private javax.swing.JButton btndesplegar;
    private javax.swing.JButton btninscribir;
    private javax.swing.JButton btnlistaEntrenadores;
    private javax.swing.JButton btnlistaclientes;
    private javax.swing.JButton btnmensualidad;
    private javax.swing.JButton btnvender;
    private javax.swing.JButton btnvolver;
    private javax.swing.JButton btnvolver1;
    private javax.swing.JButton btnvolver2;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
