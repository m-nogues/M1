package engine;

/**
 * Un moteur d'édition fournit un ensemble d'opérations permettant de manipuler du texte.
 * Ces opérations sont les suivantes :
 * 
 * - Copier
 * - Couper
 * - Coller
 * - Selectionner
 * - Supprimer du texte
 * - Insérer du texte
 * 
 * Un moteur d'édition est composé :
 * 
 * - D'un buffer, permettant de stocker et modifier le texte manipulé.
 * - D'un presse-papier, permettant d'effectuer les opérations de copier/coupe/collage
 * - D'une sélection, permettant de manipuler des parties du texte entré par l'utilisateur
 * 
 * @see Buffer
 * @see PressePapier
 * @see Selection
 */
public  interface EditionEngine 
{
	/**
	 * Effectue une opération de collage du contenu du presse-papier vers le buffer
	 */
	public void coller() ;
	
	/**
	 * Effectue une opération de copie du contenu du buffer vers le presse-papier
	 */
	public void copier() ;
	
	/**
	 * Effectue une opération de copie du contenu du buffer vers le presse-papier avec supression du contenu copié dans le buffer
	 */
	public void couper() ;
	
	/**
	 * Effectue une insertion de texte dans le buffer
	 * @param s La chaîne à insérer
	 */
	public void insererTexte(String s) ;
	
	/**
	 * Modifie la sélection du moteur d'édition
	 * @param s La nouvelle sélection
	 */
	public void selectionner(Selection s) ;
	
	/**
	 * Effectue une suppression de texte dans le buffer
	 */
	public void supprimerTexte();
	
	/**
	 * Remet les élements du moteur dans l'état précédant la dernière action effectuée
	 */
	public void defaire();
	
	/**
	 * Remet les élements du moteur dans l'état précédemment atteind
	 */
	public void refaire();
}

