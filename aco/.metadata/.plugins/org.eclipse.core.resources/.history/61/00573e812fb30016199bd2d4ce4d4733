/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Maël Nogues mael.nogues@outlook.com
 */
package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.EditionEngine;

/**
 * La commande inserer texte ordonne au engine d'édition d'effectuer une
 * opération d'insertion de texte.
 */
public final class InsertText implements Command {
	/** The Constant LOGGER. */
	private static final Logger	LOGGER	= LogManager
			.getLogger(InsertText.class.getName());
	/** Moteur d'édition qui exécutera la commande. */
	private final EditionEngine	moteur;
	/** Chaîne de caractère à insérer. */
	private final String		chaine;

	/**
	 * Le constructeur a besoin de savoir à quel engine d'édition envoyer la
	 * commande et quelle chaîne insérer.
	 *
	 * @param moteur
	 *            the moteur
	 * @param chaine
	 *            La chaine de caractère à insérer (non null)
	 */
	public InsertText(EditionEngine moteur, String chaine) {
		/* Préconditions */
		if (moteur == null)
			throw new IllegalArgumentException("Moteur est à null");
		if (chaine == null)
			throw new IllegalArgumentException("Chaine est à null");
		/* Traitement */
		this.moteur = moteur;
		this.chaine = chaine;
	}

	/**
	 * Execute la commande.
	 */
	@Override
	public final void execute() {
		LOGGER.trace("Exécution d'une commande InsertText");
		moteur.insertText(chaine);
	}
}