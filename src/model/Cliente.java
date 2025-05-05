package model;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String cpfCnpj;
    private Date criacao;

    // Getters e Setters
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
        return nome;
    }

    public void setEmail(String nome) {
        this.nome = nome;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }
    
    public static int cadastrarCliente(String nome, String email, String telefone, String cpfCnpj) throws Exception {
        if (consultarClientePorCpfCnpj(cpfCnpj) != null) {
            throw new Exception("Já existe um cliente cadastrado com o documento: " + cpfCnpj);
        }
    
        String sql = "INSERT INTO clientes (str_nome, str_email, str_telefone, str_cpf_cnpj) VALUES (?, ?, ?, ?)";
    
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            stmt.setString(4, cpfCnpj);
            stmt.executeUpdate();
    
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return -1;
    }
    
    
    public static Cliente consultarClientePorCpfCnpj(String cpfCnpj) {
        String sql = "SELECT * FROM clientes WHERE str_cpf_cnpj = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpfCnpj);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("int_id_cliente"));
                    cliente.setNome(rs.getString("str_nome"));
                    cliente.setEmail(rs.getString("str_email"));
                    cliente.setTelefone(rs.getString("str_telefone"));
                    cliente.setCpfCnpj(rs.getString("str_cpf_cnpj"));
                    cliente.setCriacao(rs.getDate("dat_criacao"));
                    return cliente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    public static List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("int_id_cliente"));
                cliente.setNome(rs.getString("str_nome"));
                cliente.setEmail(rs.getString("str_email"));
                cliente.setTelefone(rs.getString("str_telefone"));
                cliente.setCpfCnpj(rs.getString("str_cpf_cnpj"));
                cliente.setCriacao(rs.getDate("dat_criacao"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public static boolean atualizarCliente(int id, String nome, String email, String telefone, String cpfCnpj) {
        String sql = "UPDATE clientes SET str_nome = ?, str_email = ?, str_telefone = ?, str_cpf_cnpj = ? WHERE int_id_cliente = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            stmt.setString(4, cpfCnpj);
            stmt.setInt(4, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
