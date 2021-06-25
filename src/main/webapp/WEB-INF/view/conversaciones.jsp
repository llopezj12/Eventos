<%-- 
    Document   : conversacion
    Created on : May 13, 2021, 1:53:52 PM
    Author     : Ivanchu
--%>

<%@page import="java.util.List"%>
<%@page import="app.eventostaw.dao.UsuarioFacade"%>
<%@page import="app.eventostaw.entity.Mensaje"%>
<%@page import="app.eventostaw.entity.Conversacion"%>
<%@page import="app.eventostaw.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conversaciones</title>
    </head>
    <%

        Usuario user = (Usuario) session.getAttribute("usuario");

    %>

    <table border="1">
        <thead>
        <body>

        <tr>

            <th>Usuario</th>
            <th>Tipo Usuario</th>
            <th>Ultimo mensaje</th>
            <th>Escrito Por</th>
            <th>A las:</th>
        </tr>
    </thead>
    <tbody>
        <%                for (Conversacion conversacion : user.getConversacionList1()) {
                Usuario sbeve = new Usuario();
                if (conversacion.getIdUsuario1().getIdUsuario()==user.getIdUsuario())
                {
                    sbeve = conversacion.getIdUsuario2();
                }
                else {
                    sbeve = conversacion.getIdUsuario1();
                }
                String nombrecompleto = sbeve.getNombre() + " " + sbeve.getApellidos();
        %>            
        <tr>
            <td><a href="ServletConversacion?conversacion=<%=conversacion.getIdConversacion()%>"><%=nombrecompleto%></a></td>
            <td><%=sbeve.getRol().getDescripcion()%></td>
            <%
                if (conversacion.getMensajeList().isEmpty()) {

            %>
            <td>no hay mensajes</td>
            <td></td>
            <td></td>
            <%                } else {
                Mensaje ultimomensaje = conversacion.getMensajeList().get(conversacion.getMensajeList().size() - 1);
                String horayminuto = ultimomensaje.getHora() + ":" + ultimomensaje.getMinuto();
            %>
            <td><%=ultimomensaje.getMensaje()%></td>
            <td><%=ultimomensaje.getIdUsuario().getNombre()%></td>
            <td><%=horayminuto%></td>
            <%
                }
            %>
        </tr>
        <%
            }
            for (Conversacion conversacion : user.getConversacionList()) {
                Usuario sbeve = new Usuario();
                if (conversacion.getIdUsuario1().getIdUsuario()==user.getIdUsuario())
                {
                    sbeve = conversacion.getIdUsuario2();
                }
                else {
                    sbeve = conversacion.getIdUsuario1();
                }
                String nombrecompleto = sbeve.getNombre() + " " + sbeve.getApellidos();
        %>            
        <tr>
            <td><a href="ServletConversacion?conversacion=<%=conversacion.getIdConversacion()%>"><%=nombrecompleto%></a></td>
            <td><%=sbeve.getRol().getDescripcion()%></td>
            <% if (conversacion.getMensajeList().isEmpty()) {
            %>
            <td>no hay mensajes</td>
            <td></td>
            <td></td>
            <%
            } else {
                Mensaje ultimomensaje = conversacion.getMensajeList().get(conversacion.getMensajeList().size() - 1);
                String horayminuto = ultimomensaje.getHora() + ":" + ultimomensaje.getMinuto();
            %>
            <td><%=ultimomensaje.getMensaje()%></td>
            <td><%=ultimomensaje.getIdUsuario().getNombre()%></td>
            <td><%=horayminuto%></td>
            <%
                }
            %>
        </tr>
        <%
            }
        %>    
    </tbody>
</table>
<h1>Lista de Teleoperadores</h1>
<%
    List<Usuario> listaTeleop = (List<Usuario>) request.getAttribute("listaTeleop");
    for (Usuario usu : listaTeleop) {
        if (user.getIdUsuario()!=usu.getIdUsuario())
        {
%>
<a href="ServletCrearConversacion?id=<%=usu.getIdUsuario()%>"><%=usu.getNombre()%></a>
<%
    } }
%>

<%            if (user.getRol().getIdRol() == 4 || user.getRol().getIdRol() == 2) {
%>
<form action="ServletMenuConversaciones">
    <input type="hidden" value="" name="busqueda">
    <input type="submit" value="Menu de Conversaciones">
</form>
<%
    }
%>
</body>
</html>
