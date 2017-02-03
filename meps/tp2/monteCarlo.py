import random
import math

nbrand, lamb = int(input('Nombre d\'essai : ')), int(input('Lambda : '))

result, result2, x = 0, 0, 0
while x < nbrand:
    rand = - (1/lamb) * math.log(random.uniform(0,1))
    result += rand
    result2 += rand * rand
    x += 1
e = result / nbrand
print ('E[X] = ', e)

variance = (result2 - result * result / nbrand) / (nbrand - 1)
stdvar = math.sqrt(variance / nbrand)

print ('.9 confidence interval = (', result / nbrand - 1.645 * stdvar, ', ', result / nbrand + 1.645 * stdvar, ')')
print ('.95 confidence interval = (', result / nbrand - 1.96 * stdvar, ', ', result / nbrand + 1.96 * stdvar, ')')
print ('.99 confidence interval = (', result / nbrand - 2.58 * stdvar, ', ', result / nbrand + 2.58 * stdvar, ')')
