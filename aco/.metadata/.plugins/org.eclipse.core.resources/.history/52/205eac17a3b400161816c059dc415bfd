package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.EditionEngine;
import engine.Selection;

/**
 * La commande selectionner ordonne au moteur d'édition d'effectuer une nouvelle sélection
 */
public final class Selectionner implements Commande
{
	private static final Logger LOGGER = LogManager.getLogger(Selectionner.class.getName());	
	
	/**
	 * Moteur d'édition qui exécutera la commande
	 */
	private final EditionEngine moteur;
	
	/**
	 * Nouvelle sélection
	 */
	private final Selection selection;
	
	/**
	 * Le constructeur a besoin de savoir à quel moteur d'édition envoyer la commande et quelle sélection effectuer
	 * @param moteur	Le Moteur d'édition auquel envoyer la commande (non null)
	 * @param selection La nouvelle selection (non null)
	 */
	public Selectionner(EditionEngine moteur, Selection selection){
		
		/* Préconditions */
		
		if(moteur == null){
			
			throw new IllegalArgumentException("Moteur est à null");
		}
		
		if(selection == null){
			
			throw new IllegalArgumentException("Selection est à null");
		}
		
		/* Traitement */
		
		this.moteur = moteur;
		this.selection = selection;
	}

	/**
	 * Execute la commande
	 */
	@Override
	public final void executer() {
		
		LOGGER.trace("Exécution d'une commande Selectionner");
		moteur.selectionner(selection);
	}
}

