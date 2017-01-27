/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.modelo;

/**
 *
 * @author Gleywson
 */
public enum FormaPagamento {
    DINHEIRO("Dinheiro"),
    CARTAO_CREDITO("Cartão de crédito"),
    CARTAO_DEBITO("Cartão de débito"),
    CHEQUE("Cheque"),
    BOLETO_BANCARIO("Boleto bancário"),
    DEPOSITO_BANCARIO("Depósito bancário");

    private final String descricao;

    FormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
