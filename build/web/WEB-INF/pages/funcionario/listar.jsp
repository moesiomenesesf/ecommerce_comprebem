<%-- 
    Document   : listar
    Created on : 02/10/2018, 14:52:21
    Author     : Programador
--%>

<%@page import="java.util.List"%>
<%@page import="modelo.funcionario.Funcionario"%>
<%@include file="../cabecalho.jsp" %>
<div id="titulo">Cadastro de Funcionarios</div>
<% List<Funcionario> resultado = (List<Funcionario>) request.getAttribute("resultado"); %>
<% if (resultado != null && resultado.size() > 0) { %>
<form action="NovoFuncionarioServlet" method="post">
    <div>Nome</div>
    <div><input type="text" name="nome"/></div>
    <div>Senha</div>
    <div><input type="password" name="senha"/></div>
    <div>Salario</div>
    <div><input type="text" name="salario"/></div>
    <div><input type="submit" value="Cadastrar"/></div>
</form>
<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Salário</th>
        <th class="controles"></th>
    </tr>
    <% for (Funcionario item : resultado) {%>
    <tr>
        <td><%= item.getId()%></td>
        <td><%= item.getNome()%></td>
        <td><%= item.getSalario()%></td>
        <td><a href="ObterFuncionarioServlet?id=<%= item.getId()%>">Alterar</a>&nbsp;<a href="ExcluirFuncionarioServlet?id=<%= item.getId()%>">Excluir</a></td>
    </tr>
    <% } %>
</table>
<% }%>
<%@include file="../rodape.jsp" %>