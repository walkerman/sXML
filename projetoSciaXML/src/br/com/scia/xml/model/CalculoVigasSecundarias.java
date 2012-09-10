package br.com.scia.xml.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.dao.RepositorioProjeto;
import br.com.scia.xml.entity.exception.CalculoException;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.xml.Coordenada;
import br.com.scia.xml.util.CoordenadaSorterX;
import br.com.scia.xml.util.CoordenadaSorterY;
import br.com.scia.xml.util.PecaComprimentoSorter;
import br.com.scia.xml.util.SciaXMLConstantes;
import br.com.scia.xml.util.SciaXMLUtils;

public class CalculoVigasSecundarias {

	private SumarioDados sumarioDados;
	private List<Peca> vigasSecundariasFinais;
	private Integer identificadorPecas;
	private Integer identificadorNos;
	
	public CalculoVigasSecundarias() {
		this.sumarioDados = Calculo.dados;
				
		if (this.sumarioDados != null){
			this.identificadorNos = this.sumarioDados.getListaDeNos().size()+1;
			this.identificadorPecas = this.sumarioDados.getPecasFinais().size()+1;
		}
	}
	
	public void realizarCalculo () throws CalculoException{
		List<Peca> pecas = RepositorioPecas.listaTravessas;
		
		if (pecas != null && pecas.size() > 0){
			Double pontoYInicialEstrutura = Double.parseDouble(this.sumarioDados.getCoordenadaY())/100;
					
			calcularVigasY(pontoYInicialEstrutura,this.identificadorNos,this.identificadorPecas);
			
			if (this.vigasSecundariasFinais != null){
				Double total = 0.0;
				for (Peca peca : this.vigasSecundariasFinais) {
					total += peca.getComprimento();
				}
				
				List<Coordenada> nosY = getNosY(RepositorioPecas.listaTravessas);
				Collections.sort(nosY,new CoordenadaSorterX());
				Double cordenadainicioY = Double.parseDouble(this.sumarioDados.getCoordenadaY())/100;
				if (total < (nosY.get(0).getY()-cordenadainicioY)){
					throw new CalculoException(SciaXMLConstantes.COMBINACAO_DE_VIGAS_SECUNDARIAS_NAO_ENCONTRADA);
				}
			}
			
			System.out.println("Vigas Selecionadas = \n" + this.vigasSecundariasFinais);
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

	private void calcularVigasY (Double pontoInicial, Integer identificacaoNo, Integer identificacaoPeca){		
		String transpasseInformado = SciaXMLUtils.checkString(this.sumarioDados.getTranspasseSecundarias());
		
		if (!"".equals(transpasseInformado)){
			Double transpasse = Double.parseDouble(transpasseInformado)/100.0;
			Double transpaseTotal = transpasse*2;
			
			Double cordenadainicioY = Double.parseDouble(this.sumarioDados.getCoordenadaY())/100;
			Double cordenadainicioX = Double.parseDouble(this.sumarioDados.getCoordenadaX())/100;
			
			List<Coordenada> nosY = getNosY(RepositorioPecas.listaTravessas);
			Collections.sort(nosY,new CoordenadaSorterY());
			
			if (this.vigasSecundariasFinais == null)
				this.vigasSecundariasFinais  = new ArrayList<Peca>();
			
			for (Coordenada coordenada : nosY) {
				if (this.vigasSecundariasFinais != null && this.vigasSecundariasFinais.size() > 0){
					Double total = 0.0;
					for (Peca peca : this.vigasSecundariasFinais) {
						total += peca.getComprimento()-transpaseTotal;
					}
					
					System.out.println("total " +  total);
					System.out.println("nosY.get(0).getY() " + nosY.get(0).getY());
					System.out.println("cordenadainicioY " + cordenadainicioY);
					
					if (total >= (nosY.get(0).getY()-cordenadainicioY)){
						break;
					}
					
					
					
				}
				
				
				
								
				//if (coordenada.getY() == nosY.get(nosY.size()-1).getY())
				//	continue;
				
				Double tamanhoNecessarioY = coordenada.getY() + transpaseTotal - pontoInicial;
			
				List<Peca> vigas = this.sumarioDados.getVigasSecundarias();
				Collections.sort(vigas,new PecaComprimentoSorter());
			
				boolean achou = false;
				for (Peca peca : vigas) {
					if (peca.getComprimento() >= tamanhoNecessarioY){
						this.vigasSecundariasFinais.add(peca);
						
						Double yInicial = (pontoInicial-transpasse);
						Double yFinal = (pontoInicial-transpasse+peca.getComprimento());
						
						//TODO: obter desvio correto da peça de entrada (como garantir a criação das vigas apenas um x, inicialmente?)
						// Double desvioY = 0.08;
							
						Double espacamentoEntreVigas = (Double.parseDouble(this.sumarioDados.getFolgaLajeX1())/100.0) - transpasse + cordenadainicioX;
						System.out.println("espacamentoEntreVigas " + espacamentoEntreVigas);
						
						Double medidaLajeX = Double.parseDouble(this.sumarioDados.getMedidaLageX())/100.0;
						Double folgaLajeX2 = Double.parseDouble(this.sumarioDados.getFolgaLajeX2())/100.0;
						Double entreVigasInformado = Double.parseDouble(this.sumarioDados.getEspacamentoEntreVigasSecundarias())/100.0;
						Double areaDeVigasSecundarias = (medidaLajeX - folgaLajeX2 + cordenadainicioX);
						
						while (espacamentoEntreVigas <= areaDeVigasSecundarias) {
							Coordenada coordenada1 = new Coordenada(); 
							Coordenada coordenada2 = new Coordenada();
							
							Double posteEspecial = this.sumarioDados.getPosteEspecial().getComprimento();
							Double altura =  Calculo.getAlturaUtil() + CalculoVigasPrincipais.getAlturaViga() + posteEspecial + CalculoPostes.getAlturaMacacoEForcado();
							
							coordenada1.setId(identificacaoNo.toString());
							coordenada1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(identificacaoNo++));					
							coordenada1.setX(espacamentoEntreVigas);
							coordenada1.setY(yInicial);
							coordenada1.setZ(altura);
							
							coordenada2.setId(identificacaoNo.toString());
							coordenada2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(identificacaoNo++));					
							coordenada2.setX(espacamentoEntreVigas);
							coordenada2.setY(yFinal);
							coordenada2.setZ(altura);
							 
							Peca peca1 = new Peca();
							
							peca1.setId(String.valueOf(identificacaoPeca));
							peca1.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(identificacaoPeca++));
							peca1.setTipo(peca.getTipo());
							peca1.setNoInicial(coordenada1);
							peca1.setNoFinal(coordenada2);
							
							this.sumarioDados.getListaDeNos().add(coordenada1);
							this.sumarioDados.getListaDeNos().add(coordenada2);
							this.sumarioDados.getPecasFinais().add(peca1);	
							
							if (espacamentoEntreVigas < areaDeVigasSecundarias && 
									(espacamentoEntreVigas + entreVigasInformado) > areaDeVigasSecundarias){
								espacamentoEntreVigas = areaDeVigasSecundarias;
							}else{
								espacamentoEntreVigas += entreVigasInformado;
							}
						} 
						
						achou = true;
						break;
					}
				}
				
				if (achou){
					calcularVigasY((coordenada.getY() - transpasse), identificacaoNo, identificacaoPeca);
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
	
}
