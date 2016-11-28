/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package editor;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mementos.MementoCommand;
import mementos.MementoCopy;
import mementos.MementoCut;
import mementos.MementoDelText;
import mementos.MementoInsText;
import mementos.MementoPaste;
import mementos.MementoSelect;
import recordables.CommandRecordable;
import recordables.CopyRecordable;
import recordables.CutRecordable;
import recordables.DelTextRecordable;
import recordables.InsTextRecordable;
import recordables.PasteRecordable;
import recordables.SelectRecordable;

/**
 * Cette classe est chargée d'enregsitrer les mementos des commandes
 * enregsitrables de façon à pouvoir rejouer les commandes à la demande de
 * l'utilisateur.
 *
 * @see CommandRecordable
 * @see MementoCommand
 */
public final class Recorder implements Observable {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(Recorder.class.getName());

	/** The list mementos. */
	private List<MementoCommand> listMementos;

	/** The list observers. */
	private List<Observer> listObservers;

	/** The record. */
	private boolean record;

	/**
	 * Instantiates a new recorder.
	 */
	public Recorder() {
		listMementos = new ArrayList<MementoCommand>();
		listObservers = new ArrayList<Observer>();
		record = false;
	}

	/**
	 * Vide la liste des mementos enregistrés par l'objet et active
	 * l'enregsitrement.
	 */
	public final void activate() {
		if (!record) {
			LOGGER.trace("Activating record mode");

			listMementos.clear();
			record = true;
			notifyObservers();
		} else
			LOGGER.trace("Already recording");
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observable#addObserver(editor.Observer)
	 */
	@Override
	public void addObserver(Observer o) {
		if (o == null)
			throw new IllegalArgumentException("o is null");

		if (listObservers.contains(o))
			throw new IllegalArgumentException("o is already subscribed");

		listObservers.add(o);
	}

	/**
	 * Désactive l'enregistrement des commandes.
	 */
	public final void deactivate() {
		if (record) {
			LOGGER.trace("Deactivating record mode");

			record = false;
			notifyObservers();
		} else
			LOGGER.trace("Not recording");
	}

	/**
	 * Enregistre le memento d'une commande.
	 *
	 * @param command
	 *            La commande enregistrable dont on souhaite sauvegarder l'état
	 *            (non null)
	 */
	public final void enregistrer(CommandRecordable command) {
		if (command == null)
			throw new IllegalArgumentException("Command is null");

		if (record) {
			LOGGER.trace("Command Record");
			listMementos.add(command.getMemento());
		}
	}

	/**
	 * Gets the enregistrer.
	 *
	 * @return Le statut de l'recorder :
	 *         -True : enregistre
	 *         -False : N'enregistre pas
	 */
	public boolean getEnregistrer() {
		return record;
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observable#notifyObservers()
	 */
	@Override
	public void notifyObservers() {
		for (Observer o : listObservers)
			o.update(this);
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observable#removeObserver(editor.Observer)
	 */
	@Override
	public void removeObserver(Observer o) {
		if (o == null)
			throw new IllegalArgumentException("o is null");

		if (!listObservers.contains(o))
			throw new IllegalArgumentException("o is not subscribed");

		listObservers.remove(o);
	}

	/**
	 * Rejoue l'ensemble des commandes précédemment enregistrées en les
	 * restaurant à partir de leurs Memento.
	 */
	public final void replayCommands() {

		LOGGER.trace("Replay of recorded commands");

		for (MementoCommand m : listMementos)
			if (m instanceof MementoPaste)
				new PasteRecordable(m);
			else if (m instanceof MementoCopy)
				new CopyRecordable(m);
			else if (m instanceof MementoCut)
				new CutRecordable(m);
			else if (m instanceof MementoSelect)
				new SelectRecordable(m);
			else if (m instanceof MementoInsText)
				new InsTextRecordable(m);
			else if (m instanceof MementoDelText)
				new DelTextRecordable(m);
	}
}