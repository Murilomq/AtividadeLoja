package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;

import br.edu.ifal.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    public void save(Produto produto) {
        String sql = "INSERT INTO PRODUTO VALUES (?,?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1,produto.getId());
            pst.setString(2, produto.getNome());
            pst.setDouble(3, produto.getValor_unit());
            pst.setInt(4, produto.getQuantidade());

            pst.execute();

            pst.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateQuantidade(int id, int quantidadeVendida) {
        String sql = "UPDATE PRODUTO SET QUANTIDADE = QUANTIDADE - ? WHERE ID = ?";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, quantidadeVendida);
            pst.setInt(2, id);

            pst.executeUpdate();

            pst.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Produto findById(int id) {
        String sql = "SELECT * FROM PRODUTO WHERE ID = ?;";
        Produto produto = null;

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                produto = new Produto(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getDouble("VALOR_UNIT"),
                        rs.getInt("QUANTIDADE")
                );
            }

            pst.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produto;
    }


    public List<Produto> findAll() {
        String sql = "SELECT * FROM PRODUTO";

        List<Produto> lista = new ArrayList<>();

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("NOME");
                double valor_unit = rs.getDouble("VALOR_UNIT");
                int quantidade = rs.getInt("QUANTIDADE");

                Produto produto = new Produto(id, nome, valor_unit, quantidade);
                lista.add(produto);
            }

            rs.close();
            pst.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;



    }



}
