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
//modulo 26
char unsigned_mod26 (char a){
	char b  = (a%26 >= 0) ? a%26 : (a%26)+26;
	return b;
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

//fonction de déchiffrage
char* dechifrementCesar(char* s, int cle){
	
	int l = strlen(s);
	char* r = (char*)malloc(sizeof(char)*l);
	
	for(int i=0; i<l; i++){ // pour chaque caractère 
		int t = pos(s[i]);
		r[i] = Asciipos(unsigned_mod26(( t - cle)) );
	}
	return r;
	
}

//recherche de clé et lancement du déchiffrage
void cesar(char* s){
	char e = pos(plusPresent(s));
	
	int cle = e- pos('e');	
	
	printf("%s\n",dechifrementCesar(s,cle));
	
}

int main(){
	//ouverture du fichier
	FILE* f = NULL;
	f = fopen("./exercice1.txt","r+");
	if(f!=NULL){
		char c[1000] = "";
		char ch[1000] = "";
		while(fgets(c,1000, f)!=NULL)
			strcat(ch, c);
		cesar(c);
		
	}
	fclose(f);	
	return 0;
	


}
