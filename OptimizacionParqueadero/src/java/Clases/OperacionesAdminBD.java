package Clases;

import static Clases.OperacionesUser.img;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class OperacionesAdminBD {

    private int codigoAdmin,val,valregistro,totalEspCarro=121,totalEspMoto=300,totalReg,espaciosDM,espaciosDC,aux;
    private String nombreAdmin,nombreUser,placaUser,tipoAutomovil;
    private String emailAdmin, contrasenaAdmin;
    ConexionDB obConexion = new ConexionDB();
    Connection conexion = null;
    String bd="optimizacionparqueo";
    String url="jdbc:mysql://localhost:3306/"+bd;
    String user="root";
    String pass="";

    public OperacionesAdminBD(){
        
        conexion= obConexion.getConexion();
    }
    
    public OperacionesAdminBD(String nombreAdmin, String emailAdmin, String contrasenaAdmin, String NombreUser, String PlacaUser, String TipoAutomovil) {
        this.nombreAdmin = nombreAdmin;
        this.emailAdmin = emailAdmin;
        this.contrasenaAdmin = contrasenaAdmin;
        this.nombreUser = NombreUser;
        this.placaUser = PlacaUser;
        this.tipoAutomovil = TipoAutomovil;
        
    }

    

    public void Registro(MostrarProductos p) throws IOException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                System.out.println("conecta");
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
    
    public OperacionesAdminBD mostrarInfoAdmin(int codigoAd){
        OperacionesAdminBD p= new OperacionesAdminBD();
        String sql = "select * from tbadministradores where CodigoAdmin=?";
        PreparedStatement ps;
        ResultSet res;   
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conexion= DriverManager.getConnection(url,user,pass);
                ps=conexion.prepareStatement(sql);
                ps.setInt(1, codigoAd);
                res= ps.executeQuery();
                while(res.next()){
                    p.setCodigoAdmin(res.getInt("CodigoAdmin"));
                    p.setNombreAdmin(res.getString("NombreAdmin"));
                    p.setEmailAdmin(res.getString("correoAdmin"));
                }
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("No conectó"+ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Errorsiño " + ex.getStackTrace());
        }
        return p;
    }
    
    public void EliminarAdministrador(String cedul, String correo){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                System.out.println("conectó");
                PreparedStatement ps;
                conexion= DriverManager.getConnection(url,user,pass);
                String sql = "delete from tbAdministradores where idAdmin=? and correoAdmin=?";
                ps= conexion.prepareStatement(sql);
                ps.setString(1, cedul);
                ps.setString(2, correo);
                ps.execute();
                ps.close();
                ps=null;
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("No conectó"+ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Errorsiño " + ex.getStackTrace());
        }
    }
    public int validacion(String email){
        int resu=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                System.out.println("conectó");
                conexion= DriverManager.getConnection(url,user,pass);
                Statement sentenciaSQL = conexion.createStatement();
                String sql= "select count(*) as correo from TBADMINISTRADORES where correoadmin='"+email+"'";
                ResultSet res= sentenciaSQL.executeQuery(sql);
                res.next();
                resu=res.getInt("correo");
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("No conectó"+ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Errorsiño " + ex.getStackTrace());
        }
        return (resu);
    }
    
    public void addUsuario(OperacionesAdminBD op) throws Exception{
        try{
            PreparedStatement ps;
            String sql= "INSERT INTO usuariosparqueo(NombreUser, PlacaUser, TipoAutomovil) VALUES(?,?,?)";
            ps= conexion.prepareStatement(sql);
            ps.setString(1, op.getNombreUser());
            ps.setString(2, op.getPlacaUser());
            ps.setString(3, op.getTipoAutomovil());
            ps.execute();
            
        }catch(Exception ex){
            throw ex;
        }finally{
            obConexion.closeConexion(conexion);
        }
    }
    
    public void eliminarUser(OperacionesAdminBD op) throws Exception{
        try{
            PreparedStatement ps;
            String sql= "DELETE FROM usuariosparqueo WHERE NombreUser=? and PlacaUser=?";
            ps= conexion.prepareStatement(sql);
            ps.setString(1, op.getNombreUser());
            ps.setString(2, op.getPlacaUser());
            ps.execute();
            
        }catch(Exception ex){
            throw ex;
        }finally{
            obConexion.closeConexion(conexion);
        }
    }
    
    public int contarEspaciosDisponiblesCarro() throws Exception{
        int n=0, aux=0;
        
            Class.forName("com.mysql.jdbc.Driver");
            
                System.out.println("Entro a contar");
                conexion = DriverManager.getConnection(url,user,pass);
                Statement sentencialSQL = conexion.createStatement();
                String sql="select count(*) from usuariosparqueo where TipoAutomovil='Carro'";
                ResultSet res = sentencialSQL.executeQuery(sql);
                res.next();
                aux= res.getInt(1);
                n= totalEspCarro - aux;
                conexion.close();
            
        
        return (n);
    }
    
    public int contarEspaciosDisponiblesMoto() throws Exception{
        int n=0, aux=0;
        
            Class.forName("com.mysql.jdbc.Driver");
            
                System.out.println("Entro a contar");
                conexion = DriverManager.getConnection(url,user,pass);
                Statement sentencialSQL = conexion.createStatement();
                String sql="select count(*) from usuariosparqueo where TipoAutomovil='Moto'";
                ResultSet res = sentencialSQL.executeQuery(sql);
                res.next();
                aux= res.getInt(1);
                n= totalEspMoto - aux;
                conexion.close();
            
        
        return (n);
    }
    
    

    
    public void setEspaciosCarro(int espaciosDC){
        this.espaciosDC = espaciosDC;
    }
    public void setEspaciosMoto(int espaciosDM){
        this.espaciosDM = espaciosDM;
    }

    public void setCodigoAdmin(int codigoAdmin) {
        this.codigoAdmin = codigoAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }

    public void setContrasenaAdmin(String contrasenaAdmin) {
        this.contrasenaAdmin = contrasenaAdmin;
    }
    
    public void setNombreUser(String NombreUser) {
        this.nombreUser = NombreUser;
    }
    public void setPlacaUser(String PlacaUser) {
        this.placaUser = PlacaUser;
    }
    public void setTipoAutomovil(String TipoAutomovil) {
        this.tipoAutomovil = TipoAutomovil;
    }
    
    public int getVal(String PlacaUser) {
        val=validacion(PlacaUser);
        return val;
    }
    
    public int getCodigoAdmin() {
        return codigoAdmin;
    }
    
    public int getEspaciosCarro() throws Exception{
        aux = contarEspaciosDisponiblesCarro();
        return aux;
    }
    public int getEspaciosMoto() throws Exception{
        aux = contarEspaciosDisponiblesMoto();
        return espaciosDM;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public String getEmailAdmin() {
        return emailAdmin;
    }

    public String getContrasenaAdmin() {
        return contrasenaAdmin;
    }
    
    public String getNombreUser() {
        return nombreUser;
    }

    public String getPlacaUser() {
        return placaUser;
    }

    public String getTipoAutomovil() {
        return tipoAutomovil;
    }

    public int getValregistro() {
        return valregistro;
    }
    
}
