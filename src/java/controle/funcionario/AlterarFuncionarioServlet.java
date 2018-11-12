/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.funcionario;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.funcionario.FuncionarioNegocio;

/**
 *
 * @author moesio.meneses
 */
public class AlterarFuncionarioServlet extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        Double salario = Double.parseDouble(request.getParameter("salario"));
        // processamento
        FuncionarioNegocio funcionarioNegocio = new FuncionarioNegocio();
        boolean sucessoAlterar = funcionarioNegocio.alterar(id, nome, senha, salario);
        // saída
        if (sucessoAlterar) {
            request.setAttribute("mensagem", "Funcionário alterado com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível alterar este funcionário");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ListarFuncionarioServlet");
        rd.forward(request, response);
    }

}
