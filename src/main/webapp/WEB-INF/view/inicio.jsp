<%-- 
    Document   : inicio
    Created on : May 11, 2021, 7:32:42 PM
    Author     : Ivanchu
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
        <title>EvenTAW - Inicio</title>        
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/estiloregistro.css">
    </head>
    <%  
      Usuario user = (Usuario)session.getAttribute("usuario");      
    %>
    <body>
        <ul>
            <li><a class="active" href="inicio.jsp">Inicio</a></li>
            <% if (user == null) { %>
            <li><a href="login.jsp">Identificarse</a></li>
            <li><a href="registro.jsp">Registro</a></li>
            <% } %>
            <% if (user != null) { %>
            <li><a href="/conversaciones">Ticket de ayuda</a></li>
            <!--<li style="float:right"><a href="about.asp">Mi cuenta</a></li>-->
            <% } %>
        </ul>
        <%
            if (user == null) { 
        %>
         <h1>Entra con tu cuenta para poder ver los eventos.</h1>
        <%
            } else {
                String nombreCompleto = user.getNombre() + " " + user.getApellidos();
                
        %>
        <h1>¡Bienvenido, <%=nombreCompleto%>!</h1><br/>
        <%
                if(user.getRol()==1){//Creador de eventos
                List<Evento> listaEventos = (List)session.getAttribute("listaEventos");
        %>

        <h2>Estos son tus eventos creados: </h2>
        <form action="ServletCrearEvento">
            <input type="submit" value="Añadir nuevo" />
        </form><br/>
        <h3>Filtro de búsqueda</h3>
        <%
            Boolean error = (Boolean) request.getAttribute("error");
            String filtroTitulo = (String) request.getAttribute("filtroTitulo");
            String filtroDescripcion = (String) request.getAttribute("filtroDescripcion");
            String filtroPrecioMin  = (String) request.getAttribute("filtroPrecioMin");
            String filtroPrecioMax = (String) request.getAttribute("filtroPrecioMax");
            String strfiltroTitulo = "", strfiltroDescripcion="", strfiltroPrecioMin="", strfiltroPrecioMax="";
            if(filtroTitulo!=null){
                strfiltroTitulo = filtroTitulo;
            }
            if(filtroDescripcion!=null){
                strfiltroDescripcion = filtroDescripcion;
            }
            if(filtroPrecioMin!=null){
                strfiltroPrecioMin = filtroPrecioMin;
            }
            if(filtroPrecioMax!=null){
                strfiltroPrecioMax = filtroPrecioMax;
            }
                    if (error!=null) {
                %>
                <div class="block">Error aplicando el filtro</div>
                <%
                    }
                %>
        <form action="ServletFiltroEventos">
            Título <input type="text" name="filtroTitulo" value="<%=strfiltroTitulo%>" />
            Descripción: <input type="text" name="filtroDescripcion" value="<%=strfiltroDescripcion%>"/>
            Precio min.: <input type="text" name="filtroPrecioMin" value="<%=strfiltroPrecioMin%>"/>
            Precio max.: <input type="text" name="filtroPrecioMax" value="<%=strfiltroPrecioMax%>"/>
            <input type="submit" value="Filtrar" />
            <a href="ServletFiltroEventos?des=1">Deshacer filtro</a>
        </form>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>TÍTULO</th>
                <th>DESCRIPCIÓN</th>
                <th>FECHA</th>
                <th>FECHA LÍMITE DE RESERVA</th>
                <th>PRECIO</th>
                <th>AFORO</th>
                <th>LÍMITE ENTRADAS/USUARIO</th>
                <th>ASIENTOS FIJOS</th>
                <th>NÚMERO DE FILAS</th>
                <th>ASIENTOS POR FILA</th>
            </tr>
            <%
              for(Evento evento : listaEventos){
                  SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                  String strFecha = df.format(evento.getFecha());
                  String strFechares = df.format(evento.getFechares());
            %>
            <tr>
                <td><%=evento.getIdEvento() %> </td>
                <td><%=evento.getTitulo() %> </td>
                <td><%=evento.getDescripcion() %> </td>
                <td><%=strFecha %> </td>
                <td><%=strFechares %> </td>
                <td><%=evento.getCoste() %> </td>
                <td><%=evento.getAforo() %> </td>
                <td><%=evento.getEntradas() %> </td>
                <td><%=evento.getAsientosfijos() %> </td>
                <td><%=evento.getNumfilas() %> </td>
                <td><%=evento.getNumasientosporfila() %> </td>
                <td><a href="ServletBorrarEvento?id=<%= evento.getIdEvento() %>">Borrar</a></td>
                <td><a href="ServletEditarEvento?id=<%= evento.getIdEvento() %>">Editar</a></td>  
            </tr>
            <%
               }
                %>
        </table>
        <%
                }
            if(user.getRol().getIdRol() == 2){
            %>
        <a href="ServletAdminMostrarUsuarios" style="margin:20px auto; text-align:center; display:block; width:120px;" class="button large hpbottom">Pagina de Admin</a>
        <%
            }
        }
        %>
    </body>
</html>
