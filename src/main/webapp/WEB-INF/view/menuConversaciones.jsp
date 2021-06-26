<%-- 
    Document   : menuConversaciones
    Created on : May 14, 2021, 12:18:58 PM
    Author     : Ivanchu
--%>


<%@page import="java.util.List"%>
<%@ page import="app.eventostaw.entity.Conversacion" %>
<%@ page import="app.eventostaw.entity.Mensaje" %>
<%@ page import="app.eventostaw.entity.Usuario" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu de Conversaciones</title>
    </head>
    <body>
        <h1>Lista de Conversaciones</h1>
        <form action="/menuConversaciones">
            <%
                String b = (String)request.getAttribute("busqueda");
                List<Conversacion> lista = (List<Conversacion>)request.getAttribute("listacontiene");
                if (b==null) {b="";}
                %>
                
            <label> <!--for="nombre" -->Busqueda: </label><input type="text" maxlength="20" size="25" name="busqueda" value="<%=b%>">
            <input type="submit" value="Buscar">
        </form>
        <table border="1" style="width:100%">
            <tr>
                <th>Usuario</th>
                <th>Tipo de Usuario</th>
                <th>Usuario</th>
                <th>Tipo de Usuario</th>
                <th>Ultimo mensaje</th>
                <th>Escrito Por</th>
                <th>A las:</th>
                <th>Ver Conversacion</th>
                <th>Eliminar Conversacion</th>
                
            </tr>
            <%
                
                for (Conversacion c : lista)
                {
                    String nombrecompleto1 = c.getUsuarioByIdUsuario1().getNombre() + " " + c.getUsuarioByIdUsuario1().getApellidos();
                    String nombrecompleto2 = c.getUsuarioByIdUsuario2().getNombre() + " " + c.getUsuarioByIdUsuario2().getApellidos();
            %>
            <tr>
                <td><%= nombrecompleto1 %></td>
                <td><%= c.getUsuarioByIdUsuario1().getRolesByRol().getDescripcion() %></td>
                <td><%= nombrecompleto2 %></td>
                <td><%= c.getUsuarioByIdUsuario2().getRolesByRol().getDescripcion() %></td>
                <%
                if (c.getMensajesByIdConversacion().isEmpty()) {

            %>
            <td>no hay mensajes</td>
            <td></td>
            <td></td>
            <%                } else {
                List<Mensaje> lm = (List<Mensaje>) c.getMensajesByIdConversacion();
                Mensaje ultimomensaje = lm.get(lm.size() - 1);
                String horayminuto = ultimomensaje.getHora() + ":" + ultimomensaje.getMinuto();

            %>
            <td><%=ultimomensaje.getMensaje()%></td>
            <td><%=ultimomensaje.getUsuarioByIdUsuario().getNombre()%></td>
            <td><%=horayminuto%></td>
            <%
                }
            %>
            <td><a href="ServletTeleopConversacion?id=<%=c.getIdConversacion() %>">Ver Conversacion</a></td>
            <td><a href="borrarConversacion/<%=c.getIdConversacion() %>">Eliminar Conversacion</a></td>
            </tr>
            <% 
                }
                %>
        </table>
        
        <form action="conversaciones/">
            <input type="submit" value="volver">
        </form>
        <br>
        
        <h1>Crear Conversacion con usuario</h1>
        
        <% 
            List<Usuario> listaUsuario = (List<Usuario>)request.getAttribute("listaUsuario");
            for (Usuario u : listaUsuario)
            {
                if (u.getRolesByRol().getIdRol() == 1 || u.getRolesByRol().getIdRol()== 3)
                {
            %>
            
            <a href="crearConversacion/<%=u.getIdUsuario()%>"><%=u.getNombre()%> </a>
            
            <% } } %>

    </body>
</html>
