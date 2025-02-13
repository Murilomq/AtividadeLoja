package br.edu.ifal.domain;

public class Funcionario {
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;

    public Funcionario(String cpf, String nome, String endereco, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "CPF='" + cpf + '\'' +
                ", Nome='" + nome + '\'' +
                ", Endereco='" + endereco + '\'' +
                ", Telefone='" + telefone + '\'' +
                '}';
    }
}

