package br.com.scia.xml.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.xml.Coordenada;
import br.com.scia.xml.util.CoordenadaSorterZ;
import br.com.scia.xml.util.SciaXMLContantes;

public class CalculoTravessas {

	private SumarioDados sumarioDados;
	private Integer identificadorPecas;
	private Integer identificadorNos;
	
	public CalculoTravessas() {
		this.sumarioDados = Calculo.dados;
		
		if (this.sumarioDados != null){
			this.identificadorNos = this.sumarioDados.getListaDeNos().size()+1;
			this.identificadorPecas = this.sumarioDados.getPecasFinais().size()+1;
		}
	}
	
	public void replicarTravessas(){
		if (RepositorioPecas.listaRosaceas != null && RepositorioPecas.listaRosaceas.size() > 0){
			if (RepositorioPecas.listaTravessas != null && RepositorioPecas.listaTravessas.size() > 0){
			
				List<Peca> rosaceas = RepositorioPecas.listaRosaceas;
				List<Peca> travessas = RepositorioPecas.listaTravessas;
				
				List<Coordenada> coordenadasRosaceas = new ArrayList<Coordenada>();
				for (Peca rosacea : rosaceas) {
					Coordenada c = Calculo.getNo(rosacea.getNoInicial());
					coordenadasRosaceas.add(c);
				}
				
				List<Coordenada> alturasRosaceas = getCoordenadasZ(coordenadasRosaceas);
				Collections.sort(alturasRosaceas, new CoordenadaSorterZ());
				
				System.out.println(alturasRosaceas);
				
				double contador = 0.0;
				double entreKITRV = Double.parseDouble(this.sumarioDados.getEspacamentoEntreTravessas())/100.0;
				
				Coordenada coordenadaAnterior = null;
				for (int i = 0; i < alturasRosaceas.size(); i++) {
					System.out.println(contador);
					contador = Math.round(contador*100)/100.0;
					System.out.println("contador "+ contador);
					System.out.println(" entreKITRV "+ entreKITRV);
					
					Coordenada coordenada = alturasRosaceas.get(i);
					
					if (i == 0 || contador == entreKITRV || i == alturasRosaceas.size()-1){
												
						if (i > 0){
							coordenadaAnterior = alturasRosaceas.get(i-1);
							contador = ( coordenada.getZ() - coordenadaAnterior.getZ());
						}else{
							contador = 0;
						}
						
						for (Peca travessa : travessas) {
							
							Coordenada coordenada1 = new Coordenada();
							Coordenada coordenada2 = new Coordenada();
							
							Coordenada coordenadaInicialTravessa = Calculo.getNo(travessa.getNoInicial());
							Coordenada coordenadaFinalTravessa = Calculo.getNo(travessa.getNoFinal());
							
							coordenada1.setId(identificadorNos.toString());
							coordenada1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
							coordenada1.setX(coordenadaInicialTravessa.getX());
							coordenada1.setY(coordenadaInicialTravessa.getY());
							
							if (coordenadaAnterior == null || (coordenadaAnterior != null && i == alturasRosaceas.size()-1))
								coordenada1.setZ(coordenada.getZ() + SciaXMLContantes.ALTURA_ROSACEA / 2);
							else
								coordenada1.setZ(coordenadaAnterior.getZ() + SciaXMLContantes.ALTURA_ROSACEA / 2);
							
							coordenada2.setId(identificadorNos.toString());
							coordenada2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
							coordenada2.setX(coordenadaFinalTravessa.getX());
							coordenada2.setY(coordenadaFinalTravessa.getY());

							if (coordenadaAnterior == null || (coordenadaAnterior != null && i == alturasRosaceas.size()-1))
								coordenada2.setZ(coordenada.getZ() + SciaXMLContantes.ALTURA_ROSACEA / 2);
							else
								coordenada2.setZ(coordenadaAnterior.getZ() + SciaXMLContantes.ALTURA_ROSACEA / 2);
							
							Peca novaTravessa = new Peca();
							novaTravessa.setId(String.valueOf(identificadorPecas));
							novaTravessa.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
							novaTravessa.setTipo(SciaXMLContantes.ROSACEA);
							novaTravessa.setNoInicial(coordenada1.getId());
							novaTravessa.setNoFinal(coordenada2.getId());
							
							this.sumarioDados.getListaDeNos().add(coordenada1);
							this.sumarioDados.getListaDeNos().add(coordenada2);
							this.sumarioDados.getPecasFinais().add(novaTravessa);
						}
					}else{
						coordenadaAnterior = alturasRosaceas.get(i-1);
						contador += ( coordenada.getZ() - coordenadaAnterior.getZ());
					}
				}
			}
		}
	}
	
	// Método responsável por tirar as duplicidades de nós em Z
	private List<Coordenada> getCoordenadasZ (List<Coordenada> coordenadas){
		List<Coordenada> retorno = null;
		Set<Double> valoresZ = new HashSet<Double>();
		
		// Removendo os itens repetidos
		for (Coordenada coordenada : coordenadas) {
			valoresZ.add(coordenada.getZ());
		}
		
		if (valoresZ != null && valoresZ.size() > 0){
			retorno = new ArrayList<Coordenada>();
			for (Double valor : valoresZ) {
				Coordenada c = new Coordenada();
				c.setZ(valor);
				retorno.add(c);
			} 
		}
		
		return retorno;
	}
	
}
