/*
 * Labyrinthe.java                                    16 mai 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.jeu;

import java.util.List;
import java.util.ArrayList;


/** TODO comment class responsibility (SRP)
 * @author tany.catalabailly
 *
 */
public class Grille {

    /** la hauteur du labyrinthe*/
    private int hauteur;

    /** la longueur du labyrinthe */
    private int longueur;

    /** le nombre de mine dans la partie */
    private int nbMine;

    /** liste contenant l'ensemble des salle du labyrinthe*/
    private List<Case> listeCase;

    private boolean gameOver; 

    /**
     * constructeur de la grille
     * @param hauteur de la grille
     * @param longueur de la grille
     * @param nbMine de la grille
     * @throws IllegalArgumentExeption si les parametree sont negatif.
     */
    public Grille(int hauteur, int longueur,int nbMine) {
        super();
        if (   hauteur  <= 0 
            && longueur <= 0
            && nbMine   <= 0
            && nbMine   >= hauteur*longueur) {
            throw (new IllegalArgumentException("hauteur longueur et nbMine doivent etre positif"));
        }
        this.hauteur = hauteur;
        this.longueur = longueur;
        this.nbMine = nbMine;
        this.gameOver = false;
        this.listeCase = new ArrayList<Case>();
        for (int i =0; i<hauteur*longueur;i++) {
            this.listeCase.add(new Case(i));
        }


    }

    /** 
     * @return la hauteur de la grille
     */
    public int getHauteur() {
        return hauteur;
    }

    /** 
     * @return la longueur de la grille
     */
    public int getLongueur() {
        return longueur;
    }

    /** @return valeur de listeCase */
    public List<Case> getListeCase() {
        return listeCase;
    }

    /** 
     * @return la taille du labyrinthe
     */
    public int getTaille () {
        return this.listeCase.size();
    }

    /** 
     * Ajoute les case voisine dans la liste les voisin de chaque case.
     * Defini les voisin de chaque case.
     */
    public void createListeVoisin() {
        for (Case laCase : this.getListeCase()) {
            if (!this.isPremiereLigne(laCase.getIdentifiant())) {
                laCase.getListeVoisin().add(this.getListeCase().get(laCase.getIdentifiant()-this.longueur));
                if (!this.isColoneDroite(laCase.getIdentifiant())) {
                    laCase.getListeVoisin().add(this.getListeCase().get(laCase.getIdentifiant()+1-this.longueur));
                }
                if (!this.isColoneGauche(laCase.getIdentifiant())) {
                    laCase.getListeVoisin().add(this.getListeCase().get(laCase.getIdentifiant()-1-this.longueur));
                }
            }
            if (!this.isColoneDroite(laCase.getIdentifiant())) {
                laCase.getListeVoisin().add(this.getListeCase().get(laCase.getIdentifiant()+1));
            }
            if (!this.isColoneGauche(laCase.getIdentifiant())) {
                laCase.getListeVoisin().add(this.getListeCase().get(laCase.getIdentifiant()-1));
            }
            if (!this.isDerniereLigne(laCase.getIdentifiant())) {
                laCase.getListeVoisin().add(this.getListeCase().get(laCase.getIdentifiant()+this.longueur));
                if (!this.isColoneDroite(laCase.getIdentifiant())) {
                    laCase.getListeVoisin().add(this.getListeCase().get(laCase.getIdentifiant()+1+this.longueur));
                }
                if (!this.isColoneGauche(laCase.getIdentifiant())) {
                    laCase.getListeVoisin().add(this.getListeCase().get(laCase.getIdentifiant()-1+this.longueur));
                }
            }
            laCase.checkBombeVoisine();
        }
    }


    /**
     * @return le nombre de bombe
     */
    public int nombreBombe() {
        int nbBombe = 0;
        for (Case element : listeCase) {
            if (element.isEstBombe()) {
                nbBombe++;
            }
        }
        return nbBombe;

    }

    /** 
     * Creer des case qui sont des bombe
     */
    public void placeBombe() {
        int nbBombeDemande = nombreBombe();
        while(nbBombeDemande!=this.nbMine) {
            int nombrealeatoire = (int)(Math.random()*this.hauteur*this.longueur);
            if (!listeCase.get(nombrealeatoire).isEstBombe()) {
                listeCase.get(nombrealeatoire).setEstBombe(true);
                nbBombeDemande++;
            }
        }
    }



    /**
     * @param placeCase numero de la case
     * @return true si la case est a la premiere ligne
     */
    public boolean isPremiereLigne (int placeCase) {
        return placeCase / this.longueur < 1;
    }

    /**
     * @param placeCase numero de la case
     * @return true si la case est a la derniere ligne
     */
    public boolean isDerniereLigne (int placeCase) {
        return placeCase + this.longueur >= this.longueur*this.hauteur;
    }

    /**
     * @param placeCase numero de la case
     * @return true si la case est a la colone de gauche
     */
    public boolean isColoneGauche (int placeCase) {
        return placeCase % this.longueur ==0;
    }

    /**
     * @param placeCase numero de la case
     * @return true si la case est a la colone de droite
     */
    public boolean isColoneDroite (int placeCase) {
        return (placeCase+1) % this.longueur ==0;
    }

    /**
     * @return true si toute les case qui ne sont pas des bombe sont decouverte
     *         false sinon
     */
    public boolean victoire() {
        for (Case laCase : this.listeCase) {
            if (!laCase.isEstBombe()&& laCase.getEtatCase()==0) {
                return false;
            }
        }
        return true;
    }
    /**
     * met le boolean gameOver de chaque case a true
     */
    public void defaite() {
        this.gameOver = true;
    }

    /** @return valeur de gameOver */
    public boolean isGameOver() {
        return gameOver;
    }
    
}
