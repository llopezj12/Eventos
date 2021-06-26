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
    <jsp:forward page="/"/>
    <%
        }
    %>
    <body>
        <ul>
            <li><a  href="/">Eventos disponibles</a></li>
            <li><a href="/login">Identificarse</a></li>
            <li><a class="active" href="/registro">Registro</a></li>
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
            <form method="get" action="/tryregistro" accept-charset="UTF-8">
                <div class="block">
                    <label>Nombre: </label><input type="text" maxlength="20" size="25" name="nombre" value="<%=nombre%>">
                </div>
                <div class="block">
                    <label> Apellidos: </label><input type="text" maxlength="30" size="25" name="apellido" value="<%=apellido%>">
                </div>
                <div class="block">
                    <label> Ciudad de residencia: </label><input type="text" maxlength="30" size="25" name="ciudad" value="<%=ciudad%>">
                </div>
                <div class="block">
                    <label> Domicilio: </label><input type="text" maxlength="30" size="25" name="domicilio" value="<%=domicilio%>">
                </div>
                <div class="block">
                    <label> Género: </label>
                    <input type="radio" name="sexo" value="male" <%=checkMale%>>Hombre
                    <input type="radio" name="sexo" value="female" <%=checkFemale%>>Mujer
                </div>
                <div class="block">
                    <label> Fecha de nacimiento: </label><input type="date" name="nacimiento" value="<%=fecha%>">
                </div>
                <div class="block">
                    <label>E-mail: </label><input type="text" maxlength="20" size="25" name="email" value="<%=email%>">
                </div>
                <div class="block">
                    <label>Contraseña: </label><input type="password" maxlength="20" size="25" name="password">
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