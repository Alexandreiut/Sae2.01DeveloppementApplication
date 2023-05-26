/*
 * Case.java                                    19 avr. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.jeu;

import java.util.ArrayList;
import java.util.List;


/** 
 * La class Case est la classe qui va composer la grille
 * 
 * @version 1.0
 * 
 * @author Brouzes Alexandre
 * @author Catala Bailly Tany
 * @author Cluzel Enzo
 */
public class Case {
    
    private int identifiant;

    private boolean estBombe;
    
    private int nbBombeVoisine;
    
    private int etatCase;
    
    private List<Case> listeVoisin;
    
    /** 
     * Constructeur de la class voisin
     * 
     * @param identifiant 
     */
    public Case(int identifiant) {
        super();
        this.listeVoisin = new ArrayList<Case>();
        this.identifiant = identifiant;
    }

    /** @return valeur de estBombe */
    public boolean isEstBombe() {
        return estBombe;
    }

    /** @return valeur de listeVoisin */
    public List<Case> getListeVoisin() {
        return listeVoisin;
    }

    /** @param listeVoisin nouvelle valeur de listeVoisin */
    public void setListeVoisin(List<Case> listeVoisin) {
        this.listeVoisin = listeVoisin;
    }

    /** @param estBombe nouvelle valeur de estBombe */
    public void setEstBombe(boolean estBombe) {
        this.estBombe = estBombe;
    }

    /** @return valeur de nbBombeVoisine */
    public int getNbBombeVoisine() {
        return nbBombeVoisine;
    }
    
    /** @param nbBombeVoisine nouvelle valeur de nbBombeVoisine */
    public void setNbBombeVoisine(int nbBombeVoisine) {
        this.nbBombeVoisine = nbBombeVoisine;
    }

    /** @param nbBombeVoisine nouvelle valeur de nbBombeVoisine */
    public void checkBombeVoisine() {
        for (Case leVoisin : this.listeVoisin) {
            if (leVoisin.isEstBombe()) {
                this.setNbBombeVoisine(this.getNbBombeVoisine()+1);
            }
        }
    }

    /** @return valeur de etatCase */
    public int getEtatCase() {
        return etatCase;
    }

    /** @param etatCase nouvelle valeur de etatCase */
    public void setEtatCase(int etatCase) {
        this.etatCase = etatCase;
    }

    /** @return valeur de identifiant */
    public int getIdentifiant() {
        return identifiant;
    }
    
    /** TODO comment method role
     * 
     */
    public void decouvrir () {
        if (!(this.etatCase==2)) {
            this.etatCase=1;
            for(Case leVoisin : this.listeVoisin) {
                if (!leVoisin.estBombe && leVoisin.etatCase!=2) {
                    leVoisin.setEtatCase(1);
                    if (leVoisin.getEtatCase()==0 && 
                        leVoisin.getNbBombeVoisine()==0) {
                        leVoisin.decouvrir();
                    }
                } 
            }
        } else if (this.isEstBombe()) {
            gameOver();
        }
    }

    /** TODO comment method role
     * 
     */
    private void gameOver() {
        
        
    }
    
    /** TODO comment method role
     * 
     */
    public void changerEtatCase() {
        switch(this.etatCase) {
        case 0 :
            this.etatCase=2;
            break;
        case 2 :
            this.etatCase=3;
            break;
        case 3 :
            this.etatCase=0;
            break;
        default:
            break;
        }
    }
    
    
}
