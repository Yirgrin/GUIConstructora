
package Presupuestos;
import ConexionBD.OracleDBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PresupuestoDAO {
    
    public static void insertarPresupuesto(int totalMateriales, int manoDeObra, String otrosGastos) {
        OracleDBManager dbManager = new OracleDBManager();
        try (Connection conexion = dbManager.conectar()) {
            String sql = "{call sp_insertar_presupuesto(?, ?, ?)}";
            try (PreparedStatement statement = conexion.prepareCall(sql)) {
                statement.setInt(1, totalMateriales);
                statement.setInt(2, manoDeObra);
                statement.setString(3, otrosGastos);
                
                statement.executeUpdate();
                System.out.println("El presupuesto se ha insertado correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar el presupuesto: " + e.getMessage());
        } finally {
            dbManager.desconectar();
        }
    }
}
