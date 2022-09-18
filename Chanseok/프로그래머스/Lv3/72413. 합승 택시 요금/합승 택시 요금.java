import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

    class Info {
        int node, fee;

        public Info(int node, int fee) {
            super();
            this.node = node;
            this.fee = fee;
        }

    }


    public int solution(int n, int s, int a, int b, int[][] fares) {

        int INF = Integer.MAX_VALUE;
        int[][] graph = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <=n; j++) {
                if (i == j) graph[i][j] = 0;
                else graph[i][j] = INF;
            }
        }


        // 주어진 간선 정보를 이용해서 2차원 인접 행렬 구현
        int edgeSize = fares.length;
        int c, d, f;
        for(int i = 0; i < edgeSize; i++) {
            c = fares[i][0];
            d = fares[i][1];
            f = fares[i][2];

            graph[c][d] = f;
            graph[d][c] = f;
        }

        // 인접 행렬로부터 플로이드워셜 알고리즘을 이용해서 모든 정점사이의 최소 거리 구하기
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(graph[i][k] == INF || graph[k][j] == INF) continue;
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        // 시작점 s에서 이동할 수 있는 모든 정점들을 방문하면서 해당 정점에서 a와 b가 따로 이동할 때 비용을 구하여 최소 비용을 찾는다 
        int answer = bfs(graph, n, s, a, b);
        return answer;
    }

    public int bfs(int[][] graph, int n, int s, int a, int b) {

        Queue<Info> q = new ArrayDeque<>();
        boolean[] visit = new boolean[n+1];

        q.offer(new Info(s, 0));

        // 처음 min값으로 a와 b가 합승하지 않고 이동하는 비용을 저장
        int minFee = graph[s][a] + graph[s][b];
        Info cur;
        int INF = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            cur = q.poll();

            // 현재까지 합승한 비용이 최소 비용보다 크면 X
            if(cur.fee >= minFee) continue;


            int f = cur.node;
            visit[f] = true;
            // 현재 위치에서 a와 b가 따로 이동할 때 비용과 최소 비용을 비교하여 값 갱신
            int fee = cur.fee + graph[f][a] + graph[f][b];
            if(fee < minFee) minFee = fee;

            for(int t = 1; t <= n; t++) {
                if(graph[f][t] != INF && !visit[t]) {
                    // 현재위치에서 방문하지 않은 노드 중 합승하여 이동할 위치로 이동
                    q.offer(new Info(t, cur.fee + graph[f][t]));
                }
            }

        }

        return minFee;
    }
}