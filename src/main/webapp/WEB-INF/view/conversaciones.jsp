<%-- 
    Document   : conversacion
    Created on : May 13, 2021, 1:53:52 PM
    Author     : Ivanchu
--%>

<%@page import="java.util.List"%>
<%@ page import="app.eventostaw.entity.Usuario" %>
<%@ page import="app.eventostaw.entity.Conversacion" %>
<%@ page import="app.eventostaw.entity.Mensaje" %>
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
    <body>
    <table border="1">
        <thead>


        <tr>

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
                if (conversacion.getUsuarioByIdUsuario1().getIdUsuario()==user.getIdUsuario())
                {
                    sbeve = conversacion.getUsuarioByIdUsuario2();
                }
                else {
                    sbeve = conversacion.getUsuarioByIdUsuario2();
                }
                String nombrecompleto = sbeve.getNombre() + " " + sbeve.getApellidos();
        %>            
        <tr>
            <td><a href="conversacion/<%=conversacion.getIdConversacion()%>"><%=nombrecompleto%></a></td>
            <td><%=sbeve.getRolesByRol().getDescripcion()%></td>
            <%
                if (conversacion.getMensajesByIdConversacion().isEmpty()) {

            %>
            <td>no hay mensajes</td>
            <td></td>
            <td></td>
            <%                } else {

                List<Mensaje> lm = (List<Mensaje>) conversacion.getMensajesByIdConversacion();
                Mensaje ultimomensaje = lm.get(lm.size() - 1);

                String horayminuto =  "" + ultimomensaje.getHora();
                if (ultimomensaje.getMinuto() < 10)
                {
                    horayminuto += ":0" + ultimomensaje.getMinuto();
                }
                else {
                    horayminuto += ":" + ultimomensaje.getMinuto();
                }


            %>
            <td><%=ultimomensaje.getMensaje()%></td>
            <td><%=ultimomensaje.getUsuarioByIdUsuario().getNombre()%></td>
            <td><%=horayminuto%></td>
            <%
                }
            %>
        </tr>
        <%
            }
            for (Conversacion conversacion : user.getConversacionsByIdUsuario_0()) {
                Usuario sbeve = new Usuario();
                if (conversacion.getUsuarioByIdUsuario1().getIdUsuario()==user.getIdUsuario())
                {
                    sbeve = conversacion.getUsuarioByIdUsuario2();
                }
                else {
                    sbeve = conversacion.getUsuarioByIdUsuario1();
                }
                String nombrecompleto = sbeve.getNombre() + " " + sbeve.getApellidos();
        %>            
        <tr>
            <td><a href="conversacion/<%=conversacion.getIdConversacion()%>"><%=nombrecompleto%></a></td>
            <td><%=sbeve.getRolesByRol().getDescripcion()%></td>
            <% if (conversacion.getMensajesByIdConversacion().isEmpty()) {
            %>
            <td>no hay mensajes</td>
            <td></td>
            <td></td>
            <%
            } else {
                List<Mensaje> lm = (List<Mensaje>) conversacion.getMensajesByIdConversacion();
                Mensaje ultimomensaje = lm.get(lm.size() - 1);
                String horayminuto =  "" + ultimomensaje.getHora();
                if (ultimomensaje.getMinuto() < 10)
                {
                    horayminuto += ":0" + ultimomensaje.getMinuto();
                }
                else {
                    horayminuto += ":" + ultimomensaje.getMinuto();
                }

            %>
            <td><%=ultimomensaje.getMensaje()%></td>
            <td><%=ultimomensaje.getUsuarioByIdUsuario().getNombre()%></td>
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
<a href="crearConversacion/<%=usu.getIdUsuario()%>"><%=usu.getNombre()%></a>
<%
    } }
%>

<%            if (user.getRolesByRol().getIdRol() == 4 || user.getRolesByRol().getIdRol()== 2) {
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
