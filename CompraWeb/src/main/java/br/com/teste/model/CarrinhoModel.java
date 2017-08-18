package br.com.teste.model;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.teste.entity.Carrinho;
import br.com.teste.repository.CarrinhoRepository;
import br.com.teste.repository.CompraRepository;

import java.util.List;

@ApplicationScoped
public class CarrinhoModel {
   
    @Inject
    private CarrinhoRepository carrinhoRepository;
    
    @Inject
    private CompraRepository compraRepository;

    /**
     * Lista um produto pelo Id, se tiver cadastrado retorna produto, se não existir retorna null
     * @param id
     * @return
     */
    public Carrinho listUm(long id) {
        return carrinhoRepository.getEm().find(Carrinho.class, id);
    }

    /**
     * Retorna uma lista de 12 produto
     * Parâmetro posicao, foi colocado para realizar à consulta do ultimo item da lista já consultado
     * @return
     * @param posicao
     */
    public List<Carrinho> listTodos(int posicao) {
        CriteriaBuilder cb = carrinhoRepository.getEm().getCriteriaBuilder();
        CriteriaQuery<Carrinho> criteria = cb.createQuery(Carrinho.class);
        Root<Carrinho> rootProd = criteria.from(Carrinho.class);
        criteria.select(rootProd).orderBy(cb.asc(rootProd.get("id")));
        return carrinhoRepository.getEm().createQuery(criteria).setMaxResults(12).setFirstResult(posicao).getResultList();
    }
    
    /**
     * Retornar lista do carrinho de compra pela transação
     * @param valor
     * @return
     */
    public List<Carrinho> listCarIdTrasacao(int valor) {
    	CriteriaBuilder cb = carrinhoRepository.getEm().getCriteriaBuilder();
    	CriteriaQuery<Carrinho> criteria = cb.createQuery(Carrinho.class);
    	Root<Carrinho> rootProd = criteria.from(Carrinho.class);
    	criteria.where(cb.equal(rootProd.<Integer>get("idTrasacao"), valor));
    	return carrinhoRepository.getEm().createQuery(criteria).getResultList();
    }

    /**
     * retorna produtoRepository
     * @return
     */
    public CarrinhoRepository getCarrinhoRepository() {
    	return carrinhoRepository;
    }
	
	/**
	 * retorna compraRepository
	 * @return
	 */
	public CompraRepository getCompraRepository() {
		return compraRepository;
	}


	public void setCarrinhoRepository(CarrinhoRepository carrinhoRepository) {
		this.carrinhoRepository = carrinhoRepository;
	}
	
	
    
}
