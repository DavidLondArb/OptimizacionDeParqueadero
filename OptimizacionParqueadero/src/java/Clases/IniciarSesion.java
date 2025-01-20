package Clases;

import Clases.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IniciarSesion {
    public static int codigo;
    public static String id,nombre;
    public static String email, contrasena, perfil;
    Connection conexion = null;
    String bd="optimizacionparqueo";
    String url="jdbc:mysql://localhost:3306/"+bd;
    String user="root";
    String pass="";

    public IniciarSesion(String email, String contrasena, String perfil) {
        this.email = email;
        this.contrasena = contrasena;
        this.perfil = perfil;
    }

    public int ValidarInicio() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                String contra2, email2;
                System.out.println("conectó");
                conexion= DriverManager.getConnection(url,user,pass);
                Statement sentenciaSQL = conexion.createStatement();
                if (perfil.equals("Cliente")) {
                    String tbcliente = "SELECT * FROM tbclientes WHERE correocliente='" + email + "' and contrasenaCliente='" + contrasena + "'";
                    ResultSet cliente = sentenciaSQL.executeQuery(tbcliente);
                    while (cliente.next()) {
                        contra2 = cliente.getString("contrasenacliente");
                        email2 = cliente.getString("correocliente");
                        email=cliente.getString("correocliente");
                        id=cliente.getString("idcliente");
                        nombre= cliente.getString("nombrecliente");
                        codigo = cliente.getInt("codigocliente");
                        OperacionesClienteBD client = new OperacionesClienteBD(nombre, email, contra2);
                        client.setCodigoClient(codigo);
                        if (contrasena.equals(contra2) && email.equals(email2)) {
                            System.out.println("Bien");
                            return 1;
                        }
                    }
                }else {
                    String tbadmin = "SELECT * FROM tbadministradores WHERE CorreoAdmin='" + email + "' and ContrasenaAdmin='" + contrasena + "'";
                    ResultSet admin = sentenciaSQL.executeQuery(tbadmin);
                    while (admin.next()) {
                        contra2 = admin.getString("ContrasenaAdmin");
                        email2 = admin.getString("CorreoAdmin");
                        id=admin.getString("IDAdmin");
                        nombre= admin.getString("NombreAdmin");
                        codigo = admin.getInt("CodigoAdmin");
                        email = admin.getString("CorreoAdmin");
                        if (contrasena.equals(contra2) && email.equals(email2)) {
                            System.out.println("Bien");
                            return 2;
                        }
                    }
                }
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("No conectó" + ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Errorsiño " + ex.getStackTrace());
        }
        return 0;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

}
