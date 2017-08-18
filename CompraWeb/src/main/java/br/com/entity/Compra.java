package br.com.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@SequenceGenerator(name = "SEQ_COMP", sequenceName = "COMP_SEQ", initialValue = 1, allocationSize=1000)
public class Compra implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMP")
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;
	
	private int idTrasacao;
	private double vlProduto;
	private int qtdTotal;
	@SuppressWarnings("unused")
	private double vlTotal;
	private String obs;
	
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
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public double getVlProduto() {
		return vlProduto;
	}
	public void setVlProduto(double vlProduto) {
		this.vlProduto = vlProduto;
	}
	public int getQtdTotal() {
		return qtdTotal;
	}
	public void setQtdTotal(int qtdTotal) {
		this.qtdTotal = qtdTotal;
	}
	public double getVlTotal() {
		return vlTotal = this.qtdTotal * this.vlProduto;
	}
	public void setVlTotal(double vlTotal) {
		this.vlTotal = vlTotal;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
