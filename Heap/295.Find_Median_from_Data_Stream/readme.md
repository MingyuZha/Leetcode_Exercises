# Leetcode 295. Find Median from Data Stream (数据流中的中位数)

## Description

Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,

```
[2,3,4], the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5
```

Design a data structure that supports the following two operations:

- void addNum(int num) - Add a integer number from the data stream to the data structure.
- double findMedian() - Return the median of all elements so far.

**Example:**

```
addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
```

## Solution

```java
/**
* 我们考虑可以将数据流中的所有数据分割成两部分，且这两部分数据满足如下条件：
	1. 其中一部分中的所有数据必须都大于另一部分中的任何一个数据
	2. 两部分数据包含的数据个数之差必须小于等于1
	
* 这样一来，求解数据流中的中位数就非常简单了：
	1. 如果两部分数据堆size一样，中位数就是两个堆顶元素的平均值
	2. 否则，中位数就是size更大的数据堆的堆顶元素

* 我们可以使用最大堆和最小堆来实现对数据流的分割

* 插入过程中，我们可能会遇到3种不同的情况：
	1. 两个堆内元素数量一致，那么就只要比较待插入元素是否比最大堆堆顶元素大？如果是，则插入最小堆，否则插入最大堆
	2. sizeOf(最大堆) > sizeOf(最小堆)：
		a. 如果待插入元素大于最大堆堆顶，直接插入最小堆
		b. 否则，现将待插入元素插入最大堆，然后取出最大堆中的堆顶元素插入最小堆
	3. sizeOf(最大堆) < sizeOf(最小堆)：
		a. 如果待插入元素小于等于最大堆堆顶，直接插入最大堆
		b. 否则，现将待插入元素插入最小堆，然后取出最小堆中的堆顶元素插入最大堆
	
* Insert时间复杂度：O(logN)
* GetMedian时间复杂度：O(1)

*/
public class Solution {
    PriorityQueue<Integer> min = new PriorityQueue<>();
    PriorityQueue<Integer> max = new PriorityQueue<>(new Comparator<Integer>() {
        public int compare(Integer n1, Integer n2) {
            return n2-n1;
        }
    });
    public void Insert(Integer num) {
        if (min.size() == max.size()) {
            if (min.size() > 0) {
                if (num > max.peek()) min.offer(num);
                else max.offer(num);
            } else {
                min.offer(num);
            }
        } else if (min.size() > max.size()) {
            if (max.isEmpty() || num <= max.peek()) max.offer(num);
            else {
                min.offer(num);
                max.offer(min.poll());
            }
        } else {
            if (num > max.peek()) min.offer(num);
            else {
                max.offer(num);
                min.offer(max.poll());
            }
        }
    }
    public Double GetMedian() {
        if (min.size() == max.size()) return new Double((min.peek()+max.peek()))/2;
        else if (min.size() > max.size()) return new Double(min.peek());
        else return new Double(max.peek());
    }
}
```

