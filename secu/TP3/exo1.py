import random
import os

def lpowmod(x, y, n):
    result = 1
    while y>0:
        if y&1>0:
            result = (result*x)%n
        y >>= 1
        x = (x*x)%n    
    return result




def fermat(a, p):
    return pow(a, p-1, p) == 1
    
 
def prim(p) :
	if p not in [0,2,3,5,7] :
		
		for  i in [2, 3, 5, 7] :
			r =fermat(p, i)
			if r!= 1 :
				r = False
	return r
	
#print prim(97)	
	
def randompri(n) :
	a = random.getrandbits(n)
	while prim(a)==False :
		a = random.getrandbits(n)
	return a
	
#print randompri(77)
#def epgcd(a, b):
    #if a == 0:
        #return (b, 0, 1)
    #else:
        #g, y, x = epgcd(b % a, a)
        #return (g, x - (b // a) * y, y)

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

def inverse_modulaire(a, b) :
	r = a
	rp = b
	u = 1
	v = 0
	up = 0
	vp= 1
	 
	while rp != 0 :
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
	
	return u
print inverse_modulaire(5,77)	
def keygenerator() :
	p = q = 0
	while p==q :
		p = randompri(64)
		q = randompri(64)
	n = p *q
	phiden =  (p-1)*(q-1)
	e= phiden
	while inverse_modulaire(e,phiden) != 1:
		e = random.randint(3, e-1)
	d = inverse_modulaire(e, phiden)
	while d<0 :
		d= d + phiden
	return e,d,n ,p, q

print keygenerator()
	
# afficher les 100 nombre permier 


