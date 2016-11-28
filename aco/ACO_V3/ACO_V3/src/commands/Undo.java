package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.EditionEngine;

/**
 * La commande défaire sert à indiquer au engine d'édition qu'on souhaite
 * rétablir l'éditeur à l'état précédent (si possible)
 */
public class Undo implements Commande {

	private static final Logger LOGGER = LogManager.getLogger(Undo.class.getName());

	private final EditionEngine engine;

	/**
	 * Crée une commande Undo
	 *
	 * @param engine
	 *            Le engine d'édition auquel adresser la commande (non-null)
	 */
	public Undo(EditionEngine engine) {
		/* Precondition */
		if (engine == null)
			throw new IllegalArgumentException("engine est à null");

		/* Treatment */
		this.engine = engine;
	}

	@Override
	public void executer() {
		LOGGER.trace("Exécution d'une commande Défaire");
		engine.undo();
	}
}