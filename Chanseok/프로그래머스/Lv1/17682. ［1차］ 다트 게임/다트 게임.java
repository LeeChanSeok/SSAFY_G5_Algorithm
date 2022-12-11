class Solution {
    public int solution(String dartResult) {
        int answer = 0;

        int[] scores = new int[3];
        int idx = 0;
        char c;
        int num = 0;
        int i = 0;
        int len = dartResult.length();
        while(i < len) {
            c = dartResult.charAt(i);

            // 점수
            if ( c >= '0' && c <='9') {
                num = c - '0';
                if(c == '1' && dartResult.charAt(i+1) == '0') {
                    num = 10;
                    ++i;
                }
                ++i;
            }
            scores[idx] = num;

            // 보너스
            c = dartResult.charAt(i);
            if(c == 'S') {

            }else if(c == 'D') {
                scores[idx] *= scores[idx];
            }else if(c == 'T') {
                scores[idx] = scores[idx] * scores[idx] * scores[idx];
            }

            ++i;

            if(i >=len) break;
            // 옵션
            c = dartResult.charAt(i);
            if(c == '*') {
                if(idx != 0) scores[idx-1] *= 2;
                scores[idx] *=2;

            }else if(c == '#') {
                scores[idx] *= -1;
            }else {
                --i;
            }

            ++i;
            ++idx;

        }

        answer = scores[0] + scores[1] + scores[2];

        return answer;
    }
}
