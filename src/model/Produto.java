package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Produto {

    private int id;
    private String nome;
    private String categoria;
    private double preco;
    private int tamanho;
    private int estoque;

    public static boolean cadastrarProduto(String nome, String categoria, double preco, int tamanho, int estoque) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement insert = conn.prepareStatement(
                     "INSERT INTO produtos (str_nome, str_categoria, dbl_preco, int_tamanho, int_estoque) VALUES (?, ?, ?, ?, ?)")) {

            insert.setString(1, nome);
            insert.setString(2, categoria);
            insert.setDouble(3, preco);
            insert.setInt(4, tamanho);
            insert.setInt(5, estoque);

            return insert.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean atualizarEstoque(int idProduto, int quantidade) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement update = conn.prepareStatement("UPDATE produtos SET int_estoque = int_estoque + ? WHERE int_id = ?")) {

            update.setInt(1, quantidade);
            update.setInt(2, idProduto);
            return update.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Produto consultarEstoque(int idProduto) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement select = conn.prepareStatement("SELECT * FROM produtos WHERE int_id = ?")) {

            select.setInt(1, idProduto);
            ResultSet response = select.executeQuery();

            if (response.next()) {
                Produto produto = new Produto();
                produto.setId(response.getInt("int_id"));
                produto.setNome(response.getString("str_nome"));
                produto.setCategoria(response.getString("str_categoria"));
                produto.setPreco(response.getDouble("dbl_preco"));
                produto.setTamanho(response.getInt("int_tamanho"));
                produto.setEstoque(response.getInt("int_estoque"));
                return produto;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Produto> consultarEstoqueGeral() {
        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement select = conn.prepareStatement("SELECT * FROM produtos")) {

            ResultSet response = select.executeQuery();
            
            while (response.next()) {
                Produto produto = new Produto();
                produto.setId(response.getInt("int_id"));
                produto.setNome(response.getString("str_nome"));
                produto.setCategoria(response.getString("str_categoria"));
                produto.setPreco(response.getDouble("dbl_preco"));
                produto.setTamanho(response.getInt("int_tamanho"));
                produto.setEstoque(response.getInt("int_estoque"));

                produtos.add(produto); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;  
    }


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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}


