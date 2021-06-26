<%-- 
    Document   : conversacion
    Created on : May 13, 2021, 6:16:33 PM
    Author     : Ivanchu
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="app.eventostaw.entity.Usuario" %>
<%@ page import="app.eventostaw.entity.Conversacion" %>
<%@ page import="app.eventostaw.entity.Mensaje" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conversacion</title>
    </head>
    <%
        Usuario user = (Usuario) session.getAttribute("usuario");
        Conversacion conv = (Conversacion) request.getAttribute("conversacion");

    %>    
    <body>
        <table width="500">
            <tbody>

                <%
                    for (Mensaje m : conv.getMensajesByIdConversacion()) {

                %>
                <tr>
                    <%
                        String horayminuto =  "" + m.getHora();
                        if (m.getMinuto() < 10)
                        {
                            horayminuto += ":0" + m.getMinuto();
                        }
                    else {
                         horayminuto += ":" + m.getMinuto();
                        }
                        if (m.getUsuarioByIdUsuario().getIdUsuario() == user.getIdUsuario()) {
                    %>

                    <td align ="right"><%=m.getMensaje()%></td>

                    <%
                    } else {

                    %>

                    <td align ="left"><%=m.getMensaje()%></td>

                    <%
                        }
                    %>

                    <td><%= horayminuto%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <form action="/mensaje">
            <input type="hidden" name="conver" value="<%= conv.getIdConversacion()%>">

            <input type="text" id="Mensaje" name="msg"> <br>
            <input type="submit" value="Enviar Mensaje"><br>
        </form>
        <form action="/conversaciones" >
            <input type="submit" value="Ver Conversaciones">
        </form>
    </body>
</html>
