import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        int[] iTimetable = new int[timetable.length];
        for(int i = 0; i < timetable.length; ++i) {
            iTimetable[i] = time2sec(timetable[i]);
        }
        Arrays.sort(iTimetable);

        List<Integer>[] list = new List[n];
        for(int i = 0; i < n; ++i) {
            list[i] = new ArrayList<Integer>(m);
        }

        int start = time2sec("09:00");
        int idx = 0;
        for(int time : iTimetable) {
            while(start + idx * t < time) ++idx;
            if(idx >= n) break;

            if(list[idx].size() >= m) ++idx;
            if(idx >= n) break;

            list[idx].add(time);
        }

        int departTime = start + (n-1) * t;
        if(list[n-1].size() < m) answer = sec2time(departTime);
        else answer = sec2time(list[n-1].get(m-1)-1);
        
        return answer;
    }
    
    public int time2sec(String time) {
        String[] HM= time.split(":");
        return Integer.parseInt(HM[0]) * 60 + Integer.parseInt(HM[1]);
    }

    public String sec2time(int time) {
        int H = time/60;
        int M = time%60;
        String t = "";
        t = H < 10 ? "0" + H : H + "";
        t += ":";
        t += M < 10 ? "0" + M : M + "";
        return t;
    }
}

