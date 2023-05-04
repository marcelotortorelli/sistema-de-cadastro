import db.EstoquesDB;
import db.PedidosVendaDB;
import db.ProdutosDB;
import db.UsuariosDB;
import models.*;

import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    static ProdutosDB produtosDB = new ProdutosDB();
    static UsuariosDB usuariosDB = new UsuariosDB();
    static EstoquesDB estoquesDB = new EstoquesDB();
    static PedidosVendaDB pedidosVendaDB = new PedidosVendaDB();
    public static void main(String[] args) throws Exception {
        System.out.println("--- PEDIDO DE VENDAS ---");

        int option;

        do{
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos Cadastrados");
            System.out.println("3 - Cadastrar Administrador");
            System.out.println("4 - Cadastrar Cliente");
            System.out.println("5 - Listar Usuários Cadastrados");
            System.out.println("6 - Cadastrar Novo Estoque");
            System.out.println("7 - Listar Estoques");
            System.out.println("8 - Criar Pedido de Vendas");
            System.out.println("9 - Listar Pedido de Vendas");
            System.out.println("0 - Sair");

            Scanner scanner = new Scanner(System.in);

            System.out.println("Qual operação você deseja realizar: ");
            option = scanner.nextInt();

            process(option);
        }while(option != 0);
    }

    public static void process(int option) throws Exception{
        switch (option){
            case 1: {
                Scanner scanner = new Scanner(System.in);


                System.out.print("Qual descrição deseja dar ao novo produto:");
                String descricao = scanner.nextLine();

                System.out.print("Qual o ID que deseja dar ao novo produto:");
                int id = scanner.nextInt();

                System.out.print("Preço do produto: ");
                double preco = scanner.nextDouble();

                System.out.print("Data de Validade: ");
                String dataString = scanner.next();

                Date dataValidade = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);

                Produto novoProduto = new Produto(id, descricao, preco, dataValidade);

                produtosDB.addNovoProduto(novoProduto);


                novoProduto.setId(id);
                novoProduto.setDescricao(descricao);


                System.out.println("Produto criado com sucesso:");
                System.out.println("--- ID: " + novoProduto.getId());
                System.out.println("--- Descrição: " + novoProduto.getDescricao());
                System.out.println("--- Preço: " + novoProduto.getPreco());
                System.out.println("--- Data de Validade: " + novoProduto.getDataValidade());
                System.out.println("------------------------------------------");
                break;
            }
            case 2: {
                List<Produto> listaDeProdutos = produtosDB.getProdutosList();

                for (Produto produto : listaDeProdutos) {

                    System.out.println("--- ID: " + produto.getId());
                    System.out.println("--- Descrição: " + produto.getDescricao());
                    System.out.println("--- Preço: " + produto.getPreco());
                    System.out.println("--- Data de Validade: " + produto.getDataValidade());
                    System.out.println("------------------------------------------");

                }
                break;
            }
            case 3: {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Nome de usuário:");
                String nome = scanner.nextLine();


                Admin novoAdmin = new Admin(nome);
                usuariosDB.addNovoUsuario(novoAdmin);
                break;
            }
            case 4: {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Nome de usuário:");
                String nome = scanner.nextLine();


                Cliente novoCliente = new Cliente(nome);
                usuariosDB.addNovoUsuario(novoCliente);
                break;
            }
            case 5: {
                System.out.println("----------------------------------------------");
                System.out.println("--------LISTANDO USUARIOS CADASTRADOS---------");
                for(Usuario usuario: usuariosDB.getUsuarioList()) {
                    System.out.println("ID: " + usuario.getId());
                    System.out.println("NOME: " + usuario.getId());
                    System.out.println("TIPO DE USUÁRIO: " + usuario.getTipoUsuario());
                    System.out.println("----------------------------------------------");
                }
                break;
                }
            case 6: {
                Scanner scanner = new Scanner(System.in);
                System.out.println("----------------------------------------------");
                System.out.println("-------CADASTRANDO ESTOQUE DE PRODUTO---------");
                System.out.println("----------------------------------------------");

                System.out.println("Qual o identificador do estoque?");
                String id = scanner.next();

                System.out.println("Qual produto a ser adicionado? (informe o ID)");
                int produtoId = scanner.nextInt();

                Produto produto = produtosDB.getProdutoPorID(produtoId);
                System.out.println("PRODUTO ID: " + produto.getId());
                System.out.println("DESCRIÇÃO: " + produto.getDescricao());
                System.out.println("VALIDADE: " + produto.getDataValidade());

                System.out.println("Informe a quantidade a ser adicionada em estoque: ");
                int quantidade = scanner.nextInt();

                Estoque novoEstoque = new Estoque(id, produto, quantidade);
                estoquesDB.addNovoEstoque(novoEstoque);
                break;
            }
            case 7: {
                System.out.println("----------------------------------------------");
                System.out.println("--------LISTANDO ESTOQUES CADASTRADOS---------");
                for(Estoque estoque: estoquesDB.getEstoques()) {
                    System.out.println("ID: " + estoque.getId());
                    System.out.println("PRODUTO: " + estoque.getProduto().getDescricao());
                    System.out.println("PREÇO: " + estoque.getProduto().getPreco());
                    System.out.println("QUANTIDADE: " + estoque.getQuantidade());
                    System.out.println("----------------------------------------------");
                }
                break;

                }
            case 8: {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Informe o ID do Cliente: ");
                int idCliente = scanner.nextInt();
                Cliente cliente = (Cliente) usuariosDB.getUsuarioPorID(idCliente);
                    System.out.println("ID: " + cliente.getId());
                    System.out.println("NOME: " + cliente.getNome());
                    System.out.println("TIPO DE USUÁRIO: " + cliente.getTipoUsuario());
                    System.out.println("----------------------------------------------");

                System.out.println("Informe o ID do Estoque: ");
                String idEstoque = scanner.next();
                    Estoque estoque= estoquesDB.getEstoqueById(idEstoque);
                    System.out.println("ESTOQUE ID: " + estoque.getId());
                    System.out.println("DESCRIÇÃO DO PRODUTO : " + estoque.getProduto().getDescricao());
                    System.out.println("VALIDADE DO PRODUTO : " + estoque.getProduto().getDataValidade());
                    System.out.println("----------------------------------------------");

                System.out.println("Informe a Quantidade: ");
                int quantidade = scanner.nextInt();

                PedidoVenda novoPedido = new PedidoVenda(cliente, estoque, quantidade);
                pedidosVendaDB.addNovoPedidoVenda(novoPedido);
            }
            break;

            case 9: {
                System.out.println("----------------------------------------------");
                System.out.println("----------LISTANDO PEDIDOS DE VENDA-----------");
                for(PedidoVenda pedidoVenda: pedidosVendaDB.getPedidoVendas()) {
                    System.out.println("ID: " + pedidoVenda.getId());
                    System.out.println("PRODUTO: " + pedidoVenda.getEstoque().getProduto().getDescricao());
                    System.out.println("CLIENTE: " + pedidoVenda.getCliente().getNome());
                    System.out.println("QUANTIDADE: " + pedidoVenda.getQuantidade());
                    //System.out.println("VALOR TOTAL: " + pedidoVenda.getValorTotal());
                    System.out.println("----------------------------------------------");
                }

                }
                break;
            }
        }

    }

