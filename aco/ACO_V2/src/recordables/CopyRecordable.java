package recordables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.Copy;
import editor.Recorder;
import engine.EditionEngine;
import mementos.MementoCommand;
import mementos.MementoCopy;

/**
 * La classe CopyRecordable execute une commande Copier et enregistre son
 * MementoCommand dans un Recorder
 *
 * @see Recorder
 * @see Copier
 * @see CommandRecordable
 */
public final class CopyRecordable implements CommandRecordable {

	/**
	 * Logger pour suivre le déroulement de l'application
	 */
	private static final Logger LOGGER = LogManager.getLogger(CopyRecordable.class.getName());

	private Recorder		recorder;
	private EditionEngine	engine;

	/**
	 * Créé une commande CopyRecordable
	 * L'ensemble des paramètres doit être renseigné
	 *
	 * @param engine
	 *            Le EditionEngine auquel adresser la commande
	 * @param recorder
	 *            L'enregsitreur de commande
	 */
	public CopyRecordable(EditionEngine engine, Recorder recorder) {

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
	 * Créé une Command CopyRecordable à partir d'un MementoCopy et execute une
	 * commande Copier
	 *
	 * @param memento
	 *            Le memento duquel on restaure l'état de la commande
	 *            enregistrable
	 */
	public CopyRecordable(MementoCommand memento) {

		restore(memento);
		new Copy(engine).execute();
	}

	/**
	 * Effectue l'enregistrement de la commande auprès de l'recorder et execute
	 * la commande auprès du engine
	 */
	@Override
	public final void execute() {
		recorder.enregistrer(this);
		LOGGER.trace("Exécution d'une commande CopyRecordable");
		new Copy(engine).execute();
	}

	/**
	 * Retour l'état de l'objet sous forme d'un objet MementoCopy
	 *
	 * @see MementoCopy
	 */
	@Override
	public final MementoCommand getMemento() {

		return new MementoCopy(engine, recorder);
	}

	/**
	 * Restaure l'état d'une commande à partir d'un memento
	 *
	 * @param memento
	 *            L'objet memento de la classe MementoCopy (non null)
	 * @see MementoCopy
	 */
	@Override
	public final void restore(MementoCommand memento) {

		/* Preconditions */
		if (memento == null)
			throw new IllegalArgumentException("memento is null");

		if (!(memento instanceof MementoCopy))
			throw new IllegalArgumentException("Not a MementoCopy");

		LOGGER.trace("Restauration d'une commande CopyRecordable à partir d'un memento");

		/* Treatment */
		engine = memento.getEngine();
		recorder = memento.getRecorder();
	}
}
