package model;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class NotaFiscal {
    private int id;
    private int idVenda;
    private String numero;
    private Date dataEmissao;
    private String xml;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public static String gerarXMLNotaFiscal(int idVenda, String numeroNF, Date dataEmissao,
            List<Map<String, Object>> produtos) {
        StringBuilder xml = new StringBuilder();
        xml.append("<notaFiscal>");
        xml.append("<numero>").append(numeroNF).append("</numero>");
        xml.append("<dataEmissao>").append(dataEmissao).append("</dataEmissao>");
        xml.append("<vendaId>").append(idVenda).append("</vendaId>");
        xml.append("<produtos>");

        for (Map<String, Object> p : produtos) {
            xml.append("<produto>");
            xml.append("<id>").append(p.get("idProduto")).append("</id>");
            xml.append("<nome>").append(p.get("nome")).append("</nome>");
            xml.append("<quantidade>").append(p.get("quantidade")).append("</quantidade>");
            xml.append("<precoUnitario>").append(p.get("precoUnitario")).append("</precoUnitario>");
            xml.append("<total>").append(p.get("total")).append("</total>");
            xml.append("</produto>");
        }

        xml.append("</produtos>");
        xml.append("</notaFiscal>");
        return xml.toString();
    }

    public static boolean cadastrarNotaFiscal(int idVenda, String numero, Date dataEmissao, String xml) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
                PreparedStatement insert = conn.prepareStatement(
                        "INSERT INTO notas_fiscais (int_id_venda, str_numero_nf, dat_emissao, str_xml) VALUES (?, ?, ?, ?)")) {

            insert.setInt(1, idVenda);
            insert.setString(2, numero);
            insert.setDate(3, new java.sql.Date(dataEmissao.getTime()));
            insert.setString(4, xml);

            return insert.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static NotaFiscal consultarNotaFiscalPorId(int id) {
        NotaFiscal notaFiscal = null;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
                PreparedStatement select = conn.prepareStatement(
                        "SELECT int_id_nf, int_id_venda, str_numero_nf, dat_emissao, str_xml FROM notas_fiscais WHERE nt_id_nf = ?")) {

            select.setInt(1, id);

            try (ResultSet rs = select.executeQuery()) {
                if (rs.next()) {
                    notaFiscal = new NotaFiscal();
                    notaFiscal.setId(rs.getInt("int_id_nf"));
                    notaFiscal.setIdVenda(rs.getInt("int_id_venda"));
                    notaFiscal.setNumero(rs.getString("str_numero_nf"));
                    notaFiscal.setDataEmissao(rs.getDate("dat_emissao"));
                    notaFiscal.setXml(rs.getString("str_xml"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notaFiscal;
    }
}
