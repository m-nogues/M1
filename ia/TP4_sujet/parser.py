import os

list = []

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
            print('(', *sorted(sequence[0]), ')', file = tempCloSpan, end = '')
            for i in sequence[1:]:
                print(', (', *sorted(i), ')', file = tempCloSpan, end = '')
            print(file = tempCloSpan)
        listCardM = []
        listCardO = []
        sequence = []
    else:
        card = line[1]
        if player != card[:1] and len(turnCards) > 0:
            sequence.append(turnCards)
            turnCards = []

        player = card[:1]
        if card[1:] in list:
            index = list.index(card[1:]) + 1
        else:
            list.append(card[1:])
            index = len(list)

        if index not in turnCards:
            turnCards.append(index)
        if player == "M":
            if index not in listCardM:
                listCardM.append(index)
        else:
            if index not in listCardO:
                listCardO.append(index)
inputfile.close()

print (*sorted(listCardM), file = tempLCM)
print (*sorted(listCardO), file = tempLCM, end = '')
tempLCM.close()

print('(', *sorted(sequence[0]), ')', file = tempCloSpan, end = '')
for i in sequence[1:]:
    print(', (', *sorted(i), ')', file = tempCloSpan, end = '')
tempCloSpan.close()

dico = open('dictionary', 'w')
for id, v in enumerate(list):
    print(id, '=', v, file = dico)
dico.close()

os.system("java -jar spmf.jar run LCM tempLCM LCM 10%")

listOfSets = []
cardsOfSet = []
itemset = []
LCMfile =  open('LCM')
for line in LCMfile:
    itemset = line.split(" ")
    itemset[len(itemset) - 1] = itemset[len(itemset) - 1].replace('\n', '').replace('\r', '')
    for i in itemset:
        if i == "#SUP:":
            break
        else:
            cardsOfSet.append(list[int(i) - 1])
    listOfSets.append((cardsOfSet, int(itemset[len(itemset) - 1])))
    cardsOfSet = []
LCMfile.close()

LCMoutput = open('LCMoutput.txt', 'w')
for itemsets in listOfSets:
    print(itemsets, file = LCMoutput)
LCMoutput.close()

os.system("java -jar spmf.jar run CloSpan tempCloSpan CloSpan 0%")
