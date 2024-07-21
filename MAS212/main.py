import matplotlib.pyplot as plt
import numpy as np
from scipy.integrate import solve_ivp
from numba import jit

'''
delta = 0.025
x = np.arange(-3.0, 3.0, delta)
y = np.arange(-2.0, 2.0, delta)
X, Y = np.meshgrid(x, y)
print(X, Y)
Z1 = X ** 2 + Y ** 2
Z = (Z1) * 2
fig, ax = plt.subplots()
CS = ax.contour(X, Y, Z)
ax.clabel(CS, inline=True, fontsize=10)
ax.set_title('Simplest default with labels')
plt.show()
'''


def h(x, y):
    return 1/2 * (x ** 2 + y ** 2) + (x ** 2 * y) - (1 / 3 * y ** 3)


def contour(): # part 2
    levels = np.arange(-1, 1, 0.05)
    delta = 0.0025
    x = np.arange(-1.5, 1.5, delta)
    y = np.arange(-1.5, 1.5, delta)

    X, Y = np.meshgrid(x, y)
    Z = h(X, Y)

    fig, ax = plt.subplots()
    CS = ax.contour(X, Y, Z, levels=levels)
    ax.clabel(CS, inline=True, fontsize=10)
    ax.set_title('Contour of h(x,y)')
    plt.show()

    return X, Y, Z, levels


def odefun(t, z):
    x, y, px, py = z
    p_x = -x - 2 * x * y
    p_y = -y - x * x + y * y
    return [px, py, p_x, p_y]


def solving_odes():  # Part 2

    tspan = (0, 100)  # total time and steps.
    x0, y0 = 0.6, 0
    px0, py0 = 0, 0.6
    init0 = [x0, y0, px0, py0]

    if h(x0, y0) <= 1 / 6:
        print("the trajectory will stay in basin")
    else:
        print("the trajectory will diverge")

    sol = solve_ivp(odefun, tspan, init0, dense_output=True,
                    max_step=0.001, atol=1e-10, rtol=1e-12)
    t = np.linspace(0, 100, 8000)
    z = sol.sol(t)
    return z, t


@jit
def escape():  # part 3
    tspan = (0, 100)
    _x0 = np.linspace(0, 0.8, 50)
    y0 = 0
    px0 = 0
    _py0 = np.linspace(0, 0.8, 50)

    res = []
    index = 0
    for x0 in _x0:
        for py0 in _py0:
            init0 = [x0, y0, px0, py0]
            sol = solve_ivp(odefun, tspan, init0, dense_output=True,
                            max_step=0.1, atol=1e-12, rtol=1e-12)
            z_last = sol.sol(100)
            print("z = ", sol.sol(12), "index = ", index)
            index += 1
            if z_last[0] ** 2 + z_last[1] ** 2 > 1.5:
                if z_last[1] > 0:
                    res.append([x0, py0, 1])
                elif z_last[0] < 0:
                    res.append([x0, py0, 2])
                else:
                    res.append([x0, py0, 3])
            else:
                res.append([x0, py0, 0])

    colors = [e[2] for e in res]
    X = [e[0] for e in res]
    Y = [e[1] for e in res]

    plt.title("Escape with initial points")
    plt.scatter(X, Y, c=colors)
    plt.show()


z, t = solving_odes()
plt.plot(t, z.T)
plt.xlabel('t')
plt.legend(['x', 'y', 'px', 'py'], shadow=True)
plt.show()

# Part2
X, Y, Z, levels = contour()

CS = plt.contour(X, Y, Z, levels=levels)
plt.clabel(CS, inline=True, fontsize=10)
plt.show()


plt.plot(z[0], z[1])
plt.xlim(-1.5, 1.5)
plt.ylim(-1.5, 1.5)
plt.xlabel('t')
plt.xlabel('x')
plt.ylabel('y')
plt.title('Trajectory')
plt.show()

escape()