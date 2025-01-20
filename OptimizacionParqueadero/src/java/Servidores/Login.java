package Servidores;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import Clases.*;
import Clases.IniciarSesion;
import java.io.File;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Business Coffee
 */
@MultipartConfig

public class Login extends HttpServlet {

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
        if(request.getParameter("btnRegistrar") != null){
            btnRegistrar(request, response);
        }else if(request.getParameter("btnRegistrarUser") != null){
            btnRegistrarUser(request, response);
        }else if(request.getParameter("btnEliminarUser")!= null){
            btnEliminarUser(request, response);
        }
    }
    
    public void btnRegistrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        try{
            OperacionesAdminBD obOperacion = new OperacionesAdminBD();
            
            obOperacion.setNombreUser(request.getParameter("NombreUser"));
            obOperacion.setPlacaUser(request.getParameter("PlacaUser"));
            obOperacion.setTipoAutomovil(request.getParameter("AutoUser"));
            obOperacion.addUsuario(obOperacion);

            
            request.getRequestDispatcher("MenuP.jsp").forward(request, response);
            
        }catch(Exception e){
            request.setAttribute("stMensaje", e);
            request.setAttribute("stTipo", "Error");
            
            request.getRequestDispatcher("MenuP.jsp").forward(request, response);
        }
    }
    
    public void btnRegistrarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            OperacionesAdminBD obOperacion = new OperacionesAdminBD();
            
            obOperacion.setNombreUser(request.getParameter("NombreUser"));
            obOperacion.setPlacaUser(request.getParameter("PlacaUser"));
            obOperacion.setTipoAutomovil(request.getParameter("AutoUser"));
            obOperacion.addUsuario(obOperacion);
            Thread.sleep(2*1000);

            
            request.getRequestDispatcher("MenuPUser.jsp").forward(request, response);
            
        }catch(Exception e){
            request.setAttribute("stMensaje", e);
            request.setAttribute("stTipo", "Error");
            
            request.getRequestDispatcher("MenuPUser.jsp").forward(request, response);
        }
    }
    
    public void btnEliminarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            OperacionesAdminBD obOperacion = new OperacionesAdminBD();
            
            obOperacion.setNombreUser(request.getParameter("NombreUser"));
            obOperacion.setPlacaUser(request.getParameter("PlacaUser"));
            obOperacion.eliminarUser(obOperacion);
            
            request.getRequestDispatcher("MenuP.jsp").forward(request, response);
            
        }catch(Exception e){
            request.setAttribute("stMensaje", e);
            request.setAttribute("stTipo", "Error");
            
            request.getRequestDispatcher("MenuP.jsp").forward(request, response);
        }
    }
    
    
    private boolean valExtens(String fileName, String[] extensions){
        for(String et: extensions){
            if(fileName.toLowerCase().endsWith(et)){
                return true;
            }
        }
        return false;
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
        String pagina = request.getParameter("pagina");
        
        if (pagina.equals("IniciarSesion")) {
            HttpSession sesion = request.getSession();
            int val=0,validacion=0;
            String email = new String(request.getParameter("Correo").getBytes("ISO-8859-1"),"UTF-8");
            String contra = new String(request.getParameter("Contra").getBytes("ISO-8859-1"),"UTF-8");
            String perfil = new String(request.getParameter("Perfil").getBytes("ISO-8859-1"),"UTF-8");
            IniciarSesion conexion = new IniciarSesion(email, contra, perfil);
            OperacionesClienteBD cliente= new OperacionesClienteBD();
            OperacionesAdminBD admin= new OperacionesAdminBD();
            val = conexion.ValidarInicio();
            validacion = conexion.ValidarInicio();
            System.out.println(validacion);
            switch (validacion) {
                case 1: {
                    sesion.setAttribute("codigo", conexion.getCodigo());
                    sesion.setAttribute("nombre", conexion.getNombre());
                    OperacionesClienteBD infoCliente= cliente.mostrarInfoCliente(conexion.getCodigo());
                    sesion.setAttribute("infoCliente", infoCliente);
                    RequestDispatcher despachador = request.getRequestDispatcher("MenuPUser.jsp");
                    despachador.forward(request, response);
                    break;
                }
                case 2: {
                    sesion.setAttribute("codigo", conexion.getCodigo());
                    sesion.setAttribute("nombre", conexion.getNombre());
                    OperacionesAdminBD infoAdmin= admin.mostrarInfoAdmin(conexion.getCodigo());
                    sesion.setAttribute("infoAdmin", infoAdmin);
                    RequestDispatcher despachador = request.getRequestDispatcher("MenuP.jsp");
                    despachador.forward(request, response);
                    break;
                }
                default: {
                    request.setAttribute("valido", validacion);
                    RequestDispatcher despachador = request.getRequestDispatcher("Login.jsp");
                    despachador.forward(request, response);
                }
            }
        }
        else if (pagina.equals("EliminarProducto")) {
            String NombreUser = request.getParameter("NombreUser");
            String PlacaUser= request.getParameter("PlacaUser");
            OperacionesUser operPro= new OperacionesUser();
            operPro.eliminarProducto(NombreUser, PlacaUser);
            ImgProductoDao daoImg= new ImgProductoDao();
            MostrarProductos p= new MostrarProductos();
            List<MostrarProductos>lista= daoImg.listarProducto();
            request.setAttribute("lista", lista);
            RequestDispatcher despachador = request.getRequestDispatcher("MenuP.jsp");
            despachador.forward(request, response);
        }
        
         else if (pagina.equals("EditarAdmin")) {
            RequestDispatcher despachador = request.getRequestDispatcher("PerfilAdmin.jsp");
            despachador.forward(request, response);
        } else if (pagina.equals("Inicializar")) {
            int val=0,validacion=0;
            request.setAttribute("correoValido", val);
            request.setAttribute("valido", validacion);
            System.out.println("Inicializarr   "+val+" o   "+validacion);
        } else {
            processRequest(request, response);
        }

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
