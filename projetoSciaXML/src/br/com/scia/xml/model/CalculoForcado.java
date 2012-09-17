package br.com.scia.xml.model;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.view.Coordenada;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.util.Identificadores;
import br.com.scia.xml.util.SciaXMLConstantes;

public class CalculoForcado {
	
	private SumarioDados sumarioDados;
	
	public CalculoForcado() {
		this.sumarioDados = Calculo.dados;
	}		
	
	public void defineForcado(Double eixoX, Double eixoY, Double eixoZ){

		Coordenada coordenadaForcado1 = new Coordenada();
		Coordenada coordenadaDetForcado1 = new Coordenada();
		Coordenada coordenadaTopoForcado1 = new Coordenada();		
		Coordenada coordenadaForcado2 = new Coordenada();
		Coordenada coordenadaDetForcado2 = new Coordenada();
		Coordenada coordenadaTopoForcado2 = new Coordenada();
		Double altura = 0.0;
		Double alturaForcado = 0.0;

		coordenadaForcado1.setId(Identificadores.getIdentificadorNo().toString());
		coordenadaForcado1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenadaForcado1.getId()));					
		coordenadaForcado1.setX(eixoX.doubleValue());
		coordenadaForcado1.setY(eixoY.doubleValue());
		coordenadaForcado1.setZ(eixoZ.doubleValue());

		altura = eixoZ.doubleValue() + RepositorioPecas.pecas.get(sumarioDados.getForcado().getTipo()).getComprimentoZ();
		alturaForcado = RepositorioPecas.pecas.get(sumarioDados.getForcado().getTipo()).getComprimentoZ();

		coordenadaForcado2.setId(Identificadores.getIdentificadorNo().toString());
		coordenadaForcado2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenadaForcado2.getId()));					
		coordenadaForcado2.setX(eixoX.doubleValue());
		coordenadaForcado2.setY(eixoY.doubleValue());
		coordenadaForcado2.setZ(altura);

		coordenadaDetForcado1.setId(Identificadores.getIdentificadorNo().toString());
		coordenadaDetForcado1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenadaDetForcado1.getId()));					
		coordenadaDetForcado1.setX(eixoX.doubleValue() - SciaXMLConstantes.METADE_COMPRIMENTO_DET_FORCADO);
		coordenadaDetForcado1.setY(eixoY.doubleValue());
		coordenadaDetForcado1.setZ(altura - (alturaForcado/2.0));

		coordenadaDetForcado2.setId(Identificadores.getIdentificadorNo().toString());
		coordenadaDetForcado2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenadaDetForcado2.getId()));					
		coordenadaDetForcado2.setX(eixoX.doubleValue() + SciaXMLConstantes.METADE_COMPRIMENTO_DET_FORCADO);
		coordenadaDetForcado2.setY(eixoY.doubleValue());
		coordenadaDetForcado2.setZ(altura - (alturaForcado/2.0));

		coordenadaTopoForcado1.setId(Identificadores.getIdentificadorNo().toString());
		coordenadaTopoForcado1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenadaTopoForcado1.getId()));					
		coordenadaTopoForcado1.setX(eixoX.doubleValue() - SciaXMLConstantes.METADE_COMPRIMENTO_TOPO_FORCADO);
		coordenadaTopoForcado1.setY(eixoY.doubleValue());
		coordenadaTopoForcado1.setZ(altura);

		coordenadaTopoForcado2.setId(Identificadores.getIdentificadorNo().toString());
		coordenadaTopoForcado2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenadaTopoForcado2.getId()));					
		coordenadaTopoForcado2.setX(eixoX.doubleValue() + SciaXMLConstantes.METADE_COMPRIMENTO_TOPO_FORCADO);
		coordenadaTopoForcado2.setY(eixoY.doubleValue());
		coordenadaTopoForcado2.setZ(altura);

		Peca forcado1 = new Peca();
		forcado1.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		forcado1.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(forcado1.getId()));
		forcado1.setTipo(sumarioDados.getForcado().getTipo());
		forcado1.setNoInicial(coordenadaForcado1);
		forcado1.setNoFinal(coordenadaForcado2);

		Peca detforcado = new Peca();
		detforcado.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		detforcado.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(detforcado.getId()));
		detforcado.setTipo(SciaXMLConstantes.DET_FORCADO);
		detforcado.setNoInicial(coordenadaDetForcado1);
		detforcado.setNoFinal(coordenadaDetForcado2); 

		Peca topoforcado = new Peca();
		topoforcado.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		topoforcado.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(topoforcado.getId()));
		topoforcado.setTipo(SciaXMLConstantes.TOPO_FORCADO);
		topoforcado.setNoInicial(coordenadaTopoForcado1);
		topoforcado.setNoFinal(coordenadaTopoForcado2); 

		this.sumarioDados.getListaDeNos().add(coordenadaForcado1);
		this.sumarioDados.getListaDeNos().add(coordenadaForcado2);
		this.sumarioDados.getListaDeNos().add(coordenadaDetForcado1);
		this.sumarioDados.getListaDeNos().add(coordenadaDetForcado2);
		this.sumarioDados.getListaDeNos().add(coordenadaTopoForcado1);
		this.sumarioDados.getListaDeNos().add(coordenadaTopoForcado2);

		this.sumarioDados.getPecasFinais().add(forcado1);
		this.sumarioDados.getPecasFinais().add(detforcado);
		this.sumarioDados.getPecasFinais().add(topoforcado);
	}

}
