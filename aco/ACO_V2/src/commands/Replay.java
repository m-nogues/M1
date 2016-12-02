/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Recorder;

/**
 * Replay asks the recorder to replay the previously recorded commands.
 *
 * @see Recorder
 */
public class Replay implements Command {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(Replay.class.getName());

	/** The recorder. */
	private Recorder recorder;

	/**
	 * Instantiates a new replay.
	 *
	 * @param recorder
	 *            the recorder
	 */
	public Replay(Recorder recorder) {
		/* Precondition */
		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");

		/* Treatment */
		this.recorder = recorder;
	}

	/*
	 * (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public void execute() {
		LOGGER.trace("Executing command replay");
		recorder.replayCommands();
	}
}