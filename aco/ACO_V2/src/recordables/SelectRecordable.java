/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
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
 * SelectRecordable executes a select command will saving its state in a
 * recorder.
 *
 * @see Recorder
 * @see Select
 * @see CommandRecordable
 */
public final class SelectRecordable implements CommandRecordable {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(SelectRecordable.class.getName());

	/** The recorder. */
	private Recorder recorder;

	/** The engine. */
	private EditionEngine engine;

	/** The selection. */
	private Selection selection;

	/**
	 * Instantiate a SelectRecordable from the given parameters (all shall be
	 * not null).
	 *
	 * @param engine
	 *            the engine to ask to execute the command
	 * @param recorder
	 *            the command recorder
	 * @param selection
	 *            the selection to do
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
	 * Instantiate a SelectRecordable from a memento and executes a select
	 * command.
	 *
	 * @param memento
	 *            the memento from which we restore the state and execute the
	 *            select command
	 */
	public SelectRecordable(MementoCommand memento) {
		restore(memento);
		LOGGER.trace("Executing select command");
		new Select(engine, selection).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public final void execute() {
		recorder.record(this);
		LOGGER.trace("Executing select command");
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

	/*
	 * (non-Javadoc)
	 * @see recordables.CommandRecordable#restore(mementos.MementoCommand)
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
