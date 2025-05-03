package model;

import java.util.Date;
import java.sql.*;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String cpfCnpj;
    private Date criacao;

    public static boolean cadastrarCliente(String nome, String contato, String cpfCnpj) throws Exception {
        if (consultarClientePorCpfCnpj(cpfCnpj)) {
            throw new Exception("Encontrado cadastro para o cliente de documento " + cpfCnpj);
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", ""); //TODO(testar conexão com o banco)
             PreparedStatement insert = conn.prepareStatement("INSERT INTO clientes (str_nome, str_telefone, str_cpf_cnpj) VALUES (?, ?, ?)")) {
            insert.setString(1, nome);
            insert.setString(2, contato);
            insert.setString(3, cpfCnpj);
            return insert.executeUpdate() > 0;
        } catch (SQLException e) { //TODO(avaliar tratativa)
            e.printStackTrace();
        }
        return false;
    }

    public static boolean consultarClientePorCpfCnpj(String cpfCnpj) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement count = conn.prepareStatement("SELECT COUNT(*) FROM clientes WHERE str_cpf_cnpj = ?")) {
            count.setString(1, cpfCnpj);
            try (ResultSet rs = count.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Se o contador for maior que 0, o CPF/CNPJ já existe
                }
            }
        } catch (SQLException e) {  //TODO(avaliar tratativa)
            e.printStackTrace();
        }
        return false;
    }
}

