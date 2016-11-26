package recordables;

import commands.Command;
import editor.Recorder;
import mementos.MementoCommand;

/**
 * Cette interface est implémenté par les commandes enregistrables. De façon à
 * sauvegarder et restaurer leurs état, le pattern MementoCommand est utilisé.
 *
 * @author Antoine
 * @see Recorder
 * @see MementoCommand
 */
public interface CommandRecordable extends Command {

	/**
	 * Founit un memento enregistrant l'état de l'objet
	 *
	 * @return L'état de l'objet stocké sous forme de MementoCommand
	 * @see MementoCommand
	 */
	public MementoCommand getMemento();

	/**
	 * Restaure l'état à partir du memento passé en paramètre
	 *
	 * @param memento
	 *            L'état de l'objet stocké sous forme de mémento
	 */
	public void restore(MementoCommand memento);
}
