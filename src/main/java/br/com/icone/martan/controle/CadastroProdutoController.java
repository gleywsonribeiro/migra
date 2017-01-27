/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Categoria;
import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
import br.com.icone.martan.util.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "cadastroProdutoController")
@ConversationScoped
public class CadastroProdutoController implements Serializable {

    private Produto produto;
    private List<Produto> produtos;

    @Inject
    private ProdutoFacade repositorio;

    @Inject
    private Conversation conversacao;

    public CadastroProdutoController() {
        this.produto = new Produto();
    }

    public String validar() {
        if (conversacao.isTransient()) {
            return "pesquisaProduto?faces-redirect=true";
        }
        return null;
    }

    public void salvar() {
        if (produto.getId() == null) {
            repositorio.create(produto);
        } else {
            repositorio.edit(produto);
        }
        this.produto = new Produto();
        this.produtos = null;
        JsfUtil.addSuccessMessage("Salvo com sucesso!");
    }

    public String novo() {
        this.produto = new Produto();
        return "cadastroProduto?faces-redirect=true";
    }

    public String editar() {
        iniciar();
        return "cadastroProduto?faces-redirect=true";
    }

    public void remover() {
        repositorio.remove(produto);
        produtos = null;
        produto = new Produto();
        JsfUtil.addSuccessMessage("Produto removido com sucesso!");
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        if (produtos == null) {
            produtos = repositorio.findAll();
        }
        return produtos;
    }

    public void gerarEtiquetas() {
        List<Produto> produtosSemCodBarra = repositorio.getProdutosSemCodigoBarras();

        if (produtosSemCodBarra.isEmpty()) {
            JsfUtil.addErrorMessage("Não existem produtos para etiquetar!");
        } else {
            for (Produto prod : produtosSemCodBarra) {
                prod.setCodigoDeBarras(prod.getId());
                repositorio.edit(prod);
            }
            JsfUtil.addSuccessMessage("Códigos de barras gerados com sucesso!");
        }

    }

    public void iniciar() {
        if (conversacao.isTransient()) {
            conversacao.begin();
        }
    }

    public String finalizar() {
        if (!conversacao.isTransient()) {
            conversacao.end();
        }
        return "pesquisaProduto?faces-redirect=true";
    }

}
