<%@page import="Clases.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/png" href="img/Logo.png"/>
        <link rel="stylesheet" href="materialize/css/materialize.min.css">
        <link rel="stylesheet" type="text/css" href="materialize/css/materialize.css">
        <link rel="stylesheet" type="text/css" media="only screen and (min-width:777px) and (max-width:2500px)" href="CSS/formato.css"/>
        <link rel="stylesheet" type="text/css" media="only screen and (min-width:100px) and (max-width:777px)" href="CSS/formato3.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/estilo.css"/>
        <title>Parqueadero</title>
    </head>
    <body>
        <%
            OperacionesAdminBD op = new OperacionesAdminBD();
            int Carros = op.contarEspaciosDisponiblesCarro();
            int Motos = op.contarEspaciosDisponiblesMoto();
        %>
        <div class="inicio">
            <div class="buco">
                <h2 class="Espacios">Espacios Disponibles</h2>
                <h1 class="Carros"><%=Carros%></h1>
                <h2 class="txtCarros">Carros</h2>
                <h1 class="Motos"><%=Motos%></h1>
                <h2 class="txtMotos">Motos</h2>
            </div>
        </div>
        
        <ul class="ajuste">
            <li><a><img src="img/Hambur.png" width="50px"></a>
                <ul class="submenu">
                    <img class="iconoInicio" src="img/Logo.png" width="150px">
                    <div class="BORDE"><li><a href="Login.jsp">Ingresar</a></li></div>
                    <div><li><a href="https://www.google.com/maps/search/Aparcamientos/@6.2791944,-75.5826695,17.75z/data=!4m2!2m1!6e2?hl=es" target="_blank">Alternativas</a></li></div>
                </ul>
            </li>
        </ul>
        
        <script src="JS/javascript.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    </body>
</html>
