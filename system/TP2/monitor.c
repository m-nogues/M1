#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>
#include <sys/ptrace.h>
#include <sys/wait.h>


#define MAX_I 15

void tacheron(int sig) {}


int main() {
	int i;
	int status;
	pid_t pid = fork();
	if(pid  < 0){
		perror("fork failled");
	 }
	
		
	
	if (pid == 0) {//Son
		
		ptrace(PTRACE_TRACEME, NULL, NULL, NULL);
		
	
	  i = 0;
	  for (i; i <= MAX_I; i++) {
		 alarm(1);
		sleep(1);
	  }
	}
	else {//Parent
		sleep(1); 
		struct sigaction act;
		act.sa_handler = tacheron;
		
		if(sigaction(SIGALRM, &act, NULL) == -1) {
			printf("sigaction failed");
		}
		
		
		while(i <= MAX_I){

			wait(&status);
			// get DATA from Son
			int result_ptrace = ptrace(PTRACE_PEEKDATA, pid, &i, NULL);
			if(result_ptrace ==-1){
				perror("failled to read data from son");
			}else{
				if(result_ptrace==MAX_I)return 0;
				
			}
			//display i from the Son
				if (result_ptrace != -1)
				fprintf(stdout, "%s%d.\n", "The value of i read by the father is ", result_ptrace);

			//Son can continu the loop
			if (ptrace(PTRACE_CONT, pid, NULL, NULL) == -1) perror("failled ptrace_cont");
			
			
		}
	}
	
	

	return 0;
}
