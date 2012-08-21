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
		dados.setListaDeNos(Calculo.defineCoordenadas(dados));
		Calculo.aninhaNo(dados);

		return dados;
	}

	public static Double calculaVaoDeApoio(SumarioDados dados, String eixo) throws CalculoException{

		Double vaoDeApoio = 0.0;

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

	public static List<Double> calculaCoordenadas(SumarioDados sumarioDados, String eixo){

		Double pontos = 0.0;
		int finalizouPeca = 0;
		boolean imprimeVao = false;	
		boolean calculouFolgaLaje = false;		
		List<Double> listaEixo = new ArrayList<Double>();

		if (eixo.equalsIgnoreCase(SciaXMLContantes.EIXO_X)){
			for (int i = 0; i < sumarioDados.getPecasX().size() * 2; i++) {

				if (!calculouFolgaLaje){;
				calculouFolgaLaje=true;
				pontos = Double.parseDouble(sumarioDados.getFolgaLajeX1());
				listaEixo.add(pontos);	
				continue;
				}

				if (!imprimeVao){
					pontos += sumarioDados.getPecasX().get(finalizouPeca).getComprimentoPecaX();
					listaEixo.add(pontos);
					imprimeVao=true;
				}else{
					pontos += sumarioDados.getVaoDeApoioX();
					listaEixo.add(pontos);
					imprimeVao=false;
				}

				if(i%2 == 0){
					finalizouPeca+=1;
				}
			}
		}

		if (eixo.equalsIgnoreCase(SciaXMLContantes.EIXO_Y)){			
			for (int y = 0; y < sumarioDados.getPecasY().size() * 2; y++) {

				if (!calculouFolgaLaje){
					pontos = Double.parseDouble(sumarioDados.getFolgaLajeY1());
					listaEixo.add(pontos);
					calculouFolgaLaje=true;				
					continue;
				}

				if (!imprimeVao){
					pontos += sumarioDados.getPecasX().get(finalizouPeca).getComprimentoPecaX();
					listaEixo.add(pontos);
					imprimeVao=true;
				}else{
					pontos += sumarioDados.getVaoDeApoioY();
					listaEixo.add(pontos);
					imprimeVao=false;
				}

				if(y%2 == 0){
					finalizouPeca+=1;
				}			
			}
		}

		return listaEixo;
	}

	public static List<Coordenada> defineCoordenadas(SumarioDados sumarioDados) throws CalculoException{

		List<Coordenada> listaCoordenadas = new ArrayList<Coordenada>();
		int no = 1;

		List<Double> listaEixoX = new ArrayList<Double>();
		List<Double> listaEixoY = new ArrayList<Double>();

		Coordenada coordenada = null;

		//		if((sumarioDados.getPecasX().size() * sumarioDados.getComprimentoTotalEixoX()) + (sumarioDados.getPecasX().size() - 1) * sumarioDados.getVaoDeApoioX() > Double.parseDouble(sumarioDados.getMedidaLageX())){
		//			throw new CalculoException("Tamanho da laje insuficiente para os parâmetros");
		//		}

		listaEixoX = Calculo.calculaCoordenadas(sumarioDados, SciaXMLContantes.EIXO_X);
		listaEixoY = Calculo.calculaCoordenadas(sumarioDados, SciaXMLContantes.EIXO_Y);

		for (Double x : listaEixoX) {
			for (Double y : listaEixoY) {
				coordenada = new Coordenada();
				coordenada.setName("N"+String.valueOf(no));				
				listaCoordenadas.add(coordenada);
				coordenada.setX(x.doubleValue()/100);
				coordenada.setY(y.doubleValue()/100);
				coordenada.setId(String.valueOf(no));				
				no++;
				System.out.println(coordenada.getId()+"-"+coordenada.getName());
			}			
		}
		
		return listaCoordenadas;
	}

	public static SumarioDados aninhaNo(SumarioDados dados){

		int posicaoCursor = 1;
		int no1;
		int no2;
		int no3;
		int no4;
		int pecaEmY = 0;
		int idPeca = 1;

		List<Peca> pecas = new ArrayList<Peca>();

		Peca peca1 = null;
		Peca peca2 = null;
		Peca peca3 = null;
		Peca peca4 = null;

		for (int j = 0; j < dados.getPecasX().size(); j++) {

			for (int i = 1; i <= (dados.getPecasY().size() * 2);) {

				peca1 = new Peca();
				peca2 = new Peca();
				peca3 = new Peca();
				peca4 = new Peca();

				no1 = posicaoCursor;
				no2 = no1 + 1;
				no3 = no1 +  (dados.getPecasY().size() * 2);
				no4 = no3 + 1;

				peca1.setId(String.valueOf(idPeca));
				peca1.setName("B"+String.valueOf(idPeca));
				peca2.setId(String.valueOf(idPeca+1));
				peca2.setName("B"+String.valueOf(idPeca+1));
				peca3.setId(String.valueOf(idPeca+2));
				peca3.setName("B"+String.valueOf(idPeca+2));
				peca4.setId(String.valueOf(idPeca+3));
				peca4.setName("B"+String.valueOf(idPeca+3));

				peca1.setTipo(dados.getPecasY().get(pecaEmY).getTipo());
				peca2.setTipo(dados.getPecasX().get(j).getTipo());
				peca3.setTipo(dados.getPecasY().get(pecaEmY).getTipo());
				peca4.setTipo(dados.getPecasX().get(j).getTipo());

				peca1.setNoInicial(no1);
				peca1.setNoFinal(no2);
				peca2.setNoInicial(no2);
				peca2.setNoFinal(no4);
				peca3.setNoInicial(no4);
				peca3.setNoFinal(no3);
				peca4.setNoInicial(no3);
				peca4.setNoFinal(no1);

				pecas.add(peca1);
				pecas.add(peca2);
				pecas.add(peca3);
				pecas.add(peca4);

				i = i + 2;
				posicaoCursor= posicaoCursor + 2;
				pecaEmY+=1;
				idPeca+=4;
			}
			posicaoCursor = posicaoCursor + (dados.getPecasY().size() * 2);
			pecaEmY=0;
		}

		dados.setPecasFinais(pecas);

		for (Peca peca : dados.getPecasFinais()) {
			System.out.println("Peca "+ peca.getId() + " " + peca.getNoInicial() + "-"+ peca.getNoFinal() );

		}

		return dados;
	}
}