#include "userlib/syscall.h"
#include "userlib/libnachos.h"

SemId wait;

void routineWAit(){
  n_printf("Entering routineWAit\n");
  if(P(wait)==-1){
    PError("p sur semaphore failled");
    Exit(1);
  }
  n_printf("Exiting routineWAit\n");
  Exit(0);
}



void routineDelock(){
  n_printf("Entering routineDelock\n");
  int i = 0;
  for (i; i<2; i++){
    if(V(wait)==-1){
      PError("v sur semaphore failed");
      Exit(1);
    }
  }
  n_printf("Exiting routineDelock\n");
  Exit(0);
}


int main(){
  n_printf("Entering main\n");
  if((wait = SemCreate("sema",0))==-1){
    PError("semaphore creation failled");
    Exit(1);
  }
  char * threadName = "threadName";
  ThreadId thread1, thread2, thread3;
  if((thread1 = threadCreate(threadName,routineWAit))==-1){
    PError("creation thread 1 failled");
  }
  n_printf("Test thread1 passed\n");
  if((thread2 = threadCreate(threadName,routineWAit))==-1){
    PError("creation thread 2 failled");
  }
  n_printf("Test thread2 passed\n");

  if((thread3 = threadCreate(threadName,routineDelock))==-1){
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
