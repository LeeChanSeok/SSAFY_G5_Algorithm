import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        Map<String, Integer> str1Map = new HashMap<>();
        Map<String, Integer> str2Map = new HashMap<>();
        Set<String> set = new HashSet<>();

        makeGroup(str1, str1Map, set);
        makeGroup(str2, str2Map, set);

        if(str1Map.size() == 0 && str2Map.size() == 0 ) return 65536;

        int inter = 0, union = 0;


        for(String key : str1Map.keySet()) {
            if(str2Map.containsKey(key)) {
                inter += Math.min(str1Map.get(key), str2Map.get(key));
            }	        	
        }

        for(String str : set) {
            union += Math.max(str1Map.getOrDefault(str, 0), str2Map.getOrDefault(str, 0));

        }

        answer = (int)(((double)inter/union)*65536);
        return answer;
    }

    private void makeGroup(String str, Map<String, Integer> strMap, Set<String> set) {

        char c1, c2;
        String sub;
        for(int i = 0, len = str.length(); i < len-1; ++i) {
            c1 = str.charAt(i); c2 = str.charAt(i+1);
            if(isAlpha(c1) && isAlpha(c2)) {
                sub = String.valueOf(c1) + String.valueOf(c2);
                strMap.put(sub, strMap.getOrDefault(sub, 0)+1);
                set.add(sub);
            }
        }

    }

    private boolean isAlpha(char c) {
        if( c >= 'a' && c <= 'z') return true;
        return false;
    }
}
