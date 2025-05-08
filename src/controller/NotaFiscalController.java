package controller;

import model.NotaFiscal;
import view.NotaFiscalView;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class NotaFiscalController {

    private NotaFiscalView notaFiscalView;

    public NotaFiscalController() {
        this.notaFiscalView = new NotaFiscalView();
    }

    public void consultarNotaFiscalPorId(int idNotaFiscal) {
        if (idNotaFiscal <= 0) {
            throw new IllegalArgumentException("ID da nota fiscal inválido");
        }

        NotaFiscal notaFiscal = NotaFiscal.consultarNotaFiscalPorId(idNotaFiscal);
        if (notaFiscal != null) {
            notaFiscalView.exibirNotaFiscal(notaFiscal);
        } else {
            notaFiscalView.exibirMensagemDeErro("Nota fiscal não encontrada.");
        }
    }

}
