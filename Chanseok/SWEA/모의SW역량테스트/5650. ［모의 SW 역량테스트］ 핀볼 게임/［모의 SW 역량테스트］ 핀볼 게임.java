import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static class Ball{
		int x, y, dir;

		public Ball(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}

	static class Hole{
		int x, y;

		public Hole(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		//변수 선언
		int N, max, score, temp;
		int[][] map;

		Ball ball;
		Hole[][] holes;
		int[][] changeDir = {
				{0,1,2,3},
				{2,3,1,0},
				{1,3,0,2},
				{3,2,0,1},
				{2,0,3,1},
				{2,3,0,1},
		};

		int dx[] = {-1, 0, 1, 0};
		int dy[] = {0, 1, 0, -1};
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			// hole 정보 저장
			holes = new Hole[11][2];
			
			map = new int[N+2][N+2];
			// 게임판 정보 입력
			for(int i = 1; i <= N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= N; ++j) {
					
					temp = Integer.parseInt(st.nextToken());
					map[i][j] = temp;
					
					if(temp >= 6) {
						if(holes[temp][0] == null) holes[temp][0] = new Hole(i, j);
						else holes[temp][1] = new Hole(i, j);
					}
				}
			}
			
			// 게임판 외부 벽 처리
			Arrays.fill(map[0], 5);
			Arrays.fill(map[N+1], 5);
			for(int i = 1; i <= N; ++i) {
				map[i][0] = map[i][N+1] = 5;
			}
	
			max = 0;
			for(int i = 1; i <= N; ++i) {
				for(int j = 1; j <= N; ++j) {
					if(map[i][j] != 0) continue;
					
					for(int d = 0; d < 4; ++d) {
						ball = new Ball(i, j, d);
						score = simulation(changeDir, map, holes, N, dx, dy, ball);
						max = Math.max(max, score);
					}
					
				}
			}
			
			System.out.printf("#%d %d\n", tc, max);
		}// tc 종료
		

	}

	private static int simulation(int[][] changeDir, int[][] map, Hole[][] holes, int n, int[] dx, int[] dy, Ball ball) {
		
		int score = 0;
		int sx = ball.x; int sy = ball.y;
		int mapIdx;

		while(true){
			
			ball.x += dx[ball.dir];
			ball.y += dy[ball.dir];
			mapIdx = map[ball.x][ball.y];
			
			// 출발 위치로 돌아오거나 블랙홀에 빠지면 끝
			if(ball.x == sx && ball.y == sy
					|| mapIdx == -1) return score;
			
			
			// 다음 위치에 삼각형 또는 사각형이 있는 경우
			if (mapIdx > 0 && mapIdx <= 5) {
				++score;
				ball.dir = changeDir[mapIdx][ball.dir];
			}
			// 다음 위치에 홀이 있는 경우
			else if(mapIdx >= 6) {
				if(holes[mapIdx][0].x == ball.x && holes[mapIdx][0].y == ball.y) {
					ball.x = holes[mapIdx][1].x;
					ball.y = holes[mapIdx][1].y;
				}else {
					ball.x = holes[mapIdx][0].x;
					ball.y = holes[mapIdx][0].y;
				}
			}

		}
		
	}


}
