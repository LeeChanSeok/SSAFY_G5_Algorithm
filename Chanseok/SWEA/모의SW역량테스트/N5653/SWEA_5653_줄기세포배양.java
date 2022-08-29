package com.swea.모의SW역량테스트.N5653;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5653_줄기세포배양 {

	static class Cell implements Comparable<Cell>{
		int x, y, k, life;
		boolean isActive;

		public Cell(int x, int y, int k, int life, boolean isActive) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.life = life;
			this.isActive = isActive;
		}

		@Override
		public int compareTo(Cell o) {	// 생명력 수치가 높은 줄기 세포를 최대로하는 max Heap
			return o.k - this.k;
		}
		
	}

	static int N, M, K;
	static boolean[][] visit;
	static Queue<Cell> q;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/com/swea/모의SW역량테스트/N5653/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			q = new ArrayDeque<>();

			visit = new boolean[N + K][M + K];	// k시간동안 최대 한방향으로 k/2반큼 이동 가능
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 0)	continue;

					visit[i + K / 2][j + K / 2] = true;		
					q.add(new Cell(i + K / 2, j + K / 2, num, num, false));
				}
			}

			// simulation
			PriorityQueue<Cell> pq;	//생명력 수치가 높은 줄기 세포를 우선으로 선택하기 위해서
			for (int t = 0; t < K; t++) {
				
				pq = new PriorityQueue<>();
				for (int s = 0, qSize = q.size(); s < qSize; s++) {
					Cell cur = q.poll();
					
					if(cur.isActive && cur.k == cur.life) {	// 활성화된 줄기 세포는 첫 1시간 동안 상, 하, 좌, 우 네 방향으로 동시에 번식
						for (int d = 0, dSize = dx.length; d < dSize; d++) {
							int nx = cur.x + dx[d];
							int ny = cur.y + dy[d];
							if (visit[nx][ny])	continue;
							pq.add(new Cell(nx, ny, cur.k, cur.k, false));
						}
					}
					
					--cur.life;
					if(cur.life == 0) {
						if(cur.isActive) continue;	// 활성 상태가 되면 X시간 동안 살아있을 수 있으며 X시간이 지나면 세포는 죽게 된다.
						else {
							cur.isActive = true;	// X시간 동안 비활성 상태이고 X시간이 지나는 순간 활성 상태
							cur.life = cur.k;							
						}
					}					
					q.add(cur);
					
				}
				
				//생명력 수치가 높은 줄기 세포가 해당 그리드 셀을 혼자서 차지
				while(!pq.isEmpty()) {
					Cell next = pq.poll();
					if (visit[next.x][next.y])	continue;
					visit[next.x][next.y] = true;
					q.add(new Cell(next.x, next.y, next.k, next.k, next.isActive));
				}
			}
			// end - simulation
			sb.append("#" + tc + " " + q.size() + "\n");

		} // test-case 종료

		bw.write(sb.toString());
		bw.close();

	}

}
