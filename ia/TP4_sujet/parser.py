import os

#dataset
# M {card,card...}
# O {card, card,...}

list = []

listDico =[]
listCardM = []
listCardO = []
i = 0
inputfile = open('data/all_absolute+.txt')
tempfile =  open('temp', 'w')
for line in inputfile:
    find = False
    mylist = line.split(" ")
    if mylist[1]=="Begin":
        game = mylist[0]
        if len(listCardM) > 0 :
            for i in sorted(listCardM) :
                print(i, file = tempfile, end=' ')
            print (file = tempfile)
        if len(listCardO) > 0 :
            for i in sorted(listCardO) :
                print(i, file = tempfile, end=' ')
            print (file = tempfile)

        # listDico.append(sorted(listCardM))
        # listDico.append(sorted(listCardO))
        listCardM = []
        listCardO = []

    else:
        player = mylist[1]
        i+=1
        card = mylist[1]
        for id,v in enumerate(list):
            if v == card[1:] :
                find = True
                index = id+1
        if find == False :
            list.append(card[1:])
            index = len(list)

        if player[:1]=="M" :
            if index not in listCardM:
                listCardM.append(index)
        else :
            if index not in listCardO:
                listCardO.append(index)

for i in sorted(listCardM) :
    print(i, file = tempfile, end=' ')
print (file = tempfile)

for i in sorted(listCardO) :
    print(i, file = tempfile, end=' ')
print (file = tempfile)

tempfile.close()
# listDico.append(listCardM)
# listDico.append(listCardO)

# print(listDico)
# print(i)
print(list[1])
# print(len(list))

os.system("java -jar spmf.jar run LCM temp output.txt 10%")
inputfile.close()
