#include "userlib/syscall.h"
#include "userlib/libnachos.h"

void routine_envoi(char *s, int taille){
	int nb_envoi = TtySend(s);
	//n_printf("%d\n", nb_envoi);

	if(nb_envoi != taille){
		n_printf("failled nb_envoi = %d/%d \n",nb_envoi, taille);
		Exit(1);
	}
		n_printf("succes envoie\n");
}

void fullChar(){
	routine_envoi("abcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuv", 256);
	Exit(0);
}

void plusChar(){
	routine_envoi("blablabla", 9);
	Exit(0);
}

void unChar(){
	routine_envoi("a", 1);
	Exit(0);
}

int main (){

  char * threadName = "threadName";
  ThreadId thread1, thread2, thread3;
  if((thread1 = threadCreate(threadName,plusChar))==-1){
    PError("creation thread 1 failled");
  }

  if((thread2 = threadCreate(threadName,plusChar))==-1){
    PError("creation thread 2 failled");
  }

  if((thread3 = threadCreate(threadName,unChar))==-1){
    PError("creation thread 3 failled");
  }

  if(Join(thread1)==-1){
    PError("liberation thread 1 failled");
  }

  if(Join(thread2)==-1){
    PError("liberation thread 2 failled");
  }

  if(Join(thread3)==-1){
    PError("liberation thread 3 failled");
  }

  n_printf("Exiting main\n");
  return 0;
}
