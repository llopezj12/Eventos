<%-- 
    Document   : menuConversaciones
    Created on : May 14, 2021, 12:18:58 PM
    Author     : Ivanchu
--%>

<%@page import="app.eventostaw.entity.Usuario"%>
<%@page import="app.eventostaw.entity.Mensaje"%>
<%@page import="java.util.List"%>
<%@page import="app.eventostaw.entity.Conversacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu de Conversaciones</title>
    </head>
    <body>
        <h1>Lista de Conversaciones</h1>
        <form action="ServletMenuConversaciones">
            <%
                String b = (String)request.getAttribute("busqueda");
                List<Conversacion> lista = (List<Conversacion>)request.getAttribute("listacontiene");
                if (b==null) {b="";}
                %>
                
            <label for="nombre">Busqueda: </label><input type="text" maxlength="20" size="25" name="busqueda" value="<%=b%>">
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
                    String nombrecompleto1 = c.getIdUsuario1().getNombre() + " " + c.getIdUsuario1().getApellidos();
                    String nombrecompleto2 = c.getIdUsuario2().getNombre() + " " + c.getIdUsuario2().getApellidos();
            %>
            <tr>
                <td><%= nombrecompleto1 %></td>
                <td><%= c.getIdUsuario1().getRol().getDescripcion() %></td>
                <td><%= nombrecompleto2 %></td>
                <td><%= c.getIdUsuario2().getRol().getDescripcion() %></td>
                <%
                if (c.getMensajeList().isEmpty()) {

            %>
            <td>no hay mensajes</td>
            <td></td>
            <td></td>
            <%                } else {
                Mensaje ultimomensaje = c.getMensajeList().get(c.getMensajeList().size() - 1);
                String horayminuto = ultimomensaje.getHora() + ":" + ultimomensaje.getMinuto();
            %>
            <td><%=ultimomensaje.getMensaje()%></td>
            <td><%=ultimomensaje.getIdUsuario().getNombre()%></td>
            <td><%=horayminuto%></td>
            <%
                }
            %>
            <td><a href="ServletTeleopConversacion?id=<%=c.getIdConversacion() %>">Ver Conversacion</a></td>
            <td><a href="ServletBorrarConversacion?id=<%=c.getIdConversacion() %>">Eliminar Conversacion</a></td>
            </tr>
            <% 
                }
                %>
        </table>
        
        <form action="ServletConversaciones">
            <input type="submit" value="volver">
        </form>
        <br>
        
        <h1>Crear Conversacion con usuario</h1>
        
        <% 
            List<Usuario> listaUsuario = (List<Usuario>)session.getAttribute("listaUsuario");
            for (Usuario u : listaUsuario)
            {
                if (u.getRol().getIdRol() == 1 || u.getRol().getIdRol()== 3)
                {
            %>
            
            <a href="ServletCrearConversacion?id=<%=u.getIdUsuario()%>"><%=u.getNombre()%> </a>
            
            <% } } %>

    </body>
</html>
