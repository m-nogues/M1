package recordables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.InsertText;
import editor.Recorder;
import engine.EditionEngine;
import mementos.MementoCommand;
import mementos.MementoInsText;

/**
 * La classe InsTextRecordable execute une commande InsertText et enregistre
 * son MementoCommand dans un Recorder
 *
 * @see Recorder
 * @see InsertText
 * @see CommandRecordable
 */
public final class InsTextRecordable implements CommandRecordable {

	/**
	 * Logger pour suivre le déroulement de l'application
	 */
	private static final Logger LOGGER = LogManager.getLogger(InsTextRecordable.class.getName());

	private Recorder		recorder;
	private EditionEngine	engine;
	private String			chaine;

	/**
	 * Créé une commande InsTextRecordable
	 * L'ensemble des paramètres doit être renseigné
	 *
	 * @param engine
	 *            Le EditionEngine auquel adresser la commande
	 * @param recorder
	 *            L'enregsitreur de commande
	 * @param chaine
	 *            La chaîne associer à la commande
	 */
	public InsTextRecordable(EditionEngine engine, Recorder recorder, String chaine) {

		/* Preconditions */
		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");
		if (engine == null)
			throw new IllegalArgumentException("engine is null");
		if (chaine == null)
			throw new IllegalArgumentException("chaine is null");

		/* Treatment */

		this.recorder = recorder;
		this.engine = engine;
		this.chaine = chaine;
	}

	/**
	 * Créé une Command InsTextRecordable à partir d'un MementoInsText et
	 * execute une commande InsertText
	 *
	 * @param memento
	 *            Le memento duquel on restaure l'état de la commande
	 *            enregistrable
	 */
	public InsTextRecordable(MementoCommand memento) {

		restore(memento);
		new InsertText(engine, chaine).execute();
	}

	/**
	 * Effectue l'enregistrement de la commande auprès de l'recorder et execute
	 * la commande auprès du engine
	 */
	@Override
	public final void execute() {

		recorder.enregistrer(this);
		LOGGER.trace("Exécution d'une commande InsertText");
		new InsertText(engine, chaine).execute();
	}

	/**
	 * Retour l'état de l'objet sous forme d'un objet MementoInTexte
	 *
	 * @see MementoInsText
	 */
	@Override
	public final MementoCommand getMemento() {

		return new MementoInsText(engine, recorder, chaine);
	}

	/**
	 * Restaure l'état d'une commande à partir d'un memento
	 *
	 * @param memento
	 *            L'objet memento de la classe MementoInsText (non null)
	 * @see MementoInsText
	 */
	@Override
	public final void restore(MementoCommand memento) {

		/* Preconditions */
		if (memento == null)
			throw new IllegalArgumentException("memento is null");

		if (!(memento instanceof MementoInsText))
			throw new IllegalArgumentException("Not a MementoInsText");

		LOGGER.trace("Restauration d'une commande InsTextRecordable à partir d'un memento");

		/* Treatment */
		engine = memento.getEngine();
		recorder = memento.getRecorder();
		chaine = ((MementoInsText) memento).getText();
	}
}
