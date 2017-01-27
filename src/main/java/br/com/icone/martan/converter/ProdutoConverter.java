/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.converter;

import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gleywson
 */
@Named(value = "produtoConverter")
public class ProdutoConverter implements Converter {
    @Inject
    private ProdutoFacade repositorio;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Produto produto = null;
        if (value != null) {
            Long id = new Long(value);
            produto = repositorio.find(id);
        }
        return produto;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Produto) value).getId().toString();
        } else {
            return "";
        }
    }
    
}
