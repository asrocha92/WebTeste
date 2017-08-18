package br.com.teste.model;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.teste.entity.Produto;
import br.com.teste.repository.ProdutoRepository;

import java.util.List;

@ApplicationScoped
public class ProdutoModel {
   
    @Inject
    private ProdutoRepository produtoRepository;

    /**
     * Lista um produto pelo Id, se tiver cadastrado retorna produto, se não existir retorna null
     * @param id
     * @return
     */
    public Produto listUm(long id) {
        return produtoRepository.getEm().find(Produto.class, id);
    }

    /**
     * Retorna uma lista de 12 produto
     * Parâmetro posicao, foi colocado para realizar à consulta do ultimo item da lista já consultado
     * @return
     * @param posicao
     */
    public List<Produto> listTodos(int posicao) {
        CriteriaBuilder cb = produtoRepository.getEm().getCriteriaBuilder();
        CriteriaQuery<Produto> criteria = cb.createQuery(Produto.class);
        Root<Produto> rootProd = criteria.from(Produto.class);
        criteria.select(rootProd).orderBy(cb.asc(rootProd.get("descricao")));
        return produtoRepository.getEm().createQuery(criteria).setMaxResults(12).setFirstResult(posicao).getResultList();
    }
    
    /**
     * Retorna uma lista de 9 produto por grade
     * @return
     * @param posicao
     * @param vlGradeProd -> Valor da Grade do Produto
     */
    public List<Produto> listProdgrade(int posicao, int vlGradeProd) {
        CriteriaBuilder cb = produtoRepository.getEm().getCriteriaBuilder();
        CriteriaQuery<Produto> criteria = cb.createQuery(Produto.class);
        Root<Produto> rootProd = criteria.from(Produto.class);
        criteria.where(cb.equal(rootProd.<Integer>get("gradeProd"), vlGradeProd));
        criteria.select(rootProd).where(cb.equal(rootProd.<Integer>get("gradeProd"), vlGradeProd));
        criteria.select(rootProd).orderBy(cb.asc(rootProd.get("descricao")));
        return produtoRepository.getEm().createQuery(criteria).setMaxResults(9).setFirstResult(posicao).getResultList();
    }
    
    /**
     * retorna Instâcia ProdutoRepository
     * @return
     */
	public ProdutoRepository getProdRep() {
		return produtoRepository;
	}
    
}
