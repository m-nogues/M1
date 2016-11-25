package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Recorder;
import engine.EditionEngine;
import recordables.CutRecordable;

/**
 * Cette classe est chargée de stocker l'état d'une commande CutRecordable.
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