/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.modelo.repositorio;

import br.com.icone.martan.modelo.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gleywson
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "martanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario getUsuarioPorLogin(String login) {
        Usuario usuario = null;

        try {
            usuario = getEntityManager().createQuery("SELECT user FROM Usuario AS user WHERE user.login = :login", Usuario.class)
                    .setParameter("login", login).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

}
