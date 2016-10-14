/*
 * @author Maël Nogues
 * @author Mathieu Grandmontagne
 */
package fr.istic.m1.aco.tp1;

/**
 * La classe Main.
 */
public class Main {

	/**
	 * La methode main.
	 *
	 * @param args les arguments
	 */
	public static void main(String[] args) {
		Cours aco = new Cours("aco");
		Cours comp = new Cours("comp");
		Etudiant grandmontagne = new Etudiant("Grandmontagne", "Mathieu");
		Etudiant nogues = new Etudiant("Nogues", "Maël");
		grandmontagne.ajouterCours(comp);
		grandmontagne.ajouterCours(aco);
		nogues.ajouterCours(comp);
		System.out.println(aco.toString());
		System.out.println(comp.toString());
		System.out.println(nogues.listeCours());
		System.out.println(grandmontagne.listeCours());
	}

}
