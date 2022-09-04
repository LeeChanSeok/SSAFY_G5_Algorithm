package com.programmers.kakaoInternship2022;

import java.util.Arrays;

public class PGS_kakao2022_Lv3_코딩테스트공부 {
	static class Solution {

		static class Point {
			int alp, cop;

			public Point(int alp, int cop) {
				super();
				this.alp = alp;
				this.cop = cop;
			}

		}

		static int min, pSize;
		static int alpMax, copMax;

		static public int solution(int alp, int cop, int[][] problems) {

			pSize = problems.length;
			maxPoint(problems);
			min = Math.max(0, alpMax - alp) + Math.max(0, copMax - cop);

			Arrays.sort(problems,
					(p1, p2) -> Double.compare(1.0 * (p2[2] + p2[3]) / p2[4], 1.0 * (p1[2] + p1[3]) / p1[4]));

			dfs(alp, cop, problems, 0);

			return min;
		}

		private static void maxPoint(int[][] problems) {

			alpMax = copMax = 0;
			for (int i = 0; i < pSize; i++) {
				alpMax = Math.max(alpMax, problems[i][0]);
				copMax = Math.max(copMax, problems[i][1]);
			}

		}

		private static void dfs(int alp, int cop, int[][] problems, int time) {

			if (time >= min) return;

			if (isAllSolve(alp, cop)) {
				min = time;
				return;
			}

			for (int i = 0; i < pSize; i++) {
				int alp_req = problems[i][0];
				int cop_req = problems[i][1];
				int alp_rwd = problems[i][2];
				int cop_rwd = problems[i][3];
				int cost = problems[i][4];

				if (alp >= alp_req && cop >= cop_req && (double) (alp_rwd + cop_rwd) / cost > 1) {
					dfs(alp + alp_rwd, cop + cop_rwd, problems, time + cost);
				} else {
					int alp_need = Math.max(0, alp_req - alp);
					int cop_need = Math.max(0, cop_req - cop);
					dfs(alp + alp_need, cop + cop_need, problems, time + (alp_need + cop_need));
				}
			}

		}

		private static boolean isAllSolve(int alp, int cop) {
			if (alp >= alpMax && cop >= copMax)
				return true;
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] alps = { 10, 0 };
		int[] cops = { 10, 0 };

		int[][][] problemss = { { { 10, 15, 2, 1, 2 }, { 20, 20, 3, 3, 4 }, },
				{ { 0, 0, 2, 1, 2 }, { 4, 5, 3, 1, 2 }, { 4, 11, 4, 0, 2 }, { 10, 4, 0, 4, 2 } } };

		int[] results = { 15, 13 };

		for (int i = 0; i < alps.length; i++) {
			System.out.println(Solution.solution(alps[i], cops[i], problemss[i]));
			System.out.println(results[i]);
			System.out.println();
		}

	}

}