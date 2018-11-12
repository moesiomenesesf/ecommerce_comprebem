<%-- 
    Document   : verUsuario
    Created on : 19/09/2018, 14:50:53
    Author     : leoomoreira
--%>
<%@include file="cabecalho.jsp" %>
<div id="titulo">Seus Dados</div>
<jsp:useBean id="usuario" class="modelo.funcionario.Funcionario" scope="request" />
<div class="rotulo">Nome:</div>
<div class="valor"><jsp:getProperty name="usuario" property="nome" /></div>
<div class="rotulo">ID:</div>
<div class="valor"><jsp:getProperty name="usuario" property="id" /></div>
<div class="rotulo">Senha:</div>
<div class="valor"><jsp:getProperty name="usuario" property="senha" /></div>
<%@include file="rodape.jsp" %>
