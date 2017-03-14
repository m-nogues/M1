

#dataset
# M {card,card...}
# O {card, card,...}

dico = {}
inputfile = open('data/all_absolute-.txt')
for line in inputfile:

    mylist = line.split(" ")
    if mylist[1]=="Begin":
        dicoCard = []
        game = mylist[0]
        print(dico)
    else:
        player = mylist[1]
        if mylist[0]==game :
            dicoCard.append(mylist[1])
        dico = {mylist[0]+player[:1] : dicoCard}


inputfile.close()
