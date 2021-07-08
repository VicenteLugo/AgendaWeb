/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LugoD
 */
public class ConexionSQLServer {
    private static String ipAddress="localhost";
    private static String dbName="Contactos";
    private static String user="sa";
    private static String password="root";
    private static String service="1433";
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://" + ipAddress + ":" + service + ";databaseName=" + dbName;
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) throws SQLException {
        Connection con = ConexionSQLServer.getConnection();

        if (con != null) {
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
        }
    }
}
