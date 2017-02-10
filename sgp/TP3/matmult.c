#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <stdlib.h>
#include <time.h>


#define MATRICE_SIZE 64 
#define NB_SLAVE 8  //nb threads


// declaration des 3 matrices
float m1[MATRICE_SIZE][MATRICE_SIZE], m2[MATRICE_SIZE][MATRICE_SIZE], result[MATRICE_SIZE][MATRICE_SIZE];



void affiche_matrice(float m[MATRICE_SIZE][MATRICE_SIZE]) {
	for (int x = 0; x < MATRICE_SIZE; x++) {
		for (int y = 0; y < MATRICE_SIZE; y++)
			fprintf(stdout, "[%f] ", m[x][y]);
		fprintf(stdout, "\n");
	}
}



float float_random() {
	return ((rand()/(float)RAND_MAX) * 100);
}



void * matmult(void * arg) {
	int ligne, thread_nb = *((int *)arg);
	
	for (int ligne_mod = 0; ligne_mod < MATRICE_SIZE/NB_SLAVE;  ligne_mod++) {
		// affectation de la ligne à calculer au thread numéro ligne modulo NB_SLAVE
		ligne = (thread_nb) + (ligne_mod * NB_SLAVE);
		printf("thread nb = %d\n ligne n° %d\n", thread_nb, ligne);

		//chaque collones
		for (int res_col = 0; res_col < MATRICE_SIZE; res_col++)
			for (int m_col = 0; m_col < MATRICE_SIZE; m_col++)
				result[ligne][res_col] += m1[ligne][m_col] * m2[m_col][res_col];
	}
	
	pthread_exit(0);
}



void * create_threads() {
	pthread_t slaves[NB_SLAVE];
	int tab_id[NB_SLAVE];
	
	
	for (int id_fils= 0; id_fils <  NB_SLAVE; id_fils++) {
		tab_id[id_fils] = id_fils;
		if (pthread_create(&slaves[id_fils], NULL, matmult, &tab_id[id_fils]) != 0)
			fprintf(stderr, "%s%d\n", "Fail to create thread n°", id_fils);
	}
	
	for (int wait_fils = 0; wait_fils < NB_SLAVE; wait_fils++)
		if (pthread_join(slaves[wait_fils], NULL) != 0)
			fprintf(stderr, "%s%d\n", "FAIL to wait the end of the thread n°", wait_fils);

	pthread_exit(0);
}



int main() {
	// result matrice
	for (int i = 0; i < MATRICE_SIZE; i++)
		for (int j = 0; j < MATRICE_SIZE ; j++)
			result[i][j] = 0.0;

	// Initialize two others matrices whit random values
	for (int i = 0; i < MATRICE_SIZE; i++) {
		for (int j = 0; j < MATRICE_SIZE ; j++) {
			m2[i][j] = float_random();
			m1[i][j] = float_random();
		}
	}

	// Display the the matrices
	fprintf(stdout, "\n%s\n", "Matrice 1:");
	affiche_matrice(m1);
	fprintf(stdout, "\n%s\n", "Matrice 2:");
	affiche_matrice(m2);

	// Master thread
	pthread_t master_thread;
	if (pthread_create(&master_thread, NULL, &create_threads, NULL) != 0)
		fprintf(stderr, "%s\n", "FAIL to  create the master thread");

	// Wait the master thread
	if (pthread_join(master_thread, NULL) != 0) 
		fprintf(stderr, "%s\n", "Fail to  Wait the end of the master thread");

	// Then display the result
	fprintf(stdout, "\n%s\n", "The result:");
	affiche_matrice(result);

	// End
	return 0;

}
