package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Recorder;
import engine.EditionEngine;
import recordables.InsTextRecordable;

/**
 * Cette classe est chargée de stocker l'état d'une commande InsTextRecordable.
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
	 * Crée le memento à partir des attributs de la commande enregsitrable.
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
	 * Retourne l'attribut text du memento.
	 *
	 * @return L'attribut text du memento
	 */
	public final String getText() {
		return new String(text); // To retain integrity
	}

	/**
	 * Setter de l'attribut text de cette classe.
	 *
	 * @param text
	 *            Le nouvel attribut text (non-null)
	 */
	public final void setText(String text) {

		if (text == null)
			throw new IllegalArgumentException("text is null");

		this.text = text;
	}
}