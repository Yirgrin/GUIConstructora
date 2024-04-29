
package Presupuestos;
import ConexionBD.OracleDBManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;


public class PresupuestoDAO {
    private static final OracleDBManager dbManager = new OracleDBManager();
    
    public static void insertarPresupuesto(int totalMateriales, int manoDeObra, int otrosGastos) {
        try (Connection conexion = dbManager.conectar()) {
            String sql = "{call sp_insertar_presupuesto(?, ?, ?)}";
            try (PreparedStatement statement = conexion.prepareCall(sql)) {
                statement.setInt(1, totalMateriales);
                statement.setInt(2, manoDeObra);
                statement.setInt(3, otrosGastos);
                
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("ORA-20001") && e.getMessage().contains("No se permiten montos negativos.")) {
                JOptionPane.showMessageDialog(null, "No se permiten montos negativos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al insertar el presupuesto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
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
        } catch (SQLException e) {
            System.out.println("Error al eliminar el presupuesto: " + e.getMessage());
        }
    }
    
    public boolean editarPresupuesto(int idPresupuesto, int totalMateriales, int manoDeObra, int otrosGastos) {
        try (Connection conexion = dbManager.conectar()) {
            String sql = "{call sp_actualizar_presupuesto(?, ?, ?, ?)}";
            try (PreparedStatement statement = conexion.prepareCall(sql)) {
                statement.setInt(1, idPresupuesto);
                statement.setInt(2, totalMateriales);
                statement.setInt(3, manoDeObra);
                statement.setInt(4, otrosGastos);
                
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("ORA-20001") && e.getMessage().contains("No se permiten montos negativos.")) {
                JOptionPane.showMessageDialog(null, "No se permiten montos negativos.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al insertar el presupuesto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } finally {
            dbManager.desconectar();
        } 
        return true;
    }

    public static int CalcularPresupuesto(int idPresupuesto) {
        int totalPresupuesto = 0;
        try (Connection conexion = dbManager.conectar();
             CallableStatement statement = conexion.prepareCall("{ ? = call fn_total_gastos_presupuesto(?) }")) {

            statement.registerOutParameter(1, Types.NUMERIC);
            statement.setInt(2, idPresupuesto);
            statement.execute();

            totalPresupuesto = statement.getInt(1);
        } catch (SQLException e) {
            System.out.println("Error al calcular el salario semanal: " + e.getMessage());
        }
        return totalPresupuesto;
    }

    public static ResultSet MontoMaxMin() {
        Connection conexion = dbManager.conectar();
        try {
            String sql = "SELECT tipo, presupuesto_id, monto_total, proyecto FROM vw_presupuesto_monto_mayor_menor";
            PreparedStatement statement = conexion.prepareStatement(sql);
            return statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            // Si ocurre un error, devuelve null
            return null;
        }
    }

}


