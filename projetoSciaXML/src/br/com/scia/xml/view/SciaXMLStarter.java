/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scia.xml.view;

import javafx.application.Application;
import javafx.stage.Stage;
import br.com.scia.xml.controller.SplashController;

/**
 *
 * @author luy
 */
public class SciaXMLStarter extends Application{
	
    public static void main (String[] args){
    	Application.launch(SciaXMLStarter.class, (java.lang.String[])null);
    }

	@Override
    public void start(Stage primaryStage) {
    	try {
    		loadSplash();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
    }
	
	public void loadSplash(){
		SplashController.stage = new Stage();
		SplashController.loadPage();
		SplashController.stage.show();
	    SplashController.stage.getScene().getStylesheets().add("/SciaXML.css");
	}
}
