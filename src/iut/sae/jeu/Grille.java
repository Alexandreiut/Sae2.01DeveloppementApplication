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
    
    
    
}
