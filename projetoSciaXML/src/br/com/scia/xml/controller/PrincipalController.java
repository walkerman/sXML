/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scia.xml.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.dao.RepositorioProjeto;
import br.com.scia.xml.entity.exception.RepositorioPecasException;
import br.com.scia.xml.entity.exception.SciaXMLFileManagerException;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.view.TipoPecaXY;
import br.com.scia.xml.entity.view.TipoViga;
import br.com.scia.xml.model.Calculo;
import br.com.scia.xml.util.SciaXMLContantes;
import br.com.scia.xml.util.SciaXMLFileManager;
import br.com.scia.xml.util.SciaXMLUtils;
import br.com.scia.xml.view.SciaXMLStarter;

/**
 *
 * @author louis
 */
public class PrincipalController implements Initializable{
		
	public static final String fxml = "SciaXML.fxml";
	public static Stage stage;
	
	@FXML
	public TitledPane abaDimensoesVigas; 
	@FXML
	public ToggleGroup tipos;
	@FXML
	public TextField coordenadaX;
	@FXML
	public TextField coordenadaY;
	@FXML
	public TextField coordenadaZ;
	@FXML
	public TextField medidaX;
	@FXML
	public TextField medidaY;
	@FXML
	public TextField espessura;
	@FXML
	public TextField folgaX1;
	@FXML
	public TextField folgaX2;
	@FXML
	public TextField folgaY1;
	@FXML
	public TextField folgaY2;
	@FXML
	public ComboBox<String> tiposPecasX;
	@FXML
	public ComboBox<TipoPecaXY> tamanhosPecasX;
	@FXML
	public TableView<TipoPecaXY> pecasX;
	@FXML
	public TextField quantidadePecasX;
	@FXML
	public AnchorPane areaCruzetaX;
	@FXML
	public TextField alturaVigaEsquerdaX;
	@FXML
	public TextField alturaVigaDireitaX;
	@FXML
	public ToggleGroup tipoCruzetaX;
	@FXML
	public AnchorPane areaEscoraX;
	@FXML
	public ComboBox<String> tipoForcadoX;
	@FXML
	public CheckBox tripeX;
	@FXML
	public AnchorPane areaCruzetaY;
	@FXML
	public TextField alturaVigaEsquerdaY;
	@FXML
	public TextField alturaVigaDireitaY;
	@FXML
	public ToggleGroup tipoCruzetaY;
	@FXML
	public AnchorPane areaEscoraY;
	@FXML
	public ComboBox<String> tipoForcadoY;
	@FXML
	public CheckBox tripeY;
	@FXML
	public ComboBox<String> tiposPecasY;
	@FXML
	public ComboBox<TipoPecaXY> tamanhosPecasY;
	@FXML
	public TableView<TipoPecaXY> pecasY;
	@FXML
	public TextField quantidadePecasY;
	@FXML
	public ToggleGroup posicaoConsole;
	@FXML
	public CheckBox alterarTipoViga;
	@FXML
	public ComboBox<String> tipoVigaConsole;
	@FXML
	public ComboBox<String> tiposVigasPrincipais;
	@FXML
	public TableView<TipoViga> vigasPrincipais;
	@FXML
	public TextField transpassePrincipais;
	@FXML
	public ComboBox<String> tiposVigasSecundarias;
	@FXML
	public ComboBox<String> vigasSecundariasUsarDe;
	@FXML
	public TableView<TipoViga> vigasSecundarias;
	@FXML
	public AnchorPane areaVigasSecundarias;
	@FXML
	public TextField transpasseSecundarias;
	@FXML
	public TextField composicao;
	@FXML
	public TextField peDireito;
	@FXML
	public ComboBox<String> macacos;
	@FXML
	public ComboBox<String> forcados;
	@FXML
	public ComboBox<String> postes;
	@FXML
	public TextField sumarioTipo;
	@FXML
	public TextField sumarioCoordenadaX;
	@FXML
	public TextField sumarioCoordenadaY;
	@FXML
	public TextField sumarioCoordenadaZ;
	@FXML
	public TextField sumarioMedidaX;
	@FXML
	public TextField sumarioMedidaY;
	@FXML
	public TextField sumarioEspessura;
	@FXML
	public TextField sumarioFolgaX1;
	@FXML
	public TextField sumarioFolgaX2;
	@FXML
	public TextField sumarioFolgaY1;
	@FXML
	public TextField sumarioFolgaY2;
	@FXML
	public ComboBox<String> sumarioTravessasX;
	@FXML
	public ComboBox<String> sumarioTravessasY;
	@FXML
	public ComboBox<String> sumarioVigasPrincipais;
	@FXML
	public ComboBox<String> sumarioVigasSecundarias;
	@FXML
	public TextField sumarioComposicao;	
	@FXML
	public TextField totalPecas;
	@FXML
	public TextField diretorioPecas;
	@FXML
	public SplitPane split;
	
	ArrayList<TipoPecaXY> tiposTravessas;
	ArrayList<TipoPecaXY> tiposCruzetas;
	ArrayList<TipoPecaXY> tiposEscoras;
	ArrayList<TipoViga>   tiposVigasHT20;
	ArrayList<TipoViga>   tiposVigasV75;
	ArrayList<TipoViga>   tiposVigasV18;
	ArrayList<TipoViga>   tiposVigasVA18;
	
	public static void loadPage(){
		try{			
			AnchorPane page = (AnchorPane) FXMLLoader.load(SciaXMLStarter.class.getResource(fxml));
	        
	        Scene scene = new Scene(page);
	        PrincipalController.stage.setScene(scene);
	        PrincipalController.stage.setTitle("SciaXML - " + RepositorioProjeto.projeto.getNomeArquivo());
	        PrincipalController.stage.setResizable(false);
	        PrincipalController.stage.show();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void importarArquivos(ActionEvent e){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Files", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        List<File> files = fileChooser.showOpenMultipleDialog(null);
        
        if (files != null && files.size() > 0){
        	try {        	
        		
        		for (File file : files) {
					RepositorioPecas.addPeca(file);
				}
				
				JOptionPane.showMessageDialog(null, "Peças carregadas com sucesso.");
				
			} catch (RepositorioPecasException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
        	
        	this.totalPecas.setText(RepositorioPecas.pecas.size()+"");
        	
        	inicializarComponentes();
        }
        
	}
	
	private void inicializarComponentes(){
		inicializarPecasXY();
		inicializarVigas();
	}
		
	private void inicializarVigas(){
		if (RepositorioPecas.pecas != null){
			Set<String> pecas = RepositorioPecas.pecas.keySet();
			
			// Iniciando o load de vigas HT20
			this.tiposVigasHT20 = new ArrayList<TipoViga>();
			int ordem = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.VM20))
				{
					TipoViga t = new TipoViga(String.valueOf(ordem), peca);
					RepositorioPecas.vigas.put(t.getItem(), t);
					this.tiposVigasHT20.add(t);
				}
				ordem++;	
			}
			if (this.tiposVigasHT20.size() > 0){
				this.tiposVigasPrincipais.getItems().add(SciaXMLContantes.TIPO_VIGA_HT20);
				this.tiposVigasSecundarias.getItems().add(SciaXMLContantes.TIPO_VIGA_HT20);
			} 
			
			// Iniciando o load de vigas V75
			this.tiposVigasV75 = new ArrayList<TipoViga>();
			ordem = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.VI07))
				{
					TipoViga t = new TipoViga(String.valueOf(ordem), peca);
					RepositorioPecas.vigas.put(t.getItem(), t);
					this.tiposVigasV75.add(t);
				}
				ordem++;	
			}
			if (this.tiposVigasV75.size() > 0){
				this.tiposVigasPrincipais.getItems().add(SciaXMLContantes.TIPO_VIGA_V75);
				this.tiposVigasSecundarias.getItems().add(SciaXMLContantes.TIPO_VIGA_V75);
			} 
			
			// Iniciando o load de vigas V18
			this.tiposVigasV18 = new ArrayList<TipoViga>();
			ordem = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.VI18))
				{
					TipoViga t = new TipoViga(String.valueOf(ordem), peca);
					RepositorioPecas.vigas.put(t.getItem(), t);
					this.tiposVigasV18.add(t);
				}
				ordem++;	
			}
			if (this.tiposVigasV18.size() > 0){
				this.tiposVigasPrincipais.getItems().add(SciaXMLContantes.TIPO_VIGA_V18);
				this.tiposVigasSecundarias.getItems().add(SciaXMLContantes.TIPO_VIGA_V18);
			} 
			
			// Iniciando o load de vigas VA18
			this.tiposVigasVA18 = new ArrayList<TipoViga>();
			ordem = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.VA18))
				{
					TipoViga t = new TipoViga(String.valueOf(ordem), peca);
					RepositorioPecas.vigas.put(t.getItem(), t);
					this.tiposVigasVA18.add(t);
				}
				ordem++;	
			}
			if (this.tiposVigasVA18.size() > 0){
				this.tiposVigasPrincipais.getItems().add(SciaXMLContantes.TIPO_VIGA_VA18);
				this.tiposVigasSecundarias.getItems().add(SciaXMLContantes.TIPO_VIGA_VA18);
			} 
			 
		}
	}
	
	private void inicializarPecasXY(){
		if (RepositorioPecas.pecas != null){
			Set<String> pecas = RepositorioPecas.pecas.keySet();
			
			// Iniciando o load de travessas
			this.tiposTravessas = new ArrayList<TipoPecaXY>();
			int ordem = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.KITRV))
				{
					TipoPecaXY t = new TipoPecaXY(String.valueOf(ordem), peca);
					RepositorioPecas.tiposPecasXY.put(t.getItem(), t);
					this.tiposTravessas.add(t);
				}
				ordem++;	
			}
			if (this.tiposTravessas.size() > 0){
				this.tiposPecasX.getItems().add(SciaXMLContantes.TIPO_TRAVESSA);
				this.tiposPecasY.getItems().add(SciaXMLContantes.TIPO_TRAVESSA);
			} 
			
			// Iniciando o load de cruzetas
			this.tiposCruzetas = new ArrayList<TipoPecaXY>();
			ordem = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.CRU))
				{
					TipoPecaXY t = new TipoPecaXY(String.valueOf(ordem), peca);
					RepositorioPecas.tiposPecasXY.put(t.getItem(), t);
					this.tiposCruzetas.add(t);
				}
				ordem++;	
			}
			if (this.tiposCruzetas.size() > 0){
				this.tiposPecasX.getItems().add(SciaXMLContantes.TIPO_CRUZETA);
				this.tiposPecasY.getItems().add(SciaXMLContantes.TIPO_CRUZETA);
			} 
			
			// Iniciando o load de escoras
			this.tiposEscoras = new ArrayList<TipoPecaXY>();
			ordem = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.ESC))
				{
					TipoPecaXY t = new TipoPecaXY(String.valueOf(ordem), peca);
					RepositorioPecas.tiposPecasXY.put(t.getItem(), t);
					this.tiposEscoras.add(t);
				}
				ordem++;	
			}
			if (this.tiposEscoras.size() > 0){
				this.tiposPecasX.getItems().add(SciaXMLContantes.TIPO_ESCORA);
				this.tiposPecasY.getItems().add(SciaXMLContantes.TIPO_ESCORA);
			} 
			 
		}
		
	}
	
	@FXML
	public void carregarTamanhoPecasX(){
		if (this.tiposTravessas!=null && this.tiposTravessas.size()>0 && this.tiposPecasX.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_TRAVESSA)){
			ObservableList<TipoPecaXY> list = FXCollections.observableArrayList(this.tiposTravessas);				 
			this.tamanhosPecasX.setItems(list);
		}
		
		if (this.tiposCruzetas!=null && this.tiposCruzetas.size()>0 && this.tiposPecasX.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_CRUZETA)){
			ObservableList<TipoPecaXY> list = FXCollections.observableArrayList(this.tiposCruzetas);				 
			this.tamanhosPecasX.setItems(list);
		}
		
		if (this.tiposEscoras!=null && this.tiposEscoras.size()>0 && this.tiposPecasX.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_ESCORA)){
			ObservableList<TipoPecaXY> list = FXCollections.observableArrayList(this.tiposEscoras);				 
			this.tamanhosPecasX.setItems(list);
		}
	}
	
	@FXML
	public void carregarTamanhoPecasY(){
		if (this.tiposTravessas!=null && this.tiposTravessas.size()>0 && this.tiposPecasY.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_TRAVESSA)){
			ObservableList<TipoPecaXY> list = FXCollections.observableArrayList(this.tiposTravessas);				 
			this.tamanhosPecasY.setItems(list);
		}
		
		if (this.tiposCruzetas!=null && this.tiposCruzetas.size()>0 && this.tiposPecasY.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_CRUZETA)){
			ObservableList<TipoPecaXY> list = FXCollections.observableArrayList(this.tiposCruzetas);				 
			this.tamanhosPecasY.setItems(list);
		}
		
		if (this.tiposEscoras!=null && this.tiposEscoras.size()>0 && this.tiposPecasY.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_ESCORA)){
			ObservableList<TipoPecaXY> list = FXCollections.observableArrayList(this.tiposEscoras);				 
			this.tamanhosPecasY.setItems(list);
		}
	}
	
	@FXML
	public void carregarVigasPrincipais(){
		if (this.tiposVigasHT20!=null && this.tiposVigasHT20.size()>0 && this.tiposVigasPrincipais.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_VIGA_HT20)){
			ObservableList<TipoViga> list = FXCollections.observableArrayList(this.tiposVigasHT20);		
			this.vigasPrincipais.setItems(list);
			
		}
		if (this.tiposVigasV18!=null && this.tiposVigasV18.size()>0 && this.tiposVigasPrincipais.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_VIGA_V18)){
			ObservableList<TipoViga> list = FXCollections.observableArrayList(this.tiposVigasV18);	
			this.vigasPrincipais.setItems(list);
		}
		if (this.tiposVigasV75!=null && this.tiposVigasV75.size()>0 && this.tiposVigasPrincipais.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_VIGA_V75)){
			ObservableList<TipoViga> list = FXCollections.observableArrayList(this.tiposVigasV75);	
			this.vigasPrincipais.setItems(list);
		}
		if (this.tiposVigasVA18!=null && this.tiposVigasVA18.size()>0 && this.tiposVigasPrincipais.getSelectionModel().getSelectedItem().equals(SciaXMLContantes.TIPO_VIGA_VA18)){
			ObservableList<TipoViga> list = FXCollections.observableArrayList(this.tiposVigasVA18);	
			this.vigasPrincipais.setItems(list);
		}
	}
	
	@FXML
	public void carregarVigasSecundarias(){}
	
	private Boolean validarRegrasPecasXY(TipoPecaXY tipo, String origem) {
		
		if (tipo == null || "".equals(origem) || tipo.getItem() == null)
			JOptionPane.showMessageDialog(null, "Favor informar o tamanho da peça");
		
		if (tipo != null && tipo.getItem() != null && tipo.getItem().startsWith(SciaXMLContantes.KITRV)){
			return true;
		}else{			
			if (SciaXMLContantes.ORIGEM_X.equals(origem)){
				if (tipo.getItem().startsWith(SciaXMLContantes.ESC) && this.pecasX.getItems().size() == 0){
					adicionarPecaY(tipo.getItem());
					this.areaEscoraX.setDisable(false);
					this.areaEscoraY.setDisable(false);
					return true;
				}
				if (tipo.getItem().startsWith(SciaXMLContantes.ESC)){
					this.areaEscoraY.setDisable(false);
					this.areaEscoraX.setDisable(false);
					return true;
				}
				if (tipo.getItem().startsWith(SciaXMLContantes.CRU) && SciaXMLContantes.ORIGEM_X.equals(origem)){
					if (!validarQuantidadeCruzetas(this.pecasY.getItems())){
						adicionarPecaY("-");
						this.areaCruzetaX.setDisable(false);
						this.areaCruzetaY.setDisable(false);
						return true;
					}else{
						JOptionPane.showMessageDialog(null, "Eixo Y já possui cruzetas.");
					}
						
				}
			}else if (SciaXMLContantes.ORIGEM_Y.equals(origem)){
				if (tipo.getItem().startsWith(SciaXMLContantes.ESC) && this.pecasY.getItems().size() == 0){
					adicionarPecaX(tipo.getItem());
					this.areaEscoraY.setDisable(false);
					this.areaEscoraX.setDisable(false);
					return true;
				}
				if (tipo.getItem().startsWith(SciaXMLContantes.ESC)){
					this.areaEscoraY.setDisable(false);
					this.areaEscoraX.setDisable(false);
					return true;
				}
				if (tipo.getItem().startsWith(SciaXMLContantes.CRU) && SciaXMLContantes.ORIGEM_Y.equals(origem)){
					if (!validarQuantidadeCruzetas(this.pecasX.getItems())){
						adicionarPecaX("-");
						this.areaCruzetaX.setDisable(false);
						this.areaCruzetaY.setDisable(false);
						return true;
					}else{
						JOptionPane.showMessageDialog(null, "Eixo X já possui cruzetas.");
					}
				}
			}
		}
		
		return false;
	}	
	
	private Boolean validarQuantidadeCruzetas(List<TipoPecaXY> pecas){
		Boolean retorno = false;
		for (TipoPecaXY tipoPecaXY : pecas) {
			if (tipoPecaXY.getItem().startsWith(SciaXMLContantes.CRU)){
				retorno = true;				
				break;
			}
		}
		return retorno;
	}
	
	@FXML
	public void adicionarPecaX(){
		TipoPecaXY selecao = this.tamanhosPecasX.getSelectionModel().getSelectedItem();
		if (validarRegrasPecasXY(selecao, SciaXMLContantes.ORIGEM_X))
			adicionarPecaX(selecao.getItem());
	}
	
	public void adicionarPecaX(String item){
		TipoPecaXY t = new TipoPecaXY(""+(this.pecasX.getItems().size()+1), item);
		t.setTableReference(this.pecasX);
		t.setSumarioReference(this.sumarioTravessasX);
		t.setQuantidadeReference(this.quantidadePecasX);
		
		if (t != null){
			this.pecasX.getItems().add(t);
			this.sumarioTravessasX.getItems().add(t.getItem());
			this.quantidadePecasX.setText(String.valueOf(this.pecasX.getItems().size()));
		}
	}
	
	@FXML
	public void adicionarPecaY(){
		TipoPecaXY selecao = this.tamanhosPecasY.getSelectionModel().getSelectedItem();
		if (validarRegrasPecasXY(selecao, SciaXMLContantes.ORIGEM_Y))
			adicionarPecaY(selecao.getItem());
	}
	
	public void adicionarPecaY(String item){
		TipoPecaXY t = new TipoPecaXY(String.valueOf(this.pecasY.getItems().size()+1), item);
		t.setTableReference(this.pecasY);
		t.setSumarioReference(this.sumarioTravessasY);
		t.setQuantidadeReference(this.quantidadePecasY);
		
		if (t != null){
			this.pecasY.getItems().add(t);
			this.sumarioTravessasY.getItems().add(t.getItem());
			this.quantidadePecasY.setText(String.valueOf(this.pecasY.getItems().size()));
		}
	}
	
	@FXML
	public void limparDadosPecasX(){
		this.pecasX.getItems().removeAll(this.pecasX.getItems());
		this.quantidadePecasX.setText(String.valueOf(this.pecasX.getItems().size()));
		this.sumarioTravessasX.getItems().removeAll(this.sumarioTravessasX.getItems());
		this.areaCruzetaX.setDisable(true);
		this.areaEscoraX.setDisable(true);
	}
	
	@FXML
	public void limparDadosPecasY(){
		this.pecasY.getItems().removeAll(this.pecasY.getItems());
		this.quantidadePecasY.setText(String.valueOf(this.pecasY.getItems().size()));
		this.sumarioTravessasY.getItems().removeAll(this.sumarioTravessasY.getItems());
		this.areaCruzetaY.setDisable(true);
		this.areaEscoraY.setDisable(true);
	}
	
	@FXML
	public void escolherTipoEquipamento(){
		RadioButton t = (RadioButton) this.tipos.getSelectedToggle();
		this.sumarioTipo.setText(t.getText());
		
		if (t.getId().equals(SciaXMLContantes.KIBLOC_LAJE)){
			this.abaDimensoesVigas.setDisable(true);
		}
		else
			this.abaDimensoesVigas.setDisable(false);
	}
	
	@FXML
	public void setSumarioCoordenadaX (){
		this.sumarioCoordenadaX.setText(this.coordenadaX.getText());
	}
	
	@FXML
	public void setSumarioCoordenadaY (){
		this.sumarioCoordenadaY.setText(this.coordenadaY.getText());
	}
	
	@FXML
	public void setSumarioCoordenadaZ() {
		this.sumarioCoordenadaZ.setText(this.coordenadaZ.getText());
	}
	
	@FXML
	public void setSumarioMedidaX (){
		this.sumarioMedidaX.setText(this.medidaX.getText());
	}
	
	@FXML
	public void setSumarioMedidaY (){
		this.sumarioMedidaY.setText(this.medidaY.getText());
	}
	
	@FXML
	public void setSumarioEspessura() {
		this.sumarioEspessura.setText(this.espessura.getText());
	}
	
	@FXML
	public void setSumarioFolgaX1() {
		this.sumarioFolgaX1.setText(this.folgaX1.getText());
	}
	
	@FXML
	public void setSumarioFolgaX2() {
		this.sumarioFolgaX2.setText(this.folgaX2.getText());
	}
	
	@FXML
	public void setSumarioFolgaY1() {
		this.sumarioFolgaY1.setText(this.folgaY1.getText());
	}
	
	@FXML
	public void setSumarioFolgaY2() {
		this.sumarioFolgaY2.setText(this.folgaY2.getText());
	}
		
	@FXML
	public void alterarTipoConsole(){
		if (this.alterarTipoViga.isSelected())
			this.tipoVigaConsole.setDisable(false);
		else
			this.tipoVigaConsole.setDisable(true);
	}
	
	@FXML
	public void gerarXML(ActionEvent e){		
		if (!"".equals(this.totalPecas.getText())){		
			FileChooser fc = new FileChooser();
			fc.setTitle("Informe o nome do arquivo");
			File f = fc.showSaveDialog(null);
			
			SumarioDados sumario = SciaXMLUtils.popularSumarioDados(this);
			
			try {
				// TODO: Revisar cálculo Marquinhos (hoje esta fixo para 3 peças em x e 2 em y)
				sumario = Calculo.calculaEstrutura(sumario);
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			SciaXMLUtils.construirProject(sumario,f);			
			
			JOptionPane.showMessageDialog(null, "Arquivos .xml/.def salvos com sucesso.");
		}	
		else{
			JOptionPane.showMessageDialog(null, "Nenhum arquivo de peça carregado no sistema.");
		}
			
	}
		
	@FXML
	public void usarLajePre(){}
	
	@FXML
	public void salvarProjeto(){
		SciaXMLUtils.popularSumarioDados(this);
		try {
			SciaXMLFileManager.salvarProjeto(new File(RepositorioProjeto.projeto.getNomeArquivo()));
			JOptionPane.showMessageDialog(null,"Projeto salvo com sucesso.");
		} catch (SciaXMLFileManagerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(),SciaXMLContantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@FXML
	public void showCalc(){
		this.split.setDividerPositions(0.5);		
	}
	
	@FXML
	public void hideCalc(){
		this.split.setDividerPositions(1.0);		
	}
	
	@FXML
	public void abrirProjeto(){}
	
	@FXML
	public void sair(){}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (RepositorioProjeto.projeto != null  && RepositorioPecas.pecas.size() > 0 ){
			inicializarComponentes();
			
			this.totalPecas.setText(String.valueOf(RepositorioPecas.pecas.size()));
    		this.diretorioPecas.setText(RepositorioProjeto.projeto.getDiretorioPecas());    		
    		
			List<Toggle> tipos = this.tipos.getToggles();
			for (Toggle toggle : tipos) {
				RadioButton r = (RadioButton) toggle;
				if (r.getText().equals(RepositorioProjeto.projeto.getTipoEquipamento())){
					r.setSelected(true);
					this.sumarioTipo.setText(r.getText());
					break;
				}
			}
			
			this.coordenadaX.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getCoordenadaX()));
    		this.coordenadaY.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getCoordenadaY()));
    		this.coordenadaZ.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getCoordenadaZ()));
			this.medidaX.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getMedidaLageX()));
    		this.medidaY.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getMedidaLageY()));
    		this.folgaX1.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getFolgaLajeX1()));
    		this.folgaX2.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getFolgaLajeX2()));
    		this.folgaY1.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getFolgaLajeY1()));
    		this.folgaY2.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getFolgaLajeY2()));
    		this.espessura.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getEspessura()));
    		
    		this.sumarioCoordenadaX.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getCoordenadaX()));
    		this.sumarioCoordenadaY.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getCoordenadaY()));
    		this.sumarioCoordenadaZ.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getCoordenadaZ()));
    		this.sumarioMedidaX.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getMedidaLageX()));
    		this.sumarioMedidaY.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getMedidaLageY()));
    		this.sumarioFolgaX1.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getFolgaLajeX1()));
    		this.sumarioFolgaX2.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getFolgaLajeX2()));
    		this.sumarioFolgaY1.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getFolgaLajeY1()));
    		this.sumarioFolgaY2.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getFolgaLajeY2()));
    		this.sumarioEspessura.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getEspessura()));
    		
    		List<Peca> pecasX = RepositorioProjeto.projeto.getPecasX();
    		if (pecasX != null && pecasX.size() > 0)
	    		for (Peca peca : pecasX) {
	    			adicionarPecaX(peca.getTipo());
				}
    		
    		List<Peca> pecasY = RepositorioProjeto.projeto.getPecasY();
    		if (pecasY != null && pecasY.size() > 0)
	    		for (Peca peca : pecasY) {
	    			adicionarPecaY(peca.getTipo());
				}    		
    		  		
    	}		
	}
}
