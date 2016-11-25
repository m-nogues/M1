package mementos;

import editor.Recorder;
import engine.EditionEngine;
import recordables.CommandRecordable;

/**
 * La classe MementoCommand sert à stocker l'état des commandes enregistrables.
 * Comme toutes les commandes enregsitrables ont un attribut engine et recorder,
 * ceux-ci ainsi que les getters/setters qui leurs sont associés sont définis au
 * sein de cette classe abstraite
 *
 * @see CommandRecordable
 * @see Recorder
 */
public abstract class MementoCommand {

	/** The engine. */
	protected EditionEngine	engine;
	
	/** The recorder. */
	protected Recorder		recorder;

	/**
	 * Permet de sauvegarder l'attribut engine d'une commande enregistrable.
	 *
	 * @param engine
	 *            L'attribut engine de la commande enregistrable (non null)
	 * @param recorder
	 *            the recorder
	 */
	public MementoCommand(EditionEngine engine, Recorder recorder) {
		if (engine == null)
			throw new IllegalArgumentException("engine is null");

		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");

		this.engine = engine;
		this.recorder = recorder;
	}

	/**
	 * Getter de l'attribut engine.
	 *
	 * @return L'attribut engine de l'objet
	 */
	public final EditionEngine getEngine() {

		return engine;
	}

	/**
	 * Le getter de l'attribut recorder.
	 *
	 * @return L'attribut recorder de l'objet
	 */
	public final Recorder getRecorder() {
		return recorder;
	}

	/**
	 * Setter de l'attribut engine de l'objet.
	 *
	 * @param engine
	 *            Le nouveau engine (non null)
	 */
	public final void setEngine(EditionEngine engine) {

		if (engine == null)
			throw new IllegalArgumentException("engine is null");

		this.engine = engine;
	}

	/**
	 * Setter de l'attribut recorder de l'objet.
	 *
	 * @param recorder
	 *            Le nouvel recorder (non null)
	 */
	public final void setRecorder(Recorder recorder) {

		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");

		this.recorder = recorder;
	}
}
