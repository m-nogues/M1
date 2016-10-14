#include <unistd.h>
#include <stdlib.h> 
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(){
	int status;
	
	if (fork()!=0) {
		wait(&status);
		printf("%d\n",status);
	} else execl("./partie2", NULL);

	return 0;
}
