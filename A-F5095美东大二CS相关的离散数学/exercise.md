P := 0 <= k <= n; b = F_{k-1}, c = F_{k}
(a) before execution k = 0, b = 1, c = 0
we have 0 <=0=k<=n=0
        F_{0-1} = F_{-1} = 1 = b
        F_{0} = 0 = c

which satisfy (a)

(b) we have B = k != n
    under {P ^ B}
    we have (to prove P after S):
        S := (k, b, c := k + 1, c, b +c)

        k < n
        since k' := k + 1
        we have k' <= n     (1)
        since b' := c
        we have b' = c = F_{k} = F_{k' - 1}
        since c' := b + c
        we have c' = F_{k - 1} + F_{k} = F_{k + 1} = F_{k'}

        Thus P still holds for 0 <= k' <= n ^ b' = F_{k' - 1} ^ c = F_{k'}
        QED
    
(C) Since in each execution of loop, k increases by one every time, it
will finally touch n. Thus the loop will eventually terminate after n interations.

(D) -B implies that k = n, and P implies that c = F_{k} = F_{n} which shows R. 


$P(\{1,2,3\}) = \{\{\},\{1\}, \{2\}, \{3\}, \{1,2\},\{2,3\}, \{1,3\}, \{1,2,3\}\}$

$\{\{\}, \{1\}, \{2\}, \{1,2\}\}$

$\{\{3\}, \{1,3\}, \{2,3\}, \{1,2,3\}\}$

Proof:
Base case $\#S = 0$. In this case $S = \{\}$, $P(S) = \{\{\}\}$.

(Induction hypothesis) Assume that $\forall S,\#S = n \implies \#P(S) = 2^{n}$.
We need to show $\forall S, \#S = n + 1 \implies \#P(S) = 2^{n + 1}$

Let $S$ be a set with $\#S = n + 1$. (remove quantifier and premise($\#S = n + 1$)).

Let $S = S'\cup \{a\}$. We have $\#S' = n$.

$P(S')\subset P(S)$

$P(S) = P(S'\cup \{a\}) = P(S') \cup (U:=\{\{a\}\cup s|s\in S'\})$

Note that $P(S')\cap U = \{\}$.

$\#P(S) = \#(P(S')\cup U) = \#P(S') + \#U = 2^n + 2^n = 2\cdot 2^{n} = 2^{n+1}$ (induction hypothesis)

Prove: 
$$\sum_{k=0}^{n}r^k=r^{0}+r^{1}+r^2+\cdots r^n = (r^{n+1}-1)/(r-1)$$

set $n = 0$ we have $1=r^0 = (r^{1} - 1)/(r-1)=(r-1)/(r-1)=1$.

Assume that $$\sum_{k=0}^{n}r^k = (r^{n+1} - 1)/(r-1)$$.

Let $$S_{n} = \sum_{k=0}^{n}r^k$$

找与$S_{n+1}$的关系。
$$\begin{align*} S_{n+1} &= r^{n+1} + S_{n} = r^{n+1} + (r^{n+1}-1)/(r-1)\\ &= \frac{r^{n+1}(r-1) + r^{n+1} - 1}{r-1} \\
&= \frac{r^{n+2} - r^{n+1} + r^{n+1} - 1}{r-1} \\ &=\frac{r^{n+2}-1}{r-1} \end{align*}$$

Also.$$rS_{n} + 1 = S_{n+1}$$

Proof $S \cup (T- S) = S \cup T$.

$\implies$:

Let $x\in S \cup (T-S)$, then $x\in S$ or $x\in T-S$

then $x\in S$ or ($x\in T$, and $x\not \in S$).

case $x\in S$: $x\in S\cup T$.

case $x\in T$, and $x\not \in S$: $x\in T\implies x\in S\cup T$

$S \cup (T-S) \subseteq S\cup T$:

$\Leftarrow$:

$S \cup (T-S) \supseteq S\cup T$

Let $x\in S\cup T$.
then $x \in S$ or $x \in T$.

case $x\in S$. Then $x\in S\cup (T - S)$.

case $x\not \in S$: $x\in T\implies x\in T-S\implies x\in S\cup (T-S)$