<%-- 
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
        <%
            Boolean error = (Boolean)request.getAttribute("error");
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
              <form action="ServletAdminCrearEvento">
              <input type="hidden" id="idEvento" name="idEvento">
              <label for="titu">Titulo</label>
              <input type="text" id="titulo" name="titulo" ></br>
              <label for="fech">Fecha</label>
              <input type="date" id="date" name="date"></br>
              <label for="ciu">Fecha Res</label>
              <input type="date" id="fechaRes" name="fechaRes"></br> 
              <label for="cos">Coste</label>
              <input type="text" id="coste" name="coste"></br>
              <label for="asi">¿Asientos fijos?</label>
              <input type="radio" id="asientos" name="asientos" value="S" >Si
              <input type="radio" id="asientos" name="asientos" value="N" >No</br>
              <input type="hidden" id="asi" name="asi">
              <label for="afo">Aforo</label>
              <input type="text" id="aforo" name="aforo"></br> 
              <label for="ent">Entradas</label>
              <input type="text" id="entradas" name="entradas"></br>
              <label for="nfil">Num Filas</label>
              <input type="text" id="nfilas" name="nfilas"></br>
              <label for="asifil">Num Asientos por Fila</label>
              <input type="text" id="asifil" name="asifil"></br>
              <label for="idcre">ID Creador</label>
              <input type="text" id="idcre" name="idcre"></br>
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
