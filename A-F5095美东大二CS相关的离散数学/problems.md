1 induction ✅
    5.[4]
1 hoare logic ✅

    Show that the following Hoare triple is valid:

    {true}
        if x <= y then min := x;
        else min := y;
    {(x ≤ y∧min = x)∨(x > y∧min = y)}

    P := true
    S := if x < y then min := x;
            else min := y;
    Need to show R := x <= y and min = x or (x > y and min = y)

    case ((x < y) ^ P) => min := x; => min = x
    Therefore we have (x < y) ^ min = x => (x ≤ y ∧ min = x)∨(x > y ∧ min = y) by (some law with regard to v).

    case (x >= y) ^ P => min := y; min = y
    Therefore we have (x >= y) ^ min =y => (x ≤ y ∧ min = x)∨(x > y ∧ min = y) by (some law with regard to v).

    QED.


1 propositional logic ✅
    by student

1 set theorem 
    $$S - (T \cup U) = (S - T) \cap (S- U)$$
    Assume $S - (T \cup U)$.

Let $x \in S - (T \cup U)$. That means
$x\in S$ and $x\not \in T\cup U$
we have $x \not\in T\cup U \implies x\in -T,x\in -U$
we have $x\in S$, and $x\in -T\implies x\in S-T$.
we have $x\in S$, and $x\in -U\implies x\in S-U$.
we have $x \in (S-T) \cap (S-U)$.

Assume $x \in (S - T) \cap (S- U)$.
we have $x \in S-T\implies x\in S$ and $x \in -T$ and $x \in S-U$ and $x \in -U$.

we have $x\in S$, and $x \in -T\cap -U = -(T\cup U)$
    
5 translation
    page 188
    9.33

$$\exist q(\forall p. p \text{ loves } q)$$
$F(x,y,z):=$ x fools y in time z

1 binary relation
1 boolean algebra

$A\in P(S) \implies A\subseteq S$

$A \cup \{\} = A$

$A \cap S = A$

procedure multiply(m.n : integers,return product : integers)
    {m, n : integer}
    if n < 0 
        then 
            {n < 0}
            a := −n 
            {a = -n ^ n < 0}
        else
            {n >= 0} 
            a := n;
            {n >= 0 ^ a = n}
    {if n < 0 a = -n else a = n}
    k := 0; x := 0;
    {k = 0 and x = 0 and a >= 0}
    while k < a do {"loop invariant": x = m*k}
        x := x+m; k := k+1;
    {k = a ^ x = m * k}
    {x = m * a}
    if n < 0 
    then 
        {n < 0 ^ x = m * a}
        product := −x
        {product = m * (-a) = m * (--n) = m*n}
    else {n >= 0}
         product := x;
         {product = m * a = m * n}
    {goal :  prove that product = m * n }
    end of procedure