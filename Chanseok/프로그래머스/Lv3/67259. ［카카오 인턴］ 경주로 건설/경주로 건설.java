import java.util.*;

class Solution {
    class Info{
        int x, y;
        int d;
        int cost;

        public Info(int x, int y, int d, int cost) {
            super();
            this.x = x;
            this.y = y;
            this.d = d;
            this.cost = cost;
        }

    }
		
    int[] dx = {-1, 0, 1 ,0};
    int[] dy = {0, 1, 0, -1};

    public int solution(int[][] board) {
        int answer = 0;

        answer = bfs( board);

        return answer;
    }

    private int bfs(int[][] board) {
        final int INF = Integer.MAX_VALUE;  
        
        int N = board.length;
        int[][][] visit = new int[4][N][N];
        for(int d = 0; d < 4; ++d) 
            for(int i = 0; i < N; ++i) 
                Arrays.fill(visit[d][i], INF);
        
        PriorityQueue<Info> pq = new PriorityQueue<>((q1, q2) -> q1.cost - q2.cost);
        pq.offer(new Info(0, 0, 10, 0));

        Info cur;
        int x, y, nx, ny, cost;
        while(!pq.isEmpty()) {

            cur = pq.poll();
            x = cur.x; y = cur.y;
            if(x == N-1 && y == N-1) return cur.cost;

            for(int d = 0; d < 4; ++d) {
                if(Math.abs(cur.d - d) == 2) continue;
                nx = x + dx[d]; ny = y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == 1) continue;

                if(cur.d == 10 || d == cur.d) {
                    cost = cur.cost + 100;
                    if(visit[d][nx][ny] <= cost) continue;
                    visit[d][nx][ny] = cost;
                    pq.offer(new Info(nx, ny, d, cost));
                }
                else {
                    cost = cur.cost + 600;
                    if(visit[d][nx][ny] <= cost) continue;
                    visit[d][nx][ny] = cost;
                    pq.offer(new Info(nx, ny, d, cost));
                }
            }
        }

        return 0;
    }
}
