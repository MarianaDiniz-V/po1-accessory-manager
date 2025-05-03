import controller.ClienteController;
import controller.FornecedorController;
import controller.ItemVendaController;
import controller.NotaFiscalController;
import controller.ProdutoController;
import controller.VendaController;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {

            ClienteController clienteController = new ClienteController();
            int idCliente = clienteController.cadastrarCliente("Vasco Silva", "vasco.silva@email.com", "987654321",
                    "99988877766");

            FornecedorController fornecedorController = new FornecedorController();
            int idFornecedor = fornecedorController.cadastrarFornecedor("FornTec", "987654321", "contato@forntec.com",
                    "12345678000199", "Rua X XXX");

            ProdutoController produtoController = new ProdutoController();
            int idProduto = produtoController.cadastrarProduto("Produto X", "Categoria X", "G", "Cor X", 49.99, 10, idFornecedor);

            VendaController vendaController = new VendaController();
            int idVenda = vendaController.registrarVenda(idCliente, 99.98);

            ItemVendaController itemVendaController = new ItemVendaController();
            int idItemVenda = itemVendaController.cadastrarItemVenda(idVenda, idProduto, 2, 49.99);

            // 👉 Novo: Buscar produtos da venda
            List<Map<String, Object>> produtosDaVenda = vendaController.listarProdutosDaVenda(idVenda);

            NotaFiscalController notaFiscalController = new NotaFiscalController();
            Date dataEmissao = new Date();

            // 👉 Novo: Gerar XML com os produtos incluídos
            String xmlNotaFiscal = notaFiscalController.gerarXMLNotaFiscal(idVenda, "NF123456", dataEmissao,
                    produtosDaVenda);

            System.out.println("XML da Nota Fiscal Gerada:");
            System.out.println(xmlNotaFiscal);

            // Chamada correta para método estático
            boolean notaFiscalCadastrada = NotaFiscalController.cadastrarNotaFiscal(idVenda, "NF123456", dataEmissao, xmlNotaFiscal);

            if (notaFiscalCadastrada) {
                System.out.println("Nota Fiscal cadastrada com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar a Nota Fiscal.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
