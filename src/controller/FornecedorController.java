package controller;

import model.Fornecedor;
import view.FornecedorView;

public class FornecedorController {

    private FornecedorView view;

    public FornecedorController() {
        this.view = new FornecedorView();
    }

    public void cadastrarFornecedor(String nome, String contato, String email, String cnpj, String endereco) {
        try {
            int id = Fornecedor.cadastrarFornecedor(nome, contato, email, cnpj, endereco);
            if (id != -1) {
                view.exibirMensagemDeSucesso("Fornecedor cadastrado com sucesso! ID: " + id);
            } else {
                view.exibirMensagemDeErro("Erro ao cadastrar fornecedor.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void atualizarFornecedor(int id, String nome, String email, String telefone, String cnpj, String endereco) {
        boolean sucesso = Fornecedor.atualizarFornecedor(id, nome, email, telefone, cnpj, endereco);

        if (sucesso) {
            view.exibirMensagemDeSucesso("Fornecedor atualizado com sucesso!");
        } else {
            view.exibirMensagemDeErro("Erro ao atualizar fornecedor.");
        }
    }

    public void consultarFornecedorPorCnpj(String cnpj) {

        Fornecedor fornecedor = Fornecedor.consultarFornecedorPorCnpj(cnpj);

        if (fornecedor != null) {
            view.exibirFornecedor(fornecedor);
        } else {
            view.exibirMensagemDeErro("Fornecedor não encontrado com o CNPJ: " + cnpj);
        }
    }
    
}
