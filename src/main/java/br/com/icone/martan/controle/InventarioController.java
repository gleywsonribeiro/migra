/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
import br.com.icone.martan.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "inventarioController")
@ViewScoped
public class InventarioController implements Serializable {

    private List<Produto> produtos;

    @Inject
    private ProdutoFacade repositorio;
    
    public List<Produto> getProdutos() {
        if(this.produtos == null) {
            this.produtos = repositorio.findAll();
        }
        return this.produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void atualizarEstoque() {
        for (Produto item : produtos) {
            repositorio.edit(item);
        }
        JsfUtil.addSuccessMessage("Inventário finalizado com sucesso!");
    }

}
