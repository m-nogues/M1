package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import commands.Undo;
import commands.Redo;
import editor.Enregistreur;
import editor.GestionnaireHisto;
import engine.MoteurImplementation;
import recordables.InsTexteEnregistrable;

@RunWith(PowerMockRunner.class)
public class TestsIntegrationv3 {

	private MoteurImplementation moteur;
	private IHMTest ihm;
	private Enregistreur enregistreur;
	private GestionnaireHisto gestionnaire;
	
	@Before
	public void setUp() throws Exception {
		
		moteur = new MoteurImplementation();
		enregistreur = new Enregistreur();
		gestionnaire = new GestionnaireHisto();
		moteur.setHistorique(gestionnaire);
		ihm = new IHMTest();
		moteur.getBuffer().ajouterObservateur(ihm);
	}

	@Test
	public void test() {

		//On ajoute du texte et on revient en arrière
		new InsTexteEnregistrable(moteur, enregistreur, "Test").executer();
		new Undo(moteur).executer();
		assertEquals("", ihm.getDerniereInsert());
		
		//On revient à l'insertion
		new Redo(moteur).executer();
		assertEquals("Test", ihm.getDerniereInsert());
		
		//On tente de faire un Redo après une insertion, ce qui est impossible
		new Undo(moteur).executer();
		new InsTexteEnregistrable(moteur, enregistreur, "a").executer();
		new Redo(moteur).executer();
		assertEquals("a", ihm.getDerniereInsert());
	}
}
