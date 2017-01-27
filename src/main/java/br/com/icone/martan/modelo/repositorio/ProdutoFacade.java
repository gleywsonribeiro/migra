/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.martan.modelo.repositorio;

import br.com.icone.martan.modelo.Produto;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gleywson
 */
@Stateless
public class ProdutoFacade extends AbstractFacade<Produto> implements Serializable{

    @PersistenceContext(unitName = "martanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoFacade() {
        super(Produto.class);
    }
    
    public List<Produto> getProdutosSemCodigoBarras() {
        return getEntityManager().createQuery("SELECT produto FROM Produto AS produto WHERE produto.codigoDeBarras IS NULL", Produto.class).getResultList();
    }
    
    public List<Produto> getProdutosPorDescricao(String descricao) { 
        return getEntityManager().createQuery("SELECT p FROM Produto AS p WHERE P.ativo = TRUE AND UPPER(p.descricao) LIKE :descricao", Produto.class)
                .setParameter("descricao", "%" + descricao.toUpperCase() + "%").getResultList();
    }
}
