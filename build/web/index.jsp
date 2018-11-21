
<%@include file="cabecalho.jsp" %>
       
        
        
        
        
        <%
            String mensagem = (String) request.getAttribute("mensagem");
            if (mensagem != null) {
        %>
         
        
        <div id="mensagem"><b><%= mensagem%></b></div>
        <%
            }
        %>
        
        <div id="carrinho">
            <h1>Produtos da Loja</h1>
               <div>
               <%
                   List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
                   if(produtos != null){
                   for (Produto p : produtos) {
               %>
               <div>
                   <h3><%= p.getDescricao()%></h3>
                   <h5>Preço <%= p.getPreco()%></h5>
                   <form action="AdicionarCarrinhoComprasServlet">
                       <input type="hidden" name="produtoId" value="<%= p.getId()%>" />
                       Unidade(s): <input type="number" name="quantidade" step="1" value="1" />
                       <input type="submit" value="Adicionar" />
                   </form>
               </div>
               <%
                   }}
               %>
           </div>
           <%
               List<CarrinhoComprasItem> carrinhoCompras = (List<CarrinhoComprasItem>) request.getAttribute("carrinhoCompras");
               if (carrinhoCompras != null && carrinhoCompras.size() > 0) {
           %>
           <br/>
           <hr/>
           <h1>Seu Carrinho de Compras</h1>
           <%
                   double total = 0;
                   for (CarrinhoComprasItem cci : carrinhoCompras) {
           %>
           <div>
               <h3><%= cci.getProduto().getDescricao()%></h3>
               <h5>Preço <%= cci.getProduto().getPreco()%></h5>
               <form action="AdicionarCarrinhoComprasServlet">
                   <input type="hidden" name="produtoId" value="<%= cci.getProduto().getId()%>" />
                   Unidade(s): <input type="number" name="quantidade" step="1" value="<%= cci.getQuantidade()%>" />
                   <input type="submit" value="Adicionar ou Atualizar" />
                   <span>Subtotal: <%= (cci.getProduto().getPreco() * cci.getQuantidade()) %></span>
               </form>
               <a href="RemoverCarrinhoComprasServlet?produtoId=<%= cci.getProduto().getId()%>">Remover</a>
           </div>
           <%
                       total += (cci.getQuantidade() * cci.getProduto().getPreco());
                   }
           %>
           <h2>Total: <%= String.valueOf(total) %></h2>
           <%
               }
           %>
           <% if (session.getAttribute("login") != null) { %>
           <form action="ComprasServlet">
            <input type="submit" value="Comprar"/>
           </form>
           <% } else { %>
           <div>Você precisa se identificar para realizar a compra</div>
           <% } %>
        </div>
    </body>
</html>
