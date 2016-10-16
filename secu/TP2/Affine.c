#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>


//fonction qui récupère la position dans l'alphabet
int pos(char a){
	return  ((int) a)-97;
}
//position dans l'alphabet vers ASCII
int Asciipos(char a){
	return  ((int) a) +97;
}
//realise le modulo 26 sur un char
unsigned int unsigned_mod26 (unsigned char a){
	char b  = (a%26 >= 0) ? a%26 : (a%26)+26;
	return b;
}

//calcul de l'inverse modulaire
unsigned int inverse_modulaire(unsigned int a, unsigned int b){
	unsigned int r = a, rp = b, u = 1, v = 0, up = 0, vp= 1, q, rs, us, vs;
	while(rp != 0){
		q = r/rp;
		rs = r;
		us = u;
		vs = v;
		r = rp;
		u = up;
		v = vp;
		rp = rs - q*rp;
		up = us - q*u;
		vp = vs - q*vp;
	}
	return u;
}
//cherche le maximun dans un tableau
int max(int tab[26]){
	int k,nbApparitions = 0;
	for(int i = 0; i< 26; i++){
		if (tab[i] > nbApparitions){
			k = i;
			nbApparitions = tab[i];
		}
	}
	return k;
} 
//cherche la lettre la plus presente dans une chaine de caracteres
int plusPresent(char* s){
	int tab[26];
	int nbApparitions = 0;
	int taille = strlen(s);
	for (int i = 0; i < 26; i++) {
		tab[i] = 0;
	}
	for(int i = 0; i<taille; i++) {
		tab[s[i]-97]++;
	}
	nbApparitions = max(tab);
	return Asciipos(nbApparitions);	
	
}



char* dechifrementAffine(char* s, int ap, int b){
	int l = strlen(s);
	char* r = (char*)malloc(sizeof(char)*l);
	for(int i=0; i<l; i++){ // pour chaque caractère 
		int t = pos(s[i]);	//on recup la valeur de 0 a 26
		//calcul de la lettre a dechiffrer
		int tmp = unsigned_mod26(ap*(26-b)); 
		t=unsigned_mod26(ap * t + tmp );	
		r[i]= Asciipos(t);//lettre dechiffrer en ASCII
	}
	return r;
	
}





void affine(char* s){
	
	//calcul des clé possible grace a le recherche de la lettre e dans le tableau avec le nombre d'occurences 
	int premier[12] = {1,3,5,7,9,11,15,17,19,21,23,25};
	char e = pos(plusPresent(s));//on cherche le max considere comme la lettre e
	for(int a = 0; a<12; a++ ){//parcours des premier
		for(int b = 0; b<26; b++){ //parcours des 26 lettre
			
			if ( unsigned_mod26(premier[a] * 4 	+ b )== e){ //si cle possible
				printf("A : %d , B : %d\n", premier[a], b);
				int ap = inverse_modulaire(premier[a],26);//calcul inverse modulaire
				printf("inverse_modulaire = %d\n",unsigned_mod26(ap));
				printf("%s\n",dechifrementAffine(s, ap, b));//on déchiffre  la chaine avec la clé et l'inverse modulaire
			}
		}
		
	}	


}
int main(){
	//ouverture du fichier
	FILE* f = NULL;
	f = fopen("./exercice2.txt","r+");
	if(f!=NULL){
		char c[1000] = "";
		char ch[1000] = "";
		while(fgets(c,1000, f)!=NULL)
			strcat(ch, c);

		affine(ch);
	}
	fclose(f);	
	return 0;
	


}



