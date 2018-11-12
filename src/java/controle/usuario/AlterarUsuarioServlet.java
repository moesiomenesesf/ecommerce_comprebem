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
import modelo.usuario.UsuarioNegocio;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que representa a ação de alterar dados de um usuário existente
 */
public class AlterarUsuarioServlet extends HttpServlet {

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
        // entrada
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        // processamento
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        boolean sucessoAlterar = usuarioNegocio.alterar(nome, login, senha);
        // saída
        if (sucessoAlterar) {
            request.setAttribute("mensagem", "Usuário alterado com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível alterar este usuário");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ListarUsuarioServlet");
        rd.forward(request, response);
    }

}
