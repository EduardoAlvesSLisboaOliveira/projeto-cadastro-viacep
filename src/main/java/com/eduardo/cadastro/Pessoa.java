package com.eduardo.cadastro;

public record Pessoa(String nome, String email, String complemento, Endereco endereco) {
    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
               "Email: " + email + "\n" +
               "Endereço: " + endereco + "\n" +
               "Complemento: " + complemento;
    }
}