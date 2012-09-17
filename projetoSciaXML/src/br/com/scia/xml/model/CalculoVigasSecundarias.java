package br.com.scia.xml.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.dao.RepositorioProjeto;
import br.com.scia.xml.entity.exception.CalculoException;
import br.com.scia.xml.entity.view.Coordenada;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.util.CoordenadaSorterX;
import br.com.scia.xml.util.CoordenadaSorterY;
import br.com.scia.xml.util.Identificadores;
import br.com.scia.xml.util.PecaComprimentoSorter;
import br.com.scia.xml.util.SciaXMLConstantes;
import br.com.scia.xml.util.SciaXMLUtils;

public class CalculoVigasSecundarias {

	private SumarioDados sumarioDados;
	private List<Peca> vigasSecundariasFinais;
	
	public CalculoVigasSecundarias() {
		this.sumarioDados = Calculo.dados;
	}
	 
	public void realizarCalculo () throws CalculoException{
		List<Peca> pecas = RepositorioPecas.listaTravessas;
		
		if (pecas != null && pecas.size() > 0){
  			  
			List<Coordenada> nosY = getNosY(RepositorioPecas.listaForcados); 
			Collections.sort(nosY,new CoordenadaSorterY());
			  		
			calcularVigasY(nosY.get(nosY.size()-1).getY(),(SciaXMLConstantes.METADE_ESPESSURA_VIGA));
			   
			if (this.vigasSecundariasFinais != null){
				Double total = 0.0;
				for (Peca peca : this.vigasSecundariasFinais) {
					total += peca.getComprimento();
				}
				  
				if (total < ((nosY.get(0).getY())-(nosY.get(nosY.size()-1).getY()))){
					throw new CalculoException(SciaXMLConstantes.COMBINACAO_DE_VIGAS_SECUNDARIAS_NAO_ENCONTRADA);
				}
			}
			  
		}
	}
	
	private List<Coordenada> getNosY(List<Peca> pecas){
		List<Coordenada> retorno = null;
		
		List<String> nosIniciais = new ArrayList<String>();		
		for (Peca peca : pecas) {
			if (!peca.getTipo().startsWith(SciaXMLConstantes.CRU))
				nosIniciais.add(peca.getNoInicial().getId());
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

	private void calcularVigasY (Double pontoInicial, Double desvio){
		   
		String transpasseInformado = SciaXMLUtils.checkString(this.sumarioDados.getTranspasseSecundarias());
		
		if (!"".equals(transpasseInformado)){
			Double transpasse = Double.parseDouble(transpasseInformado)/100.0;
			Double transpaseTotal = transpasse*2;
    		
			List<Coordenada> nosY = getNosY(RepositorioPecas.listaForcados); 
			Collections.sort(nosY,new CoordenadaSorterY());
			
			List<Coordenada> nosX = getNosX(RepositorioPecas.listaForcados); 
			Collections.sort(nosX,new CoordenadaSorterX());
			
			if (this.vigasSecundariasFinais == null)
				this.vigasSecundariasFinais  = new ArrayList<Peca>();
			
			for (Coordenada coordenada : nosY) {
				if (this.vigasSecundariasFinais != null && this.vigasSecundariasFinais.size() > 0){
					Double total = 0.0;
					for (Peca peca : this.vigasSecundariasFinais) {
						total += peca.getComprimento()-transpaseTotal;
					}
					 
					if (total >= ((nosY.get(0).getY())-(nosY.get(nosY.size()-1).getY()))){
						break;
					}
					
					
					
				}
				    
				Double tamanhoNecessarioY = coordenada.getY() + transpaseTotal - pontoInicial;
			
				List<Peca> vigas = this.sumarioDados.getVigasSecundarias();
				Collections.sort(vigas,new PecaComprimentoSorter());
			
				boolean achou = false;
				for (Peca peca : vigas) {
					if (peca.getComprimento() >= tamanhoNecessarioY){
						this.vigasSecundariasFinais.add(peca);
						
						Double yInicial = (pontoInicial-transpasse);
						Double yFinal = (pontoInicial-transpasse+peca.getComprimento()); 
						Double espacamentoEntreVigas = nosX.get(nosX.size()-1).getX();  
						Double entreVigasInformado = Double.parseDouble(this.sumarioDados.getEspacamentoEntreVigasSecundarias())/100.0; 
						 
						
						while (espacamentoEntreVigas <= (nosX.get(0).getX())) {
							Coordenada coordenada1 = new Coordenada(); 
							Coordenada coordenada2 = new Coordenada();
							
							Double posteEspecial = this.sumarioDados.getPosteEspecial().getComprimento();
							Double coordenadaZ   = Double.parseDouble(this.sumarioDados.getCoordenadaZ())/SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
							Double altura =  Calculo.getAlturaUtil() + CalculoVigasPrincipais.getAlturaViga() + posteEspecial + CalculoUtils.getAlturaMacacoEForcado() + coordenadaZ;
							
							coordenada1.setId(Identificadores.getIdentificadorNo().toString());
							coordenada1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada1.getId()));					
							coordenada1.setX(espacamentoEntreVigas+desvio);
							coordenada1.setY(yInicial);
							coordenada1.setZ(altura);
							
							coordenada2.setId(Identificadores.getIdentificadorNo().toString());
							coordenada2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada2.getId()));					
							coordenada2.setX(espacamentoEntreVigas+desvio);
							coordenada2.setY(yFinal);
							coordenada2.setZ(altura);
							 
							Peca peca1 = new Peca();
							
							peca1.setId(String.valueOf(Identificadores.getIdentificarPecas().toString()));
							peca1.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(peca1.getId()));
							peca1.setTipo(peca.getTipo());
							peca1.setNoInicial(coordenada1);
							peca1.setNoFinal(coordenada2);
							
							this.sumarioDados.getListaDeNos().add(coordenada1);
							this.sumarioDados.getListaDeNos().add(coordenada2);
							this.sumarioDados.getPecasFinais().add(peca1);	
							
							if (espacamentoEntreVigas < nosX.get(0).getX() && 
									(espacamentoEntreVigas + entreVigasInformado) > nosX.get(0).getX()){
								espacamentoEntreVigas = nosX.get(0).getX();
							}else{
								espacamentoEntreVigas += entreVigasInformado;
							}
						} 
						
						if (desvio == -(SciaXMLConstantes.METADE_ESPESSURA_VIGA))
						{
							desvio = (SciaXMLConstantes.METADE_ESPESSURA_VIGA);
						}
						else
						{
							desvio = -(SciaXMLConstantes.METADE_ESPESSURA_VIGA);
						}
						
						achou = true;
						break;
					}
				}
				
				if (achou){
					calcularVigasY(coordenada.getY() - transpasse, desvio);
					break;
				}
			}
		}
	}

	public static Double getAlturaViga (){
		Double retorno = 0.0;
		
		String tipoViga = RepositorioProjeto.projeto.getTipoVigaPrincipal();
		
		if (SciaXMLConstantes.TIPO_VIGA_HT20.equals(tipoViga))
			retorno = 0.2;
		if (SciaXMLConstantes.TIPO_VIGA_V18.equals(tipoViga))
			retorno = 0.18;
		if (SciaXMLConstantes.TIPO_VIGA_V75.equals(tipoViga))
			retorno = 0.075;
		if (SciaXMLConstantes.TIPO_VIGA_VA18.equals(tipoViga))
			retorno = 0.18;
		
		return retorno;
		
	}
	
	public List<Peca> getVigasSecundariasFinais() {
		return vigasSecundariasFinais;
	}

	public void setVigasSecundariasFinais(List<Peca> vigasSecundariasFinais) {
		this.vigasSecundariasFinais = vigasSecundariasFinais;
	}
	


// Método responsável por obter todos os nós gerados em x
private List<Coordenada> getNosX(List<Peca> pecas){
	List<Coordenada> retorno = null;
	 
	
	List<String> nosIniciais = new ArrayList<String>();		
	for (Peca peca : pecas) {
		//if (peca.getTipo().contains(SciaXMLConstantes.ESC) || peca.getTipo().contains(SciaXMLConstantes.KITRV))
			nosIniciais.add(peca.getNoInicial().getId());
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

}