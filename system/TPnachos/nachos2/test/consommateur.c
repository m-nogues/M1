#include "userlib/syscall.h"
#include "userlib/libnachos.h"

#define TAILLE 257

void routine_reception(){

	char s[TAILLE];
	int nb_recu;

	if((nb_recu = TtyReceive(s, TAILLE)) < 0){

		PError("Failled Reception de la chaine");
		Exit(1);
	}

	n_printf("Succes Chaine recue avec : %d caractere\n", nb_recu);
}



int main (){

  char * threadName = "threadName";
  ThreadId thread1, thread2, thread3;
  if((thread1 = threadCreate(threadName,routine_reception))==-1){
    PError("creation thread 1 failled");
  }
  n_printf("Test thread1 passed\n");
  if((thread2 = threadCreate(threadName,routine_reception))==-1){
    PError("creation thread 2 failled");
  }
  n_printf("Test thread2 passed\n");

  if((thread3 = threadCreate(threadName,routine_reception))==-1){
    PError("creation thread 3 failled");
  }
  n_printf("Test thread3 passed\n");

  if(Join(thread1)==-1){
    PError("liberation thread 1 failled");
  }
  n_printf("Test Join thread1 passed\n");

  if(Join(thread2)==-1){
    PError("liberation thread 2 failled");
  }
  n_printf("Test Join thread2 passed\n");

  if(Join(thread3)==-1){
    PError("liberation thread 3 failled");
  }
  n_printf("Test Join thread3 passed\n");

  n_printf("Exiting main\n");
  return 0;
}
