import java.util.*;

class Solution {
    int banned_id_len;
    List<String>[] list;
    Map<String, Integer> id_Idx;
    Set<Integer> res;

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        int user_id_len = user_id.length;
        banned_id_len = banned_id.length;

        id_Idx = new HashMap<>();
        for(int i = 0; i < user_id_len; ++i) id_Idx.put(user_id[i], i);

        list = new List[banned_id_len];
        for(int i = 0; i < banned_id_len; ++i) list[i] = new LinkedList<String>();

        for(String user : user_id) {
            int user_len = user.length();

            for(int i = 0; i < banned_id_len; ++i) {
                String banned = banned_id[i];
                int banned_len = banned.length();
                if(user_len != banned_len) continue;

                if(isMatch(user, banned)) list[i].add(user);
            }
        }

        res = new HashSet<>();
        combination(0, 0);
        answer = res.size();

        return answer;
    }

    private void combination(int idx, int flag) {

        if(idx == banned_id_len) {
            res.add(flag);
            return;
        }

        for(String user_id : list[idx]) {
            int userIdx = id_Idx.get(user_id);
            if((flag & (1 << userIdx)) != 0) continue;

            combination(idx+1, flag | (1<<userIdx));
        }

    }

    private boolean isMatch(String user, String banned) {

        int len = user.length();
        for(int i = 0; i < len; ++i) {
            if((user.charAt(i) != banned.charAt(i)) && (banned.charAt(i) != '*') ) return false;
        }
        return true;
    }

}
