<%@page import="java.util.List"%>
<%@page import="modelo.carrinhocompras.VendaProduto"%>
<%@page import="modelo.produto.ProdutoNegocio"%>
<%@include file="cabecalho.jsp" %>
<h1>Suas Compras</h1>
<% List<VendaProduto> resultado = (List<VendaProduto>) request.getAttribute("resultado"); %>
<% if (resultado != null && resultado.size() > 0) { %>
<table>
    <tr>
        <th>IdCompra</th>
        <th>Produto</th>
        <th>Quantidade</th>
       
    </tr>
    <%  ProdutoNegocio prod;
        for (VendaProduto item : resultado) {
            prod = new ProdutoNegocio();
    %>
    <tr>
        <td><%= item.getIdVenda()%></td>
        <td><%= prod.obterProduto(item.getIdProduto()).getDescricao()%></td>
        <td><%= item.getQtd() %></td>
    </tr>
    <% } %>
</table>
<% }%>
</body>
</html>