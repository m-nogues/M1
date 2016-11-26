/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Recorder;

/**
 * Cette commande est chargée d'ordonner à l'enregsitreur de commande de rejouer
 * les commandes qu'il a précédemment enregistré.
 *
 * @see Recorder
 */
public class Replay implements Command {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(Replay.class.getName());

	/** The recorder. */
	private Recorder recorder;

	/**
	 * Crée la commande.
	 *
	 * @param recorder
	 *            L'recorder à qui adresser la commande (non null)
	 */
	public Replay(Recorder recorder) {

		if (recorder == null)
			throw new IllegalArgumentException("recorder est à null");

		this.recorder = recorder;
	}

	/**
	 * Ordonne à l'recorder de rejouer les commandes qu'il a enregistré.
	 */
	@Override
	public void execute() {

		LOGGER.trace("Exécution d'une commande Replay");
		recorder.replayCommands();
	}
}