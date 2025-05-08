package controller;

import model.Carrinho;
import model.ItemVenda;
import model.Produto;
import model.Venda;
import model.NotaFiscal;
import view.VendaView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class VendaController {

    private VendaView view;

    public VendaController() {
        this.view = new VendaView();
    }

    public void registrarVenda(int idCliente, List<Carrinho> carrinho) {
        if (carrinho == null || carrinho.isEmpty()) {
            view.exibirMensagemDeErro("O carrinho está vazio. Adicione itens antes de registrar a venda.");
            return;
        }

        double total = 0.0;
        List<ItemVenda> itensVenda = new ArrayList<>();

        // Calcular o total da venda e preparar a lista de itens para a nota fiscal
        for (Carrinho item : carrinho) {
            Produto produto = Produto.consultarEstoque(item.getIdProduto());
            if (produto == null) {
                view.exibirMensagemDeErro("Produto não encontrado: ID " + item.getIdProduto());
                return;
            }
            double itemTotal = produto.getPreco() * item.getQuantidade();
            total += itemTotal;

            // Adicionar o ItemVenda à lista de itens da venda
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setIdProduto(produto.getId());
            itemVenda.setQuantidade(item.getQuantidade());
            itemVenda.setPrecoUnitario(produto.getPreco());

            itensVenda.add(itemVenda);
        }

        //Regsitra a venda
        int idVenda = Venda.registrarVenda(idCliente, total);
        if (idVenda == -1) {
            view.exibirMensagemDeErro("Erro ao registrar venda.");
            return;
        }

        for (ItemVenda itemVenda : itensVenda) {
            ItemVenda.cadastrarItemVenda(idVenda, itemVenda.getIdProduto(), itemVenda.getQuantidade(), itemVenda.getPrecoUnitario());
        }
        String numeroNF = NotaFiscal.gerarNumeroNotaFiscal();

        // Gerar o XML da nota fiscal
        Date dataEmissao = new Date(); // Data atual
        String xmlNotaFiscal = NotaFiscal.gerarXMLNotaFiscal(idVenda, numeroNF, dataEmissao, listarProdutosDaVenda(idVenda));
        // Cadastra a nota no banco
        boolean cadastroNotaFiscal = NotaFiscal.cadastrarNotaFiscal(idVenda, numeroNF, dataEmissao, xmlNotaFiscal);

        if (cadastroNotaFiscal) {
            view.exibirMensagemDeSucesso("Venda registrada com sucesso. ID Venda: " + idVenda + ", Nota Fiscal : " + numeroNF);
        } else {
            view.exibirMensagemDeErro("Venda registrada, mas houve erro ao emitir nota fiscal.");
        }
    }

    public void listarVendas() {
        try {
            List<Venda> vendas = Venda.listarVendas();
            if (vendas != null && !vendas.isEmpty()) {
                for (Venda venda : vendas) {
                    view.exibirVenda(venda);
                }
            } else {
                view.exibirMensagemDeErro("Nenhuma venda encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.exibirMensagemDeErro("Erro ao listar vendas: " + e.getMessage());
        }
    }
    public void consultarVendasPorCliente(int idCliente) {
        try {
            List<Venda> vendas = Venda.consultarVendasPorCliente(idCliente);
            if (vendas != null && !vendas.isEmpty()) {
                for (Venda venda : vendas) {
                    view.exibirVenda(venda);
                }
            } else {
                view.exibirMensagemDeErro("Nenhuma venda encontrada para o cliente com ID: " + idCliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.exibirMensagemDeErro("Erro ao consultar vendas do cliente: " + e.getMessage());
        }
    }

    private List<Map<String, Object>> listarProdutosDaVenda(int idVenda) {
        return Venda.getProdutosPorVenda(idVenda);
    }
}
