package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Recorder;
import engine.EditionEngine;
import recordables.PasteRecordable;

/**
 * Cette classe est chargée de stocker l'état d'une commande PasteRecordable.
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