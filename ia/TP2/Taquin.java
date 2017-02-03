/*
 * TP 2 - IA  : Taquin
 * 
 * @author Tassadit BOUADI.
 */

import java.util.Vector;


public class Taquin {
	
	public static void main(String[] args) {
		Etat etatInit1, etatInit2, etatFinal1, etatFinal2;
		
		int[][] tabInit1 = {
				{2,8,3},
				{1,6,4},
				{7,0,5}};
		
		int[][] tabInit2 = {
				{2,0,4},
				{1,6,3},
				{7,5,8}};

		FonctionHeuristique1 heurist1 = new FonctionHeuristique1();
		FonctionHeuristique2 heurist2 = new FonctionHeuristique2();
		
		//algo A* avec le 1er �tat initial
		System.out.println("Avec la 1�re heuristique");
		etatInit1 = new Etat(tabInit1, heurist1);
		etatFinal1 = algoAEtoile(etatInit1, heurist1);
		if(etatFinal1 != null){
			//etatFinal1.afficherParcours();
		}
		else{
			System.out.println("Aucune solution");
		}
		System.out.println("\n**********\n\n");
		
		System.out.println("Avec la 2�me heuristique");
		etatInit2 = new Etat(tabInit1, heurist2);
		etatFinal2 = algoAEtoile(etatInit1, heurist2);
		if(etatFinal2 != null){
			//etatFinal2.afficherParcours();
		}
		else{
			System.out.println("Aucune solution");
		}
		System.out.println("\n\n********************\n\n");
		

		//algo A* avec le 2�me �tat initial
		System.out.println("Avec la 1�re heuristique");
		etatInit2 = new Etat(tabInit2, heurist1);
		etatFinal1 = algoAEtoile(etatInit2, heurist1);
		if(etatFinal1 != null){
			//etatFinal1.afficherParcours();
		}
		else{
			System.out.println("Aucune solution");
		}
		System.out.println("\n**********\n\n");
		
		System.out.println("Avec la 2�me heuristique");
		etatInit2 = new Etat(tabInit2, heurist2);
		etatFinal2 = algoAEtoile(etatInit2, heurist2);
		if(etatFinal2 != null){
			//etatFinal2.afficherParcours();
		}
		else{
			System.out.println("Aucune solution");
		}
	}

	
	/**
	 * M�thode qui applique l'algorihtme A*, � partir de l'�tat intial donn�
	 * et en utilisant l'heuristique donn�e.
	 * @param etatInit : l'�tat initial.
	 * @param heurist : l'heuristique utilis�e.
	 * @return l'�tat final.
	 */
	public static Etat algoAEtoile(Etat etatInit, FonctionHeuristique heurist){
		Etat etat, succ;
		Vector<Etat> succs;
		SetTaquin fermes = new SetTaquin();
		FileTaquin ouverts = new FileTaquin();
		int nbNoeuds = 0;
		Etat etatFin = null;
		
		//initialisation des SDD
		//� compl�ter
		
		//boucle sur ouverts
		while((!ouverts.isEmpty()) && (etatFin == null)){
			//� compl�ter
		}//while
					
		//affichage du nombre de noeuds cr��s
		System.out.println("Nombre de noeuds cr��s : " + nbNoeuds);
		
		return etatFin;
	}
}
