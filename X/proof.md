# **Surjectivity**

Surjectivity means $\forall y\exists x f(x)=y$
Then showing $g\circ f$ is surjective is equivalent to show
$$\forall y\exists x g(f(x))=y$$
By surjectivity of $g$, $$\forall y\exists t,g(t)=y$$, by surjectivity of $f$, 

$$\forall t\exists x f(x)=t$$,

Therefore, we have all we need to show $$\forall y\exists x g(f(x))=y$$.

Let $y\in Y.$, there exists $t, g(t)=y$. Also there exists $x, f(x)=t$. Thus.
$$g(f(x))=y$$.
Done.

# **Problem 5**

($\implies$)
Let $x\in \text{divisors}(a)\cap\text{divisors}(b)$. Then
$$x\in \text{divisors}(a)\land x\in \text{divisors}(b)$$.

$x| a\land x|b$. We now only need to show $x|a-b$ which is a basic property of division.

Thus $x|b\land x|(a-b)\implies x\in \text{divisors}(b)\land x\in \text{divisors(a-b)}$
Hence $$\text{divisors}(a)\cap\text{divisors}(b)\subseteq \text{divisors}(b)\cap\text{divisors}(a-b)$$.

(Another direction)

Let $x\in \text{divisors}(b)\cap\text{divisors}(a-b)$. Then
$$x\in \text{divisors}(b)\land x\in \text{divisors}(a-b)$$.

$x| b\land x|(a-b)$. We now only need to show $x|(a-b+b)=a$ which is a basic property of division.

Thus $x|b\land x|a\implies x\in \text{divisors}(b)\land x\in \text{divisors(a)}$
Hence $$\text{divisors}(a)\cap\text{divisors}(a-b)\subseteq \text{divisors}(b)\cap\text{divisors}(a)$$.

# Problem 6
Proof of Reflexivity(xRx): $n | (x - x)$ $\iff n | 0$.

Proof of Symmetricity(xRy$\implies$ yRx): That means $n | (x-y)\implies n | (y-x)$. Since $n | 0$, then $n | 0 - (x - y)=y-x$.

Proof of Transitivity(xRy, yRz$\implies$ xRz): That means
$$n | (x-y), n|(y-z)\implies n|(x-z)$$.
We note that $$n | (x-y) + (y-z)=x-z$$

(d)

$$\mathbb{N}=A\cup B\cup C$$
where $A = \{x|x\equiv 0\mod 3 \},B = \{x|x\equiv 1\mod 3 \},C = \{x|x\equiv 2\mod 3 \}$
