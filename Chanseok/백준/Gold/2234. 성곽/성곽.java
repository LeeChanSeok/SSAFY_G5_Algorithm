import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static int N, M;
	static int roomCnt, maxArea;
	static int[][] arr, indexArr;

	// 최대 방의 개수 50*50 = 2500 개 + 10%
	static int maxRoom = 2525;

	// 각 방의 크기
	static int[] roomArea;
	// 각 방과 인접한 방
	static Set<Integer>[] adjRoomSet;
	
	// 이동방향과 벽 유무
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M][N];
		indexArr = new int[M][N];
		for(int y = 0; y < M; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < N; x++) {
				arr[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		roomArea = new int[maxRoom];
		adjRoomSet = new Set[maxRoom];
		for (int i = 0; i < maxRoom; i++) {
			adjRoomSet[i] = new HashSet<>();
		}

		roomCnt = 1;
		maxArea = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				bfs(x, y);
			}
		}

		int adjAreaMax = 0;
		for(int i = 2; i <roomCnt; i++) {
			for(int idx : adjRoomSet[i]) {
				adjAreaMax = Math.max(adjAreaMax, roomArea[i]+roomArea[idx]);
			}
		}
		
		System.out.println(--roomCnt + "\n" + maxArea + "\n" + adjAreaMax);
	}

	private static void bfs(int x, int y) {

		if (indexArr[y][x] != 0) return;
		
		Queue<Point> q = new ArrayDeque<>();

		indexArr[y][x] = roomCnt;
		q.offer(new Point(x, y));
		Point cur = null;

		int area = 1;
		while (!q.isEmpty()) {
			cur = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				// 이동할 수 없는 조건
				// 1. 경계밖
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				// 2. 이미 방문한 지점
				// 2-1. 인접한 다른 구역 저장				
				int idx = indexArr[ny][nx];
				if(idx != 0) {
					if(idx != roomCnt) {
						adjRoomSet[roomCnt].add(idx);
					}
					continue;
				}
				
				// 3. 벽이 있는 곳
				if ((arr[cur.y][cur.x] & (1 << d)) != 0) {
					continue;
				}
				
				indexArr[ny][nx] = roomCnt;
				q.offer(new Point(nx, ny));
				++area;
			}

		}
		// 현재 방의에 대한 넓이
		roomArea[roomCnt] = area;
		// 가장 큰 방의 크기
		maxArea = Math.max(maxArea, area);
		// 방의 수
		++roomCnt;
		
	}

}
