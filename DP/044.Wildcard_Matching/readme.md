# Leetcode 44. Wildcard Matching

## Description

Given an input string (`s`) and a pattern (`p`), implement wildcard pattern matching with support for `'?'` and `'*'`.

```
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
```

The matching should cover the **entire** input string (not partial).

**Note:**

- `s` could be empty and contains only lowercase letters `a-z`.
- `p` could be empty and contains only lowercase letters `a-z`, and characters like `?` or `*`.

**Example 1:**

```
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
```

**Example 2:**

```
Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
```

**Example 3:**

```
Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
```

**Example 4:**

```
Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
```

**Example 5:**

```
Input:
s = "acdcb"
p = "a*c?b"
Output: false
```

## Solution

#### 动态规划

```java
/**
       p
       |
       0  1  2  3
S -> 0 
     1
     2
* match[][]: (n+1) x (m+1), n: length of s, m: length of p
* match[i][j] represents: whether length j pattern matches length i string
* 如果p[j] == '?'或者 s[i] == p[j]，那么match[i][j] = match[i-1][j-1]
* 如果p[j] == '*'，‘*’可以代表空字符，也可以代表任意长度的字符串
	* 如果*代表空字符，那么match[i][j] = match[i-1][j]
	* 如果*代表任意长度的序列，那么match[i][j] = match[i][j-1]
*/
public boolean isMatch(String s, String p) {
  int n = s.length(), m = p.length();
  boolean[][] match = new boolean[n+1][m+1];
  match[0][0] = true;
  for (int pi = 1; pi <= m; pi++) match[0][pi] = match[0][pi-1] && p.charAt(pi-1) == '*';
  for (int si = 1; si <= n; si++) match[si][0] = false;
  for (int si = 1; si <= n; si++) {
    for (int pi = 1; pi <= m; pi++) {
      if (s.charAt(si-1) == p.charAt(pi-1) || p.charAt(pi-1) == '?') {
        match[si][pi] = match[si-1][pi-1];
      } else if (p.charAt(pi-1) == '*') {
        match[si][pi] = match[si-1][pi] || match[si][pi-1];
      }
    }
  }
  return match[n][m];
}
```

