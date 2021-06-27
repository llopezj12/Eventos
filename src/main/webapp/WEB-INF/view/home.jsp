<%--
  Created by IntelliJ IDEA.
  User: EloyB
  Date: 25/06/2021
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="app.eventostaw.entity.Evento"%>
<%@page import="java.util.List"%>
<%@page import="app.eventostaw.entity.Usuario"%>
<%@ page import="java.text.DateFormat" %>
<%@ page import="app.eventostaw.entity.UsuarioInscrito" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>EvenTAW - Eventos disponibles</title>
  <link rel="stylesheet" href="/css/estilo.css">
  <link rel="stylesheet" href="/css/estiloregistro.css">
</head>
<%
  Usuario user = (Usuario)session.getAttribute("usuario");
  List<Evento> eventosList = (List)request.getAttribute("eventosList");
  DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
  List<Integer> inscritos = (List)request.getAttribute("inscritos");
%>
<body>
<ul>
  <li><a class="active" href="/">Eventos disponibles</a></li>
  <% if (user == null) { %>
  <li><a href="/login">Identificarse</a></li>
  <li><a href="registro">Registro</a></li>
  <% } %>
  <% if (user != null) { %>
  <li><a href="/conversaciones">Ticket de ayuda</a></li>
  <%
      if(user.getRolesByRol().getIdRol() == 2){
  %>
    <li><a href="/adminlistar">Panel de Admin</a></li>
  <% } %>
  <li style="float:right"><a href="/datosusuario">Mis datos</a></li>
  <% } %>
</ul>
<h1>Echa un vistazo a los eventos disponibles.</h1>
<form class="form-inline" action="/" style="margin-left: 100px; margin-bottom: 20px">
  <label for="clave">Palabra clave</label>
  <input size="24" type="text" id="clave" placeholder="Busca por título o descripción" name="clave" <% if (request.getAttribute("clave") != null) { %>value="<%=request.getAttribute("clave")%>"<% } %>>

  <label for="precio">Precio máx.</label>
  <input size="1" type="text" id="precio" name="precio" <% if (request.getAttribute("precio") != null) { %>value="<%=request.getAttribute("precio")%>"<% } %>>

  <label for="aforo">Aforo máx.</label>
  <input size="1" type="text" id="aforo" name="aforo" <% if (request.getAttribute("aforo") != null) { %>value="<%=request.getAttribute("aforo")%>"<% } %>>

  <label for="fecha1">Fecha</label>
  <input type="date" id="fecha1" name="fecha1" <% if (request.getAttribute("fecha1") != null) { %>value="<%=request.getAttribute("fecha1")%>"<% } %>>
  <input type="date" id="fecha2" name="fecha2" <% if (request.getAttribute("fecha2") != null) { %>value="<%=request.getAttribute("fecha2")%>"<% } %>>

  <label for="invertir">Invertir orden</label>
  <input type="checkbox" id="invertir" name="invertir" <% if (request.getAttribute("invertir") != null) { %>checked<% } %>>

  <button type="submit">Buscar</button>
</form>
<br>
<div style="margin-inline: 200px">
  <% if (request.getAttribute("invertir") != null) { %> <p>Eventos futuros primero.</p> <% } else { %> <p>Eventos con fecha de caducidad próxima primero.</p> <% } %>
  <% if (eventosList.isEmpty()) { %>
  <div>Vaya, parece que esto está vacío.</div>
  <% } else { %>
  <div class="wrapper">
    <% for (Evento e : eventosList) {%>

    <div class="box <%=e.getIdEvento()%>">
      <h3 style="margin-top:0px"><%=e.getTitulo()%></h3>
      <div><%=e.getDescripcion()%></div>
      <%if (e.getAsientosfijos().compareTo("S") == 0) {%>
      <div><%=e.getNumasientosporfila()%> asientos x <%=e.getNumfilas()%> filas.</div>
      <% } %>
      <div><%=e.getEntradas()%> entradas disponibles. Aforo de <%=e.getAforo()%> personas.</div>
      <div>Precio: <%=e.getCoste()%> euros.</div>
      <div>Se celebra: <%=df.format(e.getFecha())%>.</div>
      <div>Caduca: <%=df.format(e.getFechares())%>.</div>
      <%if (user != null) {%>
        <%if (inscritos.contains(e.getIdEvento())) {%>
          <div style="margin-left: 30px; margin-top: 30px"><a class="button4" style="background-color: indianred;" href="/QuitarEvento?idEvento=<%=e.getIdEvento()%>">Anular</a></div>
        <% } else { %>
          <div style="margin-left: 30px; margin-top: 30px"><a class="button4" style="background-color: darkseagreen;"href="/InscribirseEvento?idEvento=<%=e.getIdEvento()%>">Inscribete</a></div>
        <% } %>
      <% } %>
    </div>
    <% } %>
  </div>
<% } %>
</div>
</body>
</html>
