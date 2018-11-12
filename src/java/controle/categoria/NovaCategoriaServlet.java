
package controle.categoria;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.categoria.CategoriaNegocio;


/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe que representa a ação de cadastrar um novo usuário
 */
public class NovaCategoriaServlet extends HttpServlet {

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
        // processamento
        CategoriaNegocio categoriaNegocio = new CategoriaNegocio();
        boolean sucessoInserir = categoriaNegocio.inserir(descricao);
        // saída
        if (sucessoInserir) {
            request.setAttribute("mensagem", "Categoria inserida com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível inserir esta categoria");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ListarCategoriaServlet");
        rd.forward(request, response);
    }

}