<%-- 
    Document   : registro
    Created on : 11-may-2021, 16:14:35
    Author     : EloyB
--%>

<%@page import="app.eventostaw.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>EvenTAW - Registro</title>
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/estiloregistro.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <%
        Usuario user = (Usuario)session.getAttribute("usuario");
        if (user != null) {
    %>
    <jsp:forward page="inicio.jsp"/>
    <%
        }
    %>
    <body>
        <ul>
            <li><a  href="inicio.jsp">Inicio</a></li>
            <li><a href="login.jsp">Identificarse</a></li>
            <li><a class="active" href="registro.jsp">Registro</a></li>
        </ul>
        <h1>Introduce los datos para crear tu cuenta.</h1>
        <%
            String nombre = (String)request.getAttribute("nombre");
            String apellido = (String)request.getAttribute("apellido");
            String ciudad = (String)request.getAttribute("ciudad");
            String fecha = (String)request.getAttribute("fecha");
            String domicilio = (String)request.getAttribute("domicilio");
            String sexo = (String)request.getAttribute("sexo");
            String email = (String)request.getAttribute("email");
            Boolean error = (Boolean)request.getAttribute("error");
            String errorMsg = "";
            String checkMale = "";
            String checkFemale = "";
            if (error == null) {
                error = false;
            }
            if (error) {
                errorMsg = (String)request.getAttribute("errorMsg");
            }
            if (nombre == null) {
                nombre = "";
            }
            if (apellido == null) {
                apellido = "";
            }
            if (ciudad == null) {
                ciudad = "";
            }
            if (domicilio == null) {
                domicilio = "";
            }
            if (email == null) {
                email = "";
            }
            if (sexo == null) {
                sexo = "";
            }
            if (sexo.equals("male")) {
                checkMale = "checked";
            }     
            if (sexo.equals("female")) {
                checkFemale = "checked";
            }
            if (fecha == null) {
                fecha = "";
            }
        %>
        <div class="form">
            <form method="get" action="ServletRegistro" name="datos" accept-charset="UTF-8">
                <div class="block">
                    <label for="nombre">Nombre: </label><input type="text" maxlength="20" size="25" name="nombre" value="<%=nombre%>">
                </div>
                <div class="block">
                    <label for="apellido"> Apellidos: </label><input type="text" maxlength="30" size="25" name="apellido" value="<%=apellido%>">
                </div>
                <div class="block">
                    <label for="residencia"> Ciudad de residencia: </label><input type="text" maxlength="30" size="25" name="ciudad" value="<%=ciudad%>">
                </div>
                <div class="block">
                    <label for="domicilo"> Domicilio: </label><input type="text" maxlength="30" size="25" name="domicilio" value="<%=domicilio%>">
                </div>
                <div class="block">
                    <label for="sex"> Género: </label>
                    <input type="radio" name="sex" value="male" <%=checkMale%>>Hombre
                    <input type="radio" name="sex" value="female" <%=checkFemale%>>Mujer
                </div>
                <div class="block">
                    <label for="nacimiento"> Fecha de nacimiento: </label><input type="date" name="nacimiento" value="<%=fecha%>">
                </div>
                <div class="block">
                    <label for="email">E-mail: </label><input type="text" maxlength="20" size="25" name="email" value="<%=email%>">
                </div>
                <div class="block">
                    <label for="password">Contraseña: </label><input type="password" maxlength="20" size="25" name="password">
                </div>
                <%
                    if (error) {
                %>
                <div class="block">
                    Error procesando datos: <%=errorMsg%>
                </div>
                <%
                    }
                %>
                <div class="block">
                    <input type="submit" value="Registrarse">
                </div>
                
            </form>
        </div>
    </body>
</html>