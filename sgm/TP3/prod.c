#include <stdlib.h>
#include <stdio.h>
#include <sys/type.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/shm.h>

#define ID 42
#define SIZE 1024

void v(int semid){
  struct sembuf sempar;
  sempar.sem_num = 0;
  sempar.sem_op = 1;
  sempar.sem_flag = 0;
}

void v(int semid){
  struct sembuf sempar;
  sempar.sem_num = 0;
  sempar.sem_op = -1;
  sempar.sem_flag 0;
}
int main(){
  char *shared_memory;
  int shmem_id, sem_id, segment_size;
  struct shmid_ds shmbuffer;

  if ((sem_id = semget(ID, 2, 0660)) == -1)
    return errno;
  if ((shmem_id = shmget(ID, SIZE, 0660)) == -1)
    return errno;
  if ((shared_memory = (char*) shmat(shmem_id, 0, 0)) == -1)
    return errno;
  if (shmctl(shmem_id, IPC_STAT, &shmbuffer) == -1)
    return errno;

  segment_size = shmbuffer.shm_segsz;
  while (1) {
    semop(1,0)
    sprintf(shared_memory, "Hello, world.");
  }

  if (semctl(ID, 0))
  if (shmdt(shared_memory) == -1)
    return errno;
}
