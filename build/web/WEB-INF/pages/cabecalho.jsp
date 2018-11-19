<%-- 
    Document   : cabecalho
    Created on : 23/09/2018, 10:02:29
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String login = (String) session.getAttribute("login");
    if (login == null) {
        request.setAttribute("mensagem", "Você não possui um login válido");
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SMD e-commerce</title>
        <link href="css/principal.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div id="logo">
            <center>
                <div id="logo-conteudo">SMD e-commerce</div>
            </center>
        </div>
        <div id="menu">
            <center>
                <div id="menu-conteudo" class="div-table">
                    <div class="div-table-row">
                        <div class="div-table-col"><a href="VerUsuarioServlet">Meus Dados</a></div>
                        <div class="div-table-col"><a href="ListarUsuarioServlet">Cadastro de Usuários</a></div>
                        <div class="div-table-col"><a href="ListarCategoriaServlet">Cadastro de Categorias</a></div>
                        <div class="div-table-col"><a href="ListarProdutoServlet">Cadastro de Produtos</a></div>
                        <div class="div-table-col"><a href="ListarFuncionarioServlet">Cadastro de Funcionários</a></div>
                        <div class="div-table-col"><a href="ListarVendasServlet">Vendas</a></div>
                        <div class="div-table-col"><a href="LogoutServlet">Sair</a></div>
                    </div>
                </div>
            </center>
        </div>
    <center>    
        <%
            String mensagem = (String) request.getAttribute("mensagem");
            if (mensagem != null) {
        %>

        <div id="mensagem"><b><%= mensagem%></b></div>
                <%
                    }
                %>
        <div id="conteudo">
