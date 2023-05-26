/*
 * jeu.java                                    16 mai 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.jeu;

import java.util.Scanner;

/** TODO comment class responsibility (SRP)
 * @author tany.catalabailly
 *
 */
public class jeu {
    
    /**
     * le main
     * @param args
     */
    public static void main (String[] args) {
        
        Scanner entree = new Scanner(System.in);
        
        int hauteur;           // rayon du cercle entré par l'utilisateur
        int longueur;  

        // saisie du rayon du cercle
        System.out.print("Donnez la hauteur du labyrinthe : ");
        hauteur = entree.nextInt();
        System.out.print("Donnez la longueur du labyrinthe : ");
        longueur = entree.nextInt();
        
        Grille GrilleTest = new Grille(hauteur,longueur);
        int nbCase=0;
        System.out.println(GrilleTest.getTaille());
        for (int i = 0; i<hauteur;i++) {
            
            for (int j = 0; j<longueur; j++) {
                if (GrilleTest.getListeCase().get(nbCase).isEstBombe()) {
                    System.out.print("X");
                }else {
                    System.out.print("O");
                }
                nbCase++;
            }
            System.out.print("\n");
        }
    }
}
