
package Usuarios;

import ConexionBD.OracleDBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import Modelos.UsuariosModelo;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleTypes;

public class UsuarioDAO {

    // Inserta un usuario utilizando el procedimiento almacenado
    public static void insertarUsuario(int usuarioId, String nombre, String apellidos, String telefono, String correoElectronico, String cargo, Date fechaContratacion) {
        OracleDBManager dbManager = new OracleDBManager();
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
                System.out.println("La sentencia se ejecutó correctamente.");
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

}


