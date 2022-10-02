class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        return unlock(key, lock);
    }

    private boolean unlock(int[][] key, int[][] lock) {
        int M = key.length;
        int N = lock.length;

        // key와 lock, answer의 각 행을 long 타입으려 변환
        long[] keyArr = initKey(N, M, key);
        long[] lockArr = getLock(N, M, lock);
        long[] answer = getAnswer(N, M);

        int length = N + 2 * (M - 1);
        long[] newKeyArr;
        int i;
        int d = 0;
        do {
            i = 0;
            do{
                newKeyArr = keyArr.clone();

                for(int j = 0; j < N + M - 1; j++) {
                    if(isOpen(N, M, newKeyArr, lockArr, answer)) return true;	// key와 자물쇠가 일치하는 경우
                    shiftLeft(N, M, newKeyArr);	// 일치하지 않는경우 왼쪽으로 이동
                }

                shiftDown(N, M, keyArr); // 키를 한칸 아래 행으로 이동
            }while(++i < N + M - 1);
            // key 회전
            key = rotate(M, key);
            keyArr = initKey(N, M, key);
        }while(++d < 4); // 시계방향 회전


        return false;
    }

    private int[][] rotate(int M, int[][] key) {
        int[][] newKey = new int[M][M];

        for(int i = 0; i < M; ++i) {
            for(int j = 0; j<M; ++j) {
                newKey[i][j] = key[M-j-1][i];
            }
        }

        return newKey;
    }

    private boolean isOpen(int N, int M, long[] newKeyArr, long[] lockArr, long[] answer) {
        // 자물쇠와 키가 일지하는지 확인
        int length = N + 2 * (M - 1);
        for(int i = M -1; i < N + M - 1; i++) {
            if(((newKeyArr[i]^lockArr[i]) & answer[i])!=answer[i]) return false; 
        }

        return true;
    }

    private void shiftLeft(int N, int M, long[] keyArr) {
        int length = N + 2 * (M - 1);
        for (int i = 0; i < length; ++i) {
            keyArr[i] = keyArr[i]>>1;
        }
    }

    private void shiftDown(int N, int M, long[] keyArr) {
        int length = N + 2 * (M - 1);
        for (int i = length-1; i > 0; --i) {
            keyArr[i] = keyArr[i-1];
        }
        keyArr[0] = 0;
    }

    private long[] initKey(int N, int M, int[][] key) {
        int length = N + 2 * (M - 1);
        long[] answer = new long[length];
        long temp;
        for (int i = 0; i < M; ++i) {

            temp = 0;
            int j = 0;
            for(; j < M; ++j) {
                temp = temp<<1 | key[i][j];
            }
            while(j++ < length) temp = temp<<1;
            answer[i] = temp;
        }

        return answer;
    }

    private long[] getLock(int N, int M, int[][] lock) {
        int length = N + 2 * (M - 1);
        long[] answer = new long[length];
        long temp;
        for (int i = M - 1; i < N + M - 1; ++i) {

            temp = 0;
            int j = M - 1;
            for(; j < N + M - 1; ++j) {
                temp = temp<<1 | lock[i-(M-1)][j-(M-1)];
            }
            while(j++ < length) temp = temp<<1;
            answer[i] = temp;
        }

        return answer;
    }

    private long[] getAnswer(int N, int M) {
        int length = N + 2 * (M - 1);
        long[] answer = new long[length];
        long temp;
        for (int i = M - 1; i < N + M - 1; ++i) {

            temp = 0;
            int j = M - 1;
            for(; j < N + M - 1; ++j) {
                temp = temp<<1 | 1;
            }
            while(j++ < length) temp = temp<<1;
            answer[i] = temp;
        }

        return answer;
    }
}
