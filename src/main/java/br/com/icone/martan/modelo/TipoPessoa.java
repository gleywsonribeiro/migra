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
public enum TipoPessoa {
    FISICA("Física"),
    JURIDICA("Jurídica");

    private TipoPessoa(String descricao) {
        this.descricao = descricao;
    }
    
    private final String descricao;
    
    public String getDescricao() {
        return descricao;
    }
}
