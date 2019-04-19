# Leetcode 239. Sliding Window Maximum (剑指offer: 滑动窗口最大值)

## Description

给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}

**Example:**

```java
Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

## Solution

```java
/**

* 使用Deque数据结构，即双端队列。队列中只存储promising(有希望的)的元素
	1. 当我们在进行遍历的过程中，如果发现队列的头部元素已经出了[i-k+1 : i]的范围，就pop头部元素
	2. 队列中的头部元素始终是对应值最大的element的index，因此，在做插入队列操作的时候，需要将待插入元素和队列中的元素值作比较(从队列尾部开始比较)，如果队列中元素比待插入元素小，就从队列中pop出来

* 时间复杂度(Amortized): O(N)

* 空间复杂度: O(k)

*/
public class Solution {
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        Deque<Integer> dq = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();
        if (num.length == 0 || size == 0) return ans;
        for (int i = 0; i < num.length; i++) {
            if (!dq.isEmpty() && dq.peekFirst() < i-size+1) dq.pollFirst();
            while (!dq.isEmpty() && num[i] > num[dq.peekLast()]) dq.pollLast();
            dq.add(i);
            if (i > size-2) ans.add(num[dq.peekFirst()]);
        }
        return ans;
    }
}
```

