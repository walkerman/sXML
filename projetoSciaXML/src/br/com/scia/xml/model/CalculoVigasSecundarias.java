package br.com.scia.xml.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.xml.Coordenada;
import br.com.scia.xml.util.CoordenadaSorterX;
import br.com.scia.xml.util.CoordenadaSorterY;
import br.com.scia.xml.util.PecaSorter;
import br.com.scia.xml.util.SciaXMLContantes;
import br.com.scia.xml.util.SciaXMLUtils;

public class CalculoVigasSecundarias {

	private SumarioDados sumarioDados;
	private List<Peca> vigasSecundariasFinais;
	private Integer identificadorPecas;
	private Integer identificadorNos;
	
	public CalculoVigasSecundarias() {
		this.sumarioDados = Calculo.dados;
				
		if (this.sumarioDados != null){
			this.identificadorNos = this.sumarioDados.getListaDeNos().size();
			this.identificadorPecas = this.sumarioDados.getPecasFinais().size();
		}
	}
	
	public void realizarCalculo (){
		List<Peca> pecas = this.sumarioDados.getPecasFinais();
		
		if (pecas != null && pecas.size() > 0){
			System.out.println("########## Cálculo de Vigas Secundarias ##########");
			
			List<Coordenada> coordenadasInicioVigas = getNosY(pecas);
			Collections.sort(coordenadasInicioVigas,new CoordenadaSorterY());
			System.out.println("Coordenadas Y = \n" + coordenadasInicioVigas);
			
			
			List<Coordenada> coordenadasXVigas = getNosX(pecas);
			Collections.sort(coordenadasXVigas,new CoordenadaSorterX());
			System.out.println("Coordenadas X = \n" + coordenadasXVigas);
			
			if (this.sumarioDados.getVigasPrincipais() != null && this.sumarioDados.getVigasPrincipais().size() > 0)
				System.out.println("Vigas = \n" + this.sumarioDados.getVigasPrincipais());
			
			// Calculo pans (inicia a varredura para descobrir quais as vigas que serão utilizadas);
			Double pontoXInicialEstrutura = Double.parseDouble(this.sumarioDados.getCoordenadaX());
					
			calcularVigasX(pontoXInicialEstrutura,this.identificadorNos,this.identificadorPecas);
			
			if (this.vigasSecundariasFinais != null){
				Double total = 0.0;
				for (Peca peca : this.vigasSecundariasFinais) {
					total += peca.getComprimento();
				}
				
				List<Coordenada> nosX = getNosX(this.sumarioDados.getPecasFinais());
				Collections.sort(nosX,new CoordenadaSorterX());
				
				if (total < nosX.get(0).getX()){
					//TODO: enviar exceção para interface 
					System.out.println("Não foi possível encontrar composição de vigas principais.");
				}
			}
			
			
			
			System.out.println("Vigas Selecionadas = \n" + this.vigasSecundariasFinais);
		}
	}
	
	private List<Coordenada> getNosY(List<Peca> pecas){
		List<Coordenada> retorno = null;
		
		List<String> nosIniciais = new ArrayList<String>();		
		for (Peca peca : pecas) {
			if (!peca.getTipo().startsWith(SciaXMLContantes.CRU))
				nosIniciais.add(peca.getNoInicial());
		}
		
		List<Coordenada> listaCoordenadas = this.sumarioDados.getListaDeNos();
		
		if (listaCoordenadas != null && listaCoordenadas.size() > 0){
			retorno = new ArrayList<Coordenada>();
			for (Coordenada coordenada : listaCoordenadas) {
				for (String c : nosIniciais) {
					if (c.toString().equals(coordenada.getId())){
						retorno.add(coordenada);
					}
				}
				
			}
		}
		
		return getCoordenadasY(retorno);
	}
	
	private List<Coordenada> getNosX(List<Peca> pecas){
		List<Coordenada> retorno = null;
		
		List<String> nosIniciais = new ArrayList<String>();		
		for (Peca peca : pecas) {
				nosIniciais.add(peca.getNoInicial());
		}
		
		List<Coordenada> listaCoordenadas = this.sumarioDados.getListaDeNos();
		
		if (listaCoordenadas != null && listaCoordenadas.size() > 0){
			retorno = new ArrayList<Coordenada>();
			for (Coordenada coordenada : listaCoordenadas) {
				for (String c : nosIniciais) {
					if (c.toString().equals(coordenada.getId())){
						retorno.add(coordenada);
					}
				}
				
			}
		}
		
		return getCoordenadasX(retorno);
	}
		
	private List<Coordenada> getCoordenadasY (List<Coordenada> coordenadas){
		List<Coordenada> retorno = null;
		Set<Double> valoresY = new HashSet<Double>();
		
		// Removendo os itens repetidos
		for (Coordenada coordenada : coordenadas) {
			valoresY.add(coordenada.getY());
		}
		
		if (valoresY != null && valoresY.size() > 0){
			retorno = new ArrayList<Coordenada>();
			for (Double valor : valoresY) {
				Coordenada c = new Coordenada();
				c.setY(valor);
				retorno.add(c);
			} 
		}
		
		return retorno;
	}
	
	private List<Coordenada> getCoordenadasX (List<Coordenada> coordenadas){
		List<Coordenada> retorno = null;
		Set<Double> valoresX = new HashSet<Double>();
		
		// Removendo os itens repetidos
		for (Coordenada coordenada : coordenadas) {
			valoresX.add(coordenada.getX());
		}
		
		if (valoresX != null && valoresX.size() > 0){
			retorno = new ArrayList<Coordenada>();
			for (Double valor : valoresX) {
				Coordenada c = new Coordenada();
				c.setX(valor);
				retorno.add(c);
			} 
		}
		
		return retorno;
	}

	private void calcularVigasX (Double pontoInicial, Integer identificacaoNo, Integer identificacaoPeca){		
		String transpasseInformado = SciaXMLUtils.checkString(this.sumarioDados.getTranspassePrincipais());
		
		if (!"".equals(transpasseInformado)){
			Double transpasse = Double.parseDouble(transpasseInformado)/100.0;
			Double transpaseTotal = transpasse*2;
			
			List<Coordenada> nosX = getNosX(this.sumarioDados.getPecasFinais());
			Collections.sort(nosX,new CoordenadaSorterX());
			
			if (this.vigasSecundariasFinais == null)
				this.vigasSecundariasFinais  = new ArrayList<Peca>();
			
			for (Coordenada coordenada : nosX) {
				if (this.vigasSecundariasFinais != null && this.vigasSecundariasFinais.size() > 0){
					Double total = 0.0;
					for (Peca peca : this.vigasSecundariasFinais) {
						total += peca.getComprimento();
					}
					
					if (total >= nosX.get(0).getX()){
						break;
					}
				}
								
				if (coordenada.getX() == nosX.get(nosX.size()-1).getX())
					continue;
				
				Double x = coordenada.getX() + transpaseTotal - pontoInicial;
			
				List<Peca> vigas = this.sumarioDados.getVigasPrincipais();
				Collections.sort(vigas,new PecaSorter());
			
				boolean achou = false;
				for (Peca peca : vigas) {
					if (peca.getComprimento() >= x){
						this.vigasSecundariasFinais.add(peca);
						
						Double xInicial = (pontoInicial-transpasse);
						Double xFinal = (pontoInicial-transpasse+peca.getComprimento());
						
						//TODO: obter desvio correto da peça de entrada (como garantir a criação das vigas apenas um x, inicialmente?)
						// Double desvioY = 0.08;
						
						List<Coordenada> coordenadasY = getCoordenadasY(this.sumarioDados.getListaDeNos());
						
						for (Coordenada coordY : coordenadasY) {
							Coordenada coordenada1 = new Coordenada(); 
							Coordenada coordenada2 = new Coordenada();
							
							Double altura =  Calculo.getAlturaUtil();
							
							coordenada1.setId(identificacaoNo.toString());
							coordenada1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificacaoNo++));					
							coordenada1.setX(xInicial);
							coordenada1.setY(coordY.getY());
							coordenada1.setZ(altura);
							
							coordenada2.setId(identificacaoNo.toString());
							coordenada2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificacaoNo++));					
							coordenada2.setX(xFinal);
							coordenada2.setY(coordY.getY());
							coordenada2.setZ(altura);
							 
							Peca peca1 = new Peca();
							
							peca1.setId(String.valueOf(identificacaoPeca));
							peca1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca++));
							peca1.setTipo(peca.getTipo());
							peca1.setNoInicial(coordenada1.getId());
							peca1.setNoFinal(coordenada2.getId());
							
							this.sumarioDados.getListaDeNos().add(coordenada1);
							this.sumarioDados.getListaDeNos().add(coordenada2);
							
							this.sumarioDados.getPecasFinais().add(peca1);	
						}
						
						achou = true;
						break;
					}
				}
				
				if (achou){
					calcularVigasX((x - transpaseTotal), identificacaoNo, identificacaoPeca);
					break;
				}
			}
		}
	}

	public List<Peca> getVigasSecundariasFinais() {
		return vigasSecundariasFinais;
	}

	public void setVigasSecundariasFinais(List<Peca> vigasSecundariasFinais) {
		this.vigasSecundariasFinais = vigasSecundariasFinais;
	}
	
}
