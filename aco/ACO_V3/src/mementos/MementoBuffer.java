/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.Buffer;

/**
 * MementoBuffer is to store the state of the buffer at a time.
 *
 * @see Buffer
 */
public class MementoBuffer {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(MementoBuffer.class.getName());

	/** The content. */
	private StringBuffer content;

	/** The new offset. */
	private int newOffset;

	/**
	 * Instantiates a new memento buffer.
	 *
	 * @param content
	 *            the content
	 * @param newOffset
	 *            the new offset
	 */
	public MementoBuffer(StringBuffer content, int newOffset) {
		/* Precondition */
		if (content == null)
			throw new IllegalArgumentException("content is null");
		if (newOffset < 0)
			throw new IllegalArgumentException("newOffset < 0");

		/* Treatment */
		this.content = content;
		this.newOffset = newOffset;

		LOGGER.trace("MementoBuffer created");
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public StringBuffer getContent() {
		return new StringBuffer(content);
	}

	/**
	 * Gets the new offset.
	 *
	 * @return the new offset
	 */
	public int getNewOffset() {
		return newOffset;
	}

	/**
	 * Sets the content.
	 *
	 * @param content
	 *            the new content
	 */
	public void setContent(StringBuffer content) {
		/* Precondition */
		if (content == null)
			throw new IllegalArgumentException("content is null");

		/* Treatment */
		this.content = content;
	}

	/**
	 * Sets the new offset.
	 *
	 * @param newOffset
	 *            the new offset
	 */
	public void setNewOffset(int newOffset) {
		/* Precondition */
		if (newOffset < 0)
			throw new IllegalArgumentException("newOffset < 0");

		/* Treatment */
		this.newOffset = newOffset;
	}
}
