# Leetcode 23. Merge k Sorted Lists

## Description

Merge *k* sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

**Example:**

```
Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
```

## Solution

```java
/**
    假设lists中共含有k个链表，那么就维护一个size为k的最小堆，初始化时将每个链表的表头push进最小堆，此后，每次pop出最小堆的堆顶元素，并将堆顶元素的下一个元素push进最小堆（最小堆会重新排列以满足最小堆的性质），直至堆中元素全部被pop完
* 时间复杂度：O(Nlogk),N代表所有链表的节点总数，k代表链表数，每次插入元素的时间复杂度为O(logk)，一共需要进行N次插入操作，因此，总的复杂度为O(Nlogk)
* 空间复杂度：O(k)，因为需要维护一个size为k的最小堆
*/
public ListNode mergeKLists(ListNode[] lists) {
  PriorityQueue<ListNode> min_heap = new PriorityQueue<>(3, new Comparator<ListNode>() {
    public int compare(ListNode l1, ListNode l2) {
      return l1.val-l2.val;
    }
  });
  for (int i = 0; i < lists.length; i++) if (lists[i] != null) min_heap.offer(lists[i]);
  ListNode dummy = new ListNode(0);
  ListNode head = dummy;
  while (!min_heap.isEmpty()) {
    ListNode cur = min_heap.poll();
    head.next = cur;
    head = head.next;
    if (cur.next != null) min_heap.offer(cur.next);
  }
  return dummy.next;
}
```

