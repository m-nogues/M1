
public class JoueurMinMax implements Joueur {

	private FonctionEvaluation	eval;
	private static int			MAX	= 7;

	public JoueurMinMax(FonctionEvaluation f) {
		eval = f;
	}

	@Override
	public Resultat coup(Grille grille, int joueur) {
		return new Resultat(getCol(grille, joueur, 0), joueur);
	}

	private int getCol(Grille grille, int joueur, int rec) {
		int col = -1;
		Grille test;
		
		for (int i : grille.generateurCoups()){
			test = grille.copie();
			test.joueEn(joueur, i);
			if (eval.evaluation(test, joueur) == FonctionEvaluation.MAX)
				return i;
			
		}
		
		return col;
	}

}
