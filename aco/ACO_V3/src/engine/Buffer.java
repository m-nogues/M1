/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Observable;
import editor.Observer;
import mementos.MementoBuffer;

/**
 * The buffer contains the text to display and the offset of the selection made
 * by the user. It changes with the action of the user.
 */
public final class Buffer implements Observable {
	/** The Constant LOGGER. */
	private static final Logger		LOGGER	= LogManager.getLogger(Buffer.class.getName());
	/** The list of observers on this buffer. */
	private final List<Observer>	listObservers;

	/** The content. */
	private StringBuffer	content;
	/** The new offset of the cursor after an action. */
	private int				newOffset;

	/**
	 * Instantiates a new buffer.
	 */
	public Buffer() {
		listObservers = new ArrayList<>();
		content = new StringBuffer();
		newOffset = 0;
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observable#addObserver(editor.Observer)
	 */
	@Override
	public final void addObserver(final Observer o) {
		if (o == null)
			throw new IllegalArgumentException("Selection is null");
		if (listObservers.contains(o))
			throw new IllegalArgumentException("Observer already in the list");
		listObservers.add(o);
	}

	/**
	 * Add text to this buffer according to the given selection.
	 *
	 * @param s
	 *            the string to insert (not null)
	 * @param select
	 *            the selection (not null)
	 */
	public final void addText(final String s, final Selection select) {
		LOGGER.trace("Method addText");
		/* Preconditions */
		if (s == null)
			throw new IllegalArgumentException("String is null");
		if (select == null)
			throw new IllegalArgumentException("Selection is null");
		/* Treatment */
		// If select is not empty, delete select before insertion
		if (!select.isEmpty())
			deleteText(select);
		newOffset = select.getStart() + s.length();
		content.insert(select.getStart(), s);
		select.setSelection(newOffset, newOffset);
		notifyObservers();
		LOGGER.trace("End of addText");
	}

	/**
	 * Delete the text designated by the given selection. Delete the the
	 * character before if the selection is null.
	 *
	 * @param select
	 *            the selection (not null)
	 */
	public final void deleteText(final Selection select) {
		LOGGER.trace("Method deleteText");
		/* Preconditions */
		if (select == null)
			throw new IllegalArgumentException("Selection is null");
		/* Treatment */
		// If select is empty and we can delete the character before
		if (select.isEmpty() && select.getStart() != 0)
			select.setSelection(select.getStart() - 1, select.getStart());
		content.delete(select.getStart(), select.getEnd());
		select.flush();
		newOffset = select.getStart();
		notifyObservers();
		LOGGER.trace("End of deleteText");
	}

	/**
	 * Get the content as a string.
	 *
	 * @return the buffers' content
	 */
	public final String getContent() {
		return content.toString();
	}

	/**
	 * Get the content of this buffer designated by the given selection.
	 *
	 * @param select
	 *            the selection (not null)
	 * @return the substring of the content designated by the selection
	 */
	public final String getContent(final Selection select) {
		if (select == null)
			throw new IllegalArgumentException("Selection is null");
		return content.substring(select.getStart(), select.getEnd());
	}

	/**
	 * Gives the upper bound of the selection for this buffer.
	 *
	 * @return the upper bound of the selection
	 */
	public int getMaxSelect() {
		return content.length();
	}

	/**
	 * Gets the memento.
	 *
	 * @return the memento
	 */
	public MementoBuffer getMemento() {
		return new MementoBuffer(new StringBuffer(content), newOffset);
	}

	/**
	 * Get the new offset of the cursor after the last action.
	 *
	 * @return the new offset
	 */
	public final int getNewOffset() {
		return newOffset;
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observable#notifyObservers()
	 */
	@Override
	public final void notifyObservers() {
		for (Observer o : listObservers)
			o.update(this);
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observable#removeObserver(editor.Observer)
	 */
	@Override
	public final void removeObserver(final Observer o) {
		if (o == null)
			throw new IllegalArgumentException("Selection is null");
		if (!listObservers.contains(o))
			throw new IllegalArgumentException("o is not in  listObservers");
		listObservers.remove(o);
	}

	/**
	 * Restore.
	 *
	 * @param memento
	 *            the memento
	 */
	public void restore(MementoBuffer memento) {
		/* Precondition */
		if (memento == null)
			throw new IllegalArgumentException("Content is null");

		/* Treatment */
		content = memento.getContenu();
		newOffset = memento.getOffModif();

		notifyObservers();
	}
}