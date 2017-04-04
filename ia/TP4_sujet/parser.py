from operator import itemgetter
import os

list = []

listCardM = []
listCardO = []
sequence = []
turnCards = []
player = ""
inputfile = open('data/all_absolute+.txt')
tempLCM =  open('tempLCM', 'w')
tempSeq = open('tempSeq', 'w')
for line in inputfile:
    find = False
    line = line.split(" ")
    if line[1] == "Begin":
        if len(listCardM) > 0:
            print (*sorted(listCardM), file = tempLCM)
        if len(listCardO) > 0:
            print (*sorted(listCardO), file = tempLCM)
        if len(sequence) > 0:
            print(*sorted(sequence[0]), '-1 ', file = tempSeq, end = '')
            for i in sequence[1:]:
                print(*sorted(i), '-1 ', file = tempSeq, end = '')
            print('-2', file = tempSeq)
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

print(*sorted(sequence[0]), '-1 ', file = tempSeq, end = '')
for i in sequence[1:]:
    print(*sorted(i), '-1 ', file = tempSeq, end = '')
print('-2', file = tempSeq, end = '')

dico = open('dictionary', 'w')
for id, v in enumerate(list):
    print(id + 1, '=', v, file = dico)
dico.close()

os.system("java -jar spmf.jar run LCM tempLCM LCM 8%")

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
        cardsOfSet.append(list[int(i) - 1])
    listOfSets.append((cardsOfSet, int(itemset[len(itemset) - 1])))
    cardsOfSet = []
LCMfile.close()

LCMoutput = open('LCMoutput.txt', 'w')
for itemsets in sorted(listOfSets, key = itemgetter(1), reverse = True):
    print(itemsets, file = LCMoutput)
LCMoutput.close()

os.system("java -jar spmf.jar run CloSpan tempSeq CloSpan 11%")

listOfSets = []
itemsOfSet = []
cardsOfSet = []
itemset = []
CloSpanFile =  open('CloSpan')
for line in CloSpanFile:
    itemset = line.split(" ")
    itemset[len(itemset) - 1] = itemset[len(itemset) - 1].replace('\n', '').replace('\r', '')
    useful = True
    for i in itemset:
        if i == "#SUP:":
            break
        if int(i) - 1 == list.index("TheCoin"):
            useful = False
            break
        if i != "-1":
            itemsOfSet.append(list[int(i) - 1])
        else:
            cardsOfSet.append(itemsOfSet)
            itemsOfSet = []
    if useful:
        listOfSets.append((cardsOfSet, int(itemset[len(itemset) - 1])))
    cardsOfSet = []
CloSpanFile.close()

CloSpanOutput = open('CloSpanOutput.txt', 'w')
for itemsets in sorted(listOfSets, key = itemgetter(1), reverse = True):
    print(itemsets, file = CloSpanOutput)
CloSpanOutput.close()

# os.system("java -jar spmf.jar run RuleGrowth tempSeq RuleGrowth 10% 20%")

# os.system("java -jar spmf.jar run ERMiner tempSeq ERMiner 10% 20%")
