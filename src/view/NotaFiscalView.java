package view;

import model.NotaFiscal;

import java.text.SimpleDateFormat;

public class NotaFiscalView implements MensagemView {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void exibirNotaFiscal(NotaFiscal notaFiscal) {
        if (notaFiscal != null) {
            System.out.println("Nota Fiscal encontrada: ");
            System.out.println("ID: " + notaFiscal.getId());
            System.out.println("ID Venda: " + notaFiscal.getIdVenda());
            System.out.println("Número: " + notaFiscal.getNumero());
            System.out.println("Data de Emissão: " + dateFormat.format(notaFiscal.getDataEmissao()));
            System.out.println("XML: " + notaFiscal.getXml());
        } else {
            System.out.println("Nota fiscal não encontrada.");
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
