package model;

import java.sql.*;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String papel;


        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public static boolean cadastrarUsuario(String nome, String email, String senha, String papel) throws Exception {
        if (consultarUsuarioPorEmail(email)) {
            throw new Exception("Encontrado cadastro para o usuário com o email " + email);
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement insert = conn.prepareStatement("INSERT INTO usuarios (str_nome, str_email, str_senha, str_papel) VALUES (?, ?, ?, ?)")) {
            insert.setString(1, nome);
            insert.setString(2, email);
            insert.setString(3, senha);
            insert.setString(4, papel);
            return insert.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean consultarUsuarioPorEmail(String email) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement select = conn.prepareStatement("SELECT COUNT(*) FROM usuarios WHERE str_email = ?")) {
            select.setString(1, email);
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

    public static boolean atualizarUsuario(int id, String nome, String email, String senha, String papel) {
        String sql = "UPDATE usuarios SET str_nome = ?, str_email = ?, str_senha = ?, str_papel = ? WHERE int_id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setString(4, papel);
            stmt.setInt(5, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
