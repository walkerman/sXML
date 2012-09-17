package br.com.scia.xml.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.exception.CalculoException;
import br.com.scia.xml.entity.view.Coordenada;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.xml.Project;
import br.com.scia.xml.util.CoordenadaSorterY;
import br.com.scia.xml.util.CoordenadaSorterZ;
import br.com.scia.xml.util.Identificadores;
import br.com.scia.xml.util.SciaXMLConstantes;

public class CalculoConsole {

	private SumarioDados sumarioDados;
	
	public CalculoConsole() {
		this.sumarioDados = Calculo.dados;
	}
	
	public void realizarCalculo (String posicaoConsole) throws CalculoException{
		List<Peca> pecas = RepositorioPecas.listaTravessasReplicadas;
		List<Coordenada> nosX = getNosX(pecas);
		Double menorY = getMenorY(pecas);
		Double maiorY = getMaiorY(pecas);
		Double maiorZ = getMaiorZ(pecas);
		
		if (posicaoConsole != null && posicaoConsole.equals(SciaXMLConstantes.DIREITA))
			criarConsoleDireita(nosX, menorY, maiorZ);
		else if (posicaoConsole != null && posicaoConsole.equals(SciaXMLConstantes.ESQUERDA))
			criarConsoleEsquerda(nosX, maiorY, maiorZ);
	}
	
	private void criarConsoleDireita(List<Coordenada> nosX, Double menorY, Double maiorZ) {
    	Project console = RepositorioPecas.pecas.get(SciaXMLConstantes.CONSOLE);
    	
    	for (Coordenada no : nosX) {			
				Coordenada coordenada1 = new Coordenada(); 
				coordenada1.setId(Identificadores.getIdentificadorNo().toString());
				coordenada1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada1.getId()));					
				coordenada1.setX(no.getX());
				coordenada1.setY(menorY);
				coordenada1.setZ(maiorZ);
				
				Coordenada coordenada2 = new Coordenada(); 
				coordenada2.setId(Identificadores.getIdentificadorNo().toString());
				coordenada2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada2.getId()));					
				coordenada2.setX(no.getX());
				coordenada2.setY(menorY - console.getComprimentoX());
				coordenada2.setZ(maiorZ);
				
				Coordenada coordenada3 = new Coordenada(); 
				coordenada3.setId(Identificadores.getIdentificadorNo().toString());
				coordenada3.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada3.getId()));					
				coordenada3.setX(no.getX());
				coordenada3.setY(menorY);
				coordenada3.setZ(maiorZ - SciaXMLConstantes.ALTURA_CONSOLE_DIAGONAL);
				
				Peca console1 = new Peca();
				console1.setId(String.valueOf(Identificadores.getIdentificarPecas()));
				console1.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(console1.getId()));
				console1.setTipo(SciaXMLConstantes.CONSOLE);
				console1.setNoInicial(coordenada1);
				console1.setNoFinal(coordenada2);
				
				Peca console2 = new Peca();
				console2.setId(String.valueOf(Identificadores.getIdentificarPecas()));
				console2.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(console2.getId()));
				console2.setTipo(SciaXMLConstantes.CONSOLE_DIAGONAL);
				console2.setNoInicial(coordenada2);
				console2.setNoFinal(coordenada3);
				
				CalculoForcado calculoForcado = new CalculoForcado();
				calculoForcado.defineForcado(coordenada2.getX(), coordenada2.getY(), coordenada2.getZ());
				
				this.sumarioDados.getListaDeNos().add(coordenada1);
				this.sumarioDados.getListaDeNos().add(coordenada2);
				this.sumarioDados.getListaDeNos().add(coordenada3);
				
				this.sumarioDados.getPecasFinais().add(console1);
				this.sumarioDados.getPecasFinais().add(console2);
    	}
	}
	
    private void criarConsoleEsquerda(List<Coordenada> nosX, Double maiorY, Double maiorZ) {
    	Project console = RepositorioPecas.pecas.get(SciaXMLConstantes.CONSOLE);
    	
    	for (Coordenada no : nosX) {			
				Coordenada coordenada1 = new Coordenada(); 
				coordenada1.setId(Identificadores.getIdentificadorNo().toString());
				coordenada1.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada1.getId()));					
				coordenada1.setX(no.getX());
				coordenada1.setY(maiorY);
				coordenada1.setZ(maiorZ);
				
				Coordenada coordenada2 = new Coordenada(); 
				coordenada2.setId(Identificadores.getIdentificadorNo().toString());
				coordenada2.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada2.getId()));					
				coordenada2.setX(no.getX());
				coordenada2.setY(maiorY + console.getComprimentoX());
				coordenada2.setZ(maiorZ);
				
				Coordenada coordenada3 = new Coordenada(); 
				coordenada3.setId(Identificadores.getIdentificadorNo().toString());
				coordenada3.setName(SciaXMLConstantes.INDEXADOR_NO + String.valueOf(coordenada3.getId()));					
				coordenada3.setX(no.getX());
				coordenada3.setY(maiorY);
				coordenada3.setZ(maiorZ - SciaXMLConstantes.ALTURA_CONSOLE_DIAGONAL);
				
				Peca console1 = new Peca();
				console1.setId(String.valueOf(Identificadores.getIdentificarPecas()));
				console1.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(console1.getId()));
				console1.setTipo(SciaXMLConstantes.CONSOLE);
				console1.setNoInicial(coordenada1);
				console1.setNoFinal(coordenada2);
				
				Peca console2 = new Peca();
				console2.setId(String.valueOf(Identificadores.getIdentificarPecas()));
				console2.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(console2.getId()));
				console2.setTipo(SciaXMLConstantes.CONSOLE_DIAGONAL);
				console2.setNoInicial(coordenada2);
				console2.setNoFinal(coordenada3);
				
				CalculoForcado calculoForcado = new CalculoForcado();
				calculoForcado.defineForcado(coordenada2.getX(), coordenada2.getY(), coordenada2.getZ());
				
				this.sumarioDados.getListaDeNos().add(coordenada1);
				this.sumarioDados.getListaDeNos().add(coordenada2);
				this.sumarioDados.getListaDeNos().add(coordenada3);
				
				this.sumarioDados.getPecasFinais().add(console1);
				this.sumarioDados.getPecasFinais().add(console2);
    	}
	}
	
	private List<Coordenada> getNosX(List<Peca> pecas){
		List<Coordenada> nosIniciais = new ArrayList<Coordenada>();		
		for (Peca peca : pecas) {
			if (peca.getTipo().startsWith(SciaXMLConstantes.KITRV))
				nosIniciais.add(peca.getNoInicial());
		}
		
		return getCoordenadasX(nosIniciais);
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
	
	private Double getMaiorY (List<Peca> pecas){
		Double retorno = null;
		Set<Double> valoresY = new HashSet<Double>();
		
		List<Coordenada> coordTemp = new ArrayList<Coordenada>();
		for (Peca p : pecas) {
			coordTemp.add(p.getNoInicial());
		}
		
		// Removendo os itens repetidos
		for (Coordenada coordenada : coordTemp) {
			valoresY.add(coordenada.getY());
		}
		
		if (valoresY != null && valoresY.size() > 0){
			ArrayList<Coordenada> coordenadas = new ArrayList<Coordenada>();
			for (Double valor : valoresY) {
				Coordenada c = new Coordenada();
				c.setY(valor);
				coordenadas.add(c);
			} 
			
			Collections.sort(coordenadas,new CoordenadaSorterY());
			
			if (coordenadas!= null && coordenadas.size() > 0)
				retorno = coordenadas.get(0).getY();
		}
		
		return retorno;
	}
	
	private Double getMenorY (List<Peca> pecas){
		Double retorno = null;
		Set<Double> valoresY = new HashSet<Double>();
		
		List<Coordenada> coordTemp = new ArrayList<Coordenada>();
		for (Peca p : pecas) {
			coordTemp.add(p.getNoInicial());
		}
		
		// Removendo os itens repetidos
		for (Coordenada coordenada : coordTemp) {
			valoresY.add(coordenada.getY());
		}
		
		if (valoresY != null && valoresY.size() > 0){
			ArrayList<Coordenada> coordenadas = new ArrayList<Coordenada>();
			for (Double valor : valoresY) {
				Coordenada c = new Coordenada();
				c.setY(valor);
				coordenadas.add(c);
			} 
			
			Collections.sort(coordenadas,new CoordenadaSorterY());
			
			if (coordenadas!= null && coordenadas.size() > 0)
				retorno = coordenadas.get(coordenadas.size()-1).getY();
		}
		
		return retorno;
	}
		
	private Double getMaiorZ (List<Peca> pecas){
		Double retorno = null;
		Set<Double> valoresZ = new HashSet<Double>();
		
		List<Coordenada> coordTemp = new ArrayList<Coordenada>();
		for (Peca p : pecas) {
			coordTemp.add(p.getNoInicial());
		}
		
		// Removendo os itens repetidos
		for (Coordenada coordenada : coordTemp) {
			valoresZ.add(coordenada.getZ());
		}
		
		if (valoresZ != null && valoresZ.size() > 0){
			ArrayList<Coordenada> coordenadas = new ArrayList<Coordenada>();
			for (Double valor : valoresZ) {
				Coordenada c = new Coordenada();
				c.setZ(valor);
				coordenadas.add(c);
			} 
			
			Collections.sort(coordenadas,new CoordenadaSorterZ());
			
			if (coordenadas!= null && coordenadas.size() > 0)
				retorno = coordenadas.get(coordenadas.size()-1).getZ();
		}
		
		return retorno;
	}
}
