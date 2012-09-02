package br.com.scia.xml.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.exception.CalculoException;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.xml.Coordenada;
import br.com.scia.xml.util.CoordenadaSorterX;
import br.com.scia.xml.util.CoordenadaSorterY;
import br.com.scia.xml.util.PecaSorter;
import br.com.scia.xml.util.SciaXMLContantes;

public class CalculoPostes {

	private SumarioDados sumarioDados;
	private Integer identificadorPecas;
	private Integer identificadorNos;
	
	public CalculoPostes() {
		this.sumarioDados = Calculo.dados;
				
		if (this.sumarioDados != null){
			this.identificadorNos = this.sumarioDados.getListaDeNos().size()+1;
			this.identificadorPecas = this.sumarioDados.getPecasFinais().size()+1;
		}
	}
	
	public void realizarCalculo () throws CalculoException{
		List<Peca> pecas = this.sumarioDados.getPecasFinais();
		
		if (pecas != null && pecas.size() > 0){
			List<Coordenada> coordenadasTravessasY = getNosY(pecas);
			Collections.sort(coordenadasTravessasY,new CoordenadaSorterY());			
			
			List<Coordenada> coordenadasTravessasX = getNosX(pecas);
			Collections.sort(coordenadasTravessasX,new CoordenadaSorterX());
						
			RepositorioPecas.listaPostes = getComposicaoPostes();
			System.out.println("Postes selecionados " + RepositorioPecas.listaPostes);
			
			replicarPostesSelecionados(coordenadasTravessasY, coordenadasTravessasX);
		}
	}
	
    private void replicarPostesSelecionados(List<Coordenada> coordenadasTravessasY, List<Coordenada> coordenadasTravessasX) {
		
    	List<Peca> postes = RepositorioPecas.listaPostes;
    	
    	for (Coordenada coordX : coordenadasTravessasX) {
    		for (Coordenada coordY : coordenadasTravessasY) {		
			   			
			this.identificadorNos = this.sumarioDados.getListaDeNos().size()+1;
			this.identificadorPecas = this.sumarioDados.getPecasFinais().size()+1;
				
			if (postes != null && postes.size() > 0){
				Peca peca1 = postes.get(0);
				Peca peca2 = postes.get(1);
				Peca peca3 = postes.get(2);				
				Peca macaco = this.sumarioDados.getMacaco();
				Peca posteEspecial = this.sumarioDados.getPosteEspecial();
				Peca forcado = this.sumarioDados.getForcado();
				
				Collections.sort(postes,new PecaSorter());
				
				double altura = 0.0;
												
				Coordenada coordenadaPoste1 = new Coordenada(); 
				Coordenada coordenadaPoste2 = new Coordenada();
				Coordenada coordenadaPoste3 = new Coordenada(); 
				Coordenada coordenadaPoste4 = new Coordenada();
				Coordenada coordenadaMacaco1 = new Coordenada();
				Coordenada coordenadaMacaco2 = new Coordenada();
				Coordenada coordenadaPosteEspecial1 = new Coordenada();
				Coordenada coordenadaPosteEspecial2 = new Coordenada();
				Coordenada coordenadaForcado1 = new Coordenada();
				Coordenada coordenadaForcado2 = new Coordenada();
				
				coordenadaMacaco1.setId(identificadorNos.toString());
				coordenadaMacaco1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaMacaco1.setX(coordX.getX());
				coordenadaMacaco1.setY(coordY.getY());
				coordenadaMacaco1.setZ(altura);
				
				altura += getAlturaMacacoEForcado();
				coordenadaMacaco2.setId(identificadorNos.toString());
				coordenadaMacaco2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaMacaco2.setX(coordX.getX());
				coordenadaMacaco2.setY(coordY.getY());
				coordenadaMacaco2.setZ(altura);
				
				coordenadaPoste1.setId(identificadorNos.toString());
				coordenadaPoste1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaPoste1.setX(coordX.getX());
				coordenadaPoste1.setY(coordY.getY());
				coordenadaPoste1.setZ(altura);
				
				altura += peca1.getComprimento();
				coordenadaPoste2.setId(identificadorNos.toString());
				coordenadaPoste2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaPoste2.setX(coordX.getX());
				coordenadaPoste2.setY(coordY.getY());
				coordenadaPoste2.setZ(altura);
				
				altura += peca2.getComprimento();
				coordenadaPoste3.setId(identificadorNos.toString());
				coordenadaPoste3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaPoste3.setX(coordX.getX());
				coordenadaPoste3.setY(coordY.getY());
				coordenadaPoste3.setZ(altura);
				
				altura += peca3.getComprimento();
				coordenadaPoste4.setId(identificadorNos.toString());
				coordenadaPoste4.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaPoste4.setX(coordX.getX());
				coordenadaPoste4.setY(coordY.getY());
				coordenadaPoste4.setZ(altura);
					
				coordenadaPosteEspecial1.setId(identificadorNos.toString());
				coordenadaPosteEspecial1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaPosteEspecial1.setX(coordX.getX());
				coordenadaPosteEspecial1.setY(coordY.getY());
				coordenadaPosteEspecial1.setZ(altura);
				
				altura += posteEspecial.getComprimento();
				coordenadaPosteEspecial2.setId(identificadorNos.toString());
				coordenadaPosteEspecial2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaPosteEspecial2.setX(coordX.getX());
				coordenadaPosteEspecial2.setY(coordY.getY());
				coordenadaPosteEspecial2.setZ(altura);
				
				coordenadaForcado1.setId(identificadorNos.toString());
				coordenadaForcado1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaForcado1.setX(coordX.getX());
				coordenadaForcado1.setY(coordY.getY());
				coordenadaForcado1.setZ(altura);
				
				altura += getAlturaMacacoEForcado();
				coordenadaForcado2.setId(identificadorNos.toString());
				coordenadaForcado2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaForcado2.setX(coordX.getX());
				coordenadaForcado2.setY(coordY.getY());
				coordenadaForcado2.setZ(altura);
				
				Peca poste1 = new Peca();
				Peca poste2 = new Peca();
				Peca poste3 = new Peca();
				
				poste1.setId(String.valueOf(identificadorPecas));
				poste1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				poste1.setTipo(peca1.getTipo());
				poste1.setNoInicial(coordenadaPoste1.getId());
				poste1.setNoFinal(coordenadaPoste2.getId());
				
				poste2.setId(String.valueOf(identificadorPecas));
				poste2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				poste2.setTipo(peca2.getTipo());
				poste2.setNoInicial(coordenadaPoste2.getId());
				poste2.setNoFinal(coordenadaPoste3.getId());
				
				poste3.setId(String.valueOf(identificadorPecas));
				poste3.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				poste3.setTipo(peca3.getTipo());
				poste3.setNoInicial(coordenadaPoste3.getId());
				poste3.setNoFinal(coordenadaPoste4.getId());
				
				Peca macaco1 = new Peca();
				macaco1.setId(String.valueOf(identificadorPecas));
				macaco1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				macaco1.setTipo(macaco.getTipo());
				macaco1.setNoInicial(coordenadaMacaco1.getId());
				macaco1.setNoFinal(coordenadaMacaco2.getId());
								
				Peca posteEspecial1 = new Peca();
				posteEspecial1.setId(String.valueOf(identificadorPecas));
				posteEspecial1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				posteEspecial1.setTipo(posteEspecial.getTipo());
				posteEspecial1.setNoInicial(coordenadaPosteEspecial1.getId());
				posteEspecial1.setNoFinal(coordenadaPosteEspecial2.getId());
				
				Peca forcado1 = new Peca();
				forcado1.setId(String.valueOf(identificadorPecas));
				forcado1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				forcado1.setTipo(forcado.getTipo());
				forcado1.setNoInicial(coordenadaForcado1.getId());
				forcado1.setNoFinal(coordenadaForcado2.getId());
								
				this.sumarioDados.getListaDeNos().add(coordenadaPoste1);
				this.sumarioDados.getListaDeNos().add(coordenadaPoste2);
				this.sumarioDados.getListaDeNos().add(coordenadaPoste3);
				this.sumarioDados.getListaDeNos().add(coordenadaPoste4);
				this.sumarioDados.getListaDeNos().add(coordenadaMacaco1);
				this.sumarioDados.getListaDeNos().add(coordenadaMacaco2);
				this.sumarioDados.getListaDeNos().add(coordenadaPosteEspecial1);
				this.sumarioDados.getListaDeNos().add(coordenadaPosteEspecial2);
				this.sumarioDados.getListaDeNos().add(coordenadaForcado1);
				this.sumarioDados.getListaDeNos().add(coordenadaForcado2);
				
				this.sumarioDados.getPecasFinais().add(poste1);
				this.sumarioDados.getPecasFinais().add(poste2);
				this.sumarioDados.getPecasFinais().add(poste3);
				this.sumarioDados.getPecasFinais().add(macaco1);
				this.sumarioDados.getPecasFinais().add(posteEspecial1);
				this.sumarioDados.getPecasFinais().add(forcado1);
				
				CalculoRosacea calculoRosacea = new CalculoRosacea();
				calculoRosacea.calcularRosaceas(poste1,	this.identificadorNos, this.identificadorPecas);
				this.identificadorNos = this.sumarioDados.getListaDeNos().size()+1;
				this.identificadorPecas = this.sumarioDados.getPecasFinais().size()+1;
				
				calculoRosacea.calcularRosaceas(poste2, this.identificadorNos, this.identificadorPecas);
				this.identificadorNos = this.sumarioDados.getListaDeNos().size()+1;
				this.identificadorPecas = this.sumarioDados.getPecasFinais().size()+1;
				
				calculoRosacea.calcularRosaceas(poste3, this.identificadorNos, this.identificadorPecas);
				}

			}
		}

	}

	public ArrayList<Peca> getComposicaoPostes () throws CalculoException{
    	ArrayList<Peca> retorno = new ArrayList<Peca>();
    	
    	Double altura = Calculo.getAlturaUtil();
    	Double alturaMax = altura - SciaXMLContantes.VALOR_ALTURA_MAX;
    	Double alturaMin = altura - SciaXMLContantes.VALOR_ALTURA_MIN;
    	
		List<Peca> postesSelecionados = this.sumarioDados.getPostes();
		Collections.sort(postesSelecionados, new PecaSorter());
		List<Double> postes = new ArrayList<Double>();
		
		for (Peca p : postesSelecionados) {
			postes.add(p.getComprimento());
		}
		
    	boolean achou = false;
		
		Peca valor1 = postesSelecionados.get(0);
		Peca valor2 = new Peca();
		Peca valor3 = new Peca();
					
		for (int j = postes.size()-1; j >= 0; j--) {
			
			Double peDireiro = Double.parseDouble(this.sumarioDados.getPeDireito() )/100.0;
			
			if (!((peDireiro <= 6.0) && (postes.get(j) >= 3.0))){
				valor2 = postesSelecionados.get(j);
				
				if ((valor1.getComprimento() + valor2.getComprimento()) <= alturaMax){
		
					for (int j2 = j; j2 >= 0; j2--) {
						valor3 = postesSelecionados.get(j2);
						
						if (!((peDireiro <= 6.0) && (postes.get(j2) >= 3.0))){
							if ((valor1.getComprimento()+valor2.getComprimento()+valor3.getComprimento()) >= alturaMin && 
									(valor1.getComprimento()+valor2.getComprimento()+valor3.getComprimento()) <= alturaMax){
								achou = true;
								break;
							}
						}
					}
				}
				if(achou){					
					break;
				}
			}
		}
		
		if(achou){					
			retorno.add(valor1);
			retorno.add(valor2);
			retorno.add(valor3);
		}else{
			throw new CalculoException(SciaXMLContantes.COMBINACAO_DE_POSTES_NAO_ENCONTRADA);
		}
		
    	return retorno;
    }
 	
	public static Double getAlturaMacacoEForcado(){
		Double retorno = 0.0;
		
		retorno = (Calculo.getAlturaUtil() - getAlturaPostesFinais())/2;
		
		return retorno;
	}
	
	public static Double getAlturaPostesFinais (){
		Double retorno = 0.0;
		if (RepositorioPecas.listaPostes != null && RepositorioPecas.listaPostes.size() > 0){
			for (Peca p : RepositorioPecas.listaPostes) {
				retorno += p.getComprimento();
			}
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
		
}
