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
	int d_lecture;
	chaine20 chaine;
	
	d_lecture = open("./fifo",O_RDONLY);
	if (d_lecture == -1) {
		printf("Open error\n");
		return -1;
	}
	int r;
	if ((r = read(d_lecture, chaine, MAX)) < 0) {
		perror("read failed\n");
		return 1;
	}
	while (r > 0) {
		printf("%s", chaine);
		if ((r = read(d_lecture, chaine, MAX)) < 0) {
			perror("read failed\n");
			return 1;
		}
	}
		
	close(d_lecture);
	
	unlink("./fifo");

	return 0;
}
