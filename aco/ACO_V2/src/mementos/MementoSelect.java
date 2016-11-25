package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Recorder;
import engine.EditionEngine;
import engine.Selection;
import recordables.SelectRecordable;

/**
 * Cette classe est chargée de stocker l'état d'une commande SelectRecordable.
 *
 * @see SelectRecordable
 * @see MementoCommand
 */
public final class MementoSelect extends MementoCommand {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(MementoSelect.class.getName());

	/** The selection. */
	private Selection selection;

	/**
	 * Instantiates a new memento select.
	 *
	 * @param engine
	 *            the engine
	 * @param recorder
	 *            the recorder
	 * @param selection
	 *            the selection
	 */
	public MementoSelect(EditionEngine engine, Recorder recorder, Selection selection) {

		super(engine, recorder);

		if (selection == null)
			throw new IllegalArgumentException("selection is null");

		this.selection = selection;
		LOGGER.trace("MementoSelect created");
	}

	/**
	 * Getter de l'attribut selection.
	 *
	 * @return L'attribut selection du memento
	 */
	public final Selection getSelection() {
		return selection;
	}

	/**
	 * Setter de l'attribut selection.
	 *
	 * @param selection
	 *            La nouvelle selection (non null)
	 */
	public final void setSelection(Selection selection) {

		if (selection == null)
			throw new IllegalArgumentException("selection is null");

		this.selection = selection;
	}
}