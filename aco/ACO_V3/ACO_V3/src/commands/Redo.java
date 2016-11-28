package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.EditionEngine;

/**
 * Cette commande est chargée d'annuler une commande Undo précédemment
 * exécutée
 *
 * @see Undo
 */
public class Redo implements Commande {

	private static final Logger LOGGER = LogManager.getLogger(Redo.class.getName());

	private final EditionEngine engine;

	/**
	 * Crée la commande
	 *
	 * @param engine
	 *            Le engine d'édition auquel adresser la commande (non-null)
	 */
	public Redo(EditionEngine engine) {
		/* Precondition */
		if (engine == null)
			throw new IllegalArgumentException("engine est à null");

		/* Treatment */
		this.engine = engine;
	}

	@Override
	public void executer() {
		LOGGER.trace("Exécution d'une commande Redo");
		engine.redo();
	}
}