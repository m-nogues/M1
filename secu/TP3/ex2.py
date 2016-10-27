#!/usr/bin/env python
'''
exercice2 TP3 secu
Mathieu GRANDMONTAGNE & Mael NOGUES

'''
from sys import argv
from time import time

#renvoi (x^y)%n en utilisant l'exponentiation modulaire O(log(n))
def lpowmod(x, y, z):
    a = 1
    while y > 0:
        if y % 2 == 1:
            a = (a * x) % z
        x = (x * x) % z
        y //= 2
    return a


#true si a^p =1 mod p
def fermat(a, p):
	if p == 2:
		return True
	if not p & 1:
		return False
	return lpowmod(a, p-1, p) == 1

#~ print fermat(7,19)
# renvoi true si 2^p-1 = 3^p-1 = 5^p-1 = 7^p-1 = 1 mod p
def prim(n) :
	if n not in [0,1,2,3,5,7] :#on exclu ces valeur pour n
		for  i in [2, 3, 5, 7] :# pour chaque valeur on regarde si fermat renvoi true
			r = fermat(i, n)
			if r != 1 :
				return False
		return True

def prime(i, primes): #sieve of erathostenes
	for prime in primes: #goes through all primes in the list at the moment
		if not (i == prime or i % prime): #checks if we can cross out the number i
			return False
	primes.add(i) #adds i to the primes list if it has not been crossed out yet
	return i

def historic(n):
	primes = set([2]) #creates the primes list and initialize it with 2
	i, p = 2, 0 #initialize i the number to check if prime at 2 and p the number of primes found at 0
	while True: #goes through all numbers until we found n primes
		if prime(i, primes):
			p += 1 #increments the number of primes
			if p == n:
				return primes #return the list of primes found when we found n primes
		i += 1

if __name__ == "__main__":
	if len(argv) > 1 :
		start = time()
		length = int(argv[1])
		primes = sorted(list(historic(length)))
		print (primes)#prints the given number of prime numbers
		print ('Time in seconds: ' + str(time() - start)) #prints the execution time in seconds
		true = 0
		for i  in primes :
			if i in [2,3,5,7] :
				true = true +1
			elif prim(i)==True :
				true= true +1
		if true == length:
			print ("for PGP primality test all number are prime")
		else :
			print ("for PGP primality test all number are not prime")

	else :
		print ('Parameter needed : number of primes to display')
