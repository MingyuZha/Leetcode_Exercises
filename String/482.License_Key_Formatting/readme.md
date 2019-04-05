# Leetcode 482. License Key Formatting

## Description

You are given a license key represented as a string S which consists only alphanumeric character and dashes. The string is separated into N+1 groups by N dashes.

Given a number K, we would want to reformat the strings such that each group contains *exactly* K characters, except for the first group which could be shorter than K, but still must contain at least one character. Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.

Given a non-empty string S and a number K, format the string according to the rules described above.

**Example 1:**

```
Input: S = "5F3Z-2e-9-w", K = 4

Output: "5F3Z-2E9W"

Explanation: The string S has been split into two parts, each part has 4 characters.
Note that the two extra dashes are not needed and can be removed.
```

**Example 2:**

```
Input: S = "2-5g-3-J", K = 2

Output: "2-5G-3J"

Explanation: The string S has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.
```

## Solution

本题的一个小窍门是我们可以先得出一个倒序的解，然后再做一下翻转，因为我们一开始并没有办法知道第一个group里面有几个characater。

```java
public String licenseFormatting(String s, int k) {
  StringBuilder builder = new StringBuilder(); //使用Stringbuilder是因为该class有一个reverse()方法，而String类型没有
  for (int i = s.length()-1; i >= 0; i--) { //因为我们是先得出一个逆序的解，所以需要从原始s的最后一个字符开始
    if (s.charAt(i) != '-') {
      /**
      * builder.length() % (k+1) == k是用来判断当前构造的字符串长度是否达到了预设的k
      * 之所以不适用builder.length() % k == 0是为了规避当builder长度为0的情况
      */
      builder.append(builder.length() % (k+1) == k ? "-" : "").append(s.charAt(i));
    }
  }
  return builder.reverse().toString().toUpperCase(); //注意需要先将builder转成String类型，因为StringBuilder类型没有toUpperCase()方法
}
```

