package Maquinaria;
import ConexionBD.OracleDBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MaquinariaDAO {
    public static void insertarMaquina(String nombre, String descripcion, double precio, int unidadesTotales) {
        OracleDBManager dbManager = new OracleDBManager();
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
}
