package recordables;

import commands.Commande;
import editor.Enregistreur;
import mementos.MementoCommande;

/**
 * Cette interface est implémenté par les commandes enregistrables. De façon à sauvegarder et restaurer leurs état, le pattern MementoCommande est utilisé.
 * @author Antoine
 * @see Enregistreur
 * @see MementoCommande
 */
public interface CommandeEnregistrable extends Commande {

		/**
		 * Founit un memento enregistrant l'état de l'objet
		 * @return L'état de l'objet stocké sous forme de MementoCommande
		 * @see MementoCommande
		 */
		public MementoCommande getMemento();
		
		/**
		 * Restaure l'état à partir du memento passé en paramètre
		 * @param memento L'état de l'objet stocké sous forme de mémento
		 */
		public void restaurer(MementoCommande memento);
}
