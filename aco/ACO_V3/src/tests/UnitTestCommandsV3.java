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

@RunWith(PowerMockRunner.class)
@PrepareForTest(ImplementedEngine.class)
public class UnitTestCommandsV3 {

	private EditionEngine moteur;

	@Before
	public void setUp() {

		moteur = PowerMockito.mock(ImplementedEngine.class);
	}

	@Test
	public void testDefaire() {

		Undo cmd = new Undo(moteur);
		cmd.execute();
		Mockito.verify(moteur).undo();
	}

	@Test
	public void testRefaire() {

		Redo cmd = new Redo(moteur);
		cmd.execute();
		Mockito.verify(moteur).redo();
	}
}