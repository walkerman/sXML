package br.com.scia.xml.model;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.exception.CalculoException;
import br.com.scia.xml.entity.view.Coordenada;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.util.Identificadores;
import br.com.scia.xml.util.SciaXMLConstantes;

public class CalculoEscoras {

	private SumarioDados sumarioDados;	
	private Double eixoX;
	private Double eixoY;

	public CalculoEscoras(SumarioDados dados, Double posicaoEixoX, Double posicaoEixoY){
		this.sumarioDados = dados;
		this.eixoX = posicaoEixoX;
		this.eixoY = posicaoEixoY;
	}

	private Double defineAlturaEscoraFlexivel(String tipoEscora) throws CalculoException{		

		Double alturaTotalEscora = 0.0;
		Double alturaFlexivelEscora = 0.0;
		Double alturaBaseEscora = 0.0;
		Double peDireito = Double.parseDouble(sumarioDados.getPeDireito());
		Double espessuraConpensado = Double.parseDouble(sumarioDados.getEspessuraCompensado());
		Double espessuraLaje = Double.parseDouble(sumarioDados.getEspessura());
		Double alturaVigaPrincipal = CalculoVigasPrincipais.getAlturaViga() * 100.0;
		Double alturaVigaSecundaria = CalculoVigasSecundarias.getAlturaViga() * 100.0;
		Double alturaForcado = sumarioDados.getForcadoX().getComprimento() * 100.0;
	
		alturaBaseEscora = RepositorioPecas.pecas.get(tipoEscora).getComprimentoZ() ;
		alturaTotalEscora = (peDireito - espessuraConpensado - espessuraLaje - alturaVigaPrincipal - alturaVigaSecundaria - alturaForcado) / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
		alturaFlexivelEscora = alturaTotalEscora - alturaBaseEscora;

		switch (tipoEscora) {
			case SciaXMLConstantes.TIPO_ESCORA_A:
				if (alturaFlexivelEscora < SciaXMLConstantes.TAMANHO_MIN_ESCORA_A || alturaFlexivelEscora > SciaXMLConstantes.TAMANHO_MAX_ESCORA_A)
					throw new CalculoException(SciaXMLConstantes.ESCORA_IMCOMPATIVEL);				
				break;
	
			case SciaXMLConstantes.TIPO_ESCORA_B:
				if (alturaFlexivelEscora < SciaXMLConstantes.TAMANHO_MIN_ESCORA_B || alturaFlexivelEscora > SciaXMLConstantes.TAMANHO_MAX_ESCORA_B)
					throw new CalculoException(SciaXMLConstantes.ESCORA_IMCOMPATIVEL);		
				break;
	
			case SciaXMLConstantes.TIPO_ESCORA_C:
				if (alturaFlexivelEscora < SciaXMLConstantes.TAMANHO_MIN_ESCORA_C || alturaFlexivelEscora > SciaXMLConstantes.TAMANHO_MAX_ESCORA_C)
					throw new CalculoException(SciaXMLConstantes.ESCORA_IMCOMPATIVEL);		
				break;
	
			case SciaXMLConstantes.TIPO_ESCORA_D:
				if (alturaFlexivelEscora < SciaXMLConstantes.TAMANHO_MIN_ESCORA_D || alturaFlexivelEscora > SciaXMLConstantes.TAMANHO_MAX_ESCORA_D)
					throw new CalculoException(SciaXMLConstantes.ESCORA_IMCOMPATIVEL);		
				break;
	
			default:
				throw new CalculoException(SciaXMLConstantes.ESCORA_NAO_CADASTRADA);
		}

		return alturaFlexivelEscora;
	}
	
	private void defineBaseEscora(){
		
		Coordenada coordenada1 = new Coordenada();
		Coordenada coordenada2 = new Coordenada();
		
		Peca peca1 = new Peca();
		
		double altura = Double.parseDouble(this.sumarioDados.getCoordenadaZ())/SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
		
		Double pontoInicialBase = eixoX - (RepositorioPecas.pecas.get(SciaXMLConstantes.BASE_ESCORA).getComprimentoY() / 2);
		Double pontoFinalBase = eixoX + (RepositorioPecas.pecas.get(SciaXMLConstantes.BASE_ESCORA).getComprimentoY() / 2);
		
		coordenada1.setId(String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada1.getId()));					
		coordenada1.setX(pontoInicialBase / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada1.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada1.setZ(altura);
		
		coordenada2.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada2.getId()));					
		coordenada2.setX(pontoFinalBase / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada2.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada2.setZ(altura);		
		
		//DEFINE PRIMEIRA PECA ESCORA
		peca1.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		peca1.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca1.getId()));
		peca1.setTipo(SciaXMLConstantes.BASE_ESCORA);
		peca1.setNoInicial(coordenada1);
		peca1.setNoFinal(coordenada2);
		
		RepositorioPecas.listaEscoras.add(peca1);
		
		//ADICIONA COORDENADAS NA LISTA
		sumarioDados.getListaDeNos().add(coordenada1);
		sumarioDados.getListaDeNos().add(coordenada2);
		
		//ADICIONA AS PECAS NA LISTA
		sumarioDados.getPecasFinais().add(peca1);
		
	}

	public void defineEscoraNoEixoX(String tipoPeca1, String tipoPeca2) throws CalculoException{

		Coordenada coordenada1 = new Coordenada();
		Coordenada coordenada2 = new Coordenada();
		Coordenada coordenada3 = new Coordenada();
		Coordenada coordenada4 = new Coordenada();		
		Coordenada coordenada5 = new Coordenada();
		Coordenada coordenada6 = new Coordenada();

		Peca peca1 = new Peca();
		Peca peca2 = new Peca();
		Peca peca3 = new Peca();
		Peca peca4 = new Peca();
		
		defineBaseEscora();
		
		double altura = Double.parseDouble(this.sumarioDados.getCoordenadaZ())/SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
		
		coordenada1.setId(String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada1.getId()));					
		coordenada1.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada1.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada1.setZ(altura);
		
		coordenada2.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada2.getId()));					
		coordenada2.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada2.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada2.setZ(altura + RepositorioPecas.pecas.get(tipoPeca1).getComprimentoZ());
				
		Double alturaEscoraFlexivel = defineAlturaEscoraFlexivel(tipoPeca1);
		
		coordenada3.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada3.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada3.getId()));					
		coordenada3.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada3.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada3.setZ(coordenada2.getZ() + alturaEscoraFlexivel);	
		
		defineForcadoEscora(eixoX/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, eixoY/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, coordenada3.getZ());
		
		eixoY = eixoY + (RepositorioPecas.pecas.get(tipoPeca2).getComprimentoX() * 100);
		
		//DEFINE PRIMEIRA PECA ESCORA
		peca1.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		peca1.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca1.getId()));
		peca1.setTipo(tipoPeca1);
		peca1.setNoInicial(coordenada1);
		peca1.setNoFinal(coordenada2);
		
		//DEFINE PRIMEIRA PECA ESCORA
		peca2.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		peca2.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca2.getId()));
		peca2.setTipo(SciaXMLConstantes.TOPO_ESCORA);
		peca2.setNoInicial(coordenada2);
		peca2.setNoFinal(coordenada3);
		
		sumarioDados.getListaDeNos().add(coordenada1);
		sumarioDados.getListaDeNos().add(coordenada2);
		sumarioDados.getListaDeNos().add(coordenada3);
		
		//ADICIONA AS PECAS NA LISTA
		sumarioDados.getPecasFinais().add(peca1);
		sumarioDados.getPecasFinais().add(peca2);
		
		defineBaseEscora();

		coordenada4.setId(String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada4.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada4.getId()));					
		coordenada4.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada4.setY((eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML));
		coordenada4.setZ(altura);
		
		coordenada5.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada5.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada5.getId()));					
		coordenada5.setX((eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML));
		coordenada5.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada5.setZ(altura + RepositorioPecas.pecas.get(tipoPeca1).getComprimentoZ());
		
		coordenada6.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada6.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada6.getId()));					
		coordenada6.setX((eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML));
		coordenada6.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada6.setZ(coordenada5.getZ() + alturaEscoraFlexivel);
		
		defineForcadoEscora(eixoX/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, eixoY/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, coordenada6.getZ());
		
		//DEFINE SEGUNDA PECA ESCORA
		peca3.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		peca3.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca3.getId()));
		peca3.setTipo(tipoPeca1);
		peca3.setNoInicial(coordenada4);
		peca3.setNoFinal(coordenada5);
		
		//DEFINE SEGUNDA PECA ESCORA
		peca4.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		peca4.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca4.getId()));
		peca4.setTipo(SciaXMLConstantes.TOPO_ESCORA);
		peca4.setNoInicial(coordenada5);
		peca4.setNoFinal(coordenada6);
		
		//ADICIONA COORDENADAS NA LISTA
		sumarioDados.getListaDeNos().add(coordenada4);
		sumarioDados.getListaDeNos().add(coordenada5);
		sumarioDados.getListaDeNos().add(coordenada6);

		sumarioDados.getPecasFinais().add(peca3);
		sumarioDados.getPecasFinais().add(peca4);

		atualizaDadosClasseCalculo();
	}


	public void defineEscoraNoEixoY(String tipoPeca1, String tipoPeca2) throws CalculoException{

		Coordenada coordenada1 = new Coordenada();
		Coordenada coordenada2 = new Coordenada();
		Coordenada coordenada3 = new Coordenada();
		Coordenada coordenada4 = new Coordenada();		
		Coordenada coordenada5 = new Coordenada();
		Coordenada coordenada6 = new Coordenada();

		Peca peca1 = new Peca();
		Peca peca2 = new Peca();
		Peca peca3 = new Peca();
		Peca peca4 = new Peca();
		
		double altura = Double.parseDouble(this.sumarioDados.getCoordenadaZ())/SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
		
		defineBaseEscora();

		coordenada1.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada1.getId()));					
		coordenada1.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada1.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada1.setZ(altura);

		coordenada2.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada2.getId()));					
		coordenada2.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada2.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada2.setZ(altura + RepositorioPecas.pecas.get(tipoPeca2).getComprimentoZ());
		
		Double alturaEscoraFlexivel = defineAlturaEscoraFlexivel(tipoPeca2);
		
		coordenada3.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada3.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada3.getId()));					
		coordenada3.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada3.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada3.setZ(coordenada2.getZ() + alturaEscoraFlexivel);	
		
		defineForcadoEscora(eixoX/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, eixoY/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, coordenada3.getZ());
		
		//DEFINE PRIMEIRA PECA ESCORA
		peca1.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		peca1.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca1.getId()));
		peca1.setTipo(tipoPeca2);
		peca1.setNoInicial(coordenada1);
		peca1.setNoFinal(coordenada2);
		
		//DEFINE PRIMEIRA PECA ESCORA
		peca2.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		peca2.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca2.getId()));
		peca2.setTipo(SciaXMLConstantes.TOPO_ESCORA);
		peca2.setNoInicial(coordenada2);
		peca2.setNoFinal(coordenada3);
		
		sumarioDados.getListaDeNos().add(coordenada1);
		sumarioDados.getListaDeNos().add(coordenada2);
		sumarioDados.getListaDeNos().add(coordenada3);
		
		sumarioDados.getPecasFinais().add(peca1);
		sumarioDados.getPecasFinais().add(peca2);

		eixoX = eixoX + (RepositorioPecas.pecas.get(tipoPeca1).getComprimentoX() * 100); 
		
		defineBaseEscora();

		coordenada4.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada4.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada4.getId()));					
		coordenada4.setX((eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML));
		coordenada4.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada4.setZ(altura);

		coordenada5.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada5.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada5.getId()));					
		coordenada5.setX((eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML));
		coordenada5.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada5.setZ(altura + RepositorioPecas.pecas.get(tipoPeca2).getComprimentoZ());	
		
		coordenada6.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada6.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada6.getId()));					
		coordenada6.setX((eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML));
		coordenada6.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada6.setZ(coordenada5.getZ() + alturaEscoraFlexivel);
		
		defineForcadoEscora(eixoX/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, eixoY/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, coordenada6.getZ());

		eixoX = eixoX - (RepositorioPecas.pecas.get(tipoPeca1).getComprimentoX() * 100); 

		//DEFINE SEGUNDA PECA ESCORA
		peca3.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		peca3.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca3.getId()));
		peca3.setTipo(tipoPeca2);
		peca3.setNoInicial(coordenada4);
		peca3.setNoFinal(coordenada5);
		
		//DEFINE SEGUNDA PECA ESCORA
		peca4.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		peca4.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca4.getId()));
		peca4.setTipo(SciaXMLConstantes.TOPO_ESCORA);
		peca4.setNoInicial(coordenada5);
		peca4.setNoFinal(coordenada6);


		sumarioDados.getListaDeNos().add(coordenada4);
		sumarioDados.getListaDeNos().add(coordenada5);
		sumarioDados.getListaDeNos().add(coordenada6);

		sumarioDados.getPecasFinais().add(peca3);
		sumarioDados.getPecasFinais().add(peca4);
		
		atualizaDadosClasseCalculo();
	}

	public void defineEscoraNoEixoXY(String tipoPeca1, String tipoPeca2) throws CalculoException{

		Coordenada coordenada1 = new Coordenada();
		Coordenada coordenada2 = new Coordenada();
		Coordenada coordenada3 = new Coordenada();

		Peca peca1 = new Peca();
		Peca peca2 = new Peca();
		
		double altura = Double.parseDouble(this.sumarioDados.getCoordenadaZ())/SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
		
		defineBaseEscora();

		coordenada1.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada1.getId()));					
		coordenada1.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada1.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada1.setZ(altura);

		coordenada2.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada2.getId()));					
		coordenada2.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada2.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada2.setZ(altura + RepositorioPecas.pecas.get(tipoPeca2).getComprimentoZ());
		
		Double alturaEscoraFlexivel = defineAlturaEscoraFlexivel(tipoPeca1);
		
		coordenada3.setId( String.valueOf(Identificadores.getIdentificadorNo()));
		coordenada3.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada3.getId()));					
		coordenada3.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada3.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
		coordenada3.setZ(coordenada2.getZ() + alturaEscoraFlexivel);
		
		defineForcadoEscora(eixoX/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, eixoY/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML, coordenada3.getZ());

		//DEFINE PRIMEIRA PECA ESCORA
		peca1.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		peca1.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca1.getId()));
		peca1.setTipo(tipoPeca1);
		peca1.setNoInicial(coordenada1);
		peca1.setNoFinal(coordenada2);
		
		//DEFINE PRIMEIRA PECA ESCORA
		peca2.setId(String.valueOf(Identificadores.getIdentificarPecas()));
		peca2.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca2.getId()));
		peca2.setTipo(SciaXMLConstantes.TOPO_ESCORA);
		peca2.setNoInicial(coordenada2);
		peca2.setNoFinal(coordenada3);

		sumarioDados.getListaDeNos().add(coordenada1);
		sumarioDados.getListaDeNos().add(coordenada2);
		sumarioDados.getListaDeNos().add(coordenada3);

		//ADICIONA AS PECAS NA LISTA
		sumarioDados.getPecasFinais().add(peca1);
		sumarioDados.getPecasFinais().add(peca2);
		


		this.atualizaDadosClasseCalculo();
	}	
	
	private void defineForcadoEscora(Double eixoX, Double eixoY, Double eixoZ){
		
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

	private void atualizaDadosClasseCalculo(){
		Calculo.eixoX = eixoX;
		Calculo.eixoY = eixoY;
	}

}