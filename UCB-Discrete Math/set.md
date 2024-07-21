# Definition

## Definition of Set
Set is a container for a type of elements.
The order doesn't matter in a set. i.e
$$\{1,2,3\} = \{3,2,1\}$$

## Definition of Tuple
Tuple is a container for a type of elements.
The order doesn matter in a set. i.e
$$(1,2,3) \neq (3,2,1)$$.

2-tuple can be regarded as Direct product of two sets.

For example, if the elements in $C$ looks like this $(a_1, b_1), (a_1, b_1)...$, then

$C = A\times B=\{(a,b):a\in A, b\in B\}$

$A\times B \simeq B\times A$

Is there a bijection from $A\times B$ to $B\times A$?

Yes, it is $(a, b)\mapsto (b,a)$

## Definition of function


### Definition of surjection, injection and bijection
**$A$ is input and $B$ is output**

$f:A\to B$ is surjective $\iff$ $\forall b\in B$, there is $a\in A$ such that $f(a)=b$.
What does the graph look like?

$f:A\to B$ is injective $\iff$ $\forall x, y\in A, f(x)=f(y)\implies x = y$ See the graph

Bijective = injective + surjective.
Example: $f:\mathbb{R}\to \mathbb{R}$, $f(x)=x$ $f(x) = x^3$

### Defintion of arithmetic

Arithmetic

Distribution: $a(x+y)=ax+ay$

Commutatation : $x + y = y + x$, $xy=yx$

Association: $x+(y+z)=(x+y)+z$, $x(yz)=(xy)z$

Inversion $x^{-1}x=1$, $x+(-x)=0$.

## Counting
For a finite set $A$, the cardinality of $A$ is defined to be
$$|\cdot|:A\to \mathbb{N}$$

Example let $A = \{1,2,3,4\}$
$$|A| = 4$$

Couting principle 1:

If $C = X\times Y$ then

$$|C| = |X||Y| $$

Counting principle 2:

If $C = X\cup Y, X\cap Y=\empty$, then

$$|C| = |X| + |Y|$$

Counting principle 3:

If $C = U-A, A\subseteq U$, then
$$|C| = |U|-|A|$$

# Fix point

Assume such program exists,
Consider

Q(x):= if H(x, P) then x else 0

# Problem 7 

$$n=\sum^{m}_{k=0} d_{k}10^{k},\  n=\sum^{m}_{k=0} d_{k}10^{k}=\sum^{m}_{k=0} d_{k}\  \text{mod} \  9$$

Define 

$$ D(n)=\sum_{k=0}^m d_k $$

Thus $$n = D(n)\equiv \sum_{k=0}^m d_k \mod 9$$

then there is $N$ such that

$$DR(n)= D^{N}(n) = D(D(\cdots D(n)))$$.

Thus $$DR(n)= D^{N}(n)=D^{N-1}(n)=\cdots=D(n)=n\mod 9$$