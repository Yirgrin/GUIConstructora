
package Planillas;
import ConexionBD.OracleDBManager;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Types;
import javax.swing.JOptionPane;


public class PlanillaDAO {
    public static void insertarPlanilla(int usuarioId, Date FechInicio, Date FechFin, int horasSemanales, int salarioHora) {
        OracleDBManager dbManager = new OracleDBManager();
        try (Connection conexion = dbManager.conectar()) {
            String sql = "{call sp_insertar_planilla(?, ?, ?, ?, ?)}";
            try (PreparedStatement statement = conexion.prepareCall(sql)) {
                java.sql.Date sqlfechaInicio = new java.sql.Date(FechInicio.getTime());
                java.sql.Date sqlfechaFin = new java.sql.Date(FechFin.getTime());
                statement.setInt(1, usuarioId);
                statement.setDate(2, sqlfechaInicio);
                statement.setDate(3, sqlfechaFin);
                statement.setInt(4, horasSemanales);
                statement.setInt(5, salarioHora);
                
                statement.executeUpdate();
            }
        } catch (SQLException e) {
        if (e.getMessage().contains("ORA-20001") && e.getMessage().contains("No se permite un salario negativo")) {
            JOptionPane.showMessageDialog(null, "No se permite un salario negativo.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar la planilla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } finally {
        dbManager.desconectar();
    } 
   
    }
    
    private static final OracleDBManager dbManager = new OracleDBManager();
    
    public ResultSet obtenerPlanillas() {
    Connection conexion = dbManager.conectar();
    try {
        // Crea una consulta SQL para seleccionar datos de la vista
        String sql = "SELECT * FROM vw_vista_planillas";
        
        // Crea un PreparedStatement para ejecutar la consulta
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet != null) {
            return resultSet;
        } else {
            System.out.println("No se encontraron resultados.");
            return null;
        }
    } catch (SQLException e) {
        System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        return null;
    }
}

    public void eliminaPlanilla(int usuarioId) {
        try (Connection connection = dbManager.conectar();
            CallableStatement statement = connection.prepareCall("{call sp_eliminar_planilla(?)}")) {
            statement.setInt(1, usuarioId);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Error al eliminar la Planilla: " + e.getMessage());
        }
    }
    
    public static boolean editarPlanilla(int planillaId, int usuarioId, Date FechInicio, Date FechFin, int horasSemanales, int salarioHora) {
        try (Connection connection = dbManager.conectar();
            CallableStatement statement = connection.prepareCall("{call sp_actualizar_planilla(?, ?, ?, ?, ?, ?)}")) {
            java.sql.Date sqlfechaInicio = new java.sql.Date(FechInicio.getTime());
            java.sql.Date sqlfechaFin = new java.sql.Date(FechFin.getTime());
            statement.setInt(1, planillaId);
            statement.setInt(2, usuarioId);
            statement.setDate(3, sqlfechaInicio);
            statement.setDate(4, sqlfechaFin);
            statement.setInt(5, horasSemanales);
            statement.setInt(6, salarioHora);
            statement.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains("ORA-20001") && e.getMessage().contains("No se permite un salario negativo")) {
                JOptionPane.showMessageDialog(null, "No se permite un salario negativo.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al insertar la planilla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } finally {
            dbManager.desconectar();
        }    
        return true;
    }
    
    public int CalcularPlanilla(int idPlanilla) {
    int salarioSemanal = 0;
    try (Connection conexion = dbManager.conectar();
         CallableStatement statement = conexion.prepareCall("{ ? = call fn_calcular_salario_semanal(?) }")) {

        statement.registerOutParameter(1, Types.NUMERIC);
        statement.setInt(2, idPlanilla);
        statement.execute();

        salarioSemanal = statement.getInt(1);

    } catch (SQLException e) {
        System.out.println("Error al calcular el salario semanal: " + e.getMessage());
    }
    return salarioSemanal;
}


}


