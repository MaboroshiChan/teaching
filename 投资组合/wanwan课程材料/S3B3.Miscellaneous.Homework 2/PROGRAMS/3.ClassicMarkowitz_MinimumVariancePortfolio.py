# -*- coding: utf-8 -*-
"""
Classic Markowitz for Minimum Variance Portfolio (no fixed return required)

@author: Rosario
"""

import numpy as np
import pandas as pd
import scipy.optimize as sco

# load data
data= pd.read_csv('L5_Data.csv', index_col =0)  

#Compute the (log) returns from prices.
#returns=np.log(data/data.shift(1)) #do not use
returns=(data-data.shift(1))/data.shift(1)

#Get the number of assets as a variable.
no_assets=len(returns.columns.tolist())

#Application: Minimum variance portfolio
#Minimum variance portfolio 

# A portfolio-building function
#note that this function annualizes on the basis of 252, 
#meaning it assumes returns are daily returns (252 returns in a year)
def portfolio(weights):
    weights = np.array(weights)
    P_ret = np.sum(returns.mean()*weights)*252
    P_vol = np.sqrt(np.dot(weights.T, np.dot(returns.cov()*252, weights)))
    return np.array([P_ret,P_vol, P_ret/P_vol])

#Define a function for the portfolio variance.
#Variance is the square of the volatility a.k.a. standard deviation
def Variance(weights):
	return portfolio(weights)[1]**2

#Set up the constraint that portfolio weights add up to one.
cons=({'type':'eq', 'fun':lambda x: np.sum(x)-1})
#Set up boundaries for the portfolio weights (between 0 and 1) (=no short selling)
bnds=tuple((0,1) for x in range(no_assets))

#Optimisation function.
#scipy.optimize.minimize(fun, x0, args=(), method=None, jac=None, hess=None, hessp=None, bounds=None, constraints=(), tol=None, callback=None, options=None)
#fun : callable
#The objective function to be minimized.
#in this case: Variance 
#x0 : ndarray, shape (n,)
#Initial guess. Array of real elements of size (n,), where ‘n’ is the number of independent variables.
#In this case: [0.2, 0.2, 0.2, 0.2, 0.2]
#method : str or callable, optional
#Type of solver
#In this case: Sequential Least SQuares Programming (SLSQP)
#bounds : sequence or Bounds, Sequence of (min, max) pairs for each element in x. 
#in this case: bnds
#constraints : {Constraint, dict} 
#Equality constraint means that the constraint function result is to be zero whereas 
#inequality means that it is to be non-negative

#Optimisation function.
#we minimize the variance
result = sco.minimize(Variance, no_assets*[1.0/no_assets], method='SLSQP', bounds=bnds, constraints=cons)

#Print optimized minimum Variance portfolio weights.
print("weights= ", result['x'].round(3)) # weights
#Minimum variance portfolio properties.
print("P_ret,P_vol,P_ret/P_vol=", portfolio(result['x'])) # statistics  P_ret,P_vol, P_ret/P_vol
