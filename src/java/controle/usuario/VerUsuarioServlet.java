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
import modelo.funcionario.Funcionario;
import modelo.funcionario.FuncionarioNegocio;
import modelo.usuario.Usuario;
import modelo.usuario.UsuarioNegocio;

/**
 *
 * @author Leonardo Oliveira Moreira
 */
public class VerUsuarioServlet extends HttpServlet {

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
        boolean isNumber;
        isNumber = true;
        HttpSession session = request.getSession(); // recupera a sessão do usuário
        String login = (String) session.getAttribute("login"); // recupera o atributo de login armazenado na sessão do usuário, caso não existe é retornado nulo
        for(int i=0; i<login.length(); i++){
            Character character = login.charAt(0);
            if(!Character.isDigit(character)){
                isNumber = false;
            }
        }
        if(isNumber){
            FuncionarioNegocio funcionarioNegocio = new FuncionarioNegocio();
            Funcionario funcionario = funcionarioNegocio.obterFuncionario(Integer.parseInt(login)); // recupera o objeto usuário por meio do login armazenado na sessão
            if (funcionario != null) { // caso exista o usuário com o login armazenado na sessão
                request.setAttribute("usuario", funcionario);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/verUsuario.jsp");
                rd.forward(request, response);
            } else { // caso o login não exista na sessão ou não é um login válido no sistema
                request.setAttribute("mensagem", "Você não possui um login válido");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        }else{
            UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
            Usuario usuario = usuarioNegocio.obterUsuario(login); // recupera o objeto usuário por meio do login armazenado na sessão
            if (usuario != null) { // caso exista o usuário com o login armazenado na sessão
                request.setAttribute("usuario", usuario);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/verUsuario.jsp");
                rd.forward(request, response);
            } else { // caso o login não exista na sessão ou não é um login válido no sistema
                request.setAttribute("mensagem", "Você não possui um login válido");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        }
    }

}
