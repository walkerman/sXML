package br.com.scia.xml.controller;

import java.util.ArrayList;
import java.util.Set;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.view.Tipo;
import br.com.scia.xml.entity.view.TipoForcado;
import br.com.scia.xml.util.SciaXMLContantes;

public class TipoForcadoController extends TipoController{

	public TipoForcadoController(PrincipalController principal) {
		super(principal);
	}

	@Override
	public void inicializar() {
		if (RepositorioPecas.pecas != null){
			Set<String> pecas = RepositorioPecas.pecas.keySet();
			
			// Iniciando o load de forcados
			this.principal.tiposForcados = new ArrayList<Tipo>();
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.FORC))
				{
					TipoForcado t = new TipoForcado(peca);
					this.principal.forcados.getItems().add(t.getItem());
					this.principal.forcadosX.getItems().add(t.getItem());
					this.principal.forcadosY.getItems().add(t.getItem());
					this.principal.tiposForcados.add(t);
				}
			}
		}
		
	}

	@Override
	public void carregar() {
		// TODO Auto-generated method stub
		
	}
}
