/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Categoria;
import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.repositorio.CategoriaFacade;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
import br.com.icone.martan.util.JsfUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "geraProdutoController")
@ViewScoped
public class GeraProdutoController implements Serializable{

    private Produto produto;
    @Inject
    private CategoriaFacade categoriaFacade;
    @Inject
    private ProdutoFacade produtoFacade;
    
    public GeraProdutoController() {
        this.produto = new Produto();
    }
    
    public void gerarProdutos() {
        Categoria categoria = categoriaFacade.find(new Long(1));
        this.produto = new Produto();
        for (int i = 0; i < 1000; i++) {
            produto.setCategoria(categoria);
            produto.setDescricao("Produto " + (i+1));
            produto.setEstoqueMinimo(5);
            produto.setEstoqueMaximo(10);
            produto.setPercentualLucro(new BigDecimal("0"));
            produto.setValorCusto(new BigDecimal("150"));
            produto.setValorVenda(new BigDecimal("150"));
            produtoFacade.create(produto);
        }
        JsfUtil.addSuccessMessage("Geração feita com sucesso!");
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
    
}
