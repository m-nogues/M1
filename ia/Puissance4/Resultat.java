/*
 * TP 3  : Puissance 4
 * 
 * @author Tassadit BOUADI.
 */

/**
 * Résultat composé de la valeur de la grille 
 * si on joue dans la colonne donnée.
 * 
 */
public class Resultat{
	
	/** The colonne. */
	private int _colonne;
	
	/** The valeur. */
	private double _valeur;
	
	/**
	 * Instantiates a new resultat.
	 */
	public Resultat(){
		_colonne = -1;
		_valeur = 0;
	}
	
	/**
	 * Instantiates a new resultat.
	 *
	 * @param c
	 *          the c
	 * @param v
	 *          the v
	 */
	public Resultat(int c, double v){
		_colonne = c;
		_valeur = v;
	}
	
	/**
	 * Instantiates a new resultat.
	 *
	 * @param r
	 *          the r
	 */
	public Resultat(Resultat r){
		_colonne = r._colonne;
		_valeur = r._valeur;
	}
	
	/**
	 * Copie.
	 *
	 * @return the resultat
	 */
	public Resultat copie(){
		return new Resultat(_colonne, _valeur);
	}
	
	/**
	 * Gets the colonne.
	 *
	 * @return the colonne
	 */
	public int getColonne(){
		return _colonne;
	}
	
	/**
	 * Gets the valeur.
	 *
	 * @return the valeur
	 */
	public double getValeur(){
		return _valeur;
	}
	
	/**
	 * Sets the colonne.
	 *
	 * @param c
	 *          the new colonne
	 */
	public void setColonne(int c){
		_colonne = c;
	}
	
	/**
	 * Sets the valeur.
	 *
	 * @param v
	 *          the new valeur
	 */
	public void setValeur(double v){
		_valeur = v;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "(" + _colonne + ":" + _valeur + ")";
	}
}
