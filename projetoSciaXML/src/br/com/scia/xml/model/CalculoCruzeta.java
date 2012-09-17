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
			
			coordenada4.setId( String.valueOf(Identificadores.getIdentificadorNo().toString()));
			coordenada4.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada4.getId()));					
			coordenada4.setX((eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML) + (SciaXMLConstantes.TAMANHO_HORIZONTAL_CRUZETA / 2));
			coordenada4.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			
			if (tipoCruzeta.equalsIgnoreCase(SciaXMLConstantes.CRUZETA)){
				coordenada3.setZ(altura + SciaXMLConstantes.ALTURA_CRUZETA_HORIZONTAL);
				coordenada4.setZ(altura + SciaXMLConstantes.ALTURA_CRUZETA_HORIZONTAL);		
			}
			
			if (tipoCruzeta.equalsIgnoreCase(SciaXMLConstantes.CRUZETA_INVERTIDA)){
				coordenada3.setZ(altura + alturaCruzeta - SciaXMLConstantes.ALTURA_CRUZETA_HORIZONTAL);
				coordenada4.setZ(altura + alturaCruzeta - SciaXMLConstantes.ALTURA_CRUZETA_HORIZONTAL);		
			}	

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
			
			CalculoForcado forcado = new CalculoForcado();
			forcado.defineForcado(eixoX/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, eixoY/SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, coordenada2.getZ());

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
		//TODO Atualmente só resgata altura da viga esqueda. A tela perimite inclusão de mais de 2 Cruzetas.
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

			coordenada4.setId( String.valueOf(Identificadores.getIdentificadorNo().toString()));
			coordenada4.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada4.getId()));					
			coordenada4.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
			coordenada4.setY( (eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML) + (SciaXMLConstantes.TAMANHO_HORIZONTAL_CRUZETA / 2));
	
			if (tipoCruzeta.equalsIgnoreCase(SciaXMLConstantes.CRUZETA)){
				coordenada3.setZ(altura + SciaXMLConstantes.ALTURA_CRUZETA_HORIZONTAL);
				coordenada4.setZ(altura + SciaXMLConstantes.ALTURA_CRUZETA_HORIZONTAL);		
			}
			
			if (tipoCruzeta.equalsIgnoreCase(SciaXMLConstantes.CRUZETA_INVERTIDA)){
				coordenada3.setZ(altura + alturaCruzeta - SciaXMLConstantes.ALTURA_CRUZETA_HORIZONTAL);
				coordenada4.setZ(altura + alturaCruzeta - SciaXMLConstantes.ALTURA_CRUZETA_HORIZONTAL);		
			}

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
			
			CalculoForcado forcado = new CalculoForcado();
			forcado.defineForcado(eixoX/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, eixoY/SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, coordenada2.getZ());

			if (eixoX == tamanhoTotalEixo)
				processouUltimoPonto = true;

			if (eixoX+Double.parseDouble(sumarioDados.getDistanciaCruzetasX()) > tamanhoTotalEixo)
				eixoX = tamanhoTotalEixo;			

			if (eixoX+Double.parseDouble(sumarioDados.getDistanciaCruzetasX()) <= tamanhoTotalEixo)
				eixoX += Double.parseDouble(sumarioDados.getDistanciaCruzetasX());

		}
	}
}
