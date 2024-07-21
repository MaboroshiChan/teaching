$\lim_{x\to c} f(x)$ exists if and only if $$\lim_{x\to c^{-}}f(x) = \lim_{x\to c^{+}}f(x)$$.


# Definition of derivative

Given a function $f$, if $f$ has derivative at the point $x = c$, then what does this mean?

$$f'(c) :=\lim_{h\to 0}\frac{f(c+h)-f(h)}{h}$$

$$\frac{x-3}{x^2+7x-30} = \frac{x-3}{(x-3)(x+10)} = \frac{1}{x+10}$$

$$\lim_{x\to 3}\frac{1}{x+10} = \frac{1}{13}$$

$$\frac{-(\sqrt{x} + 5)(\sqrt{x}-5)}{(\sqrt{x}-5)}$$

$$\lim_{h\to 0}\frac{(-5+h)^2-25}{h} = \frac{f(-5+h)-f(-5)}{h} =f'(-5) = -5*2 = -10, f(x)=x^2$$

$$\left(\frac{4}{x}\right)'=-\frac{4}{x^2}|_{x=7}=-\frac{4}{49}$$

$$(f(x)+g(x))'=f'(x)+g'(x)$$

$$\begin{align*}
(f(x)g(x))'&=\lim_{h\to 0}\frac{f(x+h)g(x+h)-f(x)g(x)}{h} \\
           &= \lim_{h\to 0}\frac{f(x+h)g(x+h)-f(x+h)g(x)+f(x+h)g(x)-f(x)g(x)}{h} \\ 
           &= \lim_{h\to 0}\left(f(x+h)\frac{g(x+h)-g(x)}{h}+g(x)\frac{f(x+h)-f(x)}{h}\right)\\
           &= \lim_{h\to 0}f(x+h)\frac{g(x+h)-g(x)}{h} + \lim_{h\to 0}g(x)\frac{f(x+h)-f(x)}{h} \\ 
           &= f(x)g'(x) + g(x)f'(x)
\end{align*}$$

# Derivative function

## Process

Given $f(x)$, find its derivative or second derivative...
### Basic terms
$\sin(x), \cos(x), \tan(x), e^{x}, x^{a}, \ln(x), \sin^{-1}(x), \cos^{-1}(x), \tan^{-1}(x), \cosh(x)\cdots.$

### Composition

$\sin(x)e^{x}, x^2 + x + 1,\cdots$

$f(x) = g(x)h(x), f(x) = g(x) + h(x), f(x) = \frac{g(x)}{h(x)}, f(x) = g(h(x)), f(x) = g^{-1}(x)$

$$\begin{align*}
f'(x) &= g'(x)h(x) + g(x)h'(x) \\ 
f'(x) &= g'(x) + h'(x)\\
\cdots
\end{align*}$$

### Example

$$f(x) = \sin(x^2 + 1)$$

1.

$$\begin{align*}f'(x) = (g(h(x)))'&= h'(x)g'(h(x)) \\
                                  &= 2x\cos(x^2+1)
\end{align*}$$

2. 
$$\begin{align*}
    f(x) &= 4x^3 + \sqrt[3]{x^2} + \frac{2}{x^2} + \sec(x)\\
    f'(x) &= (4x^3)' + (\sqrt[3]{x^2})' + \left(\frac{2}{x^2}\right)' + \sec'(x) \\
          &= 12x^2 + \frac{2}{3}x^{-1/3} + \frac{-4}{x^3} + \tan(x)\sec(x)
\end{align*}$$

$$\begin{align*}
    f(x) &= \sqrt{x-3}\\
    f'(x) &= h'(x)g'(h(x)) = (x-3)'\frac{1}{2}\frac{1}{\sqrt{x-3}} \\
          &= \frac{1}{2\sqrt{x-3}}
\end{align*}$$

$$\begin{align*}
    f'(x) &= \lim_{h\to 0}\frac{f(x + h) - f(x)}{h}\\
          &= \lim_{h\to 0}\frac{\sqrt{x+h-3}-\sqrt{x-3}}{h}\\
          &= \lim_{h\to 0}\frac{h/(\sqrt{x+h-3}+\sqrt{x-3})}{h} \\
          &= \lim_{h\to 0}\frac{1}{\sqrt{x+h-3} + \sqrt{x-3}}\\
          &= \frac{1}{2\sqrt{x-3}}
\end{align*}$$

**Useful fact**:

$$\sqrt{x} - \sqrt{y} = \frac{x-y}{\sqrt{x} + \sqrt{y}}$$

**Definition of f**

$$dy(x) = y(x + h) - y(x)|_{h\to 0}$$
$$dx = x+h - x = h|_{h\to 0}$$

$\frac{d}{dx}$ is an operator, so that for a function $f$, $\frac{d}{dx}f = f'$
