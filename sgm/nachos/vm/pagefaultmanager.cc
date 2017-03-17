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
  #ifndef ETUDIANTS_TP
ExceptionType PageFaultManager::PageFault(int virtualPage)
{

  printf("**** Warning: page fault manager is not implemented yet\n");
    exit(-1);
    return ((ExceptionType)0);

#endif

#ifdef ETUDIANTS_TP
ExceptionType PageFaultManager::PageFault(int virtualPage) {


	// wait other thread finished
	while (g_machine->mmu->translationTable->getBitIo(virtualPage)) {

		// Put the current thread at the end of the active thread lists
		// Run all the other active threads until going back to this one
		g_current_thread->Yield();

	}


	g_machine->mmu->translationTable->setBitIo(virtualPage);

	// temporary page
	char tmpPage[g_cfg->PageSize];

	// Get a physical page
	int phys_page_id = g_physical_mem_manager->AddPhysicalToVirtualMapping(g_current_thread->GetProcessOwner()->addrspace, virtualPage);

	// If not in swap (swap bit != 1)
	if (!g_machine->mmu->translationTable->getBitSwap(virtualPage)) {

    if (g_machine->mmu->translationTable->getAddrDisk(virtualPage) == -1){
        DEBUG('m', (char *)"allocation et mise en a 0 de %d octet d'un page anonyme\n", g_cfg->PageSize);
        //fill with 0
        memset(&(g_machine->mainMemory[phys_page_id * g_cfg->PageSize]), 0, g_cfg->PageSize);

    } else {
      OpenFile *mappedFile = g_current_thread->GetProcessOwner()->addrspace->findMappedFile(virtualPage);

      if(mappedFile != NULL){
        mappedFile->ReadAt(tmpPage,g_cfg->PageSize, g_machine->mmu->translationTable->getAddrDisk(virtualPage));

      }else{
        g_current_thread->GetProcessOwner()->exec_file->ReadAt(
        tmpPage,
        g_cfg->PageSize,
        g_machine->mmu->translationTable->getAddrDisk(virtualPage));
          DEBUG('m', (char *)"Lecture d'une page de %d octets depuis l'adresse\n", g_cfg->PageSize);
      }

    }




	} else {  // if in swap (swap bit = 1)

    // A page stealer is dealing with the current page
    while (g_machine->mmu->translationTable->getAddrDisk(virtualPage) == -1) {

      // Put the current thread at the end of the active thread lists
      // Run all the other active threads until going back to this one
      g_current_thread->Yield();
    }
    	// Get the real page from the swap
    g_swap_manager->GetPageSwap(g_machine->mmu->translationTable->getAddrDisk(virtualPage), tmpPage);

	}

	// Copy the temporary page into the real page
	memcpy(&g_machine->mainMemory[phys_page_id * g_cfg->PageSize], tmpPage, g_cfg->PageSize);

	// Put this physical page as the one for this virtual page
	g_machine->mmu->translationTable->setPhysicalPage(virtualPage, phys_page_id);

	//unlock the page
	g_machine->mmu->translationTable->setBitValid(virtualPage);
	g_machine->mmu->translationTable->clearBitIo(virtualPage);

	// Unlock physical page
	g_physical_mem_manager->UnlockPage(phys_page_id);


	return NO_EXCEPTION;

}
#endif
