//package br.com.scia.xml.model;
//
//import br.com.scia.xml.dao.RepositorioPecas;
//import br.com.scia.xml.entity.exception.CalculoException;
//import br.com.scia.xml.entity.view.Peca;
//import br.com.scia.xml.entity.view.SumarioDados;
//import br.com.scia.xml.entity.xml.Coordenada;
//import br.com.scia.xml.util.SciaXMLContantes;
//import br.com.scia.xml.util.SciaXMLUtils;
//
//public class CalculoEscoras {
//
//	private SumarioDados sumarioDados;	
//	private Integer identificadorPecas;
//	private Integer identificadorNos;
//	private Double eixoX;
//	private Double eixoY;
//
//	public CalculoEscoras(SumarioDados dados, Integer identificadorPecas, Integer identificadorNos, Double posicaoEixoX, Double posicaoEixoY){
//		this.sumarioDados = dados;
//		this.identificadorPecas = identificadorPecas;
//		this.identificadorNos = identificadorNos;
//		this.eixoX = posicaoEixoX;
//		this.eixoY = posicaoEixoY;
//	}
//
//	private Double defineAlturaEscoraFlexivel(String tipoEscora) throws CalculoException{		
//
//		Double alturaTotalEscora = 0.0;
//		Double alturaFlexivelEscora = 0.0;
//		Double alturaBaseEscora = 0.0;
//		Double peDireito = Double.parseDouble(sumarioDados.getPeDireito());
//		Double espessuraConpensado = Double.parseDouble(sumarioDados.getEspessuraCompensado());
//		Double espessuraLaje = Double.parseDouble(sumarioDados.getEspessura());
//		Double alturaVigaPrincipal = CalculoVigas.getAlturaViga();
//		Double alturaVigaSecundaria = CalculoVigas.getAlturaViga();		//TODO alterar para viga secundaria
//		Double alturaForcado = sumarioDados.getForcadoX().getComprimento();
//	
//		alturaBaseEscora = RepositorioPecas.pecas.get(tipoEscora).getComprimentoZ() ;
//		alturaTotalEscora = peDireito - espessuraConpensado - espessuraLaje - alturaVigaPrincipal - alturaVigaSecundaria - alturaForcado;
//		alturaFlexivelEscora = alturaTotalEscora /100 - alturaBaseEscora;
//
//		switch (tipoEscora) {
//			case SciaXMLContantes.TIPO_ESCORA_A:
//				if (alturaFlexivelEscora < SciaXMLContantes.TAMANHO_MIN_ESCORA_A || alturaFlexivelEscora > SciaXMLContantes.TAMANHO_MAX_ESCORA_A)
//					throw new CalculoException(SciaXMLContantes.ESCORA_IMCOMPATIVEL);				
//				break;
//	
//			case SciaXMLContantes.TIPO_ESCORA_B:
//				if (alturaFlexivelEscora < SciaXMLContantes.TAMANHO_MIN_ESCORA_B || alturaFlexivelEscora > SciaXMLContantes.TAMANHO_MAX_ESCORA_B)
//					throw new CalculoException(SciaXMLContantes.ESCORA_IMCOMPATIVEL);		
//				break;
//	
//			case SciaXMLContantes.TIPO_ESCORA_C:
//				if (alturaFlexivelEscora < SciaXMLContantes.TAMANHO_MIN_ESCORA_C || alturaFlexivelEscora > SciaXMLContantes.TAMANHO_MAX_ESCORA_C)
//					throw new CalculoException(SciaXMLContantes.ESCORA_IMCOMPATIVEL);		
//				break;
//	
//			case SciaXMLContantes.TIPO_ESCORA_D:
//				if (alturaFlexivelEscora < SciaXMLContantes.TAMANHO_MIN_ESCORA_D || alturaFlexivelEscora > SciaXMLContantes.TAMANHO_MAX_ESCORA_D)
//					throw new CalculoException(SciaXMLContantes.ESCORA_IMCOMPATIVEL);		
//				break;
//	
//			default:
//				throw new CalculoException(SciaXMLContantes.ESCORA_NAO_CADASTRADA);
//		}
//
//		return alturaFlexivelEscora;
//	}
//	
//	private void defineBaseEscora(){
//		
//		Coordenada coordenada1 = new Coordenada();
//		Coordenada coordenada2 = new Coordenada();
//		
//		Peca peca1 = new Peca();
//		
//		Double pontoInicialBase = eixoX - (RepositorioPecas.pecas.get(SciaXMLContantes.BASE_ESCORA).getComprimentoY() / 2);
//		Double pontoFinalBase = eixoX + (RepositorioPecas.pecas.get(SciaXMLContantes.BASE_ESCORA).getComprimentoY() / 2);
//		
//		coordenada1.setId(String.valueOf(identificadorNos));
//		coordenada1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada1.setX(pontoInicialBase / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada1.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		
//		coordenada2.setId( String.valueOf(identificadorNos));
//		coordenada2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada2.setX(pontoFinalBase / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada2.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		
//		//DEFINE PRIMEIRA PECA ESCORA
//		peca1.setId(String.valueOf(identificadorPecas));
//		peca1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas));
//		peca1.setTipo(SciaXMLContantes.BASE_ESCORA);
//		peca1.setNoInicial(coordenada1.getId());
//		peca1.setNoFinal(coordenada2.getId());
//		
//		//ADICIONA COORDENADAS NA LISTA
//		sumarioDados.getListaDeNos().add(coordenada1);
//		sumarioDados.getListaDeNos().add(coordenada2);
//		
//		//ADICIONA AS PECAS NA LISTA
//		sumarioDados.getPecasFinais().add(peca1);
//		
//		identificadorPecas  = identificadorPecas  + 1;
//		
//	}
//
//	public void defineEscoraNoEixoX(String tipoPeca1, String tipoPeca2) throws CalculoException{
//
//		Coordenada coordenada1 = new Coordenada();
//		Coordenada coordenada2 = new Coordenada();
//		Coordenada coordenada3 = new Coordenada();
//		Coordenada coordenada4 = new Coordenada();		
//		Coordenada coordenada5 = new Coordenada();
//		Coordenada coordenada6 = new Coordenada();
//
//		Peca peca1 = new Peca();
//		Peca peca2 = new Peca();
//		Peca peca3 = new Peca();
//		Peca peca4 = new Peca();
//		
//		defineBaseEscora();
//		
//		coordenada1.setId(String.valueOf(identificadorNos));
//		coordenada1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada1.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada1.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		
//		coordenada2.setId( String.valueOf(identificadorNos));
//		coordenada2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada2.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada2.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada2.setZ(RepositorioPecas.pecas.get(tipoPeca1).getComprimentoZ());
//				
//		Double alturaEscoraFlexivel = defineAlturaEscoraFlexivel(tipoPeca1);
//		
//		coordenada3.setId( String.valueOf(identificadorNos));
//		coordenada3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada3.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada3.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada3.setZ(alturaEscoraFlexivel / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);	
//		
//		eixoY = eixoY + (RepositorioPecas.pecas.get(tipoPeca2).getComprimentoX() * 100);
//		
//		//DEFINE PRIMEIRA PECA ESCORA
//		peca1.setId(String.valueOf(identificadorPecas));
//		peca1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas));
//		peca1.setTipo(tipoPeca1);
//		peca1.setNoInicial(coordenada1.getId());
//		peca1.setNoFinal(coordenada3.getId());
//		
//		//DEFINE PRIMEIRA PECA ESCORA
//		peca2.setId(String.valueOf(identificadorPecas+2));
//		peca2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas+2));
//		peca2.setTipo(SciaXMLContantes.TOPO_ESCORA);
//		peca2.setNoInicial(coordenada3.getId());
//		peca2.setNoFinal(coordenada5.getId());
//		
//		sumarioDados.getListaDeNos().add(coordenada1);
//		sumarioDados.getListaDeNos().add(coordenada2);
//		sumarioDados.getListaDeNos().add(coordenada3);
//		
//		//ADICIONA AS PECAS NA LISTA
//		sumarioDados.getPecasFinais().add(peca1);
//		sumarioDados.getPecasFinais().add(peca2);
//		
//		identificadorPecas  = identificadorPecas  + 2;
//		
//		defineBaseEscora();
//
//		coordenada4.setId(String.valueOf(identificadorNos));
//		coordenada4.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada4.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada4.setY((eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML));
//		
//		coordenada5.setId( String.valueOf(identificadorNos));
//		coordenada5.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada5.setX((eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML));
//		coordenada5.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada5.setZ(RepositorioPecas.pecas.get(tipoPeca1).getComprimentoZ());
//		
//		coordenada6.setId( String.valueOf(identificadorNos));
//		coordenada6.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada6.setX((eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML));
//		coordenada6.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada6.setZ(alturaEscoraFlexivel  / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		
//		//DEFINE SEGUNDA PECA ESCORA
//		peca3.setId(String.valueOf(identificadorPecas+1));
//		peca3.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas+1));
//		peca3.setTipo(tipoPeca1);
//		peca3.setNoInicial(coordenada2.getId());
//		peca3.setNoFinal(coordenada4.getId());
//		
//		//DEFINE SEGUNDA PECA ESCORA
//		peca4.setId(String.valueOf(identificadorPecas+3));
//		peca4.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas+3));
//		peca4.setTipo(SciaXMLContantes.TOPO_ESCORA);
//		peca4.setNoInicial(coordenada4.getId());
//		peca4.setNoFinal(coordenada6.getId());
//		
//		//ADICIONA COORDENADAS NA LISTA
//		sumarioDados.getListaDeNos().add(coordenada4);
//		sumarioDados.getListaDeNos().add(coordenada5);
//		sumarioDados.getListaDeNos().add(coordenada6);
//
//		sumarioDados.getPecasFinais().add(peca3);
//		sumarioDados.getPecasFinais().add(peca4);
//
//		identificadorPecas  = identificadorPecas  + 2;
//		atualizaDadosClasseCalculo();
//	}
//
//
//	public void defineEscoraNoEixoY(String tipoPeca1, String tipoPeca2) throws CalculoException{
//
//		Coordenada coordenada1 = new Coordenada();
//		Coordenada coordenada2 = new Coordenada();
//		Coordenada coordenada3 = new Coordenada();
//		Coordenada coordenada4 = new Coordenada();		
//		Coordenada coordenada5 = new Coordenada();
//		Coordenada coordenada6 = new Coordenada();
//
//		Peca peca1 = new Peca();
//		Peca peca2 = new Peca();
//		Peca peca3 = new Peca();
//		Peca peca4 = new Peca();
//		
//		defineBaseEscora();
//
//		coordenada1.setId( String.valueOf(identificadorNos));
//		coordenada1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada1.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada1.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//
//		coordenada2.setId( String.valueOf(identificadorNos));
//		coordenada2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada2.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada2.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada2.setZ(RepositorioPecas.pecas.get(tipoPeca2).getComprimentoZ());
//		
//		Double alturaEscoraFlexivel = defineAlturaEscoraFlexivel(tipoPeca2);
//		
//		coordenada3.setId( String.valueOf(identificadorNos));
//		coordenada3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada3.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada3.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada3.setZ(alturaEscoraFlexivel  / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);	
//		
//		//DEFINE PRIMEIRA PECA ESCORA
//		peca1.setId(String.valueOf(identificadorPecas));
//		peca1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas));
//		peca1.setTipo(tipoPeca2);
//		peca1.setNoInicial(coordenada1.getId());
//		peca1.setNoFinal(coordenada2.getId());
//		
//		//DEFINE PRIMEIRA PECA ESCORA
//		peca2.setId(String.valueOf(identificadorPecas+2));
//		peca2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas+2));
//		peca2.setTipo(SciaXMLContantes.TOPO_ESCORA);
//		peca2.setNoInicial(coordenada2.getId());
//		peca2.setNoFinal(coordenada3.getId());
//		
//		sumarioDados.getListaDeNos().add(coordenada1);
//		sumarioDados.getListaDeNos().add(coordenada2);
//		sumarioDados.getListaDeNos().add(coordenada3);
//		
//		sumarioDados.getPecasFinais().add(peca1);
//		sumarioDados.getPecasFinais().add(peca2);
//		
//		identificadorPecas  = identificadorPecas  + 2;
//
//		eixoX = eixoX + (RepositorioPecas.pecas.get(tipoPeca1).getComprimentoX() * 100); 
//		
//		defineBaseEscora();
//
//		coordenada4.setId( String.valueOf(identificadorNos));
//		coordenada4.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada4.setX((eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML));
//		coordenada4.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//
//		coordenada5.setId( String.valueOf(identificadorNos));
//		coordenada5.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada5.setX((eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML));
//		coordenada5.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada5.setZ(RepositorioPecas.pecas.get(tipoPeca2).getComprimentoZ());	
//		
//		coordenada6.setId( String.valueOf(identificadorNos));
//		coordenada6.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada6.setX((eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML));
//		coordenada6.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada6.setZ(alturaEscoraFlexivel  / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//
//		eixoX = eixoX - (RepositorioPecas.pecas.get(tipoPeca1).getComprimentoX() * 100); 
//
//		//DEFINE SEGUNDA PECA ESCORA
//		peca3.setId(String.valueOf(identificadorPecas+1));
//		peca3.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas+1));
//		peca3.setTipo(tipoPeca2);
//		peca3.setNoInicial(coordenada2.getId());
//		peca3.setNoFinal(coordenada4.getId());
//		
//		//DEFINE SEGUNDA PECA ESCORA
//		peca4.setId(String.valueOf(identificadorPecas+3));
//		peca4.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas+3));
//		peca4.setTipo(SciaXMLContantes.TOPO_ESCORA);
//		peca4.setNoInicial(coordenada4.getId());
//		peca4.setNoFinal(coordenada6.getId());
//
//
//		sumarioDados.getListaDeNos().add(coordenada4);
//		sumarioDados.getListaDeNos().add(coordenada5);
//		sumarioDados.getListaDeNos().add(coordenada6);
//
//		sumarioDados.getPecasFinais().add(peca3);
//		sumarioDados.getPecasFinais().add(peca4);
//
//		identificadorPecas = identificadorPecas + 2;		
//		atualizaDadosClasseCalculo();
//	}
//
//	public void defineEscoraNoEixoXY(String tipoPeca1, String tipoPeca2) throws CalculoException{
//
//		Coordenada coordenada1 = new Coordenada();
//		Coordenada coordenada2 = new Coordenada();
//		Coordenada coordenada3 = new Coordenada();
//
//
//		Peca peca1 = new Peca();
//		Peca peca2 = new Peca();
//		
//		defineBaseEscora();
//
//		coordenada1.setId( String.valueOf(identificadorNos));
//		coordenada1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada1.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada1.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//
//		coordenada2.setId( String.valueOf(identificadorNos));
//		coordenada2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada2.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada2.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada2.setZ(RepositorioPecas.pecas.get(tipoPeca2).getComprimentoZ());
//		
//		Double alturaEscoraFlexivel = defineAlturaEscoraFlexivel(tipoPeca1);
//		
//		coordenada3.setId( String.valueOf(identificadorNos));
//		coordenada3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
//		coordenada3.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada3.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
//		coordenada3.setZ(alturaEscoraFlexivel  / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);	
//
//		//DEFINE PRIMEIRA PECA ESCORA
//		peca1.setId(String.valueOf(identificadorPecas));
//		peca1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas));
//		peca1.setTipo(tipoPeca1);
//		peca1.setNoInicial(coordenada1.getId());
//		peca1.setNoFinal(coordenada2.getId());
//		
//		//DEFINE PRIMEIRA PECA ESCORA
//		peca2.setId(String.valueOf(identificadorPecas+1));
//		peca2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas+1));
//		peca2.setTipo(SciaXMLContantes.TOPO_ESCORA);
//		peca2.setNoInicial(coordenada2.getId());
//		peca2.setNoFinal(coordenada3.getId());
//
//		sumarioDados.getListaDeNos().add(coordenada1);
//		sumarioDados.getListaDeNos().add(coordenada2);
//		sumarioDados.getListaDeNos().add(coordenada3);
//
//		//ADICIONA AS PECAS NA LISTA
//		sumarioDados.getPecasFinais().add(peca1);
//		sumarioDados.getPecasFinais().add(peca2);
//
//		identificadorPecas = identificadorPecas + 2;
//		this.atualizaDadosClasseCalculo();
//	}	
//
//	@SuppressWarnings("unused")
//	private Double getAltura(SumarioDados dados){
//		Double retorno = 0.0;
//
//		if (dados != null){
//			Double peDireto = !"".equals(SciaXMLUtils.checkString(dados.getPeDireito())) ? Double.parseDouble(dados.getPeDireito())/100.0 : 0.0;
//			Double espessuraLaje = !"".equals(SciaXMLUtils.checkString(dados.getEspessura())) ? Double.parseDouble(dados.getEspessura())/100.0 : 0.0;
//			Double espessuraCompensado = !"".equals(SciaXMLUtils.checkString(dados.getEspessuraCompensado())) ? Double.parseDouble(dados.getEspessuraCompensado())/100.0 : 0.0;
//
//			retorno =  peDireto - espessuraLaje - espessuraCompensado;
//		}
//
//		return retorno;
//	}
//
//	private void atualizaDadosClasseCalculo(){
//		Calculo.eixoX = eixoX;
//		Calculo.eixoY = eixoY;
//		Calculo.no = identificadorNos;
//		Calculo.identificacaoPeca = identificadorPecas;
//	}
//
//}