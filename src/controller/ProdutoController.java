package controller;

import model.Produto;
import view.ProdutoView;

import java.util.List;

public class ProdutoController {

    private ProdutoView view;

    public ProdutoController() {
        this.view = new ProdutoView();
    }

    public void cadastrarProduto(String nome, String descricao, String tamanho, String cor, Double preco,  int estoque, int idFornecedor) {
        try {
            int id = Produto.cadastrarProduto(nome, descricao, tamanho, cor, preco, estoque, idFornecedor);
            if (id != -1) {
                view.exibirMensagemDeSucesso("Produto cadastrado com sucesso! ID: " + id);
            } else {
                view.exibirMensagemDeErro("Erro ao cadastrar produto.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void atualizarEstoque(int idProduto, int quantidade) {
        try {
            boolean atualizado = Produto.atualizarEstoque(idProduto, quantidade);
            if (atualizado) {
                view.exibirMensagemDeSucesso("Estoque atualizado com sucesso.");
            } else {
                view.exibirMensagemDeErro("Erro ao atualizar o estoque. Produto não encontrado ou quantidade inválida.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultarProduto(int idProduto) {
        try {
            Produto produto = Produto.consultarEstoque(idProduto);
            if (produto != null) {
                view.exibirProduto(produto);
            } else {
                view.exibirMensagemDeErro("Produto não encontrado com o ID: " + idProduto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultarProdutos() {
        try {
            List<Produto> produtos = Produto.consultarEstoqueGeral();
            if (produtos != null && !produtos.isEmpty()) {
                view.exibirListaProdutos(produtos);
            } else {
                view.exibirMensagemDeErro("Nenhum produto encontrado no estoque.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
