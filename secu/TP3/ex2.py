from sys import argv
from time import time

def prime(i, primes):
	for prime in primes:
		if not (i == prime or i % prime):
			return False
	primes.add(i)
	return i

def historic(n):
	primes = set([2])
	i, p = 2, 0
	while True:
		if prime(i, primes):
			p += 1
			if p == n:
				return primes
		i += 1

if __name__ == "__main__":
	if len(argv) > 1 :
		start = time()
		print sorted(list(historic(int(argv[1])))) #prints the given number of prime numbers
		print 'Time in seconds: ' + str(time() - start) #prints the execution time in seconds
	else :
		print 'Parameter needed : number of primes to display'
