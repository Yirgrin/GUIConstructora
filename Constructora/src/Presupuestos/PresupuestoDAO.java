
package Presupuestos;
import ConexionBD.OracleDBManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;


public class PresupuestoDAO {
    private static final OracleDBManager dbManager = new OracleDBManager();
    
    public static void insertarPresupuesto(int totalMateriales, int manoDeObra, String otrosGastos) {
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
    
    public ResultSet obtenerPresupuestos() {
    Connection conexion = dbManager.conectar();
    try {
        // Llama al procedimiento almacenado y registra el parámetro de salida para el cursor de resultados
        CallableStatement statement = conexion.prepareCall("{call sp_mostrar_presupuestos(?)}");
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
    
    public void eliminarPresupuesto(int usuarioId) {
        try (Connection connection = dbManager.conectar();
            CallableStatement statement = connection.prepareCall("{call sp_eliminar_presupuesto(?)}")) {
            statement.setInt(1, usuarioId);
            statement.execute();
            System.out.println("presupuesto eliminado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el presupuesto: " + e.getMessage());
        }
    }
    
    public void editarPresupuesto(int idPresupuesto, int totalMateriales, int manoDeObra, String otrosGastos) {
        try (Connection conexion = dbManager.conectar()) {
            String sql = "{call sp_actualizar_presupuesto(?, ?, ?, ?)}";
            try (PreparedStatement statement = conexion.prepareCall(sql)) {
                statement.setInt(1, idPresupuesto);
                statement.setInt(2, totalMateriales);
                statement.setInt(3, manoDeObra);
                statement.setString(4, otrosGastos);
                
                statement.executeUpdate();
                System.out.println("El presupuesto se ha editado correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al editar el presupuesto: " + e.getMessage());
        } finally {
            dbManager.desconectar();
        }
    }
}

