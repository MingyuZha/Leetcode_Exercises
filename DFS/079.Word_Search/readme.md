# Leetcode 79. Word Search

## Description

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

**Example:**

```
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
```

## Solution

```java
/**
Target word: "AAB"
    C   A   A
    A - A   A
    |
    B   C   D
Solved by DFS
* Time complexity: O(M*N*(4^K)): M, N are # of rows and columns of the board, K is the length of the word. Both DFS and BFS will take exponential time.
* Space complexity: O(K): for the call stack
*/
class Solution {
    private int[] dx = {0,0,-1,1};
    private int[] dy = {1,-1,0,0};
    private char[][] board;
    private String word;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        int m = board.length, n = board[0].length;
        if ((board == null || m == 0) && (word != null || word.length() > 0)) return false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0)) return true;
            }
        }
        return false;
    }
    private boolean dfs(int x, int y, int steps) {
        if (steps == word.length()) return true;
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != word.charAt(steps)) return false;
        char old = board[x][y];
        board[x][y] = '#';
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i], yy = y + dy[i];
            flag = dfs(xx, yy, steps+1);
            if (flag) break;
        }
        board[x][y] = old;
        return flag;
    }
}
```

