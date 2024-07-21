import random

TOTAL = 100000

def dice_roll_2(n):
    success = 0
    for i in range(TOTAL):
        x = random.randint(1, 6)
        y = random.randint(1, 6)
        if x + y == n:
            success += 1
    return success / TOTAL

def dice_roll_2(n, m):
    success = 0
    for i in range(TOTAL):
        x = random.randint(1, 6)
        y = random.randint(1, 6)
        if x + y == n or x + y == m:
            success += 1
    return success / TOTAL

def dice_roll_2(n, m, k):
    success = 0
    for i in range(TOTAL):
        x = random.randint(1, 6)
        y = random.randint(1, 6)
        z = random.randint(1, 6)
        if x + y + z == n or x + y + z == m or x + y + z == k:
            success += 1
    return success / TOTAL

def problem_5():
    sum = 0
    for _ in range(TOTAL):
        x = random.randint(1, 6)
        res = -1
        if x > 3:
            res = x
        else:
            res = max(x, random.randint(1, 6))
        sum += res
    print("Expected value: ", sum / TOTAL)

def problem_5_sub():
    sum = 0
    for _ in range(TOTAL):
        Y = random.randint(1, 3)
        X = random.randint(1, 6)
        sum += max(X, Y)
    print("Expected value: ", sum / TOTAL)

def problem_5_sub_2():
    sum = 0
    for _ in range(TOTAL):
        Y = random.randint(4, 6)
        sum += Y
    print("Expected value: ", sum / TOTAL)

problem_5()
