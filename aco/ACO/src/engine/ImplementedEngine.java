package engine;

/**
 * La classe ImplementedEngine représente une implémentation d'un engine
 * d'édition.
 *
 * @see EditionEngine
 */
public final class ImplementedEngine implements EditionEngine {
	/** The selection. */
	private final Selection	selection;
	/** The buffer. */
	private final Buffer	buffer;
	private final Clipboard	clipboard;

	/**
	 * Instancie l'ensemble des objets nécessaires à la mise en oeuvre d'un
	 * engine d'édition.
	 */
	public ImplementedEngine() {
		selection = new Selection(0, 0);
		buffer = new Buffer();
		clipboard = new Clipboard();
	}

	/**
	 * Copie dans le presse-papier le contenu du buffer spécifié par la
	 * sélection passée en paramètre.
	 */
	@Override
	public final void copy() {
		if (!selection.isEmpty())
			clipboard.setContent(buffer.getContent(selection));
	}

	/**
	 * Copie le contenu du buffer spécifié par la sélection dans le
	 * presse-papier et supprime celui-ci du buffer.
	 */
	@Override
	public final void cut() {
		if (!selection.isEmpty()) {
			clipboard.setContent(buffer.getContent(selection));
			buffer.deleteText(selection);
		}
	}

	/**
	 * Supprime le texte du buffer spécifié par la sélection si elle n'est pas
	 * vide, sinon supprime le dernier caractère du buffer.
	 */
	@Override
	public final void deleteText() {
		buffer.deleteText(selection);
	}

	/**
	 * Retourne le buffer du engine afin de mettre en place le pattern Observer.
	 *
	 * @return Le buffer du engine
	 */
	public final Buffer getBuffer() {
		return new Buffer(buffer);
	}

	/**
	 * Insère la chaîne passée en paramètre dans le buffer (et supprime la
	 * sélection si elle n'est pas vide).
	 *
	 * @param s
	 *            La chaîne à insérer (non null)
	 */
	@Override
	public final void insertText(final String s) {
		if (s == null)
			throw new IllegalArgumentException("String is null");
		buffer.addText(s, selection);
	}

	/**
	 * Colle le contenu du presse-papier dans le buffer à la suite de la
	 * sélection (ou en remplaçant celle-ci si elle n'est pas vide).
	 */
	@Override
	public final void paste() {
		if (!clipboard.isEmpty())
			buffer.addText(clipboard.getContent(), selection);
	}

	/**
	 * Met à jour la sélection du engine à partir d'une autre sélection.
	 *
	 * @param selection
	 *            La nouvelle sélection (non null)
	 */
	@Override
	public final void select(final Selection selection) {
		if (selection == null)
			throw new IllegalArgumentException("Selection is null");
		int start = selection.getStart();
		int end = selection.getEnd();
		// Added for recordable commands
		if (end > buffer.getMaxSelect())
			end = buffer.getMaxSelect();
		if (start > buffer.getMaxSelect())
			start = buffer.getMaxSelect();
		this.selection.setSelection(new Selection(start, end));
	}
}
