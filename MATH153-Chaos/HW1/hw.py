import scipy
from matplotlib import pyplot as plt

# This function solves a 2x2 system of linear differential equation.
def solve_2x2_system(A, x0, t):
    # A is a 2x2 matrix
    # x0 is a 2x1 vector
    # t is a 1xN vector
    # return x, where x is a 2xN vector
    x = scipy.zeros((2, len(t)))
    x[:,0] = x0
    for i in range(1, len(t)):
        x[:,i] = scipy.linalg.expm(A*t[i]) @ x0
    return x

# This function plot the solution of a 2x2 system of linear differential equation, from -10 to 10.
def plot_2x2_system(solution_array):
    # solution_array is a 2xN vector
    plt.plot(solution_array[0,:], solution_array[1,:])
    plt.xlabel('x')
    plt.ylabel('y')
    plt.title('2x2 System of Linear Differential Equation')
    plt.show()

A = scipy.array([[3, -1], [2, 0]])
x0 = scipy.array([0, 5])
t = scipy.linspace(0, 10, 100)
x = solve_2x2_system(A, x0, t)
plot_2x2_system(x)