import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;

        List<Integer>[] graph = new List[n];
        for(int i = 0; i < n; ++i) graph[i] = new LinkedList<>();

        for(int[] p : path) {
            graph[p[0]].add(p[1]);
            graph[p[1]].add(p[0]);
        }

        int ASize = order.length;

        Map<Integer, Integer> A2B = new HashMap<>();
        Map<Integer, Integer> B2A = new HashMap<>();
        for(int[] od : order) {
            A2B.put(od[0], od[1]);
            B2A.put(od[1], od[0]);
        }

        answer = bfs(graph, A2B, B2A, ASize);

        return answer;
    }

    private boolean bfs(List<Integer>[] graph, Map<Integer, Integer> A2B, Map<Integer, Integer> B2A, int ASize) {

        Set<Integer> A = new HashSet<>();
        Set<Integer> B = new HashSet<>();
        
        Set<Integer> visit = new HashSet<>();
        visit.add(0);
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        if(A2B.containsKey(0)) A.add(0);
        if(B2A.containsKey(0)) return false;
        int cur;
        while(!q.isEmpty()) {
            cur = q.poll();

            for(int next : graph[cur]) {
                if(visit.contains(next)) continue;

                int AB = A2B.getOrDefault(next, -1);
                if(AB != -1) {
                    A.add(next);
                    q.offer(next);
                    visit.add(next);
                    if(B.contains(AB)) {
                        q.add(AB);
                        visit.add(AB);
                    }
                    continue;
                }

                int BA = B2A.getOrDefault(next, -1);
                if(BA != -1) {
                    B.add(next);
                    if(A.contains(BA)) {
                        q.offer(next);
                        visit.add(next);
                    }
                    continue;
                }

                q.offer(next);
                visit.add(next);
            }

            if(A.size() == ASize) return true;

        }

        return false;
    }
}
