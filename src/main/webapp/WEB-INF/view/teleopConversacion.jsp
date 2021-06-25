<%-- 
    Document   : TeleopConversacion
    Created on : May 14, 2021, 7:07:06 PM
    Author     : Ivanchu
--%>

<%@page import="app.eventostaw.entity.Mensaje"%>
<%@page import="app.eventostaw.entity.Conversacion"%>
<%@page import="app.eventostaw.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        Usuario user = (Usuario) session.getAttribute("usuario");
        Conversacion conv = (Conversacion) request.getAttribute("conversacion");
    %>    
    <body>
        <table border="1" width="500">
            <th><%=conv.getIdUsuario1().getNombre() %></th>
            <th><%=conv.getIdUsuario2().getNombre() %></th>
            <th>Hora</th>
                <%
                    for (Mensaje m : conv.getMensajeList()) {

                %>
                <tr>
                    <%                    
                        String horayminuto = m.getHora() + ":" + m.getMinuto();
                        if (m.getIdUsuario().getIdUsuario() == conv.getIdUsuario1().getIdUsuario()) {
                    %>

                    <td align="center"><%=m.getMensaje()%></td>
                    <td></td>

                    <%
                    } else {

                    %>
                    
                    <td></td>
                    <td align="center"><%=m.getMensaje()%></td>
                    

                    <%
                        }
                    %>

                    <td align="center"><%= horayminuto%></td>
                </tr>
                <%
                    }
                %>
        </table>
        
        <form action="ServletConversaciones" >
            <input type="submit" value="Ver Conversaciones">
        </form>
    </body>
</html>
