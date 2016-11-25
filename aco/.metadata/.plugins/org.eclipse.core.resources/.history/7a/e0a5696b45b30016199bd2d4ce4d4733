package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import editor.Enregistreur;
import engine.MoteurEdition;
import engine.MoteurImplementation;
import engine.Selection;
import recordables.CollerEnregistrable;
import recordables.CopierEnregistrable;
import recordables.CouperEnregistrable;
import recordables.InsTexteEnregistrable;
import recordables.SelectionnerEnregistrable;
import recordables.SupTexteEnregistrable;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MoteurImplementation.class, Enregistreur.class})
public class TestsUnitairesCommandesv2 {

	private static MoteurEdition moteur;
	private static Enregistreur enregistreur;
	
	public TestsUnitairesCommandesv2() {
		
		moteur = PowerMockito.mock(MoteurImplementation.class);
		enregistreur = PowerMockito.mock(Enregistreur.class);
	}
	
	@Test
	public void testInsererTexteEnregistrable() {
		
		InsTexteEnregistrable cmd = new InsTexteEnregistrable(moteur, enregistreur, "Test");
		cmd.executer();
		Mockito.verify(moteur).insererTexte(Mockito.eq("Test"));
		Mockito.verify(enregistreur).enregistrer(Mockito.eq(cmd));
	}

	@Test
	public void testSelectionnerEnregistrable() {
		
		SelectionnerEnregistrable cmd = new SelectionnerEnregistrable(moteur, enregistreur, new Selection(0, 4));
		cmd.executer();
		Mockito.verify(enregistreur).enregistrer(Mockito.eq(cmd));
	}
	
	@Test
	public void testSupprimerTexteEnregistrable() {
		
		SupTexteEnregistrable cmd = new SupTexteEnregistrable(moteur, enregistreur);
		cmd.executer();
		Mockito.verify(enregistreur).enregistrer(Mockito.eq(cmd));
	}
	
	@Test
	public void testCopierEnregistrable() {
		
		CopierEnregistrable cmd = new CopierEnregistrable(moteur, enregistreur);
		cmd.executer();
		Mockito.verify(enregistreur).enregistrer(Mockito.eq(cmd));
	}
	
	@Test
	public void testCouperEnregistrable() {

		CouperEnregistrable cmd = new CouperEnregistrable(moteur, enregistreur);
		cmd.executer();
		Mockito.verify(enregistreur).enregistrer(Mockito.eq(cmd));
	}
	
	@Test
	public void testCollerEnregistrable() {
		
		CollerEnregistrable cmd = new CollerEnregistrable(moteur, enregistreur);
		cmd.executer();
		Mockito.verify(enregistreur).enregistrer(Mockito.eq(cmd));
	}

}
