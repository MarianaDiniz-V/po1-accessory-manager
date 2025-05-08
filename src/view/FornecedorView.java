package view;

import model.Fornecedor;

public class FornecedorView implements MensagemView{

    public void exibirFornecedor(Fornecedor fornecedor) {
        if (fornecedor != null) {
            System.out.println("Fornecedor encontrado: ");
            System.out.println("ID: " + fornecedor.getId());
            System.out.println("Nome: " + fornecedor.getNome());
            System.out.println("E-mail: " + fornecedor.getEmail());
            System.out.println("Telefone: " + fornecedor.getTelefone());
            System.out.println("CNPJ: " + fornecedor.getCnpj());
            System.out.println("Endereço: " + fornecedor.getEndereco());
        } else {
            System.out.println("Fornecedor não encontrado.");
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
