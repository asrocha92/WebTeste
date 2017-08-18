package br.com.repository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.entity.Carrinho;

import java.util.logging.Logger;

/**
 * Classe para RegistrarProduto
 * @author Alex Santos Rocha
 */
@Stateless
public class CarrinhoRepository {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;
    
    /**
     * Gravar produto
     * @param p
     * @throws Exception
     */
    public void gravar(Carrinho carrinho) throws Exception {
		em.merge(carrinho);
		em.flush();
		log.info("Produto do Carrinho armazenado.");
    }
    
    /**
     * Se quantidade estiver zerada remove item do carrinho
     * @param carrinho
     */
    public void remover(Carrinho carrinho) {
		em.remove(carrinho);
		em.flush();
    	log.info("Produto removido do Carrinho.");
    }
    
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

 
}
