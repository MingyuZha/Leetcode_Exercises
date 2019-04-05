# Leetcode 844. Backspace String Compare

## Description

Given two strings `S` and `T`, return if they are equal when both are typed into empty text editors. `#` means a backspace character.

**Example 1:**

```
Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
```

**Example 2:**

```
Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
```

**Example 3:**

```
Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
```

**Example 4:**

```
Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
```

## Solution

### 使用O(N)空间复杂度解法

这种解法比较简单粗暴，就是直接build the result of each string，然后比较它们是否相同。

```java
public boolean backspaceCompare(String S, String T) {
  return build(S).equals(build(T));
}

public String build(String S) {
  Stack<Character> ans = new Stack();
  for (char c: S.toCharArray()) {
    if (c != '#')
      ans.push(c);
    else if (!ans.empty())
      ans.pop();
  }
  return String.valueOf(ans);
}
```

> 时间复杂度：O(M+N)，M和N分别代表```S```和```T```的长度
>
> 空间复杂度：O(M+N)

### 使用O(1)空间复杂度解法(Two-pointer)

如果我们选择逆向遍历每个字符串，那我们就能够提前知道要删除多少个字符，这样，用两个指针同时遍历字符串```S```和```T```，比较每个会出现在final result里的字符是否相等。

```java
public boolean backspaceCompare(String S, String T) {
  int ptr1 = S.length()-1, ptr2 = T.length()-1; //使用两指针分别遍历S和T
  int skip_s = 0, skip_t = 0; //使用两个变量记录遇到的‘#’数量
  while (ptr1 >= 0 || ptr2 >= 0) {
    while (ptr1 >= 0) { // Find position of next possible char in build(S)
      if (S.charAt(ptr1) == '#') { ptr1--; skip_s++; }
      else if (skip_s > 0 && ptr1 >= 0) { skip_s--; ptr1--; }
      else break;
    }
    while (ptr2 >= 0) { // Find position of next possible char in build(T)
      if (T.charAt(ptr2) == '#') { ptr2--; skip_t++; }
      else if (skip_t > 0 && ptr2 >= 0) { ptr2--; skip_t--; }
      else break;
    }
    // If two actual characters are different
    if (ptr1 >= 0 && ptr2 >= 0 && S.charAt(ptr1) != T.charAt(ptr2)) return false;
    // If expecting to compare char vs nothing
    if ((ptr1 >= 0) != (ptr2 >= 0)) return false;
    ptr1--; ptr2--;
  }
  return true;
}
```

