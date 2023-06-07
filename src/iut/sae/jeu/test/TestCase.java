package iut.sae.jeu.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import iut.sae.jeu.*;
class TestCase {
	
	Case test;
	Case test2;
	Case vide;
	@BeforeEach
	void setUp() {
		test = new Case(1);
		test2 = new Case(2);
	}


	@Test
	void testIsEstBombe() {
		assertFalse(test.isEstBombe());
		test.setEstBombe(true);
		assertTrue(test.isEstBombe());
	
	}

	@Test
	void testGetListeVoisin() {
		List<Case> listeV;
		listeV = new ArrayList<Case>();
		listeV.add(test2);
		test.setListeVoisin(listeV);
		assertEquals(test.getListeVoisin().get(0),test2);
	}

	@Test
	void testSetListeVoisin() {
		List<Case> listeV;
		listeV = new ArrayList<Case>();
		listeV.add(test2);
		test.setListeVoisin(listeV);
		assertEquals(test.getListeVoisin().get(0),test2);
	}

	@Test
	void testSetEstBombe() {
		assertFalse(test.isEstBombe());
		test.setEstBombe(true);
		assertTrue(test.isEstBombe());
	}

	@Test
	void testGetNbBombeVoisine() {
		List<Case> listeV;
		listeV = new ArrayList<Case>();
		test2.setEstBombe(true);
		listeV.add(test2);
		test.setListeVoisin(listeV);
		test.checkBombeVoisine();
		assertEquals(test.getNbBombeVoisine(), 1);
		
		
		
	}

	@Test
	void testSetNbBombeVoisine() {
		test.setNbBombeVoisine(2);
		assertEquals(test.getNbBombeVoisine(),2);
		
	}

	@Test
	void testCheckBombeVoisine() {
		List<Case> listeV;
		listeV = new ArrayList<Case>();
		test2.setEstBombe(true);
		listeV.add(test2);
		test.setListeVoisin(listeV);
		test.checkBombeVoisine();
		assertEquals(test.getNbBombeVoisine(), 1);
	}

	@Test
	void testGetEtatCase() {
		assertEquals(test2.getEtatCase(),0);
		test2.setEtatCase(1);
		assertEquals(test2.getEtatCase(),1);
		
	}

	@Test
	void testSetEtatCase() {
		assertEquals(test2.getEtatCase(),0);
		test2.setEtatCase(1);
		assertEquals(test2.getEtatCase(),1);
	}

	@Test
	void testGetIdentifiant() {
		assertEquals(test2.getIdentifiant(),2);
		assertEquals(test.getIdentifiant(),1);
	}

	@Test
	void testDecouvrir() {
		Case caseVoisin = new Case(3);
		List<Case> listeV;
		listeV = new ArrayList<Case>();
		
		listeV.add(caseVoisin);
		test2.setListeVoisin(listeV);
		test2.decouvrir();
		assertEquals(test2.getEtatCase(),1);
		assertEquals(caseVoisin.getEtatCase(),1);
		test.setEstBombe(true);
		test.decouvrir();
	}


	@Test
	void testDecouvrirDoubleClic() {
		Case caseVoisin = new Case(3);
		List<Case> listeV;
		listeV = new ArrayList<Case>();
		
		listeV.add(caseVoisin);
		test2.setListeVoisin(listeV);
		test2.decouvrirDoubleClic();
		assertEquals(caseVoisin.getEtatCase(),1);
	}

	

	

	@Test
	void testDrapeau() {
		
		assertEquals(test.drapeau(),0);
		test2.setEstBombe(true);
		assertEquals(test2.drapeau(),-1);
	}



}
