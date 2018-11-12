/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produto;



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
import modelo.categoria.Categoria;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe que representa os acessos aos dados de funcionários persistidos em um banco de dados relacional
 */
public class ProdutoDAO {

    /**
     * Método utilizado para recuperar todos os produtos registrados no arquivo
     *
     * @return
     */
    public List<Produto> obterTodos() {
        List<Produto> resultado = new ArrayList<Produto>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT P.id, nome, P.preco, P.idcategoria, C.descricao FROM produtos P, categorias C  WHERE P.idcategoria = C.id");
            while (resultSet.next()) {
                
                Produto produto = new Produto();
                Categoria categoria = new Categoria();
                produto.setId(Integer.parseInt(resultSet.getString("id")));
                System.out.println(produto.getId());
                produto.setDescricao(resultSet.getString("nome"));
                
                produto.setPreco(Double.parseDouble(resultSet.getString("preco")));
                categoria.setId(Integer.parseInt(resultSet.getString("idcategoria")));
                System.out.println("22222222");
                categoria.setDescricao(resultSet.getString("descricao"));
                produto.setCategoria(categoria);
                
                resultado.add(produto);
                
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Produto>();
        }
        return resultado;
    }

    /**
     * Método utilizado para obter um produto pelo ID
     *
     * @param id
     * @return
     */
    public Produto obterProduto(int id) {
        Produto produto = null;
        try {
            
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            
            PreparedStatement preparedStatement = connection.prepareCall("SELECT P.id, P.nome, P.preco, P.idcategoria, C.descricao FROM produtos P, categorias C WHERE P.id = ? and P.idcategoria = C.id");
            preparedStatement.setInt(1, id);
           
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                
                produto = new Produto();
                Categoria categoria = new Categoria();
                produto.setId(id);
                produto.setDescricao(resultSet.getString("nome"));
                produto.setPreco(Double.parseDouble(resultSet.getString("preco")));
                categoria.setId(Integer.parseInt(resultSet.getString("idcategoria")));
                categoria.setDescricao(resultSet.getString("descricao"));
                produto.setCategoria(categoria);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return produto;
    }

    /**
     * Método utilizado para inserir um novo produto
     *
     * @param descricao
     * @param preco
     * @param categoria
     * @return
     */
    public boolean inserir(String descricao, Double preco, Categoria categoria) {
        boolean resultado = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produtos (nome, preco, idcategoria) VALUES (?, ?, ?)");
            preparedStatement.setString(1, descricao);
            preparedStatement.setDouble(2, preco);
            preparedStatement.setInt(3, categoria.getId());
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método utilizado para alterar um produto já existente
     *
     * @param id
     * @param descricao
     * @param preco
     * @param categoria
     * @return
     */
    public boolean alterar(int id,String descricao, Double preco, Categoria categoria) {
        boolean resultado = false;
        try {
            int idcat = categoria.getId();
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produtos SET nome = ?, preco= ?, idcategoria = ? WHERE id = ?");
            preparedStatement.setString(1, descricao);
            preparedStatement.setDouble(2, preco);
            preparedStatement.setInt(3, idcat);
            preparedStatement.setInt(4, id);
            resultado = (preparedStatement.executeUpdate() > 0);
            
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("BBBBBBBBBBBBBBBBB");
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUTOS WHERE id = ?");
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
