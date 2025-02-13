package br.edu.ifal.domain;

import java.util.List;

public class Pedido {
    private int id;
    private String cpfCliente;
    private String cpfFuncionario;
    private double valorTotal;
    private List<ItemPedido> itens;

    public Pedido(int id, String cpfCliente, String cpfFuncionario, double valorTotal, List<ItemPedido> itens) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.cpfFuncionario = cpfFuncionario;
        this.valorTotal = valorTotal;
        this.itens = itens;
    }

    public int getId() {
        return id;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "ID=" + id +
                ", CPF Cliente='" + cpfCliente + '\'' +
                ", CPF Funcionário='" + cpfFuncionario + '\'' +
                ", Valor Total=" + valorTotal +
                '}';
    }
}
