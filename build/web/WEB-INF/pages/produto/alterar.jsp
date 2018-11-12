<%-- 
    Document   : alterar
    Created on : 02/10/2018, 15:32:46
    Author     : Programador
--%>

<%@page import="modelo.categoria.CategoriaDAO"%>
<%@page import="modelo.categoria.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="modelo.produto.Produto"%>
<%@include file="../cabecalho.jsp" %>
<div id="titulo">Cadastro de Produtos</div>
<% Produto produto = (Produto) request.getAttribute("produto"); 
   System.out.println(produto);
%>

<% if (produto != null) {
    List<Categoria> lista =  new CategoriaDAO().obterTodos();
%>
<form action="AlterarProdutoServlet" method="post">
    <div class="rotulo">Id:</div>
    <div class="campo"><input type="text" name="id" value="<%= produto.getId()%>"  readonly="readonly"/></div>
    <div class="rotulo">Nome:</div>
    <div class="campo"><input type="text" name="descricao" value="<%= produto.getDescricao()%>" /></div>
    <div class="rotulo">Preço:</div>
    <div class="campo"><input type="text" name="preco" value="<%= produto.getPreco()%>" /></div>
    <div class="rotulo">Categoria:</div>
    <select name="idcategoria">
        <%for(Categoria item : lista){%>
             <option value="<%= item.getId() %>"<%= produto.getCategoria().getId() == item.getId() ? " selected" : "" %>> <%= item.getDescricao() %> </option>
        <%}%>
    </select>
   
    <div class="controles"><input type="submit" value="Salvar" /></div>
</form>
<% }%>
<%@include file="../rodape.jsp" %>
