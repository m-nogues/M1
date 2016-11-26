package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Recorder;
import engine.EditionEngine;
import recordables.CopyRecordable;

/**
 * Cette classe est chargée de stocker l'état d'une commande CopyRecordable.
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