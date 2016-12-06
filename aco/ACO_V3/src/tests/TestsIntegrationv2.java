/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import editor.Recorder;
import engine.ImplementedEngine;
import engine.Selection;
import recordables.CopyRecordable;
import recordables.CutRecordable;
import recordables.DelTextRecordable;
import recordables.InsTextRecordable;
import recordables.PasteRecordable;
import recordables.SelectRecordable;

/**
 * The Class TestsIntegrationv2.
 */
@RunWith(PowerMockRunner.class)
public class TestsIntegrationv2 {
	/** The engine. */
	private ImplementedEngine engine;

	/** The graphical user interface. */
	private GUITest gui;

	/** The recorder. */
	private Recorder recorder;

	/**
	 * Insert delete replay test.
	 */
	@Test
	public void insertDeleteReplay() {
		// Insert "Test", select 4 first char, cut, add "new" and paste
		new InsTextRecordable(engine, recorder, "Test").execute();
		recorder.activate();
		new SelectRecordable(engine, recorder, new Selection(0, 4)).execute();
		new CutRecordable(engine, recorder).execute();
		new InsTextRecordable(engine, recorder, "new").execute();
		new PasteRecordable(engine, recorder).execute();
		recorder.deactivate();
		assertEquals("newTest", gui.getLastInsert());

		// Reset and add a new text for replay
		new SelectRecordable(engine, recorder, new Selection(0, 8)).execute();
		new DelTextRecordable(engine, recorder).execute();
		new InsTextRecordable(engine, recorder, "2ndTest").execute();
		assertEquals("2ndTest", gui.getLastInsert());
		recorder.replayCommands();
		assertEquals("new2ndTest", gui.getLastInsert());

		// Copy of text and deletion of 2 last char
		recorder.activate();
		new SelectRecordable(engine, recorder, new Selection(0, 10)).execute();
		new CopyRecordable(engine, recorder).execute();
		new SelectRecordable(engine, recorder, new Selection(10, 10)).execute();
		new PasteRecordable(engine, recorder).execute();
		new DelTextRecordable(engine, recorder).execute();
		new DelTextRecordable(engine, recorder).execute();
		recorder.deactivate();
		assertEquals("new2ndTestnew2ndTe", gui.getLastInsert());

		// Reset and add a new text for replay
		new SelectRecordable(engine, recorder, new Selection(0, 18)).execute();
		new DelTextRecordable(engine, recorder).execute();
		assertEquals("", gui.getLastInsert());
		new InsTextRecordable(engine, recorder, "Hello").execute();
		recorder.replayCommands();
		assertEquals("HelloHel", gui.getLastInsert());
	}

	/**
	 * Sets up the test environment.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		engine = new ImplementedEngine();
		recorder = new Recorder();
		gui = new GUITest();
		engine.getBuffer().addObserver(gui);
	}
}
