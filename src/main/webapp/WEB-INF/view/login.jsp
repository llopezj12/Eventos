<%-- 
    Document   : login.jsp
    Created on : May 11, 2021, 6:24:16 PM
    Author     : Ivanchu
--%>

<%@page import="app.eventostaw.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EvenTAW - Iniciar Sesión</title>
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/estiloregistro.css">
    </head>
    <body>
    <%
        Usuario user = (Usuario)session.getAttribute("usuario");
        if (user != null) {
    %>
    <jsp:forward page="inicio.jsp"/>
    <%
        }
    %>
        <ul>
            <li><a  href="/">Inicio</a></li>
            <li><a class="active" href="/login">Identificarse</a></li>
            <li><a href="registro.jsp">Registro</a></li>
        </ul>

        <h1>Introduce los datos para iniciar sesión</h1>
        <%
            String email = (String) request.getAttribute("correo");
            String contrasena = (String) request.getAttribute("contrasena");
            Boolean error = (Boolean) request.getAttribute("error");
            String errorMsg = "";

            if (error == null) {
                error = false;
            }
            if (error) {
                errorMsg = (String) request.getAttribute("errorMsg");
            }
            if (email == null) {
                email = "";
            }
            if (contrasena == null) {
                contrasena = "";
            }
        %>
        <div class="form">
            <form action="/trylogin">
                <div class="block">
                    <label for="Correo">Correo</label><input type="text" id="Correo" name="correo" value="<%=email%>"><br>
                </div>
                <div class="block">
                    <label for="Contraseña">Contraseña</label><input type="password" id="Contraseña" name="contrasena"><br>
                </div>
                <%
                    if (error) {
                %>
                <div class="block">
                    Error: <%=errorMsg%>
                </div>
                <%
                    }
                %>
                <input type="submit" name="Iniciar Sesión">
            </form>
        </div>
    </body>
</html>
