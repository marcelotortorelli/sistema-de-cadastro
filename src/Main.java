import db.ProdutosDB;
import db.UsuariosDB;
import models.Admin;
import models.Cliente;
import models.Produto;
import models.Usuario;

import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    static ProdutosDB produtosDB = new ProdutosDB();
    static UsuariosDB usuariosDB = new UsuariosDB();
    public static void main(String[] args) throws Exception {
        System.out.println("--- PEDIDO DE VENDAS ---");

        int option;

        do{
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos Cadastrados");
            System.out.println("3 - Cadastrar Administrador");
            System.out.println("4 - Cadastrar Cliente");
            System.out.println("5 - Listar Usuários Cadastrados");
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


                //novoProduto.setId(id);
                //novoProduto.setDescricao(descricao);


 //               System.out.println("Produto criado com sucesso:");
 //               System.out.println("--- ID: " + novoProduto.getId());
 //               System.out.println("--- Descrição: " + novoProduto.getDescricao());
 //               System.out.println("--- Preço: " + novoProduto.getPreco());
 //               System.out.println("--- Data de Validade: " + novoProduto.getDataValidade());
 //              System.out.println("------------------------------------------");
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
                }
        }

    }
}
