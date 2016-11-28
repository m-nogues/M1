package gui;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Enregistreur;
import engine.EditionEngine;
import engine.Selection;
import recordables.SelectionnerEnregistrable;

/**
 * Cette classe est chargée de surveiller les changements de sélection intervenant dans notre JTextArea, afin de s'assurer que notre l'objet Selection de notre Moteur d'édition est synchronisé avec la sélection de la JTextArea
 */
public final class ListenerSelection implements CaretListener
{
	private static final Logger LOGGER = LogManager.getLogger(ListenerSelection.class.getName());
	private boolean reagir;
	
	/**
	 * Le moteur d'édition à renseigner pour les commandes
	 */
	private final EditionEngine moteur;
	
	/**
	 * L'enregistreur pour les commandes enregistrables
	 */
	private final Enregistreur enregistreur;
	
	/**
	 * Le constructeur a besoin de savoir quel moteur d'édition spécifier à la commande Selectionner
	 * @param moteur	Le Moteur d'édition à renseigner pour la commande (non null)
	 */
	public ListenerSelection(EditionEngine moteur, Enregistreur enregistreur){
		
		if(moteur == null){
			
			throw new IllegalArgumentException("moteur est à null");
		}
		if(enregistreur == null){
			
			throw new IllegalArgumentException("enregistreur est à null");
		}

		this.moteur = moteur;
		this.enregistreur = enregistreur;
		reagir = true;
	}

	/**
	 * A chaque modification de la sélection dans notre JTextArea, cette méthode est invoquée
	 */
	@Override
	public final void caretUpdate(CaretEvent e) {
		
		LOGGER.trace("Détection d'une nouvelle sélection");
		
		final int min = Math.min(e.getDot(), e.getMark());
		final int max = Math.max(e.getDot(), e.getMark()); 
		
		LOGGER.debug("Nouvelle sélection : [" + min + ", " + max + "]");

		if(reagir){
			
			new SelectionnerEnregistrable(moteur, enregistreur, new Selection(min, max)).executer();
		}
	}
	
	/**
	 * Indique au listener s'il doit ou non lancer une commande lorsqu'il est notifié
	 * @param reagir Un booleen spécifiant s'il faut ou non réagir aux évènements reçus
	 */
	public void setReagir(boolean reagir){
		
		this.reagir = reagir;
	}
}