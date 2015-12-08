def smallest(l):
	min = l[0]
	for e in l:
		if e < min:
			min = e
	l.remove(min)
	return min

def smallestTwo(l):
	return smallest(l), smallest(l)
	
def totalWrapping(t):
	h, w, l = t

	s0, s1 = smallestTwo([h,w,l])
	slack = s0*s1
	
	wrappingPaper = 2*(h*w + w*l + h*l)
	
	return slack + wrappingPaper


def getDimensionsFromString(s):
	h, w, l = map(int, s.split("x"))
	return h, w, l

def totalRibbon(t):
	h, w, l = t
	bow = h*w*l
	
	s0, s1 = smallestTwo([h,w,l])
	wrap = s0*2 + s1*2

	return bow + wrap

	
f = open("input.txt", 'r')
dimensions = list(map(lambda s: getDimensionsFromString(s.strip()), f.readlines()))

wrapping = list(map(totalWrapping, dimensions))
ribbon = list(map(totalRibbon, dimensions))


allOfWrapping = 0
for e in wrapping:
	allOfWrapping += e

allOfRibbon = 0
for e in ribbon:
	allOfRibbon += e

print("total of wrapping paper: ", allOfWrapping)
print("total of ribbon: ", allOfRibbon)

