/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.funcionario;


import java.util.List;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de negócio que encapsula as regras de negócio dos usuários
 */
public class FuncionarioNegocio {

    /**
     * Método que verifica se o login e senha de um usuário é válido
     *
     * @param id
     * @param senha
     * @return
     */
    public boolean efetuarLogin(int id, String senha) {
        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario funcionario = dao.obterFuncionario(id);
        return (funcionario != null && funcionario.getSenha().equals(senha));
    }

    /**
     * Método utilizado para inserir um novo usuário
     *
     * @param nome
     * @param salario
     * @param senha
     * @return
     */
    public boolean inserir(String nome, String senha,  Double salario) {
        FuncionarioDAO dao = new FuncionarioDAO();
        return dao.inserir(nome, salario, senha);
    }

    public boolean alterar(int id,String nome, String senha, Double salario) {
        FuncionarioDAO dao = new FuncionarioDAO();
        return dao.alterar(id,nome, salario, senha);
    }

    public boolean excluir(int id) {
        FuncionarioDAO dao = new FuncionarioDAO();
        return dao.excluir(id);
    }
    
    /**
     * Método utilizado para recuperar um usuário por meio de um login
     *
     * @param id
     * @return
     */
    public Funcionario obterFuncionario(int id) {
        FuncionarioDAO dao = new FuncionarioDAO();
        return dao.obterFuncionario(id);
    }

    public List<Funcionario> obterTodos() {
        FuncionarioDAO dao = new FuncionarioDAO();
        return dao.obterTodos();
    }

}
