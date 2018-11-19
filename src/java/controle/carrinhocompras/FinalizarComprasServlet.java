/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.carrinhocompras;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.carrinhocompras.CarrinhoComprasDAO;
import modelo.carrinhocompras.CarrinhoComprasItem;
import modelo.carrinhocompras.CarrinhoComprasNegocio;

/**
 *
 * @author aluno
 */
public class FinalizarComprasServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String usuarioLogin = (String) session.getAttribute("login");

        Cookie cookie = null;
        String cookieValor = "";
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            Cookie c = cookies[i];
            if (c.getName().equals("pw1.cc")) {
                cookie = c;
                cookieValor = c.getValue();
                break;
            }
        }
        List<CarrinhoComprasItem> items = CarrinhoComprasNegocio.obterCarrinhoCompras(cookieValor);

        CarrinhoComprasDAO compras = new CarrinhoComprasDAO();
        int vendaId = compras.obterId();
        boolean sucesso = compras.inserirVenda(vendaId, usuarioLogin);
        for (int i = 0; i < items.size(); i++) {
            CarrinhoComprasItem item = items.get(i);
            compras.inserirVendaProduto(vendaId, item.getProduto().getId(), item.getQuantidade());
        }

        if (sucesso) {
            cookie.setValue("");
            response.addCookie(cookie);
            request.setAttribute("mensagem", "Sua compra foi efetuada com sucesso");
            request.getRequestDispatcher("InicioServlet").forward(request, response);
        } else {
            request.setAttribute("mensagem", "Sua compra não foi efetuada com sucesso");
            request.getRequestDispatcher("InicioServlet").forward(request, response);
        }
    }

}
