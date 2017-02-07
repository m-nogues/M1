
/*
 * TP 2 - IA: Taquin
 * @author Tassadit BOUADI.
 */

import java.util.Vector;

public class Etat implements Comparable<Etat> {
	public static final int[][]	ETAT_FINAL1	= { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } };
	public static final int[][]	ETAT_FINAL2	= { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };

	private Etat				_pere;		// l'�tat pr�c�dent
	private int[][]				_taquin;	// la configuration de jeu
	private int					_xVide;		// les coordonn�es de la case vide
	private int					_yVide;
	private Vector<Deplacement>	_coups;		// la suite de d�placements de la
											// case vide
	private int					_nbCoups;
	private int					_valG;		// les valeurs des fonctions
											// associ�es � la configuration
	private int					_valF;

	/**
	 * Constructeur qui recopie l'�tat e dans l'�tat courant.
	 *
	 * @param e
	 *            : l'�tat � cloner.
	 */
	public Etat(Etat e) {
		_pere = e._pere;
		_taquin = new int[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				_taquin[i][j] = e._taquin[i][j];
		_xVide = e._xVide;
		_yVide = e._yVide;
		_coups = new Vector<>();
		for (int i = 0; i < e._nbCoups; i++)
			_coups.add(e._coups.get(i));
		_nbCoups = e._nbCoups;
		_valG = e._valG;
		_valF = e._valF;
	}

	/**
	 * Constructeur.
	 *
	 * @param tab
	 *            : le tableau d�crivant la configuration de jeu.
	 */
	public Etat(int[][] tab) {
		_pere = null;
		_taquin = new int[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				_taquin[i][j] = tab[i][j];
				if (_taquin[i][j] == 0) {
					_xVide = i;
					_yVide = j;
				}
			}
		_coups = new Vector<>();
		_nbCoups = 0;
		_valG = 0;
		_valF = _valG;
	}

	/**
	 * Constructeur.
	 *
	 * @param tab
	 *            : le tableau d�crivant la configuration de jeu.
	 */
	public Etat(int[][] tab, FonctionHeuristique heurist) {
		_pere = null;
		_taquin = new int[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				_taquin[i][j] = tab[i][j];
				if (_taquin[i][j] == 0) {
					_xVide = i;
					_yVide = j;
				}
			}
		_coups = new Vector<>();
		_nbCoups = 0;
		_valG = 0;
		int valH = heurist.heuristique(this);
		_valF = _valG + valH;
	}

	/**
	 * M�thode qui affiche la s�quence de configurations.
	 */
	public void afficherParcours() {
		if (_pere != null)
			_pere.afficherParcours();
		System.out.println(this);
	}

	/**
	 * M�thode de l'interface Comparable, � impl�menter.
	 *
	 * @param e
	 *            : l'�tat avec lequel comparer l'�tat courant.
	 * @return -1, 0 ou 1 selon que l'�tat e est inf�rieur, �gal ou sup�rieur �
	 *         l'�tat courant, respectivement.
	 */
	@Override
	public int compareTo(Etat e) {
		int res = -1;

		// classement dans l'ordre croissant de f
		// puis dans l'ordre d�croissant de g
		if (e._valF < _valF)
			res = -1;
		else if (e._valF > _valF)
			res = 1;
		else if (e._valG > _valG)
			res = -1;
		else if (e._valG < _valG)
			res = 1;
		else
			res = 0;

		return res;
	}

	/**
	 * M�thode qui dit s'il est possible de d�placer la case vide, dans la
	 * direction donn�e par d.
	 *
	 * @param d
	 *            : la direction pour d�placer la case vide.
	 * @return vrai si la case vide peut �tre d�plac�e dans la direction d.
	 */
	private boolean deplacementPossible(Deplacement d) {
		boolean depl = true;

		switch (d) {
			case haut:
				if (_yVide == 2)
					depl = !depl;
				break;
			case bas:
				if (_yVide == 0)
					depl = !depl;
				break;
			case gauche:
				if (_xVide == 0)
					depl = !depl;
				break;
			case droite:
				if (_xVide == 2)
					depl = !depl;
				break;
		}

		return depl;
	}

	/**
	 * M�thode qui dit si 2 �tats sont �gaux, au niveau des configurations.
	 *
	 * @param e
	 *            : l'�tat avec lequel comparer l'�tat courant.
	 * @return vrai si les �tats correspondent � la m�me configuration.
	 */
	public boolean equals(Etat e) {
		boolean eq = true;

		for (int i = 0; i < 3 && eq; i++)
			for (int j = 0; j < 3 && eq; j++)
				eq = _taquin[i][j] == e._taquin[i][j];

		return eq;
	}

	/**
	 * M�thode qui dit si l'�tat courant est un �tat final.
	 *
	 * @return vrai si l'�tat courant correspond � un des 2 �tats finaux.
	 */
	public boolean estFinal() {
		Etat eFinal1 = new Etat(ETAT_FINAL1);
		Etat eFinal2 = new Etat(ETAT_FINAL2);

		return equals(eFinal1) || equals(eFinal2);
	}

	/**
	 * M�thode qui �tend l'�tat courant, en d�pla�ant la case vide dans la
	 * direction donn�e par d.
	 *
	 * @param d
	 *            : la direction pour d�placer la case vide.
	 * @return l'�tat cr�� � partir de l'�tat courant
	 */
	private Etat etendEtat(Deplacement d, FonctionHeuristique heurist) {
		Etat etat = new Etat(this);
		etat._pere = this;
		int tmp;

		// d�placement de la case vide

		if (deplacementPossible(d)) {
			switch (d) {
				case haut:
					etat._yVide++;
					tmp = etat._taquin[etat._xVide][etat._yVide];
					etat._taquin[etat._xVide][etat._yVide] = 0;
					etat._taquin[etat._xVide][etat._yVide - 1] = tmp;
					break;
				case bas:
					etat._yVide--;
					tmp = etat._taquin[etat._xVide][etat._yVide];
					etat._taquin[etat._xVide][etat._yVide] = 0;
					etat._taquin[etat._xVide][etat._yVide + 1] = tmp;
					break;
				case gauche:
					etat._xVide--;
					tmp = etat._taquin[etat._xVide][etat._yVide];
					etat._taquin[etat._xVide][etat._yVide] = 0;
					etat._taquin[etat._xVide + 1][etat._yVide] = tmp;
					break;
				case droite:
					etat._xVide++;
					tmp = etat._taquin[etat._xVide][etat._yVide];
					etat._taquin[etat._xVide][etat._yVide] = 0;
					etat._taquin[etat._xVide - 1][etat._yVide] = tmp;
					break;
			}
			etat._coups.addElement(d);
			etat._nbCoups++;
			etat._valG++;
			etat._valF = etat._valG + heurist.heuristique(etat);
		} else
			return null;

		// mise � jour des fonctions d'�valuation
		// � compl�ter...

		return etat;
	}

	/**
	 * M�thode qui donne le p�re de l'�tat courant.
	 *
	 * @return le p�re de l'�tat courant.
	 */
	public Etat getPere() {
		return _pere;
	}

	/**
	 * M�thode qui donne les �tats successeurs de l'�tat courant, calcul�s selon
	 * l'emplacement de la case vide et en utilisant la fonction heuristique
	 * donn�e.
	 *
	 * @return les �tats successeurs.
	 */
	public Vector<Etat> getSuccesseurs(FonctionHeuristique heurist) {
		Vector<Etat> succs = new Vector<>();
		Etat e = null;

		for (Deplacement d : Deplacement.values())
			if ((e = etendEtat(d, heurist)) != null)
				succs.add(e);

		return succs;
	}

	/**
	 * M�thode qui donne la valeur de la case de coordonn�es donn�es.
	 *
	 * @param i
	 *            : l'abscisse de la case.
	 * @param j
	 *            : l'ordonn�e de la case.
	 * @return la valeur de la case.
	 */
	public int getVal(int i, int j) {
		int val = -1;

		if (i >= 0 && i < 3 && j >= 0 && j < 3)
			val = _taquin[i][j];

		return val;
	}

	/**
	 * M�thode qui donne la valeur de la fonction d'�valuation f.
	 *
	 * @return la valeur de la fonction f.
	 */
	public int getValF() {
		return _valF;
	}

	/**
	 * M�thode qui donne la valeur de la fonction d'�valuation g.
	 *
	 * @return la valeur de la fonction g.
	 */
	public int getValG() {
		return _valG;
	}

	/**
	 * M�thode qui donne l'abscisse du nombre n.
	 *
	 * @param n
	 *            : le nombre consid�r�.
	 * @return l'abscisse de n.
	 */
	public int getX(int n) {
		int x = -1;

		for (int i = 0; i < 3 && x == -1; i++)
			for (int j = 0; j < 3 && x == -1; j++)
				if (_taquin[i][j] == n)
					x = i;

		return x;
	}

	/**
	 * M�thode qui donne ordonn�e du nombre n.
	 *
	 * @param n
	 *            : le nombre consid�r�.
	 * @return ordonn�e de n.
	 */
	public int getY(int n) {
		int y = -1;

		for (int i = 0; i < 3 && y == -1; i++)
			for (int j = 0; j < 3 && y == -1; j++)
				if (_taquin[i][j] == n)
					y = j;

		return y;
	}

	/**
	 * M�thode qui donne la repr�sentation de l'�tat, sous forme de cha�ne.
	 *
	 * @return la cha�ne repr�sentant l'�tat.
	 */
	@Override
	public String toString() {
		String str = "";

		// la configuration de jeu
		str += "-------\n";
		for (int i = 0; i < 3; i++) {
			str += "|";
			for (int j = 0; j < 3; j++) {
				if (j > 0)
					str += " ";
				str += _taquin[i][j];
			}
			str += "|\n";
		}
		str += "-------\n";

		// les valeurs des fonctions
		str += "f = " + _valF + " (g = " + _valG + ")\n";

		// les d�placements de la case vide
		str += _nbCoups + " d�placements : ";
		for (int i = 0; i < _nbCoups; i++) {
			if (i > 0)
				str += " - ";
			switch (_coups.get(i)) {
				case haut:
					str += "haut";
					break;
				case bas:
					str += "bas";
					break;
				case gauche:
					str += "gauche";
					break;
				case droite:
					str += "droite";
					break;
			}
		} // for i

		return str;
	}

}
