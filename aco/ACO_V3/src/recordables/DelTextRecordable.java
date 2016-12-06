/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package recordables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.DeleteText;
import editor.Recorder;
import engine.EditionEngine;
import mementos.MementoCommand;
import mementos.MementoDelText;

/**
 * DelTextRecordable executes a text deletion command will saving its state in a
 * recorder.
 *
 * @see Recorder
 * @see DeleteText
 * @see CommandRecordable
 */
public final class DelTextRecordable implements CommandRecordable {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(DelTextRecordable.class.getName());

	/** The recorder. */
	private Recorder recorder;

	/** The engine. */
	private EditionEngine engine;

	/**
	 * Instantiate a DelTextRecordable from the given parameters (all shall be
	 * not null).
	 *
	 * @param engine
	 *            the engine to ask to execute the command
	 * @param recorder
	 *            the command recorder
	 */
	public DelTextRecordable(EditionEngine engine, Recorder recorder) {
		/* Preconditions */
		if (engine == null)
			throw new IllegalArgumentException("engine is null");
		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");

		/* Treatment */
		this.engine = engine;
		this.recorder = recorder;
	}

	/**
	 * Instantiate a DelTextRecordable from a memento and executes a text
	 * deletion command.
	 *
	 * @param memento
	 *            the memento from which we restore the state and execute the
	 *            select command
	 */
	public DelTextRecordable(MementoCommand memento) {
		restore(memento);

		LOGGER.trace("Executing command delete text");

		new DeleteText(engine).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public final void execute() {
		recorder.record(this);

		LOGGER.trace("Executing command delete text");

		new DeleteText(engine).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see recordables.CommandRecordable#getMemento()
	 */
	@Override
	public final MementoCommand getMemento() {
		return new MementoDelText(engine, recorder);
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
		if (!(memento instanceof MementoDelText))
			throw new IllegalArgumentException("memento not of type MementoDelText");

		LOGGER.trace("DelTextRecordable from memento");

		/* Treatment */
		engine = memento.getEngine();
		recorder = memento.getRecorder();
	}
}
