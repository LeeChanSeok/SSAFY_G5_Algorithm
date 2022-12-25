import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;

        //FW
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (computers[i][k] == 1 && computers[k][j] == 1)
                        computers[i][j] = 1;
                }
            }
        }
        
        boolean[] visit = new boolean[n];
        for(int i = 0; i < n; ++i) {
            if(!visit[i]) {
                ++answer;
                bfs(computers, visit, i, n);
            }
        }

        return answer;
    }
    
    private void bfs(int[][] computers, boolean[] visit, int node, int n) {

        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        visit[node] = true;
        int cur;
        
        while(!q.isEmpty()) {
            cur = q.poll();

            for(int i = 0; i < n; ++i) {
                if(!visit[i] && computers[cur][i] == 1) {
                    visit[i] = true;
                    q.offer(i);
                }
            }
        }
        
    }
    
}
