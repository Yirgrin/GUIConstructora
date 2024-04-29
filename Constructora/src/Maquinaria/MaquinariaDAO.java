package Maquinaria;
import ConexionBD.OracleDBManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
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
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("ORA-20001") && e.getMessage().contains("El precio y/o las unidades de la maquinaria no pueden ser negativos.")) {
                JOptionPane.showMessageDialog(null, "El precio y/o las unidades de la maquinaria no pueden ser negativos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al insertar la máquina: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
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

    public void eliminarMaquina(int maquinaId) {
        try (Connection connection = dbManager.conectar();
            CallableStatement statement = connection.prepareCall("{call sp_eliminar_maquina(?)}")) {
            statement.setInt(1, maquinaId);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Error al eliminar maquina: " + e.getMessage());
        }
    }
    
     public static boolean editarMaquina(int maquinaId, String nombre, String descripcion, double precio, int unidadesTotales) {
        try (Connection conexion = dbManager.conectar()) {
            String sql = "{call sp_actualizar_maquina(?, ?, ?, ?, ?)}";
            try (PreparedStatement statement = conexion.prepareCall(sql)) {
                statement.setInt(1, maquinaId);
                statement.setString(2, nombre);
                statement.setString(3, descripcion);
                statement.setDouble(4, precio);
                statement.setInt(5, unidadesTotales);
                
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("ORA-20001") && e.getMessage().contains("El precio y/o las unidades de la maquinaria no pueden ser negativos.")) {
                JOptionPane.showMessageDialog(null, "El precio y/o las unidades de la maquinaria no pueden ser negativos.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al insertar la máquina: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } finally {
            dbManager.desconectar();
        }    
        return true;
    }
    
}
