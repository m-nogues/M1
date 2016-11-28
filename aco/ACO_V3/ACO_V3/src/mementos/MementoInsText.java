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
import recordables.InsTextRecordable;

/**
 * MementoInsText is to store the InsTextRecordable commands.
 *
 * @see InsTextRecordable
 * @see MementoCommand
 */
public final class MementoInsText extends MementoCommand {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(MementoInsText.class.getName());

	/** The text. */
	private String text;

	/**
	 * Instantiates a new memento ins text.
	 *
	 * @param engine
	 *            the engine
	 * @param recorder
	 *            the recorder
	 * @param text
	 *            L'attribut text de la commande InsTextRecordable (non null)
	 * @see MementoCommand
	 * @see InsTextRecordable
	 */
	public MementoInsText(EditionEngine engine, Recorder recorder, String text) {

		super(engine, recorder);

		if (text == null)
			throw new IllegalArgumentException("text is null");

		this.text = text;

		LOGGER.trace("MementoInsText created");
	}

	/**
	 * Gets the stored text.
	 *
	 * @return the text
	 */
	public final String getText() {
		return new String(text); // To retain integrity
	}

	/**
	 * Sets the stored text.
	 *
	 * @param text
	 *            the new text
	 */
	public final void setText(String text) {

		if (text == null)
			throw new IllegalArgumentException("text is null");

		this.text = text;
	}
}