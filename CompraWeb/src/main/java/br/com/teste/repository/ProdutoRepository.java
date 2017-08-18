package br.com.teste.repository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.teste.entity.Produto;

import java.util.logging.Logger;

/**
 * Classe para RegistrarProduto
 * @author Alex Santos Rocha
 */
@Stateless
public class ProdutoRepository {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    /**
     * Gravar produto
     * @param p
     * @throws Exception
     */
    public void gravar(Produto produto) throws Exception {
		em.merge(produto);
		em.flush();
		log.info("Produto " + produto.getDescricao() + ", QTD: "+ produto.getQuantidade() + " registrado ou atulizado.");
    }

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
 
}
