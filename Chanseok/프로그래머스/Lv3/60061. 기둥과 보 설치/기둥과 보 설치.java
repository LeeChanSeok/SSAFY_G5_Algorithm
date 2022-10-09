import java.util.Comparator;
import java.util.PriorityQueue;	


class Solution {
    int N;
    public int[][] solution(int n, int[][] build_frame) {

        N = n;
        boolean[][][] board = new boolean[n+1][n+1][2];
        int x, y;
        for (int[] frame : build_frame) {
            x = frame[0];
            y = frame[1];
            // 기둥 삭제
            if (frame[2] == 0 && frame[3] == 0) {
                board[y][x][0] = false;
                if(!check_All(board, x, y)) board[y][x][0] = true;
            }
            // 기둥 설치
            else if (frame[2] == 0 && frame[3] == 1) {
                board[y][x][0] = true;
                if(!column_check(board, x, y)) board[y][x][0] = false;
            }
            // 보 삭제
            else if (frame[2] == 1 && frame[3] == 0) {
                board[y][x][1] = false;
                if(!check_All(board, x, y)) board[y][x][1] = true;
            }
            // 보 설치
            else if (frame[2] == 1 && frame[3] == 1) {
                board[y][x][1] = true;
                if(!beam_check(board, x, y)) board[y][x][1] = false;
            }
        }

        int[][] answer = Result(n, board);

        return answer;
    }

    private int[][] Result(int n, boolean[][][] board) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    if(o1[1] == o2[1]) return o1[2] - o2[2];
                    else return o1[1] - o2[1];
                }
                else return o1[0] - o2[0];
            }
        });


        for(int y = 0; y < n+1; ++y) {
            for(int x = 0; x < n+1; ++x) {
                for(int k = 0; k < 2; ++k) {
                    if(board[y][x][k]) pq.offer(new int[]{x, y, k});
                }
            }
        }

        int[][] answer = new int[pq.size()][3];

        int idx = 0;
        while(!pq.isEmpty()) {
            answer[idx++] =pq.poll();
        }

        return answer;
    }


    private boolean check_All(boolean[][][] board, int x, int y) {

        for(int i = 0; i < N+1; ++i) {
            for(int j = 0; j < N+1; ++j) {
                if(!column_check(board, i ,j)) return false; 
                if(!beam_check(board, i ,j)) return false;
            }
        }

        return true;

    }

    //기둥이 설치되어 있을 수 있는 상태인지 확인
    private boolean column_check(boolean[][][] board, int x, int y) {
        if(!board[y][x][0]) return true;

        // 바닥 위
        if(y == 0) return true;

        //보의 한쪽 끝 위
        if((x > 0 && board[y][x-1][1]) || board[y][x][1]) return true;

        // 다른 기둥 위
        if((y > 0 && board[y-1][x][0])) return true;

        return false;
    }

    //기둥이 설치되어 있을 수 있는 상태인지 확인
    private boolean beam_check(boolean[][][] board, int x, int y) {
        if(!board[y][x][1]) return true;

        // 한쪽 끝 부분이 기둥 위
        if(board[y-1][x][0] || board[y-1][x+1][0]) return true;

        // 양쪽 끝 부분이 다른 보와 동시에 연결
        if(x > 0 && x < N-1 && board[y][x-1][1] && board[y][x+1][1]) return true;

        return false;
    }

}
