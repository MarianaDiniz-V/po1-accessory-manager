import controller.*;
import model.Carrinho;

import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ClienteController clienteController = new ClienteController();
    private static final FornecedorController fornecedorController = new FornecedorController();
    private static final ProdutoController produtoController = new ProdutoController();
    private static final VendaController vendaController = new VendaController();
    private static final NotaFiscalController notaFiscalController = new NotaFiscalController();
    private static final ItemVendaController itemVendaController = new ItemVendaController();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> cadastrarFornecedor();
                case 3 -> cadastrarProduto();
                case 4 -> consultarProduto();
                case 5 -> atualizarEstoque();
                case 6 -> registrarVenda();
                case 7 -> consultarNotaFiscal();
                case 8 -> listarVendas();
                case 9 -> consultarItemVenda();
                case 0 -> System.out.println("Encerrando o sistema.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Cadastrar Fornecedor");
        System.out.println("3. Cadastrar Produto");
        System.out.println("4. Consultar Produto");
        System.out.println("5. Atualizar Estoque");
        System.out.println("6. Registrar Venda");
        System.out.println("7. Consultar Nota Fiscal");
        System.out.println("8. Listar Vendas");
        System.out.println("9. Consultar Item de Venda");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        clienteController.cadastrarCliente(nome, email, telefone, cpf);
    }

    private static void cadastrarFornecedor() {
        System.out.print("Nome da Empresa: ");
        String nome = scanner.nextLine();
        System.out.print("Nome do Representante: ");
        String nomeRepresentante = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        fornecedorController.cadastrarFornecedor(nome, nomeRepresentante, email, cnpj, endereco);
    }

    private static void cadastrarProduto() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Tamanho: ");
        String tamanho = scanner.nextLine();
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        System.out.print("Estoque: ");
        int estoque = scanner.nextInt();
        System.out.print("ID do Fornecedor: ");
        int idFornecedor = scanner.nextInt();
        produtoController.cadastrarProduto(nome, descricao, tamanho, cor, preco, estoque, idFornecedor);
    }

    private static void consultarProduto() {
        System.out.print("ID do Produto: ");
        int id = scanner.nextInt();
        produtoController.consultarProduto(id);
    }

    private static void atualizarEstoque() {
        System.out.print("ID do Produto: ");
        int id = scanner.nextInt();
        System.out.print("Novo Estoque: ");
        int estoque = scanner.nextInt();
        produtoController.atualizarEstoque(id, estoque);
    }

    private static void registrarVenda() {
        System.out.print("ID do Cliente: ");
        int idCliente = scanner.nextInt();
        List<Carrinho> carrinho = new ArrayList<>();
        while (true) {
            System.out.print("ID do Produto: ");
            int idProduto = scanner.nextInt();
            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();
            carrinho.add(new Carrinho(idProduto, quantidade));
            System.out.print("Deseja adicionar mais produtos? (s/n): ");
            scanner.nextLine();
            String resposta = scanner.nextLine();
            if (!resposta.equalsIgnoreCase("s")) break;
        }
        vendaController.registrarVenda(idCliente, carrinho);
    }

    private static void consultarNotaFiscal() {
        System.out.print("ID da Nota Fiscal: ");
        int id = scanner.nextInt();
        notaFiscalController.consultarNotaFiscalPorId(id);
    }

    private static void listarVendas() {
        vendaController.listarVendas();
    }

    private static void consultarItemVenda() {
        System.out.print("ID do Item de Venda: ");
        int id = scanner.nextInt();
        itemVendaController.consultarItemVenda(id);
    }
}
