package iut.sae.application;




import java.util.List;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.Timer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import iut.sae.jeu.*;
public class ControllerSAe {
	
	private Grille grille;
    @FXML
    private ImageView smileID;

    @FXML
    private HBox bas;

    @FXML
    private GridPane JeuID;

    @FXML
    private Label MinuteurID;

    @FXML
    private Label DrapeauRestantsID;

    @FXML
    public BorderPane borderPaneID;
    @FXML
    private HBox HautID;

    @FXML
    private VBox GaucheId;

    @FXML
    private VBox DroitId;
    private ImageView[] listeImage;
    @FXML
    private Label DifficultéID;

    @FXML
    void newGame(ActionEvent event) {
    	MinuteurID.setText("minuteur : 0");
    	//Timer time = new Timer(5, null);
    	//MinuteurID.setText("minuteur : "+ time);
    	
    	JeuID = new GridPane();
    	grille = new Grille(10,10);
    	
    	
    	int hauteur = 0;
    	int longueur = 0;
    	
		LocalTime debut;
		debut = LocalTime.now();
		
		
		
		
               
    	listeImage = new ImageView[200];
    	for (hauteur = 0; hauteur < grille.getHauteur(); hauteur++) {
    		
    		int test2 = hauteur;
			 for (longueur = 0; longueur < grille.getLongueur(); longueur++) {
				 int test = hauteur* 10+longueur;
				 int test3 = longueur;
				 listeImage[test] = new ImageView("Z:\\case.png");
		           JeuID.add(listeImage[test],hauteur,longueur);
		           
		           listeImage[test].setOnMouseClicked(new EventHandler<MouseEvent>() {
		        	   
		        	     public void handle(MouseEvent event) {
		        	    	 LocalTime fin;
		        	    	 fin = LocalTime.now();
		        	    	 Duration ecart = Duration.between(debut,fin);
		        	    	 long tempExecutionL = ecart.toSeconds();
		        	    	System.out.println("Le calcul à mis "+tempExecutionL + " minisecondes ");
		        	    	 if (event.getButton() == MouseButton.PRIMARY) {
		        	    		 grille.getListeCase().get(test).decouvrir();
				        	    	if (grille.getListeCase().get(test).isEstBombe()) {
				        	    		listeImage[test] = new ImageView("Z:\\bombe.png");
				        	    	} else {
				        	    		listeImage[test] = new ImageView("Z:\\casevide.png");
				        	    	}
				        	    	//if (grille.getListeCase().get(test).getEtatCase())
				        	    	
				        	    	JeuID.add(listeImage[test], test2, test3);
		        	    	 } 
		        	    		 
		        	    	 if (event.getButton() == MouseButton.SECONDARY && event.getButton() == MouseButton.PRIMARY) {
		        	    		 
		        	    	 }
		        	    	 if (event.getButton() == MouseButton.SECONDARY) {
		        	    		 grille.getListeCase().get(test).decouvrirDoubleClic();
		        	    	 }
		        	    	
		        	   }});
		        	     
		        	     
		           
		               
		        }
    	}
    	borderPaneID.setCenter(JeuID);
    }

    @FXML
    void clickAction(ActionEvent event) {

    }
     void setBorderPane() {
    	GridPane test ;
    	test = new GridPane();
		 for (int hauteur = 0; hauteur < 10; hauteur++) {
			 for (int longueur = 0; longueur < 10; longueur++) {
		            test.add(new Label("ok"), longueur,hauteur);
		        }
	           
	        }
    	borderPaneID.setCenter(test);
    }
     

}
