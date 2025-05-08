package view;

import model.Cliente;

public class ClienteView implements MensagemView {

    public void exibirCliente(Cliente cliente) {
        if (cliente != null) {
            System.out.println("Cliente encontrado: ");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("CPF/CNPJ: " + cliente.getCpfCnpj());
            System.out.println("Data de Criação: " + cliente.getCriacao());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    @Override
    public void exibirMensagemDeErro(String mensagem) {
        System.out.println("Erro: " + mensagem);
    }

    @Override
    public void exibirMensagemDeSucesso(String mensagem) {
        System.out.println(mensagem);
    }
}
