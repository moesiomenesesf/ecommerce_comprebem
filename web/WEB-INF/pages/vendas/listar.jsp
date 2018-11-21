<%@page import="java.util.List"%>
<%@page import="modelo.carrinhocompras.Venda"%>
<%@page import="modelo.usuario.UsuarioNegocio"%>
<%@include file="../cabecalho.jsp" %>
<div id="titulo">Vendas</div>
<% List<Venda> resultado = (List<Venda>) request.getAttribute("resultado"); %>
<% if (resultado != null && resultado.size() > 0) { %>
<table>
    <tr>
        <th>IdVenda</th>
        <th>Usuario</th>
    </tr>
    <%
        
        for (Venda item : resultado) {
            
    %>
    <tr>
        <td><%= item.getId() %></td>
        <td><%= item.getLogin() %></td>
         <td><a href="ExcluirVendaServlet?id=<%= item.getId()%>">Excluir</a></td>
         
    </tr>
    <% } %>
</table>
<% }%>
<%@include file="../rodape.jsp" %>