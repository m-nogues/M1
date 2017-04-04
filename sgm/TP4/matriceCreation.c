#include <stdlib.h>
#include <stdio.h>
#define NBPAGE 50
#define taille 32*NBPAGE
int main (void)
{
	 int **matrice = malloc (sizeof *matrice * taille);
	 if (matrice != NULL)
	 {
			/* Initialisation */
			{
				 int i;
				 for (i = 0; i < taille; i++)
				 {
						int j;
						matrice[i] = malloc (sizeof *matrice[i] * taille);
						for (j = 0; j < taille; j++)
						{
							 matrice[i][j] = rand () / 1000;
						}
				 }
			}
			/* Generation du fichier */
#define FNAME "matrixFile"
			{
				 FILE *fichier = fopen (FNAME, "w" );
				 if (fichier == NULL)
				 {
						perror (FNAME);
				 }
				 else
				 {
						int i;
						for (i = 0; i < taille; i++)
						{
							 int j;
							 for (j = 0; j < taille; j++)
							 {
									fprintf (fichier, "%5d", matrice[i][j]);
							 }
							 fprintf (fichier, "\n" );
						}
				 }
			}
			/* Liberation de la matrice */
			{
				 int i;
				 for (i = 0; i < taille; i++)
				 {
						free (matrice[i]), matrice[i] = NULL;
				 }
				 free (matrice);
			}
	 }
	 return 0;
}
