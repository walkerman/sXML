package br.com.scia.xml.model;

import java.util.ArrayList;
import java.util.List;

import br.com.scia.xml.entity.exception.CalculoException;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.xml.Coordenada;
import br.com.scia.xml.util.SciaXMLContantes;

public class Calculo {

	public static SumarioDados calculaEstrutura(SumarioDados dados) throws CalculoException{
		
		dados.setVaoDeApoioX(calculaVaoDeApoio(dados, SciaXMLContantes.EIXO_X));
		dados.setVaoDeApoioY(calculaVaoDeApoio(dados, SciaXMLContantes.EIXO_Y));
		dados.setListaDeNos(Calculo.calculaCoordenadas(dados));
		dados = Calculo.aninhaNo(dados);
		
		return dados;
	}
	
	
	public static List<Coordenada> recuperaListaDeNos(SumarioDados dados) {
		try {			


			List<Coordenada> lista = calculaCoordenadas(dados);

			for (Coordenada coordenada : lista) {
				System.out.println(coordenada.getNo()+ " Coordenada X " + coordenada.getX() +  " Coordenada Y " + coordenada.getY() );
			}


		} catch (CalculoException e) {
			System.out.println("Problemas na realização do cálculo - " + e.getMessage());
		}
		return null;		
	}

	public static Double calculaVaoDeApoio(SumarioDados dados, String eixo) throws CalculoException{

		Double vaoDeApoio = 0.0;

		//		if (comprimentoTotal <= 0 
		//				|| quantidadeDeTorres == null
		//				|| quantidadeDeTorres <= 0 
		//				|| comprimentoTravessa <= 0
		//				|| folgaEixoX <= 0 
		//				|| folgaEixoY <= 0) {
		//			throw new CalculoException("calculaVaoDeApoio() - Parâmetros de entrada incorretos.");
		//		}
		
		if (eixo.equalsIgnoreCase(SciaXMLContantes.EIXO_X)){
			switch (dados.getPecasX().size()) {
			case 1:
				vaoDeApoio = Double.parseDouble(dados.getMedidaLageX()) - dados.getComprimentoTotalEixoX() - (Double.parseDouble(dados.getFolgaLajeX1()) + Double.parseDouble(dados.getFolgaLajeX2()))  / 2 ;
				break;
			default:
				vaoDeApoio = (Double.parseDouble(dados.getMedidaLageX()) - dados.getComprimentoTotalEixoX() -  (Double.parseDouble(dados.getFolgaLajeX1()) + Double.parseDouble(dados.getFolgaLajeX2()))) / (dados.getPecasX().size() - 1);
				break;
			}		
		}
		
		if (eixo.equalsIgnoreCase(SciaXMLContantes.EIXO_Y)){
			switch (dados.getPecasY().size()) {
			case 1:
				vaoDeApoio = Double.parseDouble(dados.getMedidaLageY()) - dados.getComprimentoTotalEixoY()  - (Double.parseDouble(dados.getFolgaLajeY1()) + Double.parseDouble(dados.getFolgaLajeY2())) / 2 ;
				break;
			default:
				vaoDeApoio = (Double.parseDouble(dados.getMedidaLageY()) -  dados.getComprimentoTotalEixoY() - (Double.parseDouble(dados.getFolgaLajeY1()) + Double.parseDouble(dados.getFolgaLajeY2()))) / (dados.getPecasY().size() - 1);
				break;
			}		
		}		
		
		return vaoDeApoio;
	}

	public static double calculaComprimentoPeca(Double noInicial, Double noFinal) throws CalculoException {

		if (noInicial <= 0 || noFinal <= 0) {
			throw new CalculoException("calculaComprimentoPeca() - Parâmetros de entrada incorretos.");
		}
		return noFinal - noInicial;
	}

	public static List<Coordenada> calculaCoordenadas(SumarioDados sumarioDados) throws CalculoException{

		List<Coordenada> listaCoordenadas = new ArrayList<Coordenada>();
		int no = 1;

		List<Double> listaEixoX = new ArrayList<Double>();
		List<Double> listaEixoY = new ArrayList<Double>();

		Coordenada coordenada = null;
		boolean calculouFolga = false;
		boolean ehVao = false;
		Double pontos = 0.0;
		int finalizouPeca = 0;

//		if((sumarioDados.getPecasX().size() * sumarioDados.getComprimentoTotalEixoX()) + (sumarioDados.getPecasX().size() - 1) * sumarioDados.getVaoDeApoioX() > Double.parseDouble(sumarioDados.getMedidaLageX())){
//			throw new CalculoException("Tamanho da laje insuficiente para os parâmetros");
//		}

		for (int i = 0; i < sumarioDados.getPecasX().size() * 2; i++) {
			

			if (!calculouFolga){;
			calculouFolga=true;
			pontos = Double.parseDouble(sumarioDados.getFolgaLajeX1());
			listaEixoX.add(pontos);	
			continue;
			}

			if (!ehVao){
				pontos += sumarioDados.getPecasX().get(finalizouPeca).getComprimentoPecaX();
				listaEixoX.add(pontos);
				ehVao=true;
			}else{
				pontos += sumarioDados.getVaoDeApoioX();
				listaEixoX.add(pontos);
				ehVao=false;
			}
			
			if(i%2 == 0){
				finalizouPeca+=1;
			}

			
		}

		ehVao = false;
		calculouFolga = false;
		pontos = 0.0;
		finalizouPeca=0;

		for (int y = 0; y < sumarioDados.getPecasY().size() * 2; y++) {
			
			if (!calculouFolga){
				pontos = Double.parseDouble(sumarioDados.getFolgaLajeY1());
				listaEixoY.add(pontos);
				calculouFolga=true;				
				continue;
			}

			if (!ehVao){
				pontos += sumarioDados.getPecasX().get(finalizouPeca).getComprimentoPecaY();
				listaEixoY.add(pontos);
				ehVao=true;
			}else{
				pontos += sumarioDados.getVaoDeApoioY();
				listaEixoY.add(pontos);
				ehVao=false;
			}
			
			if(y%2 == 0){
				finalizouPeca+=1;
			}
			

		}

		for (Double x : listaEixoX) {
			for (Double y : listaEixoY) {
				coordenada = new Coordenada();
				coordenada.setX(x.doubleValue());
				coordenada.setY(y.doubleValue());
				listaCoordenadas.add(coordenada);
			}			
		}

		for (Coordenada coordenadaNo : listaCoordenadas) { 
			coordenadaNo.setNo(no++); 
		} 

		return listaCoordenadas;

	}

	public static SumarioDados aninhaNo(SumarioDados dados){

		Integer eixoX = 3;
		Integer eixoY = 2;
		int posicao = 1;
		int no1;
		int no2;
		int no3;
		int no4;
		List<Peca> listaPecaX = new ArrayList<Peca>();
		List<Peca> listaPecaY = new ArrayList<Peca>();
		Peca peca1 = new Peca();
		Peca peca2= new Peca();
		Peca peca3 = new Peca();
		Peca peca4 = new Peca();

		for (int j = 0; j < eixoX; j++) {
			for (int i = 1; i <= (eixoY * 2); i++) {
				no1 = posicao;
				no2 = no1+1;
				no3 = posicao +  (eixoY*2) +1;
				no4 = no3+1;

				peca1.setTipoEquipamento(SciaXMLContantes.KITRV);
				peca2.setTipoEquipamento(SciaXMLContantes.KITRV);
				peca3.setTipoEquipamento(SciaXMLContantes.KITRV);
				peca4.setTipoEquipamento(SciaXMLContantes.KITRV);

				peca1.setNoInicial(no1);
				peca1.setNoFinal(no2);

				peca2.setNoInicial(no2);
				peca2.setNoFinal(no3);

				peca3.setNoInicial(no3);
				peca3.setNoFinal(no4);

				peca4.setNoInicial(no4);
				peca4.setNoFinal(no1);

				listaPecaX.add(peca1);
				listaPecaX.add(peca3);

				listaPecaY.add(peca2);
				listaPecaY.add(peca4);

			}
			posicao = ((eixoX * 2) * 2) + 1;
		}

		dados.setPecasX(listaPecaX);
		dados.setPecasY(listaPecaY);

		return dados;
	}


}