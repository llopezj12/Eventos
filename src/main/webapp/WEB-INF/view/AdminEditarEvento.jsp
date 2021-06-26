<%--
    Document   : AdminEditarEvento
    Created on : 14-may-2021, 19:15:16
    Author     : aaron
--%>
<%@ page import="app.eventostaw.entity.Evento" %>
<%@ page import="app.eventostaw.entity.Usuario" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/css/estilo.css">
        <link rel="stylesheet" href="/css/estiloregistro.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <%
        Evento e = (Evento)request.getAttribute("evento");
        List<Usuario> listaU = (List)request.getAttribute("lista");
    %>
        <h1>Editar evento(ID = <%= e.getIdEvento() %>) </h1>
        <div class="form">
              <form method="post" action="/guardarEvento">
              <input type="hidden" id="idEvento" name="idEvento" value="<%= e.getIdEvento() %>">
              <label for="titulo">Titulo</label>
              <input type="text" id="titulo" name="titulo" value="<%= e.getTitulo() %>"></br>
              <label for="fechaRes">Fecha</label>
              <input type="date" id="date" name="date" value="<%= e.getFecha() %>"></br>
              <label for="fechaRes">Fecha Res</label>
              <input type="date" id="fechaRes" name="fechaRes" value="<%= e.getFechares() %>"></br> 
              <label for="coste">Coste</label>
              <input type="text" id="coste" name="coste" value="<%= e.getCoste() %>"></br>
              <label>¿Asientos fijos?</label>
                  <%
                      if(e.getAsientosfijos().equals("S")){

                  %>
                  <input type="radio" id="asientos1" name="asientos" value="S" checked>Si
                  <input type="radio" id="asientos2" name="asientos" value="N" >No</br>
                  <%
                      } else{

                  %>
                  <input type="radio" id="asientos1" name="asientos" value="S" >Si
                  <input type="radio" id="asientos2" name="asientos" value="N" checked>No</br>
                  <%
                      }
                  %>
              <input type="hidden" id="asi" name="asi" value="<%= e.getAsientosfijos() %>">
              <label for="aforo">Aforo</label>
              <input type="text" id="aforo" name="aforo" value="<%= e.getAforo() %>"></br> 
              <label for="entradas">Entradas</label>
              <input type="text" id="entradas" name="entradas" value="<%= e.getEntradas() %>"></br>
              <label for="nfilas">Num Filas</label>
              <input type="text" id="nfilas" name="nfilas" value="<%= e.getNumfilas() %>"></br>
              <label for="asifil">Num Asientos por Fila</label>
              <input type="text" id="asifil" name="asifil" value="<%= e.getNumasientosporfila() %>"></br>
                  <label for="idcre">ID Creador</label>
                  <select id="idcre" name="idcre"></br>
                      <%
                          for(Usuario u : listaU){

                      %>
                      <option value="<%=u.getIdUsuario()%>"><%=u.getNombre() + " " + u.getApellidos()%></option>
                      <%
                          }
                      %>
                  </select><br>
              <label for="desc">Descripcion</label>
              <input type="text" id="desc" name="desc" value="<%= e.getDescripcion() %>"></br>
              <input type="submit" value="Guardar"/></br>
              </form>
        </div>
    </body>
</html>
