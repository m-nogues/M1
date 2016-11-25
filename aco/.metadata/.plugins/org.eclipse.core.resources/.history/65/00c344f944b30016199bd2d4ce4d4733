package recordables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.SupprimerTexte;
import editor.Enregistreur;
import engine.MoteurEdition;
import mementos.MementoCommande;
import mementos.MementoSupprTexte;

/**
 * La classe SupTexteEnregistrable execute une commande SupprimerTexte et enregistre son MementoCommande dans un Enregistreur
 * @see Enregistreur
 * @see SupprimerTexte
 * @see CommandeEnregistrable
 */
public final class SupTexteEnregistrable implements CommandeEnregistrable {


	/**
	 * Logger pour suivre le déroulement de l'application
	 */
	private static final Logger LOGGER = LogManager.getLogger(SupTexteEnregistrable.class.getName());
	
	private Enregistreur enregistreur;
	private MoteurEdition moteur;
	
	/**
	 * Créé une commande SupTexteEnregistrable
	 * L'ensemble des paramètres doit être renseigné
	 * @param moteur Le MoteurEdition auquel adresser la commande
	 * @param enregistreur L'enregsitreur de commande
	 */
	public SupTexteEnregistrable(MoteurEdition moteur, Enregistreur enregistreur){
		
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
	 * Créé une Commande SupTexteEnregistrable à partir d'un MementoSupprTexte et execute une commande SupprimerTexte
	 * @param memento Le memento duquel on restaure l'état de la commande enregistrable
	 */
	public SupTexteEnregistrable(MementoCommande memento){
		
		restaurer(memento);	
		new SupprimerTexte(moteur).executer();
	}
	
	/**
	 * Effectue l'enregistrement de la commande auprès de l'enregistreur et execute la commande auprès du moteur
	 */
	@Override
	public final void executer() {
		
		enregistreur.enregistrer(this);
		LOGGER.trace("Exécution d'une commande SupprimerTexte");
		new SupprimerTexte(moteur).executer();
	}

	/**
	 * Retour l'état de l'objet sous forme d'un objet MementoSupprTexte
	 * @see MementoSupprTexte
	 */
	@Override
	public final MementoCommande getMemento() {
		
		return new MementoSupprTexte(moteur, enregistreur);
	}

	/**
	 * Restaure l'état d'une commande à partir d'un memento
	 * @param memento L'objet memento de la classe MementoSupprTexte (non null)
	 * @see MementoSupprTexte
	 */
	@Override
	public final void restaurer(MementoCommande memento) {
		
		/* Préconditions */
		if(memento == null){
			
			throw new IllegalArgumentException("memento est à null");
		}
		
		if(!(memento instanceof MementoSupprTexte)){
			
			throw new IllegalArgumentException("Le memento n'est pas de la classe MementoSupprTexte");
		}
		
		LOGGER.trace("Restauration d'une commande SupTexteEnregistrable à partir d'un memento");
		
		/*Traitement*/
		this.moteur = memento.getMoteur();
		this.enregistreur = memento.getEnregistreur();
	}
}
