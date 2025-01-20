
package Clases;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConexionDB {
    Connection conexion = null;
    String bd, url, user, pass;
    
    public ConexionDB(){
        try{
            String bd="optimizacionparqueo";
            String url="jdbc:mysql://localhost:3306/"+bd;
            String user="root";
            String pass="";
            
            Class.forName("com.mysql.jdbc.Driver");
            conexion= DriverManager.getConnection(url,user,pass);
        }catch(Exception e){
            
        }
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
    public void closeConexion(Connection conexion){
        try{
            conexion.close();
        }catch(Exception e){
            
        }
    }
}
