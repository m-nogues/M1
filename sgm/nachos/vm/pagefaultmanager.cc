/*! \file pagefaultmanager.cc
Routines for the page fault managerPage Fault Manager
*/
//
//  Copyright (c) 1999-2000 INSA de Rennes.
//  All rights reserved.
//  See copyright_insa.h for copyright notice and limitation
//  of liability and disclaimer of warranty provisions.
//

#include "kernel/thread.h"
#include "vm/swapManager.h"
#include "vm/physMem.h"
#include "vm/pagefaultmanager.h"

PageFaultManager::PageFaultManager() {
}

// PageFaultManager::~PageFaultManager()
/*! Nothing for now
*/
PageFaultManager::~PageFaultManager() {
}

// ExceptionType PageFault(int virtualPage)
/*!
//	This method is called by the Memory Management Unit when there is a
//      page fault. This method loads the page from :
//      - read-only sections (text,rodata) $\Rightarrow$ executive
//        file
//      - read/write sections (data,...) $\Rightarrow$ executive
//        file (1st time only), or swap file
//      - anonymous mappings (stack/bss) $\Rightarrow$ new
//        page from the MemoryManager (1st time only), or swap file
//
//	\param virtualPage the virtual page subject to the page fault
//	  (supposed to be between 0 and the
//        size of the address space, and supposed to correspond to a
//        page mapped to something [code/data/bss/...])
//	\return the exception (generally the NO_EXCEPTION constant)
*/
ExceptionType PageFaultManager::PageFault(int virtualPage)
{
  #ifndef ETUDIANTS_TP
  printf("**** Warning: page fault manager is not implemented yet\n");
    exit(-1);
    return ((ExceptionType)0);
  #endif
  #ifdef ETUDIANTS_TP
  Process *processus = g_current_thread->GetProcessOwner();
  AddrSpace *addrspace = processus->addrspace;
  TranslationTable *tableTraduction = addrspace->translationTable;

  //declaration
  int taillePages = g_cfg->PageSize;

  //int numPages = g_cfg->NumPhysPages;
//
//  if(tableTraduction->getBitIo(virtualPage)){
    while (g_machine->mmu->translationTable->getBitIo(virtualPage)) {
      g_current_thread->Yield();
    }
//    return NO_EXCEPTION;
//  }
  g_machine->mmu->translationTable->setBitIo(virtualPage);

  char pageTmp[g_cfg->PageSize];

  int addrPhysique = g_physical_mem_manager->AddPhysicalToVirtualMapping(addrspace, virtualPage);

  if(g_machine->mmu->translationTable->getBitSwap(virtualPage)){

    while ((g_machine->mmu->translationTable->getAddrDisk(virtualPage))==-1) {
      g_current_thread->Yield();
    }
    g_swap_manager->GetPageSwap(tableTraduction->getAddrDisk(virtualPage),pageTmp);


    }else{
        if(g_machine->mmu->translationTable->getAddrDisk(virtualPage)==-1){
          DEBUG('m', (char*)"allocation et mise à 0 de %d octet d'une page anonyme\n", taillePages);
          memset(&(g_machine->mainMemory[addrPhysique*g_cfg->PageSize]),0,g_cfg->PageSize);
        }else{
          if(processus->exec_file->ReadAt(pageTmp, taillePages, g_machine->mmu->translationTable->getAddrDisk(virtualPage))!= taillePages){
            DEBUG('m', (char*)"Erreur");
            return PAGEFAULT_EXCEPTION;
          }
        }

    }
  //

  //
  memcpy(&(g_machine->mainMemory[addrPhysique*taillePages]), pageTmp, taillePages);
  //deverouillage de la page physique + page virtuelle valide
  g_machine->mmu->translationTable->setPhysicalPage(virtualPage, addrPhysique);
  g_machine->mmu->translationTable->setBitValid(virtualPage);
  g_machine->mmu->translationTable->clearBitIo(virtualPage);
  g_physical_mem_manager->UnlockPage(addrPhysique);

  return NO_EXCEPTION;
  #endif
}
