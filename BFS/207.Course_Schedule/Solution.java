import java.util.*;
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] graph = new int[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        for (int[] prerequiste : prerequisites) {
            graph[prerequiste[1]][prerequiste[0]] = 1;
            indegree[prerequiste[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (graph[cur][i] > 0) {
                    indegree[i]--;
                    if (indegree[i] == 0) queue.offer(i);
                }
            }
        }
        return count == numCourses;
    }
    public static void main(String[] args) {
        Solution test = new Solution();
        int[][] prerequisites = {{1,0}, {0,1}};
        System.out.println(test.canFinish(2, prerequisites));
    }
}