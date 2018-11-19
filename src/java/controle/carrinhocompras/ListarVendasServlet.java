/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.carrinhocompras;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.carrinhocompras.CarrinhoComprasDAO;
import modelo.carrinhocompras.VendaProduto;

/**
 *
 * @author aluno
 */
public class ListarVendasServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        CarrinhoComprasDAO carrinho = new CarrinhoComprasDAO();
        List<VendaProduto> resultado = carrinho.obterTodos();
        request.setAttribute("resultado", resultado);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/vendas/listar.jsp");
        rd.forward(request, response);
       
   }
}
