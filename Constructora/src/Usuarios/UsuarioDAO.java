
package Usuarios;

import ConexionBD.OracleDBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

public class UsuarioDAO {

    private static final OracleDBManager dbManager = new OracleDBManager();
    
    // Inserta un usuario utilizando el procedimiento almacenado
    public static void insertarUsuario(int usuarioId, String nombre, String apellidos, String telefono, String correoElectronico, String cargo, Date fechaContratacion) {   
        try (Connection conexion = dbManager.conectar()) {
            // Llama al procedimiento almacenado
            String sql = "{call sp_insertar_usuario(?, ?, ?, ?, ?, ?, ?)}";
            try (PreparedStatement statement = conexion.prepareCall(sql)) {
                java.sql.Date sqlFechaContratacion = new java.sql.Date(fechaContratacion.getTime());
                statement.setInt(1, usuarioId);
                statement.setString(2, nombre);
                statement.setString(3, apellidos);
                statement.setString(4, telefono);
                statement.setString(5, correoElectronico);
                statement.setString(6, cargo);
                statement.setDate(7, sqlFechaContratacion);
                statement.executeUpdate();
                System.out.println("La sentencia se ejecuto correctamente.1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al ejecutar la sentencia: " + e.getMessage());
        } finally {
            dbManager.desconectar();
        }
    }
        
   public ResultSet obtenerUsuarios() {
    OracleDBManager dbManager = new OracleDBManager();
    Connection conexion = dbManager.conectar();
    try {
        // Llama al procedimiento almacenado y registra el parámetro de salida para el cursor de resultados
        CallableStatement statement = conexion.prepareCall("{call sp_mostrar_usuarios(?)}");
        statement.registerOutParameter(1, OracleTypes.CURSOR);
        statement.execute();
        
        // Obtiene el cursor de resultados del procedimiento almacenado
        ResultSet resultSet = (ResultSet) statement.getObject(1);
        
        // Comprueba si hay un conjunto de resultados disponible
        if (resultSet != null) {
            System.out.println("La sentencia se ejecuto correctamente.2");
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
   
   public void eliminarUsuario(int usuarioId) {
        try (Connection connection = dbManager.conectar();
            CallableStatement statement = connection.prepareCall("{call sp_eliminar_usuario(?)}")) {
            statement.setInt(1, usuarioId);
            statement.execute();
            System.out.println("Usuario eliminado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}


