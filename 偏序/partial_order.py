
#操作系统: MacOS 12.6
#python版本: 3.9.10
#思路: 枚举所有5个节点的有向图（用邻接矩阵表示），对每个图做dfs，筛选出无环图。结果用邻接矩阵表示
#分别测试了 size = 2,3,5的情况
from ast import Str
from numba import njit

size = 5 #元素个数

"""
dfs函数，从某一点开始搜索
@param graph: 邻接图
@param visited: 已访问的边的列表
@param i: 节点下标
@return 布尔型，判断是否有环
"""
@njit
def dfs(graph: list[list[int]], visited: list[int], i: int) -> bool:
    if visited[i]:
        return False
    visited[i] = True
    res = True
    for j in range(size):
        if graph[i][j] == 1: 
            res &= dfs(graph, visited, j)
    return res

"""
对每一个联通分支做dfs
@param graph: 邻接图
@param visited: 已访问的边的列表
@return 布尔型，判断是否有环
"""
@njit
def list_all_par(graph: list[list[int]]) -> bool:
    for i in range(size):
        if not dfs(graph, [False for _ in range(size)], i):
            return False
    return True

"""
主函数
"""
@njit
def main():
    graph = [[i for i in range(size)] for _ in range(size)]
    for num in range(2 ** (size * size)):
        for x in range(size * size):
            graph[x // size][x % size] = (num >> x) % 2
        #print("num = ", num,graph, (num >> x) % 2)
        if(list_all_par(graph)):
            print("Partial order ", graph)

def func(N: int):
    s = 0
    for i in range(N):
        s += i
    return s

def gcd(a, b):
    assert(a > b)
    print("({},{})".format(a, b))
    while b > 0:
        (a, b) = (b, a % b)
        print("({},{})".format(a, b))
    return a

print(gcd(7654321, 1234567))