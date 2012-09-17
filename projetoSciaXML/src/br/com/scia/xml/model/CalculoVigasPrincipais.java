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
import br.com.scia.xml.util.Identificadores;
import br.com.scia.xml.util.PecaComprimentoSorter;
import br.com.scia.xml.util.SciaXMLConstantes;
import br.com.scia.xml.util.SciaXMLUtils;

public class CalculoVigasPrincipais {

	private SumarioDados sumarioDados;
	private List<Peca> vigasPrincipaisFinais;
	
	public CalculoVigasPrincipais() {
		this.sumarioDados = Calculo.dados;
	}
	
	public void realizarCalculo () throws CalculoException{
		List<Peca> pecas = RepositorioPecas.listaTravessas;
		 
						
		List<Coordenada> nosX = getNosX(RepositorioPecas.listaForcados);
		Collections.sort(nosX,new CoordenadaSorterX());
		
		//pega a primeira cordenada em x onde existe alguma escora/poste
		Double pontoXInicialEstrutura = nosX.get(nosX.size()-1).getX();
		System.out.println("pontoXInicialEstrutura - principal " + pontoXInicialEstrutura);
		
		calcularVigasX(pontoXInicialEstrutura,(SciaXMLConstantes.METADE_ESPESSURA_VIGA));
		
		if (pecas != null && pecas.size() > 0){
			
			 
			if (this.vigasPrincipaisFinais != null){
				Double total = 0.0;
				for (Peca peca : this.vigasPrincipaisFinais) {
					total += peca.getComprimento();
				} 
				
				if (total < ((nosX.get(0).getX())-(nosX.get(nosX.size()-1).getX()))){
					throw new CalculoException(SciaXMLConstantes.COMBINACAO_DE_VIGAS_PRINCIPAIS_NAO_ENCONTRADA);
				}
			}
		}
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
		
	private List<Coordenada> getNosY(List<Peca> pecas){
		List<Coordenada> retorno = null;
		
		List<String> nosIniciais = new ArrayList<String>();		
		for (Peca peca : pecas) {
			if (peca.getTipo().startsWith(SciaXMLConstantes.ESC) || peca.getTipo().startsWith(SciaXMLConstantes.KITRV))
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

	private void calcularVigasX (Double pontoInicial, Double desvio){		
		String transpasseInformado = SciaXMLUtils.checkString(this.sumarioDados.getTranspassePrincipais());
		
		if (!"".equals(transpasseInformado)){
			Double transpasse = Double.parseDouble(transpasseInformado)/100.0;
			Double transpaseTotal = transpasse*2; 
			  
			List<Coordenada> nosX = getNosX(RepositorioPecas.listaForcados);
			Collections.sort(nosX,new CoordenadaSorterX());
			
			List<Coordenada> nosY = getNosY(this.sumarioDados.getPecasFinais());
			List<Coordenada> coordenadasY = getCoordenadasY(nosY);
			
			if (this.vigasPrincipaisFinais == null)
				this.vigasPrincipaisFinais  = new ArrayList<Peca>();
			
			for (Coordenada coordenada : nosX) {
				if (this.vigasPrincipaisFinais != null && this.vigasPrincipaisFinais.size() > 0){
					Double total = 0.0;
					for (Peca peca : this.vigasPrincipaisFinais) {
						total += peca.getComprimento()-transpaseTotal;
					}
					
					//total >= (maior cordenada - menor cordenada)
					if (total >= ((nosX.get(0).getX())-(nosX.get(nosX.size()-1).getX()))){
						break;
					}
				}
				
				//caso seja a ultima cordenada(cordenada de menor valor) em x não precisa mais adicionar vigas
				if (coordenada.getX() == nosX.get(nosX.size()-1).getX())
					continue;
				  
				Double x = coordenada.getX() + transpaseTotal - pontoInicial;
			
				List<Peca> vigas = this.sumarioDados.getVigasPrincipais();
				Collections.sort(vigas,new PecaComprimentoSorter());
			     
				boolean achou = false;
				for (Peca peca : vigas) {
					if (peca.getComprimento() >= x){
						this.vigasPrincipaisFinais.add(peca);
						
						Double xInicial = (pontoInicial-transpasse);
						Double xFinal = (pontoInicial-transpasse+peca.getComprimento());
						  
						for (Coordenada coordY : coordenadasY) {
							Coordenada coordenada1 = new Coordenada(); 
							Coordenada coordenada2 = new Coordenada();
							
							Double posteEspecial = this.sumarioDados.getPosteEspecial().getComprimento();
							Double coordenadaZ   = Double.parseDouble(this.sumarioDados.getCoordenadaZ())/SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
							
							Double altura =  Calculo.getAlturaUtil() + CalculoUtils.getAlturaMacacoEForcado() + posteEspecial + coordenadaZ;
							
							coordenada1.setId(Identificadores.getIdentificadorNo().toString());
							coordenada1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada1.getId()));					
							coordenada1.setX(xInicial);
							coordenada1.setY((coordY.getY()+desvio));
							coordenada1.setZ(altura);
							
							coordenada2.setId(Identificadores.getIdentificadorNo().toString());
							coordenada2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada2.getId()));					
							coordenada2.setX(xFinal);
							coordenada2.setY((coordY.getY()+desvio));
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
					calcularVigasX((coordenada.getX() - transpasse) , desvio);
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

	public List<Peca> getVigasPrincipaisFinais() {
		return vigasPrincipaisFinais;
	}

	public void setVigasPrincipaisFinais(List<Peca> vigasPrincipaisFinais) {
		this.vigasPrincipaisFinais = vigasPrincipaisFinais;
	}
	
}
