import java.util.Arrays;

class Solution {
    int max, N;
	int[] max_arr;
	int[] score;
    
    public void subSet(int n, int[] info, int cnt, int apeach, int ryan) {
		
		if (cnt == N-1) {
			score[cnt] = n;
			int diff = ryan - apeach;
			if(diff > max) {
				max = diff;
				max_arr = Arrays.copyOf(score, score.length);
			}else if (diff == max){
				for(int i = 10; i >=0; i--) {
					if(max_arr[i] == score[i]) continue;
					else if(max_arr[i] < score[i]) 
						max_arr = Arrays.copyOf(score, score.length);
					
					break;
				}
			}
			return;
		}
		
		// 1. 점수를 획득한다.
		if(info[cnt] != 0) { // 어피치가 쏜 화살의 수가 0이 아닐 때,
			if(info[cnt] < n) { // 라이언의 화살이 어피치가 쏜 화살보다 많을 때
				score[cnt] = info[cnt]+1;
				subSet(n-score[cnt], info, cnt + 1, apeach, ryan + 10 - cnt);
			}
		}
		else {	// 어피치가 쏜 화살의 수가 0일 때,
			if(n > 0) { // 라이언이 쏠 화살이 있을 때
				score[cnt] = 1;
				subSet(n-score[cnt], info, cnt + 1, apeach, ryan + 10 - cnt);
			}
		}
		score[cnt] = 0;
		// 2. 점수를 획득하지 않는다.
		if(info[cnt] == 0)
			subSet(n, info, cnt + 1, apeach, ryan);
		else
			subSet(n, info, cnt + 1, apeach + 10 - cnt, ryan);
	}
    
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        
        		N = 11;
		max = 0;
		max_arr = new int[11];
		score = new int[11];
		
		subSet(n, info, 0, 0, 0);
		if(max == 0)
			answer = new int[]{-1};
		else
			answer = Arrays.copyOf(max_arr, max_arr.length);
        
        return answer;
    }
}