#include <stdio.h>
#include <memory.h>
#include <unistd.h>
#include <stdlib.h> 
#include <stdlib.h>
#include <stdio.h>


int main( int argc, char ** argv ) {
	char c[20];
	/* create the pipe */
	int  fd[2];
	if (pipe(fd) == -1) {
		perror("pipe creation failed\n");
		return 1;
	}

	/* create the child */
	pid_t pid;
	if ((pid = fork()) < 0) {
		perror("fork failed\n");
		return 2;
	}

	if (pid == 0) {
		char buffer[24];

		while (read(fd[0], buffer, 24) != 0)
			printf("child reads %s", buffer);

	} else {
		if (fgets(c,20,stdin) == NULL) {
			perror("fgets failed\n");
			return 3;
		}
		while (c[0] != '\n'){
			write(fd[1], c, strlen(c)+1);
			if (fgets(c,20,stdin) == NULL) {
				perror("fgets failed\n");
				return 3;
			}
		}
	}

	close(fd[0]);
	close(fd[1]); 
	return 0;
}
