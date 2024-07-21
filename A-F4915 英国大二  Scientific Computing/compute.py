from scipy import interpolate
import numpy as np
import matplotlib.pyplot as plt
from math import factorial

def MySinh(x,N=1000,epsi=1.0e-5):
    fac = 1 # denominator
    res = 0 # output
    prod = 1 # x ^ n
    index = 1 # 

    for i in range(1, 2 * N + 1, 2):
        while index < i + 1:
            prod *= x
            fac *= index
            index += 1
        res += prod / fac
    
    return res

def quadratic(f, x1, x2, x3):
    (f1, f2, f3) = (f(x1), f(x2), f(x3))
    f_mat = np.array([[f1], [f2], [f3]]) # (f1, f2, f3)^T
    
    M = np.array([[x1 * x1, x2 * x2, x3 * x3], [x1, x2, x3], [1, 1, 1]])
    M = np.matrix.transpose(M)
    M = np.linalg.inv(M) # inverse

    coes = np.matmul(M, f_mat) # multiplication
    return (lambda x: x * x * coes[0] + x * coes[1] + coes[2])

def plot_quadratic():
    # plot 
   x = np.linspace(-10, 10, 100)
   f = quadratic(lambda x: x ** 3 , 1, 2, 3)
   plt.plot(x, f(x), color='red')
   plt.show()

def redshift_interpolation(z, X1, X2):
    p1 = interpolate.CubicSpline(z, X1)
    p2 = interpolate.CubicSpline(z, X2)

    plt.plot(z, p1(z))
    plt.show()

    plt.plot(z, p2(z))
    plt.show()

#print(MySinh(2, 10))
#print("numpy.sinh = {res}".format(res=np.sinh(2)))

#print(quadratic(lambda x: x + 1, 1.,2.,3.))
#plot_quadratic()
z0 = 1.0000000e-01
z = [i * z0 for i in range(1, 11)]
X1 = [
        4.3327843e+02,
        8.4565604e+02,
        1.2367089e+03,
        1.6065104e+03,
        1.9555308e+03,
        2.2845323e+03,
        2.5944725e+03,
        2.8864243e+03,
        3.1615125e+03,
        3.4208678e+03
    ]
X2 = [
    4.3490824e+02,
    8.5199526e+02,
    1.2504843e+03,
    1.6300282e+03,
    1.9906572e+03,
    2.3327094e+03,
    2.6567584e+03,
    2.9635438e+03,
    3.2539121e+03,
    3.5287680e+03
]
print("new version")
redshift_interpolation(z, X1, X2)


"""
        print("i = ", i)
        print("fac = ", fac)
        print("prod = ", prod)
        print("res = ", res)
        print(" ")
"""