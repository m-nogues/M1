package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Recorder;
import engine.EditionEngine;
import recordables.DelTextRecordable;

/**
 * Cette classe est chargée de stocker l'état d'une commande DelTextRecordable.
 *
 * @see DelTextRecordable
 * @see MementoCommand
 */
public final class MementoDelText extends MementoCommand {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(MementoDelText.class.getName());

	/**
	 * Instantiates a new memento del text.
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