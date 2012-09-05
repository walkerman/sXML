package br.com.scia.xml.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.dao.RepositorioProjeto;
import br.com.scia.xml.entity.exception.CalculoException;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.xml.Coordenada;
import br.com.scia.xml.util.SciaXMLConstantes;
import br.com.scia.xml.util.SciaXMLUtils;

public class Calculo {

	public static SumarioDados dados;
	public static Double eixoX;
	public static Double eixoY;
	public static Integer identificacaoPeca; 
	public static Integer no;
	
	public static SumarioDados calculaEstrutura(SumarioDados dados) throws CalculoException{

		RepositorioPecas.listaPostes = new ArrayList<>();
		RepositorioPecas.listaRosaceas = new ArrayList<>();
		RepositorioPecas.listaTravessas = new ArrayList<>();
		RepositorioPecas.listaTravessasReplicadas = new ArrayList<>();
		
		RepositorioProjeto.projeto.setPecasFinais(new ArrayList<Peca>());
		RepositorioProjeto.projeto.setListaDeNos(new ArrayList<Coordenada>());
		
		Calculo.dados = dados;
		
		HashMap<String, String> map = Calculo.vinculaTipoPecas(dados);	

		calculaVaoDeApoio(SciaXMLConstantes.AMBOS_OS_EIXOS);

		if (!Calculo.isLajeCompativel()){
			throw new CalculoException(SciaXMLConstantes.LAJE_IMCOMPATIVEL);
		}

		Calculo.dados = Calculo.calculaCoordenadas(map);
		
		CalculoPostes calculoPostes = new CalculoPostes();
		calculoPostes.realizarCalculo();
		
		CalculoVigasPrincipais calculoVigas = new CalculoVigasPrincipais();
		calculoVigas.realizarCalculo();
		
		CalculoVigasSecundarias calculoVigasSecundarias = new CalculoVigasSecundarias();
		calculoVigasSecundarias.realizarCalculo();
		
		CalculoTravessas calculoTravessas = new CalculoTravessas();
		calculoTravessas.replicarTravessas();
		
		if (dados.getKidI()){
			CalculoTravessasDiagonaisVerticais calculoTravessasDiagonaisVerticais = new CalculoTravessasDiagonaisVerticais();
			calculoTravessasDiagonaisVerticais.realizarCalculo();
		}
		
		CalculoTravessasDiagonaisHorizontais calculoTravessasDiagonaisHorizontais = new CalculoTravessasDiagonaisHorizontais();
		calculoTravessasDiagonaisHorizontais.realizarCalculo();
		
		return dados;
	}

	public static SumarioDados calculaVaoDeApoio(String eixo) throws CalculoException{
		
		if (eixo.equalsIgnoreCase(SciaXMLConstantes.AMBOS_OS_EIXOS)){
			Calculo.calculaVaoDeApoio(SciaXMLConstantes.EIXO_X);
			Calculo.calculaVaoDeApoio(SciaXMLConstantes.EIXO_Y);
		}	
		
		Double medidaLaje = 0.0;
		Double comprimentoTotalEixo = 0.0;
		Double folgaLaje1 = 0.0;
		Double folgaLaje2 = 0.0;		

		if (eixo.equalsIgnoreCase(SciaXMLConstantes.EIXO_X)){	
			
			medidaLaje = Double.parseDouble(dados.getMedidaLageX());
			comprimentoTotalEixo = dados.getComprimentoTotalEixoX();
			folgaLaje1 =  Double.parseDouble(dados.getFolgaLajeX1());
			folgaLaje2 =  Double.parseDouble(dados.getFolgaLajeX2());	
			
			switch (dados.getPecasX().size()) {
			case 1:
				dados.setVaoDeApoioX( (medidaLaje - comprimentoTotalEixo - folgaLaje1 - folgaLaje2) / 2);
				break;
			default:
				dados.setVaoDeApoioX( (medidaLaje - comprimentoTotalEixo -  folgaLaje1  - folgaLaje2) / (dados.getPecasX().size() - 1));
				break;
			}

			if (dados.getVaoDeApoioX() < 0){
				throw new CalculoException(SciaXMLConstantes.VAO_IMCOMPATIVEL);
			}
		}

		if (eixo.equalsIgnoreCase(SciaXMLConstantes.EIXO_Y)){		
			
			medidaLaje = Double.parseDouble(dados.getMedidaLageY());
			comprimentoTotalEixo = dados.getComprimentoTotalEixoY();
			folgaLaje1 =  Double.parseDouble(dados.getFolgaLajeY1());
			folgaLaje2 =  Double.parseDouble(dados.getFolgaLajeY2());	
			
			switch (dados.getPecasY().size()) {
			case 1:
				dados.setVaoDeApoioY( (medidaLaje - comprimentoTotalEixo - folgaLaje1 - folgaLaje2) / 2);
				break;
			default:
				dados.setVaoDeApoioY( (medidaLaje - comprimentoTotalEixo -  folgaLaje1  - folgaLaje2) / (dados.getPecasX().size() - 1));
				break;
			}

			if (dados.getVaoDeApoioY() < 0){
				throw new CalculoException(SciaXMLConstantes.VAO_IMCOMPATIVEL);
			}
		}	

		return dados;
	}

	public static SumarioDados calculaCoordenadas(HashMap<String, String> map) throws CalculoException{

		dados.setListaDeNos(new ArrayList<Coordenada>());
		dados.setPecasFinais(new ArrayList<Peca>());
		no = 1;
		identificacaoPeca = 1;

		Coordenada coordenada1 = null;
		Coordenada coordenada2 = null;
		Coordenada coordenada3 = null;
		Coordenada coordenada4 = null;

		Peca peca1 = null;
		Peca peca2 = null;
		Peca peca3 = null;
		Peca peca4 = null;

		String tipoPeca1;
		String tipoPeca2;

		//COORDENADA INICIAL TEM PARTIDA CONSIDERANDO AS FOLGAS DA LAJE		
		switch (dados.getPecasX().size()) {
		case 1:
			eixoX = Double.parseDouble(dados.getCoordenadaX()) + Double.parseDouble(dados.getFolgaLajeX1())  + dados.getVaoDeApoioX();
			break;

		default:
			eixoX = Double.parseDouble(dados.getCoordenadaX())+Double.parseDouble(dados.getFolgaLajeX1());
			break;
		}
		
		//COORDENADA INICIAL TEM PARTIDA CONSIDERANDO AS FOLGAS DA LAJE		
		switch (dados.getPecasY().size()) {
		case 1:
			eixoY = Double.parseDouble(dados.getCoordenadaY()) + Double.parseDouble(dados.getFolgaLajeY1())  + dados.getVaoDeApoioY();
			break;

		default:
			eixoY = Double.parseDouble(dados.getCoordenadaY())+Double.parseDouble(dados.getFolgaLajeY1());
			break;
		}

		for (int i = 0; i < dados.getPecasX().size(); i++) {

			for (int j = 0; j < dados.getPecasY().size(); j++) {				

				//RESGATA TIPO DAS PECAS QUE PRECISAM SER ALOCADAS NA POSICAO XY.
				String pecas = map.get(i+"|"+j);
				tipoPeca1 = pecas.substring(0, pecas.indexOf("|"));
				tipoPeca2 = pecas.substring(pecas.indexOf("|") + 1, pecas.length());

				//DEFINE COORDENADAS DE UMA TORRE
				if (tipoPeca1.contains(SciaXMLConstantes.KITRV) && tipoPeca2.contains(SciaXMLConstantes.KITRV)){
					coordenada1 = new Coordenada();
					coordenada2 = new Coordenada();
					coordenada3 = new Coordenada();
					coordenada4 = new Coordenada();

					peca1 = new Peca();
					peca2 = new Peca();
					peca3 = new Peca();
					peca4 = new Peca();

					//DEFINE PRIMEIRA COORDENADA
					coordenada1.setId(String.valueOf(no));
					coordenada1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada1.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada1.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);

					//ALOCA CURSOR NO PONTO 2
					eixoY = eixoY + (RepositorioPecas.pecas.get(tipoPeca2).getComprimentoX() * 100);

					//DEFINE SEGUNDA COORDENADA
					coordenada2.setId( String.valueOf(no));
					coordenada2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada2.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada2.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);

					//ALOCA CURSOR NO PONTO 3
					eixoX = eixoX + (RepositorioPecas.pecas.get(tipoPeca1).getComprimentoX() * 100);

					//DEFINE TERCEIRA COORDENADA
					coordenada3.setId( String.valueOf(no));
					coordenada3.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada3.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada3.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);

					//ALOCA CURSOR NO PONTO 4
					eixoY = eixoY - (RepositorioPecas.pecas.get(tipoPeca2).getComprimentoX() * 100);

					//DEFINE QUARTA COORDENADA
					coordenada4.setId( String.valueOf(no));
					coordenada4.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada4.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada4.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);

					//REPOSICIONA O CURSOR PARA ANDAR NOS PONTOS
					eixoX = eixoX - (RepositorioPecas.pecas.get(tipoPeca1).getComprimentoX() * 100);
					eixoY = eixoY + (RepositorioPecas.pecas.get(tipoPeca2).getComprimentoX() * 100);

					//DEFINE PRIMEIRA PECA TORRE
					peca1.setId(String.valueOf(identificacaoPeca));
					peca1.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca));
					peca1.setTipo(tipoPeca2);
					peca1.setNoInicial(coordenada1);
					peca1.setNoFinal(coordenada2);

					//DEFINE SEGUNDA PECA TORRE
					peca2.setId(String.valueOf(identificacaoPeca+1));
					peca2.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca+1));
					peca2.setTipo(tipoPeca1);
					peca2.setNoInicial(coordenada2);
					peca2.setNoFinal(coordenada3);

					//DEFINE TERCEIRA PECA TORRE
					peca3.setId(String.valueOf(identificacaoPeca+2));
					peca3.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca+2));
					peca3.setTipo(tipoPeca2);
					peca3.setNoInicial(coordenada3);
					peca3.setNoFinal(coordenada4);

					//DEFINE QUARTA PECA TORRE
					peca4.setId(String.valueOf(identificacaoPeca+3));
					peca4.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca+3));
					peca4.setTipo(tipoPeca1);
					peca4.setNoInicial(coordenada4);
					peca4.setNoFinal(coordenada1);

					//ADICIONA AS COORDENADAS NA LISTA
					dados.getListaDeNos().add(coordenada1);
					dados.getListaDeNos().add(coordenada2);
					dados.getListaDeNos().add(coordenada3);
					dados.getListaDeNos().add(coordenada4);

					//ADICIONA AS PECAS NA LISTA
//					dados.getPecasFinais().add(peca1);
//					dados.getPecasFinais().add(peca2);
//					dados.getPecasFinais().add(peca3);
//					dados.getPecasFinais().add(peca4);

					RepositorioPecas.listaTravessas.add(peca1);
					RepositorioPecas.listaTravessas.add(peca2);					
					RepositorioPecas.listaTravessas.add(peca3);			
					RepositorioPecas.listaTravessas.add(peca4);
					
					identificacaoPeca = identificacaoPeca + 4;
				}

				//DEFINE COORDENADAS PARA ESCORAS VINCULADAS A TRAVESSAS	
				if (tipoPeca1.contains(SciaXMLConstantes.ESC) && tipoPeca2.contains(SciaXMLConstantes.KITRV)){				   
					CalculoEscoras calculoEscoras = new CalculoEscoras(dados, identificacaoPeca, no, eixoX, eixoY);
					calculoEscoras.defineEscoraNoEixoX(tipoPeca1, tipoPeca2);					
				}
				
				//DEFINE COORDENADAS PARA ESCORAS VINCULADAS A TRAVESSAS	
				if (tipoPeca1.contains(SciaXMLConstantes.KITRV) && tipoPeca2.contains(SciaXMLConstantes.ESC)){				   
					CalculoEscoras calculoEscoras = new CalculoEscoras(dados, identificacaoPeca, no, eixoX, eixoY);
					calculoEscoras.defineEscoraNoEixoY(tipoPeca1, tipoPeca2);
				}				
				 
				//DEFINE COORDENADAS PARA ESCORAS VINCULADAS A ESCORAS	
				if (tipoPeca1.contains(SciaXMLConstantes.ESC) && tipoPeca2.contains(SciaXMLConstantes.ESC)){				   
     				CalculoEscoras calculoEscoras = new CalculoEscoras(dados, identificacaoPeca, no, eixoX, eixoY);
					calculoEscoras.defineEscoraNoEixoXY(tipoPeca1, tipoPeca2);					 
				}

//				//DEFINE COORDENADAS PARA CRUZETAS
//				if (tipoPeca1.contains(SciaXMLConstantes.CRU) || tipoPeca2.contains(SciaXMLConstantes.CRU)){
//
//					coordenada1 = new Coordenada();
//					coordenada2 = new Coordenada();				    
//					coordenada3 = new Coordenada();
//					coordenada4 = new Coordenada();
//
//					peca1 = new Peca();
//					peca2 = new Peca();
//
//					coordenada1.setId( String.valueOf(no));
//					coordenada1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(no++));					
//					coordenada1.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
//					coordenada1.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
//
//					coordenada3.setId( String.valueOf(no));
//					coordenada3.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(no++));					
//					coordenada3.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
//					coordenada3.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
//				//	coordenada3.setZ(getAltura(this.identificadorPecas));			    
//
//					eixoY = eixoY + 50;
//
//					coordenada2.setId( String.valueOf(no));
//					coordenada2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(no++));					
//					coordenada2.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
//					coordenada2.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
//
//					coordenada4.setId( String.valueOf(no));
//					coordenada4.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(no++));					
//					coordenada4.setX(eixoX / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
//					coordenada4.setY(eixoY / SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML);
//				//	coordenada4.setZ(getAltura(dados));		
//
//					//DEFINE PRIMEIRA PECA ESCORA
//					peca1.setId(String.valueOf(identificacaoPeca));
//					peca1.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca));
//					peca1.setTipo("Escora A");
//					peca1.setNoInicial( coordenada1.getId());
//					peca1.setNoFinal( coordenada3.getId());
//
//					//DEFINE SEGUNDA PECA ESCORA
//					peca2.setId(String.valueOf(identificacaoPeca+1));
//					peca2.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca+1));
//					peca2.setTipo("Escora A");
//					peca2.setNoInicial( coordenada2.getId());
//					peca2.setNoFinal( coordenada4.getId());
//
//					//ADICIONA AS COORDENADAS NA LISTA
//					dados.getListaDeNos().add(coordenada1);
//					dados.getListaDeNos().add(coordenada2);
//					dados.getListaDeNos().add(coordenada3);
//					dados.getListaDeNos().add(coordenada4);
//
//					//ADICIONA AS PECAS NA LISTA
//					dados.getPecasFinais().add(peca1);
//					dados.getPecasFinais().add(peca2);
//
//					identificacaoPeca = identificacaoPeca + 2;
//					eixoX = eixoX - (RepositorioPecas.pecas.get(tipoPeca2).getComprimentoY() * 100);
//					eixoY = eixoY + 50;
//
//				}

				//Não soma o vão se for a última peça do eixo Y.
				if (j!=dados.getPecasY().size() -1 && tipoPeca1!=SciaXMLConstantes.CRU && tipoPeca2!=SciaXMLConstantes.CRU){
					eixoY = eixoY + dados.getVaoDeApoioY();	
				}else{
					eixoX = eixoX + (RepositorioPecas.pecas.get(tipoPeca1).getComprimentoX() * 100);
				}			 			
			}	

			eixoX = eixoX + dados.getVaoDeApoioX();
			eixoY =  Double.parseDouble(dados.getCoordenadaY())+Double.parseDouble(dados.getFolgaLajeY1());
		}
		
		return dados;

	}

	public static boolean isLajeCompativel(){

		boolean retorno = true;

		if (dados.getComprimentoTotalEixoX()
				+ dados.getVaoDeApoioX()
				+ Double.parseDouble(dados.getFolgaLajeX1()) + Double.parseDouble(dados.getFolgaLajeX2()) >
		Double.parseDouble(dados.getMedidaLageX())) {
			retorno = false;
		}

		if (dados.getComprimentoTotalEixoY()
				+ dados.getVaoDeApoioY()
				+ (Double.parseDouble(dados.getFolgaLajeY1()) + Double.parseDouble(dados.getFolgaLajeY2())) > 
		Double.parseDouble(dados.getMedidaLageY())) {
			retorno = false;
		}

		return retorno;
	}

	public static HashMap<String, String> vinculaTipoPecas(SumarioDados dados){

		HashMap<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < dados.getPecasX().size(); i++) {
			for (int j = 0; j < dados.getPecasY().size(); j++) {
				map.put(i+"|"+j, dados.getPecasX().get(i).getTipo()+"|"+dados.getPecasY().get(j).getTipo());
				System.out.println(i+"|"+j+ "|" + dados.getPecasX().get(i).getTipo()+"|"+dados.getPecasY().get(j).getTipo());
			}
		}

		return map;
	}

	public static boolean possuiTravessaNoEixo(HashMap<String, String> map, String eixo, int posicaoCursor){

		Set<String> chaves = map.keySet();
		String posicaoX;
		String posicaoY;
		String valorRegistro = "";
		String registro1 = "";

		for (String string : chaves) {
			posicaoX = string.substring(0, string.indexOf("|"));
			posicaoY = string.substring(string.indexOf("|") + 1, string.length());

			if (SciaXMLConstantes.EIXO_X.equals(eixo) && posicaoCursor != Integer.parseInt(posicaoX)){
				continue;
			}
			
			if (SciaXMLConstantes.EIXO_Y.equals(eixo) && posicaoCursor != Integer.parseInt(posicaoY)){
				continue;
			}
						
			if (SciaXMLConstantes.EIXO_X.equals(eixo) && posicaoCursor == Integer.parseInt(posicaoX)){
				valorRegistro = map.get(string);
				registro1 = valorRegistro.substring(0, valorRegistro.indexOf("|"));
				
				if (registro1.equalsIgnoreCase(SciaXMLConstantes.KITRV)){
					return true;
				}			
			}

			if (SciaXMLConstantes.EIXO_Y.equals(eixo) && posicaoCursor == Integer.parseInt(posicaoY)){
				valorRegistro = map.get(valorRegistro);
				registro1 = valorRegistro.substring(valorRegistro.indexOf("|") + 1, valorRegistro.length());
				
				if (registro1.equalsIgnoreCase(SciaXMLConstantes.KITRV)){
					return true;
				}			
			}	

		}

		return false;		
	}
	
	public static Double getAlturaUtil(){
		Double altura = 0.0;
		if (!"".equals(SciaXMLUtils.checkString(Calculo.dados.getPeDireito()))){
    		Double peDireito = Double.parseDouble(Calculo.dados.getPeDireito())/100.0;
    		Double espessuraLaje = Double.parseDouble(Calculo.dados.getEspessura())/100.0;
    		Double espessuraCompensado = Double.parseDouble(Calculo.dados.getEspessuraCompensado())/100.0;
    		Double espessuraVigaPrincipal = CalculoVigasPrincipais.getAlturaViga();
    		Double espessuraVigaSecundaria = CalculoVigasPrincipais.getAlturaViga();  //TODO: alterar para viga secundaria
    		Double tamanhoPosteEspecial = Calculo.dados.getPosteEspecial().getComprimento();
    		
    		altura = peDireito - espessuraCompensado - espessuraLaje - espessuraVigaPrincipal - espessuraVigaSecundaria - tamanhoPosteEspecial;
    	}
		return altura;
	} 

}