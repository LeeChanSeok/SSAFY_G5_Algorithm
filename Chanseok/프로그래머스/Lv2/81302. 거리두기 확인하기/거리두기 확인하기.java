import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    final int N = 5;
    final int[] dx = {-1, 1, 0, 0};
    final int[] dy = {0, 0, -1, 1};

    public int[] solution(String[][] places) {
        int roomCnt = places.length;

        int[] answer = new int[roomCnt];
        char[][] room;
        for(int i = 0; i < roomCnt; ++i) {
            room = makeRoom(places[i]);

            if(isSafe(room)) answer[i] = 1;
            else answer[i] = 0;
        }

        return answer;
    }

    private boolean isSafe(char[][] room) {

        for(int i = 0 ; i < N; ++i) {
            for(int j = 0; j < N; ++j) {
                if(room[i][j] == 'P') {
                    if(!bfs(i, j, room)) return false;
                }
            }
        }

        return true;
    }

    private boolean bfs(int i, int j, char[][] room) {

        final int dist = 2;
        boolean[][] visit = new boolean[N][N];
        visit[i][j] = true;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {i, j});

        int[] cur;
        int qSize, cnt = 0;
        int x, y, nx, ny;
        while(!q.isEmpty()) {
            qSize = q.size();
            while(qSize-- > 0) {

                cur = q.poll();
                x = cur[0]; y = cur[1];

                for(int d = 0; d < 4; ++d) {
                    nx = x + dx[d]; ny = y + dy[d];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny] || room[nx][ny] == 'X') continue;

                    if(room[nx][ny] == 'P') return false;

                    visit[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                }

            }
            if(++cnt == dist) break;
        }


        return true;
    }

    private char[][] makeRoom(String[] place) {

        char[][] room = new char[N][N];
        for(int i = 0; i < N; ++i) {
            room[i] = place[i].toCharArray();
        }
        return room;
    }
}
