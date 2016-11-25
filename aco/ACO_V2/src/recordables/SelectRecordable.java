package recordables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.Select;
import editor.Recorder;
import engine.EditionEngine;
import engine.Selection;
import mementos.MementoCommand;
import mementos.MementoSelect;

/**
 * La classe SelectRecordable execute une commande Selectionner et enregistre
 * son MementoCommand dans un Recorder
 *
 * @see Recorder
 * @see Selectionner
 * @see CommandRecordable
 */
public final class SelectRecordable implements CommandRecordable {

	/**
	 * Logger pour suivre le déroulement de l'application
	 */
	private static final Logger LOGGER = LogManager.getLogger(SelectRecordable.class.getName());

	private Recorder		recorder;
	private EditionEngine	engine;
	private Selection		selection;

	/**
	 * Créé une commande SelectRecordable
	 * L'ensemble des paramètres doit être renseigné
	 *
	 * @param engine
	 *            Le EditionEngine auquel adresser la commande
	 * @param recorder
	 *            L'enregsitreur de commande
	 * @param selection
	 *            La selection à associer à la commande
	 */
	public SelectRecordable(EditionEngine engine, Recorder recorder, Selection selection) {
		/* Preconditions */
		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");
		if (engine == null)
			throw new IllegalArgumentException("engine is null");
		if (selection == null)
			throw new IllegalArgumentException("selection is null");

		/* Treatment */
		this.recorder = recorder;
		this.engine = engine;
		this.selection = selection;
	}

	/**
	 * Créé une Command SelectRecordable à partir d'un MementoSelect et execute
	 * une commande Selectionner
	 *
	 * @param memento
	 *            Le memento duquel on restaure l'état de la commande
	 *            enregistrable
	 */
	public SelectRecordable(MementoCommand memento) {
		restore(memento);
		LOGGER.trace("Exécution d'une commande Selectionner");
		new Select(engine, selection).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public final void execute() {
		recorder.enregistrer(this);
		LOGGER.trace("Exécution d'une commande Selectionner");
		new Select(engine, selection).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see recordables.CommandRecordable#getMemento()
	 */
	@Override
	public final MementoCommand getMemento() {
		return new MementoSelect(engine, recorder, selection);
	}

	/**
	 * Restaure l'état d'une commande à partir d'un memento
	 *
	 * @param memento
	 *            L'objet memento de la classe MementoSelect (non null)
	 * @see MementoSelect
	 */
	@Override
	public final void restore(MementoCommand memento) {
		/* Preconditions */
		if (memento == null)
			throw new IllegalArgumentException("memento is null");
		if (!(memento instanceof MementoSelect))
			throw new IllegalArgumentException("Not a MementoInsSelectionner");

		LOGGER.trace("SelectRecordable from memento");

		/* Treatment */
		engine = memento.getEngine();
		recorder = memento.getRecorder();
		selection = ((MementoSelect) memento).getSelection();
	}
}
