package commands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.MoteurEdition;

/**
 * La commande inserer texte ordonne au moteur d'édition d'effectuer une opération d'insertion de texte
 */
public final class InsererTexte implements Commande
{
	
	private static final Logger LOGGER = LogManager.getLogger(InsererTexte.class.getName());	
	
	/**
	 * Moteur d'édition qui exécutera la commande
	 */
	private final MoteurEdition moteur;
	
	/**
	 * Chaîne de caractère à insérer
	 */
	private final String chaine;
	
	/**
	 * Le constructeur a besoin de savoir à quel moteur d'édition envoyer la commande et quelle chaîne insérer
	 * @param moteur	Le Moteur d'édition auquel envoyer la commande (non null)
	 * @param chaine	La chaine de caractère à insérer (non null)
	 */
	public InsererTexte(MoteurEdition moteur, String chaine){
		
		/* Préconditions */
		
		if(moteur == null){
			
			throw new IllegalArgumentException("Moteur est à null");
		}
		
		if(chaine == null){
			
			throw new IllegalArgumentException("Chaine est à null");
		}
		
		/* Traitement*/
		
		this.moteur = moteur;
		this.chaine = chaine;
	}

	/**
	 * Execute la commande
	 */
	@Override
	public final void executer() {
		
		LOGGER.trace("Exécution d'une commande InsererTexte");
		moteur.insererTexte(chaine);
	}
}