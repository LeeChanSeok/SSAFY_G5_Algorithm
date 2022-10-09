import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
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
	
	static class Passenger{
		Point start;
		Point end;
		int dist;
		
		public Passenger(Point start, Point end, int dist) {
			this.start = start;
			this.end = end;
			this.dist = dist;
		}
		
	}
	
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int F = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; ++j)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
	

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken())-1;
		int y = Integer.parseInt(st.nextToken())-1;
		
		Point car = new Point(x, y);
		
		Passenger[] passenger = new Passenger[M+2];
		int sx, sy, ex, ey;
		int dist = 0;
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			sx = Integer.parseInt(st.nextToken())-1;
			sy = Integer.parseInt(st.nextToken())-1;
			ex = Integer.parseInt(st.nextToken())-1;
			ey = Integer.parseInt(st.nextToken())-1;
			
			// 승객의 출발지와 목적지 사이의 최단 경로를 구한다
			dist = calcDist(map, N, sx, sy, ex, ey);
			// 이동할 수 없는 거리인 경우 작업을 중단한다.
			if(dist == -1) break; 
			
			// 입력받은 고객의 정보를 2번 idx부터 map과 배열에 저장한다.
			passenger[i+2] = new Passenger(new Point(sx, sy), new Point(ex, ey), dist);
			map[sx][sy] = i+2; 
		}
		
		if(dist == -1) System.out.println(-1);
		else		
			System.out.println(simulation(map, N, M, F, car, passenger));
		
	}

	private static int simulation(int[][] map, int N, int M, int F, Point car, Passenger[] passenger) {

		boolean[][] visit = new boolean[N][N];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(car);
		visit[car.x][car.y] = true; 
		Point cur, candi;
		Passenger pass;
		int qSize, dist;
		int nx, ny;
		boolean isMove = false;
		
		// M명의 승객을 모두 태울때 까지 반복한다.
		do {
			// 택시 위치 및 방문 여부 , q를 초기화 한다.
			q.clear();
			q.offer(car);
			visit = new boolean[N][N];
			visit[car.x][car.y] = true;
			candi = new Point(N, N);
			dist = 0;
			
			// 택시의 위치에서 가장 가까운 승객을 찾는다.
			isMove = false;
			while(!q.isEmpty()) {
				
				qSize = q.size();
				while(qSize-- > 0) {
					cur = q.poll();
			
					if(map[cur.x][cur.y] > 1) {
						if((cur.x < candi.x) || 
								(cur.x == candi.x && cur.y < candi.y)) 
							candi = cur;
						continue;
					}
					
					for(int d = 0; d < 4; ++d) {
						nx = cur.x + dx[d];
						ny = cur.y + dy[d];
						
						if(nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny] || map[nx][ny] == 1) continue;
						q.offer(new Point(nx, ny));
						visit[nx][ny] = true;
					}
					
				}
				// 가장 가까운 승객을 찾았을 경우, 
				if(candi.x != N) {
					pass = passenger[map[candi.x][candi.y]];
					// 승객을 태워 목적지까지 이동하는데 연료가 충분하지 않은 경우
					if(dist + pass.dist > F) return -1;
					
					// 승객의 목적지로 택시를 이동시키고 연료를 새로 계산한다.
					F = F - dist + pass.dist;
					car.x = pass.end.x;
					car.y = pass.end.y;
					map[pass.start.x][pass.start.y] = 0;  
					--M;
					isMove = true;
					break;
				}
				
				++dist;
			}
			
			if(!isMove) return -1;
			
		}while(M>0);
		
		// 모든 승객을 태우고 난 후 남은 연료를 return한다.
		return F;
	}

	private static int calcDist(int[][] map, int N, int sx, int sy, int ex, int ey) {

		boolean[][] visit = new boolean[N][N];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(sx, sy));
		visit[sx][sy] = true;
		Point cur;
		int qSize, dist = 0;
		int nx, ny;
		
		// 출발지에서 목적지까지의 최단 경로를 구한다.
		while(!q.isEmpty()) {
			qSize = q.size();
			while(qSize-- >0) {
				cur = q.poll();
				
				if(cur.x == ex && cur.y == ey) return dist;
				
				for(int d = 0; d < 4; ++d) {
					nx = cur.x + dx[d];
					ny = cur.y + dy[d];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny] || map[nx][ny] == 1) continue;
					q.add(new Point(nx, ny));
					visit[nx][ny] = true;
					
				}
			}
			++dist;
		}
		
		return -1;
	}

}
