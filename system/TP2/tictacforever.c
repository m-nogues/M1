#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <setjmp.h>
#include <stdlib.h>

int i;
jmp_buf env;
struct sigaction act, actC;

void tacheron(int sig) {
  printf("%d\n", i);
  // le handler ne fait pas arrêter le processus. On reviendra donc à l'opération qui a déclenché le signal
  alarm(1);
}

void handleC(int sig) {
  sigset_t x;
  sigemptyset (&x);
  sigaddset(&x, SIGINT);
  sigprocmask(SIG_UNBLOCK, &x, NULL);
  longjmp(env, 0);
}

int main(void) {
  setjmp(env);
  i=0;
  act.sa_handler = tacheron;
  actC.sa_handler = handleC;
  if(sigaction(SIGINT, &actC, NULL) == -1) {
    perror("sigaction failed");
  }
  if(sigaction(SIGALRM, &act, NULL) == -1) {
    perror("sigaction failed");
  }
  alarm(1);
  for (i; i <= 10; i++) {
    sleep(1);
  }
  return EXIT_SUCCESS;
}
