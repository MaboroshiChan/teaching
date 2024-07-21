# Practice final 

## **Problem 1: Set theory**
Prove that (A × B) ∪ (C × D) ⊆ (A ∪ C) × (B ∪ D)

Proof: Let $a\in A, b\in B, c\in C, d\in D$

Let $(x, y)\in (A\times B)\cup (C\times D)$
then, $x\in A\cup C, y\in B\cup D$. Therefore

$$(x,y)\in (A\cup C)\times (B\cup D)$$

## **Problem 2: Arithmetic, pigenhole principle, injectivity of finite set**

Suppose that there are six positive integers $x_1,\cdots,x_6$ . Prove that at least two of them will have
the same remainder when divided by $5$.

Let $A = \{x_1,\cdots,x_6\}$, $B = \mathbb{Z}/5\mathbb{Z}$

Let $f: A\to B$, $x\mapsto x \mod 5$

then $f$ must not be injective.
otherwise $6=|A|\le |B|=5$ contradicted. Thus there are two $x_i\neq x_j\in A$ such that $f(x_i)=f(x_j)$ which means $x_i\equiv x_j \mod 5$.

## **Problem 4: injective bijective**
$e^{x}=e^{y}\implies x=y$.
injectivity:
$$e^{x-y}=1\implies x-y=0,x=y$$
Surjectivity:
$\forall y \in (0, \infty), e^{x=\ln(y)}=y$

## **Problem 6**
方法：将set theory问题转化成propositional logic 问题

Let $x\in (A_1\cup A_2)^c$, then $x\not\in A_1\cup A_2$, and $x\not\in A_1 \land x\not\in A_2$

(2)
Induction Hypothesis
$$\left(\bigcup_{i=1}^{n-1}A_i\right)^c = \bigcap^{n-1}_{i=1}A_i$$.

Then 

$$\left(\bigcup_{i=1}^{n}A_i\right)^c = \left(\bigcup_{i=1}^{n-1}A_i\cup A_n\right)^c$$
Let $$X = \bigcup_{i=1}^{n-1}A_i$$
Then
$$(X\cup A_n)^c = X^c \cap A_n^c$$

# Euclidean division

gcd(24, 18) = gcd(18, 6) = gcd(6, 0) = 6

if a > b
gcd(a, b) = gcd(b, a mod b)