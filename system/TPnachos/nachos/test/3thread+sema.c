#include "userlib/syscall.h"
#include "userlib/libnachos.h"

SemId wait;

void routineWAit(){
  if(P(wait)==-1){
    PError("p sur semaphore failled");
    Exit(1);
  }
  Exit(0);
}



void routineDelock(){
  for (int i =0; i<2; i++){
    if(V(wait)==-1){
      PError("v sur semaphore failed");
      Exit(1);
    }
  }
  Exit(0);
}


void main(){
  if(wait = SemCreate("sema",0)==-1){
    PError("semaphore creation failled");
    Exit(1);
  }
  char * threadName = "threadName "
  threadId thread1; thread2 , thread3;
  if(thread1 = threadCreate(name,routineWAit)==-1){
    PError("creation thread 1 failled")
  }
  if(thread2 = threadCreate(name,routineWAit)==-1){
    PError("creation thread 2 failled")
  }

  if(thread3 = threadCreate(name,routineDelock)==-1){
    PError("creation thread 3 failled")
  }

  if(join(thread1)==-1){
    PError("liberation thread 1 failled")
  }
  if(join(thread2)==-1){
    PError("liberation thread 2 failled")
  }
  if(join(thread3)==-1){
    PError("liberation thread 3 failled")
  }
  return 0;
}
