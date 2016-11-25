package mementos;

import editor.Enregistreur;
import engine.EditionEngine;
import recordables.CommandeEnregistrable;

/**
 * La classe MementoCommande sert à stocker l'état des commandes enregistrables.
 * Comme toutes les commandes enregsitrables ont un attribut moteur et enregistreur, ceux-ci ainsi que les getters/setters qui leurs sont associés sont définis au sein de cette classe abstraite
 * @see CommandeEnregistrable
 * @see Enregistreur
 */
public abstract class MementoCommande {

	protected EditionEngine moteur;
	protected Enregistreur enregistreur;
	
	/**
	 * Permet de sauvegarder l'attribut moteur d'une commande enregistrable
	 * @param moteur L'attribut moteur de la commande enregistrable (non null)
	 */
	public MementoCommande(EditionEngine moteur, Enregistreur enregistreur){
		
		if(moteur == null){
			
			throw new IllegalArgumentException("moteur est à null");
		}
		
		if(enregistreur == null){
			
			throw new IllegalArgumentException("enregistreur est à null");
		}
		
		this.moteur = moteur;
		this.enregistreur = enregistreur;
	}
	
	/**
	 * Getter de l'attribut moteur
	 * @return L'attribut moteur de l'objet
	 */
	public final EditionEngine getMoteur(){
		
		return moteur;
	}
	
	/**
	 * Setter de l'attribut moteur de l'objet
	 * @param moteur Le nouveau moteur (non null)
	 */
	public final void setMoteur(EditionEngine moteur){
		
		if(moteur == null){
			
			throw new IllegalArgumentException("moteur est à null");
		}
		
		this.moteur = moteur;
	}
	
	/**
	 * Setter de l'attribut enregistreur de l'objet
	 * @param enregistreur Le nouvel enregistreur (non null)
	 */
	public final void setEnregistreur(Enregistreur enregistreur){
		
		if(enregistreur == null){
			
			throw new IllegalArgumentException("enregistreur est à null");
		}
		
		this.enregistreur = enregistreur;
	}
	
	/**
	 * Le getter de l'attribut enregistreur
	 * @return L'attribut enregistreur de l'objet
	 */
	public final Enregistreur getEnregistreur(){
		
		return enregistreur;
	}
}
