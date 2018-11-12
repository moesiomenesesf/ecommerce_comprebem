<%-- 
    Document   : novoUsuario
    Created on : 17/09/2018, 14:37:17
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SMD e-commerce</title>
    </head>
    <body>
        <h1>Novo usuário</h1>
        <form action="IncluirUsuarioServlet" method="post">
            <div>Nome:</div>
            <div><input type="text" name="nome" /></div>
            <div>Login:</div>
            <div><input type="text" name="login" /></div>
            <div>Senha:</div>
            <div><input type="password" name="senha" /></div>
            <div><input type="submit" value="Salvar" /></div>
        </form>
        <%
            String mensagem = (String) request.getAttribute("mensagem");
            if (mensagem != null) {
        %>
        <div><b><%= mensagem%></b></div>
        <%
            }
        %>
    </body>
</html>
