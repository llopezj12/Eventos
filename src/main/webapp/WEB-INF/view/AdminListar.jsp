<%-- 
    Document   : AdminListar
    Created on : 11-may-2021, 18:33:06
    Author     : aaron
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="app.eventostaw.entity.Evento"%>
<%@page import="java.util.List"%>
<%@page import="app.eventostaw.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/estilo.css">
    <link rel="stylesheet" href="css/estiloregistro.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EvenTAW - LIstar</title>
</head>
<body>
<ul>
    <li><a href="/">Eventos disponibles</a></li>
    <li><a href="/conversaciones">Ticket de ayuda</a></li>
    <li><a href="/adminlistar" class="active" >Panel de Admin</a></li>
    <li style="float:right"><a href="/datosusuario">Mis datos</a></li>
</ul>
<div style="float: left;margin-right: 20px;margin-top: 70px; margin-left: 250px" >
<form method="post" action="/filtrarUsuarios">
    <%
        String b = (String)request.getAttribute("busqueda");
        List<Usuario> listaFiltrada = (List)request.getAttribute("lista");
        if(b == null){
            b = "";
        }
    %>

    <label>Busqueda: </label><input type="text" maxlength="20" size="25" name="busqueda" value="<%=b%>">
    <input type="submit" value="Buscar">
</form>
    <pre></pre>
<table border="1">
    <thead>
    <th>ID</th>
    <th>NOMBRE</th>
    <th>APELLIDOS</th>
    <th>CORREO</th>
    <th>ROL</th>
    <th>EDITAR</th>
    <th>ELIMINAR</th>
    </tr>
    <%

        for(Usuario u : listaFiltrada){

    %>
    <tr>
        <td><%= u.getIdUsuario()  %></td>
        <td><%= u.getNombre()  %></td>
        <td><%= u.getApellidos()  %></td>
        <td><%= u.getEmail() %></td>
        <td><%=u.getRolesByRol().getDescripcion()%></td>
        <td><a href="/editarUsuario/<%=u.getIdUsuario()%>">Editar</a></td>
        <td><a href="/adminEliminarUsuario/<%=u.getIdUsuario()%>">Eliminar</a></td>
    </tr>
    <% } %>
    </thead>
</table>
<br>
<a href="/redireccionarAgregarUsuario">Añadir usuario</a>
</div>
<br>
<div style="float: left;margin-top: 39px" >
<form method="post" action="/filtrarEventos">
    <%

        String bEvento = (String)request.getAttribute("busquedaEvento");
        List<Evento> listaEventoFiltrada = (List)request.getAttribute("listaE");
        if(bEvento == null){
            bEvento = "";
        }
    %>
    <pre></pre>
    <label>Busqueda: </label><input type="text" maxlength="20" size="25" name="busquedaEvento" value="<%=bEvento%>">
    <input type="submit" value="Buscar">
</form>
<pre></pre>
<table border="1" >
    <thead>
    <th>ID</th>
    <th>TITULO</th>
    <th>FECHA</th>
    <th>COSTE</th>
    <th>AFORO</th>
    <th>DESCRIPCION</th>
    <th>EDITAR</th>
    <th>ELIMINAR</th>
    </tr>
    <%
        for(Evento e : listaEventoFiltrada){

    %>
    <tr>
        <td><%= e.getIdEvento()  %></td>
        <td><%= e.getTitulo()  %></td>
        <td><%= new SimpleDateFormat("dd/MM/yyyy").format(e.getFecha()) %></td>
        <td><%= e.getCoste() %></td>
        <td><%= e.getAforo() %></td>
        <td><%= e.getDescripcion() %></td>
        <td><a href="/adminEditarEvento/<%=e.getIdEvento()%>">Editar</a></td>
        <td><a href="/adminEliminarEvento/<%=e.getIdEvento()%>">Eliminar</a></td>
    </tr>
    <% } %>
    </thead>
</table>
    <br>
<a href="/redireccionarAgregarEvento">Añadir evento</a>
</div>
</body>
</html>
