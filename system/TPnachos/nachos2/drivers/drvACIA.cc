/* \file drvACIA.cc
   \brief Routines of the ACIA device driver
//
//      The ACIA is an asynchronous device (requests return
//      immediately, and an interrupt happens later on).
//      This is a layer on top of the ACIA.
//      Two working modes are to be implemented in assignment 2:
//      a Busy Waiting mode and an Interrupt mode. The Busy Waiting
//      mode implements a synchronous IO whereas IOs are asynchronous
//      IOs are implemented in the Interrupt mode (see the Nachos
//      roadmap for further details).
//
//  Copyright (c) 1999-2000 INSA de Rennes.
//  All rights reserved.
//  See copyright_insa.h for copyright notice and limitation
//  of liability and disclaimer of warranty provisions.
//
*/

/* Includes */

#include "kernel/system.h"         // for the ACIA object
#include "kernel/synch.h"
#include "machine/ACIA.h"
#include "drivers/drvACIA.h"

//-------------------------------------------------------------------------
// DriverACIA::DriverACIA()
/*! Constructor.
  Initialize the ACIA driver. In the ACIA Interrupt mode,
  initialize the reception index and semaphores and allow
  reception and emission interrupts.
  In the ACIA Busy Waiting mode, simply inittialize the ACIA
  working mode and create the semaphore.
  */
//-------------------------------------------------------------------------

DriverACIA::DriverACIA()
{
  #ifndef ETUDIANTS_TP
  printf("**** Warning: contructor of the ACIA driver not implemented yet\n");
  exit(-1);
  #endif
  #ifdef ETUDIANTS_TP
  //memset(send_buffer, 0 , BUFFER_SIZE);
  //memset(receive_buffer, 0, BUFFER_SIZE);

  send_sema = new Semaphore((char*)"Send_sema", 1);
  if(g_cfg->ACIA == ACIA_BUSY_WAITING){
    g_machine->acia->SetWorkingMode(BUSY_WAITING);
    receive_sema = new Semaphore((char*)"Receive_sema", 1);
  } else if(g_cfg->ACIA == ACIA_INTERRUPT){
    ind_rec=0;
    receive_sema = new Semaphore((char*)"Receive_sema", 0);
    g_machine->acia->SetWorkingMode(SEND_INTERRUPT | REC_INTERRUPT);
  }
  #endif
}

//-------------------------------------------------------------------------
// DriverACIA::TtySend(char* buff)
/*! Routine to send a message through the ACIA (Busy Waiting or Interrupt mode)
  */
//-------------------------------------------------------------------------

int DriverACIA::TtySend(char* buff)
{
  #ifndef ETUDIANTS_TP
  printf("**** Warning: method Tty_Send of the ACIA driver not implemented yet\n");
  exit(-1);
  return 0;
  #endif
  #ifdef ETUDIANTS_TP
  send_sema->P();
  int index = 0;
  if(g_machine->acia->GetWorkingMode() == BUSY_WAITING){
    DEBUG('d', (char *)"send  busy Waiting \n");
     do {
      while (g_machine->acia->GetOutputStateReg() == FULL);
      g_machine->acia->PutChar(buff[index]);
      index++;
    } while (buff[index-1] != '\0');
    DEBUG('d', (char *)"send  busy Waiting end \n");
    g_machine->acia->PutChar(buff[index]);
    send_sema->V();
  } else if(g_machine->acia->GetWorkingMode()== (REC_INTERRUPT | SEND_INTERRUPT)){
    ind_send = 0;
    do {
      send_buffer[index] = buff[index];
      index++;
    } while (buff[index-1] != '\0' && index < BUFFER_SIZE);
    send_buffer[index-1]= '\0';
    g_machine->acia->PutChar(send_buffer[ind_send]);
    ind_send++;
  }
  return index;
  #endif
}

//-------------------------------------------------------------------------
// DriverACIA::TtyReceive(char* buff,int length)
/*! Routine to reveive a message through the ACIA
//  (Busy Waiting and Interrupt mode).
  */
//-------------------------------------------------------------------------

int DriverACIA::TtyReceive(char* buff, int lg)
{
  #ifndef ETUDIANTS_TP
  printf("**** Warning: method Tty_Receive of the ACIA driver not implemented yet\n");
  exit(-1);
  return 0;
  #endif
  #ifdef ETUDIANTS_TP
  if (lg <= 0)
    return -1;
  receive_sema->P();
  int index = 0;
  if(g_machine->acia->GetWorkingMode() == BUSY_WAITING){
    DEBUG('d', (char *)"receive busy Waiting \n");
    do {
      while (g_machine->acia->GetInputStateReg() == EMPTY);

      buff[index]=g_machine->acia->GetChar();
      index++;
    } while(buff[index-1] != '\0' && index < lg);
    DEBUG('d', (char *)"receive busy Waiting  end\n");
    buff[index-1] = '\0';
    receive_sema->V();

  } else if(g_machine->acia->GetWorkingMode() == SEND_INTERRUPT){
    do {
      buff[index]= receive_buffer[index];
      index++;
    } while(buff[index-1] != '\0' && index < lg);
    buff[index-1]='\0';
    ind_rec=0;
    g_machine->acia->SetWorkingMode(SEND_INTERRUPT | REC_INTERRUPT);
  }
  return index;
  #endif

}


//-------------------------------------------------------------------------
// DriverACIA::InterruptSend()
/*! Emission interrupt handler.
  Used in the ACIA Interrupt mode only.
  Detects when it's the end of the message (if so, releases the send_sema
  semaphore), else sends the next character according to index ind_send.
  */
//-------------------------------------------------------------------------

void DriverACIA::InterruptSend()
{
  #ifndef ETUDIANTS_TP
  printf("**** Warning: send interrupt handler not implemented yet\n");
  exit(-1);
  #endif
  #ifdef ETUDIANTS_TP
  if(send_buffer[ind_send] != '\0'){
    g_machine->acia->PutChar(send_buffer[ind_send]);
    ind_send++;
  }else{
    send_sema->V();
  }
  #endif
}

//-------------------------------------------------------------------------
// DriverACIA::Interrupt_receive()
/*! Reception interrupt handler.
  Used in the ACIA Interrupt mode only. Reveices a character through the ACIA.
  Releases the receive_sema semaphore and disables reception
  interrupts when the last character of the message is received
  (character '\0').
  */
//-------------------------------------------------------------------------

void DriverACIA::InterruptReceive()
{
  #ifndef ETUDIANTS_TP
  printf("**** Warning: receive interrupt handler not implemented yet\n");
  exit(-1);
  #endif
  #ifdef ETUDIANTS_TP
  char c = g_machine->acia->GetChar();
  if(c == '\0' || ind_rec == BUFFER_SIZE-1){
    receive_sema->V();
    g_machine->acia->SetWorkingMode(SEND_INTERRUPT);
  }else{
    receive_buffer[ind_rec]=c;
    ind_rec++;
  }
  #endif
}
