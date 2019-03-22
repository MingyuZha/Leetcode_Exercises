# Leetcode 5. Longest Palindromic Substring (Medium)

## Description

Given a string **s**, find the longest palindromic substring in **s**. You may assume that the maximum length pf **s** is 1000.

**Example 1:**

```java
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
```

**Example 2:**

```java
Input: "cbbd"
Output: "bb"
```

## Solution

### Dynamic Programming Solution

本题如果使用暴力求解的方式，需要遍历所有给定字符串所有可能的子串，共有O(n^2)种可能，对于每一个子串，判断其是否为palindromic需要O(n)时间复杂度，因此总体的时间复杂度为O(n^3)。

本题如果使用**动态规划**的思想来解，在判断子串是否为回文串上可以优化为O(1)时间复杂度，具体思路是：

假设我们定义P(i, j)为：

![eq](https://latex.codecogs.com/gif.latex?P%28i%2C%20j%29%20%3D%20%5Cbegin%7Bcases%7D%20true%26%20%2C%20%5Ctext%7Bif%20s.substring%28i%2C%20j%29%20is%20palindrom%7D%20%5C%5C%20false%26%20%2C%5Ctext%7Botherwise%7D%20%5Cend%7Bcases%7D)

那么，

![eq](https://latex.codecogs.com/gif.latex?P%28i%2C%20j%29%20%3D%20P%28i&plus;1%2C%20j-1%29%20%5Ctext%7B%20and%20%7D%20s%5Bi%5D%20%3D%3D%20s%5Bj%5D)

注意：当i = j时，P(i, j) = true；当j = i+1时，P(i, j) = (s[i] == s[j])。这两类情况需特殊处理。此外，外层循环i不可以从0开始，而是应该从s.length-1开始递减，这样才可以保证在求解当前子问题时所需的其他子问题已经被解答。

核心代码如下：

```java
for (int i = n-1; i >= 0; i--) {
	for (int j = i; j < n; j++) {
		dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j <= i+1 || dp[i+1][j-1]);
		if (dp[i][j] && j-i+1 > max_len) {
			max_len = j-i+1;
			ans = s.substring(i, j+1);
		}
	}
}
```

> 时间复杂度：O(N^2)
>
> 空间复杂度：O(N^2)

**Follow up:**如何优化来降低空间复杂度？

### Expand Around Center

我们发现，所有回文串都是关于中心位置的镜像，因此，一个回文串可以由它的中心点位置向两边扩张。给定一个长度为n的字符串s，一共有2n-1个这样的中心点。

为什么有2n-1个中心点而不是n个中心点？因为有的回文串中心点可能是在量子字符中间，比如"bb"。

核心代码如下：

```java
for (int i = 0; i < n; i++) {
  int len1 = expandFromCenter(s, i, i);
  if (len1 > max_len) { max_len = len1; ans = s.substring(i-len1/2, i+len1/2+1); }
  int len2 = expandFromCenter(s, i, i+1);
  if (len2 > max_len) { max_len = len2; ans = s.substring(i-(len2-2)/2, i+(len2-2)/2+2); }
}

private static int expandFromCenter(String s, int left, int right) {
  while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
    left--;
    right++;
  }
  return right-left-1;
}
```





