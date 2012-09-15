package br.com.scia.xml.model;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.exception.CalculoException;
import br.com.scia.xml.entity.view.Coordenada;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.util.Identificadores;
import br.com.scia.xml.util.SciaXMLConstantes;

public class CalculoCruzeta {

	private SumarioDados sumarioDados;

	public CalculoCruzeta() {
		this.sumarioDados = Calculo.dados;
	}	

	public void realizarCalculoEixoX(Double eixoX, Double eixoY, String tipoCruzeta) throws CalculoException {

		Coordenada coordenada1;
		Coordenada coordenada2;
		Coordenada coordenada3;
		Coordenada coordenada4;

		Peca peca1;
		Peca peca2;

		boolean processouUltimoPonto = false;

		Double tamanhoTotalEixo = Double.parseDouble(sumarioDados.getMedidaLageY());

		Double altura = Double.parseDouble(sumarioDados.getCoordenadaZ()) / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML + Double.parseDouble(sumarioDados.getAlturaVigaEsquerdaY()) / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
		Double alturaCruzeta = RepositorioPecas.pecas.get(tipoCruzeta).getComprimentoZ();	

		while (eixoY <= tamanhoTotalEixo && !processouUltimoPonto){

			coordenada1 = new Coordenada();
			coordenada2 = new Coordenada();
			coordenada3 = new Coordenada();
			coordenada4 = new Coordenada();		

			peca1 = new Peca();
			peca2 = new Peca();

			coordenada1.setId(String.valueOf(Identificadores.getIdentificadorNo().toString()));
			coordenada1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada1.getId()));					
			coordenada1.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			coordenada1.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			coordenada1.setZ(altura);

			coordenada2.setId( String.valueOf(Identificadores.getIdentificadorNo().toString()));
			coordenada2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada2.getId()));					
			coordenada2.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			coordenada2.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			coordenada2.setZ(altura + alturaCruzeta);			    

			coordenada3.setId( String.valueOf(Identificadores.getIdentificadorNo().toString()));
			coordenada3.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada3.getId()));					
			coordenada3.setX( (eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML) - (SciaXMLConstantes.TAMANHO_HORIZONTAL_CRUZETA / 2) );
			coordenada3.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			coordenada3.setZ(altura + SciaXMLConstantes.ALTURA_CRUZETA_HORIZONTAL);

			coordenada4.setId( String.valueOf(Identificadores.getIdentificadorNo().toString()));
			coordenada4.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada4.getId()));					
			coordenada4.setX((eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML) + (SciaXMLConstantes.TAMANHO_HORIZONTAL_CRUZETA / 2));
			coordenada4.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			coordenada4.setZ(altura + SciaXMLConstantes.ALTURA_CRUZETA_HORIZONTAL);		

			//DEFINE PRIMEIRA PECA CRUZETA
			peca1.setId(String.valueOf(Identificadores.getIdentificarPecas().toString()));
			peca1.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca1.getId()));
			peca1.setTipo(tipoCruzeta);
			peca1.setNoInicial(coordenada1);
			peca1.setNoFinal(coordenada2);

			//DEFINE SEGUNDA PECA CRUZETA
			peca2.setId(String.valueOf(Identificadores.getIdentificarPecas().toString()));
			peca2.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca2.getId()));
			peca2.setTipo(SciaXMLConstantes.CRUZETA_HORIZONTAL);
			peca2.setNoInicial(coordenada3);
			peca2.setNoFinal(coordenada4);
			
			this.defineForcadoCruzeta(eixoX/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, eixoY/SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, coordenada2.getZ());

			//ADICIONA AS COORDENADAS NA LISTA
			sumarioDados.getListaDeNos().add(coordenada1);
			sumarioDados.getListaDeNos().add(coordenada2);
			sumarioDados.getListaDeNos().add(coordenada3);
			sumarioDados.getListaDeNos().add(coordenada4);

			//ADICIONA AS PECAS NA LISTA
			sumarioDados.getPecasFinais().add(peca1);
			sumarioDados.getPecasFinais().add(peca2);

			if (eixoY == tamanhoTotalEixo)
				processouUltimoPonto = true;

			if (eixoY+Double.parseDouble(sumarioDados.getDistanciaCruzetasY()) > tamanhoTotalEixo)
				eixoY = tamanhoTotalEixo;			

			if (eixoY+Double.parseDouble(sumarioDados.getDistanciaCruzetasY()) <= tamanhoTotalEixo)
				eixoY += Double.parseDouble(sumarioDados.getDistanciaCruzetasY());
		}
	}

	public void realizarCalculoEixoY(Double eixoX, Double eixoY, String tipoCruzeta) throws CalculoException {

		Coordenada coordenada1;
		Coordenada coordenada2;
		Coordenada coordenada3;
		Coordenada coordenada4;

		Peca peca1;
		Peca peca2;

		boolean processouUltimoPonto = false;

		Double tamanhoTotalEixo = Double.parseDouble(sumarioDados.getMedidaLageX());

		Double altura = Double.parseDouble(sumarioDados.getAlturaVigaEsquerdaX()) / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
		Double alturaCruzeta = RepositorioPecas.pecas.get(tipoCruzeta).getComprimentoZ();	

		while (eixoX <= tamanhoTotalEixo && !processouUltimoPonto){

			coordenada1 = new Coordenada();
			coordenada2 = new Coordenada();
			coordenada3 = new Coordenada();
			coordenada4 = new Coordenada();		

			peca1 = new Peca();
			peca2 = new Peca();

			coordenada1.setId(String.valueOf(Identificadores.getIdentificadorNo().toString()));
			coordenada1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada1.getId()));					
			coordenada1.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			coordenada1.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			coordenada1.setZ(altura);

			coordenada2.setId( String.valueOf(Identificadores.getIdentificadorNo().toString()));
			coordenada2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada2.getId()));					
			coordenada2.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			coordenada2.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			coordenada2.setZ(altura + alturaCruzeta);			    

			coordenada3.setId( String.valueOf(Identificadores.getIdentificadorNo().toString()));
			coordenada3.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada3.getId()));					
			coordenada3.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			coordenada3.setY( (eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML) - (SciaXMLConstantes.TAMANHO_HORIZONTAL_CRUZETA / 2));
			coordenada3.setZ(altura + SciaXMLConstantes.ALTURA_CRUZETA_HORIZONTAL);

			coordenada4.setId( String.valueOf(Identificadores.getIdentificadorNo().toString()));
			coordenada4.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada4.getId()));					
			coordenada4.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			coordenada4.setY( (eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML) + (SciaXMLConstantes.TAMANHO_HORIZONTAL_CRUZETA / 2));
			coordenada4.setZ(altura + SciaXMLConstantes.ALTURA_CRUZETA_HORIZONTAL);		

			//DEFINE PRIMEIRA PECA CRUZETA
			peca1.setId(String.valueOf(Identificadores.getIdentificarPecas().toString()));
			peca1.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca1.getId()));
			peca1.setTipo(tipoCruzeta);
			peca1.setNoInicial(coordenada1);
			peca1.setNoFinal(coordenada2);

			//DEFINE SEGUNDA PECA CRUZETA
			peca2.setId(String.valueOf(Identificadores.getIdentificarPecas().toString()));
			peca2.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca2.getId()));
			peca2.setTipo(SciaXMLConstantes.CRUZETA_HORIZONTAL);
			peca2.setNoInicial(coordenada3);
			peca2.setNoFinal(coordenada4);

			//ADICIONA AS COORDENADAS NA LISTA
			sumarioDados.getListaDeNos().add(coordenada1);
			sumarioDados.getListaDeNos().add(coordenada2);
			sumarioDados.getListaDeNos().add(coordenada3);
			sumarioDados.getListaDeNos().add(coordenada4);

			//ADICIONA AS PECAS NA LISTA
			sumarioDados.getPecasFinais().add(peca1);
			sumarioDados.getPecasFinais().add(peca2);
			
			this.defineForcadoCruzeta(eixoX/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, eixoY/SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, coordenada2.getZ());

			if (eixoX == tamanhoTotalEixo)
				processouUltimoPonto = true;

			if (eixoX+Double.parseDouble(sumarioDados.getDistanciaCruzetasX()) > tamanhoTotalEixo)
				eixoX = tamanhoTotalEixo;			

			if (eixoX+Double.parseDouble(sumarioDados.getDistanciaCruzetasX()) <= tamanhoTotalEixo)
				eixoX += Double.parseDouble(sumarioDados.getDistanciaCruzetasX());

		}
	}
	
	public void defineForcadoCruzeta(Double eixoX, Double eixoY, Double eixoZ){
		
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
