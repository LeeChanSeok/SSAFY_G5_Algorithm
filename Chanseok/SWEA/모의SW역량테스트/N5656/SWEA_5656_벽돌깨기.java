package com.swea.모의SW역량테스트.N5656;

import java.io.*;
import java.util.*;

public class SWEA_5656_벽돌깨기 {

	static class Block {
		int x, y, num;

		public Block(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}

	}

	static int N, W, H;
	static char[][] map;
	static int[] perm;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int totalBlock;
	static int min;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/com/swea/모의SW역량테스트/N5656/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new char[H][W];
			for (int i = 0; i < H; i++)
				map[i] = br.readLine().replace(" ", "").toCharArray();

			totalBlock = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++)
					if (map[i][j] != '0')
						totalBlock++;
			}
			perm = new int[N];
			min = Integer.MAX_VALUE;
			permutation(0);
			sb.append("#" + tc + " " + min + "\n");
		} // test-case 종료

		bw.write(sb.toString());
		bw.close();
	}

	private static void permutation(int cnt) {
		if (cnt == N) {
			int res = totalBlock - burst(perm);
			min = Math.min(min, res);
			return;
		}

		for (int i = 0; i < W; i++) {
			perm[cnt] = i;
			permutation(cnt + 1);
		}

	}

	private static int burst(int[] perm) {

		int remove = 0;
		char[][] arr = copyArr(map);

		for (int c : perm) {
			// 1. 해당 열에서 가장 위에 있는 벽돌(행)을 찾는다.
			int r = 0;
			while (r < H && arr[r][c] == '0')
				r++;
			if (r == H)
				continue; // 해당 열에 블록이 없으므로 수행할 작업이 없다.

			// 2. 해당 위치에서 사방으로 해당 칸에 적힌 숫자만큼 이동하며 지나가는 다른 벽에 대한 정보를 queue에 저장한다
			Queue<Block> q = new ArrayDeque<>();
			q.add(new Block(r, c, arr[r][c] - '0'));
			remove++;
			arr[r][c] = '0';
			while (!q.isEmpty()) {
				Block cur = q.poll();
				for (int d = 0, dSize = dx.length; d < dSize; d++) {
					int length = cur.num;
					int nx = cur.x;
					int ny = cur.y;

					for (int k = 1; k < length; k++) {
						nx += dx[d];
						ny += dy[d];
						if (nx < 0 || nx >= H || ny < 0 || ny >= W || arr[nx][ny] == '0')
							continue;

						if (arr[nx][ny] != '1') {
							q.add(new Block(nx, ny, arr[nx][ny] - '0'));
						}
						arr[nx][ny] = '0';
						remove++;
					}

				}
			}

			// 3. 해당 작업이 끝난 후 벽돌을 아래로 이동시킨다.

			for (int j = 0; j < W; j++) {
				int i = H - 1;
				while (i >= 0 && arr[i][j] != '0')
					i--;
				if (i == -1)
					continue;

				int head = i;
				int tail = i - 1;
				while (head >= 0 && tail >= 0) {
					if (arr[tail][j] != '0') {
						char temp = arr[head][j];
						arr[head][j] = arr[tail][j];
						arr[tail][j] = temp;
						head--;
					}
					tail--;
				}
			}

		}

		return remove;
	}

	private static int restBuilding(char[][] burst) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static char[][] copyArr(char[][] map) {
		char[][] arr = new char[H][W];
		for (int i = 0; i < H; i++) {
			arr[i] = map[i].clone();
		}
		return arr;
	}
}
