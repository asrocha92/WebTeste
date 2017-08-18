package br.com.teste.util;

/**
 * Criada Enum para preencher produtos Iniciais
 * @author Alex Santo Rocha
 *
 */
public enum ProdutoUtil {
	
	T1("Nike",       10, 150.0, "Lançamento", "t1", 1),
	T2("Adidas",      2, 250.0, "Lançamento", "t2", 1),
	T3("Vibe",        6, 150.0, "Lançamento", "t3", 1),
	T4("Olimpikus",   5, 300.0, "Lançamento", "t4", 1),
	T5("Andru",       5, 115.0, "Lançamento", "t5", 1),
	T6("Al Merrick", 10,  70.0, "Lançamento", "t6", 1),
	B1("Blusa M",     3,  95.0, "Lançamento", "b1", 2),
	B2("Blusa F",     3,  75.0, "Lançamento", "b2", 2),
	B3("Blusa F",     3,  75.0, "Lançamento", "b3", 2),
	BO1("Boné",       3,  35.0, "Lançamento", "bo1",3),
	BO2("Boné",       3,  25.0, "Lançamento", "bo2",3),
	BO3("Boné",       3,  31.0, "Lançamento", "bo3",3),
	O1("Óculos",      3, 200.0, "Lançamento", "o1", 4),
	O2("Óculos",      3, 150.0, "Lançamento", "o2", 4),
	O3("Óculos",      3, 120.0, "Lançamento", "o3", 4);
	
	
	private String descricao;
	private int quatidade;
	private double valor;
	private String obs;
	private String nFoto;
	private int gradeProd;
	
	private ProdutoUtil(String descricao, int quatidade, double valor, String obs, String nfoto,int gradeProd) {
		this.descricao = descricao;
		this.quatidade = quatidade;
		this.valor = valor;
		this.obs = obs;
		this.nFoto = nfoto;
		this.gradeProd = gradeProd;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getQuatidade() {
		return quatidade;
	}
	public void setQuatidade(int quatidade) {
		this.quatidade = quatidade;
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
