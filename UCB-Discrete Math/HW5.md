# Problem 1 

$130 = 13 \times 10 = 2\times 5\times 13$

It suffices to show 
$$m^{13} = m \mod 2$$
$$m^{13} = m \mod 5$$
$$m^{13} = m \mod 13$$

By FLT

$$\begin{align*}m^2 &= m \mod 2\iff m^{12}\\&= m^6 \mod 2\iff m^{13}\\ &= m^{7}\\&=mm^2 m^2 m^2\\&=mmmm\\&=m^4 \\&=m^2 m^2 = m^2=m\mod 2\end{align*}$$

$$m^5 = m \mod 5\iff m^{10} = m^2 \mod 5 \iff m^{13} = m^5 \mod 5\iff m^{13} = m^5 = m \mod 5$$

# Problem 2
(a) $p-1$

(b) **Counting**, **Complement set**

$$\phi (p^{k})=p^{k}-\frac{p^{k}}{p} =p^{k}-p^{k-1}$$

(c)

Keywords: **Set theory**, **Bijection**, **Direct product of sets**, **CRT**, **Coprime**

By Chinese Remainder Theorem, if $x$ is coprime to $a$ and $b$,

$$x=m \mod a, x=n \mod b$$

has a unique solution in $\mod ab$.

Conversely, if $x = u\mod ab$, then we can compute $x=u_1 \mod a$, and $x_u2 = \mod b$ as well.

Then we can construct a bijection from $\mod a\times\mod b$ to $\mod ab$. There are $\phi(a)$ such number of $x$ in $\mod a$, $\phi(b)$ such number of $x$ in $\mod b$, and $\phi(ab)$ such number of $x$ in $\mod ab$. Thus
$$\phi(ab) = \phi(a)\phi(b)$$

(d)

$$\begin{align*}
    \phi(n) &= \phi(p_{1}^{e_1})\cdots\phi(p_{k}^{e_k})\\
            &= (p_{1}^{e_1}-p_{1}^{e_1-1})\cdots(p_{k}^{e_k}-p_{k}^{e_k-1})\\
            &= (p_{1})^{e_1-1}(p_1-1)\cdots p_{k}^{e_k-1}(p_k-1)\\
            &=\frac{n}{p_1\cdots p_k}(p_1-1)\cdots (p_k-1)\\
            &= n\left(\frac{p_1-1}{p_1}\right)\cdots\left(\frac{p_k-1}{p_k}\right)\\
            &= n\prod_{i=1}^{k}\left(1-\frac{1}{p_i}\right)
\end{align*}$$

# Problem 3

prove that $f(x)=ax$ is bijection. 

$$am_i=am_j \mod n\implies a^{-1}am_i=a^{-1}am_j\iff m_i=m_j$$
This proves injection.

Proof of surjection:

$$\forall m_j\in S, \text{ choose }m_i=a^{-1}m_j$$

we have $am_i=m_j$
This proves bijection. 

# Problem 5

$d$ is the inverse of $e=9$ mod $(p-1)(q-1)=40$
That is $$9d\equiv1 \mod 40$$, solve this we get $d\equiv 9 \mod 40$

(b)
Let $x$ be the original message, $x^e=x^9=4 \mod 55$
The $x = 4^d=4^9= 14\mod 55$

# Problem 6
(a)
$$D(E(x))=(x^e)^d=x^{ed}=^?x \mod p$$
where $d$ is inverse of $e$ in $\mod N=p-1$.
 
So $ed = 1 + K(p-1)$.

So $x^{ed} = x^{1 + K(p-1)}=xx^{K(p-1)}=^{\text{Fermat}}x\mod p$. Therefore $D(E(x))=x$

(b)
Since $e$ is given, we need only to enumerate in the set $\{1,2,\cdots, p-1\}$ to get $d$ in linear time complexity.