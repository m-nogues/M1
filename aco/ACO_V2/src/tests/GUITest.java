/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package tests;

import editor.Observer;
import engine.Buffer;

/**
 * GUITest is to test if the changes that are tested elsewhere are made on the
 * GUI.
 */
public final class GUITest implements Observer {
	/** The last insert. */
	private String lastInsert;

	/**
	 * Instantiates a new GUI test.
	 */
	GUITest() {
		lastInsert = "";
	}

	/**
	 * Gets the last update from the Buffer.
	 *
	 * @return the last update from the Buffer
	 */
	public String getLastInsert() {
		return new String(lastInsert);
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observer#update(editor.Observable)
	 */
	@Override
	public void update(editor.Observable o) {
		/* Preconditions */
		if (o == null)
			throw new IllegalArgumentException("o is null");
		/* Treatment */
		if (o instanceof Buffer) {
			Buffer buffer = (Buffer) o;
			lastInsert = buffer.getContent();
		}
	}
}
