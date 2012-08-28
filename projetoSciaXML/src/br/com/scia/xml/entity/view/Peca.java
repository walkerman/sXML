package br.com.scia.xml.entity.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias ("peca")
public class Peca {
	
	private String id;
	private String name;
	private String tipo;
	private Integer noInicial;
	private Integer noFinal;
	private Double comprimento;
		
	public Peca() {
		
		super();
		this.setComprimento(0.0);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getNoInicial() {
		return noInicial;
	}
	public void setNoInicial(Integer noInicial) {
		this.noInicial = noInicial;
	}
	public Integer getNoFinal() {
		return noFinal;
	}
	public void setNoFinal(Integer noFinal) {
		this.noFinal = noFinal;
	}

	public Double getComprimento() {
		return comprimento;
	}

	public void setComprimento(Double comprimento) {
		this.comprimento = comprimento;
	}
}
