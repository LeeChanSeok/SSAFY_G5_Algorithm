import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] FW;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		FW = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				FW[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// Floyd-Warshall Algorithm
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					FW[i][j] = Math.min(FW[i][j], FW[i][k] + FW[k][j]);
				}
			}
		}
		// K번부터 시작해서 모든 순열 구하기
		dfs(K, 1, 1 << K, 0);
		System.out.println(min);

	}
	private static void dfs(int node, int cnt, int flag, int time) {

		if(time >= min) return;
		if(cnt == N) {
			min = time;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if((flag & 1 << i) != 0) continue;
			dfs(i, cnt+1, flag | 1 << i, time + FW[node][i]);
		}
		
	}

}