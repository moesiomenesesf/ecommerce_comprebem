<%-- 
    Document   : alterar
    Created on : 01/10/2018, 20:54:37
    Author     : Programador
--%>

<%@page import="modelo.categoria.Categoria"%>
<%@include file="../cabecalho.jsp" %>
<div id="titulo">Cadastro de Categorias</div>
<% Categoria categoria = (Categoria) request.getAttribute("categoria"); %>
<% if (categoria != null) {%>
<form action="AlterarCategoriaServlet" method="post">
    <div class="rotulo">Id:</div>
    <div class="campo"><input type="text" name="id" value="<%= categoria.getId()%>"  readonly="readonly"/></div>
    <div class="rotulo">Descricao:</div>
    <div class="campo"><input type="text" name="descricao" value="<%= categoria.getDescricao()%>" /></div>
    <div class="controles"><input type="submit" value="Salvar" /></div>
</form>
<% }%>
<%@include file="../rodape.jsp" %>