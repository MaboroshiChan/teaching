from scipy.integrate import solve_ivp
from math import sin

from sympy import solve
import numpy as np
import matplotlib.pyplot as plt

def func1(x, t):
    return sin(x + t)

def func2(x, t):
    r = x - t
    return r * r

t = np.linspace(0, 10, 101)

sol = solve_ivp(fun=func1, y0=[0, 100], [0,20,40,60,80])
plt.plot([0, 100], sol.y)
plt.xlabel('t')
plt.legend(['x', 'y'], shadow=True)
plt.title('Exercise')
plt.show()