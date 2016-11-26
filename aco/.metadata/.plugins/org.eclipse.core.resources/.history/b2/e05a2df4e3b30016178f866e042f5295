package recordables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.Paste;
import editor.Recorder;
import engine.EditionEngine;
import mementos.MementoCommand;
import mementos.MementoPaste;

/**
 * La classe PasteRecordable execute une commande Paste et enregistre son
 * MementoCommand dans un Recorder
 *
 * @see Recorder
 * @see Paste
 * @see CommandRecordable
 */
public final class PasteRecordable implements CommandRecordable {

	/**
	 * Logger pour suivre le déroulement de l'application
	 */
	private static final Logger LOGGER = LogManager.getLogger(PasteRecordable.class.getName());

	private Recorder		recorder;
	private EditionEngine	engine;

	/**
	 * Créé une commande PasteRecordable
	 * L'ensemble des paramètres doit être renseigné
	 *
	 * @param engine
	 *            Le EditionEngine auquel adresser la commande
	 * @param recorder
	 *            L'enregsitreur de commande
	 */
	public PasteRecordable(EditionEngine engine, Recorder recorder) {

		/* Preconditions */
		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");
		if (engine == null)
			throw new IllegalArgumentException("engine is null");

		/* Treatment */

		this.recorder = recorder;
		this.engine = engine;
	}

	/**
	 * Créé une Command PasteRecordable à partir d'un MementoPaste et execute
	 * une commande Paste
	 *
	 * @param memento
	 *            Le memento duquel on restaure l'état de la commande
	 *            enregistrable
	 */
	public PasteRecordable(MementoCommand memento) {

		restore(memento);
		LOGGER.trace("Rejeu d'une commande PasteRecordable");
		new Paste(engine).execute();
	}

	/**
	 * Effectue l'enregistrement de la commande auprès de l'recorder et execute
	 * la commande auprès du engine
	 */
	@Override
	public final void execute() {

		recorder.enregistrer(this);
		LOGGER.trace("Exécution d'une commande PasteRecordable");
		new Paste(engine).execute();
	}

	/**
	 * Retour l'état de l'objet sous forme d'un objet MementoPaste
	 *
	 * @see MementoPaste
	 */
	@Override
	public final MementoCommand getMemento() {

		return new MementoPaste(engine, recorder);
	}

	/**
	 * Restaure l'état d'une commande à partir d'un memento
	 *
	 * @param memento
	 *            L'objet memento de la classe MementoPaste (non null)
	 * @see MementoPaste
	 */
	@Override
	public final void restore(MementoCommand memento) {

		/* Preconditions */
		if (memento == null)
			throw new IllegalArgumentException("memento is null");

		if (!(memento instanceof MementoPaste))
			throw new IllegalArgumentException("Not a MementoPaste");

		LOGGER.trace("Restauration d'une commande PasteRecordable à partir d'un memento");

		/* Treatment */
		engine = memento.getEngine();
		recorder = memento.getRecorder();
	}
}
