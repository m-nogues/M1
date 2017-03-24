
#include "userlib/syscall.h"


#include "userlib/libnachos.h"

void main() {
  OpenFileId f;
  int i;
  int buf[1];


  n_printf("test writing\n");
  Create("testMmap",1024));
  f = Open("testMmap"));
  for (i=0;i<20;i++) {
    buf[0] = i;
  Write((char *)buf,sizeof(int),f));
  }
  n_printf("test read n");
  Close(f));
  f = Open("mmap"));
  for (i=0;i<20;i++) {
    Read((char *)buf,sizeof(int),f));
    n_printf("%d ",buf[0]);
    if (buf[0]!=i) n_printf("ERREUR\n");
  }
  ,Close(f));
  n_printf("OK \n");
	return 0;
}
/*
  n_printf("   Test of file contents: writing file\n");
  err("tst_file(Open)",f = Open("mmap"));
  n_printf("   Checking contents\n");
  err("tst_file(Close)",Close(f));
  err("tst_file(Open)",f = Open("mmap"));
  for (i=0;i<100;i++) {
    err("tst_file(Read)",Read((char *)buf,sizeof(int),f));
    n_printf("%d ",buf[0]);
    if (buf[0]!=-1*i) n_printf("   Contents NOK\n");
  }
  err("tst_file(Close)",Close(f));
  n_printf("   Contents OK\n");
*/
