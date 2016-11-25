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
 * La commande copier ordonne à l'implémentation du engine d'édition d'effectuer
 * une opération de copie.
 */
public final class Copy implements Command {
	/** The Constant LOGGER. */
	private static final Logger	LOGGER	= LogManager
			.getLogger(Copy.class.getName());
	/** Moteur d'édition qui exécutera la commande. */
	private final EditionEngine	engine;

	/**
	 * Le constructeur a besoin de savoir à quel engine d'édition envoyer la
	 * commande.
	 *
	 * @param engine
	 *            Le Moteur d'édition auquel envoyer la commande (non null)
	 */
	public Copy(EditionEngine engine) {
		/* Préconditions */
		if (engine == null)
			throw new IllegalArgumentException("Engine is null");
		this.engine = engine;
	}

	/**
	 * Execute the command.
	 */
	@Override
	public final void execute() {
		LOGGER.trace("Copy command executed");
		engine.copy();
	}
}
