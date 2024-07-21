booleans = [0,1]

def value(a, b, c):
    if (a or b or (not c)) and (a or (not b) or c) and ((not a) or b or c):
        return "True"
    else:
        return "False"

for i in booleans:
    for j in booleans:
        for k in booleans:
            print("{i}, {j}, {k}  {v}".format(i=i,j=j,k=k,v=value(i,j,k)))

p5a = lambda x: x <= 0
p5b = lambda x: x < 0 or x >= 100