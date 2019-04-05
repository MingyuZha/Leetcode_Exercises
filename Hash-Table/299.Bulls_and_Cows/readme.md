# Leetcode 299. Bulls and Cows

## Description

You are playing the following [Bulls and Cows](https://en.wikipedia.org/wiki/Bulls_and_Cows) game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use `A` to indicate the bulls and `B` to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

**Example 1:**

```
Input: secret = "1807", guess = "7810"

Output: "1A3B"

Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
```

**Example 2:**

```
Input: secret = "1123", guess = "0111"

Output: "1A1B"

Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
```

## Solution

这道题要求解```bulls```很容易，只要```secret```和```guess```在同一位置上的字符相同就将```bulls```增加1，tricky的地方在于求解```cows```。解法就是使用**Hash Table**去存储当前"可用的"字符个数。

```counts```：```counts[i]```代表了数字i在```secret```中出现的次数**减去**数字i在```guess```中出现的次数。

```java
public String getHint(String secret, String guess) {
  int bulls = 0, cows = 0;
  int[] counts = new int[10];
  for (int i = 0; i < secret.length(); i++) {
    int cur_s = secret.charAt(i)-'0';
    int cur_g = guess.charAt(i)-'0';
    if (cur_s == cur_g) bulls++; //如果两个字符相等，则说明该位置的字符猜正确了
    else { //否则的话，需要判断guess中当前位置的字符是否在secret中的其他位置出现过
      if (counts[cur_s] < 0) cows++; //如果secret中当前字符在之前的guess中出现过
      if (counts[cur_g] > 0) cows++; //如果guess中当前字符在之前的secret中出现过
      counts[cur_s]++; counts[cur_g]--; 
    }
  }
  return bulls+"A"+cows+"B";
}
```

