import random

nbrand = int(input('Nombre d\'essai : '))
lamb = int(input('Lambda : '))

def rollDice():
    roll = random.uniform(0,1)
    return roll

x = 0
e = 0
while x < nbrand:
    result = rollDice()
    e += result
    x+=1
e = e / nbrand
print ('E[X] = ', e)
