# Leetcode 849. Maximize Distance to Closest Person

## Description

In a row of `seats`, `1` represents a person sitting in that seat, and `0`represents that the seat is empty. 

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 

Return that maximum distance to closest person.

**Example 1:**

```
Input: [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
```

**Example 2:**

```
Input: [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
```

## Solution

### Next Array解

```java
/**
left[i]: the distance from seat i to the closest person sitting to the left of i
right[i]: the distance to the closest person sitting to the right of i
the closest person in seat i sits a distance min(left[i], right[i]) away.
*/
public int maxDistToClosest(int[] seats) {
  int N = seats.length;
  int[] left = new int[N];
  int[] right = new int[N];
  Arrays.fill(left, N); Arrays.fill(right, N);
  for (int i = 0; i < N; i++) {
    if (seats[i] == 1) left[i] = 0;
    else if (i > 0) {
      left[i] = left[i-1] + 1;
    }
  }
  for (int i = N-1; i >= 0; i--) {
    if (seats[i] == 1) right[i] = 0;
    else if (i < N-1) {
      right[i] = right[i+1] + 1;
    }
  }
  int ans = 0;
  for (int i = 0; i < N; i++) {
    ans = Math.max(ans, Math.min(left[i], right[i]));
  }
  return ans;
}
```

> 时间复杂度：O(N)
>
> 空间复杂度：O(N)

### Two pointers解

```java
/**
prev: the closest filled seat to the left of i
future: the cloest filled seat to the right of i
Then at seat i, the cloest person is min(i-prev, future-i) away.
Exception: 
	i-prev should be considered infinite if there is no person to the left of seat i
	future-i is infinite if there is no person to the right of seat i
*/
public int maxDistToClosest(int[] seats) {
  int N = seats.length;
  int prev = -1, future = 0;
  int ans = 0;

  for (int i = 0; i < N; ++i) {
    if (seats[i] == 1) {
      prev = i;
    } else {
      /**
      如果future在i的左侧，那么future无论如何都要++
      如果future已经在i的右侧了，我们的停止条件是找到最近的那个filled seat
      */
      while ((future < N && seats[future] == 0) || future < i)
        future++;
      int left = prev == -1 ? N : i - prev; //prev = -1代表i的左侧没有坐人
      int right = future == N ? N : future - i; //future = N代表i的右侧没有坐人
      ans = Math.max(ans, Math.min(left, right));
    }
  }

  return ans;
}
```

> 时间复杂度：O(N)，且比前一个解法更快，因为该解法只需要遍历一遍数组，而前一个解法需要遍历两遍
>
> 空间复杂度：O(1)