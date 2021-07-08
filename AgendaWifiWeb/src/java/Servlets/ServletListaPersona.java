package Servlets;

import bean.PersonaBean;
import com.google.gson.Gson;
import dao.PersonaDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LugoD
 */
public class ServletListaPersona extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        List<PersonaBean> personas =new ArrayList();
        
//        String nombre = request.getParameter("nombre");
//        String apellidos = request.getParameter("apellidos");
//        String sexo = request.getParameter("sexo");
//        String fechaNac = request.getParameter("fechaNac");
//        String correo = request.getParameter("correo");
//        String telefono = request.getParameter("telefono");        
//        String img = request.getParameter("img");
//        PersonaBean bean = new PersonaBean();
//        bean.setNombre(nombre);
//        bean.setApellidos(apellidos);
//        bean.setFechaNac(fechaNac);
//        bean.setEmail(correo);
//        bean.setTelefono(telefono);
//        bean.setSexo(sexo);
//        bean.setImagen(img);
//        PersonaDao daoP = new PersonaDao();
//        if(daoP.registrarPersona(bean)){
//            response.getWriter().write("1");
//        } else {
//            response.getWriter().write("0");
//        }
        
        PersonaBean p = new PersonaBean();
        
        p.setNombre(request.getParameter("nombre"));
        p.setApellidos(request.getParameter("apellidos"));        
        p.setFechaNac(request.getParameter("fechaNac"));   
        p.setEmail(request.getParameter("correo"));
        p.setSexo(request.getParameter("sexo"));
        p.setTelefono(request.getParameter("telefono"));
        p.setImagen(request.getParameter("imagen"));  
        
        PersonaDao dao = new PersonaDao();
        
        dao.registrarPersona(p);
        
        Gson gson=new Gson();
        String fragmentoJSON=gson.toJson(p);
        String json="{\"lista\":"+fragmentoJSON+"}";
        response.getWriter().write(json); 
        
       
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
