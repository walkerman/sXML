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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.swing.JOptionPane;

import br.com.scia.xml.entity.exception.RepositorioPecasException;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.view.TipoTravessa;
import br.com.scia.xml.entity.xml.Project;
import br.com.scia.xml.model.Calculo;
import br.com.scia.xml.model.RepositorioPecas;
import br.com.scia.xml.util.SciaXMLContantes;
import br.com.scia.xml.view.SciaXMLStarter;

/**
 *
 * @author luy
 */
public class PrincipalController implements Initializable{
	
	public static final String fxml = "SciaXML.fxml";
	private SciaXMLStarter starter;
	
	public void loadPage(){
		try{
			AnchorPane page = (AnchorPane) FXMLLoader.load(SciaXMLStarter.class.getResource(fxml));
	        
	        Scene scene = new Scene(page);
	        starter.getStage().setScene(scene);
	        starter.getStage().setTitle("SciaXML");
	        starter.getStage().setResizable(false);
	        starter.getStage().show();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setStarter(SciaXMLStarter starter) {
		this.starter = starter;
	}
	
	@FXML
	TitledPane abaDimensoesVigas;
	@FXML
	ToggleGroup tipos;
	@FXML
	TextField medidaX;
	@FXML
	TextField medidaY;
	@FXML
	TextField espessura;
	@FXML
	TextField folgaX1;
	@FXML
	TextField folgaX2;
	@FXML
	TextField folgaY1;
	@FXML
	TextField folgaY2;
	@FXML
	ComboBox<String> tiposTravessasX;
	@FXML
	ComboBox<TipoTravessa> tamanhosTravessasX;
	@FXML
	TableView<TipoTravessa> travessasX;
	@FXML
	TextField quantidadeTravessasX;
	@FXML
	ComboBox<String> tiposTravessasY;
	@FXML
	ComboBox<TipoTravessa> tamanhosTravessasY;
	@FXML
	TableView<TipoTravessa> travessasY;
	@FXML
	TextField quantidadeTravessasY;
	@FXML
	ComboBox<String> tiposVigasPrincipais;
	@FXML
	ListView<String> vigasPrincipais;
	@FXML
	ComboBox<String> tiposVigasSecundarias;
	@FXML
	ComboBox<String> vigasSecundariasUsarDe;
	@FXML
	ListView<String> vigasSecundarias;
	@FXML
	TextField composicao;
	@FXML
	TextField sumarioTipo;
	@FXML
	TextField sumarioMedidaX;
	@FXML
	TextField sumarioMedidaY;
	@FXML
	TextField sumarioEspessura;
	@FXML
	TextField sumarioFolgaX1;
	@FXML
	TextField sumarioFolgaX2;
	@FXML
	TextField sumarioFolgaY1;
	@FXML
	TextField sumarioFolgaY2;
	@FXML
	ComboBox<String> sumarioTravessasX;
	@FXML
	ComboBox<String> sumarioTravessasY;
	@FXML
	ComboBox<String> sumarioVigasPrincipais;
	@FXML
	ComboBox<String> sumarioVigasSecundarias;
	@FXML
	TextField sumarioComposicao;	
	@FXML
	TextField totalPecas;
	
	ArrayList<TipoTravessa> tiposTravessas;	
	
	@FXML
	public void importarArquivos(ActionEvent e){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Files", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        List<File> files = fileChooser.showOpenMultipleDialog(null);
        
        if (files != null && files.size() > 0){
        	try {
				RepositorioPecas.addPecas(files);
				
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
		inicializarTravessas();
	}
	
	private void inicializarTravessas(){
		if (RepositorioPecas.pecas != null){
			Set<String> pecas = RepositorioPecas.pecas.keySet();
			
			this.tiposTravessas = new ArrayList<TipoTravessa>();
			
			int ordem = 1;
			for (String peca : pecas) {
				if (peca.startsWith(SciaXMLContantes.KITRV))
				{
					TipoTravessa t = new TipoTravessa(String.valueOf(ordem), peca);
					this.tiposTravessas.add(t);
				}
				ordem++;	
			}
			
			if (this.tiposTravessas.size() > 0){
				this.tiposTravessasX.getItems().add("Travessa");
				this.tiposTravessasY.getItems().add("Travessa");
			} 
			 
		}
		
	}
	
	@FXML
	public void carregarTamanhoTravessasX(){
		if (this.tiposTravessas!=null && this.tiposTravessas.size()>0){
			ObservableList<TipoTravessa> list = FXCollections.observableArrayList(this.tiposTravessas);				 
			this.tamanhosTravessasX.setItems(list);
		}
	}
	
	@FXML
	public void carregarTamanhoTravessasY(){
		if (this.tiposTravessas!=null && this.tiposTravessas.size()>0){
			ObservableList<TipoTravessa> list = FXCollections.observableArrayList(this.tiposTravessas);				 
			this.tamanhosTravessasY.setItems(list);
		}
	}
	
	@FXML
	public void adicionarTravessaX(){
		TipoTravessa selecao = this.tamanhosTravessasX.getSelectionModel().getSelectedItem();
		TipoTravessa t = new TipoTravessa(""+(this.travessasX.getItems().size()+1), selecao.getItem());
		t.setTableReference(this.travessasX);
		t.setSumarioReference(this.sumarioTravessasX);
		t.setQuantidadeReference(this.quantidadeTravessasX);
		
		if (t != null){
			this.travessasX.getItems().add(t);
			this.sumarioTravessasX.getItems().add(t.getItem());
			this.quantidadeTravessasX.setText(String.valueOf(this.travessasX.getItems().size()));
		}
	}
	
	@FXML
	public void adicionarTravessaY(){
		TipoTravessa selecao = this.tamanhosTravessasY.getSelectionModel().getSelectedItem();
		TipoTravessa t = new TipoTravessa(String.valueOf(this.travessasY.getItems().size()+1), selecao.getItem());
		t.setTableReference(this.travessasY);
		t.setSumarioReference(this.sumarioTravessasY);
		t.setQuantidadeReference(this.quantidadeTravessasY);
		
		if (t != null){
			this.travessasY.getItems().add(t);
			this.sumarioTravessasY.getItems().add(t.getItem());
			this.quantidadeTravessasY.setText(String.valueOf(this.travessasY.getItems().size()));
		}
	}
	
	@FXML
	public void limparDadosTravessasX(){
		this.travessasX.getItems().removeAll(this.travessasX.getItems());
		this.quantidadeTravessasX.setText(String.valueOf(this.travessasX.getItems().size()));
		this.sumarioTravessasX.getItems().removeAll(this.sumarioTravessasX.getItems());
	}
	
	@FXML
	public void limparDadosTravessasY(){
		this.travessasY.getItems().removeAll(this.travessasY.getItems());
		this.quantidadeTravessasY.setText(String.valueOf(this.travessasY.getItems().size()));
		this.sumarioTravessasY.getItems().removeAll(this.sumarioTravessasY.getItems());
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
	public void gerarXML(ActionEvent e){		
		if (!"".equals(this.totalPecas.getText())){
			popularSumarioDados();
			
			SumarioDados s = popularSumarioDados();
			
			System.out.println("Entrada:\n");
			s.toString();
			
			try {
				s = Calculo.calculaEstrutura(s);
				
				System.out.println("Saída:\n");
				s.toString();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			construirProject(s);			
		}	
		else{
			JOptionPane.showMessageDialog(null, "Nenhum arquivo de peça carregado no sistema.");
		}
			
	}
	
	private void construirProject(SumarioDados s) {

		// O método mais zica		
		
	}

	private SumarioDados popularSumarioDados(){
		
		SumarioDados retorno = new SumarioDados();		
		
		try{
			String tipoEquipamento = this.sumarioTipo.getText();
			String totalPecas = this.totalPecas.getText();
			String espessura   = this.sumarioEspessura.getText();
			String medidaLageX = this.sumarioMedidaX.getText();
			String medidaLageY = this.sumarioMedidaY.getText();
			String folgaLajeX1 = this.sumarioFolgaX1.getText();
			String folgaLajeX2 = this.sumarioFolgaX2.getText();
			String folgaLajeY1 = this.sumarioFolgaY1.getText();
			String folgaLajeY2 = this.sumarioFolgaY2.getText();
			
			List<String> travessasX = this.sumarioTravessasX.getItems();
			List<Peca> pecasX = new ArrayList<Peca>();
			for (String tipo : travessasX) {
				Peca peca = new Peca();
				Project project = RepositorioPecas.pecas.get(tipo);
				peca.setTipoEquipamento(tipo);
				peca.setComprimentoPecaX(120.0);
				peca.setComprimentoPecaY(170.0);
				pecasX.add(peca);
			}		
			
			List<String> travessasY = this.sumarioTravessasY.getItems();
			List<Peca> pecasY = new ArrayList<Peca>();
			for (String tipo : travessasY) {
				Peca peca = new Peca();
				Project project = RepositorioPecas.pecas.get(tipo);
				peca.setTipoEquipamento(tipo);
				peca.setComprimentoPecaX(120.0);
				peca.setComprimentoPecaY(170.0);
				pecasY.add(peca);
			}
			
			String kidI = null;
			String kidH = null;
			List<String> vigasPrincipais = null;
			List<String> vigasSecundarias = null;
			String composicaoTorres = null;
			
			retorno.setTotalPecas(totalPecas);
			retorno.setTipoEquipamento(tipoEquipamento);
			retorno.setMedidaLageX(medidaLageX);
			retorno.setMedidaLageY(medidaLageY);
			retorno.setEspessura(espessura);
			retorno.setFolgaLajeX1(folgaLajeX1);
			retorno.setFolgaLajeX2(folgaLajeX2);
			retorno.setFolgaLajeY1(folgaLajeY1);
			retorno.setFolgaLajeY2(folgaLajeY2);
			retorno.setPecasX(pecasX);
			retorno.setPecasY(pecasY);
			retorno.setKidH(kidH);
			retorno.setKidI(kidI);
			retorno.setVigasPrincipais(vigasPrincipais);
			retorno.setVigasSecundarias(vigasSecundarias);
			retorno.setComposicaoTorres(composicaoTorres);
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problemas durante a conversão dos dados. " +
					"Por favor, verifique os dados informados", "SciaXML",JOptionPane.ERROR_MESSAGE);
		}
		
		return retorno;
	}
	
	@FXML
	public void habilitarViga(ActionEvent e){
		String selecao = this.vigasPrincipais.getSelectionModel().getSelectedItem();
		JOptionPane.showMessageDialog(null, "Habilitar a viga: " + selecao + "?");
	}
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	// Método deverá ser utilizado para carregar todos os componentes em tela
    	// Caso o load dos arquivos seja feito automaticamente e/ou previamente o load da tela principal
    }
    
}
