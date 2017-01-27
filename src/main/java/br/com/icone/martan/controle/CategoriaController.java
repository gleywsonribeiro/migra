/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Categoria;
import br.com.icone.martan.modelo.repositorio.CategoriaFacade;
import br.com.icone.martan.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "categoriaController")
@ViewScoped
public class CategoriaController implements Serializable {

    private Categoria categoria;
    private List<Categoria> categorias;

    @Inject
    private CategoriaFacade repositorio;

    public CategoriaController() {
        categoria = new Categoria();
    }

    public void salvar() {
        if (categoria.getId() == null) {
            repositorio.create(categoria);
        } else {
            repositorio.edit(categoria);
        }
        categoria = new Categoria();
        categorias = null;
    }

    public void remover() {
        try {
            repositorio.remove(categoria);
            JsfUtil.addSuccessMessage("Categoria " + categoria.getNome() + " removida com sucesso!");
            categoria = new Categoria();
            categorias = null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Categoria " + categoria.getNome() + " não pode ser excluída!");
            categoria = new Categoria();
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        if (categorias == null) {
            categorias = repositorio.findAll();
        }
        return categorias;
    }

}
