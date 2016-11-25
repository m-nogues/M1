package engine;

/**
 * Le presse-papier stocke une chaîne de caractère et la restitue sur demande.
 */
public final class Clipboard {
	/** Le contenu du presse-papier. */
	private String contenu;

	/**
	 * Crée le presse-papier avec une chaîne vide en contenu.
	 */
	public Clipboard() {
		contenu = "";
	}

	/**
	 * Retourne le contenu du presse-papier.
	 *
	 * @return Le contenu du presse-papier
	 */
	public final String getContent() {
		return new String(contenu);
	}

	/**
	 * Vide.
	 *
	 * @return Vrai si le presse-papier est vide, faux sinon
	 */
	public boolean isEmpty() {
		return contenu.isEmpty();
	}

	/**
	 * Change le contenu du presse-papier.
	 *
	 * @param chaine
	 *            Le nouveau contenu du presse-papier
	 */
	public final void setContent(String chaine) {
		/* Préconditions */
		if (chaine == null)
			throw new IllegalArgumentException("Chaine est à null");
		// On crée une nouvelle instance de string pour assurer l'int�grit� de
		// nos données
		contenu = new String(chaine);
	}
}