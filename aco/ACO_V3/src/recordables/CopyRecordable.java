/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package recordables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.Copy;
import editor.Recorder;
import engine.EditionEngine;
import mementos.MementoCommand;
import mementos.MementoCopy;

/**
 * CopyRecordable executes a copy command will saving its state in a
 * recorder.
 *
 * @see Recorder
 * @see Copy
 * @see CommandRecordable
 */
public final class CopyRecordable implements CommandRecordable {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(CopyRecordable.class.getName());

	/** The recorder. */
	private Recorder recorder;

	/** The engine. */
	private EditionEngine engine;

	/**
	 * Instantiate a CopyRecordable from the given parameters (all shall be
	 * not null).
	 *
	 * @param engine
	 *            the engine to ask to execute the command
	 * @param recorder
	 *            the command recorder
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
	 * Instantiate a CopyRecordable from a memento and executes a copy
	 * command.
	 *
	 * @param memento
	 *            the memento from which we restore the state and execute the
	 *            select command
	 */
	public CopyRecordable(MementoCommand memento) {
		restore(memento);
		new Copy(engine).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public final void execute() {
		recorder.record(this);
		LOGGER.trace("Executing copy command");
		new Copy(engine).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see recordables.CommandRecordable#getMemento()
	 */
	@Override
	public final MementoCommand getMemento() {
		return new MementoCopy(engine, recorder);
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

		if (!(memento instanceof MementoCopy))
			throw new IllegalArgumentException("Not a MementoCopy");

		LOGGER.trace("CopyRecordable from memento");

		/* Treatment */
		engine = memento.getEngine();
		recorder = memento.getRecorder();
	}
}
