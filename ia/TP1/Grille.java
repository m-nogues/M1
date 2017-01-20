
/*
 * TP 1 - IA Informatique : Sudoku
 *
 * @author Tassadit BOUADI.
 */

import java.util.ArrayList;
import java.util.List;

public class Grille {
	private Case[][] _cases;
	private int _taille;
	private int _nbCasesVides;

	public Grille(int n) {
		_taille = n;
		_nbCasesVides = n * n;
		_cases = new Case[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				_cases[i][j] = new Case(i, j);
			}
		}
	}

	public Grille(int n, int[][] grille) {
		_taille = n;
		_nbCasesVides = n * n;
		_cases = new Case[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				_cases[i][j] = new Case(i, j, grille[i][j]);
				if (_cases[i][j].getVal() != 0) {
					_nbCasesVides--;
				}
			}
		}
	}

	public Grille(Grille grille) {
		_taille = grille._taille;
		_nbCasesVides = grille._nbCasesVides;
		_cases = new Case[_taille][_taille];
		for (int i = 0; i < _taille; i++) {
			for (int j = 0; j < _taille; j++) {
				_cases[i][j] = new Case(grille.getCase(i, j));
			}
		}
	}

	public Case getCase(int i, int j) {
		return _cases[i][j];
	}

	public int getNbCasesVides() {
		return _nbCasesVides;
	}

	/*
	 * Fonction qui donne la liste des cases sans valeur (peut être vide)
	 */
	public List<Case> getCasePossible() {
		List<Case> casePoss = new ArrayList<Case>();
		if (_nbCasesVides > 0)
			for (int i = 0; i < _taille; i++)
				for (int j = 0; j < _taille; j++)
					if (_cases[i][j].getVal() == 0)
						casePoss.add(_cases[i][j]);
		return casePoss;
	}

	/*
	 * Fonction qui donne la valeur v � la case c.
	 */
	public void setCase(Case c, int v) {
		if (v != 0) {
			_cases[c.getI()][c.getJ()].setVal(v);
			_nbCasesVides--;
		}
	}

	/*
	 * Fonction qui rend vrai si la valeur v peut �tre donn�e � la case C
	 * c'est-�-dire si la grille respecte toujours les contraintes du Sudoku.
	 */
	public boolean casePossible(Case c, int v) {
		return ligneOK(c, v) && coloneOK(c, v) && regionOK(c, v);
	}

	private boolean ligneOK(Case c, int v) {
		for (int i = 0; i < _taille; i++)
			if (_cases[i][c.getJ()].getVal() == v)
				return false;
		return true;
	}

	private boolean coloneOK(Case c, int v) {
		for (int j = 0; j < _taille; j++)
			if (_cases[c.getI()][j].getVal() == v)
				return false;
		return true;
	}

	private boolean regionOK(Case c, int v) {
		int backi = findI(c), backj = findJ(c);
		for (int i = backi; i < backi + 3; i++)
			for (int j = backj; j < backj + 3; j++)
				if (_cases[i][j].getVal() == v)
					return false;
		return true;
	}

	private int findI(Case c) {
		if (c.getI() < 3)
			return 0;
		if (c.getI() < 6)
			return 3;
		return 6;
	}

	private int findJ(Case c) {
		if (c.getJ() < 3)
			return 0;
		if (c.getJ() < 6)
			return 3;
		return 6;
	}

	public void afficheGrille() {
		int v;
		int dim = (int) Math.sqrt(_taille);
		for (int i = 0; i < _taille; i++) {
			if (i % dim == 0) {
				System.out.print(" ");
				for (int k = 0; k <= _taille; k++)
					System.out.print("--");
				System.out.println();
			}
			for (int j = 0; j < _taille; j++) {
				if (j % dim == 0) {
					System.out.print("|");
				}
				v = _cases[i][j].getVal();
				if (v == 0) {
					System.out.print("  ");
				} else {
					System.out.print(v + " ");
				}
			}
			System.out.println("|");
		}
		System.out.print(" ");
		for (int k = 0; k <= _taille; k++)
			System.out.print("--");
		System.out.println();
	}
}
