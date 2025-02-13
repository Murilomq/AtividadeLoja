package br.edu.ifal;

import br.edu.ifal.dao.ClienteDao;

import br.edu.ifal.dao.PedidoDao;
import br.edu.ifal.dao.ProdutoDao;

import br.edu.ifal.domain.Cliente;
import br.edu.ifal.domain.ItemPedido;
import br.edu.ifal.domain.Pedido;
import br.edu.ifal.domain.Produto;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDao clienteDao = new ClienteDao();
        ProdutoDao produtoDao = new ProdutoDao();
        PedidoDao pedidoDao = new PedidoDao();

        int opcao;

        do {
            System.out.println("\n1 - Cadastrar Produto");
            System.out.println("2 - Cadastrar Cliente");
            System.out.println("3 - Buscar Produto por ID");
            System.out.println("4 - Listar Produtos Disponíveis");
            System.out.println("5 - Efetuar Venda");
            System.out.println("6 - Listar Vendas Realizadas");
            System.out.println("0 - Sair\n");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:

                    System.out.print("Nome do Produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Valor Unitário: ");
                    double valor = scanner.nextDouble();
                    System.out.print("Quantidade em Estoque: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();

                    Produto produto = new Produto(0, nome, valor, quantidade);
                    produtoDao.save(produto);

                    break;
                case 2:

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Nome do Cliente: ");
                    String nomeCliente = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    Cliente cliente = new Cliente(cpf, nomeCliente, endereco, telefone);
                    clienteDao.save(cliente);

                    break;
                case 3:

                    System.out.print("Digite o ID do Produto: ");
                    int id = scanner.nextInt();

                    produto = produtoDao.findById(id);
                    if (produto != null) {
                        System.out.println(produto.toString());
                    } else {
                        System.out.println("Produto não encontrado.");
                    }

                    break;
                case 4:

                    List<Produto> listaProduto = produtoDao.findAll();
                    for (Produto p : listaProduto) {
                        System.out.println(p);
                    }

                    break;
                case 5:

                    System.out.print("CPF do Cliente: ");
                    String cpfCliente = scanner.nextLine();
                    System.out.print("CPF do Funcionário: ");
                    String cpfFuncionario = scanner.nextLine();

                    List<ItemPedido> itens = new ArrayList<>();
                    double valorTotal = 0.0;

                    int idProduto;

                    do {

                        System.out.print("Digite o ID do produto (Digite 0 para sair): ");
                        idProduto = scanner.nextInt();
                        if (idProduto == 0) break;

                        produto = produtoDao.findById(idProduto);

                        System.out.print("Quantidade: ");
                        int quantidadeVendida = scanner.nextInt();
                        scanner.nextLine();

                        if (quantidadeVendida > produto.getQuantidade()) {
                            System.out.println("Quantidade indisponível em estoque.");
                            continue;
                        }

                        ItemPedido item = new ItemPedido(0, 0, idProduto, quantidadeVendida, produto.getValor_unit() * quantidadeVendida);
                        itens.add(item);
                        valorTotal += item.getValor();

                    } while (true);


                    if (!itens.isEmpty()) {
                        Pedido pedido = new Pedido(0, cpfCliente, cpfFuncionario, valorTotal, itens);
                        pedidoDao.save(pedido);


                        for (ItemPedido i : itens) {
                            produtoDao.updateQuantidade(i.getIdProduto(), i.getQuantidade());
                        }

                        System.out.println("Venda realizada com sucesso! Valor total: R$ " + valorTotal);
                    }

                    break;

                    case 6:

                            List<Pedido> listaPedido = pedidoDao.findAll();
                            for (Pedido p : listaPedido) {
                                System.out.println(p);
                            }

                        break;

                    case 0:
                         System.out.println("Encerrando");
                            break;
                    default:
                         System.out.println("Opção inválida.");
                    }
            }  while (opcao != 0) ;
        }
}

