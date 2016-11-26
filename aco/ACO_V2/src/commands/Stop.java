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
 * Cette commande est chargée d'ordonner à l'recorder de commande d'arrêter
 * d'enregistrer les commandes qui lui sont destinées.
 *
 * @see Recorder
 */
public class Stop implements Command {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(Stop.class.getName());

	/** The recorder. */
	private Recorder recorder;

	/**
	 * Crée la commande.
	 *
	 * @param recorder
	 *            L'recorder à qui adresser la commande (non null)
	 */
	public Stop(Recorder recorder) {
		if (recorder == null)
			throw new IllegalArgumentException("recorder est à null");

		this.recorder = recorder;
	}

	/**
	 * Ordonne à l'recorder d'arrêter d'enregistrer les commandes qui lui
	 * sont adressées.
	 */
	@Override
	public void execute() {
		LOGGER.trace("On exécute une commande Arrêter");
		recorder.deactivate();
	}
}