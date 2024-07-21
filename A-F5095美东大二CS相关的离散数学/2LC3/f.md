R1T
R2T
R1L
R2L

S1 := R1L ^ R2T
S2 := (R1L v R2L) ^ (R1T v R2T)

_=_ : P * P -> P

p: P
q: P

p = q : P

P B

(p v q => p) formula
(p v -p) axiom

p => q

(assume p
....
q ) inference rule

x = y
P(x) = P(y).

p <=> q := (p => q) ^ (q => p)

PQ

Integer Multiplication
where a and b are int.

Symbol: (0, 1, 2, 3...,-1,-2,-3...), *, =

Formulas: a * b = c

Axiom: 0 *0 = 0, 1* 1 = 1

inference rule:
    a *b = c
    ----------
b* a = c

    a * (b + 1) = c
    ---------------
    a * b + a = c

    (-a) * (b) = c
    --------------
    -(a * b) = c

2 *3 = 2* (2 + 1) = 2 *2 + 2 = 2* (1 + 1) + 2 = 2 *1 + 2 + 2 = 2* 1 + 4 = 1 *2 + 4 = 1* (1 + 1) + 4 = 1 * 1 + 1 + 4
= 1 + 1 + 4 = 6

2 *3 = 1 != 6 so 2* 3 = 1 is not a theorem.

****P****Q****

aPbQc <==> a + b = c

inference rule:
    (a + d) + (b + e) = (c + f)
    for all a, b, c in N
    aPbQc <==> a + b = c

$$\varphi[x] := \forall x\in \mathbb{N}, x(x+1) \text{ is even}$$.

$\varphi[0]:=0(0+1)$ is even.

$\varphi[x] = x(x+1)$ is even.

How to prove $\varphi[x+1] = (x+1)(x+2)$ is even?

$x(x+1) + 2(x+1) = (x+2)(x+1)$

$\forall x. \varphi[x]$

```Haskell
class Addiable a where
    (+) :: a -> a -> a

instance Addiable Int where
    a + b = ...

print :: (Addiable a)=>a -> String
print a = ...
```

```C++
double add(double a, double b);
int add(int a, int b);

template<typename T>
T add(T a, T b);
```

```haskell
[(i : Int) * 8 | i <- [0..4]]
```

$\forall i\in \mathbb{Z}, x \times i = 0$

$\exists i, x \times i = 0$

$q(\_, \_) := \_和_是朋友$.
$q(小红，小明) = 小红和小明是朋友$.

p v p = p

p v q = -(-p ^ -q)


x0, $\forall y. P$

y1, x1
y2, x2 

```Haskell
(+) :: Int -> I
```