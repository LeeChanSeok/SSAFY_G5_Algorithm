package com.programmers.kakaoBlind2022;

public class PGS_kakao2022_Lv3_파괴되지않은건물 {

	static class Solution {
		static public int solution(int[][] board, int[][] skill) {
			int answer = 0;
			int N = board.length;
			int M = board[0].length;
			
			int type, r1, c1, r2, c2, degree;
			for (int n = 0, sCnt = skill.length; n < sCnt; n++) {
				type = skill[n][0] == 1 ? -1 : 1;
				
				r1 = skill[n][1];
				c1 = skill[n][2];
				r2 = skill[n][3];
				c2 = skill[n][4];
				degree = skill[n][5];

				for(int i = r1; i <= r2; i++) {
					for(int j = c1; j <= c2; j++)
						board[i][j] += degree * type;
				}
			}

			for(int i = 0; i < N; i++)
				for(int j = 0; j < M; j++)
					if(board[i][j]>0) answer++;
			
			
			return answer;
		}
	}

	public static void main(String[] args) {
		int[][][] boards = {
				{ { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 } },
				{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } } };

		int[][][] skills = { { { 1, 0, 0, 3, 4, 4 }, { 1, 2, 0, 2, 3, 2 }, { 2, 1, 0, 3, 1, 2 }, { 1, 0, 1, 3, 3, 1 } },
				{ { 1, 1, 1, 2, 2, 4 }, { 1, 0, 0, 1, 1, 2 }, { 2, 2, 0, 2, 0, 100 } } };

		int[] results = { 10, 6 };

		for (int i = 0, T = boards.length; i < T; i++) {
			System.out.println(Solution.solution(boards[i], skills[i]));
			System.out.println(results[i]);
			System.out.println();
		}
	}

}
