/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.Selection;

/**
 * MementoSelection is to store the state of a selection at a time.
 *
 * @see Selection
 */
public class MementoSelection {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(MementoSelection.class.getName());

	/** The start. */
	private int start;

	/** The end. */
	private int end;

	/**
	 * Instantiates a new memento selection.
	 *
	 * @param start
	 *            the start of the stored selection
	 * @param end
	 *            the end of the stored selection
	 */
	public MementoSelection(int start, int end) {
		/* Precondition */
		if (start < 0)
			throw new IllegalArgumentException("start < 0");
		if (start > end)
			throw new IllegalArgumentException("start > end");

		/* Treatment */
		this.start = start;
		this.end = end;

		LOGGER.trace("MementoSelection created");
	}

	/**
	 * Gets the end of the stored selection.
	 *
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Gets the start of the stored selection.
	 *
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Sets the end of the stored selection.
	 *
	 * @param end
	 *            the new end
	 */
	public void setEnd(int end) {
		/* Precondition */
		if (end < 0)
			throw new IllegalArgumentException("end < 0");
		if (start > end)
			throw new IllegalArgumentException("start > end");

		/* Treatment */
		this.end = end;
	}

	/**
	 * Sets the start of the stored selection.
	 *
	 * @param start
	 *            the new start
	 */
	public void setStart(int start) {
		/* Precondition */
		if (start < 0)
			throw new IllegalArgumentException("start < 0");
		if (start > end)
			throw new IllegalArgumentException("start > end");

		/* Treatment */
		this.start = start;
	}
}
