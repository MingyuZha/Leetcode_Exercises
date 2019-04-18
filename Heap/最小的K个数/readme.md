# 最小的K个数

## Description

输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。

## Solution

```java
/**
* 时间复杂度为O(NlogK)的算法
* 维护一个size为k的最大堆
* 特别适合处理海量数据，且没有修改输入的数据
*/
public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
  if (k > input.length || k == 0) return new ArrayList<>();
  PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
    public int compare(Integer n1, Integer n2) {
      return n2-n1;
    }
  });
  for (int num : input) {
    if (pq.size() < k) pq.offer(num);
    else if (pq.peek() > num) { pq.poll(); pq.offer(num); } 
  }
  return new ArrayList(pq);
}
```

```java
public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
  if (k > input.length || k == 0) return new ArrayList<>();
  int left = 0, right = input.length-1;
  while (left <= right) {
    int index = Partition(input, left, right);
    if (index == k-1) break;
    else if (index > k-1) right = index-1;
    else left = index + 1;
  }
  ArrayList<Integer> ans = new ArrayList<>();
  for (int i = 0; i < k; i++) ans.add(input[i]);
  return ans;
}
private int Partition(int[] arr, int begin, int end) {
  int i = begin;
  for (int j = begin; j < end; j++) {
    if (arr[j] <= arr[end]) {
      int temp = arr[j];
      arr[j] = arr[i];
      arr[i] = temp;
      i++;
    }
  }
  int temp = arr[end];
  arr[end] = arr[i];
  arr[i] = temp;
  return i;
}
```

