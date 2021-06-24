<%-- 
    Document   : AdminEditar
    Created on : 11-may-2021, 19:28:06
    Author     : aaron
--%>

<%@page import="eventos.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/estiloregistro.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EvenTAW - Editar</title>
    </head>
    <%
        Usuario u = (Usuario)request.getAttribute("usuario");
    %>
    <body>
        <h1>Editar usuario(ID = <%= u.getIdUsuario() %>) </h1>
        <div class="form">
              <form action="ServletAdminGuardarEdicion">
              <input type="hidden" id="idUsuario" name="idUsuario" value="<%= u.getIdUsuario() %>">
              <label for="nom">Nombre</label>
              <input type="text" id="nombre" name="nombre" value="<%= u.getNombre() %>"></br>
              <label for="ape">Apellidos</label>
              <input type="text" id="apellidos" name="apellidos" value="<%= u.getApellidos() %>"></br>
              <label for="ciu">Ciudad</label>
              <input type="text" id="ciudad" name="ciudad" value="<%= u.getCiudad() %>"></br> 
              <label for="dom">Domicilio</label>
              <input type="text" id="dom" name="dom" value="<%= u.getDomicilio() %>"></br>
              <label for="gen">Genero</label>
              <input type="radio" id="genero" name="genero" value="H" >Hombre
              <input type="radio" id="genero" name="genero" value="M" >Mujer</br>
              <input type="hidden" id="gen" name="gen" value="<%= u.getGenero() %>">
              <label for="fecha">Fecha Nac</label>
              <input type="date" id="fecha" name="fecha" value="<%= u.getNacimiento() %>"></br> 
              <label for="email">Email</label>
              <input type="text" id="email" name="email" value="<%= u.getEmail() %>"></br>
              <label for="pass">Contraseña</label>
              <input type="text" id="pass" name="pass" value="<%= u.getPassword() %>"></br>
              <label for="rol">Rol</label>
              <input type="radio" id="rol" name="rol" value="1" >Creador de Evento</br>
              <input type="radio" id="rol" name="rol" value="2" >Administrador</br>
              <input type="radio" id="rol" name="rol" value="3" >Usuario</br>
              <input type="radio" id="rol" name="rol" value="4" >Teleoperador</br>
              <input type="radio" id="rol" name="rol" value="5" >Analista </br>
              <input type="hidden" id="rolDef" name="rolDef" value="<%= u.getRol().getIdRol() %>">
              <input type="submit" value="Guardar"/></br>
              </form>
        </div>
    </body>
</html>
