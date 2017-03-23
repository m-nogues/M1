/*
 * TP 3 : Puissance 4
 * @author Tassadit BOUADI.
 */

/**
 * Programme principal du jeu du puissance 4.
 */
public class Puissance4 {

	/** The Constant PROFONDEUR. */
	protected static final int PROFONDEUR = 5;

	/**
	 * Fonction qui effectue la boucle de jeu.
	 *
	 * @param joueur1
	 *          : le premier joueur.
	 * @param joueur2
	 *          : le second joueur.
	 */
	public static void jouer(Joueur joueur1, Joueur joueur2) {
		Resultat res;
		int coup;
		Grille grille = new Grille();

		Joueur joueurCour = joueur1;
		int numJoueur = Grille.JOUEUR1; // le joueur 1 commence

		int vainqueur = 0;// match nul
		boolean jeuFini = false;

		// boucle de jeu
		while (!jeuFini) {
			// affichage de la grille
			System.out.println(grille);

			// faire jouer le joueur courant et passer au suivant
			res = joueurCour.coup(grille, numJoueur);
			coup = res.getColonne();
			System.out.println("coup en " + coup);

			if (grille.coupGagnant(numJoueur, coup)) {
				vainqueur = numJoueur;
				jeuFini = true;
			}

			// ajout a la grille
			grille.joueEn(numJoueur, coup);

			System.out.println(numJoueur);

			if (grille.estPleine())
				jeuFini = true;

			// changement de joueur
			numJoueur = Grille.joueurSuivant(numJoueur);
			switch (numJoueur) {
				case Grille.JOUEUR1:
					joueurCour = joueur1;
					break;
				case Grille.JOUEUR2:
					joueurCour = joueur2;
					break;
			}
		} // while - boucle de jeu

		// affichage de la grille
		System.out.println(grille);

		// affichage du vainqueur
		switch (vainqueur) {
			case Grille.JOUEUR1:
				System.out.println("Victoire du joueur 1");
				break;
			case Grille.JOUEUR2:
				System.out.println("Victoire du joueur 2");
				break;
			default:
				System.out.println("Match nul");
		}
		System.out.println("Nombre de branches explorées par le joueur 1 : " + joueur1.getNbBranches() + "\n");
		System.out.println("Nombre de branches explorées par le joueur 2 : " + joueur2.getNbBranches() + "\n");
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *          the arguments
	 */
	public static void main(String[] args) {
		// creation des joueurs et appel de la fonction jouer
		JoueurHumain joueur1 = new JoueurHumain();
		JoueurMinMax joueur2 = new JoueurAlphaBeta(new Heuristique());

		jouer(joueur1, joueur2);
	}

}
