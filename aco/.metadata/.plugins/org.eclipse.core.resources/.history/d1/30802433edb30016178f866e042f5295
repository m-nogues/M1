package gui;

import javax.swing.JButton;

import editor.Recorder;
import editor.Observable;
import editor.Observateur;

/**
 * Ce bouton est chargé de se mettre à jour (au niveau de son état cliquable ou non) à chaque modification de l'recorder
 * @see Recorder
 */
public class BoutonStop extends JButton implements Observateur {

	private static final long serialVersionUID = -6273371891402818469L;

	/**
	 * @see Observateur
	 */
	@Override
	public void miseAJour(Observable o) {
		
		if(o == null){
			
			throw new IllegalArgumentException("o est à null");
		}
		
		if(!(o instanceof Recorder)){
			
			throw new IllegalArgumentException("o n'est pas du type Recorder");
		}
		
		Recorder recorder = (Recorder) o;
		
		if(recorder.getEnregistrer()){
			
			setEnabled(true);
		}
		else{
			
			setEnabled(false);
		}
	}
}