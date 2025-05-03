package controller;

import model.Venda;
import java.util.List;
import java.util.Map;

public class VendaController {

    public int registrarVenda(int idCliente, double total) {
        return Venda.registrarVenda(idCliente, total);
    }    

    public List<Venda> listarVendas() {
        return Venda.listarVendas();
    }
    public List<Venda> consultarVendasPorCliente(int idCliente) {
        return Venda.consultarVendasPorCliente(idCliente);
    }

    public List<Map<String, Object>> listarProdutosDaVenda(int idVenda) {
        return Venda.getProdutosPorVenda(idVenda);
    }
}
