import itertools
N = 6
NUM_INV = 3

perms = itertools.permutations([i+1 for i in range(N)])
perms = list(perms)
print("size = " + str(len(perms)))

def count_inversions(perms):
    res = 0
    three_inv = []
    for w in perms:
        #count number of inv here.
        sum = 0
        for i in range(N):
            for j in range(i + 1, N):
                if i < j and w[i] > w[j]:
                    sum += 1
        if sum == NUM_INV:
            res += 1
            three_inv.append(w)
    print(three_inv)
    return res 

num_inversion = count_inversions(perms)
print("number of permutation with {inv} inversions is: ".format(inv=NUM_INV), num_inversion)