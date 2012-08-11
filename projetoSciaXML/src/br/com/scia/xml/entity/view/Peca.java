package br.com.scia.xml.entity.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias ("peca")
public class Peca {
	
	private String id;
	private String name;
	private String tipo;
	private Integer noInicial;
	private Integer noFinal;
	private Double comprimentoPecaX;
	private Double comprimentoPecaY;
	private Double comprimentoPecaZ;
		
	public Peca() {
		
		super();
		this.comprimentoPecaX = 0.0;
		this.comprimentoPecaY = 0.0;
		this.comprimentoPecaZ = 0.0;
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
	public Double getComprimentoPecaX() {
		return comprimentoPecaX;
	}
	public void setComprimentoPecaX(Double comprimentoPecaX) {
		this.comprimentoPecaX = comprimentoPecaX;
	}
	public Double getComprimentoPecaY() {
		return comprimentoPecaY;
	}
	public void setComprimentoPecaY(Double comprimentoPecaY) {
		this.comprimentoPecaY = comprimentoPecaY;
	}
	public Double getComprimentoPecaZ() {
		return comprimentoPecaZ;
	}
	public void setComprimentoPecaZ(Double comprimentoPecaZ) {
		this.comprimentoPecaZ = comprimentoPecaZ;
	}
}
