import java.util.Arrays;

class Solution {
    public int solution(String[] words) {
        int answer = 0;

        Arrays.sort(words);

        int wordsLen = words.length;
        String prev, cur, next;
        int prevCnt, nextCnt;
        for(int i = 0; i < wordsLen; ++i) {

            cur = words[i];

            prevCnt = 0;
            if(i != 0) {
                prev = words[i-1];
                for(int j = 0; j < prev.length(); ++j) {
                    if(cur.charAt(j) != prev.charAt(j)) {
                        prevCnt = j+1;
                        break;
                    }
                }
                if(prevCnt == 0) prevCnt = prev.length()+1;
            }
            nextCnt = 0;
            if(i != wordsLen-1) {
                next = words[i+1];
                for(int j = 0; j < cur.length(); ++j) {
                    if(cur.charAt(j) != next.charAt(j)) {
                        nextCnt = j+1;
                        break;
                    }
                }
                if(nextCnt == 0) nextCnt = cur.length();
            }

            answer += Math.max(prevCnt, nextCnt);
        }

        return answer;
    }
}
