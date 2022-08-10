package com.baekjoon.G.G3.N1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Point2 {
	int x, y, cnt, k;

	public Point2(int x, int y, int cnt, int k) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.k = k;
	}

	public void setPoint(Point2 p) {
		this.x = p.x;
		this.y = p.y;
		this.cnt = p.cnt;
		this.k = p.k;
	}

}

public class BOJ_1600_G3_말이되고픈원숭이3 {

	// 말로 이동하는 경우
	static int[] hdx = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] hdy = { 2, 1, -1, -2, -2, -1, 1, 2 };

	// 일반적으로 이동하는 경우
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int min;
	static int W, H;

	static void bfs(int[][] map, int x, int y, int cnt, int K) {

		int[][][] cntMap = new int[H][W][2];	// 해당 좌표의 이동 횟수 및 남은 k값 저장
		for (int i = 0; i < H; i++)
			for (int j = 0; j < W; j++) {
				cntMap[i][j][0] = Integer.MAX_VALUE;
				cntMap[i][j][1] = -1;
			}

		Queue<Point2> q = new LinkedList<>();
		q.add(new Point2(x, y, 0, K));
		cntMap[x][y][0] = 0;
		cntMap[x][y][1] = K;

		while (!q.isEmpty()) {
			Point2 cur = q.poll();
			if (cur.cnt >= min)	// 현재 이동 거리가 최소 이동 거리보다 같거나 크면 판단 종료
				continue;
			if (cur.x == H - 1 && cur.y == W - 1) {	// 목적지에 도착한 경우
				min = Math.min(min, cur.cnt);
				continue;
			}

			int nx, ny;
			if (cur.k != 0) { // K가 남아 있는 경우, 말로 이동
				for (int d = 0; d < 8; d++) {
					nx = cur.x + hdx[d];
					ny = cur.y + hdy[d];

					if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == 1) // 범위를 벗어나거나 이동할 수 없는 지역 판단
						continue;
					if (cur.cnt + 1 >= cntMap[nx][ny][0] && cur.k - 1 <= cntMap[nx][ny][1]) //해당 위치에 현재 이동보다 이동 횟수도 적고 말의 이동도 크면 패스
						continue;

					cntMap[nx][ny][0] = cur.cnt + 1; // 해당 위치에서의 이동거리 저장
					cntMap[nx][ny][1] = cur.k - 1;	// 해당 위치에서의 남아있는 k값 저장
					q.add(new Point2(nx, ny, cur.cnt + 1, cur.k - 1));

				}
			}
			
			for (int d = 0; d < 4; d++) {	// 일반적인 이동
				nx = cur.x + dx[d];
				ny = cur.y + dy[d];

				if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == 1) // 범위를 벗어나거나 이동할 수 없는 지역 판단
					continue;
				if (cur.cnt + 1 >= cntMap[nx][ny][0] && cur.k <= cntMap[nx][ny][1])//해당 위치에 현재 이동보다 이동 횟수도 적고 말의 이동도 크면 패스
					continue;

				cntMap[nx][ny][0] = cur.cnt + 1;
				cntMap[nx][ny][1] = cur.k;
				q.add(new Point2(nx, ny, cur.cnt + 1, cur.k));
			}

		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		int[][] map = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		min = Integer.MAX_VALUE;

		bfs(map, 0, 0, 0, K);
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);

	}

}
