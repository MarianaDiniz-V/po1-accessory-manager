package model;

import java.sql.*;

public class Fornecedor {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String cnpj;
    private String endereco;

    public static int cadastrarFornecedor(String nome, String contato, String email, String cnpj, String endereco) throws Exception {
        if (consultarFornecedorPorCnpj(cnpj) != null) {
            throw new Exception("Encontrado cadastro para o fornecedor de documento " + cnpj);
        }
    
        String sql = "INSERT INTO fornecedores (str_nome, str_telefone, str_email, str_cnpj, str_endereco) VALUES (?, ?, ?, ?, ?)";
    
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement insert = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            insert.setString(1, nome);
            insert.setString(2, contato);
            insert.setString(3, email);
            insert.setString(4, cnpj);
            insert.setString(5, endereco);
    
            insert.executeUpdate();
    
            try (ResultSet rs = insert.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return -1;
    }
    

    public static Fornecedor consultarFornecedorPorCnpj(String cnpj) {
        String sql = "SELECT int_id_fornecedor, str_nome, str_email, str_telefone, str_cnpj, str_endereco FROM fornecedores WHERE str_cnpj = ?";
    
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement select = conn.prepareStatement(sql)) {
            
            select.setString(1, cnpj);
            try (ResultSet rs = select.executeQuery()) {
                if (rs.next()) {
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.id = rs.getInt("int_id_fornecedor");
                    fornecedor.nome = rs.getString("str_nome");
                    fornecedor.email = rs.getString("str_email");
                    fornecedor.telefone = rs.getString("str_telefone");
                    fornecedor.cnpj = rs.getString("str_cnpj");
                    fornecedor.endereco = rs.getString("str_endereco");
                    return fornecedor;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public static boolean atualizarFornecedor(int id, String nome, String email, String telefone, String cnpj, String endereco) {
        String sql = "UPDATE fornecedores SET str_nome = ?, str_email = ?, str_telefone = ?, str_cnpj = ?, str_endereco = ? WHERE int_id_fornecedor = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            stmt.setString(4, cnpj);
            stmt.setString(5, endereco);
            stmt.setInt(6, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}