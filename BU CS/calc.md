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

$$