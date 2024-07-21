nums = [6, 12, 29, 28, 34, 11, 23, 7, 0, 33, 30, 45,10001]

linear_probe = [None for _ in range(17)]
double_hash = [None for _ in range(17)]

#linear probing
for n in nums:
    m = n % 17
    if linear_probe[m] != None:
        i = m
        terminate = 0
        while linear_probe[i] != None and terminate < 18:
            i += 1
            i = i % 17
            terminate += 1
        if terminate >= 18:
            raise Exception("full")
        linear_probe[i] = n
    else:
        linear_probe[m] = n

print("Linear probe = ", linear_probe)


for n in nums:
    m = n % 17
    if double_hash[m] != None:
        terminate = 0
        while double_hash[m] != None and terminate < 18:
            d = 1 + m % 5
            m += d
            m = m % 17
            terminate += 1
        if terminate >= 18:
            raise Exception("full")
        double_hash[i] = n
    else:
        double_hash[m] = n

print("Double hashing = ", double_hash)