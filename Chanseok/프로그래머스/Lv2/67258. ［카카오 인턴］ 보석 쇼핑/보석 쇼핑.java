import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};

        // 1. 보석의 종류를 구한다. Set에 입력
        Set<String> gemsSet = new HashSet<>();
        Map<String, Integer> gemsMap= new HashMap<>();

        int gemsSize = gems.length;
        for(int i = 0; i < gemsSize; ++i) {
            gemsSet.add(gems[i]);
            gemsMap.put(gems[i], 0);
        }

        int gemkinds = gemsSet.size();
        int curSize = 0;

        String gem;
        int[] ans = {0, Integer.MAX_VALUE};
        int s = 0, e = 0;
        int cnt;
        gemsMap.put(gems[0], 1);
        curSize = 1;
        while(s <= e){
            if(curSize == gemkinds){
                if((e-s) < (ans[1] - ans[0])) ans = new int[]{s+1, e+1};
                gem = gems[s++];
                cnt = gemsMap.get(gem);
                if(cnt == 1) --curSize;
                gemsMap.put(gem, cnt - 1);

            }else{
                if(++e >= gemsSize) break;
                gem = gems[e];

                cnt = gemsMap.get(gem);
                if(cnt == 0) ++curSize;
                gemsMap.put(gem,cnt+1);

            }
        }

        return ans;
    }
}
