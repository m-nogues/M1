
/**
 * The Class Heuristique.
 */
public class Heuristique implements FonctionEvaluation {

	@Override
	public double evaluation(Grille grille, int joueur) {
		if (grille.estPleine())
			return 0;

		int nbCases = 0, value = 0, adversaire = Grille.joueurSuivant(joueur);

		// Points de positions
		// Horizontale + Diagonale 1
		for (int c = 0; c < Grille.NB_COLONNES / 2; c++)
			for (int l = 0; l < Grille.NB_LIGNES; l++) {
				nbCases = grille.getNbCasesHorizontale(joueur, l, c);
				switch (nbCases) {
					case 4:
						return FonctionEvaluation.MAX;
					case 3:
						value += 100;
						break;
					case 2:
						value += 10;
					case 1:
						value += 1;
				}
				if (l < Grille.NB_LIGNES / 2) {
					nbCases = grille.getNbCasesDiagonale1(joueur, l, c);
					switch (nbCases) {
						case 4:
							return FonctionEvaluation.MAX;
						case 3:
							value += 100;
							break;
						case 2:
							value += 10;
						case 1:
							value += 1;
					}
					nbCases = grille.getNbCasesDiagonale1(adversaire, l, c);
					switch (nbCases) {
						case 4:
							return FonctionEvaluation.MAX;
						case 3:
							value += 1000;
							break;
						case 2:
							value += 100;
						case 1:
							value += 10;
					}
				}
				nbCases = grille.getNbCasesHorizontale(adversaire, l, c);
				switch (nbCases) {
					case 4:
						return FonctionEvaluation.MAX;
					case 3:
						value += 1000;
						break;
					case 2:
						value += 100;
					case 1:
						value += 10;
				}
			}

		// Verticale + Diagonale 2
		for (int l = 0; l < Grille.NB_LIGNES / 2; l++)
			for (int c = 0; c < Grille.NB_COLONNES; c++) {
				nbCases = grille.getNbCasesVerticale(joueur, l, c);
				switch (nbCases) {
					case 4:
						return FonctionEvaluation.MAX;
					case 3:
						value += 100;
						break;
					case 2:
						value += 10;
					case 1:
						value += 1;
				}
				if (c < Grille.NB_COLONNES / 2) {
					nbCases = grille.getNbCasesDiagonale2(joueur, l, c);
					switch (nbCases) {
						case 4:
							return FonctionEvaluation.MAX;
						case 3:
							value += 100;
							break;
						case 2:
							value += 10;
						case 1:
							value += 1;
					}
					nbCases = grille.getNbCasesDiagonale2(adversaire, l, c);
					switch (nbCases) {
						case 4:
							return FonctionEvaluation.MAX;
						case 3:
							value += 1000;
							break;
						case 2:
							value += 100;
						case 1:
							value += 10;
					}
				}
				nbCases = grille.getNbCasesVerticale(adversaire, l, c);
				switch (nbCases) {
					case 4:
						return FonctionEvaluation.MAX;
					case 3:
						value += 1000;
						break;
					case 2:
						value += 100;
					case 1:
						value += 10;
				}
			}

		return value;
	}

}
