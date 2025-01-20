package Clases;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.util.ArrayList;
import Clases.MostrarProductos;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.jboss.weld.bean.builtin.ee.HttpServletRequestBean;

public class OperacionesUser {
    public static Part img;
    public static int codigoproducto;
    public static ArrayList<MostrarProductos> ArrayProductoAdmin = new ArrayList<MostrarProductos>();
    private int admin,valregistro;
    private String nombreProducto, descProducto, procedimiento, observaciones, urlProducto,fileName2;
    Connection conexion = null;
    String bd="optimizacionparqueo";
    String url="jdbc:mysql://localhost:3306/"+bd;
    String user="root";
    String pass="";
    

    public OperacionesUser(){
    }    
   
    public OperacionesUser(int admin, String nombreProducto, String descProducto, String procedimiento, String observaciones, Part img, String urlProducto) {
        this.admin = admin;
        this.nombreProducto = nombreProducto;
        this.descProducto = descProducto;
        this.procedimiento = procedimiento;
        this.observaciones = observaciones;
        this.img = img;
        this.urlProducto = urlProducto;
    }
    
    public void Registro(MostrarProductos p) throws IOException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                System.out.println("conectó");
                PreparedStatement ps;
                conexion= DriverManager.getConnection(url,user,pass);
                String sql = "INSERT INTO usuariosparqueo(NombreUser, PlacaUser, TipoAutomovil) VALUES(?,?,?)";
                ps= conexion.prepareStatement(sql);
                ps.setString(1, p.getNombreUser());
                ps.setString(2, p.getPlacaUser());
                ps.setString(3, p.getTipoAutomovil());
                ps.execute();
                ps.close();
                ps=null;
                conexion.close();
                valregistro=1;
            } catch (SQLException ex) {
                System.out.println("No conectó acá"+ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Errorsiño " + ex.getStackTrace());
        }
    }
    
    public int codprod(int administrador){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                System.out.println("conectó");
                conexion= DriverManager.getConnection(url,user,pass);
                Statement sentenciaSQL = conexion.createStatement();
                String sql = "select codigoproducto from tbproductos where administrador='"+administrador+"' order by codigoproducto desc limit 1";
                ResultSet res= sentenciaSQL.executeQuery(sql);
                while(res.next()){
                    codigoproducto= res.getInt("codigoproducto");
                }
                conexion.close();
                return codigoproducto;
            } catch (SQLException ex) {
                System.out.println("No conectó"+ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Errorsiño " + ex.getStackTrace());
        }
        return 0;
    }
    
    public void insertIngredientes(String nombre,String cantidad,int CodProd){
        if(nombre.length()>1){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                try {
                    System.out.println("conectó");
                    conexion= DriverManager.getConnection(url,user,pass);
                    PreparedStatement ps;
                    String sql = "insert into tbingredientes(NombreIngrediente, Cantidad, Producto) VALUES(?,?,?)";
                    ps= conexion.prepareStatement(sql);
                    ps.setString(1, nombre);
                    ps.setString(2, cantidad);
                    ps.setInt(3, CodProd);
                    ps.execute();
                    ps.close();
                    ps=null;
                    conexion.close();
                } catch (SQLException ex) {
                    System.out.println("No insertó ingrediente "+ex);
                }
            } catch (ClassNotFoundException ex) {
                System.out.println("Errorsiño " + ex.getStackTrace());
            }
        }
    }
    
    public String saveFile(Part part,File pathUploads){
        int num=(int) (Math.random()*100000);
        String numComplemento= Integer.toString(num); 
        String pathAbsolute= "";
        try {
            Path path= Paths.get(part.getSubmittedFileName());
            String fileName= path.getFileName().toString();
            fileName2= numComplemento+fileName;
            InputStream input= part.getInputStream();
            if (input!=null) {
                File file= new File(pathUploads, fileName2);
                pathAbsolute= file.getAbsolutePath();
                Files.copy(input, file.toPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pathAbsolute;
    }
    
    public void eliminarProducto(String NombreUser, String PlacaUser){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                System.out.println("Entro a Borrar");
                PreparedStatement ps;
                conexion= DriverManager.getConnection(url,user,pass);
                String sql4 = "DELETE FROM usuariosparqueo WHERE NombreUser=? and PlacaUser=?";
                ps= conexion.prepareStatement(sql4);
                ps.setString(1, NombreUser);
                ps.setString(2, PlacaUser);
                ps.execute();
                ps.close();
                ps=null;
            } catch (SQLException ex) {
                System.out.println("No conectó acá"+ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Errorsiño " + ex.getStackTrace());
        }
    }
    
    public void eliminarProducto2(String nombre, String img){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                System.out.println("conectó");
                PreparedStatement ps;
                conexion= DriverManager.getConnection(url,user,pass);
                String sql = "delete from tbProductos where NombreProducto=? and ImagenProducto=?";
                ps= conexion.prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setString(2, img);
                ps.execute();
                ps.close();
                ps=null;
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Problema para eliminar "+ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Errorsiño " + ex.getStackTrace());
        }
    }

    public String getFileName2() {
        return fileName2;
    }
    
    public int getAdmin() {
        return admin;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getDescProducto() {
        return descProducto;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getUrl() {
        return url;
    }
    
    public int getValregistro() {
        return valregistro;
    }
    
}
