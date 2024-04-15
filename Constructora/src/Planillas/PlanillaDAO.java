
package Planillas;
import ConexionBD.OracleDBManager;
import java.beans.Statement;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import oracle.jdbc.OracleTypes;

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
                System.out.println("La planilla se ha insertado correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar la planilla: " + e.getMessage());
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
            System.out.println("La consulta se ejecutó correctamente.");
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
            System.out.println("Planilla eliminado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar la Planilla: " + e.getMessage());
        }
    }
    
    public static void editarPlanilla(int planillaId, int usuarioId, Date FechInicio, Date FechFin, int horasSemanales, int salarioHora) {
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
            System.out.println("Planilla editado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al editadar la planilla: " + e.getMessage());
        }
    }
    
    
}


