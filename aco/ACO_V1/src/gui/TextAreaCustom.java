/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package gui;

import javax.swing.JTextArea;

import commands.Copy;
import commands.Cut;
import commands.Paste;
import engine.EditionEngine;

/**
 * This class inherits of the Swings' JtextArea to implement copy, paste and cut
 * actions.
 */
public class TextAreaCustom extends JTextArea {

	/** The Constant serialVersionUID. */
	static final long serialVersionUID = 8166387793964966707L;

	/** The engine. */
	private EditionEngine engine;

	/**
	 * Constructor for the text area.
	 *
	 * @param height
	 *            height of the text area
	 * @param width
	 *            width of the text area
	 * @param engine
	 *            engine to send the commands
	 */
	public TextAreaCustom(int height, int width, EditionEngine engine) {
		super(height, width);
		/* Precondition */
		if (engine == null)
			throw new IllegalAccessError("engine is null");

		/* Treatment */
		this.engine = engine;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.text.JTextComponent#copy()
	 */
	@Override
	/**
	 * Executes a command copy.
	 *
	 * @see Copy
	 */
	public void copy() {
		new Copy(engine).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.text.JTextComponent#cut()
	 */
	@Override
	/**
	 * Executes a command cut.
	 *
	 * @see Cut
	 */
	public void cut() {
		new Cut(engine).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.text.JTextComponent#paste()
	 */
	@Override
	/**
	 * Executes a command paste.
	 *
	 * @see Paste
	 */
	public void paste() {
		new Paste(engine).execute();
	}
}
