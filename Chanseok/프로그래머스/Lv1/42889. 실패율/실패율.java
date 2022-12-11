import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int stageL = stages.length;
        
        int[] fail = new int[N+2];
        int[] spp = new int[N+2];
        for(int i = 0; i < stageL; ++i){
            ++fail[stages[i]];
            ++spp[stages[i]];            
        }
        
        for(int i = N; i > 0; --i)
            spp[i] += spp[i+1];
            
        double[][] fail_rate = new double[N+1][2];
        fail_rate[0][0] = Integer.MAX_VALUE;
        fail_rate[0][1] = -1;
        for(int i = 1; i <= N; ++i){
            fail_rate[i][0] = i;
            if(spp[i] == 0) fail_rate[i][1] = 0;
            else fail_rate[i][1] = (double)fail[i]/spp[i];
        }
        
        Arrays.sort(fail_rate, (f1, f2) -> {
            if(Double.compare(f2[1], f1[1])==0)
                return Double.compare(f1[0], f2[0]);
            return Double.compare(f2[1], f1[1]);
        });
        
        for(int i = 0; i < N; ++i){
            answer[i] = (int)fail_rate[i][0];
            System.out.println(fail_rate[i][0] + ", " + fail_rate[i][1]);
        }
        
        return answer;
    }
}
