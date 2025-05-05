package controller;

import model.Fornecedor;

public class FornecedorController {

    public int cadastrarFornecedor(String nome, String contato, String email, String cnpj, String endereco) {
        try {
            return Fornecedor.cadastrarFornecedor(nome, contato, email, cnpj, endereco);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public boolean atualizarFornecedor(int id, String nome, String email, String telefone, String cnpj, String endereco) {
        return Fornecedor.atualizarFornecedor(id, nome, email, telefone, cnpj, endereco);
    }

    public Fornecedor consultarFornecedorPorCnpj(String cnpj) {
        return Fornecedor.consultarFornecedorPorCnpj(cnpj);
    }
    
}
