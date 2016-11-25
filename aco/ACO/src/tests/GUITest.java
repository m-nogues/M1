package tests;

import java.util.Observable;

import editor.Observer;
import engine.Buffer;

/**
 * Cette classe, crée exclusivement pour les tests sert à vérifier que les
 * changements effectuées au niveau du engine d'édition sont bien répercutés au
 * niveau de l'GUI.
 */
public final class GUITest implements Observer {
	
	/** The last insert. */
	private String lastInsert;

	/**
	 * Instantiates a new GUI test.
	 */
	GUITest() {
		lastInsert = "";
	}

	/**
	 * Retourne la dernière mise à jour effectuée par le Buffer.
	 *
	 * @return La dernière mise à jour effectuée par le Buffer
	 */
	public String getLastInsert() {
		return new String(lastInsert);
	}

	/* (non-Javadoc)
	 * @see editor.Observer#update(editor.Observable)
	 */
	@Override
	public void update(editor.Observable o) {
		/* Preconditions */
		if (o == null)
			throw new IllegalArgumentException("o est à null");
		/* Treatment */
		if (o instanceof Buffer) {
			Buffer buffer = (Buffer) o;
			lastInsert = buffer.getContent();
		}
	}
}
