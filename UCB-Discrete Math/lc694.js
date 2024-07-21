

var numIslands = function(grid) {
    const m = grid.length;  // 行数
    if (m === 0) return 0;  // 如果为空，返回 0
    const n = grid[0].length;  // 列数
    let count = 0;  // 岛屿计数器

    // 记录已访问的位置
    const visited = Array.from(Array(m), () => new Array(n).fill(false));

    // 定义 DFS 函数
    const dfs = (i, j) => {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || grid[i][j] === 0) {
            // 当前位置已经访问过或者是水，直接返回
            return;
        }
        visited[i][j] = true;  // 标记当前位置已访问
        dfs(i - 1, j);  // 上
        dfs(i + 1, j);  // 下
        dfs(i, j - 1);  // 左
        dfs(i, j + 1);  // 右
    }

    // 遍历整个网格图像，找到所有的岛屿
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (!visited[i][j] && grid[i][j] === 1) {
                // 当前位置没有访问过并且是岛屿，开始 DFS 遍历
                dfs(i, j);
                count++;  // 岛屿计数器加 1
            }
        }
    }
    return count;  // 返回岛屿计数器
};

// 测试用例
const grid1 = [
    [1,1,0,0,0],
    [1,1,0,0,0],
    [0,0,1,0,0],
    [0,0,0,1,1]
];
console.log(numIslands(grid1));  // 预期输出值为 3

const grid2 = [
    [1,1,1,1,0],
    [1,1,0,1,0],
    [1,1,0,0,0],
    [0,0,0,0,0]
];
console.log(numIslands(grid2));  // 预期输出值为 1

const grid3 = [];
console.log(numIslands(grid3));  // 预期输出值为 0

const grid4 = [
    [0,0,0,0,0],
    [0,0,0,0,0],
    [0,0,0,0,0],
    [0,0,0,0,0]
];
console.log(numIslands(grid4));  // 预期输出值为 0
