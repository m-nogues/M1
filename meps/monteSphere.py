import random
import math

nbrand = int(input('Nombre d\'essai : '))
radius = int(input('Radius : '))
center = vector(radius, radius, radius)

x = 0
while x < nbrand:
    point = (random.uniform(0,1), random.uniform(0,1), random.uniform(0,1))
    x+=1
e = result / nbrand
print ('E[X] = ', e)
