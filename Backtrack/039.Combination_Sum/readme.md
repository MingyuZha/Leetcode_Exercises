# Leetcode 39. Combination Sum

## Description

Given a **set** of candidate numbers (`candidates`) **(without duplicates)**and a target number (`target`), find all unique combinations in `candidates` where the candidate numbers sums to `target`.

The **same** repeated number may be chosen from `candidates` unlimited number of times.

**Note:**

- All numbers (including `target`) will be positive integers.
- The solution set must not contain duplicate combinations.

**Example 1:**

```
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
```

**Example 2:**

```
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```

## Solution

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private int[] candidates;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans.clear();
        this.candidates = candidates;
        backtrack(0, 0, target, new ArrayList<>());
        return ans;
    }
    private void backtrack(int index, int cur_sum, int target, List<Integer> path) {
        if (cur_sum >= target || index >= candidates.length) {
            if (cur_sum == target) ans.add(new ArrayList(path));
            return;
        }
        path.add(candidates[index]);
        backtrack(index, cur_sum+candidates[index], target, path);
        path.remove(path.size()-1);
        backtrack(index+1, cur_sum, target, path);
    }
}
```

