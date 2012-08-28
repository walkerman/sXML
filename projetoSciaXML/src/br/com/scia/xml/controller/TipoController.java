package br.com.scia.xml.controller;

public abstract class TipoController {

	PrincipalController principal;
	
	public TipoController(PrincipalController principal) {
		this.principal = principal;
	}
	
	public abstract void inicializar();
	public abstract void carregar();
	
}
