package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import editor.Recorder;
import engine.MoteurEdition;
import engine.MoteurImplementation;
import engine.Selection;
import recordables.PasteRecordable;
import recordables.CopyRecordable;
import recordables.CutRecordable;
import recordables.InsTextRecordable;
import recordables.SelectRecordable;
import recordables.DelTextRecordable;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MoteurImplementation.class, Recorder.class})
public class TestsUnitairesCommandesv2 {

	private static MoteurEdition moteur;
	private static Recorder recorder;
	
	public TestsUnitairesCommandesv2() {
		
		moteur = PowerMockito.mock(MoteurImplementation.class);
		recorder = PowerMockito.mock(Recorder.class);
	}
	
	@Test
	public void testInsererTexteEnregistrable() {
		
		InsTextRecordable cmd = new InsTextRecordable(moteur, recorder, "Test");
		cmd.execute();
		Mockito.verify(moteur).insererTexte(Mockito.eq("Test"));
		Mockito.verify(recorder).enregistrer(Mockito.eq(cmd));
	}

	@Test
	public void testSelectionnerEnregistrable() {
		
		SelectRecordable cmd = new SelectRecordable(moteur, recorder, new Selection(0, 4));
		cmd.execute();
		Mockito.verify(recorder).enregistrer(Mockito.eq(cmd));
	}
	
	@Test
	public void testSupprimerTexteEnregistrable() {
		
		DelTextRecordable cmd = new DelTextRecordable(moteur, recorder);
		cmd.executer();
		Mockito.verify(recorder).enregistrer(Mockito.eq(cmd));
	}
	
	@Test
	public void testCopierEnregistrable() {
		
		CopyRecordable cmd = new CopyRecordable(moteur, recorder);
		cmd.executer();
		Mockito.verify(recorder).enregistrer(Mockito.eq(cmd));
	}
	
	@Test
	public void testCouperEnregistrable() {

		CutRecordable cmd = new CutRecordable(moteur, recorder);
		cmd.executer();
		Mockito.verify(recorder).enregistrer(Mockito.eq(cmd));
	}
	
	@Test
	public void testCollerEnregistrable() {
		
		PasteRecordable cmd = new PasteRecordable(moteur, recorder);
		cmd.execute();
		Mockito.verify(recorder).enregistrer(Mockito.eq(cmd));
	}

}
