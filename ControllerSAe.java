package iut.sae.application;


import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import iut.sae.jeu.*;

public class ControllerSAe {


	

	private int seconds = 0;
	private  Timer timer;

	public Grille grille;
	@FXML
	private ImageView smileID;

	@FXML
	private HBox bas;

	@FXML
	private GridPane JeuID;

	@FXML
	private Label minuteurID;

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
	private int DifficulteChoisi = -1;
	
	   @FXML
	    private TextField hauteurGrilleID;
	
	   @FXML
	    private TextField longueurGrilleID;
	@FXML
	private Label DifficulteID ;
	
	  @FXML
	    private TextField nbMineID;
	private int premierClick ;
	private int compteBombe;
	private int compteDrapeau;
	public static int longueurGrille;
	public static int hauteurGrille;
	private Image[] cases = {new Image("Z:\\case.png"),new Image("Z:\\bombe.png"),new Image("Z:\\casevide.png")};
	private int nbMine;
	private Image[] smiley = {new Image("Z:\\smileyBase.png"),new Image("Z:\\smileyPerdant.png"),new Image("Z:\\smileyLunette.png"),new Image("Z:\\smileyDecou.png")};
	
	@FXML
	void newGame(ActionEvent event) {
		if (DifficulteChoisi == -1) {
			handleBeginner(null);
		}
		
		 minuteurID.setText("Minuteur : "+0);
		 

		 seconds = 0;
		premierClick = 0;
		
		//Timer time = new Timer(5, null);
		//MinuteurID.setText("minuteur : "+ time);

		JeuID = new GridPane();
		
		if (nbMine > longueurGrille*hauteurGrille ) {
			System.out.println(nbMine);
			nbMine = longueurGrille*hauteurGrille -longueurGrille - 10;
			System.out.println(nbMine);
		}
		grille = new Grille(hauteurGrille,longueurGrille,nbMine);
		compteBombe =nbMine;
		compteDrapeau =nbMine;
		DrapeauRestantsID.setText("Drapeau restants : " + compteDrapeau);
	


		int hauteur = 0;
		int longueur = 0;
		listeImage = new ImageView[longueurGrille*hauteurGrille +1];
		JeuID.getChildren().clear();

		for (hauteur = 0; hauteur < grille.getHauteur(); hauteur++) {

		
			for (longueur = 0; longueur < grille.getLongueur(); longueur++) {
				int position = hauteur* grille.getLongueur()+longueur;
				
				if  (grille.getListeCase().get(position).getEtatCase() == 0) {
					listeImage[position] = new ImageView(cases[0]);
				}
				JeuID.add(listeImage[position],hauteur,longueur);
				borderPaneID.setCenter(JeuID);

			}

		}
		deroulementJeu();
	}

	void deroulementJeu() {
		int hauteur = 0;
		int longueur = 0;
		
		
		Image image =smiley[0];
		if (!grille.getListeCase().get(0).gameOver()) {

			
			JeuID.getChildren().clear();
			smileID.setImage(image);
			//smileID = new ImageView("Z:\\smileyBase.png");
			DrapeauRestantsID.setText("Drapeau restants : " + compteDrapeau);
			for (hauteur = 0; hauteur < grille.getHauteur(); hauteur++) {
				
				

				for (longueur = 0; longueur < grille.getLongueur() ; longueur++) {

					int position = hauteur*  grille.getLongueur()+longueur;
					Case caseActuelle= grille.getListeCase().get(position);
					if (caseActuelle.gameOver()&& grille.getListeCase().get(position).isEstBombe()) {
						listeImage[position] = new ImageView(cases[1]);
					}
					else if (caseActuelle.isEstBombe() && grille.getListeCase().get(position).getEtatCase() == 1) {

						listeImage[position] = new ImageView(cases[1]);



					} else if (caseActuelle.getEtatCase() == 1 && grille.getListeCase().get(position).getNbBombeVoisine() == 0){

						listeImage[position] = new ImageView(cases[2]);

					} else if (caseActuelle.getEtatCase() == 1){
						String nombreCase = "" +grille.getListeCase().get(position).getNbBombeVoisine();

						listeImage[position] = new ImageView("Z:\\"+nombreCase+".png");

					} else if (caseActuelle.getEtatCase() == 2){


						listeImage[position] = new ImageView("Z:\\drapeau.png");
					} else if (caseActuelle.getEtatCase() == 3){


						listeImage[position] = new ImageView("Z:\\interrogation.png");
					} else {
						listeImage[position] = new ImageView(cases[0]);
					}
					JeuID.add(listeImage[position],hauteur,longueur);
					JeuID.setAlignment(Pos.CENTER);

					if (!grille.victoire()) {
							listeImage[position].setOnMouseClicked(new EventHandler<MouseEvent>() {
								public void handle(MouseEvent event) {
									/*image3 =smiley[2];
									smileID.setImage(image);
									try {
							            Thread.sleep(500);
							        } catch (InterruptedException e) {
							            e.printStackTrace();
							        }
									smileID.setImage(smiley[0]);*/

									if (event.getButton() == MouseButton.PRIMARY && grille.getListeCase().get(position).getEtatCase() <2) {
										
										while (premierClick ==0) { 						
											grille.placeBombe();
											grille.createListeVoisin();
											if (!grille.getListeCase().get(position).isEstBombe() && grille.getListeCase().get(position).getNbBombeVoisine()== 0) {
												premierClick++;
												startTimer();
											}
											if (premierClick == 0) {
												
												grille = new Grille(hauteurGrille,longueurGrille,nbMine);

											}

										}
										grille.getListeCase().get(position).decouvrir();
										if (grille.getListeCase().get(position).gameOver()) {
											grille.defaite();

										}
										Image image2 = smiley[3];	
										smileID.setImage(smiley[3]);
										deroulementJeu();
										//if (grille.getListeCase().get(position).getEtatCase())


									} 

									/*if (event.getButton() == MouseButton.SECONDARY ) {
									if (event.getButton() == MouseButton.PRIMARY) {
										System.out.println("doubleClick");
										grille.getListeCase().get(position).decouvrirDoubleClic();
									} else if (grille.getListeCase().get(position).getEtatCase()==0){
										if (grille.getListeCase().get(position).getEtatCase()== 0) {
											compteBombe += grille.getListeCase().get(position).drapeau();
											compteDrapeau --;

										} else if (grille.getListeCase().get(position).getEtatCase()== 2) {
											compteDrapeau++;

											grille.getListeCase().get(position).setEtatCase(3);
											if (grille.getListeCase().get(position).isEstBombe()) {
												compteBombe ++;
											}
										} else if (grille.getListeCase().get(position).getEtatCase()!=1){

											grille.getListeCase().get(position).setEtatCase(0);

										}
										System.out.print(compteBombe);
										deroulementJeu();
									}
								}*/
									/*if (event.getButton() == MouseButton.PRIMARY) {
									if (grille.getListeCase().get(position).getEtatCase()==1 ) {
										System.out.println("doubleClick");
										grille.getListeCase().get(position).decouvrirDoubleClic();
									}	else if (grille.getListeCase().get(position).getEtatCase()==0){
										if (grille.getListeCase().get(position).getEtatCase() <2) {

											while (premierClick ==0) { 						
												grille.placeBombe();
												grille.createListeVoisin();
												if (!grille.getListeCase().get(position).isEstBombe() && grille.getListeCase().get(position).getNbBombeVoisine()== 0) {
													premierClick++;
												}
												if (premierClick == 0) {
													grille = new Grille(hauteurGrille, longueurGrille, nbMine);

												}

											}

											grille.getListeCase().get(position).decouvrir();
											/*Image image2 =new Image("Z:\\smileyDecou.png");
											smileID.setImage(image2);*/

									/*
											if (grille.getListeCase().get(position).gameOver()) {
												grille.defaite();

											}


											deroulementJeu();
										}
									}*/

									if (event.getButton() == MouseButton.SECONDARY) {


										if (grille.getListeCase().get(position).getEtatCase()== 0) {
											compteBombe += grille.getListeCase().get(position).drapeau();
											compteDrapeau --;

										} else if (grille.getListeCase().get(position).getEtatCase()== 2) {
											compteDrapeau++;

											grille.getListeCase().get(position).setEtatCase(3);
											if (grille.getListeCase().get(position).isEstBombe()) {
												compteBombe ++;
											}
										} else if (grille.getListeCase().get(position).getEtatCase()!=1){

											grille.getListeCase().get(position).setEtatCase(0);

										}
										
										deroulementJeu();
									}

								}});


						
					}else {
						image =smiley[2];
						smileID.setImage(image);
						stopTimer();

					}


				}
			}
			} else {
				for ( hauteur = 0; hauteur < grille.getHauteur(); hauteur++) {
					for ( longueur = 0; longueur < grille.getLongueur() ; longueur++) {
						int position = hauteur*  grille.getLongueur()+longueur;
						if (grille.getListeCase().get(position).gameOver()&& grille.getListeCase().get(position).isEstBombe()) {
							listeImage[position] = new ImageView(cases[1]);
							JeuID.add(listeImage[position],hauteur,longueur);
							JeuID.setAlignment(Pos.CENTER);
						}
						
					}
				}
				image = smiley[1];
				smileID.setImage(image);
				stopTimer();
				
			}
		borderPaneID.setCenter(JeuID);
	} 

	@FXML
    void handleBeginner(ActionEvent event) {
		
		hauteurGrille = 9;
		longueurGrille = 9;
		nbMine = 10;
		DifficulteChoisi = 0;
		DifficulteID.setText(Difficulte[1]);
		newGame(null);
    }

    @FXML
    void handleIntermediate(ActionEvent event) {
    	hauteurGrille = 16;
		longueurGrille = 16;
		nbMine = 40;
		DifficulteChoisi = 1;
		DifficulteID.setText(Difficulte[0]);
		newGame(null);
    }

    @FXML
    void handleDifficult(ActionEvent event) {
    	hauteurGrille = 32;
		longueurGrille = 16;
		nbMine = 99;
		DifficulteChoisi = 2;
		DifficulteID.setText(Difficulte[2]);
		newGame(null);
    }

    @FXML
    void handleCustom(ActionEvent event) {
    	if (Integer.parseInt(hauteurGrilleID.getText())>0 && Integer.parseInt(longueurGrilleID.getText())>0 && Integer.parseInt(nbMineID.getText())>0) {
    		hauteurGrille =Integer.parseInt(hauteurGrilleID.getText());
    		longueurGrille =Integer.parseInt(longueurGrilleID.getText());
    		nbMine =Integer.parseInt(nbMineID.getText());
    		DifficulteChoisi = 3;
        	DifficulteID.setText(Difficulte[3]);
        	newGame(null);
    	}
    	
    }
    
    private void startTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
            	 Platform.runLater(() -> {
                seconds++;
                String sec= ""+seconds;
                minuteurID.setText("Minuteur : "+sec);
                
            	 });
            }
        };

        // Planifier la tâche pour s'exécuter toutes les secondes, avec un délai initial de 0
        timer.scheduleAtFixedRate(task, 0, 1000);
        System.out.println("Timer started");
    }

 

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            System.out.println("Timer stopped");
        }
    }
}

    





