def gcd(a,  b):
    print("gcd({},{})".format(a, b))
    return a if b == 0 else gcd(b, a % b)

def extended_gcd(a, b):
    if a < b:
        a, b = b, a

    old_r, r = a, b
    old_s, s = 1, 0
    old_t, t = 0, 1

    while r != 0:
        quotient = old_r // r
        old_r, r = r, old_r - quotient * r
        old_s, s = s, old_s - quotient * s
        old_t, t = t, old_t - quotient * t
    
    print("Bézout coefficients:", (old_s, old_t))
    print("greatest common divisor:", old_r)
    print("quotients by the gcd:", (t, s))


extended_gcd(663, 234)



def robot():
    pass