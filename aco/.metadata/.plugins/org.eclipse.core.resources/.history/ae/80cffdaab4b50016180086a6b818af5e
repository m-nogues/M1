/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package editor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.ImplementedEngine;
import gui.GUI;

/**
 * This class is the application starter. It creates and link all necessary
 * objects to launch the editor with the graphical user interface.
 */
public final class EditorClient {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(EditorClient.class.getName());

	/**
	 * The main method. Launches the application
	 *
	 * @param args
	 *            the arguments (not used)
	 */
	public static void main(String[] args) {
		final ImplementedEngine engine = new ImplementedEngine();
		final Recorder recorder = new Recorder();
		final GUI gui = new GUI(engine, recorder);
		engine.getBuffer().addObserver(gui);
		LOGGER.trace("Application launched and ready to use");
	}
}
