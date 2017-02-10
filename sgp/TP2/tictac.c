#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <setjmp.h>
#include <stdlib.h>

#define MAX 20
int i=0;

void tacheron(int sig) {
  printf("%d\n", i);
  alarm(1);
}
int main(void) {
	printf("le programme s'arrête automatiquement après 20 affichages\n");
  struct sigaction act;
  act.sa_handler = tacheron;
  if(sigaction(SIGALRM, &act, NULL) == -1) {
    perror("sigaction failed");
  }
  alarm(1);
  for (i; i <= MAX; i++) {
    sleep(1);
  }
  return EXIT_SUCCESS;
}
