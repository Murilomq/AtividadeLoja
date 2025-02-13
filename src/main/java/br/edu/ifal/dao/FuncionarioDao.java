package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class FuncionarioDao {

    public void save(Funcionario funcionario) {
        String sql = "INSERT INTO FUNCIONARIO VALUES (?,?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, funcionario.getCpf());
            pst.setString(2, funcionario.getNome());
            pst.setString(3, funcionario.getEndereco());
            pst.setString(4, funcionario.getTelefone());

            pst.execute();

            pst.close();
            connection.close();
        } catch (ClassNotFoundException  e) {
            throw new RuntimeException(e);
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }
}

