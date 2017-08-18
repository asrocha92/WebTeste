package br.com.teste.repository;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.teste.entity.Compra;

/**
 * Compra repository utilizado ao finalizar uma compra
 * @author Alex Santos Rocha
 *
 */
@Stateless
public class CompraRepository {
	@Inject
    private Logger log;

    @Inject
    private EntityManager em;
    
    /**
     * Gravar produto
     * @param p
     * @throws Exception
     */
    public void gravar(Compra compra) throws Exception {
		em.merge(compra);
		em.flush();
		log.info("Produto do Carrinho armazenado.");    	
    }
}
