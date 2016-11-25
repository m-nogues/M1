package recordables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.Cut;
import editor.Recorder;
import engine.EditionEngine;
import mementos.MementoCommand;
import mementos.MementoCut;

/**
 * La classe CutRecordable execute une commande Couper et enregistre son
 * MementoCommand dans un Recorder
 *
 * @see Recorder
 * @see Couper
 * @see CommandRecordable
 */
public final class CutRecordable implements CommandRecordable {

	/**
	 * Logger pour suivre le déroulement de l'application
	 */
	private static final Logger LOGGER = LogManager.getLogger(CutRecordable.class.getName());

	private Recorder		recorder;
	private EditionEngine	engine;

	/**
	 * Créé une commande CutRecordable
	 * L'ensemble des paramètres doit être renseigné
	 *
	 * @param engine
	 *            Le EditionEngine auquel adresser la commande
	 * @param recorder
	 *            L'enregsitreur de commande
	 */
	public CutRecordable(EditionEngine engine, Recorder recorder) {

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
	 * Créé une Command CutRecordable à partir d'un MementoCut et execute une
	 * commande Couper
	 *
	 * @param memento
	 *            Le memento duquel on restaure l'état de la commande
	 *            enregistrable
	 */
	public CutRecordable(MementoCommand memento) {

		restore(memento);
		new Cut(engine).execute();
	}

	/**
	 * Effectue l'enregistrement de la commande auprès de l'recorder et execute
	 * la commande auprès du engine
	 */
	@Override
	public final void execute() {
		recorder.enregistrer(this);
		LOGGER.trace("Exécution d'une commande CutRecordable");
		new Cut(engine).execute();
	}

	/**
	 * Retour l'état de l'objet sous forme d'un objet MementoCut
	 *
	 * @see MementoCut
	 */
	@Override
	public final MementoCommand getMemento() {

		return new MementoCut(engine, recorder);
	}

	/**
	 * Restaure l'état d'une commande à partir d'un memento
	 *
	 * @param memento
	 *            L'objet memento de la classe MementoCut (non null)
	 * @see MementoCut
	 */
	@Override
	public final void restore(MementoCommand memento) {

		/* Preconditions */
		if (memento == null)
			throw new IllegalArgumentException("memento is null");

		if (!(memento instanceof MementoCut))
			throw new IllegalArgumentException("Not a MementoCut");

		LOGGER.trace("Restauration d'une commande CutRecordable à partir d'un memento");

		/* Treatment */
		engine = memento.getEngine();
		recorder = memento.getRecorder();
	}
}
