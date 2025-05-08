package view;

import model.ItemVenda;

public class ItemVendaView implements MensagemView{

    public void exibirItemVenda(ItemVenda itemVenda) {
        if (itemVenda != null) {
            System.out.println("Item de venda encontrado: ");
            System.out.println("ID: " + itemVenda.getId());
            System.out.println("ID da Venda: " + itemVenda.getIdVenda());
            System.out.println("ID do Produto: " + itemVenda.getIdProduto());
            System.out.println("Quantidade: " + itemVenda.getQuantidade());
            System.out.println("Preço Unitário: " + itemVenda.getPrecoUnitario());
            System.out.println("Total: " + itemVenda.getTotal());
        } else {
            System.out.println("Item de venda não encontrado.");
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
