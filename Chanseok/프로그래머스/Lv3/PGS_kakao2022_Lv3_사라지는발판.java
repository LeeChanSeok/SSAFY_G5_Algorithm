package com.programmers.kakaoBlind2022;

public class PGS_kakao2022_Lv3_사라지는발판 {

	static class Solution {
		static public int solution(int[][] board, int[] aloc, int[] bloc) {
			int answer = -1;
			
			
			return answer;
		}
	}

	public static void main(String[] args) {
		int[][][] boards = { { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }, { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } },
				{ { 1, 1, 1, 1, 1 } }, { { 1 } } };

		int[][] alocs = { { 1, 0 }, { 1, 0 }, { 0, 0 }, { 0, 0 } };

		int[][] blocs = { { 1, 2 }, { 1, 2 }, { 0, 4 }, { 0, 0 } };

		int[] results = { 5, 4, 4, 0 };
		
		for(int i = 0, T = boards.length; i < T; i++) {
			System.out.println(Solution.solution(boards[i], alocs[i], blocs[i]));
			System.out.println(results[i]);
			System.out.println();
		}
	}

}
