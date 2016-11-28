package recordables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.Copier;
import editor.Enregistreur;
import engine.EditionEngine;
import mementos.MementoCommande;
import mementos.MementoCopier;

/**
 * La classe CopierEnregistrable execute une commande Copier et enregistre son MementoCommande dans un Enregistreur
 * @see Enregistreur
 * @see Copier
 * @see CommandeEnregistrable
 */
public final class CopierEnregistrable implements CommandeEnregistrable {


	/**
	 * Logger pour suivre le déroulement de l'application
	 */
	private static final Logger LOGGER = LogManager.getLogger(CopierEnregistrable.class.getName());
	
	private Enregistreur enregistreur;
	private EditionEngine moteur;
	
	/**
	 * Créé une commande CopierEnregistrable
	 * L'ensemble des paramètres doit être renseigné
	 * @param moteur Le EditionEngine auquel adresser la commande
	 * @param enregistreur L'enregsitreur de commande
	 */
	public CopierEnregistrable(EditionEngine moteur, Enregistreur enregistreur){
		
		/* Préconditions */
		if(enregistreur == null){
			
			throw new IllegalArgumentException("enregistreur est à null");
		}
		if(moteur == null){
			
			throw new IllegalArgumentException("moteur est à null");
		}
		
		/*Traitement*/
		
		this.enregistreur = enregistreur;
		this.moteur = moteur;
	}
	
	/**
	 * Créé une Commande CopierEnregistrable à partir d'un MementoCopier et execute une commande Copier
	 * @param memento Le memento duquel on restaure l'état de la commande enregistrable
	 */
	public CopierEnregistrable(MementoCommande memento){
		
		restaurer(memento);	
		new Copier(moteur).executer();
	}
	
	/**
	 * Effectue l'enregistrement de la commande auprès de l'enregistreur et execute la commande auprès du moteur
	 */
	@Override
	public final void executer() {
		
		enregistreur.enregistrer(this);
		LOGGER.trace("Exécution d'une commande CopierEnregistrable");
		new Copier(moteur).executer();
	}

	/**
	 * Retour l'état de l'objet sous forme d'un objet MementoCopier
	 * @see MementoCopier
	 */
	@Override
	public final MementoCommande getMemento() {
		
		return new MementoCopier(moteur, enregistreur);
	}

	/**
	 * Restaure l'état d'une commande à partir d'un memento
	 * @param memento L'objet memento de la classe MementoCopier (non null)
	 * @see MementoCopier
	 */
	@Override
	public final void restaurer(MementoCommande memento) {
		
		/* Préconditions */
		if(memento == null){
			
			throw new IllegalArgumentException("memento est à null");
		}
		
		if(!(memento instanceof MementoCopier)){
			
			throw new IllegalArgumentException("Le memento n'est pas de la classe MementoCopier");
		}
		
		LOGGER.trace("Restauration d'une commande CopierEnregistrable à partir d'un memento");
		
		/*Traitement*/
		this.moteur = memento.getMoteur();
		this.enregistreur = memento.getEnregistreur();
	}
}
