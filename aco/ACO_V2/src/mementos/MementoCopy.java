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
import recordables.CopyRecordable;

/**
 * MementoDelText is to store the CopyRecordable commands.
 *
 * @see CopyRecordable
 * @see MementoCommand
 */
public final class MementoCopy extends MementoCommand {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(MementoCopy.class.getName());

	/**
	 * Instantiates a new memento copy.
	 *
	 * @param engine
	 *            the engine
	 * @param recorder
	 *            the recorder
	 */
	public MementoCopy(EditionEngine engine, Recorder recorder) {
		super(engine, recorder);

		LOGGER.trace("MementoCopy created");
	}
}