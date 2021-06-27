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
    <link rel="stylesheet" href="css/estiloconversacion.css">
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
<div style="float: left;margin-right: 20px;margin-top: 70px; margin-left: 75px" >
<form method="post" action="/filtrarUsuarios" style="margin-left: 775px">
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
<div class="table100">
    <table class="customTable table100" style="margin-left: 340px">
        <thead class="table100-head">
            <th class="c">ID</th>
            <th class="c">NOMBRE</th>
            <th class="c">APELLIDOS</th>
            <th class="c">CORREO</th>
            <th class="c">ROL</th>
            <th class="c">EDITAR</th>
            <th class="c">ELIMINAR</th>
        </thead>
    <%

        for(Usuario u : listaFiltrada){

    %>
    <tr>
        <td class="c"><%= u.getIdUsuario()  %></td>
        <td class="c"><%= u.getNombre()  %></td>
        <td class="c"><%= u.getApellidos()  %></td>
        <td class="c"><%= u.getEmail() %></td>
        <td class="c"><%=u.getRolesByRol().getDescripcion()%></td>
        <td class="c"><a href="/editarUsuario/<%=u.getIdUsuario()%>">Editar</a></td>
        <td class="c"><a href="/adminEliminarUsuario/<%=u.getIdUsuario()%>">Eliminar</a></td>
    </tr>
    <% } %>
</table>
</div>

<br>
<a href="/redireccionarAgregarUsuario" style="margin-left: 350px">Añadir usuario</a>
</div>
<br>
<div style="float: left;margin-top: 39px" >
<form method="post" action="/filtrarEventos" style="margin-left: 850px">
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
    <div class="table100">
        <table class="customTable table100" style="margin-left: 415px">
            <thead class="table100-head">
                <th class="c">ID</th>
                <th class="c">TITULO</th>
                <th class="c">FECHA</th>
                <th class="c">COSTE</th>
                <th class="c">AFORO</th>
                <th class="c">DESCRIPCION</th>
                <th class="c">EDITAR</th>
                <th class="c">ELIMINAR</th>
            </thead>
    <%
        for(Evento e : listaEventoFiltrada){

    %>
    <tr>
        <td class="c"><%= e.getIdEvento()  %></td>
        <td class="c"><%= e.getTitulo()  %></td>
        <td class="c"><%= new SimpleDateFormat("dd/MM/yyyy").format(e.getFecha()) %></td>
        <td class="c"><%= e.getCoste() %></td>
        <td class="c"><%= e.getAforo() %></td>
        <td class="c"><%= e.getDescripcion() %></td>
        <td class="c"><a href="/adminEditarEvento/<%=e.getIdEvento()%>">Editar</a></td>
        <td class="c"><a href="/adminEliminarEvento/<%=e.getIdEvento()%>">Eliminar</a></td>
    </tr>
    <% } %>
    </table>
</div>
    <br>
<a href="/redireccionarAgregarEvento" style="margin-left: 430px;margin-bottom: 30px; display: inline-block">Añadir evento</a>
</div>
</body>
</html>
