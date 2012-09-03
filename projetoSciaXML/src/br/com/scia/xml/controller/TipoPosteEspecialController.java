package br.com.scia.xml.controller;

import java.util.ArrayList;
import java.util.Set;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.view.Tipo;
import br.com.scia.xml.entity.view.TipoPoste;
import br.com.scia.xml.util.SciaXMLContantes;


public class TipoPosteEspecialController extends TipoController{

	public TipoPosteEspecialController(PrincipalController principal) {
		super(principal);
	}

	@Override
	public void inicializar() {
		if (RepositorioPecas.pecas != null){
			Set<String> pecas = RepositorioPecas.pecas.keySet();
			
			// Iniciando o load de postes especiais
			this.principal.tiposPosteEspecial = new ArrayList<Tipo>();
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.KIP025RE) || 
					peca.startsWith(SciaXMLContantes.KIP037R1) || 
					peca.startsWith(SciaXMLContantes.KIP037R2) || 
					peca.startsWith(SciaXMLContantes.KIP050RE) || 
					peca.startsWith(SciaXMLContantes.KIP070R2))
				{
					TipoPoste t = new TipoPoste(peca);
					this.principal.postesEspeciais.getItems().add(t.getItem());
					this.principal.tiposPosteEspecial.add(t);
				}
			}
		}		
	}

	@Override
	public void carregar() {
		// TODO Auto-generated method stub
		
	}

	
}
