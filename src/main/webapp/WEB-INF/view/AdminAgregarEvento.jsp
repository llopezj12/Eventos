<%@ page import="app.eventostaw.entity.Usuario" %>
<%@ page import="java.util.List" %><%--
    Document   : AdminAgregarEvento
    Created on : 16-may-2021, 10:52:34
    Author     : aaron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/estiloregistro.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EvenTAW - Crear Evento</title>
    </head>
    <body>
    <ul>
        <li><a href="/">Eventos disponibles</a></li>
        <li><a href="/conversaciones">Ticket de ayuda</a></li>
        <li><a href="/adminlistar" class="active" >Panel de Admin</a></li>
        <li style="float:right"><a href="/datosusuario">Mis datos</a></li>
    </ul>
        <%
            Boolean error = (Boolean)request.getAttribute("error");
            List<Usuario> listaU = (List)request.getAttribute("lista");
            String errorMsg = "";
            if (error == null) {
                error = false;
            }
            if (error) {
                errorMsg = (String)request.getAttribute("errorMsg");
            }
        %>
        <h1>Crear Evento</h1>
              <div class="form">
              <form method="post" action="/crearEvento">
              <input type="hidden" id="idEvento" name="idEvento">
              <label for="titulo">Titulo</label>
              <input type="text" id="titulo" name="titulo" ></br>
              <label for="fechaRes">Fecha</label>
              <input type="date" id="date" name="date"></br>
              <label for="fechaRes">Fecha Res</label>
              <input type="date" id="fechaRes" name="fechaRes"></br> 
              <label for="coste">Coste</label>
              <input type="text" id="coste" name="coste"></br>
              <label for="asi">¿Asientos fijos?</label>
              <input type="radio" id="asientos1" name="asientos" value="S" >Si
              <input type="radio" id="asientos2" name="asientos" value="N" >No</br>
              <input type="hidden" id="asi" name="asi">
              <label for="aforo">Aforo</label>
              <input type="text" id="aforo" name="aforo"></br> 
              <label for="entradas">Entradas</label>
              <input type="text" id="entradas" name="entradas"></br>
              <label for="nfilas">Num Filas</label>
              <input type="text" id="nfilas" name="nfilas"></br>
              <label for="asifil">Num Asientos por Fila</label>
              <input type="text" id="asifil" name="asifil"></br>
              <label for="idcre">Creador</label>
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
              <input type="text" id="desc" name="desc"></br>
                <%
                    if (error) {
                %>
                <div class="block">
                    Error procesando datos: <%=errorMsg%>
                </div>
                <%
                    }
                %>
              <input type="submit" value="Guardar"/></br>
              </form>
        </div>
    </body>
</html>
