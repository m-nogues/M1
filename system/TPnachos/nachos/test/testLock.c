#include "userlib/syscall.h"
#include "userlib/libnachos.h"

LockId lock;


void routine() {
  LockAcquire(lock);
  n_printf("Le thread fils a le lock \n");
  LockRelease(lock);
}

int main() {

  if( (lock = LockCreate("lock")) < 0 ) {
    PError("Erreur creation lock");
    Exit(-1);
  }

  ThreadId  threadId = threadCreate("thread", routine);

  LockAcquire(lock);

  n_printf("Le thread pere a le lock \n");

  LockRelease(lock);

  if( LockDestroy(lock) < 0 ) {
    PError("Erreur destruction lock");
    Exit(-1);
  }

  Join(threadId);

  return 0;
}
