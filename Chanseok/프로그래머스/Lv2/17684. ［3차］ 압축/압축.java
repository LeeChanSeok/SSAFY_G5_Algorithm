import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {

    public int[] solution(String msg) {

        Map<String, Integer>  LZW = new HashMap<>();
        for(int i = 1; i <= 26; ++i) {
            LZW.put(String.valueOf((char)('A' + (i-1))), i);
        }

        ArrayList<Integer> answer = new ArrayList<>();

        String w, c;
        int len = msg.length();

	        int idx = 0;
	        while(idx < len) {
	        	w = c =  "";
	        	while(idx < len) {
	        		c = String.valueOf(msg.charAt(idx++));
	        		if(!LZW.containsKey(w+c)) {
	        			LZW.put(w+c, LZW.size()+1);
	        			c = "";
	        			break;
	        		}
	        		w += c;
	        	}
	        	
	        	if(c != "") {
	        		answer.add(LZW.get(w));
	        		break;
	        	}
	        	
	        	answer.add(LZW.get(w));
	        	--idx;
	        }

        int[] res = new int[answer.size()];
        for(int i = 0; i < answer.size(); ++i) res[i] = answer.get(i);
        return res;
    }
}
