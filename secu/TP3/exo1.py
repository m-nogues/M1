#!/usr/bin/env python
from random import randrange

from random import getrandbits
<<<<<<< HEAD
#renvoi (x^y)%n en utilisant l'exponentiation modulaire O(log(n))
def lpowmod(x, y, z): 
=======
# renvoi (x^y)%n
def lpowmod(x, y, z):

>>>>>>> 57e0e8be082347501000c61075e4e5f11d811be2
    a = 1
    while y > 0:
        if y%2==1:
            a = (a*x)%z
        x = (x*x)%z
        y //= 2
    return a


#true si a^p =1 mod p 
def fermat(a, p):
	if p==2:
		return True
	if not p&1:
		return False
	return lpowmod(a, p-1, p)==1

#~ print fermat(7,19)
<<<<<<< HEAD
# renvoi true si 2^p-1 = 3^p-1 = 5^p-1 = 7^p-1 = 1 mod p
def prim(n) :
	if n not in [0,1,2,3,5,7] :#on exclu ces valeur pour n
		for  i in [2, 3, 5, 7] :# pour chaque valeur on regarde si fermat renvoi true
			r = fermat(i, n)
			if r != 1 :
=======
# renvoi true si
def prim(n) :
	if n not in [0, 1, 2, 3, 5, 7] :
		for  i in [2, 3, 5, 7] :
			r = fermat(n, i)
			if r!=1 :
>>>>>>> 57e0e8be082347501000c61075e4e5f11d811be2
				return False
		return True




#~ print prim(7)
#revoi un nombre premier de n bit en utilisant la primalite de PGP
def randompri(n) :
	a = getrandbits(n)
<<<<<<< HEAD
	while prim(a) == False or a ==1 or a== 0 : 
		a = getrandbits(n)
	return a


#print randompri(77)
# return inverse modulaire et pgcd 
def inverse_modulaire(a, b):
	r = a
	rp = b
	u = 1
	v = 0
	up = 0
	vp= 1
	while(rp != 0):
		q = r/rp
		rs = r
		us = u
		vs = v
		r = rp
		u = up
		v = vp
		rp = rs - q*rp
		up = us - q*u
		vp = vs - q*vp
	
	return u , r
=======
	while prim(a)==False or a==1 or a==0 or a%2==0:
		a = getrandbits(n)
	return a
#~ print randompri(15)

#print randompri(77)
def epgcd(a, b):
    if a == 0:
        return (b, 0, 1)
    else:
        g, y, x = epgcd(b%a, a)
        return (g, x - (b // a)*y, y)

def extended_gcd(a, b):
    """Returns pair (x, y) such that xa + yb = gcd(a, b)"""
    x, lastx, y, lasty = 0, 1, 1, 0
    while b != 0:
        q, r = divmod(a, b)
        a, b = b, r
        x, lastx = lastx - q * x, x
        y, lasty = lasty - q * y, y
    return lastx, lasty


def inverse_modulaire(e, n):
    """Find the multiplicative inverse of e mod n."""
    x, y = extended_gcd(e, n)
    if x < 0:
        return n + x
    return x


#print invmod(5,97)
def pgcd(a, b):
    while b != 0:
        a, b = b, a % b
    return a

#~ def inverse_modulaire(e	, phi):
	#~ d = 0
	#~ x1 = 0
	#~ x2 = 1
	#~ y1 = 1
	#~ temp_phi = phi

	#~ while e > 0 :
		#~ temp1 = temp_phi/e
		#~ temp2 = temp_phi - temp1 * e
		#~ temp_phi = e
		#~ e = temp2

		#~ x = x2- temp1* x1
		#~ y = d - temp1 * y1
		#~ x2 = x1
		#~ x1 = x
		#~ d = y1
		#~ y1 = y

>>>>>>> 57e0e8be082347501000c61075e4e5f11d811be2


#~ print inverse_modulaire(77,1044)

# genere un le tuple (e, n,p,q,d) pour le couple de cles RSA
def keygenerator(taille) :
	p = q = 0
	#on prend un premier aleatoire different pour p et q
	while p == q :
		p = randompri(taille)
		q = randompri(taille)
	n = p *q
	print "phi(n) , n-phi(n)"
	phiden =  (p - 1) * (q - 1)
<<<<<<< HEAD
	print (phiden, n-phiden)
	e = randrange(1, phiden) # on prend un e aleatoire compris entre 1 et phi(n)
	inv , pgcd = inverse_modulaire(e,phiden) # on recupere le pgcd de e et phi(n)
	while  pgcd != 1: # si il ne sont pas copremier
		e = randrange(1, phiden) # on reprend un e aleatoire
		inv , pgcd = inverse_modulaire(e,phiden)
	d,_ = inverse_modulaire(e, phiden) # on calcul d td d inverse de e mod phi(n)
	while d < 0 : #si d < 0 on ajoute phi(n) 
		d = d +phiden	
	if ((d >= phiden) or (d==e) ) :# di d est sup a phi(n) ou que e a pour inverse lui meme on creer une nouvelle cle
		keygenerator(taille)
	return (e, n,p,q,d)


#genere les tuples cle publique , cle prive
def key(taille):
	e,n,p,q,d = keygenerator(taille)
	return (e,n), (d,n)

    
=======
#	print (phiden, n-phiden)
	e = randrange(1, phiden)
	while  pgcd(e,phiden) != 1:
		e = randrange(1, phiden)
	d = inverse_modulaire(e, phiden)
	#~ while ((d > phiden) or (d==e)) :
		#~ d = d - phiden
	return (e, n), (p,q,d, n)

#public, private = keygenerator(512)


message = 123

def rsa_encrypt(message, n, e):
    return lpowmod(message, e, n)


def rsa_decrypt(cipher, n, d):
    return lpowmod(cipher, d, n)


>>>>>>> 57e0e8be082347501000c61075e4e5f11d811be2
def encrypt(pk, plaintext):
    #la cle publique
    e, n = pk
    #pour chaque caratere on aplique c^e mod n
    cipher = [lpowmod(ord(char),e,n) for char in plaintext] # on conveti le char en ascii
    return cipher

def decrypt(pk, ciphertext):
   #la cle privee
<<<<<<< HEAD
	d, n = pk
   #pour chaque caractere on aplique c^d mod n
	plain = [(lpowmod(char,d,n)) for char in ciphertext]
=======
	p, q, d, n = pk
   #pour chaque caractere on aplique c^d mod n
	plain = [(lpowmod(char, d, n)) for char in ciphertext]
#	print plain
>>>>>>> 57e0e8be082347501000c61075e4e5f11d811be2
	return plain

if __name__ == '__main__':
<<<<<<< HEAD
	message = raw_input("entrer un message a chifrer : ")
	taille = int(raw_input("entrer une taille pour p et q :"))
	public, private= key(taille)
	e,n = public
	d,n = private
	print (public , private)
	messagetab = [ord(char) for char in message] # on converti le message initiale en ascci
	print ("message initial")
	print (messagetab)
	encrypted_msg = encrypt(public, message)#chiffrement du message
	print ("message chiffre")
	print (encrypted_msg)
	print ("message dechifre")
	decrypt_msg= decrypt(private, encrypted_msg)# dechifrement du message
=======
	#print "RSA Encrypter/ Decrypter"
	#print "Your public key is ", public ," and your private key is ", private
	#message = raw_input("Enter a message to encrypt with your private key: ")
	public, private= keygenerator(10)
	e, n = public
	p, q, d, n = private
	print (public, private)
	#~ messagetab = [ord(char) for char in message]
	print ("message initial")
	#~ print (messagetab)
	#~ encrypted_msg = encrypt(public, message)
	encrypted_msg = rsa_encrypt(message, e , n)
	print ("message chiffre")
	print (encrypted_msg)
	print ("message dechifre")
	#~ decrypt_msg= decrypt(private, encrypted_msg)
	decrypt_msg = rsa_decrypt(encrypted_msg, d, n)
>>>>>>> 57e0e8be082347501000c61075e4e5f11d811be2
	print (decrypt_msg)
	if messagetab==decrypt_msg : #si le tableau ascii du dechifrement est identique au tableau du message initial le chiffrement/dechifrement et fonctionnel
			print ("chifrement/dechifrement focntionnel")
	else :
<<<<<<< HEAD
		print "erreur lors du dechifrement"
	
			
		

=======
		print ("erreur")
	if p*q==n :
		print ("n OK")
	else :
			print ("n pas ok")
	phi = (p-1)*(q-1)
	print (phi)
	if pgcd(e, phi)==1:
		print ("e OK")
	else :
		print ("e pas ok")
	invmod= inverse_modulaire(e, phi)
	if invmod < 0:
		invmod = d + phi
	print invmod
	if d==invmod:
		print ("d invmod ok")
	else :
		print ("d invmod pas ok")
	if d < phi:
		print ("d inf phi ok")
	else :
		print ("d inf phi pas ok")


	#~ while encrypted_msg!=decrypt_msg :
		#~ public, private= keygenerator(20)
		#~ i= i+1
		#~ print i
		#~ encrypted_msg = encrypt(public, message)
		#~ decrypt_msg= decrypt(private, encrypted_msg)
		#~ if encrypted_msg==decrypt_msg :
			#~ print "chifrement/dechifrement focntionnel"
>>>>>>> 57e0e8be082347501000c61075e4e5f11d811be2
