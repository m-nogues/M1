/**
 * The Class JoueurAlphaBeta.
 */
public class JoueurAlphaBeta extends JoueurMinMax {

	/** The nb branches. */
	private int nbBranches = 0, joueurBase;

	/**
	 * Instantiates a new joueur alpha beta.
	 *
	 * @param f
	 *          the f
	 */
	public JoueurAlphaBeta(FonctionEvaluation f) {
		super(f);
	}

	/**
	 * Alpha beta.
	 *
	 * @param test
	 *          the test
	 * @param joueur
	 *          the joueur
	 * @param profondeur
	 *          the profondeur
	 * @return the resultat
	 */
	private Resultat alphaBeta(Grille test, int joueur, int profondeur) {
		double alpha = FonctionEvaluation.MIN, value = 0, equals = 0;
		Grille grille;
		int col = 0;
		for (int i : test.generateurCoups()) {
			grille = test.copie();
			grille.joueEn(joueur, i);
			nbBranches++;
			if (test.coupGagnant(joueur, i)) {
				return new Resultat(i, FonctionEvaluation.MAX);
			} else if (test.coupGagnant(Grille.joueurSuivant(joueur), i))
				return new Resultat(i, FonctionEvaluation.MIN);

			value = value(grille, joueur, profondeur, false, alpha, FonctionEvaluation.MAX);
			equals += value;
			if (value > alpha) {
				col = i;
				alpha = value;
			}
		}

		if (equals / Grille.NB_COLONNES == value)
			col = Grille.NB_COLONNES / 2;
		if (test.peutJouerEn(col))
			return new Resultat(col, alpha);
		return new Resultat(test.getCoupAleatoire(), 0);

	}

	/*
	 * (non-Javadoc)
	 * @see JoueurMinMax#coup(Grille, int)
	 */
	@Override
	public Resultat coup(Grille grille, int joueur) {
		joueurBase = joueur;
		return alphaBeta(grille, joueur, Puissance4.PROFONDEUR);
	}

	/**
	 * Gets the nb branches.
	 *
	 * @return the nb branches
	 */
	@Override
	public int getNbBranches() {
		return nbBranches;
	}

	/**
	 * Value.
	 *
	 * @param grille
	 *          the grille
	 * @param joueur
	 *          the joueur
	 * @param profondeur
	 *          the profondeur
	 * @param isMaxNode
	 *          the is max node
	 * @param alpha
	 *          the alpha
	 * @param beta
	 *          the beta
	 * @return the double
	 */
	private double value(Grille grille, int joueur, int profondeur, boolean isMaxNode, double alpha, double beta) {
		nbBranches++;
		if (grille.estPleine())
			return 0;
		if (profondeur <= 0)
			return eval.evaluation(grille, joueurBase);
		Grille test;
		if (isMaxNode) {
			for (int i : grille.generateurCoups()) {
				test = grille.copie();
				test.joueEn(joueur, i);
				if (grille.coupGagnant(joueur, i))
					return FonctionEvaluation.MIN;
				alpha = Math.max(alpha, value(grille, joueur, profondeur - 1, !isMaxNode, alpha, beta));
				if (alpha >= beta)
					return alpha;
			}
			return alpha;
		}
		for (int i : grille.generateurCoups()) {
			test = grille.copie();
			test.joueEn(joueur, i);
			if (grille.coupGagnant(joueur, i))
				return FonctionEvaluation.MAX;
			beta = Math.max(alpha, value(grille, joueur, profondeur - 1, !isMaxNode, alpha, beta));
			if (beta <= alpha)
				return beta;
		}
		return beta;
	}
}
