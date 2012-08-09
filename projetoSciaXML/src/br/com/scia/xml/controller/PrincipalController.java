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

import br.com.scia.xml.entity.exception.RepositorioPecasException;
import br.com.scia.xml.entity.exception.SciaXMLFileManagerException;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.view.TipoTravessa;
import br.com.scia.xml.entity.xml.Project;
import br.com.scia.xml.model.Calculo;
import br.com.scia.xml.model.RepositorioPecas;
import br.com.scia.xml.model.RepositorioProjeto;
import br.com.scia.xml.util.SciaXMLContantes;
import br.com.scia.xml.util.SciaXMLFileManager;
import br.com.scia.xml.view.SciaXMLStarter;

/**
 *
 * @author louis
 */
public class PrincipalController implements Initializable{
	
	@FXML
	TitledPane abaDimensoesVigas; 
	@FXML
	ToggleGroup tipos;
	@FXML
	TextField coordenadaX;
	@FXML
	TextField coordenadaY;
	@FXML
	TextField coordenadaZ;
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
	TextField transpassePrincipais;
	@FXML
	ComboBox<String> tiposVigasSecundarias;
	@FXML
	ComboBox<String> vigasSecundariasUsarDe;
	@FXML
	ListView<String> vigasSecundarias;
	@FXML
	TextField transpasseSecundarias;
	@FXML
	TextField composicao;
	@FXML
	TextField peDireito;
	@FXML
	ComboBox<String> macacos;
	@FXML
	ComboBox<String> forcados;
	@FXML
	ComboBox<String> postes;
	@FXML
	TextField sumarioTipo;
	@FXML
	TextField sumarioCoordenadaX;
	@FXML
	TextField sumarioCoordenadaY;
	@FXML
	TextField sumarioCoordenadaZ;
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
	@FXML
	TextField diretorioPecas;
	@FXML
	SplitPane split;
	
	ArrayList<TipoTravessa> tiposTravessas;
	
	public static final String fxml = "SciaXML.fxml";
	public static Stage stage;
	
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
					RepositorioPecas.travessas.put(t.getItem(), t);
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
		adicionarTravessaX(selecao.getItem());
	}
	
	public void adicionarTravessaX(String item){
		TipoTravessa t = new TipoTravessa(""+(this.travessasX.getItems().size()+1), item);
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
		adicionarTravessaY(selecao.getItem());
	}
	
	public void adicionarTravessaY(String item){
		TipoTravessa t = new TipoTravessa(String.valueOf(this.travessasY.getItems().size()+1), item);
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
	public void gerarXML(ActionEvent e){		
		if (!"".equals(this.totalPecas.getText())){		
			FileChooser fc = new FileChooser();
			fc.setTitle("Informe o nome do arquivo");
			File f = fc.showSaveDialog(null);
			
			SumarioDados s = popularSumarioDados();
			
			System.out.println("Entrada:\n"+s.toString());
			
			try {
				// TODO: Revisar cálculo Marquinhos (hoje esta fixo para 3 peças em x e 2 em y)
				s = Calculo.calculaEstrutura(s);
				
				System.out.println("Saída:\n"+s.toString());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			construirProject(s,f);			
		}	
		else{
			JOptionPane.showMessageDialog(null, "Nenhum arquivo de peça carregado no sistema.");
		}
			
	}
	
	private void construirProject(SumarioDados s, File f) {

		// TODO: O método mais zica		
		
		String fileName = f.getName()+SciaXMLContantes.XML;
		System.out.println(fileName);
		Project p = new Project(s,fileName);
		
		try {
			SciaXMLFileManager.project2XML(p, f);
		} catch (SciaXMLFileManagerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(),SciaXMLContantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private SumarioDados popularSumarioDados(){
				
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
			String coordenadaX = this.sumarioCoordenadaX.getText();
			String coordenadaY = this.sumarioCoordenadaY.getText();
			String coordenadaZ = this.sumarioCoordenadaZ.getText();
			
			List<String> travessasX = this.sumarioTravessasX.getItems();
			List<Peca> pecasX = new ArrayList<Peca>();
			for (String tipo : travessasX) {
				Peca peca = new Peca();
				Project project = RepositorioPecas.pecas.get(tipo);
				peca.setTipoEquipamento(tipo);
				System.out.println("X="+project.getComprimentoX());
				System.out.println("Y="+project.getComprimentoY());
				peca.setComprimentoPecaX(120.0);
				peca.setComprimentoPecaY(170.0);
				pecasX.add(peca);
			}		
			
			List<String> travessasY = this.sumarioTravessasY.getItems();
			List<Peca> pecasY = new ArrayList<Peca>();
			for (String tipo : travessasY) {
				Peca peca = new Peca();
				Project project = RepositorioPecas.pecas.get(tipo);
				System.out.println("X="+project.getComprimentoX());
				System.out.println("Y="+project.getComprimentoY());				
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
			
			RepositorioProjeto.projeto.setTotalPecas(totalPecas);
			RepositorioProjeto.projeto.setTipoEquipamento(tipoEquipamento);
			RepositorioProjeto.projeto.setMedidaLageX(medidaLageX);
			RepositorioProjeto.projeto.setMedidaLageY(medidaLageY);
			RepositorioProjeto.projeto.setEspessura(espessura);
			RepositorioProjeto.projeto.setFolgaLajeX1(folgaLajeX1);
			RepositorioProjeto.projeto.setFolgaLajeX2(folgaLajeX2);
			RepositorioProjeto.projeto.setFolgaLajeY1(folgaLajeY1);
			RepositorioProjeto.projeto.setFolgaLajeY2(folgaLajeY2);
			RepositorioProjeto.projeto.setPecasX(pecasX);
			RepositorioProjeto.projeto.setPecasY(pecasY);
			RepositorioProjeto.projeto.setKidH(kidH);
			RepositorioProjeto.projeto.setKidI(kidI);
			RepositorioProjeto.projeto.setVigasPrincipais(vigasPrincipais);
			RepositorioProjeto.projeto.setVigasSecundarias(vigasSecundarias);
			RepositorioProjeto.projeto.setComposicaoTorres(composicaoTorres);
			RepositorioProjeto.projeto.setCoordenadaX(coordenadaX);
			RepositorioProjeto.projeto.setCoordenadaX(coordenadaY);
			RepositorioProjeto.projeto.setCoordenadaX(coordenadaZ);
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problemas durante a conversão dos dados. " +
					"Por favor, verifique os dados informados", "SciaXML",JOptionPane.ERROR_MESSAGE);
		}
		
		return RepositorioProjeto.projeto;
	}
	
	@FXML
	public void habilitarViga(ActionEvent e){
		String selecao = this.vigasPrincipais.getSelectionModel().getSelectedItem();
		JOptionPane.showMessageDialog(null, "Habilitar a viga: " + selecao + "?");
	}
	
	@FXML
	public void salvarProjeto(){
		popularSumarioDados();
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
	
    private String checkString (String s){
    	if (s != null && !"".equals(s))
    		return s;
    	else
    		return "";
    }

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
			
			this.medidaX.setText(checkString(RepositorioProjeto.projeto.getMedidaLageX()));
    		this.medidaY.setText(checkString(RepositorioProjeto.projeto.getMedidaLageY()));
    		this.folgaX1.setText(checkString(RepositorioProjeto.projeto.getFolgaLajeX1()));
    		this.folgaX2.setText(checkString(RepositorioProjeto.projeto.getFolgaLajeX2()));
    		this.folgaY1.setText(checkString(RepositorioProjeto.projeto.getFolgaLajeY1()));
    		this.folgaY2.setText(checkString(RepositorioProjeto.projeto.getFolgaLajeY2()));
    		this.espessura.setText(checkString(RepositorioProjeto.projeto.getEspessura()));
    				
    		this.sumarioMedidaX.setText(checkString(RepositorioProjeto.projeto.getMedidaLageX()));
    		this.sumarioMedidaY.setText(checkString(RepositorioProjeto.projeto.getMedidaLageY()));
    		this.sumarioFolgaX1.setText(checkString(RepositorioProjeto.projeto.getFolgaLajeX1()));
    		this.sumarioFolgaX2.setText(checkString(RepositorioProjeto.projeto.getFolgaLajeX2()));
    		this.sumarioFolgaY1.setText(checkString(RepositorioProjeto.projeto.getFolgaLajeY1()));
    		this.sumarioFolgaY2.setText(checkString(RepositorioProjeto.projeto.getFolgaLajeY2()));
    		this.sumarioEspessura.setText(checkString(RepositorioProjeto.projeto.getEspessura()));
    		
    		List<Peca> pecasX = RepositorioProjeto.projeto.getPecasX();
    		if (pecasX != null && pecasX.size() > 0)
	    		for (Peca peca : pecasX) {
					adicionarTravessaX(peca.getTipoEquipamento());
				}
    		
    		List<Peca> pecasY = RepositorioProjeto.projeto.getPecasY();
    		if (pecasY != null && pecasY.size() > 0)
	    		for (Peca peca : pecasY) {
	    			adicionarTravessaY(peca.getTipoEquipamento());
				}    		
    		  		
    	}		
	}
}
