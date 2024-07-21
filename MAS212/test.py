import numpy as np

s = np.linspace(0, 1, 10)

sum = 0
index = 0
for x in s:
    sum += x
    index += 1

print(sum, index)