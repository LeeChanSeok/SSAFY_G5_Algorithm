import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] ap = new int[N];
		
		for(int i = 0; i < N; ++i)
			ap[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(ap);
		
		// 설치 할 수 있는 공유기의 거리
		// 최소값 : 인접한 집 사이의 거리인 1
		// 최대값 : 양 끝에 거리에 해당
		// 거리를 이분탐색으로 설치가능한 최대거리를 구한다.
		
		int lo = 1;
		int hi = ap[N-1] - ap[0] + 1;
		int mid;
		
		while(lo < hi) {
		
			mid = (lo + hi) / 2;
			
			if(install(mid, N, ap) < C) {
				hi = mid;
			}else {
				lo = mid + 1;
			}
			
		}
		
		System.out.println(lo-1);

	}

	private static int install(int dist, int N, int[] ap) {

		int cnt = 1;
		int locate;
		int installed = ap[0];
		for(int i = 1; i < N; ++i) {
			locate = ap[i];
			
			if(locate - installed >= dist) {
				++cnt;
				installed = locate;
			}
		}
		return cnt;
	}

}
