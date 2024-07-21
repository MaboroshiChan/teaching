# Poset

Given a set $S$, and its Power set $P(S)$, we define an order on $P(S)$:

Let $A, B\in P(S)$, $A\le B\iff A\subseteq B$.
This definition of $\le$ satisfies trans, which means if $A\le B, B\le C$, then $A\le C$.

But not all two elements in $P(S)$ have this relation. For example, let $S=\{1,2,3\}$,
$A=\{1,2\}, B=\{2,3\}$, then $A\not\subseteq B$ nor $B\not\subseteq A$.

We then call a set together with a $\le$ relation with not every two elements being capable of comparison a partially ordered set.

Formally $\lnot\forall a, b\in S, a\le b \lor b\le a$ then it is partially ordered.

```
A relation "<=" is a partial order on a set S if it has:

1. Reflexivity: a<=a for all a in S.

2. Antisymmetry: a<=b and b<=a implies a=b.

3. Transitivity: a<=b and b<=c implies a<=c.
```

Other than $(P(S), \subseteq)$, $(\mathbb{N}, |)$ is also a poset.

Note: $a | b$ means $a$ divides $b$.

**reflexivity: $a|a$***

**antisymmetry: if a|b, b|a, then a=b**

**Transitivity: if a|b, b|c, then a|c**

## Definition of upper bound
****
$$U\forall x\in S, x\le U$$

## Definition of lower bound
****
$$U\forall x\in S, U\le x$$

# Problem 7

(d)
From previous sections, we concluded that 
$$\frac{n^p-n}{p}$$
is the number of wristbands with at least two colors. That is actually an integers with the implication that $p$ divides $n^p-n$, which is equivalent to say $n^p\equiv n\mod p\iff n^{p-1}\equiv 1 \mod p$