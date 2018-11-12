/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.funcionario;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.funcionario.Funcionario;
import modelo.funcionario.FuncionarioNegocio;

/**
 *
 * @author moesio.meneses
 */
public class ObterFuncionarioServlet extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        FuncionarioNegocio funcionarioNegocio = new FuncionarioNegocio();
        Funcionario funcionario = funcionarioNegocio.obterFuncionario(id);
        request.setAttribute("funcionario", funcionario);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/funcionario/alterar.jsp");
        rd.forward(request, response);
    }


}
