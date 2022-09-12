import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

	static class Cow {
		int id;
		int x, y;

		public Cow(int id, int x, int y) {
			super();
			this.id = id;
			this.x = x;
			this.y = y;
		}

	}

	static int N, K, R;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] cowArr;
	static List<Point>[][] pathArr;
	static Cow[] cows;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		// 해당 좌표와 길이 있는 좌표를 List에 저장
		// 길의 연결성을 저장하기 위해 2차원 배열의 List에 저장
		pathArr = new List[N][N];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());

			int r1 = Integer.parseInt(st.nextToken()) - 1;
			int c1 = Integer.parseInt(st.nextToken()) - 1;
			int r2 = Integer.parseInt(st.nextToken()) - 1;
			int c2 = Integer.parseInt(st.nextToken()) - 1;

			if (pathArr[r1][c1] == null)	pathArr[r1][c1] = new ArrayList<>();
			if (pathArr[r2][c2] == null)	pathArr[r2][c2] = new ArrayList<>();
			pathArr[r1][c1].add(new Point(r2, c2));
			pathArr[r2][c2].add(new Point(r1, c1));
		}

		// 소의 id값을 1번부터 입력받은 순서대로 배정하고 해당 번호를 2차원에 저장
		// 각 소의 좌표를 관리하기 위해 List에 저장
		cowArr = new int[N][N];
		cows = new Cow[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			cows[i] = new Cow(i + 1, r, c);
			cowArr[r][c] = i + 1;
		}

		System.out.println(bfs());

	}

	private static int bfs() {
		int answer = 0;

		// 모든 소 들에 대해서 이동가능한 범위 내의 소들 찾기
		for (int n = 0; n < K; n++) {
			Cow cow = cows[n];
			Queue<Point> q = new LinkedList<>();
			// 방문을 처리하기 위한 배열
			boolean[][] visit = new boolean[N][N];
			// 만날 수 있는 소를 저장히기 위한 배열
			boolean[] isMeet = new boolean[K + 1];

			q.add(new Point(cow.x, cow.y));
			visit[cow.x][cow.y] = true;

			while (!q.isEmpty()) {
				Point cur = q.poll();
				// 현재 좌표의 소의 id를 방문 처리, 소가 없을 경우 0번 인덱스에 방문 처리
				isMeet[cowArr[cur.x][cur.y]] = true;

				for (int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N 
							|| visit[nx][ny])	continue;

					if (!isPath(cur, nx, ny))	continue;

					visit[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}

			// 중복 쌍을 제거 하기 위해서 자신의 id보다 큰 소에 대해서만 만날 수 있는지 판단
			for (int i = cow.id + 1; i <= K; i++) {
				if (!isMeet[i])
					answer++;
			}
		}

		return answer;
	}

	private static boolean isPath(Point cur, int nx, int ny) {

		// 해당 좌표와 연결된 길이 없는 경우
		if (pathArr[cur.x][cur.y] == null)	return true;

		// 현재 좌표에 연결된 길 중 이동하려는 좌표와 길이 있는지 확인
		for (Point path : pathArr[cur.x][cur.y]) {
			if (path.x == nx && path.y == ny)	return false;
		}

		return true;
	}

}