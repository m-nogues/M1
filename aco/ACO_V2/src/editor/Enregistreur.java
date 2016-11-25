package editor;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mementos.MementoColler;
import mementos.MementoCommande;
import mementos.MementoCopier;
import mementos.MementoCouper;
import mementos.MementoInsTexte;
import mementos.MementoSelectionner;
import mementos.MementoSupprTexte;
import recordables.CollerEnregistrable;
import recordables.CommandeEnregistrable;
import recordables.CopierEnregistrable;
import recordables.CouperEnregistrable;
import recordables.InsTexteEnregistrable;
import recordables.SelectionnerEnregistrable;
import recordables.SupTexteEnregistrable;

/**
 * Cette classe est chargée d'enregsitrer les mementos des commandes enregsitrables de façon à pouvoir rejouer les commandes à la demande de l'utilisateur
 * @see CommandeEnregistrable
 * @see MementoCommande
 */
public final class Enregistreur implements Observable {

	private static final Logger LOGGER = LogManager.getLogger(Enregistreur.class.getName());	
	
	private List<MementoCommande> listeMementos;
	private List<Observateur> listeObservateurs;
	private boolean enregistrer;
	
	public Enregistreur(){
		
		//Compatibilité Java 6
		listeMementos = new ArrayList<MementoCommande>();
		listeObservateurs = new ArrayList<Observateur>();
		enregistrer = false;
	}
	
	/**
	 * Vide la liste des mementos enregistrés par l'objet et active l'enregsitrement
	 */
	public final void activer(){
		
		LOGGER.trace("Activation de l'enregistrement");
		
		listeMementos.clear();
		enregistrer = true;
		notifierObservateurs();
	}
	
	/**
	 * Désactive l'enregistrement des commandes
	 */
	public final void desactiver(){
		
		LOGGER.trace("Désactivation de l'enregistrement");
		
		enregistrer = false;
		notifierObservateurs();
	}
	
	/**
	 * Enregistre le memento d'une commande
	 * @param commande La commande enregistrable dont on souhaite sauvegarder l'état (non null)
	 */
	public final void enregistrer(CommandeEnregistrable commande){
		
		if(commande == null){
			
			throw new IllegalArgumentException("commande est à null");
		}
		
		if(enregistrer){
			
			LOGGER.trace("Enregistrement d'une commande");
			listeMementos.add(commande.getMemento());
		}
	}
	
	/**
	 * Rejoue l'ensemble des commandes précédemment enregistrées en les restaurant à partir de leurs Memento
	 */
	public final void rejouerCommandes(){
		
		LOGGER.trace("Rejeu des commandes précedemment enregsitrées");
		
		for(MementoCommande m : listeMementos){
			
			if(m instanceof MementoColler){
				
				new CollerEnregistrable((MementoColler)m);
			}
			else if(m instanceof MementoCopier){
				
				new CopierEnregistrable((MementoCopier)m);
			}
			else if(m instanceof MementoCouper){
				
				new CouperEnregistrable((MementoCouper)m);
			}
			else if(m instanceof MementoSelectionner){
				
				new SelectionnerEnregistrable((MementoSelectionner)m);
			}
			else if(m instanceof MementoInsTexte){
				
				new InsTexteEnregistrable((MementoInsTexte)m);
			}
			else if(m instanceof MementoSupprTexte){
				
				new SupTexteEnregistrable((MementoSupprTexte)m);
			}
		}
	}

	/**
	 * @see Observable
	 */
	@Override
	public void ajouterObservateur(Observateur o) {
	
		if(o == null){
			
			throw new IllegalArgumentException("o est à null");
		}
		
		if(listeObservateurs.contains(o)){
			
			throw new IllegalArgumentException("o est déjà dans la liste des observateurs");
		}
		
		listeObservateurs.add(o);
	}

	/**
	 * @see Observable
	 */
	@Override
	public void notifierObservateurs() {

		for(Observateur o : listeObservateurs){
			
			o.miseAJour(this);
		}
		
	}

	/**
	 * @see Observable
	 */
	@Override
	public void retirerObservateur(Observateur o) {
		
		if(o == null){
			
			throw new IllegalArgumentException("o est à null");
		}
		
		if(!listeObservateurs.contains(o)){
			
			throw new IllegalArgumentException("o n'est pas dans la liste des observateurs");
		}
		
		listeObservateurs.remove(o);
	}
	
	/**
	 * @return Le statut de l'enregistreur :
	 * 	-True : enregistre
	 * 	-False : N'enregistre pas
	 */
	public boolean getEnregistrer(){
		
		return enregistrer;
	}
}