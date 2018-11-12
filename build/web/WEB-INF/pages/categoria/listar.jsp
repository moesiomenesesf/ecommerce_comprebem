<%-- 
    Document   : listar
    Created on : 01/10/2018, 20:42:54
    Author     : Programador
--%>
<%@page import="java.util.List"%>
<%@page import="modelo.categoria.Categoria"%>
<%@include file="../cabecalho.jsp" %>
<div id="titulo">Cadastro de Categorias</div>
<% List<Categoria> resultado = (List<Categoria>) request.getAttribute("resultado"); %>
<% if (resultado != null && resultado.size() > 0) { %>
<form action="NovaCategoriaServlet" method="post">
    <div>Descrição</div>
    <div><input type="text" name="descricao"/></div>
    <div><input type="submit" value="Cadastrar"/></div>
</form>
<table>
    <tr>
        <th>ID</th>
        <th>Descrição</th>
        <th class="controles"></th>
    </tr>
    <% for (Categoria item : resultado) {%>
    <tr>
        <td><%= item.getId()%></td>
        <td><%= item.getDescricao()%></td>
        <td><a href="ObterCategoriaServlet?id=<%= item.getId()%>">Alterar</a>&nbsp;<a href="ExcluirCategoriaServlet?id=<%= item.getId()%>">Excluir</a></td>
    </tr>
    <% } %>
</table>
<% }%>
<%@include file="../rodape.jsp" %>