/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * MementoSystem is to store the necessary informations to restore the context
 * after a command when needed. It stores the buffers' state and the selection.
 *
 * @see MementoBuffer
 * @see MementoSelection
 */
public class MementoSystem {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(MementoSystem.class.getName());

	/** The memento buffer. */
	private MementoBuffer memBuffer;

	/** The memento selection. */
	private MementoSelection memSelection;

	/**
	 * Instantiates a new memento system.
	 *
	 * @param memBuffer
	 *            the memento buffer
	 * @param memSelection
	 *            the memento selection
	 */
	public MementoSystem(MementoBuffer memBuffer, MementoSelection memSelection) {
		/* Precondition */
		if (memBuffer == null)
			throw new IllegalArgumentException("memBuffer is null");
		if (memSelection == null)
			throw new IllegalArgumentException("memBuffer is null");

		/* Treatment */
		this.memBuffer = memBuffer;
		this.memSelection = memSelection;

		LOGGER.trace("MementoSystem created");
	}

	/**
	 * Gets the memento buffer.
	 *
	 * @return the memento buffer
	 */
	public MementoBuffer getMemBuffer() {
		return memBuffer;
	}

	/**
	 * Gets the memento selection.
	 *
	 * @return the memento selection
	 */
	public MementoSelection getMemSelection() {
		return memSelection;
	}

	/**
	 * Sets the memento buffer.
	 *
	 * @param memBuffer
	 *            the new memento buffer
	 */
	public void setMemBuffer(MementoBuffer memBuffer) {
		/* Precondition */
		if (memBuffer == null)
			throw new IllegalArgumentException("memBuffer is null");

		/* Treatment */
		this.memBuffer = memBuffer;
	}

	/**
	 * Sets the memento selection.
	 *
	 * @param memSelection
	 *            the new memento selection
	 */
	public void setMemSelection(MementoSelection memSelection) {
		/* Precondition */
		if (memSelection == null)
			throw new IllegalArgumentException("memBuffer is null");

		/* Treatment */
		this.memSelection = memSelection;
	}
}
