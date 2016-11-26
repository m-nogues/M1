/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package recordables;

import commands.Command;
import editor.Recorder;
import mementos.MementoCommand;

/**
 * CommandRecordable is implemented by the recordable commands. It allows to
 * save and restore their state when asked to.
 *
 * @see Recorder
 * @see MementoCommand
 */
public interface CommandRecordable extends Command {

	/**
	 * Get a memento saving the state of the object.
	 *
	 * @return the memento containing the state of this object
	 * @see MementoCommand
	 */
	public MementoCommand getMemento();

	/**
	 * Restore the state from the given memento.
	 *
	 * @param memento
	 *            the memento containing the state to restore
	 */
	public void restore(MementoCommand memento);
}
