package br.com.scia.xml.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.exception.CalculoException;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.xml.Coordenada;
import br.com.scia.xml.util.SciaXMLContantes;
import br.com.scia.xml.util.SciaXMLUtils;

public class Calculo {

	public static List<Coordenada> listaCoordenadas = null;
	public static List<Peca> listaPecasFinais = null;
	public static SumarioDados dados;
	
	public static SumarioDados calculaEstrutura(SumarioDados dados) throws CalculoException{

		Calculo.dados = dados;
		
		HashMap<String, String> map = Calculo.vinculaTipoPecas(dados);	

		calculaVaoDeApoio(Calculo.dados, SciaXMLContantes.AMBOS_OS_EIXOS);

		if (!Calculo.isLajeCompativel(Calculo.dados)){
			throw new CalculoException(SciaXMLContantes.LAJE_IMCOMPATIVEL);
		}

		Calculo.dados = Calculo.calculaCoordenadas(Calculo.dados, map);
	
		CalculoPostes calculoPostes = new CalculoPostes();
		calculoPostes.realizarCalculo();
		
		CalculoVigas calculoVigas = new CalculoVigas();
		calculoVigas.realizarCalculo();
		
		//	Calculo.aninhaNo(dados, map);

		return dados;
	}

	public static SumarioDados calculaVaoDeApoio(SumarioDados dados, String eixo) throws CalculoException{

		if (eixo.equalsIgnoreCase(SciaXMLContantes.EIXO_X)){
			switch (dados.getPecasX().size()) {
			case 1:
				dados.setVaoDeApoioX(Double.parseDouble(dados.getMedidaLageX()) - dados.getComprimentoTotalEixoX() - (Double.parseDouble(dados.getFolgaLajeX1()) + Double.parseDouble(dados.getFolgaLajeX2()))  / 2 );
				break;
			default:
				dados.setVaoDeApoioX((Double.parseDouble(dados.getMedidaLageX()) - dados.getComprimentoTotalEixoX() -  (Double.parseDouble(dados.getFolgaLajeX1()) + Double.parseDouble(dados.getFolgaLajeX2()))) / (dados.getPecasX().size() - 1));
				break;
			}

			if (dados.getVaoDeApoioX() < 0){
				throw new CalculoException(SciaXMLContantes.VAO_IMCOMPATIVEL);
			}
		}

		if (eixo.equalsIgnoreCase(SciaXMLContantes.EIXO_Y)){
			switch (dados.getPecasY().size()) {
			case 1:
				dados.setVaoDeApoioY(Double.parseDouble(dados.getMedidaLageY()) - dados.getComprimentoTotalEixoY()  - (Double.parseDouble(dados.getFolgaLajeY1()) + Double.parseDouble(dados.getFolgaLajeY2())) / 2);
				break;
			default:
				dados.setVaoDeApoioY((Double.parseDouble(dados.getMedidaLageY()) -  dados.getComprimentoTotalEixoY() - (Double.parseDouble(dados.getFolgaLajeY1()) + Double.parseDouble(dados.getFolgaLajeY2()))) / (dados.getPecasY().size() - 1));
				break;
			}

			if (dados.getVaoDeApoioY() < 0){
				throw new CalculoException(SciaXMLContantes.VAO_IMCOMPATIVEL);
			}
		}

		if (eixo.equalsIgnoreCase(SciaXMLContantes.AMBOS_OS_EIXOS)){
			Calculo.calculaVaoDeApoio(dados, SciaXMLContantes.EIXO_X);
			Calculo.calculaVaoDeApoio(dados, SciaXMLContantes.EIXO_Y);
		}		

		return dados;
	}

	public static SumarioDados calculaCoordenadas(SumarioDados dados, HashMap<String, String> map){

		Double eixoX = Double.parseDouble(dados.getCoordenadaX()+dados.getFolgaLajeX1());
		Double eixoY = Double.parseDouble(dados.getCoordenadaY()+dados.getFolgaLajeY1());

		int no = 1;
		int identificacaoPeca = 1;

		Coordenada coordenada1 = null;
		Coordenada coordenada2 = null;
		Coordenada coordenada3 = null;
		Coordenada coordenada4 = null;
		Coordenada coordenada5 = null;
		Coordenada coordenada6 = null;
		Coordenada coordenada7 = null;
		Coordenada coordenada8 = null;

		Peca peca1 = null;
		Peca peca2 = null;
		Peca peca3 = null;
		Peca peca4 = null;

		listaCoordenadas = new ArrayList<Coordenada>();
		listaPecasFinais = new ArrayList<Peca>();

		String tipoPeca1;
		String tipoPeca2;

		//COORDENADA INICIAL TEM PARTIDA CONSIDERANDO AS FOLGAS DA LAJE
		eixoX = Double.parseDouble(dados.getFolgaLajeX1());
		eixoY = Double.parseDouble(dados.getFolgaLajeY1());

		for (int i = 0; i < dados.getPecasX().size(); i++) {

			for (int j = 0; j < dados.getPecasY().size(); j++) {				

				//RESGATA TIPO DAS PECAS QUE PRECISAM SER ALOCADAS NA POSICAO XY.
				String pecas = map.get(i+"|"+j);
				tipoPeca1 = pecas.substring(0, pecas.indexOf("|"));
				tipoPeca2 = pecas.substring(pecas.indexOf("|") + 1, pecas.length());

				//DEFINE COORDENADAS DE UMA TORRE
				if (tipoPeca1.contains(SciaXMLContantes.KITRV) && tipoPeca2.contains(SciaXMLContantes.KITRV)){
					coordenada1 = new Coordenada();
					coordenada2 = new Coordenada();
					coordenada3 = new Coordenada();
					coordenada4 = new Coordenada();

					peca1 = new Peca();
					peca2 = new Peca();
					peca3 = new Peca();
					peca4 = new Peca();

					//DEFINE PRIMEIRA COORDENADA
					coordenada1.setId( String.valueOf(no));
					coordenada1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada1.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada1.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);

					//ALOCA CURSOR NO PONTO 2
					eixoY = eixoY + (RepositorioPecas.pecas.get(tipoPeca2).getComprimentoX() * 100);

					//DEFINE SEGUNDA COORDENADA
					coordenada2.setId( String.valueOf(no));
					coordenada2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada2.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada2.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);

					//ALOCA CURSOR NO PONTO 3
					eixoX = eixoX + (RepositorioPecas.pecas.get(tipoPeca1).getComprimentoX() * 100);

					//DEFINE TERCEIRA COORDENADA
					coordenada3.setId( String.valueOf(no));
					coordenada3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada3.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada3.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);

					//ALOCA CURSOR NO PONTO 4
					eixoY = eixoY - (RepositorioPecas.pecas.get(tipoPeca2).getComprimentoX() * 100);

					//DEFINE QUARTA COORDENADA
					coordenada4.setId( String.valueOf(no));
					coordenada4.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada4.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada4.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);

					//REPOSICIONA O CURSOR PARA ANDAR NOS PONTOS
					eixoX = eixoX - (RepositorioPecas.pecas.get(tipoPeca1).getComprimentoX() * 100);
					eixoY = eixoY + (RepositorioPecas.pecas.get(tipoPeca2).getComprimentoX() * 100);

					//DEFINE PRIMEIRA PECA TORRE
					peca1.setId(String.valueOf(identificacaoPeca));
					peca1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca));
					peca1.setTipo(tipoPeca2);
					peca1.setNoInicial( coordenada1.getId());
					peca1.setNoFinal(coordenada2.getId());

					//DEFINE SEGUNDA PECA TORRE
					peca2.setId(String.valueOf(identificacaoPeca+1));
					peca2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca+1));
					peca2.setTipo(tipoPeca1);
					peca2.setNoInicial(coordenada2.getId());
					peca2.setNoFinal(coordenada3.getId());

					//DEFINE TERCEIRA PECA TORRE
					peca3.setId(String.valueOf(identificacaoPeca+2));
					peca3.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca+2));
					peca3.setTipo(tipoPeca2);
					peca3.setNoInicial(coordenada3.getId());
					peca3.setNoFinal(coordenada4.getId());

					//DEFINE QUARTA PECA TORRE
					peca4.setId(String.valueOf(identificacaoPeca+3));
					peca4.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca+3));
					peca4.setTipo(tipoPeca1);
					peca4.setNoInicial(coordenada4.getId());
					peca4.setNoFinal(coordenada1.getId());

					//ADICIONA AS COORDENADAS NA LISTA
					listaCoordenadas.add(coordenada1);
					listaCoordenadas.add(coordenada2);
					listaCoordenadas.add(coordenada3);
					listaCoordenadas.add(coordenada4);

					//ADICIONA AS PECAS NA LISTA
					listaPecasFinais.add(peca1);
					listaPecasFinais.add(peca2);					
					listaPecasFinais.add(peca3);			
					listaPecasFinais.add(peca4);

					identificacaoPeca = identificacaoPeca + 4;
				}

				//DEFINE COORDENADAS PARA ESCORAS VINCULADAS A TRAVESSAS	
				if (tipoPeca1.contains(SciaXMLContantes.ESC) && tipoPeca2.contains(SciaXMLContantes.KITRV)){				   
					coordenada1 = new Coordenada();
					coordenada2 = new Coordenada();
					coordenada3 = new Coordenada();
					coordenada4 = new Coordenada();

					peca1 = new Peca();
					peca2 = new Peca();

					coordenada1.setId( String.valueOf(no));
					coordenada1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada1.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada1.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);

					coordenada3.setId( String.valueOf(no));
					coordenada3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada3.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada3.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada3.setZ(getAltura(dados));			    

					eixoY = eixoY + (RepositorioPecas.pecas.get(tipoPeca2).getComprimentoX() * 100);

					coordenada2.setId( String.valueOf(no));
					coordenada2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada2.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada2.setY((eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML));
					

					coordenada4.setId( String.valueOf(no));
					coordenada4.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada4.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada4.setY((eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML));
					coordenada4.setZ(getAltura(dados));		

					//DEFINE PRIMEIRA PECA ESCORA
					peca1.setId(String.valueOf(identificacaoPeca));
					peca1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca));
					peca1.setTipo(tipoPeca1);
					peca1.setNoInicial( coordenada1.getId());
					peca1.setNoFinal( coordenada3.getId());

					//DEFINE SEGUNDA PECA ESCORA
					peca2.setId(String.valueOf(identificacaoPeca+1));
					peca2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca+1));
					peca2.setTipo(tipoPeca1);
					peca2.setNoInicial( coordenada2.getId());
					peca2.setNoFinal( coordenada4.getId());

					listaCoordenadas.add(coordenada1);
					listaCoordenadas.add(coordenada2);
					listaCoordenadas.add(coordenada3);
					listaCoordenadas.add(coordenada4);

					//ADICIONA AS PECAS NA LISTA
					listaPecasFinais.add(peca1);
					listaPecasFinais.add(peca2);	

					identificacaoPeca = identificacaoPeca + 2;
				}
				
				//DEFINE COORDENADAS PARA ESCORAS VINCULADAS A TRAVESSAS	
				if (tipoPeca1.contains(SciaXMLContantes.KITRV) && tipoPeca2.contains(SciaXMLContantes.ESC)){				   
					coordenada1 = new Coordenada();
					coordenada2 = new Coordenada();				    
					coordenada3 = new Coordenada();
					coordenada4 = new Coordenada();

					peca1 = new Peca();
					peca2 = new Peca();
					
					coordenada1.setId( String.valueOf(no));
					coordenada1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada1.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada1.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);

					coordenada3.setId( String.valueOf(no));
					coordenada3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada3.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada3.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada3.setZ(getAltura(dados));			    

					 
					
					eixoX = eixoX + (RepositorioPecas.pecas.get(tipoPeca1).getComprimentoX() * 100); 
					
					  
					coordenada2.setId( String.valueOf(no));
					coordenada2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada2.setX((eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML));
					coordenada2.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);

					coordenada4.setId( String.valueOf(no));
					coordenada4.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada4.setX((eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML));
					coordenada4.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada4.setZ(getAltura(dados));	
					
					eixoX = eixoX - (RepositorioPecas.pecas.get(tipoPeca1).getComprimentoX() * 100); 
					  
					//DEFINE PRIMEIRA PECA ESCORA
					peca1.setId(String.valueOf(identificacaoPeca));
					peca1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca));
					peca1.setTipo(tipoPeca2);
					peca1.setNoInicial( coordenada1.getId());
					peca1.setNoFinal( coordenada3.getId());

					//DEFINE SEGUNDA PECA ESCORA
					peca2.setId(String.valueOf(identificacaoPeca+1));
					peca2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca+1));
					peca2.setTipo(tipoPeca2);
					peca2.setNoInicial( coordenada2.getId());
					peca2.setNoFinal( coordenada4.getId());
					  
					listaCoordenadas.add(coordenada1);
					listaCoordenadas.add(coordenada2);
					listaCoordenadas.add(coordenada3);
					listaCoordenadas.add(coordenada4);

					//ADICIONA AS PECAS NA LISTA
					listaPecasFinais.add(peca1);
					listaPecasFinais.add(peca2);	

					identificacaoPeca = identificacaoPeca + 2;
					 
				}
				
				 
				//DEFINE COORDENADAS PARA ESCORAS VINCULADAS A ESCORAS	
				if (tipoPeca1.contains(SciaXMLContantes.ESC) && tipoPeca2.contains(SciaXMLContantes.ESC)){				   
					coordenada1 = new Coordenada();
					coordenada3 = new Coordenada();

					peca1 = new Peca();
					 
					coordenada1.setId( String.valueOf(no));
					coordenada1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada1.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada1.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);

					coordenada3.setId( String.valueOf(no));
					coordenada3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada3.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada3.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada3.setZ(getAltura(dados));	 

					//DEFINE PRIMEIRA PECA ESCORA
					peca1.setId(String.valueOf(identificacaoPeca));
					peca1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca));
					peca1.setTipo(tipoPeca1);
					peca1.setNoInicial( coordenada1.getId());
					peca1.setNoFinal( coordenada3.getId());

					listaCoordenadas.add(coordenada1);
					listaCoordenadas.add(coordenada3); 

					//ADICIONA AS PECAS NA LISTA
					listaPecasFinais.add(peca1); 

					identificacaoPeca = identificacaoPeca + 1;
				}

				//DEFINE COORDENADAS PARA CRUZETAS
				if (tipoPeca1.contains(SciaXMLContantes.CRU) || tipoPeca2.contains(SciaXMLContantes.CRU)){

					coordenada1 = new Coordenada();
					coordenada2 = new Coordenada();				    
					coordenada3 = new Coordenada();
					coordenada4 = new Coordenada();

					peca1 = new Peca();
					peca2 = new Peca();

					coordenada1.setId( String.valueOf(no));
					coordenada1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada1.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada1.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);

					coordenada3.setId( String.valueOf(no));
					coordenada3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada3.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada3.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada3.setZ(getAltura(dados));			    

					eixoY = eixoY + 50;

					coordenada2.setId( String.valueOf(no));
					coordenada2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada2.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada2.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);

					coordenada4.setId( String.valueOf(no));
					coordenada4.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(no++));					
					coordenada4.setX(eixoX / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada4.setY(eixoY / SciaXMLContantes.PRECISAO_ENVIO_COORDENADAS_XML);
					coordenada4.setZ(getAltura(dados));		

					//DEFINE PRIMEIRA PECA ESCORA
					peca1.setId(String.valueOf(identificacaoPeca));
					peca1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca));
					peca1.setTipo("Escora A");
					peca1.setNoInicial( coordenada1.getId());
					peca1.setNoFinal( coordenada3.getId());

					//DEFINE SEGUNDA PECA ESCORA
					peca2.setId(String.valueOf(identificacaoPeca+1));
					peca2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca+1));
					peca2.setTipo("Escora A");
					peca2.setNoInicial( coordenada2.getId());
					peca2.setNoFinal( coordenada4.getId());

					listaCoordenadas.add(coordenada1);
					listaCoordenadas.add(coordenada2);
					listaCoordenadas.add(coordenada3);
					listaCoordenadas.add(coordenada4);

					//ADICIONA AS PECAS NA LISTA
					listaPecasFinais.add(peca1);
					listaPecasFinais.add(peca2);	

					identificacaoPeca = identificacaoPeca + 2;
					eixoX = eixoX - (RepositorioPecas.pecas.get(tipoPeca2).getComprimentoY() * 100);
					eixoY = eixoY + 50;

				}

				//Não soma o vão se for a última peça do eixo Y.
				if (j!=dados.getPecasY().size() -1 && tipoPeca1!=SciaXMLContantes.CRU &&  tipoPeca2!=SciaXMLContantes.CRU){
					eixoY = eixoY + dados.getVaoDeApoioY();	
				}else{
					eixoX = eixoX + (RepositorioPecas.pecas.get(tipoPeca1).getComprimentoX() * 100);
				}			 			
			}	

			eixoX = eixoX + dados.getVaoDeApoioX();
			eixoY = Double.parseDouble(dados.getFolgaLajeY1());
		}

		dados.setListaDeNos(listaCoordenadas);
		dados.setPecasFinais(listaPecasFinais);

		return dados;

	}

	public static boolean isLajeCompativel(SumarioDados sumarioDados){

		boolean retorno = true;

		if (sumarioDados.getComprimentoTotalEixoX()
				+ sumarioDados.getVaoDeApoioX()
				+ (Double.parseDouble(sumarioDados.getFolgaLajeX1()) + Double.parseDouble(sumarioDados.getFolgaLajeX1())) >
		Double.parseDouble(sumarioDados.getMedidaLageX())) {
			retorno = false;
		}

		if (sumarioDados.getComprimentoTotalEixoY()
				+ sumarioDados.getVaoDeApoioY()
				+ (Double.parseDouble(sumarioDados.getFolgaLajeY1()) + Double.parseDouble(sumarioDados.getFolgaLajeY1())) > 
		Double.parseDouble(sumarioDados.getMedidaLageY())) {
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

			if (SciaXMLContantes.EIXO_X.equals(eixo) && posicaoCursor != Integer.parseInt(posicaoX)){
				continue;
			}
			
			if (SciaXMLContantes.EIXO_Y.equals(eixo) && posicaoCursor != Integer.parseInt(posicaoY)){
				continue;
			}
						
			if (SciaXMLContantes.EIXO_X.equals(eixo) && posicaoCursor == Integer.parseInt(posicaoX)){
				valorRegistro = map.get(string);
				registro1 = valorRegistro.substring(0, valorRegistro.indexOf("|"));
				
				if (registro1.equalsIgnoreCase(SciaXMLContantes.KITRV)){
					return true;
				}			
			}

			if (SciaXMLContantes.EIXO_Y.equals(eixo) && posicaoCursor == Integer.parseInt(posicaoY)){
				valorRegistro = map.get(valorRegistro);
				registro1 = valorRegistro.substring(valorRegistro.indexOf("|") + 1, valorRegistro.length());
				
				if (registro1.equalsIgnoreCase(SciaXMLContantes.KITRV)){
					return true;
				}			
			}	

		}

		return false;		
	}
	
	private static Double getAltura (SumarioDados dados){
		Double retorno = 0.0;
		
		if (dados != null){
			Double peDireto = !"".equals(SciaXMLUtils.checkString(dados.getPeDireito())) ? Double.parseDouble(dados.getPeDireito())/100.0 : 0.0;
			Double espessuraLaje = !"".equals(SciaXMLUtils.checkString(dados.getEspessura())) ? Double.parseDouble(dados.getEspessura())/100.0 : 0.0;
			Double espessuraCompensado = !"".equals(SciaXMLUtils.checkString(dados.getEspessuraCompensado())) ? Double.parseDouble(dados.getEspessuraCompensado())/100.0 : 0.0;
			
			retorno =  peDireto - espessuraLaje - espessuraCompensado;
		}
		
		return retorno;
	}
	
	public static Double getAlturaUtil(){
		Double altura = 0.0;
		if (!"".equals(SciaXMLUtils.checkString(Calculo.dados.getPeDireito()))){
    		Double peDireito = Double.parseDouble(Calculo.dados.getPeDireito())/100.0;
    		Double espessuraLaje = Double.parseDouble(Calculo.dados.getEspessura())/100.0;
    		Double espessuraCompensado = Double.parseDouble(Calculo.dados.getEspessuraCompensado())/100.0;
    		Double espessuraVigaPrincipal = CalculoVigas.getAlturaViga();
    		Double espessuraVigaSecundaria = CalculoVigas.getAlturaViga();  //TODO: alterar para viga secundaria
    		Double tamanhoPosteEspecial = Calculo.dados.getPosteEspecial().getComprimento();
    		
    		altura = peDireito - espessuraCompensado - espessuraLaje - espessuraVigaPrincipal - espessuraVigaSecundaria - tamanhoPosteEspecial;
    	}
		return altura;
	} 

}