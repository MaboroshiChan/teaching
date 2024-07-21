# Set theory

## Problem 1 

(a)
$A$ is empty set $\iff$ $\forall x. x\not \in A$, $\lnot\exists x. x\in A$

(b) $A$ and $B$ have some common element $\iff \exists x. x\in A \land x\in B$ $\lnot\forall x. x\not\in A \lor x\not\in B$.

## Problem 2
a) $\forall x\lnot (M(x))$

Negation: $\lnot\forall x\lnot (M(x))$

Applying DeMorgan's law: $\exists xM(x)$

English: There is a passenger take the medication.

## Problem 3

a)

$P\subseteq Q$

$x\in P \to x\in Q$

$x\not\in Q\to x\not\in P$

$x\in \overline{Q}\to x\in \overline{P}$

$\overline{Q}\subseteq \overline{P}$

## Problem 4

b)

$$\begin{align*}C\cup (A-B) &= C\cup (A\cap \overline{B} )\\
              &=(C\cup A)\cap \left( C\cup \overline{B} \right)\\
              &=(C\cup B)\cap \left( C\cup \overline{B} \right) \\
              &=C\cup \left( B\cap \overline{B} \right)=C\end{align*}$$

Thus $A-B\subseteq C$

$$C\cap(A-B)=C\cap A\cap \overline{B}=(C\cap A)\cap\overline{B}=(C\cap B)\cap\overline{B}=C\cap(B\cap\overline{B})=\empty$$

We have either $A-B\not\subseteq C$ or $A-B=\empty$.
Since we cannot have contradiction, we must have $A-B=\empty$.
Thus $$A=B$$

c)

$$\begin{align*}
    (B-A)\cup(C-A)&= (B\cap\overline{A})\cup(C\cap\overline{A})\\
                  &= \overline{A}\cap(B\cup C)\\
                  &= (B\cup C)-A
\end{align*}$$

## Problem 5
b)

$$A\times (B\cap C) = (A\times B)\cap (A\times C)$$

Proof:

$(x, y)\in A\times (B\cap C)\iff (x\in A \land y\in (B\cap C))\iff (x\in A\land y\in B\land y\in C)\iff (x\in A\land x\in A\land y\in B\land y\in C)\iff (x\in A\land y\in B)\land(x\in A\land y \in C)\iff (x,y)\in A\times B\land (x,y)\in A\times C\iff (x,y)\in (A\times B)\cap (A\times C)\iff A\times (B\cap C) = (A\times B)\cap (A\times C)$

$$\begin{align*}
    & A = A\cap (C\cup \overline{C}) \\
    &= (A\cap C)\cup (A\cap\overline{C}) \\
    &= (A\cap C)\cup (A - C)\\
    &= (B\cap C) \cup (A-C)\\
    &= (B\cap C)\cup(A\cap\overline{C})\\
    &= (A\cup (B\cap C))\cap(\overline{C}\cup(B\cap C))\\
    &= ((A\cup B)\cap(A\cup C))\cap ((\overline{C}\cup B)\cap(\overline{C}\cup C))\\
    &= ((A\cup B)\cap(B\cup C))\cap (\overline{C}\cup B)\\
    &=(B\cup (A\cap C))\cap (\overline{C}\cup B)\\
    &= (B\cup (B\cap C))\cap (\overline{C}\cup B)\\
    &= B\cap(B\cup \overline{C}) \\
    &=B
\end{align*}$$