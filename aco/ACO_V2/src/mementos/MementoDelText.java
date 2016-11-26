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
import recordables.DelTextRecordable;

/**
 * MementoDelText is to store the DelTextRecordable command.
 *
 * @see DelTextRecordable
 * @see MementoCommand
 */
public final class MementoDelText extends MementoCommand {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(MementoDelText.class.getName());

	/**
	 * Instantiates a new memento text deletion.
	 *
	 * @param engine
	 *            the engine
	 * @param recorder
	 *            the recorder
	 */
	public MementoDelText(EditionEngine engine, Recorder recorder) {

		super(engine, recorder);
		LOGGER.trace("MementoDelText created");
	}
}