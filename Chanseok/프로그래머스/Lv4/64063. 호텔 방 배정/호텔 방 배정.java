import java.util.*;

class Solution {
    Map<Long, Long> map;
    
    public long[] solution(long k, long[] room_number) {
        int len = room_number.length;
        long[] answer = new long[len];
        int idx = 0;

        map = new HashMap<>();
        long want, room, next;
        for(int i = 0; i < len; ++i) {
            want = room_number[i];

            room = find(want);
            answer[idx++] = room;
            map.put(room, room+1);

        }

        return answer;
    }
    private long find(long want) {
        long next = map.getOrDefault(want, 0l);
        if(next == 0l) {
            return want;
        }else {
            map.put(want, find(next));
            return map.get(want);
        }
    }
}
