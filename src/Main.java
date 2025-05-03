import model.Cliente;

public class Main {
    public static void main(String[] args) {
        try {
            boolean cadastrado = Cliente.cadastrarCliente("Maria da Costa", "11977777777", "012.345.678-09");
            if (cadastrado) {
                System.out.println("Cliente cadastrado com sucesso!");
            } else {
                System.out.println("Não foi possível cadastrar o cliente.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }
}
