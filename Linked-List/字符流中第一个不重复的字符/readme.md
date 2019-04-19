# 剑指offer：字符流中第一个不重复的字符

## Description

请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。

**输出描述：**

```
如果当前字符流没有存在出现一次的字符，返回#字符。
```

## Solution

```java
/**
	我的解题思路是使用Doubly-Linked List来解，DList里存储的是按出现次序排列的所有只出现了一次的字符
	
	为了实现插入和删除操作都是O(1)的时间复杂度，我们还需要使用一个HashMap来存储每个DListNode的地址
	
	Insert操作：
	   检查插入的字符是否已经在先前的字符流中出现过。如果出现过，将其对应的节点从链表中删除，并且设置其在HashMap中对应的value值为null；如果没有出现过，将其插入到链表的尾部，并在哈希表中插入这条记录
	   * 时间复杂度：O(1)
	   * 空间复杂度：O(1)：因为实际上，总共只可能有256中字符，因此哈希表和链表的size都是constant的
	   
	FirstAppearingOnce操作：
	   如果链表中含有有效节点，返回头部节点
	   如果链表中没有有效节点，返回’#‘
	   * 时间复杂度：O(1)
*/
public class Solution {
    private class DListNode {
        char val;
        DListNode prev, next;
        DListNode(char v) { val = v; }
    }
    HashMap<Character, DListNode> map = new HashMap<>();
    DListNode head, tail;
    public Solution() {
        head = new DListNode(' ');
        tail = new DListNode(' ');
        head.next = tail; tail.prev = head;
    }
    //Insert one char from stringstream
    public void Insert(char ch)
    {
        if (map.containsKey(ch) && map.get(ch) != null) {
            DListNode node = map.get(ch);
            //Delete the node from the linked list
            DListNode prev = node.prev;
            node.next.prev = prev;
            prev.next = node.next;
            //Set the ch's corresponding node as null in the hashmap
            map.put(ch, null);
        } else {
            DListNode new_node = new DListNode(ch);
            //Insert the new node
            DListNode last_node = tail.prev;
            last_node.next = new_node;
            new_node.prev = last_node;
            new_node.next = tail;
            tail.prev = new_node;
            map.put(ch, new_node);
        }
    }
  //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        return head.next == tail ? '#' : head.next.val;
    }
}
```

