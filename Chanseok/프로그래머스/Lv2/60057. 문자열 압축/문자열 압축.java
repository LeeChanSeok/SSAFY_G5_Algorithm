class Solution {
    public int solution(String s) {
        int min = s.length();
        int sLength = s.length();
        int minBound;
        int totalL, cnt;
        String temp, subS;

        // 문자열을 자를 단위
        for(int unit = 1, half = sLength/2; unit <= half; ++unit) {

            subS = s.substring(0, unit);
            cnt = 1; totalL = 0;
            for(int i = 1, bound = sLength/unit; i < bound; ++i) {
                temp = s.substring(i*unit, i*unit + unit);
                if(subS.equals(temp)) ++cnt;
                else {
                    if(cnt == 1) totalL += unit;
	        		else totalL += unit + calcDigit(cnt);

                    subS = temp;
                    cnt = 1;
                }
            }

            if(cnt == 1) totalL += unit;
            else totalL += unit + calcDigit(cnt);

            totalL += sLength % unit;

            min = Math.min(min, totalL);

        }

        return min;
    }
    
    int calcDigit(int cnt) {
        int digit = 1;
        while((cnt /= 10) != 0) ++digit;

        return digit;
    }
}
