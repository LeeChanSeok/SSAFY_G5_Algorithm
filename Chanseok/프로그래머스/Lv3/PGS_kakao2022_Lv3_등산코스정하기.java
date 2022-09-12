/*
 테스트 1 〉	통과 (0.91ms, 75.8MB)
테스트 2 〉	통과 (0.63ms, 74.6MB)
테스트 3 〉	통과 (0.57ms, 72.8MB)
테스트 4 〉	통과 (0.64ms, 73.8MB)
테스트 5 〉	통과 (0.62ms, 76.8MB)
테스트 6 〉	통과 (0.70ms, 78.6MB)
테스트 7 〉	통과 (0.82ms, 74.7MB)
테스트 8 〉	통과 (0.68ms, 78MB)
테스트 9 〉	통과 (0.91ms, 73.8MB)
테스트 10 〉	통과 (1.75ms, 75.1MB)
테스트 11 〉	통과 (1.54ms, 78.5MB)
테스트 12 〉	통과 (1.21ms, 76.1MB)
테스트 13 〉	통과 (10.15ms, 82.9MB)
테스트 14 〉	통과 (16.34ms, 100MB)
테스트 15 〉	통과 (47.23ms, 152MB)
테스트 16 〉	통과 (62.79ms, 152MB)
테스트 17 〉	통과 (60.49ms, 167MB)
테스트 18 〉	통과 (73.32ms, 103MB)
테스트 19 〉	실패 (시간 초과)
테스트 20 〉	통과 (104.14ms, 149MB)
테스트 21 〉	실패 (시간 초과)
테스트 22 〉	통과 (56.82ms, 118MB)
테스트 23 〉	통과 (124.60ms, 137MB)
테스트 24 〉	통과 (280.60ms, 325MB)
테스트 25 〉	통과 (531.72ms, 528MB)
채점 결과
정확성: 90.3
합계: 90.3 / 100.0
 * 
 */

package com.programmers.kakaoInternship2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PGS_kakao2022_Lv3_등산코스정하기 {

	static class Solution {

		static class Edge {
			int to, weight;

			public Edge(int to, int weight) {
				super();
				this.to = to;
				this.weight = weight;
			}
		}

		static int min, minSummits, minIntensity;
		static List<Edge>[] edges;
		static int[] sGates, sSummits;
		static boolean[] visit;
		static boolean isUpdage;

		static public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
			int[] answer = {};

			// 두 지점 사이의 연결성 및 길이를 나타내기 위한 리스트
			edges = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++)
				edges[i] = new ArrayList<>();

			for (int i = 0, pSize = paths.length; i < pSize; i++) {
				int from = paths[i][0];
				int to = paths[i][1];
				int weight = paths[i][2];

				edges[from].add(new Edge(to, weight));
				edges[to].add(new Edge(from, weight));

				minIntensity = Math.min(minIntensity, weight);
			}

			Arrays.sort(summits);
			min = Integer.MAX_VALUE;
			sGates = gates;
			sSummits = summits;
			int minSummits = 0;
			
			// 가장 낮은 산봉우리부터 시작하므로서 같은 산봉우리에 대한 조건 제거
			for (int summit : summits) {

				visit = new boolean[n + 1]; // 방문 여부 체크
				isUpdage = false; // Intensity값을 업데이트 한 경우, summit값을 변경시켜 주기 위해
				visit[summit] = true;
				dfs(summit, 0);
				// min값이 변경된 경우, summit값 업데이트
				if (isUpdage) {
					minSummits = summit;
					if (min == minIntensity)
						break;
				}
				visit[summit] = false;
			}

			answer = new int[] { minSummits, min };
			return answer;
		}

		private static void dfs(int node, int intensity) {

			if (intensity >= min) return;

			// 게이트에 도착한 경우
			if (isGate(node)) {	
				isUpdage = true;
				min = intensity;
				return;
			}

			for (Edge edge : edges[node]) {
				if (!visit[edge.to] && !isSummit(edge.to)) {
					visit[edge.to] = true;
					dfs(edge.to, Math.max(intensity, edge.weight));
					visit[edge.to] = false;
				}
			}

		}

		private static boolean isSummit(int node) {
			for (int summit : sSummits) {
				if (summit == node)	return true;
			}
			return false;
		}

		private static boolean isGate(int node) {
			for (int gate : sGates) {
				if (gate == node) return true;
			}
			return false;
		}
	}

	public static void main(String[] args) {
		int[] ns = { 6, 7, 7, 5 };
		int[][][] pathss = {
				{ { 1, 2, 3 }, { 2, 3, 5 }, { 2, 4, 2 }, { 2, 5, 4 }, { 3, 4, 4 }, { 4, 5, 3 }, { 4, 6, 1 },
						{ 5, 6, 1 } },
				{ { 1, 4, 4 }, { 1, 6, 1 }, { 1, 7, 3 }, { 2, 5, 2 }, { 3, 7, 4 }, { 5, 6, 6 } },
				{ { 1, 2, 5 }, { 1, 4, 1 }, { 2, 3, 1 }, { 2, 6, 7 }, { 4, 5, 1 }, { 5, 6, 1 }, { 6, 7, 1 } },
				{ { 1, 3, 10 }, { 1, 4, 20 }, { 2, 3, 4 }, { 2, 4, 6 }, { 3, 5, 20 }, { 4, 5, 6 } } };

		int[][] gatess = { { 1, 3 }, { 1 }, { 3, 7 }, { 1, 2 } };

		int[][] summitss = { { 5 }, { 2, 3, 4 }, { 1, 5 }, { 5 } };

		int[][] results = { { 5, 3 }, { 3, 4 }, { 5, 1 }, { 5, 6 } };

		for (int i = 0, tc = ns.length; i < tc; i++) {
			System.out.println(Arrays.toString(Solution.solution(ns[i], pathss[i], gatess[i], summitss[i])));
			System.out.println(Arrays.toString(results[i]));
			System.out.println();
		}
	}

}
