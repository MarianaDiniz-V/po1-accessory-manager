package controller;

import java.util.List;

import model.ItemVenda;
import view.ItemVendaView;

public class ItemVendaController {

    private ItemVendaView itemVendaView;

    public ItemVendaController() {
        this.itemVendaView = new ItemVendaView();
    }
    
    public void consultarItemVenda(int id) {
        ItemVenda itemVenda = ItemVenda.consultarItemVenda(id);

        if (itemVenda != null) {
            itemVendaView.exibirItemVenda(itemVenda);
        } else {
            itemVendaView.exibirMensagemDeErro("Item de venda não encontrado.");
        }
    }

    public void consultarItensVenda(int idVenda) {

        List<ItemVenda> itensVenda = ItemVenda.consultarItensVenda(idVenda);

        if (!itensVenda.isEmpty()) {
            for (ItemVenda item : itensVenda) {
                itemVendaView.exibirItemVenda(item);
            }
        } else {
            itemVendaView.exibirMensagemDeErro("Nenhum item encontrado para esta venda.");
        }
    }
    

    
}
