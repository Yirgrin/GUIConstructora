package ConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Melvin
 */
public class OracleDBManager {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "CONSTRUCTORA_USER";
    private static final String PASSWORD = "constructora2023";

    public Connection cadena;
    
    public OracleDBManager(){
    this.cadena = null;
    }
    // Método para obtener la conexión a la base de datos
    public Connection conectar(){
        try{
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (ClassNotFoundException | SQLException e){
            System.out.print("Error de conexion: " + e.getMessage());
        }
        return this.cadena;
    }
    // Método para cerrar la conexión
    public void desconectar() {
        try {
            this.cadena.close();
        } catch (SQLException e) {
            System.out.print("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
    /*public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new SQLException("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }

    // Método para cerrar la conexión
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }*/
