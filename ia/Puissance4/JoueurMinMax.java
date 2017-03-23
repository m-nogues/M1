/**
 * The Class JoueurMinMax.
 */
public class JoueurMinMax implements Joueur {

	/** The eval. */
	protected FonctionEvaluation eval;

	/** The nb branches. */
	private int nbBranches = 0, joueurBase;

	/**
	 * Instantiates a new joueur min max.
	 *
	 * @param f
	 *          the f
	 */
	public JoueurMinMax(FonctionEvaluation f) {
		eval = f;
	}

	/*
	 * (non-Javadoc)
	 * @see Joueur#coup(Grille, int)
	 */
	@Override
	public Resultat coup(Grille grille, int joueur) {
		joueurBase = joueur;
		return minmax(grille, joueur, Puissance4.PROFONDEUR);
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
	 * Minmax.
	 *
	 * @param test
	 *          the test
	 * @param joueur
	 *          the joueur
	 * @param profondeur
	 *          the profondeur
	 * @return the resultat
	 */
	private Resultat minmax(Grille test, int joueur, int profondeur) {
		double[] values = new double[Grille.NB_COLONNES];
		Grille grille;
		for (int i : test.generateurCoups()) {
			grille = test.copie();
			grille.joueEn(joueur, i);
			nbBranches++;
			if (test.coupGagnant(joueur, i))
				return new Resultat(i, FonctionEvaluation.MAX);
			else if (test.coupGagnant(Grille.joueurSuivant(joueur), i))
				return new Resultat(i, FonctionEvaluation.MIN);

			values[i] = value(grille, Grille.joueurSuivant(joueur), profondeur, false);
		}

		double max = FonctionEvaluation.MIN, equals = 0;
		int col = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] > max) {
				max = values[i];
				col = i;
			}
			equals += values[i];
		}

		if (equals / values.length == max)
			col = Grille.NB_COLONNES / 2;
		if (test.peutJouerEn(col))
			return new Resultat(col, max);
		return new Resultat(test.getCoupAleatoire(), 0);
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
	 * @return the double
	 */
	private double value(Grille grille, int joueur, int profondeur, boolean isMaxNode) {
		nbBranches++;
		if (grille.estPleine())
			return 0;
		if (profondeur <= 0)
			return eval.evaluation(grille, joueurBase);
		double[] values = new double[Grille.NB_COLONNES];
		Grille test;
		for (int i : grille.generateurCoups()) {
			test = grille.copie();
			test.joueEn(joueur, i);
			if (grille.coupGagnant(joueur, i))
				if (!isMaxNode)
					return FonctionEvaluation.MAX;
				else
					return FonctionEvaluation.MIN;
			values[i] = value(grille, Grille.joueurSuivant(joueur), profondeur - 1, !isMaxNode);
		}
		double max = FonctionEvaluation.MIN, min = FonctionEvaluation.MAX;
		for (double d : values) {
			if (d > max)
				max = d;
			if (d < min)
				min = d;
		}
		if (isMaxNode)
			return max;
		return min;
	}

}
