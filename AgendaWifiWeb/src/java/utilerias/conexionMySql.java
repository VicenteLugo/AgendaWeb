package utez.appfon.utilerias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class conexionMySql {

    private static String ipAddress;
    private static String dbName;
    private static String user;
    private static String password;
    private static String portservice;
    private static ResourceBundle propertiesConexion;

    /**
     * Método que carga el driver, establece la conexión.
     *
     * @return 
     * @throws java.sql.SQLException
     * 
     *
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLException: "+e);
        }

        if (propertiesConexion == null) {
            propertiesConexion = ResourceBundle.getBundle("mysql_properties");
            ipAddress = propertiesConexion.getString("ip_address");
            dbName = propertiesConexion.getString("db_name");
            user = propertiesConexion.getString("user");
            password = propertiesConexion.getString("password");
            portservice = propertiesConexion.getString("port_service");
            
        }

        return DriverManager.getConnection("jdbc:mysql://" + ipAddress + ":" + portservice + "/" + dbName, user, password);
    }

    public static void main(String[] args) {
        try {
            Connection con = getConnection();
            System.out.println("Conexion efectuada... "+con);
            con.close();
        } catch (SQLException e) {
            System.out.println("No efectuada");
        }
    }
}
