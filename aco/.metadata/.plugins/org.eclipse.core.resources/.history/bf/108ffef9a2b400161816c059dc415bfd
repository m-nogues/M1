package engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Observable;
import editor.Observateur;
import mementos.MementoBuffer;

/**
 * Cette classe contient le texte entré par l'utilisateur suite à ses actions
 */
public final class Buffer implements Observable
{
	
	/**
	 * Logger pour suivre le déroulement de l'application
	 */
	private static final Logger LOGGER = LogManager.getLogger(Buffer.class.getName());
	
	/**
	 * Répertorie les observateurs de l'objet
	 */
	private final List<Observateur> listeObservateurs;
	
	/**
	 * Un objet de la classe StringBuffer est utilisé pour ses méthodes d'insertions/suppressions pratiques
	 */
	private StringBuffer contenu;
	
	/**
	 * Cet entier décrit la nouvelle position du curseur suite à la modification
	 */
	private int offsetModif;
	
	public Buffer(){

		listeObservateurs = new ArrayList<Observateur>();
		contenu = new StringBuffer();
		offsetModif = 0;
	}

	/**
	 * Ajoute du texte à notre buffer à l'emplacement désigné par la sélection
	 * @param chaine La chaîne à insérer au buffer (non null)
	 * @param selection La selection actuelle (non null)
	 */
	public final void ajouterTexte(final String chaine, final Selection selection) {
		
		LOGGER.trace("Entrée dans ajouterTexte");

		/* Préconditions */
		
		if(chaine == null){
			
			throw new IllegalArgumentException("chaine est à null");
		}
		if(selection == null){
			
			throw new IllegalArgumentException("selection est à null");
		}
		
		/* Traitement */
		
		//Si la sélection n'est pas vide on supprime le texte avant insertion du nouveau
		if(!selection.estVide()){
			
			supprimerTexte(selection);
		}
		final int nouvellePosition = selection.getDebut()+ chaine.length();
		offsetModif = nouvellePosition;
		
		contenu.insert(selection.getDebut(), chaine);
		selection.setSelection(nouvellePosition, nouvellePosition);

		notifierObservateurs();
		
		LOGGER.trace("Sortie de ajouterTexte");
	}
	
	/**
	 * Supprime le contenu du buffer désigné par la sélection passée en paramètre. Supprime le dernier caractère en cas de sélection vide.
	 * @param selection La sélection actuelle (non null)
	 */
	public final void supprimerTexte(final Selection selection) {
		
		LOGGER.trace("Entrée dans supprimerTexte");
		
		/* Préconditions */

		if(selection == null){
			
			throw new IllegalArgumentException("selection est à null");
		}
		
		/* Traitement */
		
		//Si la sélection est vide et qu'on peut supprimer le caractère précedant
		if(selection.estVide() && selection.getDebut() != 0){
			
			selection.setSelection(selection.getDebut()-1, selection.getDebut());
		}
		
		contenu.delete(selection.getDebut(), selection.getFin());
		selection.rendreVide();
		offsetModif = selection.getDebut();
		
		notifierObservateurs();
		
		LOGGER.trace("Sortie de supprimerTexte");
	}
	
	/**
	 * Renseigne l'indice max. pour l'objet Selection
	 * @return L'indice max. utilisable dans la sélection
	 */
	public int getSelMax(){
		
		return contenu.length();
	}
	/**
	 * Récupère le contenu du buffer spécifié par la sélection passée en paramètre
	 * @param selection La sélection souhaitée (non null)
	 * @return La chaîne du buffer représentée par la sélection
	 */
	public final String getContenu(final Selection selection){
		
		if(selection == null){
			
			throw new IllegalArgumentException("selection est à null");
		}
		
		return contenu.substring(selection.getDebut(), selection.getFin());
	}
	
	/**
	 * Retourne l'ensemble du buffer sous forme de chaîne de caractère
	 * @return Le contenu du buffer
	 */
	public final String getContenu(){
		
		return contenu.toString();
	}
	
	/**
	 * Retourne la position du curseur suite à la dernière modification
	 * @return La nouvelle position du curseur
	 */
	public final int getOffsetModif(){
		
		return offsetModif;
	}
	
	/**
	 * Ajoute un observateur à la liste des observateurs de l'objet
	 * @param o L'observateur à ajouter (ici l'IHM) (non null)
	 */
	public final void ajouterObservateur(final Observateur o) {
		
		if(o == null){
			
			throw new IllegalArgumentException("selection est à null");
		}
		
		if(listeObservateurs.contains(o)){
			
			throw new IllegalArgumentException("o est déjà dans la liste des observateurs");
		}
		
		listeObservateurs.add(o);
	}
	
	/**
	 * Retire un observateur de la liste des observateurs de l'objet
	 * @param o L'observateur à retirer (non null)
	 */
	public final void retirerObservateur(final Observateur o) {
		
		if(o == null){
			
			throw new IllegalArgumentException("selection est à null");
		}
		
		if(!listeObservateurs.contains(o)){
			
			throw new IllegalArgumentException("o n'est pas dans la liste des observateurs");
		}

		listeObservateurs.remove(o);
	}
	
	/**
	 * Indique à l'ensemble des observateurs qu'ils doivent se mettre à jour suite à un changement du contenu du buffer
	 */
	public final void notifierObservateurs() {
		
		for(Observateur o : listeObservateurs){
			
			o.miseAJour(this);
		}
	}
	
	/**
	 * Sauvegarde l'état du Buffer au sein d'un memento
	 * @return Un memento contenant l'etat du Buffer
	 */
	public MementoBuffer getMemento(){
		
		return new MementoBuffer(new StringBuffer(contenu), offsetModif);
	}
	
	/**
	 * Restore l'état du Buffer depuis un memento
	 * @param memento Un memento contenant l'etat du Buffer (non null)
	 */
	public void restaurer(MementoBuffer memento){
		
		if(memento == null){
			
			throw new IllegalArgumentException("Contenu est à null");
		}
		
		contenu = memento.getContenu();
		offsetModif = memento.getOffModif();
		
		notifierObservateurs();
	}
}