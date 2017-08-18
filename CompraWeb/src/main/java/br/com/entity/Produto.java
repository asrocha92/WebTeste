package br.com.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@SequenceGenerator(name = "SEQ_PROD", sequenceName = "PROD_SEQ", initialValue = 1, allocationSize=100)
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROD")
	private long id;
	
	private String descricao;
	private int quantidade;
	private double valor;
	private String obs;
	private String nFoto;
	private int gradeProd;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quatidade) {
		this.quantidade = quatidade;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
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
	public String getnFoto() {
		return nFoto;
	}
	public void setnFoto(String nFoto) {
		this.nFoto = nFoto;
	}
	public int getGradeProd() {
		return gradeProd;
	}
	public void setGradeProd(int gradeProd) {
		this.gradeProd = gradeProd;
	}
}
