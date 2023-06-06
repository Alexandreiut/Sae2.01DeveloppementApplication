package iut.sae.application;


import java.io.*;
import java.lang.Thread;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import java.util.List;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.Timer;

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
/** TODO comment class responsibility (SRP)
 * @author tany.catalabailly
 *
 */
public class ControllerSAe {

    /** TODO comment field role (attribute, association) */
    public Grille grille;
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

    /** TODO comment field role (attribute, association) */
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
    private int longueurGrille;
    private int hauteurGrille;
    private Image[] cases = {new Image("file:image/case.png"),new Image("file:image/bombe.png"),new Image("file:image/casevide.png")};
    private int nbMine;
    private Image[] smiley = {new Image("file:image/smileyBase.png"),new Image("file:image/smileyLoose.png"),new Image("file:image/win.png"),new Image("file:image/devo.png")};

    @FXML
    void newGame(ActionEvent event) {
        if (DifficulteChoisi == -1) {
            handleBeginner(null);
        }
        premierClick = 0;
        MinuteurID.setText("minuteur : 0");
        //Timer time = new Timer(5, null);
        //MinuteurID.setText("minuteur : "+ time);

        JeuID = new GridPane();


        grille = new Grille(hauteurGrille,longueurGrille,nbMine);
        compteBombe = nbMine;
        compteDrapeau = nbMine;
        DrapeauRestantsID.setText("Drapeau restants : " + compteDrapeau);



        int hauteur = 0;
        int longueur = 0;
        listeImage = new ImageView[longueurGrille*hauteurGrille +1];
        JeuID.getChildren().clear();

        for (hauteur = 0; hauteur < grille.getHauteur(); hauteur++) {


            for (longueur = 0; longueur < grille.getLongueur(); longueur++) {
                int test = hauteur* grille.getLongueur()+longueur;

                if  (grille.getListeCase().get(test).getEtatCase() == 0) {
                    listeImage[test] = new ImageView(cases[0]);
                }
                JeuID.add(listeImage[test],hauteur,longueur);
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

                    int test = hauteur*  grille.getLongueur()+longueur;

                    if (grille.getListeCase().get(test).gameOver()&& grille.getListeCase().get(test).isEstBombe()) {
                        listeImage[test] = new ImageView(cases[1]);
                    }
                    else if (grille.getListeCase().get(test).isEstBombe() && grille.getListeCase().get(test).getEtatCase() == 1) {

                        listeImage[test] = new ImageView(cases[1]);



                    } else if (grille.getListeCase().get(test).getEtatCase() == 1 && grille.getListeCase().get(test).getNbBombeVoisine() == 0){

                        listeImage[test] = new ImageView(cases[2]);

                    } else if (grille.getListeCase().get(test).getEtatCase() == 1){
                        String nombreCase = "" +grille.getListeCase().get(test).getNbBombeVoisine();

                        listeImage[test] = new ImageView("Z:\\"+nombreCase+".png");

                    } else if (grille.getListeCase().get(test).getEtatCase() == 2){


                        listeImage[test] = new ImageView("Z:\\drapeau.png");
                    } else if (grille.getListeCase().get(test).getEtatCase() == 3){


                        listeImage[test] = new ImageView("Z:\\interrogation.png");
                    } else {
                        listeImage[test] = new ImageView(cases[0]);
                    }
                    JeuID.add(listeImage[test],hauteur,longueur);
                    JeuID.setAlignment(Pos.CENTER);

                    if (!grille.victoire()) {



                        listeImage[test].setOnMouseClicked(new EventHandler<MouseEvent>() {

                            public void handle(MouseEvent event) {


                                if (event.getButton() == MouseButton.PRIMARY && grille.getListeCase().get(test).getEtatCase() <2) {

                                    while (premierClick ==0) {                                              
                                        grille.placeBombe();
                                        grille.createListeVoisin();
                                        if (!grille.getListeCase().get(test).isEstBombe() && grille.getListeCase().get(test).getNbBombeVoisine()== 0) {
                                            premierClick++;
                                        }
                                        if (premierClick == 0) {
                                            grille = new Grille(hauteurGrille,longueurGrille,nbMine);

                                        }

                                    }

                                    grille.getListeCase().get(test).decouvrir();
                                    if (grille.getListeCase().get(test).gameOver()) {
                                        grille.defaite();

                                    }

                                    Image image2 = smiley[3];

                                    smileID.setImage(smiley[3]);


                                    if(smileID.getImage() == image2) {
                                        System.out.println("ok");
                                    }
                                    deroulementJeu();
                                    //if (grille.getListeCase().get(test).getEtatCase())


                                } 

                                /*if (event.getButton() == MouseButton.SECONDARY ) {
                                                                        if (event.getButton() == MouseButton.PRIMARY) {
                                                                                System.out.println("doubleClick");
                                                                                grille.getListeCase().get(test).decouvrirDoubleClic();
                                                                        } else if (grille.getListeCase().get(test).getEtatCase()==0){
                                                                                if (grille.getListeCase().get(test).getEtatCase()== 0) {
                                                                                        compteBombe += grille.getListeCase().get(test).drapeau();
                                                                                        compteDrapeau --;

                                                                                } else if (grille.getListeCase().get(test).getEtatCase()== 2) {
                                                                                        compteDrapeau++;

                                                                                        grille.getListeCase().get(test).setEtatCase(3);
                                                                                        if (grille.getListeCase().get(test).isEstBombe()) {
                                                                                                compteBombe ++;
                                                                                        }
                                                                                } else if (grille.getListeCase().get(test).getEtatCase()!=1){

                                                                                        grille.getListeCase().get(test).setEtatCase(0);

                                                                                }
                                                                                System.out.print(compteBombe);
                                                                                deroulementJeu();
                                                                        }
                                                                }*/
                                /*if (event.getButton() == MouseButton.PRIMARY) {
                                                                        if (grille.getListeCase().get(test).getEtatCase()==1 ) {
                                                                                System.out.println("doubleClick");
                                                                                grille.getListeCase().get(test).decouvrirDoubleClic();
                                                                        }       else if (grille.getListeCase().get(test).getEtatCase()==0){
                                                                                if (grille.getListeCase().get(test).getEtatCase() <2) {

                                                                                        while (premierClick ==0) {                                              
                                                                                                grille.placeBombe();
                                                                                                grille.createListeVoisin();
                                                                                                if (!grille.getListeCase().get(test).isEstBombe() && grille.getListeCase().get(test).getNbBombeVoisine()== 0) {
                                                                                                        premierClick++;
                                                                                                }
                                                                                                if (premierClick == 0) {
                                                                                                        grille = new Grille(hauteurGrille, longueurGrille, nbMine);

                                                                                                }

                                                                                        }

                                                                                        grille.getListeCase().get(test).decouvrir();
                                                                                        /*Image image2 =new Image("Z:\\smileyDecou.png");
                                                                                        smileID.setImage(image2);*/

                                /*
                                                                                        if (grille.getListeCase().get(test).gameOver()) {
                                                                                                grille.defaite();

                                                                                        }


                                                                                        deroulementJeu();
                                                                                }
                                                                        }*/

                                if (event.getButton() == MouseButton.SECONDARY) {


                                    if (grille.getListeCase().get(test).getEtatCase()== 0) {
                                        compteBombe += grille.getListeCase().get(test).drapeau();
                                        compteDrapeau --;

                                    } else if (grille.getListeCase().get(test).getEtatCase()== 2) {
                                        compteDrapeau++;

                                        grille.getListeCase().get(test).setEtatCase(3);
                                        if (grille.getListeCase().get(test).isEstBombe()) {
                                            compteBombe ++;
                                        }
                                    } else if (grille.getListeCase().get(test).getEtatCase()!=1){

                                        grille.getListeCase().get(test).setEtatCase(0);

                                    }

                                    deroulementJeu();
                                }

                            }});



                    }else {
                        image =smiley[2];
                        smileID.setImage(image);


                    }


                }
            }


        } else {
            for ( hauteur = 0; hauteur < grille.getHauteur(); hauteur++) {





                for ( longueur = 0; longueur < grille.getLongueur() ; longueur++) {
                    int test = hauteur*  grille.getLongueur()+longueur;
                    if (grille.getListeCase().get(test).gameOver()&& grille.getListeCase().get(test).isEstBombe()) {
                        listeImage[test] = new ImageView(cases[1]);
                        JeuID.add(listeImage[test],hauteur,longueur);
                        JeuID.setAlignment(Pos.CENTER);

                    }

                }
            }
            image = new Image("Z:\\smileyPerdant.png");
            smileID.setImage(image);
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


}