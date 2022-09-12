/*
 채점을 시작합니다.
정확성  테스트
테스트 1 〉	통과 (0.02ms, 78.8MB)
테스트 2 〉	통과 (0.80ms, 77.9MB)
테스트 3 〉	통과 (0.48ms, 82MB)
테스트 4 〉	통과 (0.75ms, 80.2MB)
테스트 5 〉	통과 (17.57ms, 84MB)
테스트 6 〉	통과 (8.98ms, 83.4MB)
테스트 7 〉	통과 (13.65ms, 97.8MB)
테스트 8 〉	통과 (10.51ms, 88.2MB)
테스트 9 〉	통과 (6.33ms, 86.5MB)
테스트 10 〉	통과 (2.98ms, 94.3MB)
효율성  테스트
테스트 1 〉	실패 (시간 초과)
테스트 2 〉	실패 (시간 초과)
테스트 3 〉	실패 (시간 초과)
테스트 4 〉	실패 (시간 초과)
테스트 5 〉	실패 (시간 초과)
테스트 6 〉	실패 (시간 초과)
테스트 7 〉	실패 (시간 초과)
테스트 8 〉	실패 (시간 초과)
테스트 9 〉	실패 (시간 초과)
채점 결과
정확성: 25.0
효율성: 0.0
합계: 25.0 / 100.0 
 */

package com.programmers.kakaoInternship2022;

import java.util.Arrays;

public class PGS_kakao2022_Lv4_행렬과연산 {

	static class Solution {
		static int[] dr = {1, 0, -1, 0};
		static int[] dc = {0, 1, 0, -1};
		
		static public int[][] solution(int[][] rc, String[] operations) {
			int R = rc.length;
			int C = rc[0].length;

			for(String op : operations) {
				if(op.compareTo("Rotate") == 0) {
					int temp = rc[0][0];
					int cnt = (R + C - 2)*2;
					int r = 0, c = 0, dir = 0;
					int nr, nc;
					while(cnt-- > 0 ) {
						nr = r + dr[dir];
						nc = c + dc[dir];
						
						if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
							dir++;
							nr = r + dr[dir];
							nc = c + dc[dir];
						}
						
						rc[r][c] = rc[nr][nc];
						
						r = nr; c = nc;
					}
					rc[0][1] = temp;
					
				}else {//shift
					int[] temp = rc[R-1];
					for(int i = R-1; i > 0; i--)
						rc[i] = rc[i-1].clone();
					rc[0] = temp;
					
				}
			}
			
			return rc;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][][] rcs = { { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }, { { 8, 6, 3 }, { 3, 3, 7 }, { 8, 4, 9 } },
				{ { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } } };
		String[][] operationss = { { "Rotate", "ShiftRow" }, { "Rotate", "ShiftRow", "ShiftRow" },
				{ "ShiftRow", "Rotate", "ShiftRow", "Rotate" } };

		int[][][] result = { { { 8, 9, 6 }, { 4, 1, 2 }, { 7, 5, 3 } }, { { 8, 3, 3 }, { 4, 9, 7 }, { 3, 8, 6 } },
				{ { 1, 6, 7, 8 }, { 5, 9, 10, 4 }, { 2, 3, 12, 11 } } };

		for (int i = 0; i < rcs.length; i++) {
			printArr(Solution.solution(rcs[i], operationss[i]));
			System.out.println();
			printArr(result[i]);
			System.out.println();
			System.out.println();
		}

	}

	private static void printArr(int[][] arr) {
		for(int i = 0, r = arr.length; i < r; i++)
			System.out.println(Arrays.toString(arr[i]));

	}
	
	

}