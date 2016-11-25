package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.EditionEngine;

/**
 * La commande défaire sert à indiquer au moteur d'édition qu'on souhaite rétablir l'éditeur à l'état précédent (si possible)
 */
public class Defaire implements Commande {

	private static final Logger LOGGER = LogManager.getLogger(Defaire.class.getName());	
	
	private final EditionEngine moteur;
	
	/**
	 * Crée une commande Defaire
	 * @param moteur Le moteur d'édition auquel adresser la commande (non-null)
	 */
	public Defaire(EditionEngine moteur){
		
		if(moteur == null){
			
			throw new IllegalArgumentException("moteur est à null");
		}
		
		this.moteur = moteur;
	}
	
	/**
	 * Exécute la commande
	 */
	@Override
	public void executer() {
		
		LOGGER.trace("Exécution d'une commande Défaire");
		moteur.defaire();
	}
}