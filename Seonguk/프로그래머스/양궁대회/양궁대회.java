import java.util.Arrays;

class Solution {
    static int[] ryan=new int[11]; static int[] answer=new int[11];;
    static int maxGap; static int N;
    
    public int[] solution(int n, int[] info) {
        N=n;
        dfs(info, 0, 0);
        
        if(maxGap == 0){    // 아파치가 이길 수 밖에 없는 상황
            int[] ans={-1};
            return ans;
        }
        else
            return answer;
    }
    
    public static void calScore(int[] apeach){
        
        int rySum=0; int apSum=0;
        // 아파치 라이언의 과녁 점수를 각각 계산
        for(int i=0;i<11;i++){
            if(apeach[i] == 0 && ryan[i] == 0)
                continue;
            if(apeach[i] >= ryan[i])
                apSum+=(10-i);
            else
                rySum+=(10-i);
        }
        // 점수차가 더 큰값으로 maxGap 초기화, answer 초기화
        if(rySum-apSum > maxGap){
            maxGap=rySum-apSum;
            answer=Arrays.copyOf(ryan, ryan.length);
        }
        // 점수차가 maxGap과 같은 경우, answer와 ryan의 과녁 점수를 비교
        else if(maxGap > 0 && rySum-apSum == maxGap){
            for(int i=10;i>-1;i--){
                if(ryan[i] < answer[i]) break; // answer의 우선순위가 큰 경우
                else if(ryan[i] > answer[i]){  // ryan의 우선순위가 큰 경우, answer 초기화
                    answer=Arrays.copyOf(ryan, ryan.length);
                    break;
                }
            }
        }
    }
    
    public static void dfs(int[] apeach, int idx, int cnt){
        if(cnt == N){
            calScore(apeach);
            return;
        }
        if(idx == -1 || cnt > N) return;
        
        for(int i=idx;i<11;i++){
            // 만약 마지막 과녁(0점)에서 cnt가 남은 경우, cnt로 초기화
            if(i == 10 && cnt < N){
                int temp=N-cnt;
                ryan[i]=temp;
                dfs(apeach, i+1, cnt+temp);
                ryan[i]=0;
                continue;
            }
            // 아파치보다 1점 높거나 아예 0점인 경우로 탐색
            ryan[i]=apeach[i]+1;
            dfs(apeach, i+1, cnt+ryan[i]);
            ryan[i]=0;
        }
        
    }
}
