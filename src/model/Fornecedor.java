package model;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class Fornecedor {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String cnpj;
    private String endereco;

    public static boolean cadastrarFornecedor(String nome, String contato, String cnpj) throws Exception {
        if (consultarFornecedorPorCnpj(cnpj)) {
            throw new Exception("Encontrado cadastro para o fornecedor de documento " + cnpj);
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement insert = conn.prepareStatement("INSERT INTO fornecedores (str_nome, str_telefone, str_cnpj) VALUES (?, ?, ?)")) {
            insert.setString(1, nome);
            insert.setString(2, contato);
            insert.setString(3, cnpj);
            return insert.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean consultarFornecedorPorCnpj(String cnpj) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement select = conn.prepareStatement("SELECT COUNT(*) FROM fornecedores WHERE str_cnpj = ?")) {
            select.setString(1, cnpj);
            try (ResultSet rs = select.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

