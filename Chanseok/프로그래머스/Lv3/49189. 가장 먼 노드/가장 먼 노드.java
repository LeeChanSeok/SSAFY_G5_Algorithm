import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        final int delta = 100_000;
        int a, b;
        Set<Integer> e = new HashSet<>();
        for(int i = 0, edgeSize = edge.length; i < edgeSize; ++i) {
            a = edge[i][0]; b = edge[i][1];
            if(a > b) {
                a = edge[i][1]; b = edge[i][0]; 
            }

            e.add(a*delta + b);
        }

        boolean[] visit = new boolean[n+1];
        visit[1] = true;

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        int qSize, cur, cnt;
        while(!q.isEmpty()) {

            cnt = 0;
            qSize = q.size();

            while(qSize-- > 0) {
                cur = q.poll();

                for(int i = 1; i <= n; ++i) {
                    if(cur >= i) {
                        a = i; b = cur;
                    }else {
                        a = cur; b = i;
                    }
                    if(!visit[i] && e.contains(a*delta + b)) {
                        q.offer(i);
                        visit[i] = true;
                        ++cnt;
                    }
                }
            }
            if(cnt == 0) break;
            answer = cnt;
        }

        return answer;
    }
}
