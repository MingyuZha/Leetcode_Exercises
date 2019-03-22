# Leetcode 207. Course Schedule (Medium)

## Description

There are a total of *n* courses you have to take, labeled from `0` to `n-1`.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: `[0,1]`

Given the total number of courses and a list of prerequisite **pairs**, is it possible for you to finish all courses?

**Example 1:**

```python
Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
```

**Example 2:**

```python
Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
```

## Solution

本题的解题思路是将课程之间的依赖关系想象成一张**有向的**graph，生成edge的规则是**需要先修**的课程指向想要修习的课程。然后对这张有向图进行**拓扑排序**，记录每个节点的**indegree**，使用BFS不断的更新节点的入度，如果节点的入度等于0，就将这个节点push到队列里面去，循环直到所有节点的入度都为0或者所有节点都已被访问过。

核心代码：

```java
public boolean canFinish(int numCourses, int[][] prerequisites) {
  int[][] graph = new int[numCourses][numCourses];
  int[] indegree = new int[numCourses];
  for (int[] prerequiste : prerequisites) {
    graph[prerequiste[1]][prerequiste[0]] = 1; //prerequisite course -> target course
    indegree[prerequiste[0]]++;  //update the indegree of the target course
  }
  Queue<Integer> queue = new LinkedList<>();
  for (int i = 0; i < numCourses; i++) {
    if (indegree[i] == 0) queue.offer(i);   //if the course's indegree = 0, then push it into the queue
  }
  int count = 0;  //keep track of the number of courses with indegree equals to 0
  while (!queue.isEmpty()) {
    int cur = queue.poll();
    count++;
    for (int i = 0; i < numCourses; i++) {
      if (graph[cur][i] > 0) {
        indegree[i]--;  //update the indegree of the neighboring courses of the current course
        if (indegree[i] == 0) queue.offer(i);
      }
    }
  }
  return count == numCourses;  //if all courses' indegree euqals to 0, then returns true
}
```



