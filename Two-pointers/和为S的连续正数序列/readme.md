# 剑指offer：和为S的连续正数序列

## Description

给定一个数，要求输出所有和为该数的连续正数序列

**Example:**

```
Input: 10
Output: [1,2,3,4]
Explain: 满足和为10的连续正数序列只有1+2+3+4=10
```

## Solution

```java
/**
	使用两个指针，一个指针指向连续正数序列的起始位置，另一个指针指向连续正数序列的结束位置
	
	初始化第一个指针为1，第二个指针为2
	
	移动规则：
		1. 如果当前连续正数序列的和大于sum，那么将第一个指针向后移动一位
		2. 如果当前连续正数序列的和小于sum，那么将第二个指针向后移动一位
		3. 如果当前连续正数序列的和等于sum，那么将结果记入ans，并且将第一个指针向后移动一位
*/
public class Solution {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        // low和high为两个指针, cur_sum用来记录当前序列的和
        int low = 1, high = 2, cur_sum = low+high;
        // 储存结果的链表
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        // 储存当前序列的链表
        ArrayList<Integer> path = new ArrayList<>();
        path.add(low); path.add(high);
        while (low <= sum/2) {
            if (cur_sum < sum) { high++; cur_sum += high; path.add(high); }
            else if (cur_sum > sum) { cur_sum -= low; low++; path.remove(0); }
            else {
                ans.add(new ArrayList<>(path));
                cur_sum -= low;
                low++;
                path.remove(0);
            }
        }
        return ans;
    }
}
```

