from sys import argv
from time import time

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
		print sorted(list(historic(int(argv[1])))) #prints the given number of prime numbers
		print 'Time in seconds: ' + str(time() - start) #prints the execution time in seconds
	else :
		print 'Parameter needed : number of primes to display'
