/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.categoria;

import modelo.categoria.*;
import java.util.List;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de negócio que encapsula as regras de negócio das categorias
 */
public class CategoriaNegocio {

    

    /**
     * Método utilizado para inserir um nova categoria
     *
     * @param id
     * @param descricao
     * @return
     */
    public boolean inserir(String descricao){
        CategoriaDAO dao = new CategoriaDAO();
        return dao.inserir(descricao);
    }

    public boolean alterar(int id, String descricao) {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.alterar(id,descricao);
    }

    public boolean excluir(int id) {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.excluir(id);
    }
    
    /**
     * Método utilizado para recuperar uma categoria por meio de um ID
     *
     * @param id
     * @return
     */
    public Categoria obterCategoria(int id) {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.obterCategoria(id);
    }

    public List<Categoria> obterTodos() {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.obterTodos();
    }

}
