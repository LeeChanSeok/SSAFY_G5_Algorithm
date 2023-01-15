import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};

        List<List<Integer>> list = new LinkedList<>();

        int sLen = s.length();
        String[] numStr = s.substring(2, sLen-2).split("},\\{");

        int n = numStr.length;
        String[] nums;

        for(int i = 0; i < n; ++i){
            nums = numStr[i].split(",");

            List<Integer> subset = new LinkedList<>();
            for(int j = 0, numLen = nums.length; j < numLen; ++j){
                subset.add(Integer.parseInt(nums[j]));
            }
            list.add(subset);
        }

        list.sort((l1, l2) -> l1.size() - l2.size());

        Set<Integer> numSet = new HashSet<>();
        answer = new int[n];
        int idx = 0;
        for(List<Integer> li : list){
            for(int num : li){
                if(!numSet.contains(num)){
                    answer[idx++] = num;
                    numSet.add(num);
                    break;
                }
            }
        }

        return answer;
    }
}
