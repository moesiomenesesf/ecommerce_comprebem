/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.funcionario;


import static config.Configuracao.JDBC_SENHA;
import static config.Configuracao.JDBC_URL;
import static config.Configuracao.JDBC_USUARIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe que representa os acessos aos dados de funcionários persistidos em um banco de dados relacional
 */
public class FuncionarioDAO {

    /**
     * Método utilizado para recuperar todos os funcionarios registrados no arquivo
     *
     * @return
     */
    public List<Funcionario> obterTodos() {
        List<Funcionario> resultado = new ArrayList<Funcionario>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, nome, salario, senha FROM funcionarios");
            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(Integer.parseInt(resultSet.getString("id")));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setSalario(Double.parseDouble(resultSet.getString("salario")));
                funcionario.setSenha(resultSet.getString("senha"));
                resultado.add(funcionario);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Funcionario>();
        }
        return resultado;
    }

    /**
     * Método utilizado para obter um usuário pelo login
     *
     * @param id
     * @return
     */
    public Funcionario obterFuncionario(int id) {
        Funcionario funcionario = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT nome, salario , senha FROM funcionarios WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                funcionario = new Funcionario();
                funcionario.setId(id);
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setSalario(Double.parseDouble(resultSet.getString("salario")));
                funcionario.setSenha(resultSet.getString("senha"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return funcionario;
    }

    /**
     * Método utilizado para inserir um novo usuário
     *
     * @param nome
     * @param salario
     * @param senha
     * @return
     */
    public boolean inserir(String nome, Double salario, String senha) {
        boolean resultado = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO FUNCIONARIOS (nome, salario, senha) VALUES (?, ?, ?)");
            preparedStatement.setString(1, nome);
            preparedStatement.setDouble(2, salario);
            preparedStatement.setString(3, senha);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método utilizado para alterar um usuário já existente
     *
     * @param id
     * @param nome
     * @param salario
     * @param senha
     * @return
     */
    public boolean alterar(int id,String nome, Double salario, String senha) {
        boolean resultado = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE funcionarios SET nome = ?, senha = ?, salario = ? WHERE id = ?");
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, senha);
            preparedStatement.setDouble(3, salario);
            preparedStatement.setInt(4, id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método para excluir um usuário já existente
     *
     * @param id
     * @return
     */
    public boolean excluir(int id) {
        boolean resultado = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM FUNCIONARIOS WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

}
