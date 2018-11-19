/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.usuario;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.funcionario.FuncionarioNegocio;
import modelo.usuario.UsuarioNegocio;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que representa a ação de validar um login de usuário
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id;
        // entrada
        String login = request.getParameter("login");
        boolean isNumber;
        isNumber = true;
        String senha = request.getParameter("senha");
        for(int i=0; i<login.length(); i++){
            Character character = login.charAt(0);
            if(!Character.isDigit(character)){
                isNumber = false;
            }
        }
        if(isNumber){
            id = Integer.parseInt(login);
            FuncionarioNegocio funcionarioNegocio = new FuncionarioNegocio();
            boolean sucessoLoginFun = funcionarioNegocio.efetuarLogin(id, senha);
            if(sucessoLoginFun){
                HttpSession session = request.getSession(true); // cria e referencia a sessão do usuário
                session.setAttribute("login", Integer.toString(id)); // coloca o atributo login na sessão do usuário
                
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/principal.jsp"); // despacha a requisição para a página main.jsp, encaminhando as instância de request e response
                rd.forward(request, response);
            }else {
                request.setAttribute("mensagem", "Login ou senha incorreta"); // coloca uma mensagem no objeto request
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); // despacha a requisição para a página index.jsp, encaminhando as instância de request e response
                rd.forward(request, response);
            }
        }else{
            // processamento
            UsuarioNegocio usuarioNegocio = new UsuarioNegocio(); // utiliza a classe de negócio para verificar se o login e senha estão corretos
            boolean sucessoLogin = usuarioNegocio.efetuarLogin(login, senha);
            if (sucessoLogin) { // caso o login e senha estejam corretos
                HttpSession session = request.getSession(true); // cria e referencia a sessão do usuário
                session.setAttribute("login", login); // coloca o atributo login na sessão do usuário
                request.setAttribute("mensagem", "Seja bem vindo!");
                RequestDispatcher rd = request.getRequestDispatcher("InicioServlet"); // despacha a requisição para a página main.jsp, encaminhando as instância de request e response
                rd.forward(request, response);
            }else {
                request.setAttribute("mensagem", "Login ou senha incorreta"); // coloca uma mensagem no objeto request
                RequestDispatcher rd = request.getRequestDispatcher("InicioServlet"); // despacha a requisição para a página index.jsp, encaminhando as instância de request e response
                rd.forward(request, response);
            }
        }
        }

}
