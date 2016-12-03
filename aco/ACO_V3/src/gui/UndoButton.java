/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package gui;

import javax.swing.JButton;

import editor.HistoryManager;
import editor.Observable;
import editor.Observer;

/**
 * UndoButton implements the behavior of the UndoButton (enable/disable)
 *
 * @see HistoryManager
 */
public class UndoButton extends JButton implements Observer {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6273371891402818469L;

	/**
	 * Instantiates a new undo button.
	 */
	public UndoButton() {
		setEnabled(false);
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observer#update(editor.Observable)
	 */
	@Override
	public void update(Observable o) {

		if (o == null)
			throw new IllegalArgumentException("o is null");
		if (!(o instanceof HistoryManager))
			throw new IllegalArgumentException("o not of type HistoryManager");

		HistoryManager manager = (HistoryManager) o;

		setEnabled(manager.canUndo());
	}
}
