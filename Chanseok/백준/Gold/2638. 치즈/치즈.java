import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
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
	
	static int N, M;
	
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 모눈종이의 상태를 나타내기 위한 배열 -> 0 : 치즈 내 빈공간, 1 : 치즈, 2 : 외부 공기
		int[][] map = new int[N][M];
		Queue<Point> cheese= new ArrayDeque<>(); // 치즈 좌표를 저장
		Queue<Point> die = new ArrayDeque<>(); // 녹아 없어질 치즈
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1) {
					cheese.offer(new Point(i, j));
					map[i][j] = num;
				}
			}
		}
		
		System.out.println(solution(map, cheese, die));
	}
	private static int solution(int[][] map, Queue<Point> cheese, Queue<Point> die) {
		initMap(map);
		int time = 0;
		
		while(!cheese.isEmpty()) {
			int qSize = cheese.size();
			while(qSize-- > 0) {
				Point cur = cheese.poll();
				
				// 치즈에 인접한 외부 공기가 2 이상인지 확인
				int air = 0;
				for(int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					if(map[nx][ny] == 2) air++;
				}
				if(air >= 2) die.offer(cur); // 인접한 외부공기가 2 이상이면 die에 저장
				else cheese.offer(cur); // 아니면 다시 치즈에 저장
			}
			
			
			while(!die.isEmpty()) {
				// 녹아 없어질 치즈들에 대해서 해당 좌표를 외부 공기(2)로 표시하고 bfs 탐색
				Point cur = die.poll();	
				map[cur.x][cur.y] = 2;
				for(int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					if(map[nx][ny] == 0) {
						map[nx][ny] = 2;
						die.offer(new Point(nx, ny));
					}
				}
			}
			
			time++;
		}
		
		return time;
	}
	private static void initMap(int[][] map) {
		// (0, 0)부터 bfs를 적용하여 이동할 수 있는 공기(0번)에 대해서 탐색하며 해당 위치를 외부공기(2번)으로 표시
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(0, 0));
		map[0][0] = 2;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] != 0) continue;
				map[nx][ny] = 2;
				q.offer(new Point(nx, ny));
			}
		}
		
	}

}