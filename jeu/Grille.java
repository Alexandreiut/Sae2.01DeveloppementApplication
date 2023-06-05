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

    
    /**
     * constructeur de labyrinthe
     * @param hauteur du labyrinthe
     * @param longueur du labyrinthe
     */
    public Grille(int hauteur, int longueur) {
        super();
        this.hauteur = hauteur;
        this.longueur = longueur;
        this.nbMine = 1;
        this.listeCase = new ArrayList<Case>();
        for (int i =0; i<hauteur*longueur;i++) {
            this.listeCase.add(new Case(i));
        }
        placeBombe();
        createListeVoisin();
        
    }
    
    public int getHauteur() {
		return hauteur;
	}

	public int getLongueur() {
		return longueur;
	}

	/** @return valeur de listeCase */
    public List<Case> getListeCase() {
        return listeCase;
    }

    /** TODO comment method role
     * @return la taille du labyrinthe
     */
    public int getTaille () {
        return this.listeCase.size();
    }
    
    /** TODO comment method role
     * 
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
    
    
    /** TODO comment method role
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
    
    /** TODO comment method role
     * 
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
    
    /** TODO comment method role
     * 
     */
    
    
    /** TODO comment method role
     * @param placeCase numero de la case
     * @return true si la case est a la premiere ligne
     */
    public boolean isPremiereLigne (int placeCase) {
        return placeCase / this.longueur < 1;
    }
    
    /** TODO comment method role
     * @param placeCase numero de la case
     * @return true si la case est a la derniere ligne
     */
    public boolean isDerniereLigne (int placeCase) {
        return placeCase + this.longueur >= this.longueur*this.hauteur;
    }
    
    /** TODO comment method role
     * @param placeCase numero de la case
     * @return true si la case est a la colone de gauche
     */
    public boolean isColoneGauche (int placeCase) {
        return placeCase % this.longueur ==0;
    }
    
    /** TODO comment method role
     * @param placeCase numero de la case
     * @return true si la case est a la colone de droite
     */
    public boolean isColoneDroite (int placeCase) {
        return (placeCase+1) % this.longueur ==0;
    }

	public boolean victoire() {
		for (Case laCase : this.listeCase) {
			if (!laCase.isEstBombe()&& laCase.getEtatCase()==0) {
				return false;
			}
		}
		return true;
	}
    
}
