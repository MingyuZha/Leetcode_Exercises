# Leetcode 3. Longest Substring Without Repeating Characters (Medium)

## Description

Given a string, find the length of the **longest substring** without repeating characters.

**Example 1:**

```python
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
```

**Example 2:**

```python
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```

**Example 3:**

```python
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
```

## Solution

### Dynamic Programming Solution

本题我一开始的思路是觉得可以用动态规划的思路求解，具体实现方案如下：

* 使用一个Map来存储每个character最近出现的位置
* 使用一个数组dp[]来记录当以index为i的字符为substring的最后一个字符时，能取得的最大长度。
* 使用一个变量max_len来记录当前最长的substring
* 每遍历到一个新的字符时，假设其index为i，有两种情况：
  * 该字符在之前出现过：这种情况下，计算两个值：1. 当前字符的位置与前一次出现该字符位置之间的距离，即**i - map[c]**；2. **dp[i-1] + 1**。取这两个值中的较小值作为dp[i]
  * 该字符没有出现过：这种情况下，可以直接计算dp[i]的值，即**dp[i] = dp[i-1] + 1**。
* 更新map
* 更新max_len

核心代码：

```java
public int lengthOfLongestSubstring(String s) {
  if (s.length() == 0) return 0;
  int[] dp = new int[s.length()]; //数组中每个位置的元素代表字符串中以该位置字符为终止字符的subarray所能得到的最大长度
  dp[0] = 1;
  Map<Character, Integer> map = new HashMap<>(); //用来存储每个字符字符串中**最近**出现的位置
  map.put(s.charAt(0), 0);
  int max_len = 1;
  for (int i = 1; i < s.length(); i++) {
    char cur = s.charAt(i);
    if (map.containsKey(cur)) {
      dp[i] = Math.min(dp[i-1]+1, i-map.get(cur));
    } else {
      dp[i] = dp[i-1]+1;
    }
    map.put(cur, i); //更新该字符在字符串中最近出现的位置
    max_len = Math.max(max_len, dp[i]); //更新max_len
  }
  return max_len;
}
```

> 时间复杂度：O(N)
>
> 空间复杂度：O(N+128) -> O(N)：在这个解法中，我们需要一个dp数组，其空间复杂度为O(N)，因为它的长度和输入字符串的长度是一致的。此外，我们还需要一个Map，在ASCII系统下，总共有128个可能的字符，因此其空间复杂度为O(128)。

### Two-pointers: Sliding Window Solution

上述动态规划的解法有一个劣势是需要较大的空间复杂度，因此能否有一个使用constant memory space的解答呢？

答案是有的，那就是使用**Two-pointers**的解法，该解法的具体思路如下：

* 维护两个指针变量，第一个指针用来指向subarray的起始位置，第二个指针用来指向subarray的终止位置
* 维护一个Map，用来记录字符在原字符串中**最近**出现的位置，这点和前面一个解法的思路一样
* 每次迭代，将第二个指针向右移动一位，并判断移动后，**第二个指针**所指向的字符是否已经在Map中存在：
  * 如果存在，判断是否存在位置在第一个指针之后：
    * 如果是，那么就将**第一个指针**移动到那个位置之后一位，因为以之前的字符为起始位置的最长substring我们已经找到了，不可能比那更长了，所以我们需要更新第一个指针的位置。
    * 如果否，那么可以安全的保持第一个指针的位置。
  * 如果不存在，那么可以安全的保持第一个和第二个指针的位置
* 每次迭代，都需要更新字符在map中出现的位置
* 同样维护一个变量：max_len，来记录最终答案

核心代码：

```java
public int lengthOfLongestSubstring(String s) {
  if (s.length() == 0) return 0;
  int[] map = new int[128];
  int max_len = 1;
  for (int i = 0, j = 0; j < s.length(); j++) {
    i = Math.max(i, map[s.charAt(j)]);
    max_len = Math.max(max_len, j-i+1);
    map[s.charAt(j)] = j+1;
  }
  return max_len;
}
```



