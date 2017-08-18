package br.com.servico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.entity.Carrinho;
import br.com.entity.Compra;
import br.com.entity.Produto;
import br.com.model.CarrinhoModel;
import br.com.model.ProdutoModel;
import br.com.util.Util;

/**
 * Carrinho serviço
 * @author Alex Santos Rocha
 *
 */
@Path("/carrinho")
@RequestScoped
@Stateful
public class CarrinhoService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
    private Logger log;
	
	@Inject
	private ProdutoModel produtoModel;
	
	@Inject
	private CarrinhoModel carrinhoModel;

	Response.ResponseBuilder builder = null;
	
	/**
	 * Adiciona item ao carrinho
	 * @param id
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addCarrinho/{id:[0-9][0-9]*}/{idTrasacao:[0-9][0-9]*}")
	public int addCarrinho(@PathParam("id") long id, @PathParam("idTrasacao") int idTrasacao){
		try {
			log.info("ID da transação: " + idTrasacao);
			int vlTrasacao = idTrasacao;
			if (vlTrasacao <= 0){
				vlTrasacao = getornaTrasacao();
			}
			
			Produto p = produtoModel.listUm(id);
			Carrinho carrinho = new Carrinho();
			
			if (idTrasacao == vlTrasacao){
				carrinho.setCarrinho(carrinhoModel.listCarIdTrasacao(vlTrasacao));
				carrinho = carrinho.addItem(p);
			} else {
				carrinho = carrinho.addItem(p);
			}
			
			if (carrinho != null) {
				carrinho.setIdTrasacao(vlTrasacao);
				carrinhoModel.getCarrinhoRepository().gravar(carrinho);
			}
			return vlTrasacao;
		} catch (Exception e) {
			log.warning("Erro add Item no carrinho!");
			builder = Util.msgRequest(e.getMessage());
			return 0;
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("removeProdCarrinho/{id:[0-9][0-9]*}/{idTrasacao:[0-9][0-9]*}")
	public int removeProdCarrinho(@PathParam("id") long id, @PathParam("idTrasacao") int idTrasacao){
		try {
			log.info("ID da transação: " + idTrasacao);
			
			Produto p = produtoModel.listUm(id);
			Carrinho carrinho = new Carrinho();
			carrinho.setCarrinho(carrinhoModel.listCarIdTrasacao(idTrasacao));
			carrinho = carrinho.removeItem(p);
			carrinho.setProduto(p);
			carrinho.setIdTrasacao(idTrasacao);
			
			if (carrinho.getQtd() > 0){
				carrinhoModel.getCarrinhoRepository().gravar(carrinho);				
			} else {
				carrinhoModel.getCarrinhoRepository().remover(carrinho);
			}
			return idTrasacao;
		} catch (Exception e) {
			log.warning("Erro remover Item do carrinho!");
			builder = Util.msgRequest(e.getMessage());
			return 0;
		}
	}
	
	/**
	 * lista produtos que estão no carrinho
	 * @return
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listProdCarrinho/{idTrasacao:[0-9][0-9]*}")
    public List<Produto> listProdCarrinho(@PathParam("idTrasacao") int idTrasacao) {
    	try {
    		List<Produto> produto = new ArrayList<Produto>();
    		for(Carrinho o: carrinhoModel.listCarIdTrasacao(idTrasacao)){
    			Produto prod = produtoModel.listUm(o.getProduto().getId());
    			Produto p = new Produto();
    			p.setId(prod.getId());
    			p.setGradeProd(prod.getGradeProd());
    			p.setnFoto(prod.getnFoto());
    			p.setDescricao(prod.getDescricao());
    			p.setObs(prod.getObs());
    			p.setQuantidade(o.getQtd());
    			p.setValor(prod.getValor() * o.getQtd());
    			produto.add(p);
    		}    		
    		log.info("Lista produto armazenado no carrinho, pelo ID Trasação.");
    		return produto;
		} catch (Exception e) {
			log.warning("Erro ao retornar produtos armazenados no carrinho!");
			builder = Util.msgRequest(e.getMessage());
			return null;
		}
    }
    
    /**
     * retorna idTrasação
     * @return
     */
    private int getornaTrasacao(){
    	boolean vdd = true;
    	Random gerador = new Random();
    	int idTrasacao = gerador.nextInt(100);
    	while (vdd){
    		if (carrinhoModel.listCarIdTrasacao(idTrasacao).size() == 0){
    			vdd = false;
    		} else {   			
    			idTrasacao = gerador.nextInt(100);
    		}
    	}
    	return idTrasacao;
    }
	
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("finalizarCompra/{idTrasacao:[0-9][0-9]*}")
    public void finalizarCompra( @PathParam("idTrasacao") int idTrasacao){
    	try {
    		log.info("Finalizando Compra!");
	    	for(Carrinho c: carrinhoModel.listCarIdTrasacao(idTrasacao)){
	    		Produto produto = produtoModel.listUm(c.getProduto().getId());
	    		Compra compra = new Compra();
	    		compra.setProduto(produto);
	    		compra.setVlProduto(produto.getValor());
	    		compra.setQtdTotal(c.getQtd());
	    		compra.setIdTrasacao(c.getIdTrasacao());
	    		carrinhoModel.getCompraRepository().gravar(compra);
	    		log.info("Item compra: Produto"+ compra.getProduto().getDescricao() + "; qtd: " + compra.getQtdTotal() + "; Vl Total: " + compra.getVlTotal() +"\n"); 
	    		produto.setQuantidade(produto.getQuantidade() - c.getQtd());
	    		produtoModel.getProdRep().gravar(produto);
	    		carrinhoModel.getCarrinhoRepository().remover(c);
	    	}
	    	log.info("Compra finalizada.");
    	} catch (Exception e) {
    		log.warning("Erro ao Finalizar Compra!");
			builder = Util.msgRequest(e.getMessage());
			return;
    	}
    }
    
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("atulizarQtdProdVenda/{idProduto:[0-9][0-9]*}/{idTrasacao:[0-9][0-9]*}/{qtd:[0-9][0-9]*}")
    public Produto atulizarQtdProdVenda(@PathParam("idProduto") long idProduto, @PathParam("idTrasacao") int idTrasacao, @PathParam("qtd") int qtd){
		try {
			log.info("ID da transação: " + idTrasacao + ". Valida quantidade do produto para Venda.");

			Produto p = produtoModel.listUm(idProduto);
			
			if (p.getQuantidade() >= qtd) {
				Carrinho carrinho = new Carrinho();
				carrinho.setCarrinho(carrinhoModel.listCarIdTrasacao(idTrasacao));
				carrinho = carrinho.atualizarItem(p, qtd);
				carrinho.setIdTrasacao(idTrasacao);
				carrinhoModel.getCarrinhoRepository().gravar(carrinho);
			}
			return p;
		} catch (Exception e) {
			log.warning("Erro ao Finalizar Compra!");
			builder = Util.msgRequest(e.getMessage());
			return null;
		}
    }
}
