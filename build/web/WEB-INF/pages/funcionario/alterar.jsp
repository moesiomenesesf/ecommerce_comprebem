<%-- 
    Document   : alterar
    Created on : 02/10/2018, 15:32:46
    Author     : Programador
--%>

<%@page import="modelo.funcionario.Funcionario"%>
<%@include file="../cabecalho.jsp" %>
<div id="titulo">Cadastro de Funcionarios</div>
<% Funcionario funcionario = (Funcionario) request.getAttribute("funcionario"); %>
<% if (funcionario != null) {%>
<form action="AlterarFuncionarioServlet" method="post">
    <div class="rotulo">Id:</div>
    <div class="campo"><input type="text" name="id" value="<%= funcionario.getId()%>"  readonly="readonly"/></div>
    <div class="rotulo">Nome:</div>
    <div class="campo"><input type="text" name="nome" value="<%= funcionario.getNome()%>" /></div>
    <div class="rotulo">Senha:</div>
    <div class="campo"><input type="password" name="senha" value="<%= funcionario.getSenha()%>" /></div>
    <div class="rotulo">Salário:</div>
    <div class="campo"><input type="text" name="salario" value="<%= funcionario.getSalario()%>" /></div>
    <div class="controles"><input type="submit" value="Salvar" /></div>
</form>
<% }%>
<%@include file="../rodape.jsp" %>
