/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scia.xml.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Stage;
import br.com.scia.xml.controller.PrincipalController;
import br.com.scia.xml.controller.SplashController;

/**
 *
 * @author luy
 */
public class SciaXMLStarter extends Application{
    
	Stage stage;	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	SplashController splashController;
	PrincipalController principalController;
	
    public static void main (String[] args){
    	Application.launch(SciaXMLStarter.class, (java.lang.String[])null);
    }

	@Override
    public void start(Stage primaryStage) {
    	try {
    		this.stage = primaryStage;
    		loadPrincipal();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
    }
	
	public void loadSplash(){
		splashController = (SplashController) getController(SplashController.fxml);
		splashController.setStarter(this);
		splashController.loadPage();
	}
	
	public void loadPrincipal(){
		principalController = (PrincipalController) getController(PrincipalController.fxml);
		principalController.setStarter(this);
		principalController.loadPage();
	}
    
	private Initializable getController(String fxml){
		FXMLLoader loader = new FXMLLoader();
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(SciaXMLStarter.class.getResource(fxml));
		
		try{			
			loader.load(SciaXMLStarter.class.getResourceAsStream(fxml));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return (Initializable) loader.getController();
	}
	
}
