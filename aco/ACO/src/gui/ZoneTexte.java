/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package gui;

import javax.swing.JTextArea;

import commands.Copy;
import commands.Cut;
import commands.Paste;
import engine.EditionEngine;

/**
 * Cette classe hérite de JTextArea afin de traiter les actions de copie, coupe,
 * collage
 */
public class ZoneTexte extends JTextArea {
	static final long		serialVersionUID	= 8166387793964966707L;
	private EditionEngine	moteur;

	/**
	 * Crée la zone de texte
	 *
	 * @param engine
	 *            Le engine d'edition auquel adresser les commands
	 */
	public ZoneTexte(int hauteur, int largeur, EditionEngine moteur) {
		super(hauteur, largeur);
		if (moteur == null)
			throw new IllegalAccessError("engine est à null");
		this.moteur = moteur;
	}

	/**
	 * Lance une commande CopierEnregsitrable
	 *
	 * @see Copy
	 */
	@Override
	public void copy() {
		new Copy(moteur).execute();
	}

	/**
	 * Lance une commande CouperEnregsitrable
	 *
	 * @see Cut
	 */
	@Override
	public void cut() {
		new Cut(moteur).execute();
	}

	/**
	 * Lance une commande CollerEnregsitrable
	 *
	 * @see Paste
	 */
	@Override
	public void paste() {
		new Paste(moteur).execute();
	}
}
