/*
 * @author Maël Nogues
 * @author Mathieu Grandmontagne
 */
package fr.istic.m1.aco.tp1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * La classe Cours.
 */
public class Cours {
	
	/** Le nom. */
	private String nom;
	
	/** Les inscrits. */
	private Set<Etudiant> inscrits;
	
	/**
	 * Instancie un nouveau cours.
	 *
	 * @param n le nom
	 */
	public Cours(String n){
		inscrits = new HashSet<>();
		nom = n;
	}

	/**
	 * Test si un étudiant peut assister au cours.
	 *
	 * @param e l'étudiant
	 * @return true, si l'étudiant est inscrit.
	 */
	public boolean assiste(Etudiant e) {
		if (inscrits.contains(e))
			return true;
		else return false;
	}
	
	/**
	 * Inscription au cours.
	 *
	 * @param e l'étudiant
	 */
	public void inscription(Etudiant e){
		inscrits.add(e);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String ret = nom + "\n";
		Iterator<Etudiant> it = inscrits.iterator();
		while (it.hasNext())
			ret += "\t" + it.next().toString() + "\n";
		return ret;
	}

	/**
	 * Renvoie le nom.
	 *
	 * @return le nom
	 */
	public String getNom() {
		return nom;
	}
}
