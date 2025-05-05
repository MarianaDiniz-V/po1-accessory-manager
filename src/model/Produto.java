package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Produto {

    private int id;
    private String nome;
    private String descricao;
    private String cor;
    private double preco;
    private int tamanho;
    private int estoque;
    private int idFornecedor;

    
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
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

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int estoque) {
        this.estoque = estoque;
    }


    public static int cadastrarProduto(String nome, String descricao, String tamanho, String cor, Double preco,  int estoque, int idFornecedor) {
        String sql = "INSERT INTO produtos (str_nome, str_descricao, str_tamanho, str_cor, dec_preco, int_qtd_estoque, int_id_fornecedor) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement insert = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    
            insert.setString(1, nome);
            insert.setString(2, descricao);
            insert.setString(3, tamanho);
            insert.setString(4, cor);
            insert.setDouble(5, preco);
            insert.setInt(6, estoque);
            insert.setInt(7, idFornecedor);
    
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


    public static boolean atualizarEstoque(int idProduto, int quantidade) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement update = conn.prepareStatement("UPDATE produtos SET int_qtd_estoque = int_qtd_estoque + ? WHERE int_id_produto = ?")) {

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
             PreparedStatement select = conn.prepareStatement("SELECT * FROM produtos WHERE int_id_produto = ?")) {

            select.setInt(1, idProduto);
            ResultSet response = select.executeQuery();

            if (response.next()) {
                Produto produto = new Produto();
                produto.setId(response.getInt("int_id_produto"));
                produto.setNome(response.getString("str_nome"));
                produto.setDescricao(response.getString("str_descricao"));
                produto.setCor(response.getString("str_cor"));
                produto.setPreco(response.getDouble("dec_preco"));
                produto.setTamanho(response.getInt("str_tamanho"));
                produto.setEstoque(response.getInt("int_qtd_estoque"));
                produto.setIdFornecedor(response.getInt("int_id_fornecedor"));
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
                produto.setId(response.getInt("int_id_produto"));
                produto.setNome(response.getString("str_nome"));
                produto.setDescricao(response.getString("str_descricao"));
                produto.setCor(response.getString("str_cor"));
                produto.setPreco(response.getDouble("dec_preco"));
                produto.setTamanho(response.getInt("str_tamanho"));
                produto.setEstoque(response.getInt("int_qtd_estoque"));
                produto.setIdFornecedor(response.getInt("int_id_fornecedor"));

                produtos.add(produto); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;  
    }

    public static List<Produto> listarProdutosPorVenda(int idVenda) {
        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seu_banco", "usuario", "senha")) {
            String sql = "SELECT p.int_id_produto, p.nome_produto, p.preco " +
                         "FROM Produtos v " +
                         "JOIN vendas_produtos vp ON v.int_id_venda = vp.int_id_venda " +
                         "JOIN produtos p ON vp.int_id_produto = p.int_id_produto " +
                         "WHERE v.int_id_venda = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idVenda);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Produto produto = new Produto();
                        produto.setId(rs.getInt("int_id_produto"));
                        produto.setNome(rs.getString("nome_produto"));
                        produto.setPreco(rs.getDouble("preco"));
                        produtos.add(produto);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

}


