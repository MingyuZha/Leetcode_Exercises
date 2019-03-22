# Leetcode 2. Add Two Numbers (Medium)

## Description

You are given two **non-empty** linked lists representing two non-negative integers. The digits are stored in **reverse order** and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

**Example:**

```python
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
```

## Solution

用一个变量carray来记录进位数，对链表中对应位置的数字依次（从最低位开始）相加。

![img](https://leetcode.com/articles/Figures/2_add_two_numbers.svg)

**注意：**当计算到最高位时，如果存在进位数，要在答案链表的末尾新增加一个节点，来存放进位数!

核心代码：

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
  int carry = 0;
  ListNode dummy = new ListNode(0);
  ListNode head = dummy;
  dummy.next = head;
  while (l1 != null || l2 != null) {
    int sum = (l1 == null ? 0 : l1.val) + (l2==null ? 0 : l2.val) + carry;
    head.next = new ListNode(sum % 10);
    carry = (sum) / 10;
    if (l2 != null) l1 = l1.next;
    if (l2 != null) l2 = l2.next;
    head = head.next;
  }
  if (carry > 0) {
    head.next = new ListNode(carry);
  }
  return dummy.next;
}
```

