/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produto;



import java.util.List;
import modelo.categoria.Categoria;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de negócio que encapsula as regras de negócio dos usuários
 */
public class ProdutoNegocio {


    /**
     * Método utilizado para inserir um novo usuário
     *
     * @param descricao
     * @param preco
     * @param categoria
         * @return
     */
    public boolean inserir(String descricao,  Double preco, Categoria categoria) {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.inserir(descricao, preco, categoria);
    }

    public boolean alterar(int id,String descricao, Double preco, Categoria categoria) {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.alterar(id,descricao, preco, categoria);
    }

    public boolean excluir(int id) {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.excluir(id);
    }
    
    /**
     * Método utilizado para recuperar um usuário por meio de um login
     *
     * @param id
     * @return
     */
    public Produto obterProduto(int id) {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.obterProduto(id);
    }

    public List<Produto> obterTodos() {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.obterTodos();
    }

}
