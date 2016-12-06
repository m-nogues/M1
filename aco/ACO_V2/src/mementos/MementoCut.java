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
import recordables.CutRecordable;

/**
 * MementoCut is to store the CutRecordable commands.
 *
 * @see CutRecordable
 * @see MementoCommand
 */
public final class MementoCut extends MementoCommand {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(MementoCut.class.getName());

	/**
	 * Instantiates a new memento cut.
	 *
	 * @param engine
	 *            the engine
	 * @param recorder
	 *            the recorder
	 */
	public MementoCut(EditionEngine engine, Recorder recorder) {
		super(engine, recorder);

		LOGGER.trace("MementoCut created");
	}
}