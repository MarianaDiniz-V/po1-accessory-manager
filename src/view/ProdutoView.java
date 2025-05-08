package view;

import model.Produto;

import java.util.List;

public class ProdutoView implements MensagemView{

    public void exibirProduto(Produto produto) {
        if (produto != null) {
            System.out.println("Produto encontrado:");
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Tamanho: " + produto.getTamanho());
            System.out.println("Cor: " + produto.getCor());
            System.out.println("Preço: R$ " + produto.getPreco());
            System.out.println("Estoque: " + produto.getEstoque());
            System.out.println("ID Fornecedor: " + produto.getIdFornecedor());
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void exibirListaProdutos(List<Produto> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            System.out.println("Lista de produtos:");
            for (Produto produto : produtos) {
                exibirProduto(produto);
                System.out.println("-------------------------");
            }
        }
    }

    @Override
    public void exibirMensagemDeErro(String mensagem) {
        System.out.println("Erro: " + mensagem);
    }

    @Override
    public void exibirMensagemDeSucesso(String mensagem) {
        System.out.println(mensagem);
    }
}
