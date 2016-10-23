#!/usr/bin/env python
from random import randrange

from random import getrandbits
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




#~ print prim(7)
#revoi un nombre premier de n bit en utilisant la primalite de PGP
def randompri(n) :
	a = getrandbits(n)
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


#~ print inverse_modulaire(77,1044)

# genere un le tuple (e, n,p,q,d) pour le couple de cles RSA
def keygenerator(taille) :
	p = q = 0
	#on prend un premier aleatoire different pour p et q
	while p == q :
		p = randompri(taille)
		q = randompri(taille)
	n = p *q
	print ("phi(n) , n-phi(n)")
	phiden =  (p - 1) * (q - 1)
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


def encrypt(pk, plaintext):
    #la cle publique
    e, n = pk
    #pour chaque caratere on aplique c^e mod n
    cipher = [lpowmod(ord(char),e,n) for char in plaintext] # on conveti le char en ascii
    return cipher

def decrypt(pk, ciphertext):
   #la cle privee
	d, n = pk
   #pour chaque caractere on aplique c^d mod n
	plain = [(lpowmod(char,d,n)) for char in ciphertext]
	return plain

if __name__ == '__main__':
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
	print (decrypt_msg)
	if messagetab==decrypt_msg : #si le tableau ascii du dechifrement est identique au tableau du message initial le chiffrement/dechifrement et fonctionnel
			print ("chifrement/dechifrement focntionnel")
	else :
		print ("erreur lors du dechifrement")
