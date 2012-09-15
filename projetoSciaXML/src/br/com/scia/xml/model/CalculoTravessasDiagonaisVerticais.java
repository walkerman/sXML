package br.com.scia.xml.model;

import java.util.ArrayList;
import java.util.List;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.util.Identificadores;
import br.com.scia.xml.util.SciaXMLConstantes;


public class CalculoTravessasDiagonaisVerticais {

	private SumarioDados sumarioDados;
	
	public CalculoTravessasDiagonaisVerticais() {
		this.sumarioDados = Calculo.dados;
	}
	
	public void realizarCalculo(){
		List<Peca> travessasReplicadas = RepositorioPecas.listaTravessasReplicadas;
		
		if (travessasReplicadas != null && travessasReplicadas.size() > 0){			
			List<ArrayList<Peca>> listaDeListas = new ArrayList<ArrayList<Peca>>();
			List<String> idAdicionados = new ArrayList<String>();
			
			for (Peca travessaReferencia : travessasReplicadas) {
				if (!idAdicionados.contains(travessaReferencia.getId())){
					ArrayList<Peca> pecasMesmaCoordenadaXY = new ArrayList<Peca>();
					for (Peca travessa : travessasReplicadas) {
						if (!(pecasMesmaCoordenadaXY.contains(travessa))){
							if (travessa.getNoInicial().getX() == travessaReferencia.getNoInicial().getX() &&
								travessa.getNoInicial().getY() == travessaReferencia.getNoInicial().getY() &&
								travessa.getNoFinal().getX() == travessaReferencia.getNoFinal().getX() &&
								travessa.getNoFinal().getY() == travessaReferencia.getNoFinal().getY()){
								
								pecasMesmaCoordenadaXY.add(travessa);
								idAdicionados.add(travessa.getId());
							}
						}
					}

					if (pecasMesmaCoordenadaXY.size() > 0)
						listaDeListas.add(pecasMesmaCoordenadaXY);
				}
			}
			
			for (ArrayList<Peca> lista : listaDeListas) {
				boolean isNoFinal = false;
				for (int i = 0; i < lista.size(); i++) {
					if (i > 0){
						Peca peca = lista.get(i);
						Peca pecaAnterior = lista.get(i-1);
							
						Peca novaTravessaDiagonal = new Peca();
						novaTravessaDiagonal.setId(String.valueOf(Identificadores.getIdentificarPecas().toString()));
						novaTravessaDiagonal.setName(SciaXMLConstantes.INDEXADOR_PECA + String.valueOf(novaTravessaDiagonal.getId()));
						novaTravessaDiagonal.setTipo(SciaXMLConstantes.KIDI1215);
						
						if (isNoFinal){
							novaTravessaDiagonal.setNoInicial(pecaAnterior.getNoFinal());
							novaTravessaDiagonal.setNoFinal(peca.getNoInicial());
							isNoFinal = true;
						}else{
							novaTravessaDiagonal.setNoInicial(pecaAnterior.getNoInicial());
							novaTravessaDiagonal.setNoFinal(peca.getNoFinal());
							isNoFinal = false;
						}
						
						this.sumarioDados.getPecasFinais().add(novaTravessaDiagonal);
					}
				}
			}
		}
	}
}
