
public class JoueurMinMax implements Joueur {
	
	private FonctionEvaluation eval;
	
	public JoueurMinMax(FonctionEvaluation f) {
		eval = f;
	}

	@Override
	public Resultat coup(Grille grille, int joueur) {
		int col = 0, val = 0;
		double res = 0;
		
		int coups[] = grille.generateurCoups();
		for (int i : coups){
			if ((res = eval.evaluation(grille, joueur)) == FonctionEvaluation.MAX){
				col = i;
				break;
			} else if (res == 0)
				val = i;
		}
		
		if (res != FonctionEvaluation.MAX)
			col = val;
		
		
		return new Resultat(col, joueur);
	}

	
}
