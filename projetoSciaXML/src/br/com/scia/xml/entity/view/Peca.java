package br.com.scia.xml.entity.view;

import br.com.scia.xml.entity.xml.Coordenada;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias ("peca")
public class Peca {
	
	private String id;
	private String name;
	private String tipo;
	private Coordenada noInicial;
	private Coordenada noFinal;
	private Double comprimento;
	
	public Peca() {		
		super();
		this.comprimento = 0.0;
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
	public Coordenada getNoInicial() {
		return noInicial;
	}
	public void setNoInicial(Coordenada noInicial) {
		this.noInicial = noInicial;
	}
	public Coordenada getNoFinal() {
		return noFinal;
	}
	public void setNoFinal(Coordenada noFinal) {
		this.noFinal = noFinal;
	}

	public Double getComprimento() {
		return this.comprimento;
	}

	public void setComprimento(Double comprimento) {
		this.comprimento = comprimento;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Peca [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", noInicial=");
		builder.append(noInicial);
		builder.append(", noFinal=");
		builder.append(noFinal);
		builder.append(", comprimento=");
		builder.append(comprimento);
		builder.append("]\n");
		return builder.toString();
	}
	
	
}
