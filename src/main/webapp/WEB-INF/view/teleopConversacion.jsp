<%@ page import="app.eventostaw.entity.Conversacion" %>
<%@ page import="app.eventostaw.entity.Usuario" %>
<%@ page import="app.eventostaw.entity.Mensaje" %><%--
    Document   : TeleopConversacion
    Created on : May 14, 2021, 7:07:06 PM
    Author     : Ivanchu
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="/css/estilo.css">
        <link rel="stylesheet" href="/css/estiloconversacion.css">
    </head>
    <%
        Usuario user = (Usuario) session.getAttribute("usuario");
        Conversacion conv = (Conversacion) request.getAttribute("conversacion");
    %>    
    <body>
    <div class="wrap-table100" style="margin: auto">
        <div class="table100">
        <table width="500">
            <thead>
            <tr class="table100-head">
            <th><%=conv.getUsuarioByIdUsuario1().getNombre() %></th>
            <th><%=conv.getUsuarioByIdUsuario2().getNombre() %></th>
            <th>Hora</th>
            </tr>
            </thead>
                <%
                    for (Mensaje m : conv.getMensajesByIdConversacion()) {

                %>
                <tr>
                    <%                    
                        String horayminuto = m.getHora() + ":" + m.getMinuto();
                        if (m.getUsuarioByIdUsuario().getIdUsuario() == conv.getUsuarioByIdUsuario1().getIdUsuario()) {
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
        </div>
        
        <form class="form-inline" action="/conversaciones" >
            <button type="submit">Ver Conversaciones</button>
        </form>
    </body>
</html>
