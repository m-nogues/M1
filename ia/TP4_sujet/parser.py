import os

#dataset
# M {card,card...}
# O {card, card,...}

list = []

# listDico =[]
listCardM = []
listCardO = []
sequence = []
turnCards = []
player = ""
inputfile = open('data/all_absolute+.txt')
tempLCM =  open('tempLCM', 'w')
tempCloSpan = open('tempCloSpan', 'w')
for line in inputfile:
    find = False
    line = line.split(" ")
    if line[1] == "Begin":
        game = line[0]
        if len(listCardM) > 0:
            print (*sorted(listCardM), file = tempLCM)
        if len(listCardO) > 0:
            print (*sorted(listCardO), file = tempLCM)
        if len(sequence) > 0:
            print('(', *sequence[0], ')', file = tempCloSpan, end = '')
            for i in sequence[1:]:
                print(', (', *i, ')', file = tempCloSpan, end = '')
            print(file = tempCloSpan)
        # listDico.append(sorted(listCardM))
        # listDico.append(sorted(listCardO))
        listCardM = []
        listCardO = []
        sequence = []
    else:
        card = line[1]
        if player != card[:1] and len(turnCards) > 0:
            sequence.append(turnCards)
            turnCards = []

        player = card[:1]
        for id, v in enumerate(list):
            if v == card[1:]:
                find = True
                index = id + 1
        if find == False:
            list.append(card[1:])
            index = len(list)

        turnCards.append(index)
        if player == "M":
            if index not in listCardM:
                listCardM.append(index)
        else:
            if index not in listCardO:
                listCardO.append(index)

for i in sorted(listCardM):
    print(i, file = tempLCM, end=' ')
print (file = tempLCM)

for i in sorted(listCardO):
    print(i, file = tempLCM, end=' ')
print (file = tempLCM)

inputfile.close()
tempLCM.close()
# listDico.append(listCardM)
# listDico.append(listCardO)

# print(listDico)
# print(i)
# print(list[1])
# print(len(list))


os.system("java -jar spmf.jar run LCM tempLCM LCM 10%")

listOfSets = []
cardsOfSet = []
itemset = []
LCMfile =  open('LCM')
LCMoutput = open('LCMoutput.txt', 'w')
for line in LCMfile:
    itemset = line.split(" ")
    itemset[len(itemset) - 1] = itemset[len(itemset) - 1].replace('\n', '').replace('\r', '')
    for i in itemset:
        if i == "#SUP:":
            break
        else:
            cardsOfSet.append(list[int(i) - 1])
    listOfSets.append((cardsOfSet, itemset[len(itemset) - 1]))
    cardsOfSet = []

for itemsets in listOfSets:
    print(itemsets, file = LCMoutput)

# os.system("java -jar spmf.jar run CloSpan tempCloSpan CloSpan 10%")
