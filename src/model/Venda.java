package model;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class Venda {
    private int id;
    private Date data;
    private int idCliente;
    private double total;
    private String status;

    public static boolean registrarVenda(int idCliente, List<Produto> produtos, double desconto, double valorPago) {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_roupa", "root", "");
             PreparedStatement insert = conn.prepareStatement("INSERT INTO vendas (int_cliente_id, dbl_desconto, dbl_valor_pago, dt_data) VALUES (?, ?, ?, NOW())", Statement.RETURN_GENERATED_KEYS)) {

            // Registrar a venda
            insert.setInt(1, idCliente);
            insert.setDouble(2, desconto);
            insert.setDouble(3, valorPago);

            int response = insert.executeUpdate();

            if (response == 0) {
                throw new SQLException("Falha ao registrar a venda.");
            }


            ResultSet generatedKeys = insert.getGeneratedKeys();
            int idVenda = -1;
            if (generatedKeys.next()) {
                idVenda = generatedKeys.getInt(1);
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}

