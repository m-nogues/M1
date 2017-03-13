//-----------------------------------------------------------------
/*! \file mem.cc
//  \brief Routines for the physical page management
*/
//
//  Copyright (c) 1999-2000 INSA de Rennes.
//  All rights reserved.
//  See copyright_insa.h for copyright notice and limitation
//  of liability and disclaimer of warranty provisions.
//-----------------------------------------------------------------

#include <unistd.h>
#include "vm/physMem.h"

//-----------------------------------------------------------------
// PhysicalMemManager::PhysicalMemManager
//
/*! Constructor. It simply clears all the page flags and inserts them in the
// free_page_list to indicate that the physical pages are free
*/
//-----------------------------------------------------------------
PhysicalMemManager::PhysicalMemManager() {

  long i;

  tpr = new struct tpr_c[g_cfg->NumPhysPages];

  for (i=0;i<g_cfg->NumPhysPages;i++) {
    tpr[i].free=true;
    tpr[i].locked=false;
    tpr[i].owner=NULL;
    free_page_list.Append((void*)i);
  }
  i_clock=-1;
}

PhysicalMemManager::~PhysicalMemManager() {
  // Empty free page list
  int64_t page;
  while (!free_page_list.IsEmpty()) page =  (int64_t)free_page_list.Remove();

  // Delete physical page table
  delete[] tpr;
}

//-----------------------------------------------------------------
// PhysicalMemManager::RemovePhysicalToVitualMapping
//
/*! This method releases an unused physical page by clearing the
//  corresponding bit in the page_flags bitmap structure, and adding
//  it in the free_page_list.
//
//  \param num_page is the number of the real page to free
*/
//-----------------------------------------------------------------
void PhysicalMemManager::RemovePhysicalToVirtualMapping(long num_page) {

  // Check that the page is not already free
  ASSERT(!tpr[num_page].free);

  // Update the physical page table entry
  tpr[num_page].free=true;
  tpr[num_page].locked=false;
  if (tpr[num_page].owner->translationTable!=NULL)
    tpr[num_page].owner->translationTable->clearBitValid(tpr[num_page].virtualPage);

  // Insert the page in the free list
  free_page_list.Prepend((void*)num_page);
}

//-----------------------------------------------------------------
// PhysicalMemManager::UnlockPage
//
/*! This method unlocks the page numPage, after
//  checking the page is in the locked state. Used
//  by the page fault manager to unlock at the
//  end of a page fault (the page cannot be evicted until
//  the page fault handler terminates).
//
//  \param num_page is the number of the real page to unlock
*/
//-----------------------------------------------------------------
void PhysicalMemManager::UnlockPage(long num_page) {
  ASSERT(num_page<g_cfg->NumPhysPages);
  ASSERT(tpr[num_page].locked==true);
  ASSERT(tpr[num_page].free==false);
  tpr[num_page].locked = false;
}

//-----------------------------------------------------------------
// PhysicalMemManager::ChangeOwner
//
/*! Change the owner of a page
//
//  \param owner is a pointer on new owner (Thread *)
//  \param numPage is the concerned page
*/
//-----------------------------------------------------------------
void PhysicalMemManager::ChangeOwner(long numPage, Thread* owner) {
  // Update statistics
  g_current_thread->GetProcessOwner()->stat->incrMemoryAccess();
  // Change the page owner
  tpr[numPage].owner = owner->GetProcessOwner()->addrspace;
}

//-----------------------------------------------------------------
// PhysicalMemManager::AddPhysicalToVirtualMapping
//
/*! This method returns a new physical page number. If there is no
//  page available, it evicts one page (page replacement algorithm).
//
//  NB: this method locks the newly allocated physical page such that
//      it is not stolen during the page fault resolution. Don't forget
//      to unlock it
//
//  \param owner address space (for backlink)
//  \param virtualPage is the number of virtualPage to link with physical page
//  \return A new physical page number.
*/
//-----------------------------------------------------------------
int PhysicalMemManager::AddPhysicalToVirtualMapping(AddrSpace* owner,int virtualPage)
{
  #ifndef ETUDIANTS_TP
    printf("**** Warning: function AddPhysicalToVirtualMapping is not implemented\n");
    exit(-1);
    return (0);
  #endif
  #ifdef ETUDIANTS_TP
    ASSERT(g_machine->mmu->translationTable->getBitIo(virtualPage));
    int phys_page = FindFreePage();
    // not free page call EvictPage
    if (phys_page == -1)
      phys_page = EvictPage();

    tpr[phys_page].locked = true;

    tpr[phys_page].virtualPage = virtualPage;
    tpr[phys_page].owner = owner;


    DEBUG('v', (char *)"AddPhysicalToVirtualMapping, virtualPage : %i, realPage : %i\n", virtualPage, phys_page);
    return phys_page;
  #endif
}

//-----------------------------------------------------------------
// PhysicalMemManager::FindFreePage
//
/*! This method returns a new physical page number, if it finds one
//  free. If not, return -1. Does not run the clock algorithm.
//
//  \return A new free physical page number.
*/
//-----------------------------------------------------------------
int PhysicalMemManager::FindFreePage() {
  int64_t page;

  // Check that the free list is not empty
  if (free_page_list.IsEmpty())
    return -1;

  // Update statistics
  g_current_thread->GetProcessOwner()->stat->incrMemoryAccess();

  // Get a page from the free list
  page = (int64_t)free_page_list.Remove();

  // Check that the page is really free
  ASSERT(tpr[page].free);

  // Update the physical page table
  tpr[page].free = false;

  return page;
}

//-----------------------------------------------------------------
// PhysicalMemManager::EvictPage
//
/*! This method implements page replacement, using the well-known
//  clock algorithm.
//
//  \return A new free physical page number.
*/
//-----------------------------------------------------------------
int PhysicalMemManager::EvictPage() {
  #ifndef ETUDIANTS_TP
    printf("**** Warning: page replacement algorithm is not implemented yet\n");
    exit(-1);
    return (0);
  #endif
  #ifdef ETUDIANTS_TP
  DEBUG('m', (char*)"Entering EvictPage\n");
  int numPhysPages = g_cfg->NumPhysPages, pageSize = g_cfg->PageSize;
  int local_i_clock = (i_clock+1)%numPhysPages;
  int begin = (local_i_clock-1)%numPhysPages;
  int nbTraveledPages = 0;
  int virtualPage;

	TranslationTable *transTable;
	OpenFile *fileMap = NULL;
	char *pageAdresse = NULL;
	bool found = false;
	tpr_c realPage;



  	while((tpr[local_i_clock].owner->translationTable->getBitU(tpr[local_i_clock].virtualPage))||(tpr[local_i_clock].locked)){
      DEBUG('m', (char*)"Entering EvictPage while\n");
      tpr[local_i_clock].owner->translationTable->clearBitU(tpr[local_i_clock].virtualPage);
      local_i_clock = (local_i_clock+1)%numPhysPages;


      if(local_i_clock==begin){
        g_current_thread->Yield();
      }
  		/*realPage = tpr[local_i_clock];
  		virtualPage = realPage.virtualPage;
  		transTable = realPage.owner->translationTable;*/

  	/*	if(nbTraveledPages == numPhysPages){
  			i_clock = local_i_clock;
  			g_current_thread->Yield();
  			nbTraveledPages = 0;
  		}*/

  		/*if(!realPage.locked) {
  			if(!transTable->getBitU(virtualPage))
  				found = true;
  			else
  				transTable->clearBitU(virtualPage);
      }*/

  	//	nbTraveledPages++;
  	}
    tpr[local_i_clock].owner->translationTable->clearBitValid(tpr[local_i_clock].virtualPage);
	  tpr[local_i_clock].locked = true;
  	i_clock = local_i_clock;
  //	realPage.locked = true;
  	//transTable->clearBitValid(virtualPage);

  	DEBUG('v', (char *)"Physical page n°%i to be stolen\n", local_i_clock);

  	if(tpr[local_i_clock].owner->translationTable->getBitM(tpr[local_i_clock].virtualPage)){
  		pageAdresse = (char*)(&(g_machine->mainMemory[local_i_clock*pageSize]));

  		if(tpr[local_i_clock].owner->translationTable->getBitSwap(tpr[local_i_clock].virtualPage)) {

  			DEBUG('v', (char *)"Page is already in swap (%i)\n", transTable->getAddrDisk(virtualPage));
  			g_swap_manager->PutPageSwap(tpr[local_i_clock].owner->translationTable->getAddrDisk(tpr[local_i_clock].virtualPage), pageAdresse);

  		}else{
        OpenFile *fileMap = tpr[local_i_clock].owner->findMappedFile(tpr[local_i_clock].virtualPage*pageSize)
        if(fileMap!= NULL){
          fileMap->WriteAt((char *)&g_machine->mainMemory[local_i_clock*pageSize],
          pageSize,
          tpr[local_i_clock]t.owner->translationTable->getAddrDisk(tpr[local_i_clock].virtualPage));

          tpr[local_i_clock].owner->translationTable->clearBitM(tpr[local_i_clock].virtualPage);
        }else{
          int sector = g_swap_manager->PutPageSwap(-1, (char *)&g_machine->mainMemory[local_i_clock*pageSize]);
          if (sector ==-1){
            DEBUG('h',(char *)"try to put a swaped page in EvictPage but failled, return code is %d\n", sector);
            g_machine->interrupt->Halt(-1);
          }
          tpr[local_i_clock].owner->translationTable->setAddrDisk(tpr[local_i_clock].virtualPage, sector);
          tpr[local_i_clock].owner->translationTable->getBitSwap(tpr[local_i_clock].virtualPage);
          tpr[local_i_clock].owner->translationTable->clearBitM(tpr[local_i_clock].virtualPage);
        }
      }


      /*else if( (fileMap = realPage.owner->findMappedFile(virtualPage)) != NULL) {
  			DEBUG('u', (char *)"Copy mapped page in file %s (offset %d)\n", fileMap->GetName(), transTable->getAddrDisk(virtualPage));
  			fileMap->WriteAt(pageAdresse, pageSize, transTable->getAddrDisk(virtualPage));
  		} else {
  			DEBUG('v', (char *)"Page is not in swap\n");
  			sector = g_swap_manager->PutPageSwap(-1, (char*)(&(g_machine->mainMemory[local_i_clock*pageSize])));
  			DEBUG('v', (char *)"Associated swap page : %i\n", sector);

  			//transTable->setAddrDisk(virtualPage, sector);

  			//transTable->setBitSwap(virtualPage);
  		}*/
  	}

  	return local_i_clock;
  #endif
}

//-----------------------------------------------------------------
// PhysicalMemManager::Print
//
/*! print the current status of the table of physical pages
//
//  \param rpage number of real page
*/
//-----------------------------------------------------------------

void PhysicalMemManager::Print(void) {
  int i;

  printf("Contents of TPR (%d pages)\n",g_cfg->NumPhysPages);
  for (i=0;i<g_cfg->NumPhysPages;i++) {
    printf("Page %d free=%d locked=%d virtpage=%d owner=%lx U=%d M=%d\n",
	   i,
	   tpr[i].free,
	   tpr[i].locked,
	   tpr[i].virtualPage,
	   (long int)tpr[i].owner,
	   (tpr[i].owner!=NULL) ? tpr[i].owner->translationTable->getBitU(tpr[i].virtualPage) : 0,
	   (tpr[i].owner!=NULL) ? tpr[i].owner->translationTable->getBitM(tpr[i].virtualPage) : 0);
  }
}
