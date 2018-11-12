<%-- 
    Document   : listar
    Created on : 02/10/2018, 14:52:21
    Author     : Programador
--%>

<%@page import="java.util.List"%>
<%@page import="modelo.produto.Produto"%>
<%@page import="modelo.categoria.*"%>
<%@include file="../cabecalho.jsp" %>
<div id="titulo">Cadastro de Produtos</div>
<% List<Produto> resultado = (List<Produto>) request.getAttribute("resultado"); %>
<% if (resultado != null && resultado.size() > 0) {
      List<Categoria> lista =  new CategoriaDAO().obterTodos();
        
%>
<form action="NovoProdutoServlet" method="post">
    <div>Descrição</div>
    <div><input type="text" name="descricao"/></div>
    <div>Preço</div>
    <div><input type="text" name="preco"/></div>
    <div>Categoria</div>
    <select name="idcategoria">
        <%for(Categoria item : lista){%>
             <option value=<%= item.getId() %>> <%= item.getDescricao() %> </option>
        <%}%>
    </select>
    
    <div><input type="submit" value="Cadastrar"/></div>
</form>
<table>
    <tr>
        <th>ID</th>
        <th>Descrição</th>
        <th>Preço</th>
        <th>Categoria</th>
        <th class="controles"></th>
    </tr>
    <% for (Produto item : resultado) {%>
    <tr>
        <td><%= item.getId()%></td>
        <td><%= item.getDescricao()%></td>
        <td><%= item.getPreco()%></td>
        <td><%= item.getCategoria().getDescricao()%></td>
        <td><a href="ObterProdutoServlet?id=<%= item.getId()%>">Alterar</a>&nbsp;<a href="ExcluirProdutoServlet?id=<%= item.getId()%>">Excluir</a></td>
    </tr>
    <% } %>
</table>
<% }%>
<%@include file="../rodape.jsp" %>