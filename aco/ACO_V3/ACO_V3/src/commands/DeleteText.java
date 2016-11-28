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
 * The delete text command, asks the engine to execute the text deletion.
 */
public final class DeleteText implements Command {
	/** The Constant LOGGER. */
	private static final Logger	LOGGER	= LogManager.getLogger(DeleteText.class.getName());
	/** Engine that will execute the command. */
	private final EditionEngine	engine;

	/**
	 * Instantiate a new DeleteText that will execute a text deletion in the
	 * given engine.
	 *
	 * @param engine
	 *            the engine
	 */
	public DeleteText(EditionEngine engine) {
		/* Preconditions */
		if (engine == null)
			throw new IllegalArgumentException("Engine is null");
		/* Treatment */
		this.engine = engine;
	}

	/**
	 * Execute the command.
	 */
	@Override
	public final void execute() {
		LOGGER.trace("Executing delete text command");
		engine.deleteText();
	}
}