import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";

	        List<Character> list = new ArrayList<>(t*m + n);
	        list.add('0');
	        
	        int maxLen = (t-1)*m + p;
	        int num = 1;
	        while(true) {
	        	nDigit(n, list,num);
	        	if(list.size() >= maxLen) break;
	        	++num;
	        }
	        
	        for(int i = 0; i < t; ++i) {
	        	answer += list.get(m*i + p-1);
	        }


        return answer;
    }

    private void nDigit(int n, List<Character> list, int num) {

        List<Character> ndigit = new ArrayList<>();
        int mod;
        while(num != 0) {
            mod = num % n;

            if(mod >= 10) {
                ndigit.add((char)('A' + mod - 10));
            }
            else {
                ndigit.add((char)(mod + '0'));
            }

            num /= n;
        }

        Collections.reverse(ndigit);
        for(char c : ndigit) list.add(c);

    }
}
