#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>


#define ID 42
#define SIZE 1024

struct sembuf v ={0,1,0};

struct sembuf p = {1,-1,0};


int main(){
  char *shared_memory;
  int shmem_id, sem_id, readC = SIZE;
  struct shmid_ds shmbuffer;

  if ((sem_id = semget(ID, 2, 0660 | IPC_CREAT)) == -1){
    perror("semget");
    return -1;
  }
  if ((shmem_id = shmget(ID, SIZE, 0660 | IPC_CREAT)) == -1){
    perror("shmget");
    return -1;
  }
  if ((shared_memory = (char*) shmat(shmem_id, 0, 0)) == -1){
    perror("shmat");
    return -1;
  }
  if (shmctl(shmem_id, IPC_STAT, &shmbuffer) == -1){
    perror("shmctl IPC_STAT");
    return -1;
  }

  while (readC == SIZE) {
    if (semop(sem_id, &p, 1) == -1){
      perror("semop p");
      return -1;
    }

    readC = strlen(shared_memory);
    printf("%s", shared_memory);

    if (semop(sem_id, &v, 1) == -1){
      perror("semop v");
      return -1;
    }
  }

  if (semctl(sem_id, 0, IPC_RMID, 0) == -1){
    perror("semctl destroy");
    return -1;
  }
  if (shmdt(shared_memory) == -1){
    perror("shmdt");
    return -1;
  }
}
