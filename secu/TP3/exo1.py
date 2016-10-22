#!/usr/bin/env python
from random import randrange

from random import getrandbits
# renvoi (x^y)%n
def lpowmod(x, y, z):
    
    number = 1
    while y:
        if y & 1:
            number = number * x % z
        y >>= 1
        x = x * x % z
    return number


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
	a = getrandbits(n)
	while prim(a) == False or a ==1 or a== 0 or a%2==0:
		a = getrandbits(n)
	return a
#~ print randompri(15)

#print randompri(77)
def epgcd(a, b):
    if a == 0:
        return (b, 0, 1)
    else:
        g, y, x = epgcd(b % a, a)
        return (g, x - (b // a) * y, y)

#def xpgcd(b, n):
    #x0, x1, y0, y1 = 1, 0, 0, 1
    #while n != 0:
        #q, b, n = b // n, n, b % n
        #x0, x1 = x1, x0 - q * x1
        #y0, y1 = y1, y0 - q * y1
    #return  b, x0, y0

#def invmod(a, m):
    #g, x, y = epgcd(a, m)
    #if g == 1:
        #return x % m


#print invmod(5,97)
def pgcd(a, b):
    while b != 0:
        a, b = b, a % b
    return a

def inverse_modulaire(e, phi):
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

#print inverse_modulaire(,)

def keygenerator(taille) :
	p = q = 0
	while p == q :
		p = randompri(taille)
		q = randompri(taille)
	n = p *q
	phiden =  (p - 1) * (q - 1)
#	print (phiden, n-phiden)
	e = randrange(1, phiden)
	while  pgcd(e,phiden) != 1:
		e = randrange(1, phiden)
	d = inverse_modulaire(e, phiden)
	
	#~ while d <= 0 or d==e :
		#~ d = d + phiden
	return (e, n), (p,q,d, n)

#public, private= keygenerator(512)


message = 'abc'

def encrypt(pk, plaintext):
    #la cle publique
    e, n = pk
    #pour chaque caratere on aplique c^e mod n
    cipher = [lpowmod(ord(char),e,n) for char in plaintext]
 #   print cipher
    return cipher

def decrypt(pk, ciphertext):
   #la cle privee
	p,q,d, n = pk
   #pour chaque caractere on aplique c^d mod n
	plain = [(lpowmod(char,d, n) for char in ciphertext]
#	print plain
	return plain
	
if __name__ == '__main__':
	#print "RSA Encrypter/ Decrypter"
	#print "Your public key is ", public ," and your private key is ", private
	#message = raw_input("Enter a message to encrypt with your private key: ")
	public, private= keygenerator(512)
	e,n = public
	p,q,d,n = private
	print public , private
	encrypted_msg = encrypt(public, message)
	print ("message chiffre")
	print encrypted_msg
	print "message dechifre"
	decrypt_msg= decrypt(private, encrypted_msg)
	print decrypt_msg
	if encrypted_msg==decrypt_msg :
			print "chifrement/dechifrement focntionnel"
	else :
		print "erreur"
	if p*q==n :
		print "n OK"
	else :
			print "n pas ok"
	phi = (p-1)*(q-1)
	print phi
	if pgcd(e, phi)==1:
		print "e OK"
	else : 
		print "e pas ok"
	invmod= inverse_modulaire(e, phi)
	if invmod<0:
		invmod = d+ phi
	print invmod
	if d==invmod:
		print "d invmod ok"
	else : 
		print "d invmod pas ok"
	if d< phi:
		print "d inf phi ok"
	else : 
		print "d inf phi pas ok"
	
	
	#~ while encrypted_msg!=decrypt_msg :
		#~ public, private= keygenerator(20)
		#~ i= i+1
		#~ print i
		#~ encrypted_msg = encrypt(public, message)
		#~ decrypt_msg= decrypt(private, encrypted_msg)	
		#~ if encrypted_msg==decrypt_msg :
			#~ print "chifrement/dechifrement focntionnel"
			
		

