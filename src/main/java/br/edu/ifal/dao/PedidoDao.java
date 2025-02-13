package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.ItemPedido;
import br.edu.ifal.domain.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {

    public void save(Pedido pedido) {
        String sqlPedido = "INSERT INTO PEDIDO (CPF_CLIENTE_FK, CPF_FUNCIONARIO_FK, VALOR_TOTAL) VALUES (?,?,?);";
        String sqlItemPedido = "INSERT INTO ITEM_PEDIDO (ID_PEDIDO_FK, ID_PRODUTO_FK, QUANTIDADE, VALOR) VALUES (?,?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pstPedido = connection.prepareStatement(sqlPedido, PreparedStatement.RETURN_GENERATED_KEYS);

            pstPedido.setString(1, pedido.getCpfCliente());
            pstPedido.setString(2, pedido.getCpfFuncionario());
            pstPedido.setDouble(3, pedido.getValorTotal());
            pstPedido.executeUpdate();

            ResultSet rs = pstPedido.getGeneratedKeys();
            int idPedido = -1;
            if (rs.next()) {
                idPedido = rs.getInt(1);
            }

            PreparedStatement pstItemPedido = connection.prepareStatement(sqlItemPedido);
            for (ItemPedido item : pedido.getItens()) {
                pstItemPedido.setInt(1, idPedido);
                pstItemPedido.setInt(2, item.getIdProduto());
                pstItemPedido.setInt(3, item.getQuantidade());
                pstItemPedido.setDouble(4, item.getValor());
                pstItemPedido.execute();
            }

            pstPedido.close();
            pstItemPedido.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pedido> findAll() {
        String sql = "SELECT * FROM PEDIDO;";
        List<Pedido> pedidos = new ArrayList<>();

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pedidos.add(new Pedido(
                        rs.getInt("ID"),
                        rs.getString("CPF_CLIENTE_FK"),
                        rs.getString("CPF_FUNCIONARIO_FK"),
                        rs.getDouble("VALOR_TOTAL"),
                        new ArrayList<>()
                ));
            }

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidos;
    }
}
