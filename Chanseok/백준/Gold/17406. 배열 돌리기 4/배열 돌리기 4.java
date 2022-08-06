import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[] dx = { 1, 0, -1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };
	public static int N;
	public static int M;
	public static int K;
	public static int min;

	public static int calc_score(int[][] arr) {
		int min = Integer.MAX_VALUE;

		for (int[] row : arr) {
			int sum = 0;
			for (int a : row)
				sum += a;
			min = Math.min(min, sum);
		}
		return min;
	}

	public static void copyArray(int[][] from, int[][] to) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				to[i][j] = from[i][j];
	}

	public static int[][] rotation(int[][] arr, int[][] command, int[] order) {
		int[][] new_arr = new int[N][M];
		copyArray(arr, new_arr);
		for (int i = 0; i < K; i++) {
			int r = command[order[i] - 1][0];
			int c = command[order[i] - 1][1];
			int s = command[order[i] - 1][2];

			for (int k = 1; k <= s; k++) {
				int dir = 0;
				int x = r - k;
				int y = c - k;

				int temp = new_arr[x][y];
				for (int cnt = 1; cnt <= k * 8; ++cnt) {
					new_arr[x][y] = new_arr[x + dx[dir]][y + dy[dir]];

					x += dx[dir];
					y += dy[dir];

					if (cnt % (2 * k) == 0)
						dir = (++dir) % 4;
				}
				new_arr[x][y + 1] = temp;
			}
		}
		return new_arr;
	}

	public static void dfs(int[][] arr, int[][] command, int[] order, int cnt) {
		if (cnt == K + 1) {
			min = Math.min(min, calc_score(rotation(arr, command, order)));
			return;
		}

		for (int i = 0; i < K; i++) {
			if (order[i] == 0) {
				order[i] = cnt;
				dfs(arr, command, order, cnt + 1);
				order[i] = 0;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][] command = new int[K][];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            command[i] = new int[] { r - 1, c - 1, s };
        }

        int[] order = new int[K];
        min = Integer.MAX_VALUE;
        dfs(arr, command, order, 1);
        System.out.println(min);
	}

}