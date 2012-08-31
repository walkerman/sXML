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

public class CalculoPostes {

	private SumarioDados sumarioDados;
	private List<Peca> postesFinais;
	private Integer identificadorPecas;
	private Integer identificadorNos;
	
	public CalculoPostes(SumarioDados sumario) {
		this.sumarioDados = sumario;
				
		if (this.sumarioDados != null){
			this.identificadorNos = this.sumarioDados.getListaDeNos().size()+1;
			this.identificadorPecas = this.sumarioDados.getPecasFinais().size()+1;
		}
	}
	
	public void realizarCalculo (){
		List<Peca> pecas = this.sumarioDados.getPecasFinais();
		
		if (pecas != null && pecas.size() > 0){
			System.out.println("########## Cálculo de Postes ##########");
			
			List<Coordenada> coordenadasTravessasY = getNosY(pecas);
			Collections.sort(coordenadasTravessasY,new CoordenadaSorterY());
			System.out.println("Coordenadas Y = \n" + coordenadasTravessasY);
			
			
			List<Coordenada> coordenadasTravessasX = getNosX(pecas);
			Collections.sort(coordenadasTravessasX,new CoordenadaSorterX());
			System.out.println("Coordenadas X = \n" + coordenadasTravessasX);
			
			List<Peca> postesTela = this.sumarioDados.getPostes();
			System.out.println(postesTela);
			
			postesFinais = getComposicaoPostes();
			System.out.println("Postes selecionados: " + postesFinais);
			
			replicarPostesSelecionados(coordenadasTravessasY, coordenadasTravessasX);
		}
	}
	
    private void replicarPostesSelecionados(List<Coordenada> coordenadasTravessasY, List<Coordenada> coordenadasTravessasX) {
		
    	List<Peca> postes = this.postesFinais;
    	
    	for (Coordenada coordX : coordenadasTravessasX) {
    		for (Coordenada coordY : coordenadasTravessasY) {		
			
			if (postes != null && postes.size() > 0){
				Peca peca1 = postes.get(0);
				Peca peca2 = postes.get(1);
				Peca peca3 = postes.get(2);
				
				Collections.sort(postes,new PecaSorter());
				
				double altura = 0.01;
				
				Coordenada coordenada1 = new Coordenada(); 
				Coordenada coordenada2 = new Coordenada();
				Coordenada coordenada3 = new Coordenada(); 
				Coordenada coordenada4 = new Coordenada();
				
				coordenada1.setId(identificadorNos.toString());
				coordenada1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1.setX(coordX.getX());
				coordenada1.setY(coordY.getY());
				coordenada1.setZ(altura);
				
				altura += peca1.getComprimento();
				coordenada2.setId(identificadorNos.toString());
				coordenada2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2.setX(coordX.getX());
				coordenada2.setY(coordY.getY());
				coordenada2.setZ(altura);
				
				altura += peca2.getComprimento();
				coordenada3.setId(identificadorNos.toString());
				coordenada3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada3.setX(coordX.getX());
				coordenada3.setY(coordY.getY());
				coordenada3.setZ(altura);
				
				altura += peca3.getComprimento() + 0.1;
				coordenada4.setId(identificadorNos.toString());
				coordenada4.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada4.setX(coordX.getX());
				coordenada4.setY(coordY.getY());
				coordenada4.setZ(altura);
					
				Peca p1 = new Peca();
				Peca p2 = new Peca();
				Peca p3 = new Peca();
				
				p1.setId(String.valueOf(identificadorPecas));
				p1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				p1.setTipo(peca1.getTipo());
				p1.setNoInicial(coordenada1.getId());
				p1.setNoFinal(coordenada2.getId());
				
				p2.setId(String.valueOf(identificadorPecas));
				p2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				p2.setTipo(peca2.getTipo());
				p2.setNoInicial(coordenada2.getId());
				p2.setNoFinal(coordenada3.getId());
				
				p3.setId(String.valueOf(identificadorPecas));
				p3.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				p3.setTipo(peca3.getTipo());
				p3.setNoInicial(coordenada3.getId());
				p3.setNoFinal(coordenada4.getId());
				
				this.sumarioDados.getListaDeNos().add(coordenada1);
				this.sumarioDados.getListaDeNos().add(coordenada2);
				this.sumarioDados.getListaDeNos().add(coordenada3);
				this.sumarioDados.getListaDeNos().add(coordenada4);
				
				this.sumarioDados.getPecasFinais().add(p1);
				this.sumarioDados.getPecasFinais().add(p2);
				this.sumarioDados.getPecasFinais().add(p3);
			}
		
		}
		}
    	
	}

	public ArrayList<Peca> getComposicaoPostes (){
    	ArrayList<Peca> retorno = new ArrayList<Peca>();
    
    	Double altura = !"".equals(SciaXMLUtils.checkString(this.sumarioDados.getPeDireito())) ? Double.parseDouble(this.sumarioDados.getPeDireito())/100.0 : 0.0;
    	Double alturaMax = altura - SciaXMLContantes.VALOR_ALTURA_MAX;
    	Double alturaMin = altura - SciaXMLContantes.VALOR_ALTURA_MIN;
    	
		List<Peca> postesSelecionados = this.sumarioDados.getPostes();
		Collections.sort(postesSelecionados, new PecaSorter());
		List<Double> postes = new ArrayList<Double>();
		
		for (Peca p : postesSelecionados) {
			postes.add(p.getComprimento());
		}
		
    	boolean achou = false;
		
		Peca valor1 = new Peca();
		Peca valor2 = new Peca();
		Peca valor3 = new Peca();
		
		for (int i = 0; i <= postes.size()-1; i++) {
			valor1 = postesSelecionados.get(i);
			
			for (int j = postes.size()-1; j >= 0; j--) {
				
				if ((altura < 6.0) && (postes.get(j) >= 3.0))
					continue;
				
				valor2 = postesSelecionados.get(j);
				
				if ((valor1.getComprimento() + valor2.getComprimento()) <= alturaMax){

					for (int j2 = j; j2 >= 0; j2--) {
						valor3 = postesSelecionados.get(j2);
						
						if ((valor1.getComprimento()+valor2.getComprimento()+valor3.getComprimento()) >= alturaMin && 
								(valor1.getComprimento()+valor2.getComprimento()+valor3.getComprimento()) <= alturaMax){
							achou = true;
							break;
						}
					}
				}
				if(achou){					
					break;
				}
			}
			if(achou){					
				break;
			}
		}
		
		if(achou){					
			retorno.add(valor1);
			retorno.add(valor2);
			retorno.add(valor3);
			System.out.println(retorno.toString());
		}else{
			System.out.println("Não Achou Combinação");
		}
		
    	return retorno;
    }

	private List<Coordenada> getNosY(List<Peca> pecas){
		List<Coordenada> retorno = null;
		
		List<String> nosIniciais = new ArrayList<String>();		
		for (Peca peca : pecas) {
			if (peca.getTipo().startsWith(SciaXMLContantes.KITRV))
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
			if (peca.getTipo().startsWith(SciaXMLContantes.KITRV))
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
	
	public List<Peca> getPostesFinais() {
		return postesFinais;
	}

	public void setPostesFinais(List<Peca> postesFinais) {
		this.postesFinais = postesFinais;
	}
	
}
