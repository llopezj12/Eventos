<%@ page import="app.eventostaw.entity.Usuario" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : AdminAgregarUsuario
    Created on : 13-may-2021, 12:13:53
    Author     : aaron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/estiloregistro.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EvenTAW - Crear Usuario</title>
    </head>
    <body>
    <ul>
        <li><a href="/">Eventos disponibles</a></li>
        <li><a href="/conversaciones">Ticket de ayuda</a></li>
        <li><a href="/adminlistar" class="active" >Panel de Admin</a></li>
        <li style="float:right"><a href="/datosusuario">Mis datos</a></li>
    </ul>
        <%
            Usuario u = (Usuario)request.getAttribute("usuario");
            Boolean error = (Boolean)request.getAttribute("error");
            String errorMsg = "";
            if (error == null) {
                error = false;
            }
            if (error) {
                errorMsg = (String)request.getAttribute("errorMsg");
            }
        %>
        <h1>Crear nuevo usuario </h1>
        <div class="form">
            <form method="post" action="/crear">
                <input type="hidden" id="idUsuario" name="idUsuario" >
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" ></br>
                <label for="apellidos">Apellidos</label>
                <input type="text" id="apellidos" name="apellidos" ></br>
                <label for="ciudad">Ciudad</label>
                <input type="text" id="ciudad" name="ciudad" ></br>
                <label for="dom">Domicilio</label>
                <input type="text" id="dom" name="dom" ></br>
                <div class="block">
                    <label>Genero</label>
                    <input type="radio" id="genero1" name="genero" value="H" >Hombre
                    <input type="radio" id="genero2" name="genero" value="M" >Mujer</br>
                </div>
                <label for="fecha">Fecha Nac</label>
                <input type="date" id="fecha" name="fecha" ></br>
                <label for="email">Email</label>
                <input type="text" id="email" name="email" ></br>
                <label for="pass">Contrase??a</label>
                <input type="text" id="pass" name="pass" ></br>
                <label for="rol">Rol</label>
                <input type="radio" id="rol" name="rol" value="1" >Creador de Evento</br>
                <input type="radio" id="rol" name="rol" value="2" >Administrador</br>
                <input type="radio" id="rol" name="rol" value="3" >Usuario</br>
                <input type="radio" id="rol" name="rol" value="4" >Teleoperador</br>
                <input type="radio" id="rol" name="rol" value="5" >Analista </br>
                <%
                    if (error) {
                %>
                <div class="block">
                    Error procesando datos: <%=errorMsg%>
                </div>
                <%
                    }
                %>
                <input type="submit" value="Guardar"/></br>
            </form>
        </div>
    </body>
</html>
