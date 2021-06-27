<%--
    Document   : crearEvento
    Created on : 13-may-2021, 11:57:24
    Author     : luilo
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="app.eventostaw.entity.Evento"%>
<%@page import="app.eventostaw.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>EvenTAW - Crear evento</title>
        <link rel="stylesheet" href="/css/estilo.css">
        <link rel="stylesheet" href="/css/estiloregistro.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            function showContent() {
                element = document.getElementById("content");
                check = document.getElementById("asientosFijos");
                if (check.checked) {
                    element.style.display='block';
                }
                else {
                    element.style.display='none';
                }
            }
        </script>
    </head>
    <%
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        Boolean error = (Boolean)request.getAttribute("error");
        String errorMsg = "";
        if (error == null) {
            error = false;
        }
        if (error) {
            errorMsg = (String)request.getAttribute("errorMsg");
        }
        Evento evento = (Evento) request.getAttribute("evento");
        String strId="", strTitulo = "", strDescripcion = "", strPrecio = "",strFecha="", strFechaRes="",
                strAforo = "", strEntradas = "", strAsientosFijos = "", strNumFilas = "", strAsientosFila = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        if(evento!=null){//Editar evento
            strId = String.valueOf(evento.getIdEvento());
            strTitulo = evento.getTitulo();
            strDescripcion = evento.getDescripcion();
            strFecha = df.format(evento.getFecha());
            strFechaRes = df.format(evento.getFechares());
            strPrecio = evento.getCoste().toString();
            strAforo = evento.getAforo().toString();
            strEntradas = evento.getEntradas().toString();
            if(evento.getAsientosfijos().equals("S")){
                strAsientosFijos = "checked";
                strNumFilas = evento.getNumfilas().toString();
                strAsientosFila = evento.getNumasientosporfila().toString();
            }
        }
%>
    <body>

        <h1>Rellene los siguientes campos para crear un evento:</h1>
        <div class="form">
            <form method="post" action="/guardarEventoCreador">
                <input type="hidden" name="id" value="<%= strId %>" />
                    <label>Título: </label><input type="text" name="titulo" maxlength="60" size="60" value="<%=strTitulo%>" /></br>

                    <label>Descripción: </label><textarea name="descripcion" rows="5" cols="60" ><%=strDescripcion%></textarea></br>

                    <label>Fecha del evento: </label><input type="date" name="fecha" value="<%=strFecha%>"></br>

                    <label>Fecha límite de reserva de entradas: </label><input type="date" name="fechaReserva" value="<%=strFechaRes%>"></br>

                    <label>Precio: </label><input type="text" name="precio"  maxlength="10" size="10" value="<%=strPrecio%>" /></br>

                    <label>Aforo: </label><input type="text" name="aforo"  maxlength="10" size="10" value="<%=strAforo%>" /></br>

                    <label>Número máximo de entradas por usuario: </label><input type="text" name="limiteEntradas"  maxlength="10" size="10" value="<%=strEntradas%>" /></br>

                    <label for="asientosFijos">Asientos fijos asignados: </label>
                <%
                if(strAsientosFijos.length()>0){
                %>
                <input type="checkbox" name="asientosFijos" id="asientosFijos" value="asientosFijos" onchange="javascript:showContent()" checked="" /></br>
                <div class="block" id="content" >
                    <label>Número de filas: </label><input type="text" name="numFilas"  maxlength="10" size="10" value="<%=strNumFilas%>" /></br>
                    <label>Número de asientos por fila: </label><input type="text" name="asientosFila"  maxlength="10" size="10" value="<%=strAsientosFila%>" /></br></br>
                </div>
                <%
                    }else{
                %>
                <input type="checkbox" name="asientosFijos" id="asientosFijos" value="asientosFijos" onchange="javascript:showContent()"/></br>
                <div class="block" id="content" style="display: none;">
                    <label>Número de filas: </label><input type="text" name="numFilas"  maxlength="10" size="10" value="<%=strNumFilas%>" /></br>
                    <label>Número de asientos por fila: </label><input type="text" name="asientosFila"  maxlength="10" size="10" value="<%=strAsientosFila%>" /></br></br>
                </div>
                <%
                    }
                %>

                <%
                    if (error) {
                %>
                <div class="block">
                    Error procesando datos: <%=errorMsg%>
                </div>
                <%
                    }
                %>
                <input type="submit" value="Guardar evento">
            </form>
        </div>
    </body>
</html>
