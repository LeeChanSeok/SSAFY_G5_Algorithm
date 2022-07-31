import java.util.Scanner;

public class Main {

	public static int[] dx = {0, 1, 1};
	public static int[] dy = {1, 1, 0};
	public static int map[][];
	public static int count, N;
	
	public static void recursive(int sx, int sy, int dir) {
		//경계 밖을 나갔을 경우
		if(sx < 1 || sx >N || sy < 1|| sy > N) return;
		
		//벽이 있는 경우
		if(map[sx][sy] == 1) return;
		
		// 대각선의 경우 추가 범위의 벽 확인
		if(dir==1) {
			if(map[sx-1][sy] == 1 || map[sx][sy-1]==1) return;
		}
		
		//목적지에 도착하였을 경우
		if(sx == N && sy == N) {
			count++;
			return;
		}
		
		if (dir - 1 >= 0) recursive(sx + dx[dir - 1], sy + dy[dir - 1], dir - 1);
		recursive(sx + dx[dir], sy + dy[dir], dir);
		if (dir + 1 <= 2) recursive(sx + dx[dir + 1], sy + dy[dir + 1], dir + 1);	
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = 1;
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			
			map = new int[N+2][N+2];
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			int sx = 1;
			int sy = 2;
			int dir = 0;
			count = 0;
			
			recursive(sx, sy, dir);
			
			System.out.println(count);			
		}
	}
}