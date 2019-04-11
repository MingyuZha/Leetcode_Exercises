# Leetcode 63. Unique Paths II

## Description

A robot is located at the top-left corner of a *m* x *n* grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

![img](https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png)

An obstacle and empty space is marked as `1` and `0` respectively in the grid.

**Note:** *m* and *n* will be at most 100.

**Example 1:**

```
Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
```

## Solution

```java
/**
    Solved by DP
解题思路和Unique Paths基本一致，只不过需要考虑遇到障碍物的情况
*/
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
  if (obstacleGrid[0][0] == 1) return 0;
  int m = obstacleGrid.length, n = obstacleGrid[0].length;
  int[][] dp = new int[m][n];
  dp[0][0] = 1;
  //如果遇到障碍物，那么robot从起点处到达该点的可能路径为0，因为机器人根本不可能到达这个点
  for (int i = 1; i < m; i++) dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i-1][0];
  for (int i = 1; i < n; i++) dp[0][i] = obstacleGrid[0][i] == 1 ? 0 : dp[0][i-1];
  for (int i = 1; i < m; i++) {
    for (int j = 1; j < n; j++) {
      if (obstacleGrid[i][j] == 1) continue; //如果该点有障碍物，直接跳过即可，dp[i][j]=0，代表到达该点的可能路径为0
      dp[i][j] = dp[i-1][j] + dp[i][j-1];
    }
  }
  return dp[m-1][n-1];
}
```

