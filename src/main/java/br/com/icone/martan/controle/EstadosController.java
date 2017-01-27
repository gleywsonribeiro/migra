/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.controle;

import br.com.icone.martan.modelo.Estado;
import br.com.icone.martan.modelo.repositorio.EstadoFacade;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "estadosController")
@Dependent
public class EstadosController {
 
    @Inject private EstadoFacade repositorio;

    public List<Estado> getEstados() {
        return repositorio.findAll();
    }
    
    
}
