package br.com.scia.xml.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.JOptionPane;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.view.Tipo;
import br.com.scia.xml.entity.view.TipoPecaXY;
import br.com.scia.xml.util.SciaXMLContantes;


public class TipoPecaXYController extends TipoController{

	public TipoPecaXYController(PrincipalController principal) {
		super(principal);
	}

	@Override
	public void inicializar() {
		if (RepositorioPecas.pecas != null){
			Set<String> pecas = RepositorioPecas.pecas.keySet();
			
			// Iniciando o load de travessas
			this.principal.tiposTravessas = new ArrayList<Tipo>();
			int ordem = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.KITRV))
				{
					TipoPecaXY t = new TipoPecaXY(String.valueOf(ordem), peca);
					this.principal.tiposTravessas.add(t);
				}
				ordem++;	
			}
			if (this.principal.tiposTravessas.size() > 0){
				this.principal.tiposPecasX.getItems().add(SciaXMLContantes.TIPO_TRAVESSA);
				this.principal.tiposPecasY.getItems().add(SciaXMLContantes.TIPO_TRAVESSA);
			} 
			
			// Iniciando o load de cruzetas
			this.principal.tiposCruzetas = new ArrayList<Tipo>();
			ordem = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.CRU))
				{
					TipoPecaXY t = new TipoPecaXY(String.valueOf(ordem), peca);
					this.principal.tiposCruzetas.add(t);
				}
				ordem++;	
			}
			if (this.principal.tiposCruzetas.size() > 0){
				this.principal.tiposPecasX.getItems().add(SciaXMLContantes.TIPO_CRUZETA);
				this.principal.tiposPecasY.getItems().add(SciaXMLContantes.TIPO_CRUZETA);
			} 
			
			// Iniciando o load de escoras
			this.principal.tiposEscoras = new ArrayList<Tipo>();
			ordem = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.ESC))
				{
					TipoPecaXY t = new TipoPecaXY(String.valueOf(ordem), peca);
					this.principal.tiposEscoras.add(t);
				}
				ordem++;	
			}
			if (this.principal.tiposEscoras.size() > 0){
				this.principal.tiposPecasX.getItems().add(SciaXMLContantes.TIPO_ESCORA);
				this.principal.tiposPecasY.getItems().add(SciaXMLContantes.TIPO_ESCORA);
			} 
			
			
			this.principal.tiposPecasX.getSelectionModel().selectFirst();
			this.principal.tiposPecasY.getSelectionModel().selectFirst();
		}
	}

	@Override
	public void carregar() {
		if (this.principal.tiposTravessas!=null && this.principal.tiposTravessas.size()>0 && 
				this.principal.tiposPecasX.getSelectionModel().getSelectedItem() != null &&
						this.principal.tiposPecasX.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_TRAVESSA)){
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposTravessas);				 
			this.principal.tamanhosPecasX.setItems(list);
		}
		
		if (this.principal.tiposCruzetas!=null && this.principal.tiposCruzetas.size()>0 && 
				this.principal.tiposPecasX.getSelectionModel().getSelectedItem() != null &&
						this.principal.tiposPecasX.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_CRUZETA)){
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposCruzetas);				 
			this.principal.tamanhosPecasX.setItems(list);
		}
		
		if (this.principal.tiposEscoras!=null && this.principal.tiposEscoras.size()>0 && 
				this.principal.tiposPecasX.getSelectionModel().getSelectedItem() != null &&
						this.principal.tiposPecasX.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_ESCORA)){
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposEscoras);				 
			this.principal.tamanhosPecasX.setItems(list);
		}
		if (this.principal.tiposTravessas!=null && this.principal.tiposTravessas.size()>0 && 
				this.principal.tiposPecasY.getSelectionModel().getSelectedItem() != null &&
						this.principal.tiposPecasY.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_TRAVESSA)){
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposTravessas);				 
			this.principal.tamanhosPecasY.setItems(list);
		}
		
		if (this.principal.tiposCruzetas!=null && this.principal.tiposCruzetas.size()>0 && 
				this.principal.tiposPecasY.getSelectionModel().getSelectedItem() != null &&
						this.principal.tiposPecasY.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_CRUZETA)){
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposCruzetas);				 
			this.principal.tamanhosPecasY.setItems(list);
		}
		
		if (this.principal.tiposEscoras!=null && this.principal.tiposEscoras.size()>0 && 
				this.principal.tiposPecasY.getSelectionModel().getSelectedItem() != null &&
						this.principal.tiposPecasY.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_ESCORA)){
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposEscoras);				 
			this.principal.tamanhosPecasY.setItems(list);
		}
	}

	public Boolean validarRegrasPecasXY(Tipo tipo, String origem) {
		
		if (tipo == null || "".equals(origem) || tipo.getItem() == null)
			JOptionPane.showMessageDialog(null, "Favor informar o tamanho da peça");
		
		if (tipo != null && tipo.getItem() != null && tipo.getItem().startsWith(SciaXMLContantes.KITRV)){
			return true;
		}else{			
			if (SciaXMLContantes.ORIGEM_X.equals(origem)){
				if (tipo.getItem().startsWith(SciaXMLContantes.ESC) && this.principal.pecasX.getItems().size() == 0){
					this.principal.adicionarPecaY(tipo.getItem());
					this.principal.areaEscoraX.setDisable(false);
					this.principal.areaEscoraY.setDisable(false);
					return true;
				}
				if (tipo.getItem().startsWith(SciaXMLContantes.ESC)){
					this.principal.areaEscoraY.setDisable(false);
					this.principal.areaEscoraX.setDisable(false);
					return true;
				}
				if (tipo.getItem().startsWith(SciaXMLContantes.CRU) && SciaXMLContantes.ORIGEM_X.equals(origem)){
					if (!validarQuantidadeCruzetas(this.principal.pecasY.getItems())){
						this.principal.areaCruzetaX.setDisable(false);
						this.principal.areaCruzetaY.setDisable(false);
						return true;
					}else{
						JOptionPane.showMessageDialog(null, "Eixo Y já possui cruzetas.");
					}
						
				}
			}else if (SciaXMLContantes.ORIGEM_Y.equals(origem)){
				if (tipo.getItem().startsWith(SciaXMLContantes.ESC) && this.principal.pecasY.getItems().size() == 0){
					this.principal.adicionarPecaX(tipo.getItem());
					this.principal.areaEscoraY.setDisable(false);
					this.principal.areaEscoraX.setDisable(false);
					return true;
				}
				if (tipo.getItem().startsWith(SciaXMLContantes.ESC)){
					this.principal.areaEscoraY.setDisable(false);
					this.principal.areaEscoraX.setDisable(false);
					return true;
				}
				if (tipo.getItem().startsWith(SciaXMLContantes.CRU) && SciaXMLContantes.ORIGEM_Y.equals(origem)){
					if (!validarQuantidadeCruzetas(this.principal.pecasX.getItems())){
						this.principal.areaCruzetaX.setDisable(false);
						this.principal.areaCruzetaY.setDisable(false);
						return true;
					}else{
						JOptionPane.showMessageDialog(null, "Eixo X já possui cruzetas.");
					}
				}
			}
		}
		
		return false;
	}	
	
	public Boolean validarQuantidadeCruzetas(List<Tipo> pecas){
		Boolean retorno = false;
		for (Tipo tipoPecaXY : pecas) {
			if (tipoPecaXY.getItem().startsWith(SciaXMLContantes.CRU)){
				retorno = true;				
				break;
			}
		}
		return retorno;
	}
}
