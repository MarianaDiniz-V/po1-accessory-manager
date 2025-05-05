package controller;

import java.util.List;

import model.ItemVenda;

public class ItemVendaController {

    public int cadastrarItemVenda(int idVenda, int idProduto, int quantidade, double precoUnitario) {
        return ItemVenda.cadastrarItemVenda(idVenda, idProduto, quantidade, precoUnitario);
    }
    
    public ItemVenda consultarItemVenda(int id) {
        return ItemVenda.consultarItemVenda(id);
    }

    public List<ItemVenda> consultarItensVenda(int idVenda) {
        return ItemVenda.consultarItensVenda(idVenda);
    }
    

    
}
