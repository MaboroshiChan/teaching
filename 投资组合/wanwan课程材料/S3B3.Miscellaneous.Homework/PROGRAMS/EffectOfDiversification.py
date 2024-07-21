# -*- coding: utf-8 -*-
"""
It is (naturally) not possible to distribute the capital among an infinite
number of assets, nor is it necessary for achieving diversification benefits. In most
cases, the diversification effect is considerable even when only 15 assets are included
in the portfolio and the marginal benefit of including more than 50 assets is for all
practical purposes equal to zero.

To illustrate the issue of diversification the Python script below simulates a
covariance matrix and calculates the variance of portfolios with an increasing number
of assets included.

Lines [34]–[36] define the number 
of entries in the covariance matrix, i.e. the number of assets that are contained by
the eligible investment universe. The value for the variable ‘bgn’ in line [35] indicates
that two assets should be included in the first calculation. The ‘xx’ in line [36] is
simply a counting variable used when plotting the result. Lines [37]–[41] simulate the
covariance matrix. Line [37] generates the lower triangular matrix from a matrix of
uniformly distributed random variables between zero and one. A covariance matrix
is symmetric and therefore the lower triangular part of the matrix is taken so that a
symmetric matrix can be generated in line [38]. Line [36] generates a vector of standard
deviations, one for each asset; a lower bound of unity of the standard deviation is
arbitrarily chosen. In line [40] it is ensured that the diagonal of the correlation matrix
is equal to unity. Line [41] generates the covariance matrix following the calculation
cov(x1, x2)=std(x1)∗std(x2)∗corr(x1, x2). This is done through element-by-element
multiplication of the covariance and correlation matrices. Lines [45]–[48] calculate
the portfolio variance for an investment universe that increases in size by one asset
from one iteration to the next. The rest of the code plots the results.

"""
import numpy as np
import matplotlib.pyplot as plt 

nObs = 100
bgn=2
xx=np.linspace(bgn+1,nObs, num=nObs-2, endpoint=True)
#temp=np.tril(np.random.rand(nObs,nObs))
temp=np.random.uniform(low=0.9, high=1.0, size=(nObs,nObs)) #try both
temp=temp+temp.T
std=np.random.rand(nObs)+1
corr=temp-np.diag(np.diag(temp))+np.diag(np.ones((nObs)))  
cov=(std@std.T)*corr

sig2=np.ndarray(nObs-bgn)

for j in range(1,nObs-bgn):
    C=cov[1:j+bgn+1,1:j+bgn+1]
    w=(1/j)*np.ones((j+bgn,1))
    sig2[j] = w.T@C@w

sig2pct = (sig2/sig2[1])*100

# x axis values 
x = xx[1:]
# corresponding y axis values 
y = sig2pct[1:]
  
# plotting the points  
plt.plot(x, y) 