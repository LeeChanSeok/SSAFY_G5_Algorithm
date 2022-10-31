import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static class Point{
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N, M, L, K;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Point[] stars = new Point[K];
		
		int x, y;
		for(int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			stars[i] = new Point(x, y);
			
		}

		int max = 0;
		int cnt;
		for(int i = 0; i < K; ++i) {
			for(int j = 0; j < K; ++j) {
				x = stars[i].x;
				y = stars[j].y;
				cnt = 0;
				for(int k = 0; k < K; ++k) {
					if(stars[k].x >= x && stars[k].x <= (x + L) && stars[k].y >= y && stars[k].y <= (y + L)) ++cnt;
				}
				max = Math.max(max, cnt);
			}
		}
		
		System.out.println(K-max);

	}

}
