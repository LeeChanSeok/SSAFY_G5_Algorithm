class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;

        char[][] boards = new char[m][n];
        for(int i = 0; i < m; ++i)
            boards[i] = board[i].toCharArray();

        int removeBlock;	        
        while(true) {

            // 1. 블록 제거하기
            removeBlock = removeBlock(m, n, boards);
            if(removeBlock == 0) break;
            answer += removeBlock;

            // 2. 블록 이동하기
            moveBoard(m, n, boards);

        }
        return answer;
    }

    private static int removeBlock(int m, int n, char[][] boards) {

        char c;
        int removedCnt = 0;
        boolean[][] isRemoved = new boolean[m][n];
        for(int i = 0; i < m-1; ++i) {
            for(int j = 0; j < n-1; ++j) {
                c = boards[i][j];
                if(c == ' ') continue;
                if(boards[i][j+1] == c && boards[i+1][j] == c && boards[i+1][j+1] == c) {
                    removedCnt += checkRemove(boards, i, j, isRemoved);
                }
            }
        }

        remove(m, n, boards, isRemoved);

        return removedCnt;
    }

    private static void remove(int m, int n, char[][] boards, boolean[][] isRemoved) {
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(isRemoved[i][j]) boards[i][j] = ' ';
            }

        }

    }

    private static int checkRemove(char[][] boards, int i, int j, boolean[][] isRemoved) {
        int cnt = 0;
        if(!isRemoved[i][j]) {
            isRemoved[i][j] = true;
            ++cnt;
        }
        if(!isRemoved[i][j+1]) {
            isRemoved[i][j+1] = true;
            ++cnt;
        }
        if(!isRemoved[i+1][j]) {
            isRemoved[i+1][j] = true;
            ++cnt;
        }
        if(!isRemoved[i+1][j+1]) {
            isRemoved[i+1][j+1] = true;
            ++cnt;
        }


        return cnt;
    }

    private static void moveBoard(int m , int n, char[][] boards) {

        int i, k;
        for(int j = 0; j < n; ++j) {
            i = m-1;
            while(i > 0) {

                while(i > 0 && boards[i][j] != ' ') --i;
                if(i == 0) break;

                k = i-1;
                while( k >= 0 && boards[k][j] == ' ') --k;
                if(k < 0) break;

                swap(boards, j, i, k);
                --i;
            }

        }
    }

    private static void swap(char[][] boards, int j, int i, int k) {

        char temp = boards[i][j];
        boards[i][j] = boards[k][j];
        boards[k][j] = temp;
    }
}
