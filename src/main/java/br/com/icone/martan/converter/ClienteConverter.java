/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.converter;

import br.com.icone.martan.modelo.Cliente;
import br.com.icone.martan.modelo.repositorio.ClienteFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gleywson
 */
@Named(value = "clienteConverter")
public class ClienteConverter implements Converter {

    @Inject
    private ClienteFacade repositorio;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Cliente cliente = null;
        if (value != null) {
            Long id = new Long(value);
            cliente = repositorio.find(id);
        }
        return cliente;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Cliente) value).getId().toString();
        } else {
            return "";
        }
    }
    
}
