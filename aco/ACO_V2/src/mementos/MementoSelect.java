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
import engine.Selection;
import recordables.SelectRecordable;

/**
 * MementoSelect is to store the SelectRecordable commands.
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
		/* Precondition */
		if (selection == null)
			throw new IllegalArgumentException("selection is null");

		/* Treatment */
		this.selection = selection;

		LOGGER.trace("MementoSelect created");
	}

	/**
	 * Gets the selection.
	 *
	 * @return the selection
	 */
	public final Selection getSelection() {
		return selection;
	}

	/**
	 * Sets the selection.
	 *
	 * @param selection
	 *            the new selection (not null)
	 */
	public final void setSelection(Selection selection) {
		/* Precondition */
		if (selection == null)
			throw new IllegalArgumentException("selection is null");

		/* Treatment */
		this.selection = selection;
	}
}