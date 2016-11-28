/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import commands.Copy;
import commands.Cut;
import commands.DeleteText;
import commands.InsertText;
import commands.Paste;
import commands.Select;
import engine.EditionEngine;
import engine.ImplementedEngine;
import engine.Selection;

/**
 * The Class UnitTestCommandsV1.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ImplementedEngine.class })
public class UnitTestCommandsV1 {
	/** The engine. */
	private static EditionEngine engine;

	/**
	 * Instantiates a new unit test commands V 1.
	 */
	public UnitTestCommandsV1() {
		engine = PowerMockito.mock(ImplementedEngine.class);
	}

	/**
	 * Test copy.
	 */
	@Test
	public void testCopy() {
		new Copy(engine).execute();
		Mockito.verify(engine).copy();
	}

	/**
	 * Test cut.
	 */
	@Test
	public void testCut() {
		new Cut(engine).execute();
		Mockito.verify(engine).cut();
	}

	/**
	 * Test delete text.
	 */
	@Test
	public void testDeleteText() {
		new DeleteText(engine).execute();
		Mockito.verify(engine).deleteText();
	}

	/**
	 * Test insert text.
	 */
	@Test
	public void testInsertText() {
		new InsertText(engine, "Test").execute();
		Mockito.verify(engine).insertText(Matchers.eq("Test"));
	}

	/**
	 * Test paste.
	 */
	@Test
	public void testPaste() {
		new Paste(engine).execute();
		Mockito.verify(engine).paste();
	}

	/**
	 * Test select.
	 */
	@Test
	public void testSelect() {
		final Selection sel = new Selection(0, 4);
		new Select(engine, sel).execute();
		Mockito.verify(engine).select(Matchers.eq(sel));
	}
}
