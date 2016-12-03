/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.EditionEngine;

/**
 * Redo is to ask the engine to undo the last undo done (if possible).
 *
 * @see Undo
 */
public class Redo implements Command {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(Redo.class.getName());

	/** The engine. */
	private final EditionEngine engine;

	/**
	 * Instantiates a new redo.
	 *
	 * @param engine
	 *            the engine
	 */
	public Redo(EditionEngine engine) {
		/* Precondition */
		if (engine == null)
			throw new IllegalArgumentException("engine is null");

		/* Treatment */
		this.engine = engine;
	}

	/*
	 * (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public void execute() {
		LOGGER.trace("Executing command redo");
		engine.redo();
	}
}