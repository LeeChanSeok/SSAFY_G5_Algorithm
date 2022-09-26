import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    int maxKey = 24;
    List<Integer>[] list;
    Map<Character, Integer>[] indexMap;
    List<Integer> keyList;
    int sizeArr[] = {3, 2, 2, 2};
    public int[] solution(String[] info, String[] query) {

        int[] answer = new int[query.length];

        init();

        int key, idx, k;
        String[] input;
        for(int i = 0, infoSize = info.length; i< infoSize; i++) {
            key = idx = 0;
            input = info[i].split(" ");

            for(k = 0; k < 4; k++) {
                key = key<<1 | indexMap[k].get(input[k].charAt(0));
            }

            list[key].add(Integer.parseInt(input[k]));
        }

        for(int i = 0; i < maxKey; i++)
            Collections.sort(list[i]);

        int score, cnt, res;
        keyList = new ArrayList<>();
        for(int i = 0, querySize = query.length; i < querySize; i++) {
            keyList.clear();
            input = query[i].split(" ");
            dfs(0, 0, input);
            score = Integer.parseInt(input[7]);
            cnt = 0;
            for(int flag : keyList) {
	        		int lsize = list[flag].size();
	        		if(lsize == 0) continue;
	        		int j;
	        		for(j = 0; j < lsize; j++) {
	        			if(list[flag].get(j) >= score) break;
	        		}
	        		cnt += lsize - j;
            }
            answer[i] = cnt;
        }


        return answer;
    }
    private void dfs(int cnt, int flag, String[] input) {
        if(cnt == 4) {
            keyList.add(flag);
            return;
        }

        char key = input[cnt*2].charAt(0);
        if(indexMap[cnt].containsKey(key)) {
            dfs(cnt + 1, flag<<1 | indexMap[cnt].get(key), input);				
        }
        else {
            for(int i = 0, size = sizeArr[cnt]; i < size; i++) {
                dfs(cnt + 1, flag<<1 | i, input);		
            }
        }


    }
    private void init() {
        list = new ArrayList[maxKey];

        for(int i = 0 ; i <maxKey; i++)
            list[i] = new ArrayList<>();

        indexMap = new HashMap[4];
        for(int i =0; i < 4; i++)
            indexMap[i] = new HashMap<>();

        indexMap[0].put('c', 0); indexMap[0].put('j', 1); indexMap[0].put('p', 2);
        indexMap[1].put('b', 0); indexMap[1].put('f', 1);
        indexMap[2].put('j', 0); indexMap[2].put('s', 1);
        indexMap[3].put('c', 0); indexMap[3].put('p', 1);

    }
}
