/*
 * 정확성  테스트
테스트 1 〉	통과 (0.78ms, 76MB)
테스트 2 〉	통과 (0.90ms, 74.3MB)
테스트 3 〉	통과 (2.77ms, 74.8MB)
테스트 4 〉	통과 (8.39ms, 80MB)
테스트 5 〉	통과 (5.62ms, 79.5MB)
테스트 6 〉	통과 (7.42ms, 99MB)
테스트 7 〉	통과 (8.21ms, 92.6MB)
테스트 8 〉	통과 (16.24ms, 90MB)
테스트 9 〉	통과 (18.97ms, 85.2MB)
테스트 10 〉	통과 (13.22ms, 84.4MB)
테스트 11 〉	통과 (8.09ms, 87.9MB)
테스트 12 〉	통과 (6.14ms, 86.7MB)
테스트 13 〉	통과 (9.91ms, 91.2MB)
테스트 14 〉	통과 (12.52ms, 86.4MB)
테스트 15 〉	통과 (11.29ms, 83.6MB)
테스트 16 〉	통과 (8.01ms, 95.3MB)
테스트 17 〉	통과 (6.60ms, 76.4MB)
테스트 18 〉	통과 (9.61ms, 90.6MB)
효율성  테스트
테스트 1 〉	통과 (406.66ms, 146MB)
테스트 2 〉	통과 (325.31ms, 150MB)
테스트 3 〉	통과 (635.98ms, 147MB)
테스트 4 〉	통과 (565.94ms, 145MB)
채점 결과
정확성: 40.0
효율성: 60.0
합계: 100.0 / 100.0
 */
package com.programmers.kakaoBlind2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PGS_kakao2021_Lv2_순위검색2 {
	
	static class Solution {
		static int maxKey = 24;
		static List<Integer>[] list;
		static Map<Character, Integer>[] indexMap;
		static List<Integer> keyList;
		static int sizeArr[] = {3, 2, 2, 2};
	    static public int[] solution(String[] info, String[] query) {
	    	
	        int[] answer = new int[query.length];
	        
	        init();
	        
	        int key, k;
	        String[] input;
	        for(int i = 0, infoSize = info.length; i< infoSize; i++) {
	        	key = 0;
	        	input = info[i].split(" ");
	        	
	        	for(k = 0; k < 4; k++) {
	        		key = key<<1 | indexMap[k].get(input[k].charAt(0));
	        	}
	        	
	        	list[key].add(Integer.parseInt(input[k]));
	        }

	        for(int i = 0; i < maxKey; i++)
	        	Collections.sort(list[i]);
	        
	        int score, cnt, res, index;
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

	        		index = loBound(list[flag], score);
	        		cnt += (lsize - index);
	        	}
	        	answer[i] = cnt;
	        }
	        
	        
	        return answer;
	    }
		private static int loBound(List<Integer> list, int score) {
			
			int start = 0;
			int end = list.size();
			int mid = 0;
			
			while(start < end) {
				mid = (start + end)/2;
				if(list.get(mid) < score) {
					start = mid + 1;
				}else {
					end = mid;
				}
			}
						
			return start;
		
		}
		private static void dfs(int cnt, int flag, String[] input) {
			if(cnt == 4) {
				keyList.add(flag);
				return;
			}
			
			char key = input[cnt*2].charAt(0);
			if(key == '-'){
				for(int i = 0, size = sizeArr[cnt]; i < size; i++) {
					dfs(cnt + 1, flag<<1 | i, input);		
				}
			}
			else {
				dfs(cnt + 1, flag<<1 | indexMap[cnt].get(key), input);				
			}
			
			
		}
		private static void init() {
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

	public static void main(String[] args) {

		
		String[] info = {
				"java backend junior pizza 150",
				"python frontend senior chicken 210",
				"python frontend senior chicken 150",
				"cpp backend senior pizza 260",
				"java backend junior chicken 80",
				"python backend senior chicken 50"
		};
		
		String[] query = {
				"java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200",
				"cpp and - and senior and pizza 250",
				"- and backend and senior and - 150",
				"- and - and - and chicken 100",
				"- and - and - and - 150"
				
		};
		
		int[] result = {1, 1, 1, 1, 2 ,4};
		
		System.out.println(Arrays.toString(Solution.solution(info, query)));
		System.out.println(Arrays.toString(result));
				
	}

}
