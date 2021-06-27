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
        <link rel="stylesheet" href="/css/estilo.css">
        <link rel="stylesheet" href="/css/estiloregistro.css">
    </head>
    <%  
      Usuario user = (Usuario)session.getAttribute("usuario");
    %>
    <body>
    <ul>
        <li><a class="active" href="/creador">Inicio</a></li>
        <% if (user == null) { %>
        <li><a href="/login">Identificarse</a></li>
        <li><a href="/registro">Registro</a></li>
        <% } %>
        <% if (user != null) { %>
        <li><a href="/conversaciones">Ticket de ayuda</a></li>
        <li style="float:right"><a href="/cerrarsesion">Cerrar Sesión</a></li>
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
                if(user.getRolesByRol().getIdRol()==1){//Creador de eventos
                List<Evento> listaEventos = (List)request.getAttribute("listaEventos");

        %>

        <h2>Estos son tus eventos creados: </h2>
        <form action="/nuevoEvento">
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
        <form method="post" action="/filtroEventos">
            Título <input type="text" name="filtroTitulo" value="<%=strfiltroTitulo%>" />
            Descripción: <input type="text" name="filtroDescripcion" value="<%=strfiltroDescripcion%>"/>
            Precio min.: <input type="text" name="filtroPrecioMin" value="<%=strfiltroPrecioMin%>"/>
            Precio max.: <input type="text" name="filtroPrecioMax" value="<%=strfiltroPrecioMax%>"/>
            <input type="submit" value="Filtrar" />
            <a href="/creador">Deshacer filtro</a>
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
                  String numFilas="-";
                  String asientosFila="-";
                  if(evento.getNumfilas()!=null){
                      numFilas = String.valueOf(evento.getNumfilas());
                  }
                  if(evento.getNumasientosporfila()!=null){
                      asientosFila = String.valueOf(evento.getNumasientosporfila());
                  }

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
                <td><%=numFilas %> </td>
                <td><%=asientosFila%> </td>
                <td><a href="eliminarEvento/<%= evento.getIdEvento() %>">Borrar</a></td>
                <td><a href="editarEvento/<%= evento.getIdEvento() %>">Editar</a></td>
            </tr>
            <%
               }
                %>
        </table>
        <%
                }
            if(user.getRolesByRol().getIdRol() == 2){
            %>
        <a href="ServletAdminMostrarUsuarios" style="margin:20px auto; text-align:center; display:block; width:120px;" class="button large hpbottom">Pagina de Admin</a>
        <%
            }
        }
        %>
    </body>
</html>
