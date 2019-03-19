# Leetcode 547. Friend Circles (Medium)

## Description

There are **N** students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a **direct** friend of B, and B is a **direct** friend of C, then A is an **indirect**friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a **N\*N** matrix **M** representing the friend relationship between students in the class. If M[i][j]= 1, then the ith and jth students are **direct** friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

**Example 1:**

```java
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation: The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
```

**Example 2:**

```java
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
```

## Solution

### DFS Solution

按照常理，我们需要一个visited数组来记录访问过的student，但是在这边我们可以利用矩阵中对角线元素来代替visited数组，每当递归访问学生**i**时，我们都先检查M\[i\][i]，如果M\[i]\[i] = 0，那么说明该学生已经被访问过，直接返回即可。

核心代码如下：

**Main函数**

```java
public int findCircleNum(int[][] M) {
  int ans = 0;
  int m = M.length, n = M[0].length;
  for (int i = 0; i < m; i++) {
    if (M[i][i] == 1) {
      dfs(M, i);
      ans++;
    }
  }
  return ans;
}
```

**递归函数**

```java
private void dfs(int[][] M, int i) {
  if (M[i][i] == 0) return;
  M[i][i] = 0;
  for (int j = 0; j < M.length; j++) {
    if (M[i][j] == 1) {
      M[i][j] = 0;
      dfs(M, j);
    }
  }
}
```

