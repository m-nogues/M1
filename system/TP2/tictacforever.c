#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <setjmp.h>
#include <stdlib.h>

#define MAX 10
int i;
jmp_buf env;
struct sigaction act, actC;

void tacheron(int sig) {//handler for display
  printf("%d\n", i);
  alarm(1);
}

void handleC(int sig) {//handler for ^C
  sigset_t x;
  sigemptyset (&x);
  sigaddset(&x, SIGINT);
  sigprocmask(SIG_UNBLOCK, &x, NULL);
  longjmp(env, 0);
}

int main(void) {
  setjmp(env);//set the restart point
  i=0;
  //set sigaction parameters
  act.sa_handler = tacheron;
  actC.sa_handler = handleC;
  
  if(sigaction(SIGINT, &actC, NULL) == -1) {
    perror("sigaction failed");
  }
  if(sigaction(SIGALRM, &act, NULL) == -1) {
    perror("sigaction failed");
  }
  alarm(1);
  for (i; i <= MAX; i++) {
    sleep(1);
  }
  return EXIT_SUCCESS;
}
