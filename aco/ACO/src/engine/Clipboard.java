/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package engine;

/**
 * The clipboard saves a string and gives it on demand.
 */
public final class Clipboard {
	/** The content. */
	private String content;

	/**
	 * Instantiate a Clipboard with an empty content.
	 */
	public Clipboard() {
		content = "";
	}

	/**
	 * Get the content.
	 *
	 * @return the content
	 */
	public final String getContent() {
		return new String(content);
	}

	/**
	 * Checks if the clipboard is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return content == "";
	}

	/**
	 * Sets the content.
	 *
	 * @param s
	 *            the new content
	 */
	public final void setContent(String s) {
		/* Preconditions */
		if (s == null)
			throw new IllegalArgumentException("String is null");

		/* Treatment */
		// We create a new string to ensure the integrity of our data.
		content = new String(s);
	}
}