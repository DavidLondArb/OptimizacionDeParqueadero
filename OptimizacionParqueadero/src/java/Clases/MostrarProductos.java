package Clases;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.Part;

public class MostrarProductos {
    int admin,codigoproducto,codigouser;
    String nombreProducto, descProducto, procedimiento, observaciones, urlProducto,img,nombreadmin,nombreuser,placauser,tipoautomovil;
    int codigoIngre;
    String nombreIngre, cantidadIngre;
    Connection conexion = null;
    String bd="optimizacionparqueo";
    String url="jdbc:mysql://localhost:3306/"+bd;
    String user="root";
    String pass="";
    PreparedStatement ps;
    ResultSet res;  
    
    public String getImg() {
        return img;
    }

    public int getAdmin() {
        return admin;
    }

    public String getNombreadmin() {
        String sql = "select NombreAdmin from tbadministradores where CodigoAdmin=?";
        String nombreAdminProEs="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conexion= DriverManager.getConnection(url,user,pass);
                ps=conexion.prepareStatement(sql);
                ps.setInt(1, admin);
                res= ps.executeQuery();
                while(res.next()){
                    nombreadmin= res.getString("NombreAdmin");
                }
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("No conectó"+ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Errorsiño " + ex.getStackTrace());
        }
        return nombreadmin;
    }
    
    public int getCodigoUser() {
        return codigouser;
    }
    
    public String getNombreUser() {
        return nombreuser;
    }
    
    public String getPlacaUser() {
        return placauser;
    }
    
    public String getTipoAutomovil() {
        return tipoautomovil;
    }
    
    public int getCodigoproducto() {
        return codigoproducto;
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

    public String getUrlProducto() {
        return urlProducto;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
    
    public void setCodigoUser(int codigouser) {
        this.codigouser = codigouser;
    }
    
    public void setNombreUser(String nombreuser) {
        this.nombreuser = nombreuser;
    }
    
    public void setPlacaUser(String placauser) {
        this.placauser = placauser;
    }
    
    public void setTipoAutomovil(String tipoautomovil) {
        this.tipoautomovil = tipoautomovil;
    }

    public void setCodigoproducto(int codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setUrlProducto(String urlProducto) {
        this.urlProducto = urlProducto;
    }

    public int getCodigoIngre() {
        return codigoIngre;
    }

    public void setCodigoIngre(int codigoIngre) {
        this.codigoIngre = codigoIngre;
    }

    public String getNombreIngre() {
        return nombreIngre;
    }

    public void setNombreIngre(String nombreIngre) {
        this.nombreIngre = nombreIngre;
    }

    public String getCantidadIngre() {
        return cantidadIngre;
    }

    public void setCantidadIngre(String cantidadIngre) {
        this.cantidadIngre = cantidadIngre;
    }
    
    
}
