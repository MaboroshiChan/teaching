# Problem 1

$$\begin{align*}P(\text{the other side is black} | \text{this side is red}) &= P(\text{card = 1})P(\text{the other side is black | card = 1})+P(\text{card = 2})P(\text{the other side is black | card = 2})\\
&= \frac{1}{2}\times 0 + \frac{1}{2} \times 1 = \frac{1}{2}\end{align*}$$

# Problem 2

$$\begin{align*}
P(X^2 -10X + 16\le 0) &= P((X-2)(X-8)\le 0)\\
                      &= P(2 \le X \le 8)\\
                      &= \sum_{k=2}^{8}\binom{10}{k}p^k(1-p)^{10-k}
\end{align*}$$

# Problem 3

$$\begin{align*}
    & E(X^v) = \int_{-\infty}^{+\infty}x^vf(x;\alpha,\beta)\\
    &= \frac{1}{B(\alpha,\beta)}\int_{-\infty}^{+\infty}x^vx^{\alpha - 1}(1-x)^{\beta - 1}\\
    &=\frac{1}{B(\alpha, \beta)}\int_{-\infty}^{+\infty}x^{\alpha+v - 1}(1-x)^{\beta - 1} \\
    &=\frac{\Gamma(\alpha + \beta)}{\Gamma(\alpha)\Gamma(\beta)}\frac{\Gamma(\alpha + v)\Gamma(\beta)}{\Gamma(\alpha + \beta + v)}\\
    &=\frac{\Gamma(\alpha + \beta)\Gamma(v + \alpha)}{\Gamma(\alpha)\Gamma(\alpha + \beta + v)}
\end{align*}$$

# Problem 6

$$\begin{align*}
    & \mathrm{Cov}(X, Y) = E(XY) - E(X)E(Y)\\
    &= E(XY)-E(X)E(E(Y|X)) \\
    &= E(E(XY|X))-E(X)E(E(Y|X)) \\
    &= E(XE(Y|X))-E(X)E(E(Y|X)) \\
    &= \mathrm{Cov}(X, E(Y|X))
\end{align*}$$

# Problem 4

$$X = WT$$

$$P(X \le x) = P(T\le x|W=1)P(W=1) + P(T\ge -x|W=-1)P(W=-1) = \frac{2}{3}\exp(-\beta x)+\frac{1}{3}(1-\exp(-\beta x))=\frac{1}{3}\exp(-\beta x)+\frac{1}{3}$$

$$f_X(x)=-\beta/3\exp(-\beta x)$$

# Problem 7

$$\begin{align*}P(n(1-T_n)\le x) = P\left(T_n\ge \frac{n-x}{n} \right)&=1-P\left(T_n \le \frac{n-x}{n}\right)\\
&= 1-\prod_{k=1}^{n}P\left(U_k\le \frac{n-x}{n}\right)\\
&= 1-\end{align*}$$

# Problem exam 4

$$$$