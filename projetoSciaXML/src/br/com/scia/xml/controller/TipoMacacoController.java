package br.com.scia.xml.controller;

import java.util.ArrayList;
import java.util.Set;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.view.Tipo;
import br.com.scia.xml.util.SciaXMLContantes;


public class TipoMacacoController extends TipoController{

	public TipoMacacoController(PrincipalController principal) {
		super(principal);
	}

	@Override
	public void inicializar() {
		if (RepositorioPecas.pecas != null){
			Set<String> pecas = RepositorioPecas.pecas.keySet();
			
			// Iniciando o load de poste especiais
			this.principal.tiposForcados = new ArrayList<Tipo>();
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.MACACO))
				{
					this.principal.macacos.getItems().add(peca);
				}
			}
		}		
	}

	@Override
	public void carregar() {
		// TODO Auto-generated method stub
		
	}

	
}
