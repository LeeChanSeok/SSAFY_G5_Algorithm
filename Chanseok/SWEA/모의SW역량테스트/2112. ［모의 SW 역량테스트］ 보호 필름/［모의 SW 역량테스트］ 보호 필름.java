import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int D, W ,K, min;
	static int[] films;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int film;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; ++tc) {
			
			st = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			films = new int[D];
			
			for(int i = 0; i < D; ++i) {
				st = new StringTokenizer(br.readLine());
				film = 0;
				for(int j = 0; j < W; ++j) {
					film = film << 1 | Integer.parseInt(st.nextToken());
				}
				films[i] = film;
			}
			
			min = D;
			if(check(films) || K == 1) min = 0;
			else recursive(0, films, 0,false);
			System.out.printf("#%d %d\n", tc, min);
			
		} // tc 종료
		
	}

	private static void recursive(int cnt, int[] films, int paintCnt, boolean painted) {
		
		if(paintCnt >= min) return;
		
		// 1. 검사
		if(painted && check(films)) {
			min = paintCnt;
			return;
		}
		
		if(cnt == D) return;
		
		// 2. 약품 처리 하기
		// 약품 처리 안하기
		int origin = films[cnt];
		recursive(cnt+1, films, paintCnt, false);
		
		// 약품 처리 하기 
		films[cnt] = 0;
		recursive(cnt+1, films, paintCnt + 1,  true);
		
		// 1로 처리
		films[cnt] = (1 << W) - 1;
		recursive(cnt+1, films, paintCnt + 1, true);
		
		films[cnt] = origin;
	}

	private static boolean check(int[] films) {
		int color, temp, cnt;
		for(int j = 0; j < W; ++j) {
			// j열의 첫 행의 색(0, 1)
			color = (films[0] & (1<<j))>>j;
			cnt = 1;
			for(int i = 1; i < D; ++i) {
				if(color == (films[i] & (1<<j))>>j) ++cnt;
				else {
					// 동일한 색이 K개 이상인 경우
					if(cnt >= K) break;
					// 남은 film의 수가 연속 K개 이상 될 수 없는 경우
					if(D - i < K) return false;
					
					// 현재 색 바꿔주기 (0 -> 1, 1 -> 0)
					color ^= 1;
					cnt = 1;
				}
			}
		}
		
		return true;
	}

}
