/*
 * TP 3 : Puissance 4
 * @author Tassadit BOUADI.
 */

/**
 * Programme principal du jeu du puissance 4.
 */
public class Puissance4 {

	public static void main(String[] args) {
		// cr�ation des joueurs et appel de la fonction jouer
		JoueurMinMax joueur1 = new JoueurMinMax(new FonctionEvaluationProf());
		JoueurMinMax joueur2 = new JoueurMinMax(new FonctionEvaluationProf());

		jouer(joueur1, joueur2);
	}

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
		int numJoueur = Grille.JOUEUR1; // le joueur 1 commence � jouer

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
			if (joueurCour == joueur1)
				joueurCour = joueur2;
			else
				joueurCour = joueur1;
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
	}

}
