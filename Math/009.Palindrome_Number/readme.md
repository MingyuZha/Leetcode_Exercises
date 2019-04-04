# Leetcode 9. Palindrome Number

## Description 

Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

**Example 1:**

```
Input: 121
Output: true
```

**Example 2:**

```
Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
```

**Example 3:**

```
Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
```

## Solution

为了只使用comstant memory来解这道题，我们可以直接revert the number itself，然后和原数比较，如果他们相同，那么就是回文数。

> 直接求原数的reverted number有一个弊端，就是有可能翻转过后的数会溢出，即大于INT.MAX。

为了避免这一问题的发生，我们选择只翻转数的**一半**(reverse half of the number)。

那么问题来了，我们如何知道我们翻转了数的一半呢？

> Since we divided the number by 10, and multiplied the reversed number by 10, when the original number is less than the reversed number, it means we've processed half of the number digits.

```java
public boolean isPalindrome(int x) {
  if (x < 0 || (x % 10 == 0 && x != 0)) return false;
  int revertedNum = 0;
  while (x > revertedNum) {
    revertedNum = revertedNum * 10 + x % 10;
    x /= 10;
  }
  return revertedNum == x || x == revertedNum / 10;
}
```

