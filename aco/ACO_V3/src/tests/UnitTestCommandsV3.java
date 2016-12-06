/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import commands.Redo;
import commands.Undo;
import engine.EditionEngine;
import engine.ImplementedEngine;

/**
 * The Class UnitTestCommandsV3.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ImplementedEngine.class)
public class UnitTestCommandsV3 {

	/** The engine. */
	private EditionEngine engine;

	/**
	 * Sets up the testing environment.
	 */
	@Before
	public void setUp() {
		engine = PowerMockito.mock(ImplementedEngine.class);
	}

	/**
	 * Test redo.
	 */
	@Test
	public void testRedo() {
		Redo cmd = new Redo(engine);
		cmd.execute();
		Mockito.verify(engine).redo();
	}

	/**
	 * Test undo.
	 */
	@Test
	public void testUndo() {
		Undo cmd = new Undo(engine);
		cmd.execute();
		Mockito.verify(engine).undo();
	}
}