package recordables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.DeleteText;
import editor.Recorder;
import engine.EditionEngine;
import mementos.MementoCommand;
import mementos.MementoDelText;

/**
 * La classe DelTextRecordable execute une commande SupprimerTexte et enregistre
 * son MementoCommand dans un Recorder
 *
 * @see Recorder
 * @see SupprimerTexte
 * @see CommandRecordable
 */
public final class DelTextRecordable implements CommandRecordable {

	/**
	 * Logger pour suivre le déroulement de l'application
	 */
	private static final Logger LOGGER = LogManager.getLogger(DelTextRecordable.class.getName());

	private Recorder		recorder;
	private EditionEngine	engine;

	/**
	 * Créé une commande DelTextRecordable
	 * L'ensemble des paramètres doit être renseigné
	 *
	 * @param engine
	 *            Le EditionEngine auquel adresser la commande
	 * @param recorder
	 *            L'enregsitreur de commande
	 */
	public DelTextRecordable(EditionEngine engine, Recorder recorder) {

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
	 * Créé une Command DelTextRecordable à partir d'un MementoDelText et
	 * execute une commande SupprimerTexte
	 *
	 * @param memento
	 *            Le memento duquel on restaure l'état de la commande
	 *            enregistrable
	 */
	public DelTextRecordable(MementoCommand memento) {

		restore(memento);
		new DeleteText(engine).execute();
	}

	/**
	 * Effectue l'enregistrement de la commande auprès de l'recorder et execute
	 * la commande auprès du engine
	 */
	@Override
	public final void execute() {

		recorder.enregistrer(this);
		LOGGER.trace("Exécution d'une commande SupprimerTexte");
		new DeleteText(engine).execute();
	}

	/**
	 * Retour l'état de l'objet sous forme d'un objet MementoDelText
	 *
	 * @see MementoDelText
	 */
	@Override
	public final MementoCommand getMemento() {

		return new MementoDelText(engine, recorder);
	}

	/**
	 * Restaure l'état d'une commande à partir d'un memento
	 *
	 * @param memento
	 *            L'objet memento de la classe MementoDelText (non null)
	 * @see MementoDelText
	 */
	@Override
	public final void restore(MementoCommand memento) {

		/* Preconditions */
		if (memento == null)
			throw new IllegalArgumentException("memento is null");

		if (!(memento instanceof MementoDelText))
			throw new IllegalArgumentException("Not a MementoDelText");

		LOGGER.trace("Restauration d'une commande DelTextRecordable à partir d'un memento");

		/* Treatment */
		engine = memento.getEngine();
		recorder = memento.getRecorder();
	}
}
