# Regular Curve
# Reparametrization
# Curvature
# Cross product

$$\mathbf{b(s)}=\mathbf{t}(s)\times \mathbf{n}(s)$$

There are two parametrization of a circle:
$$\gamma(t) = (t, \sqrt{1-t^2}), 0\le t\le 1$$
$$\bar{\gamma}(t) = (\cos t, \sin t), 0\le t\le \pi$$.

We can show there is a $\phi$ such that $\bar{\gamma}(\phi(t))=\gamma(t)$. What do you think $\phi(t)$ should look like? $\phi(t)=\cos^{-1}(t)$. Then we check that

$$\bar{\gamma}(\cos^{-1}(t))=(t, \sin(\cos^{-1}(t)))=(t, \sqrt{1-(\cos{\cos^{-1}(t)})^2})=(t, \sqrt{1-t^2})$$