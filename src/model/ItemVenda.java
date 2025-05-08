package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemVenda {
    private int id;
    private int idVenda;
    private int idProduto;
    private int quantidade;
    private double precoUnitario;
    private double total;

    public double getTotal() {
        return total;
    }

    private void calcularTotal() {
        this.total = this.precoUnitario * this.quantidade;
    }

    public static int cadastrarItemVenda(int idVenda, int idProduto, int quantidade, double precoUnitario) {
        double total = precoUnitario * quantidade;

        String sql = "INSERT INTO itens_venda (int_id_venda, int_id_produto, int_quantidade, dec_preco_unitario, dec_total) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement insert = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            insert.setInt(1, idVenda);
            insert.setInt(2, idProduto);
            insert.setInt(3, quantidade);
            insert.setDouble(4, precoUnitario);
            insert.setDouble(5, total);

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

    public static ItemVenda consultarItemVenda(int id) {
        String sql = "SELECT int_id_item, int_id_venda, int_id_produto, int_quantidade, dec_preco_unitario, dec_total FROM itens_venda WHERE int_id_item = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement select = conn.prepareStatement(sql)) {

            select.setInt(1, id);
            try (ResultSet rs = select.executeQuery()) {
                if (rs.next()) {
                    ItemVenda item = new ItemVenda();
                    item.setId(rs.getInt("int_id_item"));
                    item.setIdVenda(rs.getInt("int_id_venda"));
                    item.setIdProduto(rs.getInt("int_id_produto"));
                    item.setQuantidade(rs.getInt("int_quantidade"));
                    item.setPrecoUnitario(rs.getDouble("dec_preco_unitario"));
                    item.total = rs.getDouble("dec_total");

                    return item;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ItemVenda> consultarItensVenda(int idVenda) {
        String sql = "SELECT int_id_item, int_id_venda, int_id_produto, int_quantidade, dec_preco_unitario, dec_total FROM itens_venda WHERE int_id_venda = ?";
        List<ItemVenda> itens = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement select = conn.prepareStatement(sql)) {

            select.setInt(1, idVenda);
            try (ResultSet rs = select.executeQuery()) {
                while (rs.next()) {
                    ItemVenda item = new ItemVenda();
                    item.setId(rs.getInt("int_id_item"));
                    item.setIdVenda(rs.getInt("int_id_venda"));
                    item.setIdProduto(rs.getInt("int_id_produto"));
                    item.setQuantidade(rs.getInt("int_quantidade"));
                    item.setPrecoUnitario(rs.getDouble("dec_preco_unitario"));
                    item.total = rs.getDouble("dec_total");

                    itens.add(item);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {this.idVenda = idVenda;}

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        calcularTotal();
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
        calcularTotal();
    }

}
