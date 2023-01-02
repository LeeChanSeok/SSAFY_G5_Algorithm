import java.util.*;

class Solution {
		
    class Node{
        int num, dist, trap;

        public Node(int num, int dist, int trap) {
            super();
            this.num = num;
            this.dist = dist;
            this.trap = trap;
        }

    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;

        List<int[]>[] original_graph = new List[n+1];
        List<int[]>[] reverse_graph = new List[n+1];

        for(int i = 1; i <= n; ++i) {
            original_graph[i] = new LinkedList<int[]>();
            reverse_graph[i] = new LinkedList<int[]>();
        }

        int from, to, dist;
        for(int i = 0, edgeSize = roads.length; i < edgeSize; ++i) {
            from = roads[i][0];
            to = roads[i][1];
            dist = roads[i][2];

            original_graph[from].add(new int[] {to, dist});
            reverse_graph[to].add(new int[] {from, dist});
        }

        Map<Integer, Integer> trap_idx = new HashMap<>();
        for(int i = 0, trap_size = traps.length; i < trap_size; ++i) {
            trap_idx.put(traps[i], i);
        }


        answer = bfs(start, end, n, original_graph, reverse_graph, traps, trap_idx);

        return answer;
    }

    private int bfs(int start, int end, int n, List<int[]>[] original_graph, List<int[]>[] reverse_graph, int[] traps, Map<Integer, Integer> trap_idx) {

        PriorityQueue<Node> pq = new PriorityQueue<>((p1, p2) -> p1.dist - p2.dist);
        pq.offer(new Node(start, 0, 0));
        Map<Integer, int[]> visit = new HashMap<>();
        
        Node cur;
        int to, dist;
        int[] temp;
        int tIdx;
        List<int[]> edges;
        boolean fromTrap, toTrap;
        while(!pq.isEmpty()) {
            fromTrap = false;

            cur = pq.poll();
            if(cur.num == end)  return cur.dist;

            // trap 방문 처리
            if(trap_idx.containsKey(cur.num)) {
                tIdx = trap_idx.get(cur.num);
                if(visitTrap(cur.trap, tIdx)) {
                    cur.trap &= ~(1 << tIdx); 
                }else {
                    cur.trap |= (1 << tIdx);
                    fromTrap = true;
                }
            } 


            // original
            edges = original_graph[cur.num];
            for(int[] edge : edges) {
                to = edge[0]; dist = edge[1];

                toTrap = false;
                if(trap_idx.containsKey(to) && visitTrap(cur.trap, trap_idx.get(to))) toTrap = true;

                if(fromTrap ^ toTrap) continue;
                
                if(visit.containsKey(to)) {
                    temp = visit.get(to);
                    if(temp[0] == cur.trap && temp[1] <= (cur.dist + dist)) continue;
                }

visit.put(to, new int[] {cur.trap, cur.dist + dist});
                
                pq.offer(new Node(to, cur.dist + dist, cur.trap));

            }

            // reverse
            edges = reverse_graph[cur.num];
            for(int[] edge : edges) {
                to = edge[0]; dist = edge[1];

                toTrap = false;
                if(trap_idx.containsKey(to) && visitTrap(cur.trap, trap_idx.get(to))) toTrap = true;

                if(!(fromTrap ^ toTrap)) continue;

                if(visit.containsKey(to)) {
                    temp = visit.get(to);
                    if(temp[0] == cur.trap && temp[1] <= (cur.dist + dist)) continue;
                }

visit.put(to, new int[] {cur.trap, cur.dist + dist});
                
                pq.offer(new Node(to, cur.dist + dist, cur.trap));

            }

        }

        return -1;
    }


    private boolean visitTrap(int num, int to) {
        if((num & (1 << to)) != 0) return true;
        else return false;
    }
}
