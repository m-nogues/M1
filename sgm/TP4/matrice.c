#include <stdlib.h>
#include <stdio.h>
#include <signal.h>
#include <sys/mman.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>


#define NBPAGE 50

#define STEPS 20

int pageAccess = 0;


void handler(int signum, siginfo_t *sip, void *ptr){

	pageAccess++;

	if(mprotect(sip->si_addr - ((int)sip->si_addr%getpagesize()), getpagesize(), PROT_READ) == -1){

		perror("call mprotect failled");
		sleep(1);
		exit(1);
	}
}

int main() {

  int index, fileOpen;
  float * map;
  struct sigaction rerouting, old;


  // openFile
	if( (fileOpen=open("matrixFile", O_RDONLY)) == -1){

		perror("openFile matrixFile failled");
		exit(1);
  }
  rerouting.sa_sigaction = handler;
  sigemptyset(&rerouting.sa_mask);
  rerouting.sa_flags = SA_SIGINFO;

  if(sigaction(SIGSEGV, &rerouting, &old) == -1){
  		perror("routine setup failled");
  		exit(1);
  }
  //mapping file
  if( (map = (float *)mmap(0, getpagesize()*NBPAGE, PROT_NONE, MAP_SHARED, fileOpen, 0)) == -1){
		perror("mapping file failled");
		exit(1);
  }

  //random access

  	for(int i=0; i<STEPS; i++){
			printf("ca passa ici\n");
  		index = rand()%( (getpagesize()*NBPAGE)/sizeof(int));
			printf("%d\n",index);


  		printf("Matrice[%d] = %f\n", index, map[index]);
  	}

  printf("%f%% des pages ont été accédées (%d pages)\n", (pageAccess/(float)NBPAGE)*100, pageAccess);

//unmap
  if(munmap(map, getpagesize()*NBPAGE) == -1){
		perror("unmapping failled");
		exit(1);
	}

  close(fileOpen);

  return 0;
}
