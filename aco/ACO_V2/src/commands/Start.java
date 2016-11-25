package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Recorder;

/**
 * Cette commande est chargée d'ordonner à l'recorder de commande de commencer à
 * enregistrer les commandes qui lui sont destinées.
 *
 * @see Recorder
 */
public class Start implements Command {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(Start.class.getName());

	/** The recorder. */
	private Recorder recorder;

	/**
	 * Crée la commande.
	 *
	 * @param recorder
	 *            L'recorder à qui adresser la commande (non null)
	 */
	public Start(Recorder recorder) {

		if (recorder == null)
			throw new IllegalArgumentException("recorder est à null");

		this.recorder = recorder;
	}

	/**
	 * Ordonne à l'recorder d'arrêter de commencer à enregistrer les commandes
	 * qui lui sont adressées.
	 */
	@Override
	public void execute() {

		LOGGER.trace("Exécution d'une commande Start");
		recorder.activate();
	}
}
