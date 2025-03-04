class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0)
            return true;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int[] indegrees = new int[numCourses];
        for (int[] pr : prerequisites) {
            indegrees[pr[0]]++;
            if (!map.containsKey(pr[1])) {
                map.put(pr[1], new ArrayList<>());
            }
            map.get(pr[1]).add(pr[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                q.add(i);
                count++;
            }
        }
        while (!q.isEmpty()) {
            int curr = q.poll();
            List<Integer> children = map.get(curr);
            if (children != null) {
                for (int child : children) {
                    indegrees[child]--;
                    if (indegrees[child] == 0) {
                        q.add(child);
                        count++;
                        if (count == numCourses)
                            return true;
                    }
                }
            }

        }
        return false;
    }
}