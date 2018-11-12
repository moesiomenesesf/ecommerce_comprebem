<%-- 
    Document   : listar
    Created on : 23/09/2018, 10:25:15
    Author     : leoomoreira
--%>
<%@page import="java.util.List"%>
<%@page import="modelo.usuario.Usuario"%>
<%@include file="../cabecalho.jsp" %>
<div id="titulo">Cadastro de Usuários</div>
<% List<Usuario> resultado = (List<Usuario>) request.getAttribute("resultado"); %>
<% if (resultado != null && resultado.size() > 0) { %>
<form action="NovoUsuarioServlet" method="post">
    <div>Nome</div>
    <div><input type="text" name="nome"/></div>
    <div>Login</div>
    <div><input type="text" name="login"/></div>
    <div>Senha</div>
    <div><input type="password" name="senha"/></div>
    <div><input type="submit" value="Cadastrar"/></div>
</form>
<table>
    <tr>
        <th>Login</th>
        <th>Nome</th>
        <th class="controles"></th>
    </tr>
    <% for (Usuario item : resultado) {%>
    <tr>
        <td><%= item.getLogin()%></td>
        <td><%= item.getNome()%></td>
        <td><a href="ObterUsuarioServlet?login=<%= item.getLogin()%>">Alterar</a>&nbsp;<a href="ExcluirUsuarioServlet?login=<%= item.getLogin()%>">Excluir</a></td>
    </tr>
    <% } %>
</table>
<% }%>
<%@include file="../rodape.jsp" %>