<%-- 
    Document   : conversacion
    Created on : May 13, 2021, 1:53:52 PM
    Author     : Ivanchu
--%>

<%@page import="java.util.List" %>
<%@ page import="app.eventostaw.entity.Usuario" %>
<%@ page import="app.eventostaw.entity.Conversacion" %>
<%@ page import="app.eventostaw.entity.Mensaje" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Conversaciones</title>
    <link rel="stylesheet" href="/css/estilo.css">
    <link rel="stylesheet" href="/css/estiloconversacion.css">
</head>
<%


    Usuario user = (Usuario) session.getAttribute("usuario");

%>
<body>
<ul>
    <li><a href="/">Eventos disponibles</a></li>
    <% if (user == null) { %>
    <li><a href="/login">Identificarse</a></li>
    <li><a href="/registro">Registro</a></li>
    <% } %>
    <% if (user != null) { %>
    <li><a class="active" href="/conversaciones">Ticket de ayuda</a></li>
    <li style="float:right"><a href="/datosusuario">Mis datos</a></li>
    <% } %>
</ul>


<div class="wrap-table100" style="margin: auto">
    <h1 style="margin-top: 70px">Conversaciones</h1>

    <div class="table100">

        <table class="table">
            <thead>


            <tr class="table100-head">

                <th>Usuario</th>
                <th>Tipo Usuario</th>
                <th>Ultimo mensaje</th>
                <th>Escrito Por</th>
                <th>A las:</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Conversacion conversacion : user.getConversacionsByIdUsuario()) {
                    Usuario sbeve = new Usuario();
                    if (conversacion.getUsuarioByIdUsuario1().getIdUsuario() == user.getIdUsuario()) {
                        sbeve = conversacion.getUsuarioByIdUsuario2();
                    } else {
                        sbeve = conversacion.getUsuarioByIdUsuario2();
                    }
                    String nombrecompleto = sbeve.getNombre() + " " + sbeve.getApellidos();
            %>
            <tr>
                <td><a href="conversacion/<%=conversacion.getIdConversacion()%>"><%=nombrecompleto%>
                </a></td>
                <td><%=sbeve.getRolesByRol().getDescripcion()%>
                </td>
                <%
                    if (conversacion.getMensajesByIdConversacion().isEmpty()) {

                %>
                <td>no hay mensajes</td>
                <td></td>
                <td></td>
                <% } else {

                    List<Mensaje> lm = (List<Mensaje>) conversacion.getMensajesByIdConversacion();
                    Mensaje ultimomensaje = lm.get(lm.size() - 1);

                    String horayminuto = "" + ultimomensaje.getHora();
                    if (ultimomensaje.getMinuto() < 10) {
                        horayminuto += ":0" + ultimomensaje.getMinuto();
                    } else {
                        horayminuto += ":" + ultimomensaje.getMinuto();
                    }


                %>
                <td><%=ultimomensaje.getMensaje()%>
                </td>
                <td><%=ultimomensaje.getUsuarioByIdUsuario().getNombre()%>
                </td>
                <td><%=horayminuto%>
                </td>
                <%
                    }
                %>
            </tr>
            <%
                }
                for (Conversacion conversacion : user.getConversacionsByIdUsuario_0()) {
                    Usuario sbeve = new Usuario();
                    if (conversacion.getUsuarioByIdUsuario1().getIdUsuario() == user.getIdUsuario()) {
                        sbeve = conversacion.getUsuarioByIdUsuario2();
                    } else {
                        sbeve = conversacion.getUsuarioByIdUsuario1();
                    }
                    String nombrecompleto = sbeve.getNombre() + " " + sbeve.getApellidos();
            %>
            <tr>
                <td><a href="conversacion/<%=conversacion.getIdConversacion()%>"><%=nombrecompleto%>
                </a></td>
                <td><%=sbeve.getRolesByRol().getDescripcion()%>
                </td>
                <% if (conversacion.getMensajesByIdConversacion().isEmpty()) {
                %>
                <td>no hay mensajes</td>
                <td></td>
                <td></td>
                <%
                } else {
                    List<Mensaje> lm = (List<Mensaje>) conversacion.getMensajesByIdConversacion();
                    Mensaje ultimomensaje = lm.get(lm.size() - 1);
                    String horayminuto = "" + ultimomensaje.getHora();
                    if (ultimomensaje.getMinuto() < 10) {
                        horayminuto += ":0" + ultimomensaje.getMinuto();
                    } else {
                        horayminuto += ":" + ultimomensaje.getMinuto();
                    }

                %>
                <td><%=ultimomensaje.getMensaje()%>
                </td>
                <td><%=ultimomensaje.getUsuarioByIdUsuario().getNombre()%>
                </td>
                <td><%=horayminuto%>
                </td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</div>
</div>

<h1>Lista de Teleoperadores</h1>
<div style="margin-left: 20px">
<%
    List<Usuario> listaTeleop = (List<Usuario>) request.getAttribute("listaTeleop");
    for (Usuario usu : listaTeleop) {
        if (user.getIdUsuario() != usu.getIdUsuario()) {
%>
<a href="crearConversacion/<%=usu.getIdUsuario()%>"><%=usu.getNombre()%>
</a>
<%
        }
    }
%>
</div>

<% if (user.getRolesByRol().getIdRol() == 4 || user.getRolesByRol().getIdRol() == 2) {
%>
<form class="form-inline" style="margin-left: 50px; margin-bottom: 20px" action="/menuConversaciones">
    <input type="hidden" value="" name="busqueda">
    <button type="submit">Menu de Conversaciones</button>
</form>
<%
    }
%>
</body>
</html>
