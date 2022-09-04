import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

	static int R, C;
	static char[][] map;
	static int[][] visit;

	static Point mine;
	static Point[] aduino;
	static int count, move, comCnt;
	static char[] command;

	static final int[] dx = { 1, 1, 1, 0, 0, 0, -1, -1, -1 };
	static final int[] dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		visit = new int[R][C];
		aduino = new Point[R * C];
		count = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				char c = map[i][j];
				if (c == 'I')
					mine = new Point(i, j);

				else if (c == 'R') {
					visit[i][j]++;
					aduino[count++] = new Point(i, j);
				}
				map[i][j] = '.';
			}

		}

		String temp = br.readLine();
		comCnt = temp.length();
		command = new char[comCnt];
		for (int i = 0; i < comCnt; i++)
			command[i] = temp.charAt(i);

		move = 0;
		if (simulation()) {
			printMap();
		} else {
			System.out.println("kraj " + move);
		}

	}

	private static void printMap() {
		for (int i = 0; i < count; i++) {
			map[aduino[i].x][aduino[i].y] = 'R';
		}
		map[mine.x][mine.y] = 'I';

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++)
				System.out.print(map[i][j]);
			System.out.println();
		}

	}

	private static boolean simulation() {

		while (move++ < comCnt) {

			// 1. 종수 이동
			mine.x += dx[command[move - 1] - '0' - 1];
			mine.y += dy[command[move - 1] - '0' - 1];

			// 2. 미친 아두이노가 있는 칸으로 이동한 경우 끝
			if (visit[mine.x][mine.y] != 0)
				return false;

			// 3. 미친 아두이노 이동
			for (int i = 0; i < count; i++) {
				int dist = INF;
				int mx = -1, my = -1;

				for (int d = 0; d < 9; d++) {
					int nx = aduino[i].x + dx[d];
					int ny = aduino[i].y + dy[d];
					int diff = calc_dist(mine.x, mine.y, nx, ny);
					if (diff < dist) {
						dist = diff;
						mx = nx;
						my = ny;
					}
				}
				// 4. 미친 아두이노가 종수의 위치로 이동한 경우 끝
				if (dist == 0)
					return false;

				visit[aduino[i].x][aduino[i].y]--;
				aduino[i].x = mx;
				aduino[i].y = my;
				visit[aduino[i].x][aduino[i].y]++;
			}

			// 5. 2개 이상의 미친 아두이노가 같은 칸에 있는 경우
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (visit[i][j] >= 2) {
						burst(i, j);
						visit[i][j] = 0;
					}
				}
			}

		}

		return true;
	}

	private static void burst(int x, int y) {
		for (int i = 0; i < count; i++) {
			if (aduino[i].x == x && aduino[i].y == y) {
				aduino[i] = aduino[--count];
				i--;
			}
		}
	}

	private static int calc_dist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}