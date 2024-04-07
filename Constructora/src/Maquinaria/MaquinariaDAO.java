package Maquinaria;
import ConexionBD.OracleDBManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;


public class MaquinariaDAO {
    private static final OracleDBManager dbManager = new OracleDBManager();
    
    public static void insertarMaquina(String nombre, String descripcion, double precio, int unidadesTotales) {
        try (Connection conexion = dbManager.conectar()) {
            String sql = "{call sp_insertar_maquina(?, ?, ?, ?)}";
            try (PreparedStatement statement = conexion.prepareCall(sql)) {
                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setDouble(3, precio);
                statement.setInt(4, unidadesTotales);
                
                statement.executeUpdate();
                System.out.println("La máquina se insertó correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar la máquina: " + e.getMessage());
        } finally {
            dbManager.desconectar();
        }
    }

    public ResultSet obtenerMaquinas() {
        Connection conexion = dbManager.conectar();
        try {
            // Llama al procedimiento almacenado y registra el parámetro de salida para el cursor de resultados
            CallableStatement statement = conexion.prepareCall("{call sp_mostrar_maquinas(?)}");
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.execute();

            // Obtiene el cursor de resultados del procedimiento almacenado
            ResultSet resultSet = (ResultSet) statement.getObject(1);

            // Comprueba si hay un conjunto de resultados disponible
            if (resultSet != null) {
                System.out.println("La sentencia se ejecutó correctamente.");
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

    public void eliminarMaquina(int usuarioId) {
        try (Connection connection = dbManager.conectar();
            CallableStatement statement = connection.prepareCall("{call sp_eliminar_maquina(?)}")) {
            statement.setInt(1, usuarioId);
            statement.execute();
            System.out.println("Maquina eliminado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar maquina: " + e.getMessage());
        }
    }
}
