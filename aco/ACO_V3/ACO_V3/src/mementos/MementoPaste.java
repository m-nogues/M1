/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Recorder;
import engine.EditionEngine;
import recordables.PasteRecordable;

/**
 * MementoPaste is to store the PasteRecordable commands.
 *
 * @see PasteRecordable
 * @see MementoCommand
 */
public final class MementoPaste extends MementoCommand {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(MementoPaste.class.getName());

	/**
	 * Instantiates a new memento paste.
	 *
	 * @param engine
	 *            the engine
	 * @param recorder
	 *            the recorder
	 */
	public MementoPaste(EditionEngine engine, Recorder recorder) {

		super(engine, recorder);
		LOGGER.trace("MementoPaste created");
	}
}