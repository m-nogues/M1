/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package gui;

import javax.swing.JButton;

import editor.Observable;
import editor.Observateur;
import editor.Recorder;

/**
 * Ce bouton est chargé de se mettre à jour (au niveau de son état cliquable ou
 * non) à chaque modification de l'recorder
 * 
 * @see Recorder
 */
public class BoutonStop extends JButton implements Observateur {

	private static final long serialVersionUID = -6273371891402818469L;

	/**
	 * @see Observateur
	 */
	@Override
	public void miseAJour(Observable o) {

		if (o == null)
			throw new IllegalArgumentException("o est à null");

		if (!(o instanceof Recorder))
			throw new IllegalArgumentException("o n'est pas du type Recorder");

		Recorder recorder = (Recorder) o;

		if (recorder.getEnregistrer())
			setEnabled(true);
		else
			setEnabled(false);
	}
}