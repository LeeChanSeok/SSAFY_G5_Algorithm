import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        // 내구도의 총합을 나타내는 리스트
        int[][] arr=new int[board.length+1][board[0].length+1];
        int[] op={0,-1,1};
        
        for(int i=0;i<skill.length;i++){
            int type=skill[i][0];
            int r1=skill[i][1];
            int c1=skill[i][2];
            int r2=skill[i][3];
            int c2=skill[i][4];
            int degree=skill[i][5];
            
            // 누적합 범위 설정
            arr[r1][c1]+=(degree*op[type]);
            arr[r1][c2+1]-=(degree*op[type]);
            arr[r2+1][c1]-=(degree*op[type]);
            arr[r2+1][c2+1]+=(degree*op[type]);
        }
         // 열 누적합 계산
        for(int i=0;i<arr.length;i++){
            for(int j=1;j<arr[i].length;j++){
                arr[i][j]+=arr[i][j-1];
            }
        }
        // 행 누적합 계산
        for(int j=0;j<arr[0].length;j++){
            for(int i=1;i<arr.length;i++){
                arr[i][j]+=arr[i-1][j];
            }
        }
        
        // arr와 board의 원소들간 합
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]+arr[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}
