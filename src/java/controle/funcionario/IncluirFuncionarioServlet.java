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
import modelo.funcionario.FuncionarioNegocio;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe que representa a ação de cadastrar uma nova categoria
 */
public class IncluirFuncionarioServlet extends HttpServlet {

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
        String senha = request.getParameter("senha");
        Double salario = Double.parseDouble(request.getParameter("salario"));
        // processamento
        FuncionarioNegocio funcionarioNegocio = new FuncionarioNegocio();
        boolean sucessoInserir = funcionarioNegocio.inserir(nome,senha,salario);
        // saída
        if (sucessoInserir) {
            request.setAttribute("mensagem", "Funcionario inserido com sucesso");
            RequestDispatcher rd = request.getRequestDispatcher("verFuncionario.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("mensagem", "Não foi possível inserir este Funcionario");
            RequestDispatcher rd = request.getRequestDispatcher("novoFuncionario.jsp");
            rd.forward(request, response);
        }
    }

}
