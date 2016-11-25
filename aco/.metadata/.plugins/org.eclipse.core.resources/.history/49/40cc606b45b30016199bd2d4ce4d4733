package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Enregistreur;
import engine.MoteurEdition;
import recordables.CollerEnregistrable;

/**
 * Cette classe est chargée de stocker l'état d'une commande CollerEnregistrable
 * @see CollerEnregistrable
 * @see MementoCommande
 */
public final class MementoColler extends MementoCommande{

	private static final Logger LOGGER = LogManager.getLogger(MementoColler.class.getName());	
	
	public MementoColler(MoteurEdition moteur, Enregistreur enregistreur){
		
		super(moteur, enregistreur);
		LOGGER.trace("Création d'un MementoColler");
	}
}