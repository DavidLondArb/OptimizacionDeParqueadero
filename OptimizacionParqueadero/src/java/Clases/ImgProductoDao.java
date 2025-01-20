package Clases;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ImgProductoDao {
    Connection conexion = null;
    String bd="optimizacionparqueo";
    String url="jdbc:mysql://localhost:3306/"+bd;
    String user="root";
    String pass="";
    int codigoAdminProEs=0;
    PreparedStatement ps;
    ResultSet res;    
    
    public List listarProducto(){
        List<MostrarProductos>lista= new ArrayList<MostrarProductos>();
        String sql = "select * from usuariosparqueo";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                InputStream bImg = null;
                byte[] imgData = null ;
                conexion= DriverManager.getConnection(url,user,pass);
                ps=conexion.prepareStatement(sql);
                res= ps.executeQuery();
                while(res.next()){
                    MostrarProductos p= new MostrarProductos();
                    p.setCodigoUser(res.getInt("CodigoUser"));
                    p.setNombreUser(res.getString("NombreUser"));
                    p.setPlacaUser(res.getString("PlacaUser"));
                    p.setTipoAutomovil(res.getString("TipoAutomovil"));
                    lista.add(p);
                }
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("No conectó"+ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Errorsiño " + ex.getStackTrace());
        }
        return lista;
    }
    
    public List listarProductoEspec(int codigoP){
        List<MostrarProductos>lista= new ArrayList<MostrarProductos>();
        String sql = "select * from tbproductos where CodigoProducto=?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                InputStream bImg = null;
                byte[] imgData = null ;
                int codigo, codigoadmin;
                String nombre, desc, proc, observ, urlP;
                conexion= DriverManager.getConnection(url,user,pass);
                ps=conexion.prepareStatement(sql);
                ps.setInt(1, codigoP);
                res= ps.executeQuery();
                while(res.next()){
                    MostrarProductos p= new MostrarProductos();
                    p.setCodigoproducto(res.getInt("CodigoProducto"));
                    p.setNombreProducto(res.getString("NombreProducto"));
                    p.setDescProducto(res.getString("DescripcionProducto"));
                    p.setProcedimiento(res.getString("Procedimiento"));
                    p.setObservaciones(res.getString("Observaciones"));
                    p.setAdmin(res.getInt("Administrador"));
                    p.setImg(res.getString("ImagenProducto"));
                    p.setUrlProducto(res.getString("UrlProducto"));
                    lista.add(p);
                }
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("No conectó"+ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Errorsiño " + ex.getStackTrace());
        }
        return lista;
    }
    
    public String MostrarAdminProEs(int codAdmin){
        String sql = "select NombreAdmin from tbadministradores where CodigoAdmin=?";
        String nombreAdminProEs="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conexion= DriverManager.getConnection(url,user,pass);
                ps=conexion.prepareStatement(sql);
                ps.setInt(1, codAdmin);
                res= ps.executeQuery();
                while(res.next()){
                    nombreAdminProEs= res.getString("NombreAdmin");
                }
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("No conectó"+ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Errorsiño " + ex.getStackTrace());
        }
        return nombreAdminProEs;
    }
    
    
}
