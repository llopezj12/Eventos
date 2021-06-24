<%-- 
    Document   : AdminEditarEvento
    Created on : 14-may-2021, 19:15:16
    Author     : aaron
--%>

<%@page import="eventos.entity.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/estiloregistro.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <%
        Evento e = (Evento)request.getAttribute("evento");
    %>
    <body>
        <h1>Editar evento(ID = <%= e.getIdEvento() %>) </h1>
        <div class="form">
              <form action="ServletAdminGuardarEdicionEvento">
              <input type="hidden" id="idEvento" name="idEvento" value="<%= e.getIdEvento() %>">
              <label for="titu">Titulo</label>
              <input type="text" id="titulo" name="titulo" value="<%= e.getTitulo() %>"></br>
              <label for="fech">Fecha</label>
              <input type="date" id="date" name="date" value="<%= e.getFecha() %>"></br>
              <label for="ciu">Fecha Res</label>
              <input type="date" id="fechaRes" name="fechaRes" value="<%= e.getFechares() %>"></br> 
              <label for="cos">Coste</label>
              <input type="text" id="coste" name="coste" value="<%= e.getCoste() %>"></br>
              <label for="asi">¿Asientos fijos?</label>
              <input type="radio" id="asientos" name="asientos" value="S" >Si
              <input type="radio" id="asientos" name="asientos" value="N" >No</br>
              <input type="hidden" id="asi" name="asi" value="<%= e.getAsientosfijos() %>">
              <label for="afo">Aforo</label>
              <input type="text" id="aforo" name="aforo" value="<%= e.getAforo() %>"></br> 
              <label for="ent">Entradas</label>
              <input type="text" id="entradas" name="entradas" value="<%= e.getEntradas() %>"></br>
              <label for="nfil">Num Filas</label>
              <input type="text" id="nfilas" name="nfilas" value="<%= e.getNumfilas() %>"></br>
              <label for="asifil">Num Asientos por Fila</label>
              <input type="text" id="asifil" name="asifil" value="<%= e.getNumasientosporfila() %>"></br>
              <label for="idcre">ID Creador</label>
              <input type="text" id="idcre" name="idcre" value="<%= e.getIdCreador().getIdUsuario() %>"></br>
              <label for="desc">Descripcion</label>
              <input type="text" id="desc" name="desc" value="<%= e.getDescripcion() %>"></br>
              <input type="submit" value="Guardar"/></br>
              </form>
        </div>
    </body>
</html>
