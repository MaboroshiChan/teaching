
# arr = {} | x :: xs
def max_list(arr):
    def max_list2(m, arr):
        if arr == []:
            return m
        else:
            head = arr[0]
            if head > m:
                return max_list2(head, arr[1:])
    return max_list2(-1000, arr)

def fib(n):
    if n == 1 or n == 0:
        return 1
    else: 
        return fib(n - 1) + fib(n - 2)

# (F[n], F[n - 1], n) -> (F[n] + F[n - 1], F[n], n - 1) = (F[n + 1], F[n], n - 1)
# F[n + 1] = F[n] + F[n - 1]
def fib_tail(n):
    def fib_tail(h, t, n):
        if n == 0:
            return t
        else:
            return fib(h + t, h, n - 1)
    return fib_tail(1, 1, n)

def fib_inter(n):
    h = 1
    t = 1
    while n > 0:
        h, t = h + t, h
        n -= 1
    return t

# (prod ,n) -> (prod * n, n - 1) -> (prod * n * (n - 1), n - 2) -> ... -> (prod * n * (n - 1)...2 * 1, 1)
def fac_recur(n):
    if n == 0:
        return 1
    else:
        return n * fac_recur(n - 1)
# n * fac_recur(n - 1) = n * (n - 1) * fac_recr(n - 1) ==== 

def fac_tail(n):
    def fac_tail_help(prod, n):
        if n == 0:
            return prod
        else:
            return fac_tail_help(prod * n, n - 1)
    return fac_tail_help(1, n)

def fac_iter(n):
    prod = 1
    n = n
    while n > 0:
        prod *= n
        n = n - 1
    return prod

for i in range(10):
    print(fac_tail(i), fac_iter(i))


def gcd(a, b):
    if b == 0:
        return a
    else:
        return gcd(b, a % b)

print("gcd of a and b is ",gcd(20, 15))