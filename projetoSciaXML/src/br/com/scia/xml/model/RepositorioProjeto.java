package br.com.scia.xml.model;

import br.com.scia.xml.entity.view.SumarioDados;

public class RepositorioProjeto {

	public static SumarioDados projeto;
	
	static{
		if (RepositorioProjeto.projeto == null)
			projeto = new SumarioDados();
	}
}
