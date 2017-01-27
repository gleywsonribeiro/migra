/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Fornecedor;
import br.com.icone.martan.modelo.TipoPessoa;
import br.com.icone.martan.modelo.repositorio.FornecedorFacade;
import br.com.icone.martan.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "fornecedorController")
@SessionScoped
public class FornecedorController implements Serializable {

    private Fornecedor fornecedor;
    private List<Fornecedor> fornecedores;
    @Inject
    private FornecedorFacade repositorio;

    public FornecedorController() {
        this.fornecedor = new Fornecedor();
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getFornecedores() {
        if (this.fornecedores == null) {
            fornecedores = repositorio.findAll();
        }
        return fornecedores;
    }
    
    public void novo() {
        this.fornecedor = new Fornecedor();
    }

    public void salvar() {
        if (fornecedor.getId() == null) {
            repositorio.create(fornecedor);
            JsfUtil.addSuccessMessage("Fornecedor cadastrado com sucesso!");
        } else {
            repositorio.edit(fornecedor);
            JsfUtil.addSuccessMessage("Fornecedor alterado com sucesso!");
        }
        this.fornecedor = new Fornecedor();
        this.fornecedores = null;
    }

    public void remover() {
        this.repositorio.remove(fornecedor);
        fornecedor = new Fornecedor();
        fornecedores = null;
        JsfUtil.addSuccessMessage("Fornecedor removido com sucesso!");
    }

    public TipoPessoa[] getTiposPessoa() {
        return TipoPessoa.values();
    }
}
