package br.com.scia.xml.model;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.xml.Coordenada;
import br.com.scia.xml.util.SciaXMLContantes;

public class CalculoRosacea {

	private SumarioDados sumarioDados;
	
	public CalculoRosacea() {
		this.sumarioDados = Calculo.dados;
	}
	
	public void calcularRosaceas (Peca posteReferencia, Integer identificadorNos, Integer identificadorPecas){
		if (posteReferencia != null){
			
			if (posteReferencia.getTipo().equals(SciaXMLContantes.KIP025R1)){
				Coordenada posteReferenciaNoInicial = Calculo.getNo(posteReferencia.getNoInicial());
				
				Double altura = posteReferenciaNoInicial.getZ() + 0.12125;
				
				Coordenada coordenadaRosacea1 = new Coordenada();
				coordenadaRosacea1.setId(identificadorNos.toString());
				coordenadaRosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaRosacea1.setX(posteReferenciaNoInicial.getX());
				coordenadaRosacea1.setY(posteReferenciaNoInicial.getY());
				coordenadaRosacea1.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenadaRosacea2 = new Coordenada();
				coordenadaRosacea2.setId(identificadorNos.toString());
				coordenadaRosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaRosacea2.setX(posteReferenciaNoInicial.getX());
				coordenadaRosacea2.setY(posteReferenciaNoInicial.getY());
				coordenadaRosacea2.setZ(altura);
				
				Peca rosacea = new Peca();
				rosacea.setId(String.valueOf(identificadorPecas));
				rosacea.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea.setTipo(SciaXMLContantes.ROSACEA);
				rosacea.setNoInicial(coordenadaRosacea1.getId());
				rosacea.setNoFinal(coordenadaRosacea2.getId());
				
				this.sumarioDados.getListaDeNos().add(coordenadaRosacea1);
				this.sumarioDados.getListaDeNos().add(coordenadaRosacea2);
				this.sumarioDados.getPecasFinais().add(rosacea);
				
				RepositorioPecas.listaRosaceas.add(rosacea);
			}
			
			else if (posteReferencia.getTipo().equals(SciaXMLContantes.KIP025RE)){
				Coordenada posteReferenciaNoInicial = Calculo.getNo(posteReferencia.getNoInicial());
				
				Double altura = posteReferenciaNoInicial.getZ() + 0.12125;
				
				Coordenada coordenadaRosacea1 = new Coordenada();
				coordenadaRosacea1.setId(identificadorNos.toString());
				coordenadaRosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaRosacea1.setX(posteReferenciaNoInicial.getX());
				coordenadaRosacea1.setY(posteReferenciaNoInicial.getY());
				coordenadaRosacea1.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenadaRosacea2 = new Coordenada();
				coordenadaRosacea2.setId(identificadorNos.toString());
				coordenadaRosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaRosacea2.setX(posteReferenciaNoInicial.getX());
				coordenadaRosacea2.setY(posteReferenciaNoInicial.getY());
				coordenadaRosacea2.setZ(altura);
				
				Peca rosacea = new Peca();
				rosacea.setId(String.valueOf(identificadorPecas));
				rosacea.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea.setTipo(SciaXMLContantes.ROSACEA);
				rosacea.setNoInicial(coordenadaRosacea1.getId());
				rosacea.setNoFinal(coordenadaRosacea2.getId());
				
				this.sumarioDados.getListaDeNos().add(coordenadaRosacea1);
				this.sumarioDados.getListaDeNos().add(coordenadaRosacea2);
				this.sumarioDados.getPecasFinais().add(rosacea);
				
				RepositorioPecas.listaRosaceas.add(rosacea);
			}
			else if (posteReferencia.getTipo().equals(SciaXMLContantes.KIP037R1)){
				Coordenada posteReferenciaNoInicial = Calculo.getNo(posteReferencia.getNoInicial());
				
				Double altura = posteReferenciaNoInicial.getZ() + 0.30375;
				
				Coordenada coordenadaRosacea1 = new Coordenada();
				coordenadaRosacea1.setId(identificadorNos.toString());
				coordenadaRosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaRosacea1.setX(posteReferenciaNoInicial.getX());
				coordenadaRosacea1.setY(posteReferenciaNoInicial.getY());
				coordenadaRosacea1.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenadaRosacea2 = new Coordenada();
				coordenadaRosacea2.setId(identificadorNos.toString());
				coordenadaRosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenadaRosacea2.setX(posteReferenciaNoInicial.getX());
				coordenadaRosacea2.setY(posteReferenciaNoInicial.getY());
				coordenadaRosacea2.setZ(altura);
				
				Peca rosacea = new Peca();
				rosacea.setId(String.valueOf(identificadorPecas));
				rosacea.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea.setTipo(SciaXMLContantes.ROSACEA);
				rosacea.setNoInicial(coordenadaRosacea1.getId());
				rosacea.setNoFinal(coordenadaRosacea2.getId());
				
				this.sumarioDados.getListaDeNos().add(coordenadaRosacea1);
				this.sumarioDados.getListaDeNos().add(coordenadaRosacea2);
				this.sumarioDados.getPecasFinais().add(rosacea);		
				
				RepositorioPecas.listaRosaceas.add(rosacea);
			}
			else if (posteReferencia.getTipo().equals(SciaXMLContantes.KIP037R2)){
				
				Coordenada posteReferenciaNoInicial = Calculo.getNo(posteReferencia.getNoInicial());
				
				Double altura = posteReferenciaNoInicial.getZ() + 0.1215;
				
				Coordenada coordenada1Rosacea1 = new Coordenada();
				coordenada1Rosacea1.setId(identificadorNos.toString());
				coordenada1Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea1.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea1 = new Coordenada();
				coordenada2Rosacea1.setId(identificadorNos.toString());
				coordenada2Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea1.setZ(altura);
				
				altura = posteReferenciaNoInicial.getZ() + 0.2965;
				
				Coordenada coordenada1Rosacea2 = new Coordenada();
				coordenada1Rosacea2.setId(identificadorNos.toString());
				coordenada1Rosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea2.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea2.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea2.setZ(altura);
				
				altura += 0.00700;
				Coordenada coordenada2Rosacea2 = new Coordenada();
				coordenada2Rosacea2.setId(identificadorNos.toString());
				coordenada2Rosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea2.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea2.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea2.setZ(altura);
				
				Peca rosacea1 = new Peca();
				rosacea1.setId(String.valueOf(identificadorPecas));
				rosacea1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea1.setTipo(SciaXMLContantes.ROSACEA);
				rosacea1.setNoInicial(coordenada1Rosacea1.getId());
				rosacea1.setNoFinal(coordenada2Rosacea1.getId());
				
				Peca rosacea2 = new Peca();
				rosacea2.setId(String.valueOf(identificadorPecas));
				rosacea2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea2.setTipo(SciaXMLContantes.ROSACEA);
				rosacea2.setNoInicial(coordenada1Rosacea2.getId());
				rosacea2.setNoFinal(coordenada2Rosacea2.getId());
				
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea2);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea2); 
				
				this.sumarioDados.getPecasFinais().add(rosacea1);
				this.sumarioDados.getPecasFinais().add(rosacea2);
				
				RepositorioPecas.listaRosaceas.add(rosacea1);
				RepositorioPecas.listaRosaceas.add(rosacea2);
				  
			}
			else if (posteReferencia.getTipo().equals(SciaXMLContantes.KIP050R1)){
			
				Coordenada posteReferenciaNoInicial = Calculo.getNo(posteReferencia.getNoInicial());
				
				Double altura = posteReferenciaNoInicial.getZ() + 0.37875;
				
				Coordenada coordenada1Rosacea1 = new Coordenada();
				coordenada1Rosacea1.setId(identificadorNos.toString());
				coordenada1Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea1.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea1 = new Coordenada();
				coordenada2Rosacea1.setId(identificadorNos.toString());
				coordenada2Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea1.setZ(altura);
				 
				
				Peca rosacea1 = new Peca();
				rosacea1.setId(String.valueOf(identificadorPecas));
				rosacea1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea1.setTipo(SciaXMLContantes.ROSACEA);
				rosacea1.setNoInicial(coordenada1Rosacea1.getId());
				rosacea1.setNoFinal(coordenada2Rosacea1.getId());
				 
				
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea1);  
				
				this.sumarioDados.getPecasFinais().add(rosacea1); 
				
				RepositorioPecas.listaRosaceas.add(rosacea1);
				
			}
			else if (posteReferencia.getTipo().equals(SciaXMLContantes.KIP050RE)){
				
				Coordenada posteReferenciaNoInicial = Calculo.getNo(posteReferencia.getNoInicial());
				
				Double altura = posteReferenciaNoInicial.getZ() + 0.37875;
				
				Coordenada coordenada1Rosacea1 = new Coordenada();
				coordenada1Rosacea1.setId(identificadorNos.toString());
				coordenada1Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea1.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea1 = new Coordenada();
				coordenada2Rosacea1.setId(identificadorNos.toString());
				coordenada2Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea1.setZ(altura);
				 
				
				Peca rosacea1 = new Peca();
				rosacea1.setId(String.valueOf(identificadorPecas));
				rosacea1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea1.setTipo(SciaXMLContantes.ROSACEA);
				rosacea1.setNoInicial(coordenada1Rosacea1.getId());
				rosacea1.setNoFinal(coordenada2Rosacea1.getId());
				 
				
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea1);
				this.sumarioDados.getPecasFinais().add(rosacea1); 
				
				RepositorioPecas.listaRosaceas.add(rosacea1);
				
			}
			else if (posteReferencia.getTipo().equals(SciaXMLContantes.KIP070R2)){
				
				Coordenada posteReferenciaNoInicial = Calculo.getNo(posteReferencia.getNoInicial());
				
				Double altura = posteReferenciaNoInicial.getZ() + 0.24625;
				
				Coordenada coordenada1Rosacea1 = new Coordenada();
				coordenada1Rosacea1.setId(identificadorNos.toString());
				coordenada1Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea1.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea1 = new Coordenada();
				coordenada2Rosacea1.setId(identificadorNos.toString());
				coordenada2Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea1.setZ(altura);
				
				altura = posteReferenciaNoInicial.getZ() + 0.62125;
				
				Coordenada coordenada1Rosacea2 = new Coordenada();
				coordenada1Rosacea2.setId(identificadorNos.toString());
				coordenada1Rosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea2.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea2.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea2.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea2 = new Coordenada();
				coordenada2Rosacea2.setId(identificadorNos.toString());
				coordenada2Rosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea2.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea2.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea2.setZ(altura);
				
				Peca rosacea1 = new Peca();
				rosacea1.setId(String.valueOf(identificadorPecas));
				rosacea1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea1.setTipo(SciaXMLContantes.ROSACEA);
				rosacea1.setNoInicial(coordenada1Rosacea1.getId());
				rosacea1.setNoFinal(coordenada2Rosacea1.getId());
				
				Peca rosacea2 = new Peca();
				rosacea2.setId(String.valueOf(identificadorPecas));
				rosacea2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea2.setTipo(SciaXMLContantes.ROSACEA);
				rosacea2.setNoInicial(coordenada1Rosacea2.getId());
				rosacea2.setNoFinal(coordenada2Rosacea2.getId());
				
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea2);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea2); 
				
				this.sumarioDados.getPecasFinais().add(rosacea1);
				this.sumarioDados.getPecasFinais().add(rosacea2); 
				
				RepositorioPecas.listaRosaceas.add(rosacea1);
				RepositorioPecas.listaRosaceas.add(rosacea2);
				
			}
			else if (posteReferencia.getTipo().equals(SciaXMLContantes.KIP100R2)){
				
				Coordenada posteReferenciaNoInicial = Calculo.getNo(posteReferencia.getNoInicial());
				
				Double altura = posteReferenciaNoInicial.getZ() + 0.37125;
				
				Coordenada coordenada1Rosacea1 = new Coordenada();
				coordenada1Rosacea1.setId(identificadorNos.toString());
				coordenada1Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea1.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea1 = new Coordenada();
				coordenada2Rosacea1.setId(identificadorNos.toString());
				coordenada2Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea1.setZ(altura);
				
				altura = posteReferenciaNoInicial.getZ() + 0.87125;
				
				Coordenada coordenada1Rosacea2 = new Coordenada();
				coordenada1Rosacea2.setId(identificadorNos.toString());
				coordenada1Rosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea2.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea2.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea2.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea2 = new Coordenada();
				coordenada2Rosacea2.setId(identificadorNos.toString());
				coordenada2Rosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea2.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea2.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea2.setZ(altura);
				
				Peca rosacea1 = new Peca();
				rosacea1.setId(String.valueOf(identificadorPecas));
				rosacea1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea1.setTipo(SciaXMLContantes.ROSACEA);
				rosacea1.setNoInicial(coordenada1Rosacea1.getId());
				rosacea1.setNoFinal(coordenada2Rosacea1.getId());
				
				Peca rosacea2 = new Peca();
				rosacea2.setId(String.valueOf(identificadorPecas));
				rosacea2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea2.setTipo(SciaXMLContantes.ROSACEA);
				rosacea2.setNoInicial(coordenada1Rosacea2.getId());
				rosacea2.setNoFinal(coordenada2Rosacea2.getId());
				
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea2);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea2); 
				
				this.sumarioDados.getPecasFinais().add(rosacea1);
				this.sumarioDados.getPecasFinais().add(rosacea2);
				
				RepositorioPecas.listaRosaceas.add(rosacea1);
				RepositorioPecas.listaRosaceas.add(rosacea2);
				
			}
			
			else if (posteReferencia.getTipo().equals(SciaXMLContantes.KIP150R3)){
				
				Coordenada posteReferenciaNoInicial = Calculo.getNo(posteReferencia.getNoInicial());
				
				Double altura = posteReferenciaNoInicial.getZ() + 0.37125;
				
				Coordenada coordenada1Rosacea1 = new Coordenada();
				coordenada1Rosacea1.setId(identificadorNos.toString());
				coordenada1Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea1.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea1 = new Coordenada();
				coordenada2Rosacea1.setId(identificadorNos.toString());
				coordenada2Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea1.setZ(altura);
				
				altura = posteReferenciaNoInicial.getZ() + 0.87125;
				
				Coordenada coordenada1Rosacea2 = new Coordenada();
				coordenada1Rosacea2.setId(identificadorNos.toString());
				coordenada1Rosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea2.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea2.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea2.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea2 = new Coordenada();
				coordenada2Rosacea2.setId(identificadorNos.toString());
				coordenada2Rosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea2.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea2.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea2.setZ(altura);
				
				altura = posteReferenciaNoInicial.getZ() + 1.37125;
				
				Coordenada coordenada1Rosacea3 = new Coordenada();
				coordenada1Rosacea3.setId(identificadorNos.toString());
				coordenada1Rosacea3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea3.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea3.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea3.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea3 = new Coordenada();
				coordenada2Rosacea3.setId(identificadorNos.toString());
				coordenada2Rosacea3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea3.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea3.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea3.setZ(altura);
				 
				Peca rosacea1 = new Peca();
				rosacea1.setId(String.valueOf(identificadorPecas));
				rosacea1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea1.setTipo(SciaXMLContantes.ROSACEA);
				rosacea1.setNoInicial(coordenada1Rosacea1.getId());
				rosacea1.setNoFinal(coordenada2Rosacea1.getId());
				
				Peca rosacea2 = new Peca();
				rosacea2.setId(String.valueOf(identificadorPecas));
				rosacea2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea2.setTipo(SciaXMLContantes.ROSACEA);
				rosacea2.setNoInicial(coordenada1Rosacea2.getId());
				rosacea2.setNoFinal(coordenada2Rosacea2.getId());
				
				Peca rosacea3 = new Peca();
				rosacea3.setId(String.valueOf(identificadorPecas));
				rosacea3.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea3.setTipo(SciaXMLContantes.ROSACEA);
				rosacea3.setNoInicial(coordenada1Rosacea3.getId());
				rosacea3.setNoFinal(coordenada2Rosacea3.getId());

				
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea2);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea2); 
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea3);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea3);
				
				this.sumarioDados.getPecasFinais().add(rosacea1);
				this.sumarioDados.getPecasFinais().add(rosacea2);
				this.sumarioDados.getPecasFinais().add(rosacea3); 
				
				RepositorioPecas.listaRosaceas.add(rosacea1);
				RepositorioPecas.listaRosaceas.add(rosacea2);
				RepositorioPecas.listaRosaceas.add(rosacea3);
			}
			else if (posteReferencia.getTipo().equals(SciaXMLContantes.KIP200R4)){
				
				Coordenada posteReferenciaNoInicial = Calculo.getNo(posteReferencia.getNoInicial());
				
				Double altura = posteReferenciaNoInicial.getZ() + 0.37125;
				
				Coordenada coordenada1Rosacea1 = new Coordenada();
				coordenada1Rosacea1.setId(identificadorNos.toString());
				coordenada1Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea1.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea1 = new Coordenada();
				coordenada2Rosacea1.setId(identificadorNos.toString());
				coordenada2Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea1.setZ(altura);
				
				altura = posteReferenciaNoInicial.getZ() + 0.87125;
				
				Coordenada coordenada1Rosacea2 = new Coordenada();
				coordenada1Rosacea2.setId(identificadorNos.toString());
				coordenada1Rosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea2.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea2.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea2.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea2 = new Coordenada();
				coordenada2Rosacea2.setId(identificadorNos.toString());
				coordenada2Rosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea2.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea2.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea2.setZ(altura);
				
				altura = posteReferenciaNoInicial.getZ() + 1.37125;
				
				Coordenada coordenada1Rosacea3 = new Coordenada();
				coordenada1Rosacea3.setId(identificadorNos.toString());
				coordenada1Rosacea3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea3.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea3.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea3.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea3 = new Coordenada();
				coordenada2Rosacea3.setId(identificadorNos.toString());
				coordenada2Rosacea3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea3.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea3.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea3.setZ(altura);
				
				altura = posteReferenciaNoInicial.getZ() + 1.87125;
				
				Coordenada coordenada1Rosacea4 = new Coordenada();
				coordenada1Rosacea4.setId(identificadorNos.toString());
				coordenada1Rosacea4.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea4.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea4.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea4.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea4 = new Coordenada();
				coordenada2Rosacea4.setId(identificadorNos.toString());
				coordenada2Rosacea4.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea4.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea4.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea4.setZ(altura);
				 
				Peca rosacea1 = new Peca();
				rosacea1.setId(String.valueOf(identificadorPecas));
				rosacea1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea1.setTipo(SciaXMLContantes.ROSACEA);
				rosacea1.setNoInicial(coordenada1Rosacea1.getId());
				rosacea1.setNoFinal(coordenada2Rosacea1.getId());
				
				Peca rosacea2 = new Peca();
				rosacea2.setId(String.valueOf(identificadorPecas));
				rosacea2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea2.setTipo(SciaXMLContantes.ROSACEA);
				rosacea2.setNoInicial(coordenada1Rosacea2.getId());
				rosacea2.setNoFinal(coordenada2Rosacea2.getId());
				
				Peca rosacea3 = new Peca();
				rosacea3.setId(String.valueOf(identificadorPecas));
				rosacea3.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea3.setTipo(SciaXMLContantes.ROSACEA);
				rosacea3.setNoInicial(coordenada1Rosacea3.getId());
				rosacea3.setNoFinal(coordenada2Rosacea3.getId());
				
				Peca rosacea4 = new Peca();
				rosacea4.setId(String.valueOf(identificadorPecas));
				rosacea4.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea4.setTipo(SciaXMLContantes.ROSACEA);
				rosacea4.setNoInicial(coordenada1Rosacea4.getId());
				rosacea4.setNoFinal(coordenada2Rosacea4.getId());
 				
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea2);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea2); 
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea3);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea3); 
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea4);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea4);
 				
				this.sumarioDados.getPecasFinais().add(rosacea1);
				this.sumarioDados.getPecasFinais().add(rosacea2);
				this.sumarioDados.getPecasFinais().add(rosacea3);
				this.sumarioDados.getPecasFinais().add(rosacea4);
				
				RepositorioPecas.listaRosaceas.add(rosacea1);
				RepositorioPecas.listaRosaceas.add(rosacea2);
				RepositorioPecas.listaRosaceas.add(rosacea3);
				RepositorioPecas.listaRosaceas.add(rosacea4);
			}
			else if (posteReferencia.getTipo().equals(SciaXMLContantes.KIP300R6)){
				
				Coordenada posteReferenciaNoInicial = Calculo.getNo(posteReferencia.getNoInicial());
				
				Double altura = posteReferenciaNoInicial.getZ() + 0.37125;
				
				Coordenada coordenada1Rosacea1 = new Coordenada();
				coordenada1Rosacea1.setId(identificadorNos.toString());
				coordenada1Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea1.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea1 = new Coordenada();
				coordenada2Rosacea1.setId(identificadorNos.toString());
				coordenada2Rosacea1.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea1.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea1.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea1.setZ(altura);
				
				altura = posteReferenciaNoInicial.getZ() + 0.87125;
				
				Coordenada coordenada1Rosacea2 = new Coordenada();
				coordenada1Rosacea2.setId(identificadorNos.toString());
				coordenada1Rosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea2.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea2.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea2.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea2 = new Coordenada();
				coordenada2Rosacea2.setId(identificadorNos.toString());
				coordenada2Rosacea2.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea2.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea2.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea2.setZ(altura);
				
				altura = posteReferenciaNoInicial.getZ() + 1.37125;
				
				Coordenada coordenada1Rosacea3 = new Coordenada();
				coordenada1Rosacea3.setId(identificadorNos.toString());
				coordenada1Rosacea3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea3.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea3.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea3.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea3 = new Coordenada();
				coordenada2Rosacea3.setId(identificadorNos.toString());
				coordenada2Rosacea3.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea3.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea3.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea3.setZ(altura);
				
				altura = posteReferenciaNoInicial.getZ() + 1.87125;
				
				Coordenada coordenada1Rosacea4 = new Coordenada();
				coordenada1Rosacea4.setId(identificadorNos.toString());
				coordenada1Rosacea4.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea4.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea4.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea4.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea4 = new Coordenada();
				coordenada2Rosacea4.setId(identificadorNos.toString());
				coordenada2Rosacea4.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea4.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea4.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea4.setZ(altura);
				
				altura = posteReferenciaNoInicial.getZ() + 2.37125;
				
				Coordenada coordenada1Rosacea5 = new Coordenada();
				coordenada1Rosacea5.setId(identificadorNos.toString());
				coordenada1Rosacea5.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea5.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea5.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea5.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea5 = new Coordenada();
				coordenada2Rosacea5.setId(identificadorNos.toString());
				coordenada2Rosacea5.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea5.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea5.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea5.setZ(altura);
				
				altura = posteReferenciaNoInicial.getZ() + 2.87125;
				
				Coordenada coordenada1Rosacea6 = new Coordenada();
				coordenada1Rosacea6.setId(identificadorNos.toString());
				coordenada1Rosacea6.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada1Rosacea6.setX(posteReferenciaNoInicial.getX());
				coordenada1Rosacea6.setY(posteReferenciaNoInicial.getY());
				coordenada1Rosacea6.setZ(altura);
				
				altura += 0.00750;
				Coordenada coordenada2Rosacea6 = new Coordenada();
				coordenada2Rosacea6.setId(identificadorNos.toString());
				coordenada2Rosacea6.setName(SciaXMLContantes.INDEXADOR_NO + String.valueOf(identificadorNos++));					
				coordenada2Rosacea6.setX(posteReferenciaNoInicial.getX());
				coordenada2Rosacea6.setY(posteReferenciaNoInicial.getY());
				coordenada2Rosacea6.setZ(altura);
				 
				Peca rosacea1 = new Peca();
				rosacea1.setId(String.valueOf(identificadorPecas));
				rosacea1.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea1.setTipo(SciaXMLContantes.ROSACEA);
				rosacea1.setNoInicial(coordenada1Rosacea1.getId());
				rosacea1.setNoFinal(coordenada2Rosacea1.getId());
				
				Peca rosacea2 = new Peca();
				rosacea2.setId(String.valueOf(identificadorPecas));
				rosacea2.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea2.setTipo(SciaXMLContantes.ROSACEA);
				rosacea2.setNoInicial(coordenada1Rosacea2.getId());
				rosacea2.setNoFinal(coordenada2Rosacea2.getId());
				
				Peca rosacea3 = new Peca();
				rosacea3.setId(String.valueOf(identificadorPecas));
				rosacea3.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea3.setTipo(SciaXMLContantes.ROSACEA);
				rosacea3.setNoInicial(coordenada1Rosacea3.getId());
				rosacea3.setNoFinal(coordenada2Rosacea3.getId());
				
				Peca rosacea4 = new Peca();
				rosacea4.setId(String.valueOf(identificadorPecas));
				rosacea4.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea4.setTipo(SciaXMLContantes.ROSACEA);
				rosacea4.setNoInicial(coordenada1Rosacea4.getId());
				rosacea4.setNoFinal(coordenada2Rosacea4.getId());
 				
				Peca rosacea5 = new Peca();
				rosacea5.setId(String.valueOf(identificadorPecas));
				rosacea5.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea5.setTipo(SciaXMLContantes.ROSACEA);
				rosacea5.setNoInicial(coordenada1Rosacea5.getId());
				rosacea5.setNoFinal(coordenada2Rosacea5.getId());
				
				Peca rosacea6 = new Peca();
				rosacea6.setId(String.valueOf(identificadorPecas));
				rosacea6.setName(SciaXMLContantes.INDEXADOR_PECA + String.valueOf(identificadorPecas++));
				rosacea6.setTipo(SciaXMLContantes.ROSACEA);
				rosacea6.setNoInicial(coordenada1Rosacea6.getId());
				rosacea6.setNoFinal(coordenada2Rosacea6.getId());
				
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea1);
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea2);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea2); 
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea3);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea3); 
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea4);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea4); 
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea5);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea5); 
				this.sumarioDados.getListaDeNos().add(coordenada1Rosacea6);
				this.sumarioDados.getListaDeNos().add(coordenada2Rosacea6);
 				
				this.sumarioDados.getPecasFinais().add(rosacea1);
				this.sumarioDados.getPecasFinais().add(rosacea2);
				this.sumarioDados.getPecasFinais().add(rosacea3);
				this.sumarioDados.getPecasFinais().add(rosacea4);
 				this.sumarioDados.getPecasFinais().add(rosacea5);
				this.sumarioDados.getPecasFinais().add(rosacea6);
				
				RepositorioPecas.listaRosaceas.add(rosacea1);
				RepositorioPecas.listaRosaceas.add(rosacea2);
				RepositorioPecas.listaRosaceas.add(rosacea3);
				RepositorioPecas.listaRosaceas.add(rosacea4);
				RepositorioPecas.listaRosaceas.add(rosacea5);
				RepositorioPecas.listaRosaceas.add(rosacea6);
			}
		}
	}
	
}
