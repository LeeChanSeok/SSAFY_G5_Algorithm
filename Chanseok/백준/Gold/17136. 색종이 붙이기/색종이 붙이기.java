import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[][] map;
	public static int N = 10;
	public static int ans = 987654321;
	public static int[] paper_cnt;
	public static int use;
	public static int remain;
	public static StringTokenizer st;
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };

	public static int[][] Arraycopy(int[][] map) {
		int[][] copy = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				copy[i][j] = map[i][j];
		return copy;
	}

	public static int[][] fill(int[][] map, int r, int c, int k) {

		for (int i = 0; i < k; i++)
			for (int j = 0; j < k; j++)
				map[r + i][c + j] = 0;
		return map;
	}

	public static void recursive(int r, int c, int[][] map, int[] paper_cnt, int use, int remain) {

		// 사용한 색종이의 수가 최소 개수보다 크면 return
		if (use >= ans)
			return;

		// 열의 끝을 벗어났을 경우 다음으로 이동
		if (c >= N) {
			r++;
			c = 0;
		}

		if (r >= N) {
			if (remain == 0)
				ans = Math.min(ans, use);
			return;
		}

		// 채울 공간이 아니면 다음 위치로
		if (map[r][c] != 1) {
			recursive(r, c + 1, map, paper_cnt, use, remain);
			return;
		}

		// 가능한 최대 정사각형 크기 구하기
		int max_length = 5;
		for (int i = 0; i < max_length; i++) {
			if (r + i >= N) {
				max_length = i;
				break;
			}
			if (map[r + i][c] != 1) {
				max_length = i;
				break;
			}

			for (int j = 0; j < max_length; j++) {
				if (c + j >= N) {
					max_length = j;
					break;
				}
				if (map[r + i][c + j] != 1) {
					max_length = j;
					break;
				}
			}
		}

		for (int k = 1; k <= max_length; k++) {
			if (paper_cnt[k - 1] != 0) {
				int[][] new_map = Arraycopy(map);
				fill(new_map, r, c, k);
				paper_cnt[k - 1]--;
				use++;
				remain = remain - (k) * (k);
				recursive(r, c + k, new_map, paper_cnt, use, remain);

				paper_cnt[k - 1]++;
				use--;
				remain = remain + (k) * (k);
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		//System.setIn(new FileInputStream(Main.class.getResource("input.txt").getFile()));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//int T = Integer.parseInt(br.readLine());
		int T = 1;

		for (int tc = 1; tc <= T; tc++) {

			// map initialization
			map = new int[N][N];
			remain = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						remain++;
				}
			}

			ans = 987654321;
			use = 0;
			paper_cnt = new int[] { 5, 5, 5, 5, 5 };
			if (remain < 4) {
				System.out.println(remain);
				continue;
			}
			recursive(0, 0, map, paper_cnt, use, remain);

			if (ans == 987654321) {
				System.out.println(-1);
			} else {
				System.out.println(ans);
			}

		}

	}

}
