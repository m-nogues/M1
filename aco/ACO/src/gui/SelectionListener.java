/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package gui;

import java.util.logging.LogManager;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import commands.Select;
import engine.EditionEngine;
import engine.Selection;

/**
 * This class monitors the selection in TextArea and ensures a synchronization
 * with the engine selection.
 */
public final class SelectionListener implements CaretListener {
	private static final Logger	LOGGER	= LogManager.getLogger(SelectionListener.class.getName());
	private boolean				active;
	/**
	 * Engine to perform commands
	 */
	private final EditionEngine	engine;

	/**
	 * Constructor need to know the edition engine to perform commands
	 *
	 * @param engine
	 *            Edition engine (not null)
	 */
	public SelectionListener(EditionEngine engine) {
		if (engine == null)
			throw new IllegalArgumentException("Null engine");
		this.engine = engine;
		active = true;
	}

	/**
	 * Invok when the selection changes in TextArea
	 *
	 * @param e
	 *            CaretEvent
	 */
	@Override
	public final void caretUpdate(CaretEvent e) {
		LOGGER.trace("Selection change detect");
		final int min = Math.min(e.getDot(), e.getMark());
		final int max = Math.max(e.getDot(), e.getMark());
		LOGGER.debug("New selection is : [" + min + ", " + max + "]");
		if (active)
			new Select(engine, new Selection(min, max)).execute();
	}

	/**
	 * Notice Filter if it must launch a command to the engine or not
	 *
	 * @param active
	 *            Boolean to notice
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}