<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.carrinhocompras.CarrinhoComprasItem"%>
<%@page import="java.util.List"%>
<%@page import="modelo.produto.Produto"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Compre Bem</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/index.css" rel="stylesheet" type="text/css" />
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
    </head>
    <body class="container  ">
        <header>
            <div class="row">
                <div class="col-6">
                    <img src ="img/logo.png"/>
                </div>
                <div class="col-6">
                    <ul class="menu">
                    <% String login = (String) session.getAttribute("login"); 
                        if(login==null){%>
                    
                    <li class="loginSair">
                            <div>Identificação do Usuário</div>
                            <form action="LoginServlet" method="post">
                                <div>Login ou ID:</div>
                                <div><input type="text" name="login" /></div>
                                <div>Senha:</div>
                                <div><input type="password" name="senha" /></div>
                                <div><input type="submit" value="Enviar" /></div>
                            </form>
                            <div><a href="novoUsuario.jsp">Criar novo usuário</a></div>
                    </li>
                    <% }else{ %>
                      <a href="ListarComprasServlet"><li>Compras</li></a>
                      <a href="LogoutServlet"> <li class="loginSair">Sair</li> </a>
                    <% } %>
                    </ul>
                </div>
                
            
        </header>