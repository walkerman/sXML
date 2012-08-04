/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scia.xml.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import br.com.scia.xml.view.SciaXMLStarter;

/**
 *
 * @author luy
 */
public class SplashController implements Initializable{
	
	public static final String fxml = "SciaXMLSplash.fxml";
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
	
	public SciaXMLStarter getStarter() {
		return starter;
	}

	@FXML
	public void abrirProximaTela(ActionEvent e) throws IOException{
		
	}
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
