
package Visual;
import Actividades.Actividades;
import Alquileres.Alquileres;
import Proyectos.Proyectos;
import Usuarios.FrameUsuarios;
import Tareas.Tareas;
import Planillas.FramePlanillas; 
import Presupuestos.FramePresupuestos;
import Maquinaria.FrameMaquinaria;

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
                    .addComponent(btnvolver1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(62, Short.MAX_VALUE))
        );

        btnlistaEntrenadores.getAccessibleContext().setAccessibleName("Actividades");

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(786, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnvolver2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolver2ActionPerformed
       
    }//GEN-LAST:event_btnvolver2ActionPerformed

    private void btnTareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTareasActionPerformed
        this.dispose();
        Tareas nuevaTarea = new Tareas();
        nuevaTarea.setVisible(true);
    }//GEN-LAST:event_btnTareasActionPerformed

    private void btnProyectosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProyectosActionPerformed
        this.dispose();
        Proyectos nuevoProyecto = new Proyectos();
        nuevoProyecto.setVisible(true);
    }//GEN-LAST:event_btnProyectosActionPerformed

    private void btnActividadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActividadesActionPerformed
        this.dispose();
        Actividades nuevaActividad = new Actividades();
        nuevaActividad.setVisible(true);
    }//GEN-LAST:event_btnActividadesActionPerformed

    private void btnalquileresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnalquileresActionPerformed
        this.dispose();
        Alquileres nuevoAlquiler = new Alquileres();
        nuevoAlquiler.setVisible(true);
    }//GEN-LAST:event_btnalquileresActionPerformed

    private void btnMaquinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaquinariaActionPerformed
        this.dispose();
        FrameMaquinaria nuevoMaquina = new FrameMaquinaria();
        nuevoMaquina.setVisible(true);
    }//GEN-LAST:event_btnMaquinariaActionPerformed

    private void btnPresupuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPresupuestosActionPerformed
        this.dispose();
        FramePresupuestos nuevoPresupuesto = new FramePresupuestos();
        nuevoPresupuesto.setVisible(true);
    }//GEN-LAST:event_btnPresupuestosActionPerformed

    private void btnPlanillasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlanillasActionPerformed
        this.dispose();
        FramePlanillas nuevaPlanilla = new FramePlanillas();
        nuevaPlanilla.setVisible(true);
    }//GEN-LAST:event_btnPlanillasActionPerformed

    private void btniGestionUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btniGestionUsuariosActionPerformed
        this.dispose();
        FrameUsuarios nuevoUsuario = new FrameUsuarios();
        nuevoUsuario.setVisible(true);
    }//GEN-LAST:event_btniGestionUsuariosActionPerformed

  
    public static void main(String args[]) {
     java.awt.EventQueue.invokeLater(() -> {
         new Inicio().setVisible(true);
     });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CentralFrame;
    private javax.swing.JButton btnagendar;
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
