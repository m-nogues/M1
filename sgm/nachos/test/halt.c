/* halt.c
 *	Simple program to test whether running a user program works.
 *
 *	Just do a "syscall" that shuts down the OS.
//
//  Copyright (c) 1992-1993 The Regents of the University of California.
//  All rights reserved.  See copyright.h for copyright notice and limitation
//  of liability and disclaimer of warranty provisions.
 */

// Nachos system calls
#include "userlib/syscall.h"

int
main()
{
  // Halt Nachos
  n_printf("Halting Nachos\n");
  Halt();
  /* not reached */
  n_printf("Nachos not halted\n");
  return 0;
}
