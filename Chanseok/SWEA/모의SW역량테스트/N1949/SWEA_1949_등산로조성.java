package com.swea.모의SW역량테스트.N1949;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1949_등산로조성 {

	static class Peak {
		int x, y, k;

		public Peak(int x, int y, int k) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}

	static int N, K, max;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/com/swea/모의SW역량테스트/N1949/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			int height = 0;
			List<Peak> peaks = new ArrayList<>();	// 최대 정점에 대한 좌표 구하기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] >= height) {
						if (map[i][j] > height)
							peaks.clear();
						height = map[i][j];
						peaks.add(new Peak(i, j, K));
					}
				}
			} // 정보 입력 종료

			max = 0;
			int res = 0;
			for (Peak peak : peaks) {	// 각 정점에서 탐색 시작
				visit = new boolean[N][N];
				dfs(peak.x, peak.y, 1, true);
				res = Math.max(res, max);
			}
			sb.append("#" + tc + " " + res + "\n");

		} // test-case 종료

		bw.write(sb.toString());
		bw.close();
	}

	private static void dfs(int x, int y, int length, boolean isK) {
		visit[x][y] = true;	// 방문처리
		
		for (int d = 0, dSize = dx.length; d < dSize; d++) {	// 현재 위치에서 4방향 탐색
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny])	// 격자 밖과 방문 여부 확인
				continue;

			if (map[x][y] > map[nx][ny]) {	// 이동하려는 곳이 낮은 숫자이면 이동
				dfs(nx, ny, length + 1, isK);
				visit[nx][ny] = false;
			} else {
				if (isK && map[x][y] + K > map[nx][ny]) {	// 공사를 한 적이 없고 공사하여 이동할 수 있는지 판단
					int nextNum = map[nx][ny];
					for (int i = K; nextNum - i < map[x][y]; i--) {	// 가능한 공사 깊이에 대해서 반복 수행
						map[nx][ny] -= i;
						dfs(nx, ny, length + 1, false);
						map[nx][ny] += i;
						visit[nx][ny] = false;
					}
				}
			}
		}
		max = Math.max(max, length);	// 최대 이동 거리
	}

}
