count = 0
for i in range(10, 100):
    if (not i % 2 == 0) and not (i % 3 == 0):
        count += 1
print(count)