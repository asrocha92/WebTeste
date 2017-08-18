package br.com.teste.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@SequenceGenerator(name = "SEQ_CAR", sequenceName = "CAR_SEQ", initialValue = 1, allocationSize=100)
public class Carrinho implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAR")
	private long id;
	
	private int idTrasacao;

	private double total = 0.0;
	
	private int qtd;
	
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;
	
	@Transient
	private List<Carrinho> carrinho;

	public Carrinho addItem(Produto produto) {
		getCarrinho();
		for (Carrinho i : this.carrinho) {
			if (i.getProduto().getId() == produto.getId()){
				i.setQtd(i.getQtd() + 1);
				if (i.getQtd() <= produto.getQuantidade()){
					i.setTotal(i.getTotal() + produto.getValor());
					return i;
				} else {
					return null;
				}
			}
		}
		
		this.qtd = 1;
		this.produto = produto;			
		this.total = this.total + produto.getValor();
		return this;
	}
	
	public Carrinho removeItem(Produto produto) {
		getCarrinho();
		for (Carrinho i : this.carrinho) {
			if (i.getProduto().getId() == produto.getId()){
				i.setQtd(i.getQtd() - 1);
				i.setTotal(i.getTotal() - produto.getValor());
				return i;
			}
		}
		return null;
	}
	
	public Carrinho atualizarItem(Produto p,int qtd) {
		getCarrinho();
		for (Carrinho i : this.carrinho) {
			if (i.getProduto().getId() == p.getId()){
				i.setQtd(qtd);
				i.setTotal(qtd * p.getValor());
				return i;
			}
		}
		return null;
	}
	
	public List<Carrinho> getCarrinho() {
		if (carrinho == null)
			carrinho = new ArrayList<>();
		return carrinho;
	}
	
	public void setCarrinho(List<Carrinho> c) {
		this.carrinho = c;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIdTrasacao() {
		return idTrasacao;
	}

	public void setIdTrasacao(int idTrasacao) {
		this.idTrasacao = idTrasacao;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}