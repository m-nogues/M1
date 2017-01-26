import random
import math

nbrand = int(input('Nombre d\'essai : '))
lamb = int(input('Lambda : '))

x = 0
result = 0
while x < nbrand:
    result += - (1/lamb) * math.log(random.uniform(0,1))
    x+=1
e = result / nbrand
print ('E[X] = ', e)
