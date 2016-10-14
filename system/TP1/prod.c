#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#define  MAX 20
typedef char chaine20[MAX];

int main(){
	int d_ecriture;
	chaine20  chaine;
	
	mknod("./fifo", S_IFIFO | 0666, 0);
	d_ecriture = open("./fifo",O_WRONLY);
	if (d_ecriture == -1) {
		printf("Open error");
		return -1;
	}
	printf("Write a string\n");
	if (fgets(chaine, MAX, stdin) == NULL) {
		perror("fgets failed\n");
		return 1;
	}
	while (chaine[0] != '\n'){
		write(d_ecriture, chaine, strlen(chaine)+1);
		if (fgets(chaine, MAX, stdin) == NULL) {
			perror("fgets failed\n");
			return 1;
		}
	}
	
	close(d_ecriture);

	return 0;
}
