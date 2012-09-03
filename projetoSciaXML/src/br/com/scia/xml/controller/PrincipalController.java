/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scia.xml.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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
import javafx.scene.control.ToggleButton;
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
import br.com.scia.xml.entity.view.Tipo;
import br.com.scia.xml.entity.view.TipoPecaXY;
import br.com.scia.xml.entity.view.TipoPoste;
import br.com.scia.xml.model.Calculo;
import br.com.scia.xml.util.SciaXMLConstantes;
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
	public ComboBox<Tipo> tamanhosPecasX;
	@FXML
	public TableView<Tipo> pecasX;
	@FXML
	public TextField quantidadePecasX;
	@FXML
	public AnchorPane areaCruzetaX;
	@FXML
	public TextField alturaVigaEsquerdaX;
	@FXML
	public TextField alturaVigaDireitaX;
	@FXML
	public TextField distanciaCruzetasX;
	@FXML
	public AnchorPane areaEscoraX;
	@FXML
	public ComboBox<String> forcadosX;
	@FXML
	public CheckBox tripeX;
	@FXML
	public AnchorPane areaCruzetaY;
	@FXML
	public TextField alturaVigaEsquerdaY;
	@FXML
	public TextField alturaVigaDireitaY;
	@FXML
	public TextField distanciaCruzetasY;
	@FXML
	public AnchorPane areaEscoraY;
	@FXML
	public ComboBox<String> forcadosY;
	@FXML
	public CheckBox tripeY;
	@FXML
	public ComboBox<String> tiposPecasY;
	@FXML
	public ComboBox<Tipo> tamanhosPecasY;
	@FXML
	public TableView<Tipo> pecasY;
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
	public TableView<Tipo> vigasPrincipais;
	@FXML
	public TextField transpassePrincipais;
	@FXML
	public ToggleButton lajePre;
	@FXML
	public ComboBox<String> tiposVigasSecundarias;
	@FXML
	public ComboBox<String> vigasSecundariasUsarDe;
	@FXML
	public TableView<Tipo> vigasSecundarias;
	@FXML
	public AnchorPane areaVigasSecundarias;
	@FXML
	public TextField transpasseSecundarias;
	@FXML
	public TextField entreVigas;
	@FXML
	public TableView<Tipo> postes;
	@FXML
	public TextField composicaoTorres;
	@FXML
	public TextField peDireito;
	@FXML
	public ComboBox<String> macacos;
	@FXML
	public ComboBox<String> forcados;
	@FXML
	public ComboBox<String> postesEspeciais;
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
	public ComboBox<String> sumarioPecasX;
	@FXML
	public ComboBox<String> sumarioPecasY;
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
	@FXML
	public ToggleGroup kidi;
	@FXML
	public CheckBox kidh;
	@FXML
	public ToggleGroup entreTravessas;
	@FXML
	public TextField espessuraCompensado;
	@FXML
	public TextField calculoViga;
	@FXML
	public TextField calculoLaje;
	@FXML
	public TextField calculoVigaLaje;
	@FXML
	public TextField calculoPiso;
	@FXML
	public TextField calculoAltura;
	@FXML
	public TextField calculoAjuste;
	@FXML
	public TextField calculoInferior;
	@FXML
	public TextField calculoSuperior;
	
	public ArrayList<Tipo> tiposTravessas;
	public ArrayList<Tipo> tiposCruzetas;
	public ArrayList<Tipo> tiposEscoras;
	public ArrayList<Tipo> tiposVigasPrincipaisHT20;
	public ArrayList<Tipo> tiposVigasPrincipaisV75;
	public ArrayList<Tipo> tiposVigasPrincipaisV18;
	public ArrayList<Tipo> tiposVigasPrincipaisVA18;
	public ArrayList<Tipo> tiposVigasSecundariasHT20;
	public ArrayList<Tipo> tiposVigasSecundariasV75;
	public ArrayList<Tipo> tiposVigasSecundariasV18;
	public ArrayList<Tipo> tiposVigasSecundariasVA18;
	public ArrayList<Tipo> tiposForcados;
	public ArrayList<Tipo> tiposPosteEspecial;
	public ArrayList<Tipo> tiposPoste;
	
	private TipoPecaXYController pecaXYController;
	private TipoForcadoController forcadoController;
	private TipoPosteController posteController;
	private TipoPosteEspecialController posteEspecialController;
	private TipoMacacoController macacoController;
	private TipoVigaController vigaController;
	
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
		this.pecaXYController = new TipoPecaXYController(this);
		this.forcadoController = new TipoForcadoController(this);
		this.posteController = new TipoPosteController(this);
		this.posteEspecialController = new TipoPosteEspecialController(this);
		this.vigaController = new TipoVigaController(this);
		this.macacoController = new TipoMacacoController(this);
		
		pecaXYController.inicializar();
		forcadoController.inicializar();
		posteController.inicializar();
		posteEspecialController.inicializar();
		macacoController.inicializar();
		vigaController.inicializar();	
	}
	
	@FXML
	public void carregarTamanhoPecasX(){
		this.pecaXYController.carregar();
	}
	
	@FXML
	public void carregarTamanhoPecasY(){
		this.pecaXYController.carregar();
	}
	
	@FXML
	public void carregarVigasPrincipais(){
		this.vigaController.VIGA_LOAD = TipoVigaController.VIGA_PRINCIPAL;
		this.vigaController.carregar();
	}
	
	@FXML
	public void carregarVigasSecundarias(){
		this.vigaController.VIGA_LOAD = TipoVigaController.VIGA_SECUNDARIA;
		this.vigaController.carregar();
	}
	
	@FXML
	public void adicionarPecaX(){
		Tipo selecao = this.tamanhosPecasX.getSelectionModel().getSelectedItem();
		if (this.pecaXYController.validarRegrasPecasXY(selecao, SciaXMLConstantes.ORIGEM_X))
			adicionarPecaX(selecao.getItem());
	}
	
	public void adicionarPecaX(String item){
		TipoPecaXY t = new TipoPecaXY(""+(this.pecasX.getItems().size()+1), item);
		t.setTableReference(this.pecasX);
		t.setSumarioReference(this.sumarioPecasX);
		t.setQuantidadeReference(this.quantidadePecasX);
		
		if (t != null){
			this.pecasX.getItems().add(t);
			this.sumarioPecasX.getItems().add(t.getItem());
			this.quantidadePecasX.setText(String.valueOf(this.pecasX.getItems().size()));
		}
	}
	
	@FXML
	public void adicionarPecaY(){
		Tipo selecao = this.tamanhosPecasY.getSelectionModel().getSelectedItem();
		if (this.pecaXYController.validarRegrasPecasXY(selecao, SciaXMLConstantes.ORIGEM_Y))
			adicionarPecaY(selecao.getItem());
	}
	
	public void adicionarPecaY(String item){
		TipoPecaXY t = new TipoPecaXY(String.valueOf(this.pecasY.getItems().size()+1), item);
		t.setTableReference(this.pecasY);
		t.setSumarioReference(this.sumarioPecasY);
		t.setQuantidadeReference(this.quantidadePecasY);
		
		if (t != null){
			this.pecasY.getItems().add(t);
			this.sumarioPecasY.getItems().add(t.getItem());
			this.quantidadePecasY.setText(String.valueOf(this.pecasY.getItems().size()));
		}
	}
	
	@FXML
	public void limparDadosPecasX(){
		this.pecasX.getItems().removeAll(this.pecasX.getItems());
		this.quantidadePecasX.setText(String.valueOf(this.pecasX.getItems().size()));
		this.sumarioPecasX.getItems().removeAll(this.sumarioPecasX.getItems());
		this.areaCruzetaX.setDisable(true);
		this.areaEscoraX.setDisable(true);
	}
	
	@FXML
	public void limparDadosPecasY(){
		this.pecasY.getItems().removeAll(this.pecasY.getItems());
		this.quantidadePecasY.setText(String.valueOf(this.pecasY.getItems().size()));
		this.sumarioPecasY.getItems().removeAll(this.sumarioPecasY.getItems());
		this.areaCruzetaY.setDisable(true);
		this.areaEscoraY.setDisable(true);
	}
	
	@FXML
	public void escolherTipoEquipamento(){
		RadioButton t = (RadioButton) this.tipos.getSelectedToggle();
		this.sumarioTipo.setText(t.getText());
		
		if (t.getId().equals(SciaXMLConstantes.KIBLOC_LAJE)){
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
	public void setAlturaVigaEsquerdaX(){
		this.alturaVigaEsquerdaY.setText(this.alturaVigaEsquerdaX.getText());
	}
	
	@FXML
	public void setAlturaVigaDireitaX(){
		this.alturaVigaDireitaY.setText(this.alturaVigaDireitaX.getText());
	}
	
	@FXML
	public void setDistanciaEntreCruzetasX(){
		this.distanciaCruzetasY.setText(this.distanciaCruzetasX.getText());
	}
	
	@FXML
	public void setAlturaVigaEsquerdaY(){
		this.alturaVigaEsquerdaX.setText(this.alturaVigaEsquerdaY.getText());
	}
	
	@FXML
	public void setAlturaVigaDireitaY(){
		this.alturaVigaDireitaX.setText(this.alturaVigaDireitaY.getText());
	}
	
	@FXML
	public void setDistanciaEntreCruzetasY(){
		this.distanciaCruzetasX.setText(this.distanciaCruzetasY.getText());
	}
	
	@FXML
	public void setForcadoX(){
		if (this.forcadosX.getSelectionModel().getSelectedItem() != null)
			this.forcadosY.getSelectionModel().select(this.forcadosX.getSelectionModel().getSelectedItem());
	}
	
	@FXML
	public void setForcadoY(){
		if (this.forcadosY.getSelectionModel().getSelectedItem() != null)
			this.forcadosX.getSelectionModel().select(this.forcadosY.getSelectionModel().getSelectedItem());
	}
	
	@FXML
	public void setTripeX(){
		if (this.tripeX.isSelected())
			this.tripeY.setSelected(true);
		else
			this.tripeY.setSelected(false);
	}

	@FXML
	public void setTripeY(){
		if (this.tripeY.isSelected())
			this.tripeX.setSelected(true);
		else
			this.tripeX.setSelected(false);
	}

	@FXML
	public void gerarXML(ActionEvent e){		
		if (!"".equals(this.totalPecas.getText())){		
			FileChooser fc = new FileChooser();
			fc.setTitle("Informe o nome do arquivo");
			File f = fc.showSaveDialog(null);
			
			if (f != null){
				SumarioDados sumario = SciaXMLUtils.popularSumarioDados(this);
				
				try {
					sumario = Calculo.calculaEstrutura(sumario);
					
					SciaXMLUtils.construirProject(sumario,f);
					
					JOptionPane.showMessageDialog(null, "Arquivos .xml/.def salvos com sucesso.");
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2.getMessage(),SciaXMLConstantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
				}
			}
		}	
		else{
			JOptionPane.showMessageDialog(null, "Nenhum arquivo de peça carregado no sistema.");
		}
			
	}
		
	@FXML
	public void usarLajePre(){
		if (this.lajePre.isSelected()){
			this.areaVigasSecundarias.setDisable(true);
			this.sumarioVigasSecundarias.setItems(FXCollections.observableArrayList(new ArrayList<String>()));
		}
		else{
			this.areaVigasSecundarias.setDisable(false);
			carregarVigasSecundarias();
		}
	}
	
	@FXML
	public void salvarProjeto(){
		SciaXMLUtils.popularSumarioDados(this);
		try {
			SciaXMLFileManager.salvarProjeto(new File(RepositorioProjeto.projeto.getNomeArquivo()));
			JOptionPane.showMessageDialog(null,"Projeto salvo com sucesso.");
		} catch (SciaXMLFileManagerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(),SciaXMLConstantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
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
	public void abrirProjeto(){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SciaXML Files", "*.sxml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showOpenDialog(null);
        
        if (file != null){
        	try {
				SumarioDados sumario = SciaXMLFileManager.carregarProjeto(file);
				
				if (sumario != null){					
					File diretorio = new File(sumario.getDiretorioPecas());
					
					if (diretorio != null && diretorio.exists()){
						RepositorioProjeto.projeto = sumario;
						
						File[] arquivos = diretorio.listFiles();
						
						if (arquivos != null && arquivos.length > 0){
							if (RepositorioPecas.pecas.size() > 0)
								RepositorioPecas.pecas = new HashMap<>();
							
							for (File f : arquivos) {
								RepositorioPecas.addPeca(f);
							}
						}
						
						initialize();
					}else{
						JOptionPane.showMessageDialog(null, "Diretório de peças não encontrado",SciaXMLConstantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (SciaXMLFileManagerException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(),SciaXMLConstantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
			} catch (RepositorioPecasException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(),SciaXMLConstantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
			}
        }	
	}
	
	@FXML
	public void sair(){}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initialize();
	}
	
	private void initialize(){
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
    		this.peDireito.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getPeDireito()));
    		this.espessuraCompensado.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getEspessuraCompensado()));
    		this.transpassePrincipais.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getTranspassePrincipais()));
    		this.transpasseSecundarias.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getTranspasseSecundarias()));
    		this.entreVigas.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getEspacamentoEntreVigasSecundarias()));
    		this.composicaoTorres.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getComposicaoTorres()));
    		
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
    		this.sumarioComposicao.setText(SciaXMLUtils.checkString(RepositorioProjeto.projeto.getComposicaoTorres()));
    		
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
    		
    		List<Peca> vigasPrincipais = RepositorioProjeto.projeto.getVigasPrincipais();
    		if (vigasPrincipais != null && vigasPrincipais.size() > 0){
    			if (SciaXMLConstantes.TIPO_VIGA_HT20.equals(RepositorioProjeto.projeto.getTipoVigaPrincipal())){
					SciaXMLUtils.getSelectedTiposViga(this.tiposVigasPrincipaisHT20, vigasPrincipais);
				}
				if (SciaXMLConstantes.TIPO_VIGA_V18.equals(RepositorioProjeto.projeto.getTipoVigaPrincipal())){
					SciaXMLUtils.getSelectedTiposViga(this.tiposVigasPrincipaisV18, vigasPrincipais);
				}
				if (SciaXMLConstantes.TIPO_VIGA_V75.equals(RepositorioProjeto.projeto.getTipoVigaPrincipal())){
					SciaXMLUtils.getSelectedTiposViga(this.tiposVigasPrincipaisV75, vigasPrincipais);
				}
				if (SciaXMLConstantes.TIPO_VIGA_VA18.equals(RepositorioProjeto.projeto.getTipoVigaPrincipal())){
					SciaXMLUtils.getSelectedTiposViga(this.tiposVigasPrincipaisVA18, vigasPrincipais);
				}
				
    			for (Peca peca : vigasPrincipais) {
    				this.sumarioVigasPrincipais.getItems().add(peca.getTipo());
				}
    		}
    		
    		this.tiposVigasPrincipais.getSelectionModel().select(RepositorioProjeto.projeto.getTipoVigaPrincipal());
    		carregarVigasPrincipais();
    		
    		List<Peca> vigasSecundarias = RepositorioProjeto.projeto.getVigasSecundarias();
    		if (vigasSecundarias != null && vigasSecundarias.size() > 0){
    			if (SciaXMLConstantes.TIPO_VIGA_HT20.equals(RepositorioProjeto.projeto.getTipoVigaSecundaria())){
					SciaXMLUtils.getSelectedTiposViga(this.tiposVigasSecundariasHT20, vigasSecundarias);
				}
				if (SciaXMLConstantes.TIPO_VIGA_V18.equals(RepositorioProjeto.projeto.getTipoVigaSecundaria())){
					SciaXMLUtils.getSelectedTiposViga(this.tiposVigasSecundariasV18, vigasSecundarias);
				}
				if (SciaXMLConstantes.TIPO_VIGA_V75.equals(RepositorioProjeto.projeto.getTipoVigaSecundaria())){
					SciaXMLUtils.getSelectedTiposViga(this.tiposVigasSecundariasV75, vigasSecundarias);
				}
				if (SciaXMLConstantes.TIPO_VIGA_VA18.equals(RepositorioProjeto.projeto.getTipoVigaSecundaria())){
					SciaXMLUtils.getSelectedTiposViga(this.tiposVigasSecundariasVA18, vigasSecundarias);
				}
				
    			for (Peca peca : vigasSecundarias) {
    				this.sumarioVigasSecundarias.getItems().add(peca.getTipo());
				}
    		}
    		
    		this.tiposVigasSecundarias.getSelectionModel().select(RepositorioProjeto.projeto.getTipoVigaSecundaria());
    		carregarVigasSecundarias();
    		
    		List<Peca> postesSelecionados = RepositorioProjeto.projeto.getPostes();
    		List<Tipo> postes = this.postes.getItems();
    		
    		if (postesSelecionados != null){
				for (Tipo tipo : postes) {
					((TipoPoste) tipo).getHabilitar().setSelected(false);
					for (Peca peca : postesSelecionados) {
						if (tipo.getItem().equals(peca.getTipo())){
							((TipoPoste) tipo).getHabilitar().setSelected(true);
							break;
						}
					}
				}	
			}
    		
    		if (RepositorioProjeto.projeto.getKidI() != null && RepositorioProjeto.projeto.getKidI()){    			
    			RadioButton semKIDI = (RadioButton) this.entreTravessas.getToggles().get(0);
    			semKIDI.setSelected(false);
    			RadioButton comKIDI = (RadioButton) this.entreTravessas.getToggles().get(1);
    			comKIDI.setSelected(true);
    		}else if (RepositorioProjeto.projeto.getKidI() != null && !RepositorioProjeto.projeto.getKidI()){
    			RadioButton semKIDI = (RadioButton) this.entreTravessas.getToggles().get(0);
    			semKIDI.setSelected(true);
    			RadioButton comKIDI = (RadioButton) this.entreTravessas.getToggles().get(1);
    			comKIDI.setSelected(false);
    		}
    		
    		if (RepositorioProjeto.projeto.getKidH() != null && RepositorioProjeto.projeto.getKidH()){
    			this.kidh.setSelected(true);
    		}
    		
    		if (RepositorioProjeto.projeto.getEspacamentoEntreTravessas() != null){
    			for (Toggle tipo : this.entreTravessas.getToggles()) {
					RadioButton r = (RadioButton) tipo;
					if (r.getText().equals(RepositorioProjeto.projeto.getEspacamentoEntreTravessas()))
						r.setSelected(true);
				}
    		}
    		
    		if (RepositorioProjeto.projeto.getPosteEspecial() != null)
    			this.postesEspeciais.getSelectionModel().select(RepositorioProjeto.projeto.getPosteEspecial().getTipo());
    		if (RepositorioProjeto.projeto.getMacaco() != null)
	    		this.macacos.getSelectionModel().select(RepositorioProjeto.projeto.getMacaco().getTipo());
    		if (RepositorioProjeto.projeto.getForcado() != null)
    			this.forcados.getSelectionModel().select(RepositorioProjeto.projeto.getForcado().getTipo());

    		if (RepositorioProjeto.projeto.getPossuiCruzetaX() != null && RepositorioProjeto.projeto.getPossuiCruzetaX()){
    			this.areaCruzetaX.setDisable(false);
    			
    			if (RepositorioProjeto.projeto.getAlturaVigaDireitaX() != null)
        			this.alturaVigaDireitaX.setText(RepositorioProjeto.projeto.getAlturaVigaDireitaX());
    			if (RepositorioProjeto.projeto.getAlturaVigaEsquerdaX() != null)
        			this.alturaVigaEsquerdaX.setText(RepositorioProjeto.projeto.getAlturaVigaEsquerdaX());
    			if (RepositorioProjeto.projeto.getDistanciaCruzetasX() != null)
        			this.distanciaCruzetasX.setText(RepositorioProjeto.projeto.getDistanciaCruzetasX());
    		}
    		
    		if (RepositorioProjeto.projeto.getPossuiCruzetaY() != null && RepositorioProjeto.projeto.getPossuiCruzetaY()){
    			this.areaCruzetaY.setDisable(false);
    			
    			if (RepositorioProjeto.projeto.getAlturaVigaDireitaY() != null)
        			this.alturaVigaDireitaY.setText(RepositorioProjeto.projeto.getAlturaVigaDireitaY());
    			if (RepositorioProjeto.projeto.getAlturaVigaEsquerdaY() != null)
        			this.alturaVigaEsquerdaY.setText(RepositorioProjeto.projeto.getAlturaVigaEsquerdaY());
    			if (RepositorioProjeto.projeto.getDistanciaCruzetasY() != null)
        			this.distanciaCruzetasY.setText(RepositorioProjeto.projeto.getDistanciaCruzetasY());
    		}
    		
    		if (RepositorioProjeto.projeto.getPossuiEscoraX() != null && RepositorioProjeto.projeto.getPossuiEscoraX()){
    			this.areaEscoraX.setDisable(false);
    			
    			if (RepositorioProjeto.projeto.getTripeX() != null && RepositorioProjeto.projeto.getTripeX())
        			this.tripeX.setSelected(true);
    			
    			if (RepositorioProjeto.projeto.getForcadoX() != null)
        			this.forcadosX.getSelectionModel().select(RepositorioProjeto.projeto.getForcadoX().getTipo());
    		}
    		
    		if (RepositorioProjeto.projeto.getPossuiEscoraY() != null && RepositorioProjeto.projeto.getPossuiEscoraY()){
    			this.areaEscoraY.setDisable(false);
    		
    			if (RepositorioProjeto.projeto.getTripeY() != null && RepositorioProjeto.projeto.getTripeY())
        			this.tripeY.setSelected(true);
    			
    			if (RepositorioProjeto.projeto.getForcadoY() != null)
        			this.forcadosY.getSelectionModel().select(RepositorioProjeto.projeto.getForcadoY().getTipo());
    		}
    	}
	}
}
