# Leetcode 135. Candy

## Description

There are *N* children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

- Each child must have at least one candy.
- Children with a higher rating get more candies than their neighbors.

What is the minimum candies you must give?

**Example 1:**

```
Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
```

**Example 2:**

```
Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
             The third child gets 1 candy because it satisfies the above two conditions.
```

## Solution

### Using One Array 

```java
/**
    1 2 4 5 3 3 1
->  1 2 3 4 1 1 1 
<-  1 1 1 2 1 2 1
MAX 1 2 3 4 1 2 1
*/
public int candy(int[] ratings) {
  int[] candies = new int[ratings.length];
  Arrays.fill(candies, 1);
  for (int i = 1; i < ratings.length; i++) {
    if (ratings[i] > ratings[i-1]) candies[i] = candies[i-1]+1;
  }
  int total = candies[ratings.length-1];
  for (int i = ratings.length-2; i >= 0; i--) {
    if (ratings[i] > ratings[i+1]) total += Math.max(candies[i], candies[i+1]+1);
    else total += Math.max(1, candies[i]);
  }
  return total;
}
```

> 时间复杂度 ：O(2N) -> O(N)
>
> 空间复杂度：O(N)

### One-pass using constant memory

![img](https://leetcode.com/articles/Figures/135_Candy_Constant_Space.PNG)

我们可以把每个人的rating看成一幅折线图，折线图由若干个mountains组成，如上图所示。为了使得所需的candies最少，我们会选择给每个处于valley的成员分发1个糖果，给处在up-hill的成员分发1, 2, … , n个糖果，给处在down-hill的成员分发k, k-1, … , 1个糖果。比较特殊的是处在peak处的成员，给他分发的糖果数取决于up-hill和down-hill上的成员数量，假如有5个成员在up-hill上，3个成员在down-hill上，那么peak处的成员应分发6个糖果，即```MAX(count(up-hill), count(down-hill)) + 1```个candies。注意，处于up-hill和down-hill上成员所需的candies总数均可通过求和公式```sum(n) = (n * (n + 1)) / 2```求得

```java
public int count(int n) {
  return (n * (n + 1)) / 2;
}
public int candy(int[] ratings) {
  if (ratings.length <= 1) {
    return ratings.length;
  }
  int candies = 0;
  int up = 0;
  int down = 0;
  int old_slope = 0;
  for (int i = 1; i < ratings.length; i++) {
    int new_slope = (ratings[i] > ratings[i - 1]) ? 1 : (ratings[i] < ratings[i - 1] ? -1 : 0);
    if ((old_slope > 0 && new_slope == 0) || (old_slope < 0 && new_slope >= 0)) {
      candies += count(up) + count(down) + Math.max(up, down);
      up = 0;
      down = 0;
    }
    if (new_slope > 0)
      up++;
    if (new_slope < 0)
      down++;
    if (new_slope == 0)
      candies++;

    old_slope = new_slope;
  }
  candies += count(up) + count(down) + Math.max(up, down) + 1;
  return candies;
}
```

