package controller;

import model.Produto;
import java.util.List;

public class ProdutoController {

    public int cadastrarProduto(String nome, String descricao, String tamanho, String cor, Double preco,  int estoque, int idFornecedor) {
        return Produto.cadastrarProduto(nome, descricao, tamanho, cor, preco, estoque, idFornecedor);
    }
    
    public boolean atualizarEstoque(int idProduto, int quantidade) {
        return Produto.atualizarEstoque(idProduto, quantidade);
    }

    public Produto consultarProduto(int idProduto) {
        return Produto.consultarEstoque(idProduto);
    }

    public List<Produto> consultarProdutos() {
        return Produto.consultarEstoqueGeral();
    }
}
