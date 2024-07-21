# Problem 1

# Problem 2
$S = \{10,11,\cdots,99\}, A_2 = \{10,12,14,\cdots, 98\}, A_3 = \{12,15,\cdots,99\}$

$$|S| = 90, |A_2| = 45, |A_3| = 30$$
$$|S-(A_2\cup A_3)| = 90-45-30+15=30$$

# Problem 5

# Problem 6

$$S = \{\text{all permutation}\}$$
$$A_i = \{i\text{ is in its natural position}\}$$
$$\begin{align*}
    |A_i| &= 8! \\
    \left|\bigcap_{j\in \{j_1,\cdots,j_k\}} A_{j}\right| &= (9-k)!\\
    |S - (A_1\cup A_3\cdots\cup A_9)| &= 9! + \sum_{k=1}^{9}(-1)^k\binom{9}{k}(9-k)!
\end{align*}$$
Thus the final answer is 
$$9! - \left(9! + \sum_{k=1}^{5}(-1)^{k}\binom{9}{k}(9-k)!\right)$$

# Problem 7

$$\begin{align*}
    w_1 &< w_2 > w_3 < w_4 > w_5 < w_6 > w_7\\
    S &= \{(w_1,\cdots,w_7): w_1 < w_2, w_3 < w_4, w_5 < w_6\}\\
    A_1 &= \{(w_1,w_2,\cdots, w_7): w_1<w_2 < w_3\}\\
    A_2 &= \{(w_1,w_2,\cdots, w_7): w_4 < w_5\}\\
    A_3 &= \{(w_1,w_2,\cdots, w_7): w_6 < w_7\}\\
    |S| &= \binom{7}{2}\binom{5}{2}\binom{3}{2}\\
    |A_i\cap A_j| &= \binom{7}{2}\binom{5}{2}\\
    |A_1\cap A_2\cap A_3|&=\binom{7}{2}\binom{5}{2}\binom{3}{2}\\
    \left|S-(A_1\cup A_2\cup A_3)\right| &= |S| - |A_1| - |A_2| - |A_3| + |A_1\cap A_2| + |A_2 \cap A_3|\\
    &+|A_3 \cap A_1| - |A_1\cap A_2\cap A_3|\\
    &= \binom{7}{2}\binom{5}{2}\binom{3}{2} - 3\binom{7}{2}+3\binom{7}{2}\binom{5}{2}-\binom{7}{2}\binom{5}{2}\binom{3}{2}
\end{align*}$$