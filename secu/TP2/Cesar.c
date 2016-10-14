#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

//fonction qui récupère la position dans l'alphabet
int pos(char a) {
	return ((int) a) - 97;
}

//fonction qui donne la valeur en ascii
char unsigned_mod26(char a) {
	char b = (a % 26 >= 0) ? a % 26 : (a % 26) + 26;
	return b;
}

void decrypter(char message[1024], int cle, int taille) {
	for (int i = 0; i < taille; i++) {
		if (message[i] >= 'A' && message[i] <= 'Z')
			message[i] = (char) (((message[i] - cle - 'A' + 26) % 26) + 'A');
		else if (message[i] >= 'a' && message[i] <= 'z')
			message[i] = (char) (((message[i] - cle - 'a' + 26) % 26) + 'a');
	}
}

int max(int tab[26]) {
	int k, nbApparitions = 0;
	for (int i = 0; i < 26; i++) {
		if (tab[i] > nbApparitions) {
			k = i;
			nbApparitions = tab[i];
		}
	}
	return k;
}

void main() {
	int tab[26] = { 0 }, cle;
	char message[1024] =
			"vcfgrwqwfsbhfsntowbsobgfsbhfsnqvsnjcigsghqsoixcifrviwtshseicwbsgojsnjcigdogeisjcigoihfsgofhwgobgjcigbsrsjsnqwfqizsfrobgzsgfisgzsgxcifgcijfopzsgeiojsqzsggwubsgrsjchfsdfctsggwcbdofzseiszsghhcbashwsf";

	for (int i = 0; message[i] != '\0'; i++)
		tab[pos(message[i])]++;

	cle = (max(tab) - pos('e')) % 26;

	decrypter(message, cle, strlen(message));
	printf("%s\n", message);
}
