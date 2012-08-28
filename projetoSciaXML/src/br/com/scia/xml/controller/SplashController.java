/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scia.xml.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.dao.RepositorioProjeto;
import br.com.scia.xml.entity.exception.RepositorioPecasException;
import br.com.scia.xml.entity.exception.SciaXMLFileManagerException;
import br.com.scia.xml.entity.exception.SciaXMLValidationException;
import br.com.scia.xml.util.SciaXMLContantes;
import br.com.scia.xml.util.SciaXMLFileManager;
import br.com.scia.xml.view.SciaXMLStarter;

/**
 *
 * @author luy
 */
public class SplashController implements Initializable{
	
	public static final String fxml = "SciaXMLSplash.fxml";
	public static Stage stage;
	
	@FXML
	TextField nome;
	@FXML
	TextField sigla;
	@FXML
	TextField diretorio;
	@FXML
	ProgressBar progressBar;
	
	@FXML
	public void escolherDiretorio(){		
		DirectoryChooser directoryChooser = new DirectoryChooser();
        
		File f = directoryChooser.showDialog(null);
		
		if (f != null){
			try {
				this.diretorio.setText(f.getPath());  
				File[] arquivos = f.listFiles();
				
				if (arquivos != null && arquivos.length > 0){
					for (File file: arquivos) {
						RepositorioPecas.addPeca(file);
					}
					this.progressBar.setProgress(1.0);
				}
			} catch (RepositorioPecasException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),SciaXMLContantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
			} 
		}
	}
	
	@FXML
	public void abrirProjeto(){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SciaXML Files", "*.sxml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showOpenDialog(null);
        
        if (file != null){
        	try {
				RepositorioProjeto.projeto = SciaXMLFileManager.carregarProjeto(file);
				
				if (RepositorioProjeto.projeto != null){
					this.nome.setText(RepositorioProjeto.projeto.getNomeProjeto());
					this.sigla.setText(RepositorioProjeto.projeto.getSiglaProjeto());
					this.diretorio.setText(RepositorioProjeto.projeto.getDiretorioPecas());
					
					File diretorio = new File(this.diretorio.getText());
					
					if (diretorio != null){
						File[] arquivos = diretorio.listFiles();
						if (arquivos != null && arquivos.length > 0){
							for (File f : arquivos) {
								RepositorioPecas.addPeca(f);
							}
							this.progressBar.setProgress(1.0);
						}
					}
				}
			} catch (SciaXMLFileManagerException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(),SciaXMLContantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
			} catch (RepositorioPecasException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(),SciaXMLContantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
			}
        }		
	}
	
	@FXML
	public void carregarProjeto(){		
		try {			
			validarCamposSplash();
			
			RepositorioProjeto.projeto.setNomeProjeto(this.nome.getText());
			RepositorioProjeto.projeto.setSiglaProjeto(this.sigla.getText());
			RepositorioProjeto.projeto.setDiretorioPecas(this.diretorio.getText());
			
			File file = null;
			
			if (RepositorioProjeto.projeto.getNomeArquivo() == null){
				FileChooser fc = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SciaXML Files", "*.sxml");
				fc.getExtensionFilters().add(extFilter);
		        
				file = fc.showSaveDialog(null);
			}else{
				file = new File(RepositorioProjeto.projeto.getNomeArquivo());
			}
			
			if (file != null){
				String nome = file.getAbsolutePath();
				
				File f;
				
				if (!nome.endsWith(SciaXMLContantes.SXML))
					f = new File(nome + SciaXMLContantes.SXML);
				else
					f = new File(nome);
				
				RepositorioProjeto.projeto.setNomeArquivo(f.getAbsolutePath());
				
				SciaXMLFileManager.salvarProjeto(f);
				
				PrincipalController.stage = new Stage();
				PrincipalController.loadPage();
						
				PrincipalController.stage.show();
				
				SplashController.stage.close();
			}
		}
		catch (SciaXMLValidationException e) {			
			JOptionPane.showMessageDialog(null, e.getMessage(),SciaXMLContantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
		}
		catch (SciaXMLFileManagerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),SciaXMLContantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	private void validarCamposSplash() throws SciaXMLValidationException{

		String mensagem = null;
		
		if ("".equals(this.nome.getText())){
			this.nome.getStyleClass().add(SciaXMLContantes.CSS_FIELD_ERROR);
			this.nome.setFocusTraversable(true);
			mensagem = SciaXMLContantes.CAMPOS_EM_OBRIGATORIOS;
		}else {
			this.nome.getStyleClass().add(SciaXMLContantes.CSS_FIELD_OK);
		}
		
		if ("".equals(this.sigla.getText())){
			this.sigla.getStyleClass().add(SciaXMLContantes.CSS_FIELD_ERROR);
			this.sigla.setFocusTraversable(true);
			mensagem = SciaXMLContantes.CAMPOS_EM_OBRIGATORIOS;
		}else {
			this.sigla.getStyleClass().add(SciaXMLContantes.CSS_FIELD_OK);
		}
		
		if ("".equals(this.diretorio.getText())){
			this.diretorio.getStyleClass().add(SciaXMLContantes.CSS_FIELD_ERROR);
			this.diretorio.setFocusTraversable(true);
			mensagem = SciaXMLContantes.CAMPOS_EM_OBRIGATORIOS;
		}else {
			if (RepositorioPecas.pecas.size() == 0)
				mensagem = SciaXMLContantes.NENHUM_ARQUIVO_PECA;
			else				
				this.diretorio.getStyleClass().add(SciaXMLContantes.CSS_FIELD_OK);
		}
			
		
		if (mensagem != null)
			throw new SciaXMLValidationException(mensagem);
		
	}

	@FXML
	public void sair(){
		SplashController.stage.close();
	}
	
	@FXML
	public void abrirSobre(){		
		// TODO: Implementar popup com os dados do sistema
	}
	
	public static void loadPage(){
		try{
			AnchorPane page = (AnchorPane) FXMLLoader.load(SciaXMLStarter.class.getResource(fxml));
	        
	        Scene scene = new Scene(page);
	        SplashController.stage.setScene(scene);
	        SplashController.stage.setTitle("SciaXML");
	        SplashController.stage.setResizable(false);
	        SplashController.stage.show();
	        
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
