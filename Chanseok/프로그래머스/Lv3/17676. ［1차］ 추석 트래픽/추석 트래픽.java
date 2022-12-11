class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        int lineSize = lines.length;
        float[][] fLines = new float[lines.length+1][2];


        String[] str;
        for(int i = 1; i <= lineSize; ++i) {
            str = lines[i-1].split(" ");
            fLines[i][0] = time2sec(str[1]);
            fLines[i][1] = Float.parseFloat(str[2].substring(0, str[2].length()-1));
        }
        fLines[0][0] = fLines[1][0] - fLines[1][1];

        int cnt;
        float start, end;
        for(int i = 0; i <= lineSize; ++i) {
            if(lineSize - i < answer) break;

//				start = fLines[i][0] - fLines[i][1] + 0.001f;
            end = fLines[i][0] + 1;
            cnt = (i == 0) ? 0 : 1;
            for(int j = i+1; j <= lineSize; ++j) {
                if(fLines[j][0] - 3 > end) break;

                if(fLines[j][0] - fLines[j][1] + 0.001 <= end) ++cnt;
            }

            answer = Math.max(answer, cnt);
        }

        return answer;
    }

    static public float time2sec(String time) {
        String[] str = time.split(":");
        float sec = 0;
        sec += Float.parseFloat(str[0])*60*60 + Float.parseFloat(str[1])*60 + Float.parseFloat(str[2]);
        return sec;
    }
}
