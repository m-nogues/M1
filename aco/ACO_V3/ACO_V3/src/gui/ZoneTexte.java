package gui;

import javax.swing.JTextArea;

import editor.Enregistreur;
import engine.EditionEngine;
import recordables.CollerEnregistrable;
import recordables.CopierEnregistrable;
import recordables.CouperEnregistrable;

/**
 * Cette classe hérite de JTextArea afin de traiter les actions de copie, coupe, collage
 */
public class ZoneTexte extends JTextArea {

	static final long serialVersionUID = 8166387793964966707L;

	private EditionEngine moteur;
	private Enregistreur enregistreur;
	
	/**
	 * Crée la zone de texte
	 * @param moteur Le moteur d'edition auquel adresser les commandes
	 * @param enregistreur L'enregistreur auquel adresser les commandes enregsitrables
	 */
	public ZoneTexte(int hauteur, int largeur, EditionEngine moteur, Enregistreur enregistreur){
		
		super(hauteur, largeur);
		
		if(moteur == null){
			
			throw new IllegalAccessError("moteur est à null");
		}
		
		if(enregistreur == null){
			
			throw new IllegalArgumentException("enregistreur est à null");
		}
		
		this.moteur = moteur;
		this.enregistreur = enregistreur;
	}
	
	/**
	 * Lance une commande CopierEnregsitrable
	 * @see CopierEnregistrable
	 */
	@Override
	public void copy(){
		
		new CopierEnregistrable(moteur, enregistreur).executer();
	}
	
	/**
	 * Lance une commande CouperEnregsitrable
	 * @see CouperEnregistrable
	 */
	@Override
	public void cut(){
		
		new CouperEnregistrable(moteur, enregistreur).executer();
	}
	
	/**
	 * Lance une commande CollerEnregsitrable
	 * @see CollerEnregistrable
	 */
	@Override
	public void paste(){
		
		new CollerEnregistrable(moteur, enregistreur).executer();
	}
}
