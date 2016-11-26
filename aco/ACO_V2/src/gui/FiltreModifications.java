/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.DeleteText;
import commands.InsertText;
import engine.EditionEngine;

/**
 * La classe FiltreModifications sert à filtrer les entrées effectuées dans la
 * JTextArea de l'GUI. Dans un premier temps elle ne transmet pas l'action
 * demandée à la JTextArea mais elle transmet une commande au Moteur d'édition.
 * Suite à la modification du Buffer, l'GUI va être misee à jour (via le pattern
 * Observer) et ce classe autorisera la modifications demandée sur la JTextArea
 */
public final class FiltreModifications extends DocumentFilter {
	private static final Logger	LOGGER	= LogManager.getLogger(FiltreModifications.class.getName());
	private final EditionEngine	moteur;
	private boolean				reagir;

	/**
	 * Le constructeur a besoin de savoir quel engine d'édition spécifier aux
	 * commands
	 *
	 * @param engine
	 *            Le Moteur d'édition à renseigner pour les commands (non null)
	 */
	public FiltreModifications(EditionEngine moteur) {
		super();
		if (moteur == null)
			throw new IllegalArgumentException("engine est à null");
		this.moteur = moteur;
		reagir = true;
	}

	@Override
	/**
	 * Cette méthode est invoquée lorsqu'on colle une chaîne de caractère dans
	 * la JTextArea
	 *
	 * @param string
	 *            La chaîne à insérer
	 */
	public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {
		LOGGER.trace("Entrée dans insertString");
		LOGGER.debug("Insertion de la chaîne : " + string);
		if (reagir)
			new InsertText(moteur, string).execute();
		else
			super.insertString(fb, offset, string, attr);
		LOGGER.trace("Sortie de insertString");
	}

	@Override
	/**
	 * Cette méthode est invoquée lorsqu'on supprime du texte dans la JTextArea
	 */
	public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
		LOGGER.trace("Entrée dans remove");
		LOGGER.debug("Supression de la chaîne en position " + offset + " de longueur " + length);
		if (reagir)
			new DeleteText(moteur).execute();
		else
			super.remove(fb, offset, length);
		LOGGER.trace("Sortie de remove");
	}

	/**
	 * Cette méthode est invoquée lorsqu'on insère un caractère au clavier dans
	 * la JTextArea
	 */
	@Override
	public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attrs)
			throws BadLocationException {
		LOGGER.trace("Entrée dans replace");
		LOGGER.debug("Remplacement de la chaîne en position " + offset + " de longueur " + length + " par : " + string);
		if (reagir)
			new InsertText(moteur, string).execute();
		else
			super.replace(fb, offset, length, string, attrs);
		LOGGER.trace("Sortie de replace");
	}

	/**
	 * Indique au filtre s'il doit ou non lancer une commande lorsqu'il est
	 * notifié
	 *
	 * @param reagir
	 *            Un booleen spécifiant s'il faut ou non réagir aux évènements
	 *            reçus
	 */
	public void setReagir(boolean reagir) {
		this.reagir = reagir;
	}
}