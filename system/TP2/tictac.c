#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <setjmp.h>
#include <stdlib.h>

int i=0;

void tacheron(int sig) {
  printf("%d\n", i);
  // le handler ne fait pas arrêter le processus. On reviendra donc à l'opération qui a déclenché le signal
  alarm(1);
}
int main(void) {
  struct sigaction act;
  act.sa_handler = tacheron;
  if(sigaction(SIGALRM, &act, NULL) == -1) {
    perror("sigaction failed");
  }
  alarm(1);
  for (i; i <= 1000; i++) {
    sleep(1);
  }
  return EXIT_SUCCESS;
}
