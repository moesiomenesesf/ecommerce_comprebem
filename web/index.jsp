<%-- 
    Document   : index
    Created on : 12/09/2018, 15:28:30
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.carrinhocompras.CarrinhoComprasItem"%>
<%@page import="java.util.List"%>
<%@page import="modelo.produto.Produto"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SMD e-commerce</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/index.css" rel="stylesheet" type="text/css" />
    </head>
    <body class="container">
        <header>
            <img src ="img/logo.png"/>
            <ul class="menu">
                <a href="#carrinho"><li>Carrinho</li></a>
                <% if(true){%>
                  <li class="loginSair">Login</li>
                <% }else{ %>
                  <li class="loginSair">Sair</li>
                <% } %>
            </ul>
            
        </header>
        <%  String login = (String) session.getAttribute("login");
            if (login == null) {
                
        %>
        <div>Identificação do Usuário</div>
        <form action="LoginServlet" method="post">
            <div>Login ou ID:</div>
            <div><input type="text" name="login" /></div>
            <div>Senha:</div>
            <div><input type="password" name="senha" /></div>
            <div><input type="submit" value="Enviar" /></div>
        </form>
        <div><a href="novoUsuario.jsp">Criar novo usuário</a></div>
        <% }else{ %>
        <a href="LogoutServlet"><p> sair </p></a>
        <%}%>
        <div class="poster">
            <ul>
                <li> 
                    <ul>
                        <li> Materiais Escolares</li>
                        <li> Tecnologias</li>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>
                </li>
                <li>
                    Imagem
                </li>
            </ul>
        </div>
        
        
        
        
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
           <form action="ComprarServlet">
            <input type="submit" value="Comprar"/>
           </form>
        </div>
    </body>
</html>
