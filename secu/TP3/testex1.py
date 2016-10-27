#!/usr/bin/env python


import random

def lpowmod(x, y, z): 
    a = 1
    while y > 0:
        if y % 2 == 1:
            a = (a * x) % z
        x = (x * x) % z
        y //= 2
    return a


def gcd(a, b):
    while b != 0:
        a, b = b, a % b
    return a

#true si a^p =1 mod p
def fermat(a, p):
	if p == 2:
		return True
	if not p & 1:
		return False
	return lpowmod(a, p-1, p) == 1
    
#~ print fermat(7,19)
# renvoi true si 
def prim(n) :
	if n not in [0,1,2,3,5,7] :
		for  i in [2, 3, 5, 7] :
			r = fermat(n, i)
			if r != 1 :
				return False
		return True
	
			
		

#~ print prim(7)
#revoi un nombre premier de n bit
def randompri(n) :
	a = random.getrandbits(n)
	while prim(a) == False or a ==1 or a== 0 or a%2==0:
		a = random.getrandbits(n)
	return a
#~ print randompri(15)
def multiplicative_inverse(e, phi):
    d = 0
    x1 = 0
    x2 = 1
    y1 = 1
    temp_phi = phi
    
    while e > 0:
        temp1 = temp_phi/e
        temp2 = temp_phi - temp1 * e
        temp_phi = e
        e = temp2
        
        x = x2- temp1* x1
        y = d - temp1 * y1
        
        x2 = x1
        x1 = x
        d = y1
        y1 = y
    
    if temp_phi == 1:
        return d + phi


def is_prime(num):
    if num == 2:
        return True
    if num < 2 or num % 2 == 0:
        return False
    for n in xrange(3, int(num**0.5)+2, 2):
        if num % n == 0:
            return False
    return True
def simple_is_prime(n):
   
    if n % 2 == 0:
        return n == 2
    if n % 3 == 0:
        return n == 3
    k = 6L
    while (k - 1) ** 2 <= n:
        if n % (k - 1) == 0 or n % (k + 1) == 0:
            return False
        k += 6
    return True
def generate_keypair(p, q):
    if not (simple_is_prime(p) and simple_is_prime(q)):
        raise ValueError('Both numbers must be prime.')
    elif p == q:
        raise ValueError('p and q cannot be equal')
    #n = pq
    n = p * q

    #Phi is the totient of n
    phi = (p-1) * (q-1)

    #Choose an integer e such that e and phi(n) are coprime
    e = random.randrange(1, phi)

    #Use Euclid's Algorithm to verify that e and phi(n) are comprime
    g = gcd(e, phi)
    while g != 1:
        e = random.randrange(1, phi)
        g = gcd(e, phi)

    #Use Extended Euclid's Algorithm to generate the private key
    d = multiplicative_inverse(e, phi)
    
    #Return public and private keypair
    #Public key is (e, n) and private key is (d, n)
    return ((e, n), (d, n))

def encrypt(pk, plaintext):
    #Unpack the key into it's components
    key, n = pk
    #Convert each letter in the plaintext to numbers based on the character using a^b mod m
    cipher = [lpowmod(ord(char),  key, n)  for char in plaintext]
    #Return the array of bytes
    return cipher

def decrypt(pk, ciphertext):
    #Unpack the key into its components
    key, n = pk
    #Generate the plaintext based on the ciphertext and key using a^b mod m
    plain = [lpowmod(char, key,n) for char in ciphertext]
    #Return the array of bytes as a string
    return plain#''.join(plain)
    

if __name__ == '__main__':
	print "RSA Encrypter/ Decrypter"
	#~ p = int(raw_input("Enter a prime number (17, 19, 23, etc): "))
	#~ q = int(raw_input("Enter another prime number (Not one you entered above): "))
	print "Generating your public/private keypairs now . . ."
	p = randompri(512)
	q= randompri(512)

	public, private = generate_keypair(p, q)
	#~ print "Your public key is ", public ," and your private key is ", private
	#~ message = raw_input("Enter a message to encrypt with your private key: ")
	message ="hello"
	messagetab = [ord(char) for char in message] 
	print messagetab
	encrypted_msg = encrypt(private, message)
	print "Your encrypted message is: "
	print ''.join(map(lambda x: str(x), encrypted_msg))
	print "Decrypting message with public key ", public ," . . ."
	print "Your message is:"
	decrypt_msg = decrypt(public, encrypted_msg)
	print decrypt_msg
	if messagetab==decrypt_msg :
		print ("chifrement/dechifrement focntionnel")
	else :
		print "erreur"
