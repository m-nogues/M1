/*
 * TP 3  : Puissance 4
 * 
 * @author Tassadit BOUADI.
 */

/**
 * Interface pour la d�finition d'une fonction d'�valuation d'une grille.
 * 
 */
public interface FonctionEvaluation{
	public static final double MIN = Double.NEGATIVE_INFINITY;
	public static final double MAX = Double.POSITIVE_INFINITY;
	

	/**
	 * Fonction qui donne une valeur � la grille, pour le joueur,
	 * de telle fa�on que la valeur soit �gale � MAX si joueur gagne,
	 * �gale � 0 s'il y a match nul et �gale � MIN si joueur perd.
	 * @param grille : la grille de puissance 4.
	 * @param joueur : le joueur qui joue le coup.
	 * @return la valeur donn�e � la grille, pour le joueur.
	 */
	public double evaluation(Grille grille, int joueur);
}
