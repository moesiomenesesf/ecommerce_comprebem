/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuario;

import java.util.List;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de negócio que encapsula as regras de negócio dos usuários
 */
public class UsuarioNegocio {

    /**
     * Método que verifica se o login e senha de um usuário é válido
     *
     * @param login
     * @param senha
     * @return
     */
    public boolean efetuarLogin(String login, String senha) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.obterUsuario(login);
        return (usuario != null && usuario.getSenha().equals(senha));
    }

    /**
     * Método utilizado para inserir um novo usuário
     *
     * @param nome
     * @param login
     * @param senha
     * @return
     */
    public boolean inserir(String nome, String login, String senha) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.inserir(nome, login, senha);
    }

    public boolean alterar(String nome, String login, String senha) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.alterar(nome, login, senha);
    }

    public boolean excluir(String login) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.excluir(login);
    }
    
    /**
     * Método utilizado para recuperar um usuário por meio de um login
     *
     * @param login
     * @return
     */
    public Usuario obterUsuario(String login) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.obterUsuario(login);
    }

    public List<Usuario> obterTodos() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.obterTodos();
    }

}
