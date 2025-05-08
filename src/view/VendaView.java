package view;

import model.Venda;
import model.ItemVenda;

import java.text.SimpleDateFormat;
import java.util.List;

public class VendaView implements MensagemView {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void exibirVenda(Venda venda) {
        if (venda != null) {
            System.out.println("Venda encontrada: ");
            System.out.println("ID: " + venda.getId());
            System.out.println("ID Cliente: " + venda.getIdCliente());
            System.out.println("Total: " + venda.getTotal());
            System.out.println("Data da venda: " + venda.getData());
            System.out.println("------------");
        } else {
            System.out.println("Venda não encontrada.");
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
