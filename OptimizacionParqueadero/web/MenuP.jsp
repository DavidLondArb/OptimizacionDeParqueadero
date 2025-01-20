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
        <link rel="stylesheet" type="text/css" href="CSS/IniSesionAdmin.css"/>
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
        <%
                    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                    response.setHeader("Pragma", "no-cache");
                    response.setDateHeader("Expires", 0);
                    if (request.getParameter("cerrar") != null) {
                        session.removeAttribute("nombre");
                        session.invalidate();
                    }
                    ImgProductoDao daoImg= new ImgProductoDao();
                    MostrarProductos p= new MostrarProductos();
                    List<MostrarProductos>lista= daoImg.listarProducto();
                %>
        <div class="contenedor">
            <h1></h1>
            <div class="row">
            <%
                if (!lista.isEmpty()) {
                    for(MostrarProductos c: lista){
            %>
                    <div class="col s12 m6 l6">
                        <form class="boton" method="post" action="Login" enctype="multipart/form-data">
                        <ul class="ajuste">
                            <li><a><h3 class="descripcion"><%=c.getPlacaUser()%></h3></a>
                                <ul class="submenu">
                                    <div class="material-placeholder">
                                        <h3 class="descripcion">Nombre del usuario: <input class="formInput" id="NombreU" type="text" name="NombreUser" value="<%=c.getNombreUser()%>" > </h3>
                                        <h3 class="descripcion">Placa del vehiculo: <input class="formInput" id="PlacaU" type="text" name="PlacaUser" value="<%=c.getPlacaUser()%>" </h3>
                                        <h3 class="descripcion">Tipo de vehiculo: <%=c.getTipoAutomovil()%></h3>
                                        <button type="submit" class="ingresar2 bot" name="btnEliminarUser" id="btnEliminarUser"><img src="img/Eliminar.png"></button>
                                        <input type="hidden" name="pagina" value="Prueba"/>
                                                
                                    </div>
                                </ul>
                            </li>
                        </ul></form>
                    </div>
            <%
                    }
                }
            %>
            </div>
        </div>
        
        <div class="Agregar">
            <a class="btn-floating btn-large" href="#registrar">
                <img id="" src="img/Agregar.png" alt="Agregar" height="100px">      <!MODIFICACION>
            </a>
        </div>
            
        <aside id="registrar" class="elegirReg">
            <div class="contentido">
                <header>
                    <a href="MenuP.jsp" class="cerrar">Cerrar</a>
                    <h2>Ingrese los datos del usuario</h2>
                </header>
                <form class="boton" method="post" action="Login" enctype="multipart/form-data" onsubmit="return ValidarAlertaAdmin();"> 
                    <input class="formInput" type="text" name="NombreUser" PlaceHolder="Nombre" required/><br>
                    <input class="formInput" type="text" name="PlacaUser" PlaceHolder="Placa" required/><br>
                    <input class="formInput" type="checkbox" name="AutoUser" id="AutoUser1" value="Moto"/><label for="AutoUser" class="AutoMoto">Moto</label><br>
                    <input class="formInput" type="checkbox" name="AutoUser" id="AutoUser2" value="Carro"/><label for="AutoUser" class="AutoCarro">Carro</label><br>
                    <input type="submit" class="ingresar bot" name="btnRegistrar" id="btnRegistrarAdmin" value="Registrarse"/>
                    <input type="hidden" name="pagina" value="Prueba"/>
                </form>
            </div>
        </aside>
            
        <script src="JS/javascript.js"></script>
        <script src="JS/RegistroJS.js"></script>
        <script src="JS/SweetAlert.js"></script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    </body>
</html>
