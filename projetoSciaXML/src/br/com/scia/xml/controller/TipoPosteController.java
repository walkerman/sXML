package br.com.scia.xml.controller;

import java.util.ArrayList;
import java.util.Set;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.view.Tipo;
import br.com.scia.xml.entity.view.TipoPoste;
import br.com.scia.xml.util.SciaXMLContantes;


public class TipoPosteController extends TipoController{

	public TipoPosteController(PrincipalController principal) {
		super(principal);
	}

	@Override
	public void inicializar() {
		if (RepositorioPecas.pecas != null){
			Set<String> pecas = RepositorioPecas.pecas.keySet();
			
			// Iniciando o load de postes
			this.principal.tiposPoste = new ArrayList<Tipo>();
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.KIP025R1) || 
					peca.startsWith(SciaXMLContantes.KIP050R1) || 
					peca.startsWith(SciaXMLContantes.KIP100R2) || 
					peca.startsWith(SciaXMLContantes.KIP150R3) || 
					peca.startsWith(SciaXMLContantes.KIP200R4) ||
					peca.startsWith(SciaXMLContantes.KIP300R6))
				{
					TipoPoste t = new TipoPoste(peca);
					t.setTableReference(this.principal.postes);
					RepositorioPecas.postes.put(t.getItem(), t);					
					this.principal.tiposPoste.add(t);
				}
			}
			
			if (this.principal.tiposPoste.size() > 0){
				this.principal.postes.getItems().addAll(this.principal.tiposPoste);
			} 			
		}
		
	}

	@Override
	public void carregar() {
		// TODO Auto-generated method stub
		
	}

	
}
