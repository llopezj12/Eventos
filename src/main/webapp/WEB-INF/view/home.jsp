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
%>
<body>
<ul>
  <li><a class="active" href="/">Eventos disponibles</a></li>
  <% if (user == null) { %>
  <li><a href="login.jsp">Identificarse</a></li>
  <li><a href="registro.jsp">Registro</a></li>
  <% } %>
  <% if (user != null) { %>
  <li><a href="ServletConversaciones">Ticket de ayuda</a></li>
  <!--<li style="float:right"><a href="about.asp">Mi cuenta</a></li>-->
  <% } %>
</ul>
<h1>Echa un vistazo a los eventos disponibles.</h1>
<% if (eventosList.isEmpty()) { %>
<div>Vaya, parece que esto está vacío.</div>
<% } else { %>
<div class="wrapper">
  <% for (Evento e : eventosList) {%>
  <div class="box <%=e.getIdEvento()%>">
    <h3><%=e.getTitulo()%></h3>
    <div><%=e.getDescripcion()%></div>
    <%if (e.getAsientosfijos().compareTo("S") == 0) {%>
    <div><%=e.getNumasientosporfila()%> asientos x <%=e.getNumfilas()%> filas.</div>
    <% } %>
    <div><%=e.getEntradas()%> entradas disponibles. Aforo de <%=e.getAforo()%> personas.</div>
    <div>Precio: <%=e.getCoste()%> euros.</div>
    <div>Se celebra: <%=e.getFecha()%> euros.</div>
    <div>Caduca: <%=e.getFechares()%> euros.</div>
  </div>
  <% } %>
</div>
<% } %>
</body>
</html>
