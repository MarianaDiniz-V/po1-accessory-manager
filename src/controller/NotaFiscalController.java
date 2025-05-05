package controller;

import model.NotaFiscal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class NotaFiscalController {

    public static boolean cadastrarNotaFiscal(int idVenda, String numero, Date dataEmissao, String xml) {
        if (idVenda <= 0 || numero == null || numero.isEmpty() || dataEmissao == null || xml == null || xml.isEmpty()) {
            throw new IllegalArgumentException("Parâmetros inválidos para cadastrar a nota fiscal");
        }

        return NotaFiscal.cadastrarNotaFiscal(idVenda, numero, dataEmissao, xml);
    }

    public static NotaFiscal consultarNotaFiscalPorId(int idNotaFiscal) {
        if (idNotaFiscal <= 0) {
            throw new IllegalArgumentException("ID da nota fiscal inválido");
        }

        return NotaFiscal.consultarNotaFiscalPorId(idNotaFiscal);
    }

    public String gerarXMLNotaFiscal(int idVenda, String numeroNF, Date dataEmissao, List<Map<String, Object>> produtos)
    {
        return NotaFiscal.gerarXMLNotaFiscal(idVenda, numeroNF, dataEmissao, produtos);
    }

}
