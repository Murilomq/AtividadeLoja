package br.edu.ifal.domain;

public class ItemPedido {
    private int id;
    private int idPedido;
    private int idProduto;
    private int quantidade;
    private double valor;

    public ItemPedido(int id, int idPedido, int idProduto, int quantidade, double valor) {
        this.id = id;
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "ID=" + id +
                ", ID Pedido=" + idPedido +
                ", ID Produto=" + idProduto +
                ", Quantidade=" + quantidade +
                ", Valor=" + valor +
                '}';
    }
}

