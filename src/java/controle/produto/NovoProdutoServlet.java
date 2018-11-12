/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.produto;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.categoria.Categoria;
import modelo.categoria.CategoriaDAO;
import modelo.produto.ProdutoNegocio;

/**
 *
 * @author moesio.meneses
 */
public class NovoProdutoServlet extends HttpServlet {

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
        String descricao = request.getParameter("descricao");
        Double preco = Double.parseDouble(request.getParameter("preco"));
        int idcategoria = Integer.parseInt(request.getParameter("idcategoria"));
        CategoriaDAO cat = new CategoriaDAO();
        Categoria categoria = cat.obterCategoria(idcategoria);
        // processamento
        ProdutoNegocio produtoNegocio = new ProdutoNegocio();
        boolean sucessoInserir = produtoNegocio.inserir(descricao, preco, categoria);
        // saída
        if (sucessoInserir) {
            request.setAttribute("mensagem", "Produto inserido com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível inserir este produto");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ListarProdutoServlet");
        rd.forward(request, response);
    }

}
