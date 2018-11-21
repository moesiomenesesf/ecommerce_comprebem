/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.carrinhocompras;

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
 * @author Programador
 */
public class CarrinhoComprasDAO {

    public int obterId() {
        int id = 0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT nextval('venda_id_seq') AS id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return id;
        }

        return id;
    }

    public boolean inserirVenda(int id, String login) {
        boolean resultado = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO vendas (id, usuario_login) VALUES (?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, login);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    public boolean inserirVendaProduto(int idVenda, int idProduto, int qtd) {
        boolean resultado = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO venda_produto (venda_id, produto_id, quantidade) VALUES (?,?,?)");
            preparedStatement.setInt(1, idVenda);
            preparedStatement.setInt(2, idProduto);
            preparedStatement.setInt(3, qtd);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return resultado;
    }

    public List<VendaProduto> obterTodos() {
        List<VendaProduto> resultado = new ArrayList<VendaProduto>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT venda_id, produto_id, quantidade FROM venda_produto");
            while (resultSet.next()) {
                VendaProduto vendaProduto = new VendaProduto();
                vendaProduto.setIdVenda(Integer.parseInt(resultSet.getString("venda_id")));
                vendaProduto.setIdProduto(Integer.parseInt(resultSet.getString("produto_id")));
                vendaProduto.setQtd(Integer.parseInt(resultSet.getString("quantidade")));
                resultado.add(vendaProduto);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<VendaProduto>();
        }
        return resultado;
    }
    public List<Venda> obterTodasVendas() {
        List<Venda> resultado = new ArrayList<Venda>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, usuario_login FROM vendas");
            while (resultSet.next()) {
                Venda venda = new Venda();
                venda.setId(Integer.parseInt(resultSet.getString("id")));
                venda.setLogin(resultSet.getString("usuario_login"));
                
                
                
                venda.setProdutos(obterVendaProduto(venda.getId()));
                resultado.add(venda);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Venda>();
        }
        return resultado;
    }
    
    public Venda obterVenda(int id) {
        Venda venda = new Venda();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT id, usuario_login FROM vendas WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                venda = new Venda();
                venda.setId(resultSet.getInt("id"));
                venda.setLogin(resultSet.getString("usuario_login"));
                venda.setProdutos(obterVendaProduto(id));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return venda;
    }
       public List<VendaProduto> comprasUsuario(String login) {
        List<VendaProduto> resultado = new ArrayList<VendaProduto>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT venda_id, produto_id, quantidade FROM venda_produto, vendas WHERE venda_id = id and usuario_login = ?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                VendaProduto vendaProduto = new VendaProduto();
                vendaProduto.setIdVenda(resultSet.getInt("venda_id"));
                vendaProduto.setIdProduto(resultSet.getInt("produto_id"));
                vendaProduto.setQtd(resultSet.getInt("quantidade"));
                resultado.add(vendaProduto);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<VendaProduto>();
        }
        return resultado;
    }
    
    public List<VendaProduto> obterVendaProduto(int idVenda) {
        List<VendaProduto> resultado = new ArrayList<VendaProduto>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT venda_id, produto_id, quantidade FROM venda_produto WHERE venda_id = ?");
            preparedStatement.setInt(1, idVenda);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                VendaProduto vendaProduto = new VendaProduto();
                vendaProduto.setIdVenda(resultSet.getInt("venda_id"));
                vendaProduto.setIdProduto(resultSet.getInt("produto_id"));
                vendaProduto.setQtd(resultSet.getInt("quantidade"));
                resultado.add(vendaProduto);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<VendaProduto>();
        }
        return resultado;
    }

    public boolean excluir(int id) {
        boolean resultado = false;
        System.out.println("\nAAAAAAAAAAAAAAAAA");
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM vendas WHERE id = ?");
            preparedStatement.setInt(1, id);
            System.out.println(id+"\nAAAAAAAAAAAAAAAAA");
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();

        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }
}
