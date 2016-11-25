package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import editor.Enregistreur;
import engine.MoteurImplementation;
import engine.Selection;
import recordables.CollerEnregistrable;
import recordables.CopierEnregistrable;
import recordables.CouperEnregistrable;
import recordables.InsTexteEnregistrable;
import recordables.SelectionnerEnregistrable;
import recordables.SupTexteEnregistrable;

@RunWith(PowerMockRunner.class)
public class TestsIntegrationv2 {

	private MoteurImplementation moteur;
	private IHMTest ihm;
	private Enregistreur enregistreur;
	
	@Before
	public void setUp() throws Exception {
		
		moteur = new MoteurImplementation();
		enregistreur = new Enregistreur();
		ihm = new IHMTest();
		moteur.getBuffer().ajouterObservateur(ihm);
	}

	@Test
	public void insertionSuppressionRejeu() {

		//On réalise le premier jeu
		//Insertion de "Test", sélection des 4 premiers car., on coupe, on ajoute "nouv" et on colle à la suite
		new InsTexteEnregistrable(moteur, enregistreur, "Test").executer();
		enregistreur.activer();
		new SelectionnerEnregistrable(moteur, enregistreur, new Selection(0, 4)).executer();
		new CouperEnregistrable(moteur, enregistreur).executer();
		new InsTexteEnregistrable(moteur, enregistreur, "nouv").executer();
		new CollerEnregistrable(moteur, enregistreur).executer();
		enregistreur.desactiver();
		assertEquals("nouvTest", ihm.getDerniereInsert());
		
		//RàZ et ajout d'un nouveau texte pour le rejeu
		new SelectionnerEnregistrable(moteur, enregistreur, new Selection(0, 8)).executer();
		new SupTexteEnregistrable(moteur, enregistreur).executer();
		new InsTexteEnregistrable(moteur, enregistreur, "Youp").executer();
		assertEquals("Youp", ihm.getDerniereInsert());
		enregistreur.rejouerCommandes();
		assertEquals("nouvYoup", ihm.getDerniereInsert());
		
		//Copie du texte actuel et suppression des 2 derniers car.
		enregistreur.activer();
		new SelectionnerEnregistrable(moteur, enregistreur, new Selection(0, 8)).executer();
		new CopierEnregistrable(moteur, enregistreur).executer();
		new SelectionnerEnregistrable(moteur, enregistreur, new Selection(8, 8)).executer();
		new CollerEnregistrable(moteur, enregistreur).executer();
		new SupTexteEnregistrable(moteur, enregistreur).executer();
		new SupTexteEnregistrable(moteur, enregistreur).executer();
		enregistreur.desactiver();
		assertEquals("nouvYoupnouvYo", ihm.getDerniereInsert());
		
		//RàZ et ajout d'un nouveau texte pour le rejeu
		new SelectionnerEnregistrable(moteur, enregistreur, new Selection(0, 14)).executer();
		new SupTexteEnregistrable(moteur, enregistreur).executer();
		assertEquals("", ihm.getDerniereInsert());
		new InsTexteEnregistrable(moteur, enregistreur, "Hello").executer();
		enregistreur.rejouerCommandes();
		assertEquals("HelloHel", ihm.getDerniereInsert());
	}
}
