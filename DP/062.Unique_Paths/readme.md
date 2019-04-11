# Leetcode 62. Unique Paths

## Description

A robot is located at the top-left corner of a *m* x *n* grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

![img](https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png)

Above is a 7 x 3 grid. How many possible unique paths are there?

**Note:** *m* and *n* will be at most 100.

**Example 1:**

```
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
```

**Example 2:**

```
Input: m = 7, n = 3
Output: 28
```

## Solution

```java
/**
由于robot每次只能向右或者向下移动，因此也就意味着棋盘上的每个位置，都只可能通过它的上方或左侧的格子到达。
假设下图中每个cell中的数字代表robot从起点到达该cell所有可能的行动路径：
    1     1     1      1  ...
    1  (1+1=2)  3      4  ...
    1  (2+1=3) (3+3=6) 10 ... 
*/
public int uniquePaths(int m, int n) {
  int[][] dp = new int[m][n];
  for (int i = 0; i < m; i++) dp[i][0] = 1;
  for (int i = 0; i < n; i++) dp[0][i] = 1;
  for (int i = 1; i < m; i++) {
    for (int j = 1; j < n; j++) {
      dp[i][j] = dp[i-1][j] + dp[i][j-1];
    }
  }
  return dp[m-1][n-1];
}
```

