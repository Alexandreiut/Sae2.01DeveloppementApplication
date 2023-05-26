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
        if (!estBombe) {
            this.etatCase=1;
            this.listeVoisin.forEach(leVoisin -> {
                if (!leVoisin.isEstBombe()) {
                    leVoisin.setEtatCase(1);
                    if (leVoisin.getEtatCase()==0) {
                        leVoisin.decouvrir();
                    }
                }
            });
        }
    }


}
