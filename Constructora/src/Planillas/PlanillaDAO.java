
package Planillas;
import ConexionBD.OracleDBManager;
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
        // Llama al procedimiento almacenado y registra el parámetro de salida para el cursor de resultados
        CallableStatement statement = conexion.prepareCall("{call sp_mostrar_planillas(?)}");
        statement.registerOutParameter(1, OracleTypes.CURSOR);
        statement.execute();
        
        // Obtiene el cursor de resultados del procedimiento almacenado
        ResultSet resultSet = (ResultSet) statement.getObject(1);
        
        // Comprueba si hay un conjunto de resultados disponible
        if (resultSet != null) {
            System.out.println("La sentencia se ejecuto correctamente.");
            return resultSet;
        } else {
            System.out.println("No se encontraron resultados.");
            return null;
        }
    } catch (SQLException e) {
        System.out.println("Error al ejecutar la sentencia: " + e.getMessage());
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
}


