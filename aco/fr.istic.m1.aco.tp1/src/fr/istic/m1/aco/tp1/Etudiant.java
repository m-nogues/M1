/*
 * @author Ma�l Nogues
 * @author Mathieu Grandmontagne
 */
package fr.istic.m1.aco.tp1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * La classe Etudiant.
 */
public class Etudiant {
	
	/** Le pr�nom. */
	private String nom, prenom;
	
	/** La liste de cours. */
	private Set<Cours> cours;
	
	/**
	 * Instantiates a new etudiant.
	 *
	 * @param n the n
	 * @param p the p
	 */
	public Etudiant(String n, String p){
		this.cours = new HashSet<>();
		nom = n;
		prenom = p;
	}
	
	/**
	 * Test si l'�tudiant peut assister au cours donn�.
	 *
	 * @param c le cours
	 * @return true, si l'�tudiant peut assister au cours
	 */
	public boolean assiste(Cours c) {
		if (cours.contains(c))
			return c.assiste(this);
		else return false;
	}
	
	/**
	 * Ajoute un cours.
	 *
	 * @param c le cours
	 */
	public void ajouterCours(Cours c){
		c.inscription(this);
		cours.add(c);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return nom + " " + prenom;
	}
	
	/**
	 * Liste les cours de l'�l�ve.
	 *
	 * @return the string
	 */
	public String listeCours(){
		String ret = toString() + "\n";
		Iterator<Cours> it = cours.iterator();
		while (it.hasNext())
			ret += "\t" + it.next().getNom() + "\n";
		return ret;
	}
}
