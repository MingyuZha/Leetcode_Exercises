# Leetcode 264. Ugly Number II

## Description

Write a program to find the `n`-th ugly number.

Ugly numbers are **positive numbers** whose prime factors only include `2, 3, 5`. 

**Example:**

```
Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
```

## Solution

```java
/**
每一个丑数都是由比其小的丑数乘以2，3或5得来的，假如我们使用一个数组来存储按顺序生成的丑数，那么下一个生成的丑数应该为：
         Min(dp[p2]*2, dp[p3]*3, dp[p5]*5)
p2, p3, p5分别代表了指向可生成大于目前数组中最大丑数的备选丑数，意味着：
    dp[p2]*2 > dp[end]
    dp[p3]*3 > dp[end]
    dp[p5]*5 > dp[end]
*/
public int GetUglyNumber_Solution(int index) {
  if (index == 0) return 0;
  int p2 = 0, p3 = 0, p5 = 0;
  int[] uglyNumbers = new int[index];
  uglyNumbers[0] = 1;
  for (int i = 1; i < index; i++) {
    uglyNumbers[i] = Math.min(uglyNumbers[p2]*2, Math.min(uglyNumbers[p3]*3, uglyNumbers[p5]*5));
    if (uglyNumbers[i] == uglyNumbers[p2]*2) p2++;
    if (uglyNumbers[i] == uglyNumbers[p3]*3) p3++;
    if (uglyNumbers[i] == uglyNumbers[p5]*5) p5++;
  }
  return uglyNumbers[index-1];
}
```

