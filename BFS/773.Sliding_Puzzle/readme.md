# Leetcode 773. Sliding Puzzle (Hard)

## Description

On a 2x3 `board`, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing `0` and a 4-directionally adjacent number and swapping it.

The state of the board is *solved* if and only if the `board` is `[[1,2,3],[4,5,0]].`

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

**Examples:**

```java
Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
```

```java
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
```

```java
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
```

其实这道题就是**华容道**问题。

## Solution

要求最少的步数，第一时间就能想到是通过**BFS**来求解这道题，但是这道题的难点在于我们不仅仅是要把"0"移到指定位置，而是要使得整个棋盘满足一定的排列。一个比较巧妙的方案就是把棋盘的**状态**转换成一个**String**，这样一来，每个棋盘状态都有一个唯一的String与之对应。我们的目标是将棋盘的状态调整为```123450```。接下来的问题就是一个标准的BFS问题了。

核心代码：

```java
public int slidingPuzzle(int[][] board) {
  String target = "123450";
  String cur = "";
  for (int i = 0; i < 2; i++) {
    for (int j = 0; j < 3; j++) {
      cur += board[i][j];
    }
  }
  Queue<String> queue = new LinkedList<>();
  Set<String> visited = new HashSet<>();
  queue.add(cur);
  visited.add(cur);
  int step = 0;
  int[][] dir = {{1, 3}, {0,2,4}, {1,5}, {0,4}, {1,3,5}, {2,4}};
  while (!queue.isEmpty()) {
    int size = queue.size();
    for (int i = 0; i < size; i++) {
      cur = queue.poll();
      if (cur.equals(target)) return step;
      int zero = cur.indexOf('0');
      for (int d : dir[zero]) {
        String next = swap(cur, zero, d);
        if (!visited.contains(next)) {
          visited.add(next);
          queue.add(next);
        }
      }
    }
    step++;
  }
  return -1;
}
private String swap(String str, int i, int j) {
  StringBuilder builder = new StringBuilder(str);
  builder.setCharAt(i, str.charAt(j));
  builder.setCharAt(j, str.charAt(i));
  return builder.toString();
}
```

