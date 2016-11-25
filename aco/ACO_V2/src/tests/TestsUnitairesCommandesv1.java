package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import commands.Coller;
import commands.Copier;
import commands.Couper;
import commands.InsererTexte;
import commands.Selectionner;
import commands.SupprimerTexte;
import engine.MoteurEdition;
import engine.MoteurImplementation;
import engine.Selection;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MoteurImplementation.class})
public class TestsUnitairesCommandesv1 {

	private static MoteurEdition moteur;

	public TestsUnitairesCommandesv1() {
		
		moteur = PowerMockito.mock(MoteurImplementation.class);
	}
	
	@Test
	public void testInsererTexte() {
		
		new InsererTexte(moteur, "Test").executer();
		Mockito.verify(moteur).insererTexte(Mockito.eq("Test"));
	}

	@Test
	public void testSelectionner() {
		
		final Selection sel = new Selection(0,4);
		new Selectionner(moteur, sel).executer();
		Mockito.verify(moteur).selectionner(Mockito.eq(sel));
	}
	
	@Test
	public void testSupprimerTexte() {
		
		new SupprimerTexte(moteur).executer();
		Mockito.verify(moteur).supprimerTexte();
	}
	
	@Test
	public void testCopier() {
		
		new Copier(moteur).executer();
		Mockito.verify(moteur).copier();
	}
	
	@Test
	public void testCouper() {

		new Couper(moteur).executer();
		Mockito.verify(moteur).couper();
	}
	
	@Test
	public void testColler() {
		
		new Coller(moteur).executer();
		Mockito.verify(moteur).coller();
	}
}
