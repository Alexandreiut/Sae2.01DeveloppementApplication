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
        this.nbMine = 10;
        this.listeCase = new ArrayList<Case>();
        for (int i =0; i<hauteur*longueur;i++) {
            this.listeCase.add(new Case(i));
        }
        placeBombe();
        setNbBombeVoisine()
        
        
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
     * @param idCase 
     * @return le nombre de bombe parmie les voisin d'une case
     */
    public static int getNbBombeVoisine(int idCase) {
        return idCase;
        
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
    public void setNbBombeVoisine() {
        for (int placeCase = 0;placeCase<this.longueur*this.hauteur;placeCase++) {
            int nbBombeVoisine =0;
            if (!isPremiereLigne(placeCase)) {
                if (!isColoneGauche(placeCase)) {
                    
                } else if
            }
            this.listeCase.get(placeCase).setNbBombeVoisine(nbBombeVoisine);
        }
    }
    
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
        return placeCase + this.longueur > this.longueur*this.hauteur;
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
    
}
