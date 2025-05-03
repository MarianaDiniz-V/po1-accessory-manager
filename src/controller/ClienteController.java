package controller;

import model.Cliente;

public class ClienteController {

    public int cadastrarCliente(String nome, String email, String telefone, String cpfCnpj) {
        try {
            return Cliente.cadastrarCliente(nome, email, telefone, cpfCnpj);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    

    public Cliente consultarClientePorCpfCnpj(String cpfCnpjCliente) {
        return Cliente.consultarClientePorCpfCnpj(cpfCnpjCliente);
    }
}
