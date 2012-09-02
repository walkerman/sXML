package br.com.scia.xml.controller;

import java.util.ArrayList;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.view.Tipo;
import br.com.scia.xml.entity.view.TipoViga;
import br.com.scia.xml.util.SciaXMLContantes;
import br.com.scia.xml.util.SciaXMLUtils;

public class TipoVigaController extends TipoController{

	public static final Byte VIGA_PRINCIPAL = 1;
	public static final Byte VIGA_SECUNDARIA = 2;
	public Byte VIGA_LOAD = 0; 
	
	public TipoVigaController(PrincipalController principal) {
		super(principal);
	}

	@Override
	public void inicializar() {
		this.inicializarVigasPrincipais();
		this.inicializarVigasSecundarias();		
	}

	@Override
	public void carregar() {
		if (this.VIGA_LOAD == VIGA_PRINCIPAL)
			carregarVigasPrincipais();
		else if (this.VIGA_LOAD == VIGA_SECUNDARIA)
			carregarVigasSecundarias();
	}

	private void carregarVigasPrincipais(){
		if (this.principal.tiposVigasPrincipaisHT20!=null && 
				this.principal.tiposVigasPrincipaisHT20.size()>0 && 
				this.principal.tiposVigasPrincipais.getSelectionModel().getSelectedItem() != null &&
				this.principal.tiposVigasPrincipais.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_VIGA_HT20)){
				
				ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposVigasPrincipaisHT20);		
				this.principal.vigasPrincipais.setItems(list);	
				this.principal.sumarioVigasPrincipais.setItems(FXCollections.observableArrayList(SciaXMLUtils.getItensTipo(this.principal.tiposVigasPrincipaisHT20)));
			}else
			if (this.principal.tiposVigasPrincipaisV18!=null && 
				this.principal.tiposVigasPrincipaisV18.size()>0 && 
				this.principal.tiposVigasPrincipais.getSelectionModel().getSelectedItem() != null && 
				this.principal.tiposVigasPrincipais.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_VIGA_V18)){
				
				ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposVigasPrincipaisV18);	
				this.principal.vigasPrincipais.setItems(list);
				this.principal.sumarioVigasPrincipais.setItems(FXCollections.observableArrayList(SciaXMLUtils.getItensTipo(this.principal.tiposVigasPrincipaisV18)));
			}else
			if (this.principal.tiposVigasPrincipaisV75!=null && 
				this.principal.tiposVigasPrincipaisV75.size()>0 && 
				this.principal.tiposVigasPrincipais.getSelectionModel().getSelectedItem() != null &&
				this.principal.tiposVigasPrincipais.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_VIGA_V75)){
				
				ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposVigasPrincipaisV75);	
				this.principal.vigasPrincipais.setItems(list);
				this.principal.sumarioVigasPrincipais.setItems(FXCollections.observableArrayList(SciaXMLUtils.getItensTipo(this.principal.tiposVigasPrincipaisV75)));
			}else
			if (this.principal.tiposVigasPrincipaisVA18!=null && 
				this.principal.tiposVigasPrincipaisVA18.size()>0 && 
				this.principal.tiposVigasPrincipais.getSelectionModel().getSelectedItem() != null &&
				this.principal.tiposVigasPrincipais.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_VIGA_VA18)){
				
				ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposVigasPrincipaisVA18);	
				this.principal.vigasPrincipais.setItems(list);
				this.principal.sumarioVigasPrincipais.setItems(FXCollections.observableArrayList(SciaXMLUtils.getItensTipo(this.principal.tiposVigasPrincipaisVA18)));
			}
	}
	
	private void carregarVigasSecundarias(){
		if (this.principal.tiposVigasSecundariasHT20!=null && 
			this.principal.tiposVigasSecundariasHT20.size()>0 && 
			this.principal.tiposVigasSecundarias.getSelectionModel().getSelectedItem() != null &&
			this.principal.tiposVigasSecundarias.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_VIGA_HT20)){
			
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposVigasSecundariasHT20);		
			this.principal.vigasSecundarias.setItems(list);
			this.principal.sumarioVigasSecundarias.setItems(FXCollections.observableArrayList(SciaXMLUtils.getItensTipo(this.principal.tiposVigasSecundariasHT20)));
		}else
		if (this.principal.tiposVigasSecundariasV18!=null && 
			this.principal.tiposVigasSecundariasV18.size()>0 && 
			this.principal.tiposVigasSecundarias.getSelectionModel().getSelectedItem() != null &&
			this.principal.tiposVigasSecundarias.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_VIGA_V18)){
			
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposVigasSecundariasV18);	
			this.principal.vigasSecundarias.setItems(list);
			this.principal.sumarioVigasSecundarias.setItems(FXCollections.observableArrayList(SciaXMLUtils.getItensTipo(this.principal.tiposVigasSecundariasV18)));
		}else
		if (this.principal.tiposVigasSecundariasV75!=null && 
			this.principal.tiposVigasSecundariasV75.size()>0 && 
			this.principal.tiposVigasSecundarias.getSelectionModel().getSelectedItem() != null &&
			this.principal.tiposVigasSecundarias.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_VIGA_V75)){
			
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposVigasSecundariasV75);	
			this.principal.vigasSecundarias.setItems(list);
			this.principal.sumarioVigasSecundarias.setItems(FXCollections.observableArrayList(SciaXMLUtils.getItensTipo(this.principal.tiposVigasSecundariasV75)));
		}else
		if (this.principal.tiposVigasSecundariasVA18!=null && 
			this.principal.tiposVigasSecundariasVA18.size()>0 && 
			this.principal.tiposVigasSecundarias.getSelectionModel().getSelectedItem() != null &&
			this.principal.tiposVigasSecundarias.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_VIGA_VA18)){
			
			ObservableList<Tipo> list = FXCollections.observableArrayList(this.principal.tiposVigasSecundariasVA18);	
			this.principal.vigasSecundarias.setItems(list);
			this.principal.sumarioVigasSecundarias.setItems(FXCollections.observableArrayList(SciaXMLUtils.getItensTipo(this.principal.tiposVigasSecundariasVA18)));
		}
	}
	
	private void inicializarVigasPrincipais(){
		if (RepositorioPecas.pecas != null){
			Set<String> pecas = RepositorioPecas.pecas.keySet();
			
			// Iniciando o load de vigas HT20
			this.principal.tiposVigasPrincipaisHT20 = new ArrayList<Tipo>();
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.VM20))
				{
					Tipo t = new TipoViga(peca);
					t.setSumarioReference(this.principal.sumarioVigasPrincipais);
					this.principal.tiposVigasPrincipaisHT20.add(t);
				}
			}
			if (this.principal.tiposVigasPrincipaisHT20.size() > 0){
				this.principal.tiposVigasPrincipais.getItems().add(SciaXMLContantes.TIPO_VIGA_HT20);
				this.principal.tipoVigaConsole.getItems().add(SciaXMLContantes.TIPO_VIGA_HT20);
			} 
			
			// Iniciando o load de vigas V75
			this.principal.tiposVigasPrincipaisV75 = new ArrayList<Tipo>();
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.VI07))
				{
					Tipo t = new TipoViga(peca);
					t.setSumarioReference(this.principal.sumarioVigasPrincipais);
					this.principal.tiposVigasPrincipaisV75.add(t);
				}
			}
			if (this.principal.tiposVigasPrincipaisV75.size() > 0){
				this.principal.tiposVigasPrincipais.getItems().add(SciaXMLContantes.TIPO_VIGA_V75);
				this.principal.tipoVigaConsole.getItems().add(SciaXMLContantes.TIPO_VIGA_V75);
			} 
			
			// Iniciando o load de vigas V18
			this.principal.tiposVigasPrincipaisV18 = new ArrayList<Tipo>();
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.VI18))
				{
					Tipo t = new TipoViga(peca);
					t.setSumarioReference(this.principal.sumarioVigasPrincipais);
					this.principal.tiposVigasPrincipaisV18.add(t);
				}
			}
			if (this.principal.tiposVigasPrincipaisV18.size() > 0){
				this.principal.tiposVigasPrincipais.getItems().add(SciaXMLContantes.TIPO_VIGA_V18);
				this.principal.tipoVigaConsole.getItems().add(SciaXMLContantes.TIPO_VIGA_V18);
			} 
			
			// Iniciando o load de vigas VA18
			this.principal.tiposVigasPrincipaisVA18 = new ArrayList<Tipo>();
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.VA18))
				{
					TipoViga t = new TipoViga( peca);
					t.setSumarioReference(this.principal.sumarioVigasPrincipais);
					this.principal.tiposVigasPrincipaisVA18.add(t);
				}
			}
			if (this.principal.tiposVigasPrincipaisVA18.size() > 0){
				this.principal.tiposVigasPrincipais.getItems().add(SciaXMLContantes.TIPO_VIGA_VA18);
				this.principal.tipoVigaConsole.getItems().add(SciaXMLContantes.TIPO_VIGA_VA18);
			} 
			 
		}
	}
	
	private void inicializarVigasSecundarias(){
		if (RepositorioPecas.pecas != null){
			Set<String> pecas = RepositorioPecas.pecas.keySet();
			
			// Iniciando o load de vigas HT20
			this.principal.tiposVigasSecundariasHT20 = new ArrayList<Tipo>();
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.VM20))
				{
					TipoViga t = new TipoViga(peca);
					t.setSumarioReference(this.principal.sumarioVigasSecundarias);
					this.principal.tiposVigasSecundariasHT20.add(t);
				}
			}
			if (this.principal.tiposVigasSecundariasHT20.size() > 0){
				this.principal.tiposVigasSecundarias.getItems().add(SciaXMLContantes.TIPO_VIGA_HT20);
			} 
			
			// Iniciando o load de vigas V75
			this.principal.tiposVigasSecundariasV75 = new ArrayList<Tipo>();
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.VI07))
				{
					TipoViga t = new TipoViga(peca);
					t.setSumarioReference(this.principal.sumarioVigasSecundarias);
					this.principal.tiposVigasSecundariasV75.add(t);
				}
			}
			if (this.principal.tiposVigasSecundariasV75.size() > 0){
				this.principal.tiposVigasSecundarias.getItems().add(SciaXMLContantes.TIPO_VIGA_V75);
			} 
			
			// Iniciando o load de vigas V18
			this.principal.tiposVigasSecundariasV18 = new ArrayList<Tipo>();
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.VI18))
				{
					TipoViga t = new TipoViga(peca);
					t.setSumarioReference(this.principal.sumarioVigasSecundarias);
					this.principal.tiposVigasSecundariasV18.add(t);
				}
			}
			if (this.principal.tiposVigasSecundariasV18.size() > 0){
				this.principal.tiposVigasSecundarias.getItems().add(SciaXMLContantes.TIPO_VIGA_V18);
			} 
			
			// Iniciando o load de vigas VA18
			this.principal.tiposVigasSecundariasVA18 = new ArrayList<Tipo>();
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.VA18))
				{
					TipoViga t = new TipoViga(peca);
					t.setSumarioReference(this.principal.sumarioVigasSecundarias);
					this.principal.tiposVigasSecundariasVA18.add(t);
				}
			}
			if (this.principal.tiposVigasSecundariasVA18.size() > 0){
				this.principal.tiposVigasSecundarias.getItems().add(SciaXMLContantes.TIPO_VIGA_VA18);
			} 
			 
		}
	}
}
