
package Planillas;
import ConexionBD.OracleDBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

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
}

