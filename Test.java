package iut.sae.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.scene.Parent;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class Test extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader chargeurFXML = new FXMLLoader();
			
			chargeurFXML.setLocation(getClass().getResource("test.fxml")); 
			
			Parent racine = chargeurFXML.load();
			

			
			
			
			
	

			Scene scene = new Scene(racine); 

			primaryStage.setTitle("Demineur");
			 primaryStage.setHeight(1000);
			 primaryStage.setWidth(1000);
			 primaryStage.setScene(scene);
			 
			 primaryStage.show(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}