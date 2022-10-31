import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    class Block{
    int x, y, dir;

        public Block(int x, int y, int dir) {
            super();
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

    }

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] board) {

        int answer = simulation(board);
        return answer;
    }

    private int simulation(int[][] board) {
        int N = board.length;
        boolean[][][] visit = new boolean[2][N][N];

        Queue<Block> q = new LinkedList<>();

        q.offer(new Block(0,0,0));
        visit[0][0][0] = true;

        int move = 0, qSize;
        int x, y,dir,  nx, ny, ndir;
        Block cur, next;
        while(!q.isEmpty()) {

            qSize = q.size();

            while(qSize-- > 0) {

                cur = q.poll();
                x = cur.x;
                y = cur.y;
                dir = cur.dir;

                if((dir == 0 && x == N - 1 && y == N - 2) ||
                        (dir == 1 && x == N - 2 && y == N - 1)) return move;

                // 가로 일때
                if(dir == 0) {
                    // 좌우 이동
                    for(int d = 2; d < 4; ++d) {
                        nx = x;
                        ny = y+dy[d];

                        if(ny < 0 || ny >= N-1 || visit[dir][nx][ny] || board[nx][ny] == 1 || board[nx][ny+1] == 1) continue;
                        visit[dir][nx][ny] = true;
                        q.offer(new Block(nx, ny, dir));
                    }

                    // 상하 이동
                    for(int d = 0; d < 2; ++d) {
                        nx = x + dx[d];
                        ny = y;

                        if(nx < 0 || nx >= N || visit[cur.dir][nx][ny] || board[nx][ny] == 1 || board[nx][ny+1] == 1) continue;
                        visit[dir][nx][ny] = true;
                        q.offer(new Block(nx, ny, dir));

                    }

                    // 회전
                    // 	왼쪽 축
                    // 		위 회전
                    ndir = dir^1;
                    if(x > 0 && !visit[ndir][x-1][y] && board[x-1][y] == 0 && board[x-1][y+1] ==0) {
                        visit[ndir][x-1][y] = true;
                        q.offer(new Block(x-1, y, ndir));
                    }
                    //		아래 회전
                    if(x < N-1 && !visit[ndir][x][y] && board[x+1][y] == 0 && board[x+1][y+1] ==0) {
                        visit[ndir][x][y] = true;
                        q.offer(new Block(x, y, ndir));
                    }

                    // 	오른쪽의 축
                    // 		위 회전
                    if(x > 0 && !visit[ndir][x-1][y+1] && board[x-1][y] == 0 && board[x-1][y+1] ==0) {
                        visit[ndir][x-1][y+1] = true;
                        q.offer(new Block(x-1, y+1, ndir));
                    }
                    //		아래 회전
                    if(x < N-1 && !visit[ndir][x][y+1] && board[x+1][y] == 0 && board[x+1][y+1] ==0) {
                        visit[ndir][x][y+1] = true;
                        q.offer(new Block(x, y+1, ndir));
                    }

                }else { // 세로일때
                    // 상하 이동
                    for(int d = 0; d < 2; ++d) {
                        nx = x + dx[d];
                        ny = y;

                        if(nx < 0 || nx >= N - 1 || visit[dir][nx][ny] || board[nx][ny] == 1 || board[nx+1][ny] == 1) continue;
                        visit[dir][nx][ny] = true;
                        q.offer(new Block(nx, ny, dir));
                    }

                    // 좌우 이동
                    for(int d = 2; d < 4; ++d) {
                        nx = x;
                        ny = y + dy[d];

                        if(ny < 0 || ny >= N || visit[dir][nx][ny] || board[nx][ny] == 1 || board[nx+1][ny] == 1) continue;
                        visit[dir][nx][ny] = true;
                        q.offer(new Block(nx, ny, dir));

                    }

                    // 회전
                    // 	위의 축
                    // 		왼쪽 회전
                    ndir = dir^1;
                    if(y > 0 && !visit[ndir][x][y-1] && board[x][y-1] == 0 && board[x+1][y-1] ==0) {
                        visit[ndir][x][y-1] = true;
                        q.offer(new Block(x, y-1, ndir));
                    }
                    // 		오른쪽 회전
                    if(y < N-1 && !visit[ndir][x][y] && board[x][y+1] == 0 && board[x+1][y+1] ==0) {
                        visit[ndir][x][y] = true;
                        q.offer(new Block(x, y,ndir));
                    }

                    // 	아래 축
                    // 		왼쪽 회전
                    if(y > 0 && !visit[ndir][x+1][y-1] && board[x][y-1] == 0 && board[x+1][y-1] ==0) {
                        visit[ndir][x+1][y-1] = true;
                        q.offer(new Block(x+1, y-1, ndir));
                    }
                    // 		오른쪽 회전
                    if(y < N-1 && !visit[ndir][x+1][y] && board[x][y+1] == 0 && board[x+1][y+1] ==0) {
                        visit[ndir][x+1][y] = true;
                        q.offer(new Block(x+1, y, ndir));
                    }

                }
            }
            ++move;
        }

        return move;
    }
}
