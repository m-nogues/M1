package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Enregistreur;
import engine.EditionEngine;
import recordables.SupTexteEnregistrable;

/**
 * Cette classe est chargée de stocker l'état d'une commande SupTexteEnregistrable
 * @see SupTexteEnregistrable
 * @see MementoCommande
 */
public final class MementoSupprTexte extends MementoCommande{

	private static final Logger LOGGER = LogManager.getLogger(MementoSupprTexte.class.getName());	
	
	public MementoSupprTexte(EditionEngine moteur, Enregistreur enregistreur){
		
		super(moteur, enregistreur);
		LOGGER.trace("Création d'un MementoSupprTexte");
	}
}