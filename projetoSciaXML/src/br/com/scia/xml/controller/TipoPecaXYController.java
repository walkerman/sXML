package br.com.scia.xml.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;

import javax.swing.JOptionPane;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.exception.SciaXMLValidationException;
import br.com.scia.xml.entity.view.Tipo;
import br.com.scia.xml.entity.view.TipoPecaXY;
import br.com.scia.xml.util.SciaXMLConstantes;


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
				if (peca.startsWith(SciaXMLConstantes.KITRV))
				{
					TipoPecaXY t = new TipoPecaXY(String.valueOf(ordem), peca);
					this.principal.tiposTravessas.add(t);
				}
				ordem++;	
			}
			if (this.principal.tiposTravessas.size() > 0){
				this.principal.tiposPecasX.getItems().add(SciaXMLConstantes.TIPO_TRAVESSA);
				this.principal.tiposPecasY.getItems().add(SciaXMLConstantes.TIPO_TRAVESSA);
			} 
			
			// Iniciando o load de cruzetas
			this.principal.tiposCruzetas = new ArrayList<Tipo>();
			ordem = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLConstantes.CRU))
				{
					TipoPecaXY t = new TipoPecaXY(String.valueOf(ordem), peca);
					this.principal.tiposCruzetas.add(t);
				}
				ordem++;	
			}
			if (this.principal.tiposCruzetas.size() > 0){
				this.principal.tiposPecasX.getItems().add(SciaXMLConstantes.TIPO_CRUZETA);
				this.principal.tiposPecasY.getItems().add(SciaXMLConstantes.TIPO_CRUZETA);
			} 
			
			// Iniciando o load de escoras
			this.principal.tiposEscoras = new ArrayList<Tipo>();
			ordem = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLConstantes.ESC))
				{
					TipoPecaXY t = new TipoPecaXY(String.valueOf(ordem), peca);
					this.principal.tiposEscoras.add(t);
				}
				ordem++;	
			}
			if (this.principal.tiposEscoras.size() > 0){
				this.principal.tiposPecasX.getItems().add(SciaXMLConstantes.TIPO_ESCORA);
				this.principal.tiposPecasY.getItems().add(SciaXMLConstantes.TIPO_ESCORA);
			} 
			
			
			this.principal.tiposPecasX.getSelectionModel().selectFirst();
			this.principal.tiposPecasY.getSelectionModel().selectFirst();
		}
	}

	@Override
	public void carregar() {
		if (this.principal.tiposTravessas!=null && this.principal.tiposTravessas.size()>0 && 
				this.principal.tiposPecasX.getSelectionModel().getSelectedItem() != null &&
						this.principal.tiposPecasX.getSelectionModel().getSelectedItem().equals(SciaXMLConstantes.TIPO_TRAVESSA)){
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposTravessas);				 
			this.principal.tamanhosPecasX.setItems(list);
		}
		
		if (this.principal.tiposCruzetas!=null && this.principal.tiposCruzetas.size()>0 && 
				this.principal.tiposPecasX.getSelectionModel().getSelectedItem() != null &&
						this.principal.tiposPecasX.getSelectionModel().getSelectedItem().equals(SciaXMLConstantes.TIPO_CRUZETA)){
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposCruzetas);				 
			this.principal.tamanhosPecasX.setItems(list);
		}
		
		if (this.principal.tiposEscoras!=null && this.principal.tiposEscoras.size()>0 && 
				this.principal.tiposPecasX.getSelectionModel().getSelectedItem() != null &&
						this.principal.tiposPecasX.getSelectionModel().getSelectedItem().equals(SciaXMLConstantes.TIPO_ESCORA)){
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposEscoras);				 
			this.principal.tamanhosPecasX.setItems(list);
		}
		if (this.principal.tiposTravessas!=null && this.principal.tiposTravessas.size()>0 && 
				this.principal.tiposPecasY.getSelectionModel().getSelectedItem() != null &&
						this.principal.tiposPecasY.getSelectionModel().getSelectedItem().equals(SciaXMLConstantes.TIPO_TRAVESSA)){
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposTravessas);				 
			this.principal.tamanhosPecasY.setItems(list);
		}
		
		if (this.principal.tiposCruzetas!=null && this.principal.tiposCruzetas.size()>0 && 
				this.principal.tiposPecasY.getSelectionModel().getSelectedItem() != null &&
						this.principal.tiposPecasY.getSelectionModel().getSelectedItem().equals(SciaXMLConstantes.TIPO_CRUZETA)){
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposCruzetas);				 
			this.principal.tamanhosPecasY.setItems(list);
		}
		
		if (this.principal.tiposEscoras!=null && this.principal.tiposEscoras.size()>0 && 
				this.principal.tiposPecasY.getSelectionModel().getSelectedItem() != null &&
						this.principal.tiposPecasY.getSelectionModel().getSelectedItem().equals(SciaXMLConstantes.TIPO_ESCORA)){
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposEscoras);				 
			this.principal.tamanhosPecasY.setItems(list);
		}
	}

	public Boolean validarRegrasPecasXY(Tipo tipo, String origem) throws SciaXMLValidationException{
		
		RadioButton t = (RadioButton) this.principal.tipos.getSelectedToggle();
		
		if (tipo == null || "".equals(origem) || tipo.getItem() == null)
			JOptionPane.showMessageDialog(null, "Favor informar o tamanho da peça");
		
		if (tipo != null && tipo.getItem() != null && tipo.getItem().startsWith(SciaXMLConstantes.KITRV)){
			
			if (t.getId().equals(SciaXMLConstantes.KIBLOC_VIGA) &&
					(Double) Double.parseDouble(this.principal.quantidadePecasY.getText()) >= 2 &&
						SciaXMLConstantes.ORIGEM_Y.equals(origem))
				throw new SciaXMLValidationException("Tipo da estrutura permite no máximo 2 peças no eixo Y.");
			
			return true;
		}else{			
			if (SciaXMLConstantes.ORIGEM_X.equals(origem)){
				if (tipo.getItem().startsWith(SciaXMLConstantes.ESC) && this.principal.pecasX.getItems().size() == 0){
					this.principal.adicionarPecaY(tipo.getItem());
					this.principal.areaEscoraX.setDisable(false);
					this.principal.areaEscoraY.setDisable(false);
					return true;
				}
				if (tipo.getItem().startsWith(SciaXMLConstantes.ESC)){
					this.principal.areaEscoraY.setDisable(false);
					this.principal.areaEscoraX.setDisable(false);
					return true;
				}
				if (tipo.getItem().startsWith(SciaXMLConstantes.CRU) && SciaXMLConstantes.ORIGEM_X.equals(origem)){
					if (!validarQuantidadeCruzetas(this.principal.pecasY.getItems())){
						this.principal.areaCruzetaX.setDisable(false);
						this.principal.areaCruzetaY.setDisable(false);
						return true;
					}else{
						JOptionPane.showMessageDialog(null, "Eixo Y já possui cruzetas.");
					}
						
				}
			}else if (SciaXMLConstantes.ORIGEM_Y.equals(origem)){
				
				if (t.getId().equals(SciaXMLConstantes.KIBLOC_VIGA) &&
						(Double) Double.parseDouble(this.principal.quantidadePecasY.getText()) >= 2 &&
							SciaXMLConstantes.ORIGEM_Y.equals(origem))
					throw new SciaXMLValidationException("Tipo da estrutura permite no máximo 2 peças no eixo Y.");
				else{
					if (tipo.getItem().startsWith(SciaXMLConstantes.ESC) && this.principal.pecasY.getItems().size() == 0){
						this.principal.adicionarPecaX(tipo.getItem());
						this.principal.areaEscoraY.setDisable(false);
						this.principal.areaEscoraX.setDisable(false);
						return true;
					}
					if (tipo.getItem().startsWith(SciaXMLConstantes.ESC)){
						this.principal.areaEscoraY.setDisable(false);
						this.principal.areaEscoraX.setDisable(false);
						return true;
					}
					if (tipo.getItem().startsWith(SciaXMLConstantes.CRU) && SciaXMLConstantes.ORIGEM_Y.equals(origem)){
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
		}
		
		return false;
	}	
	
	public Boolean validarQuantidadeCruzetas(List<Tipo> pecas){
		Boolean retorno = false;
		for (Tipo tipoPecaXY : pecas) {
			if (tipoPecaXY.getItem().startsWith(SciaXMLConstantes.CRU)){
				retorno = true;				
				break;
			}
		}
		return retorno;
	}
}
