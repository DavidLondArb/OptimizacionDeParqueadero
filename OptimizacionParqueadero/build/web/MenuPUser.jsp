<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Clases.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/png" href="img/Logo.png"/>
        <link rel="stylesheet" type="text/css" href="CSS/IniSesionUser.css"/>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script type="text/javascript" src="JS/javascript.js"></script>
        <script src="JS/RegistroJS.js"></script>
        <title>Parqueadero</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession(false);
            String nombre = (String) sesion.getAttribute("nombre");
            OperacionesAdminBD infoAd= (OperacionesAdminBD) sesion.getAttribute("infoAdmin");
            if (nombre == null) {
                response.sendRedirect("IniSesion.jsp");
            }
            OperacionesAdminBD op = new OperacionesAdminBD();
            int Carros = op.contarEspaciosDisponiblesCarro();
            int Motos = op.contarEspaciosDisponiblesMoto();
        %>
        <div class="inicio">
            <div class="buco">
                <h2 class="Esp">Espacios</h2>
                <h2 class="Dis">Disponibles</h2>
                <h1 class="Carros"><%=Carros%></h1>
                <h2 class="txtCarros">Carros</h2>
                <h1 class="Motos"><%=Motos%></h1>
                <h2 class="txtMotos">Motos</h2>
                <img id="Logo" src="img/Logo.png" alt="Logo">
            </div>
        </div>
        
        <div class="contenedor">
            <h1>Agendamiento de espacio</h1>
            <form class="boton" method="post" action="Login" enctype="multipart/form-data" onsubmit="return ValidarAlerta();"> 
                <input class="formInput" type="text" name="NombreUser" PlaceHolder="Nombre" required/><br>
                <input class="formInput" type="text" name="PlacaUser" PlaceHolder="Placa" required/><br>
                <input class="formInput" type="checkbox" name="AutoUser" id="AutoUser1" value="Moto"/><label for="AutoUser" class="AutoMoto">Moto</label><br>
                <input class="formInput" type="checkbox" name="AutoUser" id="AutoUser2" value="Carro"/><label for="AutoUser" class="AutoCarro">Carro</label><br>
                <input type="submit" class="ingresar bot" name="btnRegistrarUser" id="btnRegistrarUser" value="Registrarse"/>
                <input type="hidden" name="pagina" value="Prueba"/>
            </form>
        </div>
           
        <script src="JS/javascript.js"></script>
        <script src="JS/RegistroJS.js"></script>
        <script src="JS/SweetAlert.js"></script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    </body>
</html>
