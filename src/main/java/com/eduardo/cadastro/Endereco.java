package com.eduardo.cadastro;

public record Endereco(String logradouro, String bairro, String localidade, String uf, String ddd) {
    @Override
    public final String toString() {
            // TODO Auto-generated method stub
            return logradouro + ", " + bairro+ ", "  + localidade + ", " + uf + ", " + ddd;
    }
}
