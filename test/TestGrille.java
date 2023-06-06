package iut.sae.jeu.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.sae.jeu.Case;
import iut.sae.jeu.Grille;

class TestGrille {
	Grille grandeGrille;
	Grille petiteGrille;
	Grille videGrille;
	@BeforeEach
	void setUp() throws Exception {
		grandeGrille = new Grille(10,10,2);
		petiteGrille = new Grille (5,5,2);
		videGrille = new Grille (0,0,0);
	}

	@Test
	void testGetHauteur() {
		assertEquals(grandeGrille.getHauteur(), 10);
		assertEquals(petiteGrille.getHauteur(), 5);
		assertEquals(videGrille.getHauteur(), 5);
	
	}

	@Test
	void testGetLongueur() {
		assertEquals(grandeGrille.getLongueur(), 10);
		assertEquals(petiteGrille.getLongueur(), 5);
		assertEquals(videGrille.getLongueur(), 5);
	}

	@Test
	void testGetListeCase() {
		assertEquals(grandeGrille.getListeCase().get(0).getIdentifiant(), 0);
	}

	@Test
	void testGetTaille() {
		assertEquals(grandeGrille.getTaille(),100);
	}

	@Test
	void testCreateListeVoisin() {
		grandeGrille.createListeVoisin();
		assertEquals(grandeGrille.getListeCase().get(0).getIdentifiant(),grandeGrille.getListeCase().get(1).getListeVoisin().get(1).getIdentifiant());
	}

	@Test
	void testNombreBombe() {
		grandeGrille.placeBombe();
		assertEquals(grandeGrille.nombreBombe(),2);
	}

	@Test
	void testPlaceBombe() {
		grandeGrille.placeBombe();
		assertEquals(grandeGrille.nombreBombe(),2);
	}

	@Test
	void testIsPremiereLigne() {
		assertTrue(grandeGrille.isPremiereLigne(2));
		assertFalse(grandeGrille.isPremiereLigne(11));
	}

	@Test
	void testIsDerniereLigne() {
		assertTrue(grandeGrille.isDerniereLigne(99));
		assertFalse(grandeGrille.isDerniereLigne(11));
	}

	@Test
	void testIsColoneGauche() {
		assertTrue(grandeGrille.isColoneGauche(0));
		assertFalse(grandeGrille.isColoneGauche(15));
	}

	@Test
	void testIsColoneDroite() {
		assertTrue(grandeGrille.isColoneDroite(9));
		assertFalse(grandeGrille.isColoneGauche(21));
	
	}

	@Test
	void testVictoire() {
		int i=0;
		for (Case element : grandeGrille.getListeCase()) {
			element.setEtatCase(1);
			i++;
			
		}
		assertFalse(petiteGrille.victoire());
		assertTrue(grandeGrille.victoire());
		grandeGrille.getListeCase().get(0).setEstBombe(true);
		grandeGrille.getListeCase().get(2).setEtatCase(0);
		assertFalse(grandeGrille.victoire());
	}

	@Test
	void testDefaite() {
		assertFalse(grandeGrille.getListeCase().get(0).gameOver());
		grandeGrille.defaite();
		assertTrue(grandeGrille.getListeCase().get(0).gameOver());
		
	}

}
