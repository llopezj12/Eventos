<%@ page import="app.eventostaw.entity.Usuario" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="app.eventostaw.entity.UsuarioInscrito" %>
<%@ page import="app.eventostaw.entity.Evento" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="app.eventostaw.util.Consulta" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: EloyB
  Date: 26/06/2021
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EvenTAW - Mis datos</title>
    <link rel="stylesheet" href="/css/estilo.css">
    <link rel="stylesheet" href="/css/estiloconversacion.css">
</head>
<%
    Usuario user = (Usuario)session.getAttribute("usuario");
    List<Evento> inscritos = (List)request.getAttribute("listaInscritos");
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    if (user == null) {
%>
<jsp:forward page="/"/>
<%
    }
    List<Evento> abiertos = new ArrayList<Evento>();
    List<Evento> reservados = new ArrayList<Evento>();
    List<Evento> historial = new ArrayList<Evento>();

    for (Evento e : inscritos) {
        if (e.getFechares().after(new Date())) {
            abiertos.add(e);
        } else {
            if (e.getFecha().before(new Date())) {
                historial.add(e);
            } else {
                reservados.add(e);
            }
        }
    }

%>
<body>
<ul>
    <%
    if(user.getRolesByRol().getIdRol() ==1){
    %>
    <li><a href="/creador">Inicio</a></li>
    <%
        }else{
    %>
    <li><a href="/">Eventos disponibles</a></li>
    <%
        }
    %>

    <li><a href="/conversaciones">Ticket de ayuda</a></li>
    <%
        if(user.getRolesByRol().getIdRol() == 2){

    %>
        <li><a href="/adminlistar">Panel de Admin</a></li>
    <%
        }
    %>
    <li style="float:right"><a href="/datosusuario" class="active">Mis datos</a></li>
</ul>
<h1 style="margin-top: 75px; margin-left: 270px">Datos del perfil</h1>
<a style="background-color: indianred; color:white; font-size: 20px; padding: 10px; float:right; margin-right:200px" href="/cerrarsesion">Cerrar Sesi??n</a>
<div style="margin-left: 300px">
    <div>E-mail: <%=user.getEmail()%></div>
    <div>Nombre: <%=user.getNombre()%></div>
    <div>Apellidos: <%=user.getApellidos()%></div>
    <div>Sexo: <%=user.getGenero()%></div>
    <div>Fecha de nacimiento: <%=df.format(user.getNacimiento())%></div>
    <div>Ciudad: <%=user.getCiudad()%></div>
    <div>Domicilio: <%=user.getDomicilio()%></div>
    <br>
    <div>Est??s registrado como <%=user.getRolesByRol().getDescripcion()%>.</div>
    <%
        if(user.getRolesByRol().getIdRol() != 2 && user.getRolesByRol().getIdRol() != 4){

    %>
    <div>Contacta con un teleoperador para cambiar tus datos.</div>
    <%
        }
    %>
    <br>
</div>
<div></div>
    <h1 style="margin-left: 270px">Eventos en los que te has inscrito</h1>
        <form class="form-inline" action="/datosusuario" style="margin-left: 320px; margin-bottom: 20px">
            <label for="clave">Palabra clave</label>
            <input size="24" type="text" id="clave" placeholder="Busca por t??tulo o descripci??n" name="clave" <% if (request.getAttribute("clave") != null) { %>value="<%=request.getAttribute("clave")%>"<% } %>>

            <label for="precio">Precio m??x.</label>
            <input size="1" type="text" id="precio" name="precio" <% if (request.getAttribute("precio") != null) { %>value="<%=request.getAttribute("precio")%>"<% } %>>

            <label for="aforo">Aforo m??x.</label>
            <input size="1" type="text" id="aforo" name="aforo" <% if (request.getAttribute("aforo") != null) { %>value="<%=request.getAttribute("aforo")%>"<% } %>>

            <label for="fecha1">Fecha</label>
            <input type="date" id="fecha1" name="fecha1" <% if (request.getAttribute("fecha1") != null) { %>value="<%=request.getAttribute("fecha1")%>"<% } %>>
            <input type="date" id="fecha2" name="fecha2" <% if (request.getAttribute("fecha2") != null) { %>value="<%=request.getAttribute("fecha2")%>"<% } %>>

            <label for="invertir">Invertir orden</label>
            <input type="checkbox" id="invertir" name="invertir" <% if (request.getAttribute("invertir") != null) { %>checked<% } %>>

            <button type="submit">Buscar</button>
        </form>
        <% if (inscritos.isEmpty()) { %>
            <h3 style="margin-left: 300px">No te has inscrito a ning??n evento.</h3>
        <% } else { %>
            <h2 style="margin-left: 300px">Eventos abiertos</h2>
                <% if (abiertos.isEmpty()) { %>
                    <h4 style="margin-left: 340px">No tienes ning??n evento abierto.</h4>
                <% } else { %>
                    <h4 style="margin-left: 340px">Mientras el evento este abierto, tienes hasta la fecha l??mite para anularlo.</h4>
                        <div class="table100">
                            <table class="customTable table100" style="margin-left: 340px">
                                <thead class="table100-head">
                                    <th class="c">T??tulo</th>
                                    <th class="c" style="width: 30%">Descripci??n</th>
                                    <th class="c">Coste</th>
                                    <th class="c">Aforo</th>
                                    <th class="c">Asientos Fijos</th>
                                    <th class="c">Fecha l??mite de reserva</th>
                                    <th class="c">Fecha del evento</th>
                                    <th class="c">Entradas disponibles</th>
                                    <th></th>
                                </thead>
                                    <% for (Evento e: abiertos) { %>
                                        <tr>
                                            <td class="c"><%=e.getTitulo()%></td>
                                            <td class="c"><%=e.getDescripcion()%></td>
                                            <td class="c"><%=e.getCoste()%></td>
                                            <td class="c"><%=e.getAforo()%></td>
                                            <% if (e.getAsientosfijos().compareTo("S") == 0) { %>
                                                <td class="c"><%=e.getNumasientosporfila()%> x <%=e.getNumfilas()%> filas</td>
                                            <% } else { %>
                                                <td class="c">-</td>
                                            <% } %>
                                            <td class="c"><%=df2.format(e.getFechares())%></td>
                                            <td class="c"><%=df2.format(e.getFecha())%></td>
                                            <td class="c"><%=e.getEntradas()%></td>
                                            <td><a href="/QuitarEventoD?idEvento=<%=e.getIdEvento()%>" style="color: red">Anular</a></td>
                                        </tr>
                                    <% } %>
                            </table>
                        </div>
                <% } %>
            <h2 style="margin-left: 300px">Eventos ya reservados</h2>
                <% if (reservados.isEmpty()) { %>
                    <h4 style="margin-left: 340px">No tienes ning??n evento ya reservado.</h4>
                <% } else { %>
                    <div class="table100">
                        <table class="customTable table100" style="margin-left: 340px">
                            <thead class="table100-head">
                                <th class="c">T??tulo</th>
                                <th class="c" style="width: 30%">Descripci??n</th>
                                <th class="c">Coste</th>
                                <th class="c">Aforo</th>
                                <th class="c">Asientos Fijos</th>
                                <th class="c">Fecha l??mite de reserva</th>
                                <th class="c">Fecha del evento</th>
                            </thead>
                                <% for (Evento e: reservados) { %>
                                    <tr>
                                        <td class="c"><%=e.getTitulo()%></td>
                                        <td class="c"><%=e.getDescripcion()%></td>
                                        <td class="c"><%=e.getCoste()%></td>
                                        <td class="c"><%=e.getAforo()%></td>
                                            <% if (e.getAsientosfijos().compareTo("S") == 0) { %>
                                            <td class="c"><%=e.getNumasientosporfila()%> x <%=e.getNumfilas()%> filas</td>
                                            <% } else { %>
                                            <td class="c">-</td>
                                            <% } %>
                                        <td class="c"><%=df2.format(e.getFechares())%></td>
                                        <td class="c"><%=df2.format(e.getFecha())%></td>
                                    </tr>
                                <% } %>
                        </table>
                    </div>
                <% } %>
            <h2 style="margin-left: 300px">Historial de eventos pasados</h2>
                <% if (historial.isEmpty()) { %>
                    <h4 style="margin-left: 340px">No tienes ning??n evento anterior.</h4>
                <% } else { %>
                    <div class="table100">
                        <table class="customTable table100" style="margin-left: 340px; margin-bottom: 50px">
                            <thead class="table100-head">
                                <th class="c">T??tulo</th>
                                <th class="c" style="width: 30%">Descripci??n</th>
                                <th class="c">Coste</th>
                                <th class="c">Aforo</th>
                                <th class="c">Asientos Fijos</th>
                                <th class="c">Fecha del evento</th>
                            </thead>
                            <% for (Evento e: historial) { %>
                            <tr>
                                <td class="c"><%=e.getTitulo()%></td>
                                <td class="c"><%=e.getDescripcion()%></td>
                                <td class="c"><%=e.getCoste()%></td>
                                <td class="c"><%=e.getAforo()%></td>
                                <% if (e.getAsientosfijos().compareTo("S") == 0) { %>
                                <td class="c"><%=e.getNumasientosporfila()%> x <%=e.getNumfilas()%> filas</td>
                                <% } else { %>
                                <td class="c">-</td>
                                <% } %>
                                <td class="c"><%=df2.format(e.getFecha())%></td>
                            </tr>
                            <% } %>
                        </table>
                    </div>
                <% } %>
        <% } %>
</body>
</html>
