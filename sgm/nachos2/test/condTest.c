#include "userlib/syscall.h"
#include "userlib/libnachos.h"

CondId cond;


void routine() {
  if (CondWait(cond) != 0){
    PError("Erreur wait Cond\n");
    Exit(-1);
  }
  n_printf("Le thread fils a reçu le signal du cond\n");
  int i = 0;
  while (i++ < 50000){
    int prout = i*i*i*i*i*i*i*i;
  }
  n_printf("Broadcast sur cond\n");
  CondBroadcast(cond);
  n_printf("Le thread fils a broadcasté le cond\n");
}

int main() {

  if((cond = CondCreate("cond")) < 0 ) {
    PError("Erreur creation cond\n");
    Exit(-1);
  }

  n_printf("Création du cond\n");

  ThreadId  threadId = threadCreate("thread", routine);

  int i = 0;
  while (i++ < 50000){
    int prout = i*i*i*i*i*i*i*i;
  }

  n_printf("Signal sur cond\n");

  if (CondSignal(cond) != 0){
    PError("Erreur signal cond\n");
    Exit(-1);
  }

  n_printf("Le thread pere a signalé le cond\n");

  if (CondWait(cond) != 0){
    PError("Erreur wait Cond\n");
    Exit(-1);
  }


  n_printf("Le thread pere a reçu le broadcast du Cond\n");

  if(CondDestroy(cond) != 0 ) {
    PError("Erreur destruction cond\n");
    Exit(-1);
  }

  n_printf("Le thread pere a détruit le cond\n");

  return 0;
}
