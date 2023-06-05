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
	
	private String[] Difficulte = {"Difficulté : normal","Difficulté : facile", "Difficulté : avancé","Difficulté : personnalisé"};
	private int DifficulteChoisi = 0;
	@FXML
	private Label DifficulteID ;
	private int premierClick ;
	@FXML
	void newGame(ActionEvent event) {
		premierClick = 0;
		MinuteurID.setText("minuteur : 0");
    	//Timer time = new Timer(5, null);
    	//MinuteurID.setText("minuteur : "+ time);
    	
    	JeuID = new GridPane();
    	
    	
    	grille = new Grille(10,10);

    	
		LocalTime debut;
		debut = LocalTime.now();
		
		int hauteur = 0;
		int longueur = 0;
		
		
		
    	listeImage = new ImageView[200];
    	JeuID.getChildren().clear();
    	
    	for (hauteur = 0; hauteur < grille.getHauteur(); hauteur++) {
    	    		
    	    		int test2 = hauteur;
    	    	for (longueur = 0; longueur < grille.getLongueur(); longueur++) {
    				 int test = hauteur* 10+longueur;
    				 int test3 = longueur;
    				if  (grille.getListeCase().get(test).getEtatCase() == 0) {
    				 listeImage[test] = new ImageView("Z:\\case.png");
    				 }
    		           JeuID.add(listeImage[test],hauteur,longueur);
    		           borderPaneID.setCenter(JeuID);
    		           
    	    	}
    	    	
    	}
    	deroulementJeu();
	}
	
	void deroulementJeu() {

		boolean gameOver = false;
		int hauteur = 0;
		int longueur = 0;
		JeuID.getChildren().clear();
		for (hauteur = 0; hauteur < grille.getHauteur(); hauteur++) {

			int test2 = hauteur;

			for (longueur = 0; longueur < grille.getLongueur(); longueur++) {
				int test = hauteur* 10+longueur;
				int test3 = longueur;
				if (grille.getListeCase().get(test).isEstBombe() && grille.getListeCase().get(test).getEtatCase() == 1) {

					listeImage[test] = new ImageView("Z:\\bombe.png");

				} else if (grille.getListeCase().get(test).getEtatCase() == 1 && grille.getListeCase().get(test).getNbBombeVoisine() == 0){

					listeImage[test] = new ImageView("Z:\\casevide.png");

				} else if (grille.getListeCase().get(test).getEtatCase() == 1){
					String nombreCase = "" +grille.getListeCase().get(test).getNbBombeVoisine();

					listeImage[test] = new ImageView("Z:\\"+nombreCase+".png");

				} else if (grille.getListeCase().get(test).getEtatCase() == 2){


					//listeImage[test] = new ImageView("C:\\Users\\Cluzel\\Documents\\drapeau.png");

				} else {
					listeImage[test] = new ImageView("Z:\\case.png");
				}
				JeuID.add(listeImage[test],hauteur,longueur);
				if (!gameOver) {


					listeImage[test].setOnMouseClicked(new EventHandler<MouseEvent>() {

						public void handle(MouseEvent event) {

							System.out.println(test);
							if (event.getButton() == MouseButton.PRIMARY) {
								
								while (premierClick ==0) { 						
									grille.placeBombe();
							        grille.createListeVoisin();
							        if (!grille.getListeCase().get(test).isEstBombe() && grille.getListeCase().get(test).getNbBombeVoisine()== 0) {
							        	premierClick++;
							        }
							        if (premierClick == 0) {
							        	grille = new Grille(10,10);
							        	
							        }
							       
								}
								
								grille.getListeCase().get(test).decouvrir();
								if(grille.getListeCase().get(test).gameOver()) {
									//gameOver = true;
								}


								deroulementJeu();
								//if (grille.getListeCase().get(test).getEtatCase())


								} 
							
							if (event.getButton() == MouseButton.SECONDARY && event.getButton() == MouseButton.PRIMARY) {
								int compteBombe = 20;
								if (grille.getListeCase().get(test).getEtatCase()== 0) {
									compteBombe -= grille.getListeCase().get(test).drapeau();
								} else if (grille.getListeCase().get(test).getEtatCase()== 2) {

									grille.getListeCase().get(test).setEtatCase(3);
									//if (grille.getListeCase().get(test).isEstBombe) {
									//    compteBombe ++;
									//}
								} else {
									grille.getListeCase().get(test).setEtatCase(0);

								}
								if (event.getButton() == MouseButton.SECONDARY) {
									grille.getListeCase().get(test).decouvrirDoubleClic();
								}

							}}});


				}

			}
		}
		borderPaneID.setCenter(JeuID);
	}
	private void updateAffichage(Grille grille) {
		
		System.out.println("dans le affichage");
		int compteur =0;
		for (Case laCase : grille.getListeCase()) {

			switch(laCase.getEtatCase()) {
			case 0:
				System.out.println("yo");
				listeImage[compteur] = new ImageView("Z:\\case.png");
				break;
			case 1:
				System.out.println("je dois etre decouver, case :" + compteur);
				if (grille.getListeCase().get(compteur).isEstBombe()) {
					listeImage[compteur] = new ImageView("Z:\\bombe.png");
				} else {

					listeImage[compteur] = new ImageView("Z:\\casevide.png");
				}
				break;
			case 2:
				listeImage[compteur] = new ImageView("Z:\\drapeau.png");
				break;
			case 3:
				listeImage[compteur] = new ImageView("Z:\\ptInterro.png");
				break;
			}
			JeuID.add(listeImage[compteur], compteur/grille.getLongueur(), compteur%grille.getLongueur());
			
			compteur ++;
		}
	
		System.out.println("fin du affichage");
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