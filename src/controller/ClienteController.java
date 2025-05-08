package controller;

import model.Cliente;
import view.ClienteView;

import java.util.List;

public class ClienteController {

    private ClienteView view;

    public ClienteController() {
        this.view = new ClienteView();
    }

    public void cadastrarCliente(String nome, String email, String telefone, String cpfCnpj) {
        try {
            int id = Cliente.cadastrarCliente(nome, email, telefone, cpfCnpj);
            if (id != -1) {
                view.exibirMensagemDeSucesso("Cliente cadastrado com sucesso! ID: " + id);
            } else {
                view.exibirMensagemDeErro("Erro ao cadastrar cliente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void consultarClientePorCpfCnpj(String cpfCnpjCliente) {
        Cliente cliente = Cliente.consultarClientePorCpfCnpj(cpfCnpjCliente);

        if (cliente != null) {
            view.exibirCliente(cliente);
        } else {
            view.exibirMensagemDeErro("Cliente não encontrado com o CPF/CNPJ: " + cpfCnpjCliente);
        }
    }

    public void listarClientes() {
        try {
            List<Cliente> clientes = Cliente.listarClientes();
            if (clientes != null && !clientes.isEmpty()) {
                for (Cliente cliente : clientes) {
                    view.exibirCliente(cliente);
                }
            } else {
                view.exibirMensagemDeErro("Nenhum cliente encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarCliente(int id, String nome, String email, String telefone, String cpfCnpj) {
        try {
            boolean atualizado = Cliente.atualizarCliente(id, nome, email, telefone, cpfCnpj);
            if (atualizado) {
                view.exibirMensagemDeSucesso("Cliente atualizado com sucesso!");
            } else {
                view.exibirMensagemDeErro("Erro ao atualizar cliente. Cliente não encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
