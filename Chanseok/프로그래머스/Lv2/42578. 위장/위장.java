import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < clothes.length; ++i) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0)+1);
        }

        for(String key : map.keySet()) {
            answer *= (map.get(key)+1);
        }
        --answer;

        return answer;
    }
}
