import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K, ans;
	static boolean[][] map;
	static boolean[][][][] stickys;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];
		stickys = new boolean[K][4][][];

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			stickys[k][0] = new boolean[R][C];
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					stickys[k][0][r][c] = (st.nextToken().charAt(0) == '1') ? true : false;
				}
			}
		}
		// input end

		// make rotation map
		makeRotate();
		ans = 0;
		solution();
		System.out.println(ans);

	}

	private static void makeRotate() {
		for (int k = 0; k < K; k++) {
			int R = stickys[k][0].length;
			int C = stickys[k][0][0].length;

			// d = 1
			stickys[k][1] = new boolean[C][R];
			for (int i = 0; i < C; i++) {
				for (int j = 0; j < R; j++) {
					stickys[k][1][i][j] = stickys[k][0][R - 1 - j][i];
				}
			}

			// d = 2
			stickys[k][2] = new boolean[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++)
					stickys[k][2][i][j] = stickys[k][0][R - 1 - i][C - 1 - j];
			}

			// d = 3
			stickys[k][3] = new boolean[C][R];
			for (int i = 0; i < C; i++) {
				for (int j = 0; j < R; j++) {
					stickys[k][3][i][j] = stickys[k][1][C - 1 - i][R - 1 - j];
				}
			}

		}

	}

	private static void solution() {
		// 모든 스티커에 대해서
		for (int k = 0; k < K; k++) {

			// 4방향 회전
			for (int d = 0; d < 4; d++) {
				// 스티커를 붙일 수 있는지 확인
				if (isStick(k, d))
					break;
			}
		}

	}

	private static boolean isStick(int k, int d) {
		int R = stickys[k][d].length;
		int C = stickys[k][d][0].length;

		// 왼쪽, 위의 좌표부터 스티커를 붙일 수 있는지 판단
		for (int i = 0; i < N - R + 1; i++) {
			for (int j = 0; j < M - C + 1; j++) {
				// 현재 좌표에서 스티커를 붙일 수 있는지 판단
				if (!isDuplicate(k, d, i, j, R, C)) {
					put(k, d, i, j, R, C);
					return true;
				}
			}
		}

		return false;
	}

	private static boolean isDuplicate(int k, int d, int i, int j, int R, int C) {

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[i + r][j + c] && stickys[k][d][r][c])	// 스티커를 붙이려는 곳이 비어있지 않은 경우
					return true;
			}
		}

		return false;
	}

	private static void put(int k, int d, int i, int j, int R, int C) {

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (!map[i + r][j + c] && stickys[k][d][r][c]) {// 스티커를 붙이려는 곳이 비어있는 경우
					map[i + r][j + c] = true;
					ans++;
				}
			}
		}
	}
	
}