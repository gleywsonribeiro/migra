/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.converter;

import br.com.icone.martan.modelo.Usuario;
import br.com.icone.martan.modelo.repositorio.UsuarioFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gleywson
 */
@Named(value = "usuarioConverter")
public class UsuarioConverter implements Converter {
    @Inject
    private UsuarioFacade repositorio;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Usuario usuario = null;
        if (value != null) {
            Long id = new Long(value);
            usuario = repositorio.find(id);
        }
        return usuario;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Usuario) value).getId().toString();
        } else {
            return "";
        }
    }
    
}
