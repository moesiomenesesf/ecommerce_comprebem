/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.carrinhocompras;

import java.util.ArrayList;
import java.util.List;
import modelo.produto.ProdutoNegocio;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que representa as ações de manipulação de carrinho de compras
 */
public final class CarrinhoComprasNegocio {

    private static final String SEPARADOR_REGISTRO = "SEPREGISTER";
    private static final String SEPARADOR_CAMPOS = "SEPFIELD";

    private CarrinhoComprasNegocio() {

    }

    /**
     * Método utilizado para adicionar um novo produto ou atualizar o existente,
     * retornando a String que representa o cookie atualizada
     *
     * @param cookieValor
     * @param produtoId
     * @param quantidade
     * @return
     */
    public static String salvarProduto(String cookieValor, int produtoId, int quantidade) {
        if (cookieValor == null || cookieValor.trim().length() == 0) {
            return produtoId + SEPARADOR_CAMPOS + quantidade;
        }
        if (existeProduto(cookieValor, produtoId)) {
            if (!cookieValor.contains(SEPARADOR_REGISTRO)) { // só existe um produto
                cookieValor = produtoId + SEPARADOR_CAMPOS + quantidade;
            } else { // existe mais de um produto
                String[] produtos = cookieValor.split(SEPARADOR_REGISTRO);
                cookieValor = "";
                for (String p : produtos) {
                    String[] produto = p.split(SEPARADOR_CAMPOS);
                    if (cookieValor.trim().length() > 0) {
                        cookieValor = cookieValor + SEPARADOR_REGISTRO;
                    }
                    if (Integer.parseInt(produto[0]) == produtoId) {
                        cookieValor = cookieValor + (produtoId + SEPARADOR_CAMPOS + quantidade);
                    } else {
                        cookieValor = cookieValor + (Integer.parseInt(produto[0]) + SEPARADOR_CAMPOS + Integer.parseInt(produto[1]));
                    }
                }
            }
        } else {
            cookieValor = cookieValor + SEPARADOR_REGISTRO + (produtoId + SEPARADOR_CAMPOS + quantidade);
        }
        return cookieValor;
    }

    /**
     * Método utilizado para remover um produto existente, retornando a String
     * que representa o cookie atualizada
     *
     * @param cookieValor
     * @param produtoId
     * @return
     */
    public static String removerProduto(String cookieValor, int produtoId) {
        if (cookieValor == null || cookieValor.trim().length() == 0) {
            return "";
        }
        if (existeProduto(cookieValor, produtoId)) {
            if (!cookieValor.contains(SEPARADOR_REGISTRO)) { // só existe um produto
                cookieValor = "";
            } else { // existe mais de um produto
                String[] produtos = cookieValor.split(SEPARADOR_REGISTRO);
                cookieValor = "";
                for (String p : produtos) {
                    String[] produto = p.split(SEPARADOR_CAMPOS);
                    if (cookieValor.trim().length() > 0) {
                        cookieValor = cookieValor + SEPARADOR_REGISTRO;
                    }
                    if (Integer.parseInt(produto[0]) != produtoId) {
                        cookieValor = cookieValor + (Integer.parseInt(produto[0]) + SEPARADOR_CAMPOS + Integer.parseInt(produto[1]));
                    }
                }
            }
        }
        return cookieValor;
    }

    /**
     * Método utilizado para verificar se o produto existe
     *
     * @param cookieValor
     * @param produtoId
     * @return
     */
    public static boolean existeProduto(String cookieValor, int produtoId) {
        if (cookieValor == null || cookieValor.trim().length() == 0) {
            return false;
        }
        String[] produtos = cookieValor.split(SEPARADOR_REGISTRO);
        if (produtos == null || produtos.length == 0) {
            produtos = new String[]{cookieValor};
        }
        for (String p : produtos) {
            String[] produto = p.split(SEPARADOR_CAMPOS);
            if (Integer.parseInt(produto[0]) == produtoId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método utilizado para recuperar todos os itens do carrinho de compras
     *
     * @param cookieValor
     * @return
     */
    public static List<CarrinhoComprasItem> obterCarrinhoCompras(String cookieValor) {
        if (cookieValor == null || cookieValor.trim().length() == 0) {
            return new ArrayList<CarrinhoComprasItem>();
        }
        List<CarrinhoComprasItem> resultado = new ArrayList<CarrinhoComprasItem>();
        String[] produtos = cookieValor.split(SEPARADOR_REGISTRO);
        if (produtos == null || produtos.length == 0) {
            produtos = new String[]{cookieValor};
        }
        ProdutoNegocio produtoNegocio = new ProdutoNegocio();
        for (String p : produtos) {
            String[] produto = p.split(SEPARADOR_CAMPOS);
            CarrinhoComprasItem carrinhoComprasItem = new CarrinhoComprasItem();
            carrinhoComprasItem.setProduto(produtoNegocio.obterProduto(Integer.parseInt(produto[0])));
            carrinhoComprasItem.setQuantidade(Integer.parseInt(produto[1]));
            resultado.add(carrinhoComprasItem);
        }
        return resultado;
    }

    /**
     * Método main utilizado para testar se os métodos desta classe funcionam
     * 
     * @param args 
     */
    public static void main(String[] args) {
        List<CarrinhoComprasItem> carrinhoComprasItems = null;

        String cookie = "3SEPFIELD2";

        System.out.println("Existe: " + existeProduto(cookie, 3));

        System.out.println("---");
        carrinhoComprasItems = obterCarrinhoCompras(cookie);
        for (CarrinhoComprasItem c : carrinhoComprasItems) {
            System.out.println(c.getProduto().getId() + " - " + c.getProduto().getDescricao());
        }
        System.out.println("---");

        System.out.println("Removendo 3");
        cookie = removerProduto(cookie, 3);

        System.out.println("---");
        carrinhoComprasItems = obterCarrinhoCompras(cookie);
        for (CarrinhoComprasItem c : carrinhoComprasItems) {
            System.out.println(c.getProduto().getId() + " - " + c.getProduto().getDescricao());
        }
        System.out.println("---");
    }

}
