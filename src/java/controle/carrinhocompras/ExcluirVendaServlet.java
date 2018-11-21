/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.carrinhocompras;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.carrinhocompras.CarrinhoComprasDAO;

/**
 *
 * @author Programador
 */
public class ExcluirVendaServlet extends HttpServlet {

  protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        int id = Integer.parseInt(request.getParameter("id"));
        // processamento
        CarrinhoComprasDAO carrinho = new CarrinhoComprasDAO();
        boolean sucessoExcluir = carrinho.excluir(id);
        if (sucessoExcluir) {
            request.setAttribute("mensagem", "Venda excluída com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível excluir esta venda");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ListarVendasServlet");
        rd.forward(request, response);
    }
}
