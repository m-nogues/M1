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
 * Recorder is to record the memento of commands as to replay them on the users'
 * demand.
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
		listMementos = new ArrayList<>();
		listObservers = new ArrayList<>();
		record = false;
	}

	/**
	 * Clears the list of recorded memento (if there was any) and activate the
	 * record mode.
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
		/* Precondition */
		if (o == null)
			throw new IllegalArgumentException("o is null");
		if (listObservers.contains(o))
			throw new IllegalArgumentException("o is already subscribed");

		/* Treatment */
		listObservers.add(o);
	}

	/**
	 * Deactivates the record mode.
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
	 * Gets the record.
	 *
	 * @return true if it records
	 */
	public boolean getRecord() {
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

	/**
	 * Records the memento of a command.
	 *
	 * @param command
	 *            the recordable command that we want to save the state of
	 */
	public final void record(CommandRecordable command) {
		/* Precondition */
		if (command == null)
			throw new IllegalArgumentException("Command is null");

		/* Treatment */
		if (record) {
			LOGGER.trace("Command Record");
			listMementos.add(command.getMemento());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observable#removeObserver(editor.Observer)
	 */
	@Override
	public void removeObserver(Observer o) {
		/* Precondition */
		if (o == null)
			throw new IllegalArgumentException("o is null");
		if (!listObservers.contains(o))
			throw new IllegalArgumentException("o is not subscribed");

		/* Treatment */
		listObservers.remove(o);
	}

	/**
	 * Replay all the recorded commands from the corresponding recorded memento.
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