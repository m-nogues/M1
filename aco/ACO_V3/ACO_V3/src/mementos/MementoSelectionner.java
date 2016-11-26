package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Enregistreur;
import engine.EditionEngine;
import engine.Selection;
import recordables.SelectionnerEnregistrable;

/**
 * Cette classe est chargée de stocker l'état d'une commande SelectionnerEnregistrable
 * @see SelectionnerEnregistrable
 * @see MementoCommande
 */
public final class MementoSelectionner extends MementoCommande{

	private static final Logger LOGGER = LogManager.getLogger(MementoSelectionner.class.getName());	
	
	private Selection selection;
		
	public MementoSelectionner(EditionEngine moteur, Enregistreur enregistreur, Selection selection){
		
		super(moteur, enregistreur);
		
		if(selection == null){
			
			throw new IllegalArgumentException("selection est à null");
		}

		this.selection = selection;
		LOGGER.trace("Création d'un MementoSelectionner");
	}
	
	/**
	 * Getter de l'attribut selection
	 * @return L'attribut selection du memento
	 */
	public final Selection getSelection(){
		
		return selection;
	}
	
	/**
	 * Setter de l'attribut selection
	 * @param selection La nouvelle selection (non null)
	 */
	public final void setSelection(Selection selection){
		
		if(selection == null){
			
			throw new IllegalArgumentException("selection est à null");
		}
		
		this.selection = selection;
	}
}