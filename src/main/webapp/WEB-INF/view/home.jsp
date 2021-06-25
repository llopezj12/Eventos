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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>EvenTAW - Eventos disponibles</title>
  <link rel="stylesheet" href="css/estilo.css">
  <link rel="stylesheet" href="css/estiloregistro.css">
</head>
<%
  Usuario user = (Usuario)session.getAttribute("usuario");
%>
<body>
<ul>
  <li><a class="active" href="home.jsp">Eventos disponibles</a></li>
  <% if (user == null) { %>
  <li><a href="login.jsp">Identificarse</a></li>
  <li><a href="registro.jsp">Registro</a></li>
  <% } %>
  <% if (user != null) { %>
  <li><a href="ServletConversaciones">Ticket de ayuda</a></li>
  <!--<li style="float:right"><a href="about.asp">Mi cuenta</a></li>-->
  <% } %>
</ul>

</body>
</html>
