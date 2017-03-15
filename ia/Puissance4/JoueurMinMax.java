
public class JoueurMinMax implements Joueur {

	private FonctionEvaluation eval;

	private static int PROFONDEUR = 5;

	public JoueurMinMax(FonctionEvaluation f) {
		eval = f;
	}

	@Override
	public Resultat coup(Grille grille, int joueur) {
		return minmax(grille, joueur, PROFONDEUR);
	}

	private Resultat minmax(Grille test, int joueur, int profondeur) {
		return min(test, joueur, profondeur);
	}

	private Resultat min(Grille test, int joueur, int profondeur) {
		int col = -1;
		double val = FonctionEvaluation.MAX, tmp;
		for (int i : test.generateurCoups()) {
			Grille grille = test.copie();
			if (test.coupGagnant(joueur, i))
				return new Resultat(i, eval.evaluation(grille, joueur));
			grille.joueEn(joueur, i);
			tmp = val;
			if (profondeur > 0)
				val = Math.min(val, max(grille, joueur, profondeur - 1).getValeur());
			else
				val = Math.min(val, eval.evaluation(grille, joueur));
			if (tmp > val)
				col = i;
		}
		return new Resultat(col, val);
	}

	private Resultat max(Grille test, int joueur, int profondeur) {
		int col = -1;
		double val = FonctionEvaluation.MIN, tmp;

		for (int i : test.generateurCoups()) {
			Grille grille = test.copie();
			if (grille.coupGagnant(Grille.joueurSuivant(joueur), i))
				return new Resultat(i, eval.evaluation(grille, Grille.joueurSuivant(joueur)));
			grille.joueEn(Grille.joueurSuivant(joueur), i);
			tmp = val;
			if (profondeur > 0)
				val = Math.max(val, min(grille, joueur, profondeur - 1).getValeur());
			else
				val = Math.max(val, eval.evaluation(grille, Grille.joueurSuivant(joueur)));
			if (tmp < val)
				col = i;
		}
		return new Resultat(col, val);
	}

}
