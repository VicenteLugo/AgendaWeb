package dao;

import bean.PersonaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utez.appfon.utilerias.conexionMySql;
import utilerias.ConexionSQLServer;

/**
 *
 * @author LugoD
 */
public class PersonaDao {
    
    public boolean registrarPersona(PersonaBean bean){
        try {
            Connection con = ConexionSQLServer.getConnection();
            String query = "INSERT INTO Personas (nombre,apellidos,sexo,fechaNac,correo,telefono,imagen)"
                    + "VALUES (?,?,?,?,?,?,?);";
            PreparedStatement pstm = con.prepareCall(query);
            pstm.setString(1, bean.getNombre());
            pstm.setString(2, bean.getApellidos());
            pstm.setString(3, bean.getSexo());
            pstm.setString(4, bean.getFechaNac());
            pstm.setString(5, bean.getEmail());
            pstm.setString(6, bean.getTelefono());
            pstm.setString(7, bean.getImagen());
            
            if(pstm.executeUpdate() == 1){
                return true;
            }
            pstm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<PersonaBean> consultarPersonas() {
        List<PersonaBean> list = new ArrayList();
        try {
            Connection conexion = ConexionSQLServer.getConnection();
            String query = "SELECT * FROM Persona order by apellidos ASC ";
            PreparedStatement pstm = conexion.prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                PersonaBean bean = new PersonaBean();
                bean.setIdPersona(res.getInt("idPersona"));
                bean.setNombre(res.getString("nombre"));
                bean.setApellidos(res.getString("apellidos"));
                bean.setSexo(res.getString("sexo"));
                bean.setFechaNac(res.getString("fechaNac"));
                bean.setEmail(res.getString("correo"));
                bean.setTelefono(res.getString("telefono"));
                bean.setImagen(res.getString("imagen"));
                list.add(bean);
            }
            res.close();
            pstm.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean modificarPersona(PersonaBean bean){
        try {
            Connection con = conexionMySql.getConnection();
            String query = "UPDATE Persona SET Nombre = ?, "
                    + "Apellidos=?, "
                    + "Sexo=? "
                    + "fechaNac=?, "
                    + "Email=?, "
                    + "Telefono=?, " 
                    + "Imagen=?, "
                    + "WHERE idPersona=?";
            PreparedStatement pstm = con.prepareCall(query);
            pstm.setString(1, bean.getNombre());
            pstm.setString(2, bean.getApellidos());
            pstm.setString(7, bean.getSexo());
            pstm.setString(3, bean.getFechaNac());
            pstm.setString(4, bean.getEmail());
            pstm.setString(5, bean.getTelefono());
            pstm.setString(6, bean.getImagen());
            pstm.setInt(8, bean.getIdPersona());
            if(pstm.executeUpdate() == 1){
                return true;
            }
            pstm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean eliminarPersona(PersonaBean bean) {
        try {
            Connection con = conexionMySql.getConnection();
            String query = "DELETE FROM Persona WHERE idPersona=?;";
            PreparedStatement pstm = con.prepareCall(query);
            pstm.setInt(1, bean.getIdPersona());
            pstm.execute();
            pstm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
