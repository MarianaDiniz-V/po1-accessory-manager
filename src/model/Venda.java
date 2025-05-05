package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id;
    private Date data;
    private int idCliente;
    private double total;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static int registrarVenda(int idCliente, double total) {
        String sql = "INSERT INTO vendas (int_id_cliente, dec_total, str_status, dat_data) VALUES (?, ?, ?, NOW())";
    
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement insert = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    
            insert.setInt(1, idCliente);
            insert.setDouble(2, total);
            insert.setString(3, "Pendente");
    
            int response = insert.executeUpdate();
    
            if (response == 0) {
                throw new SQLException("Falha ao registrar a venda.");
            }
    
            ResultSet generatedKeys = insert.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return -1;
    }
    
    public static List<Venda> listarVendas() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT int_id_venda, int_id_cliente, dec_total, str_status, dat_data FROM vendas";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement select = conn.prepareStatement(sql);
             ResultSet rs = select.executeQuery()) {

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("int_id_venda"));
                venda.setIdCliente(rs.getInt("int_id_cliente"));
                venda.setTotal(rs.getDouble("dec_total"));
                venda.setStatus(rs.getString("str_status"));
                venda.setData(rs.getDate("dat_data"));
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendas;
    }

    public static List<Venda> consultarVendasPorCliente(int idCliente) {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT int_id_venda, int_id_cliente, dec_total, str_status, dat_data FROM vendas WHERE int_id_cliente = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement select = conn.prepareStatement(sql)) {

            select.setInt(1, idCliente);

            try (ResultSet rs = select.executeQuery()) {
                while (rs.next()) {
                    Venda venda = new Venda();
                    venda.setId(rs.getInt("int_id_venda"));
                    venda.setIdCliente(rs.getInt("int_id_cliente"));
                    venda.setTotal(rs.getDouble("dec_total"));
                    venda.setStatus(rs.getString("str_status"));
                    venda.setData(rs.getDate("dat_data"));
                    vendas.add(venda);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendas.isEmpty() ? null : vendas;
    }

    public static List<java.util.Map<String, Object>> getProdutosPorVenda(int idVenda) {
        List<java.util.Map<String, Object>> produtos = new ArrayList<>();
    
        String sql = "SELECT p.int_id_produto, p.str_nome, i.int_quantidade, i.dec_preco_unitario, i.dec_total " +
                     "FROM itens_venda i " +
                     "JOIN produtos p ON i.int_id_produto = p.int_id_produto " +
                     "WHERE i.int_id_venda = ?";
    
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, idVenda);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                java.util.Map<String, Object> produto = new java.util.HashMap<>();
                produto.put("idProduto", rs.getInt("int_id_produto"));
                produto.put("nome", rs.getString("str_nome"));
                produto.put("quantidade", rs.getInt("int_quantidade"));
                produto.put("precoUnitario", rs.getDouble("dec_preco_unitario"));
                produto.put("total", rs.getDouble("dec_total"));
    
                produtos.add(produto);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return produtos;
    }
    
}
