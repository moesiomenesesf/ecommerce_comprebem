/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.carrinhocompras.CarrinhoComprasDAO;
import modelo.carrinhocompras.VendaProduto;

/**
 *
 * @author Programador
 */
public class ListarComprasServlet extends HttpServlet {

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
       
        HttpSession session = request.getSession();
        String usuarioLogin = (String) session.getAttribute("login");
        CarrinhoComprasDAO carrinho = new CarrinhoComprasDAO();
        List<VendaProduto> resultado = carrinho.comprasUsuario(usuarioLogin);
        request.setAttribute("resultado", resultado);
        RequestDispatcher rd = request.getRequestDispatcher("compras.jsp");
        rd.forward(request, response);
       
   }

}
