Function composition
injective
surjective
involution

Example 1. 
injective => left-cancellable
f . g1 == f . g2 <=> g1 == g2

==>
f . g1(x) == f . g2(x)

g1(x) == g2(x)

<==
g1(x) = g2(x)
f(g1(x)) == f(g2(x))

Example 2. m' == m

1. k must be injective. 
m . k = Id
k . m = Id 

m'. k = Id
k . m' = Id

k . m == k . m' ==>m = m'

example3:

$$
\begin{align*}
F_{n}F_{n-1} - F_{n+1}F_{n-2} &= (F_{n-1} + F_{n-2})F_{n-1} - (F_{n} + F_{n-1})F_{n-2}\\
&=F_{n-1}^2+F_{n-1}F_{n-2} - F_{n}F_{n-2}-F_{n-1}F_{n-2}\\
&=F^2_{n-1}-F_{n}F_{n-2}\\
&=-(-1)^n\\
&=(-1)^{n+1}
\end{align*}
$$
