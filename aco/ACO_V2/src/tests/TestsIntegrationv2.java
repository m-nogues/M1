package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import editor.Recorder;
import engine.MoteurImplementation;
import engine.Selection;
import recordables.PasteRecordable;
import recordables.CopyRecordable;
import recordables.CutRecordable;
import recordables.InsTextRecordable;
import recordables.SelectRecordable;
import recordables.DelTextRecordable;

@RunWith(PowerMockRunner.class)
public class TestsIntegrationv2 {

	private MoteurImplementation moteur;
	private IHMTest ihm;
	private Recorder recorder;
	
	@Before
	public void setUp() throws Exception {
		
		moteur = new MoteurImplementation();
		recorder = new Recorder();
		ihm = new IHMTest();
		moteur.getBuffer().ajouterObservateur(ihm);
	}

	@Test
	public void insertionSuppressionRejeu() {

		//On réalise le premier jeu
		//Insertion de "Test", sélection des 4 premiers car., on coupe, on ajoute "nouv" et on colle à la suite
		new InsTextRecordable(moteur, recorder, "Test").execute();
		recorder.activate();
		new SelectRecordable(moteur, recorder, new Selection(0, 4)).execute();
		new CutRecordable(moteur, recorder).executer();
		new InsTextRecordable(moteur, recorder, "nouv").execute();
		new PasteRecordable(moteur, recorder).execute();
		recorder.deactivate();
		assertEquals("nouvTest", ihm.getDerniereInsert());
		
		//RàZ et ajout d'un nouveau texte pour le rejeu
		new SelectRecordable(moteur, recorder, new Selection(0, 8)).execute();
		new DelTextRecordable(moteur, recorder).executer();
		new InsTextRecordable(moteur, recorder, "Youp").execute();
		assertEquals("Youp", ihm.getDerniereInsert());
		recorder.replayCommands();
		assertEquals("nouvYoup", ihm.getDerniereInsert());
		
		//Copie du texte actuel et suppression des 2 derniers car.
		recorder.activate();
		new SelectRecordable(moteur, recorder, new Selection(0, 8)).execute();
		new CopyRecordable(moteur, recorder).executer();
		new SelectRecordable(moteur, recorder, new Selection(8, 8)).execute();
		new PasteRecordable(moteur, recorder).execute();
		new DelTextRecordable(moteur, recorder).executer();
		new DelTextRecordable(moteur, recorder).executer();
		recorder.deactivate();
		assertEquals("nouvYoupnouvYo", ihm.getDerniereInsert());
		
		//RàZ et ajout d'un nouveau texte pour le rejeu
		new SelectRecordable(moteur, recorder, new Selection(0, 14)).execute();
		new DelTextRecordable(moteur, recorder).executer();
		assertEquals("", ihm.getDerniereInsert());
		new InsTextRecordable(moteur, recorder, "Hello").execute();
		recorder.replayCommands();
		assertEquals("HelloHel", ihm.getDerniereInsert());
	}
}
