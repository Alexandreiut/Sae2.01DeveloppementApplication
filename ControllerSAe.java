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

		
		grille = new Grille(4,4);
		listeImage = new ImageView[grille.getTaille()];
		JeuID = new GridPane();
		updateAffichage(grille);
		//while (!grille.victoire()) {
			borderPaneID.setCenter(JeuID);
		
			for (int idcase=0;idcase<grille.getTaille();idcase++) {
				int nbCase = idcase;
				listeImage[nbCase].setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						if (event.getButton() == MouseButton.PRIMARY) {
							grille.getListeCase().get(nbCase).decouvrir();

						} 

						if (event.getButton() == MouseButton.SECONDARY && event.getButton() == MouseButton.PRIMARY) {
							grille.getListeCase().get(nbCase).decouvrirDoubleClic();

						}
						if (event.getButton() == MouseButton.SECONDARY) {
							grille.getListeCase().get(nbCase).setEtatCase(2);
						}
						
					}	
				});
			
				for (int idcae=0;idcae<100000000;idcae++) {
					System.out.println("fin du while");
				}
		}
		
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