package br.com.servico;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.entity.Produto;
import br.com.model.ProdutoModel;
import br.com.util.ProdutoUtil;

/**
 * Definindo uma classe Rest para produto
 * @author Alex Santos Rocha
 *
 */
@Path("/produtos")
@RequestScoped
@Stateful
public class ProdutoService {
	
	@Inject
    private Logger log;
    
    @Inject
    private ProdutoModel produtoModel;
    
    Response.ResponseBuilder builder = null;
    
    /**
     * Retorna lista de produto
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listAllProduto")
    public List<Produto> listAllProduto() {
    	try {
    		gravarProdInicial();
    		return produtoModel.listTodos(0);
		} catch (Exception e) {
			log.warning("Erro ao retornar lista de produtos");
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
    	return null;
    }
    
    /**
     * Retorna lista de produto pela sua grade
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listProdutoGrade/{id:[0-9][0-9]*}")
    public List<Produto> listProdutoGrade(@PathParam("id") int gradeProd) {
    	try {
    		gravarProdInicial();
    		return produtoModel.listProdgrade(0, gradeProd);
		} catch (Exception e) {
			log.warning("Erro ao retornar lista de produtos");
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
    	return null;
    }
    
    /**
     * Consulta produto pelo ID
     * @param id
     * @return
     */
    @GET
    @Path("listUmProduto/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto listUmProduto(@PathParam("id") long id){
    	gravarProdInicial();
    	Produto p = produtoModel.listUm(id);
    	if (p == null){
    		throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	return p;
    }
    
    /**
     * Método Posto Utilizado para preencher a lista de produtos
     */
    public void gravarProdInicial(){
    	try {
    		List<Produto> l = produtoModel.listTodos(0);
    		if (l.size() == 0){
				for (ProdutoUtil obj : ProdutoUtil.values()) {
					Produto p = new Produto();
					p.setDescricao(obj.getDescricao());
					p.setQuantidade(obj.getQuatidade());
					p.setValor(obj.getValor());
					p.setObs(obj.getObs());
					p.setnFoto(obj.getnFoto());
					p.setGradeProd(obj.getGradeProd());
					produtoModel.getProdRep().gravar(p);
				}
    		}
		} catch (Exception e) {
			log.warning("Erro ao gravar produtos Inicial");
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
    	
    }
    
}
