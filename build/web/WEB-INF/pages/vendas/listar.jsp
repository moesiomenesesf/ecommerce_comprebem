<%@page import="java.util.List"%>
<%@page import="modelo.carrinhocompras.VendaProduto"%>
<%@page import="modelo.produto.ProdutoNegocio"%>
<%@include file="../cabecalho.jsp" %>
<div id="titulo">Vendas</div>
<% List<VendaProduto> resultado = (List<VendaProduto>) request.getAttribute("resultado"); %>
<% if (resultado != null && resultado.size() > 0) { %>
<table>
    <tr>
        <th>IdVenda</th>
        <th>Produto</th>
        <th>Quantidade</th>
        <th class="controles"></th>
    </tr>
    <%  ProdutoNegocio prod;
        for (VendaProduto item : resultado) {
            prod = new ProdutoNegocio();
    %>
    <tr>
        <td><%= item.getIdVenda()%></td>
        <td><%= prod.obterProduto(item.getIdProduto()).getDescricao()%></td>
        <td><%= item.getQtd() %></td>
        <!--<td><a href="ExcluirVendaServlet?=<%=// item.getLogin()%>">Excluir</a></td>         TODO        -->
    </tr>
    <% } %>
</table>
<% }%>
<%@include file="../rodape.jsp" %>