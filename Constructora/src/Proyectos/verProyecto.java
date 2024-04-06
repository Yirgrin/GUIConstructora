/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Proyectos;
import ConexionBD.OracleDBManager;
import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.util.Date;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Melvin
 */
public class verProyecto extends javax.swing.JPanel {
    OracleDBManager conexion = new OracleDBManager();
    JDateChooser dateInicio = new JDateChooser();
    JDateChooser dateFinalizacion = new JDateChooser();
    
    
    
    /**
     * Creates new form verProyecto
     */
    public verProyecto() {
        initComponents();
        dateInicio.setDateFormatString("yyyy-MM-dd");
        dateFinalizacion.setDateFormatString("yyyy-MM-dd");
        
    }
    
    public void mostrarProyecto() {
    // Sentencia SQL para llamar al procedimiento almacenado
    String sql = "{call sp_obtener_proyecto(?)}";

    try {
        // Obtener la conexión desde OracleDBManager
        Connection conn = conexion.conectar();
        CallableStatement stmt = conn.prepareCall(sql);

        // Crear un modelo de tabla para la jTable1
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        // Limpiar la tabla antes de agregar los datos
        model.setRowCount(0);

        // Parámetro de salida para los resultados del procedimiento almacenado
        stmt.registerOutParameter(1, OracleTypes.CURSOR);
        // Ejecutar el procedimiento almacenado
        stmt.execute();
        // Obtener el cursor de salida
        ResultSet rs = (ResultSet) stmt.getObject(1);
        // Llenar la tabla con los resultados de la consulta
        while (rs.next()) {
            Object[] row = new Object[7];
            for (int i = 0; i < 7; i++) {
                row[i] = rs.getObject(i + 1);
            }
            model.addRow(row);
        }
        // Cerrar recursos
        rs.close();
        stmt.close();
        conexion.desconectar();

    } catch (SQLException e) {
        System.out.println("Error al mostrar proyectos: " + e.getMessage());
    }
}

    
    public void borrarProyecto() {
    int selectedRow = jTable1.getSelectedRow();
    int proyectoId;
    if (selectedRow != -1) {
         proyectoId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();
        try {
            Connection conn = conexion.conectar();
            String sql = "{call sp_eliminar_proyecto(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, proyectoId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Proyecto eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el Proyecto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarProyecto();
}

    
    public void editProyecto() {
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        int proyectoId = ((BigDecimal) jTable1.getValueAt(selectedRow, 0)).intValue();
        int nuevoCodigoPresupuesto= Integer.parseInt(JOptionPane.showInputDialog(null, "Nuevo Código de Presupuesto:", "Editar Proyecto", JOptionPane.QUESTION_MESSAGE));
        String nuevoNombre = JOptionPane.showInputDialog(null, "Nuevo Nombre:", "Editar Proyecto", JOptionPane.QUESTION_MESSAGE);
        String nuevaDescripcion = JOptionPane.showInputDialog(null, "Nueva Descripción:", "Editar Proyecto", JOptionPane.QUESTION_MESSAGE);
        JPanel panelInicio = new JPanel(new GridLayout(2, 2));
        panelInicio.add(new JLabel("Nueva Fecha de Inicio:"));
        panelInicio.add(dateInicio);
        JOptionPane.showConfirmDialog(null, panelInicio, "Editar Proyecto", JOptionPane.OK_CANCEL_OPTION);
        JPanel panelFinalizacion = new JPanel(new GridLayout(2, 2));
        panelFinalizacion.add(new JLabel("Nueva Fecha de Finalización:"));
        panelFinalizacion.add(dateFinalizacion);
        JOptionPane.showConfirmDialog(null, panelFinalizacion, "Editar Proyecto",JOptionPane.OK_CANCEL_OPTION);
        String nuevoClienteDatos = JOptionPane.showInputDialog(null, "Nuevos Datos del Cliente:", "Editar Proyecto", JOptionPane.QUESTION_MESSAGE);
        Date fechaInicioUtil = dateInicio.getDate();
        Date fechaFinalizacionUtil = dateFinalizacion.getDate();
             
        java.sql.Date nuevaFechaInicio = new java.sql.Date(fechaInicioUtil.getTime());
        java.sql.Date nuevaFechaFinalizacion = new java.sql.Date(fechaFinalizacionUtil.getTime());

        try {
            Connection conn = conexion.conectar();
            String sql = "{call sp_actualizar_proyecto(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, proyectoId);
            stmt.setInt(2, nuevoCodigoPresupuesto); 
            stmt.setString(3, nuevoNombre);
            stmt.setString(4, nuevaDescripcion);
            stmt.setDate(5, nuevaFechaInicio);
            stmt.setDate(6, nuevaFechaFinalizacion);
            stmt.setString(7, nuevoClienteDatos);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Proyecto editado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar el proyecto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    mostrarProyecto();
}

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 102));

        jTable1.setBackground(new java.awt.Color(102, 102, 102));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Proyecto", "Cod_Proyecto", "Nombre", "Descripcion", "Fecha Inicio", "Fecha Finalizacion", "Datos del cliente"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(12);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(15);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Proyectos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(318, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(250, 250, 250))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
