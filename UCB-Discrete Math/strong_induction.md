# Strong mathematical induction

Assume we have a proposition about natural number $P(n)$.

## The standard mathematical induction:

**Base case**: proof P(0).

**Inductive step**: assume $P(n)$, need to show $P(n+1)$.

## Strong version

**Base case**: proof P(0).

**Inductive step**: assume $P(0)\cdots P(n)$, need to show $P(n+1)$.

写的话可以写：Assume the proposition holds for all $0\le k\le n$, need to show $P(n+1)$.

### *We establish a connection from strong to weak induction*

什么是induction，induction就是把大问题，拆解成小问题，把大结构拆解成小结构。
比如，自然数n是个大结构，它可以拆解成$(n-1) + 1$.

$\{0,\cdots, n\}$也是一个大结构，它可以拆解成$\{0,\cdots,n-1\}\cup \{n\}$. $n$到$\{0,\cdots,n\}$是一一对应的关系，所以strong induction可以和weak induction互换。

## sub-strong induction

**Base case**: proof $P(0), P(1)$.

**Inductive step**: assume $P(n-1), P(n)$, need to show $P(n+1)$.

大结构：$(n, n-1)=(n-1,n-2) + (1,1)$

假设我们要证明一个binary tree的性质$P(B)$ where B is a binary tree. 

大结构: $B=\text{Node Left Right}$, $\text{Leaf} = \text{Node Null Null}$

Base case: $P(\text{Leaf})$

Inductive step:: $P(\text{Left}) \land P(\text{Right})$ need to show $P(\text{root})$.

# Injective
proof that $f:R\to R,f(x) = x^3$ is injective.

Recall the definition of injectivity: If $f:A\to B$
$$\forall x, y\in A, f(x)=f(y)\implies x=y$$.

Let $x,y\in R$, $x^3=y^3$ goal: x=y.
$$x^3=y^3\implies x^3-y^3=0\implies (x-y)(x^2+xy+y^2)=0\implies (x-y)=0\lor x^2+xy+y^2=0$$.
If $x-y=0$, then $x=y$, we are done.
otherwise $$0=x^2+xy+y^2=\left(x + \frac{y}{2}\right)^2 + \frac{3y^2}{4}\implies x=-y/2,y=0\implies x=0,y=0$$ contradiction.

# Surjective
If $f:A\to B$, $\forall b\in B\exist a\in A, f(a)=b$
Let $b\in R$, we choose $x=\sqrt[3]{b}$, then$\exist x\in A, f(x)=b$, thus $f$ is surjective.

# $x^2:R\to R$ is not surjective.
Student version: Let $b$ in R, we choose $x=\sqrt{b}$ yet, when $x=-1$, $b$ does not exists in $R$.

My version: Choose $b=-1$, then $\forall x\in R$, $x^2\ge 0 > -1 = b$, the equation $x^2=b$ does not hold. Thus $x^2$ is not surjective.

# Permutation
Permutation over a set $S$ is a bijection
$$\sigma:S\to S$$.

Example find all permutation of $S=\{1,2,3\}$.
$$\sigma_1 = (1,2,3)\mapsto (1,2,3)$$
$$\sigma_2 = (1,2,3)\mapsto (2,1,3)$$
$$\sigma_2 = (1,2,3)\mapsto (3,2,1)$$
$$\sigma_2 = (1,2,3)\mapsto (1,3,2)$$
$$\sigma_2 = (1,2,3)\mapsto (2,3,1)$$
$$\sigma_2 = (1,2,3)\mapsto (3,1,2)$$