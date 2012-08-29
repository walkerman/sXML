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

public class CalculoVigas {

	private SumarioDados sumarioDados;
	private List<Peca> vigasPrincipaisFinais;
	
	public CalculoVigas(SumarioDados sumario) {
		this.sumarioDados = sumario;
		
		if (this.sumarioDados != null){
			realizarCalculo(this.sumarioDados.getPecasFinais());
		}
	}
	
	public void realizarCalculo (List<Peca> pecas){
		if (pecas != null && pecas.size() > 0){
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
					
			calcularVigasX(pontoXInicialEstrutura,1,1);
			
			if (this.vigasPrincipaisFinais != null){
				Double total = 0.0;
				for (Peca peca : this.vigasPrincipaisFinais) {
					total += peca.getComprimento();
				}
				
				List<Coordenada> nosX = getNosX(this.sumarioDados.getPecasFinais());
				Collections.sort(nosX,new CoordenadaSorterX());
				
				if (total < nosX.get(0).getX()){
					System.out.println("Fudeu");
				}
			}
			
			System.out.println("Vigas Selecionadas = \n" + this.vigasPrincipaisFinais);
		}
	}
	
	// Método responsável por obter todos os nós gerados em y (com exceção de cruzetas)
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
	
	// Método responsável por obter todos os nós gerados em x
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
		
	// Método responsável por tirar as duplicidades de nós em y
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
	
	// Método responsável por tirar as duplicidades de nós em X
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
			
			if (this.vigasPrincipaisFinais == null)
				this.vigasPrincipaisFinais  = new ArrayList<Peca>();
			
			for (Coordenada coordenada : nosX) {
				if (this.vigasPrincipaisFinais != null && this.vigasPrincipaisFinais.size() > 0){
					Double total = 0.0;
					for (Peca peca : this.vigasPrincipaisFinais) {
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
						this.vigasPrincipaisFinais.add(peca);
						
						Double xInicial = (pontoInicial-transpasse);
						Double xFinal = (pontoInicial-transpasse+peca.getComprimento());
						
						Coordenada coordenada1 = new Coordenada(); 
						Coordenada coordenada2 = new Coordenada();
						
						coordenada1.setId(150+identificacaoNo.toString());
						coordenada1.setName("VP"+SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificacaoNo++));					
						coordenada1.setX(xInicial);
						coordenada1.setY(5.0);

						coordenada2.setId(150+identificacaoNo.toString());
						coordenada2.setName("VP"+SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificacaoNo++));					
						coordenada2.setX(xFinal);
						coordenada2.setY(5.0);		
						 
						Peca peca1 = new Peca();
						
						peca1.setId("VP"+String.valueOf(identificacaoPeca));
						peca1.setName("VP"+SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca++));
						peca1.setTipo(peca.getTipo());
						peca1.setNoInicial(coordenada1.getId());
						peca1.setNoFinal(coordenada2.getId());
						
						this.sumarioDados.getListaDeNos().add(coordenada1);
						this.sumarioDados.getListaDeNos().add(coordenada2);
						
						this.sumarioDados.getPecasFinais().add(peca1);	
						
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

	public List<Peca> getVigasPrincipaisFinais() {
		return vigasPrincipaisFinais;
	}

	public void setVigasPrincipaisFinais(List<Peca> vigasPrincipaisFinais) {
		this.vigasPrincipaisFinais = vigasPrincipaisFinais;
	}
}
