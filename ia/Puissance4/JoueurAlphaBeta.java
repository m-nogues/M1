
public class JoueurAlphaBeta extends JoueurMinMax {

	public JoueurAlphaBeta(FonctionEvaluation f) {
		super(f);
	}
	
	public Resultat coup(Grille grille, int joueur){
		int col = 0;
		
		return new Resultat(col, joueur);
	}

}
