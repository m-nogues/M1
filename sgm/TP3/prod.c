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

union semun {
    int              val;    /* Value for SETVAL */
    struct semid_ds *buf;    /* Buffer for IPC_STAT, IPC_SET */
    unsigned short  *array;  /* Array for GETALL, SETALL */
    struct seminfo  *__buf;  /* Buffer for IPC_INFO
                                (Linux-specific) */
};

struct sembuf v ={1, 1, 0};

struct sembuf p = {0,-1,0};


int main(){
  char *shared_memory;
  int shmem_id, sem_id, readC = SIZE;
  struct shmid_ds shmbuffer;
  int file;

  if((file=open("memorySeg", O_RDONLY)) == -1){
		perror("openFile memorySeg failled");
		exit(1);
  }

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
  if (semctl(sem_id, 0, SETVAL, 1) == -1){
    perror("semctl SETVAL");
    return -1;
  }
  if (semctl(sem_id, 1, SETVAL, 1) == -1){
    perror("semctl SETVAL");
    return -1;
  }

  while (readC == SIZE) {
    if (semop(sem_id, &p, 1) == -1){
      perror("semop p");
      return -1;
    }

    if ((readC = read(file, shared_memory, SIZE)) == -1){
      perror("read");
      return -1;
    }
    if (readC < SIZE)
      shared_memory[readC] = '\0';

    if (semop(sem_id, &v, 1) == -1){
      perror("semop v");
      return -1;
    }
  }

  close(file);
  return 0;
}
