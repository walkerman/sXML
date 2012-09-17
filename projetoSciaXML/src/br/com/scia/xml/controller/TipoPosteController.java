package br.com.scia.xml.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.view.Tipo;
import br.com.scia.xml.entity.view.TipoPoste;
import br.com.scia.xml.util.SciaXMLConstantes;


public class TipoPosteController extends TipoController{

	public TipoPosteController(PrincipalController principal) {
		super(principal);
	}

	@Override
	public void inicializar() {
		if (RepositorioPecas.pecas != null){
			List<String> pecas = new ArrayList<String>();
			pecas.addAll(RepositorioPecas.pecas.keySet());
			Collections.sort(pecas);
			
			// Iniciando o load de postes
			this.principal.tiposPoste = new ArrayList<Tipo>();
			int index = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLConstantes.KIP025R1) || 
					peca.startsWith(SciaXMLConstantes.KIP050R1) || 
					peca.startsWith(SciaXMLConstantes.KIP100R2) || 
					peca.startsWith(SciaXMLConstantes.KIP150R3) || 
					peca.startsWith(SciaXMLConstantes.KIP200R4) ||
					peca.startsWith(SciaXMLConstantes.KIP300R6))
				{
					TipoPoste t = new TipoPoste(peca);
					t.setTableReference(this.principal.postes);
					t.setOrdem(String.valueOf(index));
					this.principal.tiposPoste.add(t);
					index++;
				}
			}
			
			if (this.principal.tiposPoste.size() > 0){
				this.principal.postes.getItems().addAll(this.principal.tiposPoste);
			} 			
		}
		
	}

	@Override
	public void carregar() {}

	
}
