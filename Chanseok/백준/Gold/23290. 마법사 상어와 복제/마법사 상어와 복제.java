import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Fish implements Cloneable{
		int x, y, d;

		public Fish(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
		@Override
		protected Fish clone() throws CloneNotSupportedException {
			return (Fish)super.clone();
		}
		
		public void rotate() {
			d = (d+7)%8;
		}
		
	}
	
	static class Shark{
		int x, y;

		public Shark(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static final int[] fdx = {0, -1, -1, -1, 0, 1, 1, 1};
	static final int[] fdy = {-1, -1, 0, 1, 1, 1, 0, -1};

	static final int[] sdx = {-1, 0, 1, 0};
	static final int[] sdy = {0, -1, 0, 1};
	
	static int max, direction;
	static List<Fish>[][] copyArr;
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int x, y, d;
		List<Fish>[][] fishArr = new LinkedList[4][4];
		for(int i = 0; i < 4; ++i)
			for(int j = 0; j < 4; ++j)
				fishArr[i][j] = new LinkedList<>();
		
		// 4x4 격자판에 물고기 정보 저장
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			d = Integer.parseInt(st.nextToken()) - 1;
			fishArr[x][y].add(new Fish(x, y, d));
		}
		
		// 상어 정보 저장
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken()) - 1;
		y = Integer.parseInt(st.nextToken()) - 1;
		Shark shark = new Shark(x, y);
		
		System.out.println(solution(M, S, fishArr, shark));
	}

	private static int solution(int M, int S, List<Fish>[][] fishArr, Shark shark) throws CloneNotSupportedException {
		// 물고기의 냄새를 저장하는 배열
		int[][] smallArr = new int[4][4];
		
		// 물고기의 이동 정보를 저장할 배열 초기화
		copyArr = new LinkedList[4][4];
		for(int i = 0; i < 4; ++i)
			for(int j = 0; j < 4; ++j)
				copyArr[i][j] = new LinkedList<>();
		
		while(S-- >0) {
			clearCopyArr(copyArr);
			
			// 1. 상어가 모든 물고기에게 복제 마법을 시전한다
			// 5번에서 물고기가 복제되어 칸에 나타난다.
			
			// 2. 모든 물고기가 한 칸 이동한다.
			// 상어가 있는 칸, 물고기의 냄새가 있는 칸, 격자의 범위를 벗어나는 칸으로는 이동할 수 없다.
			// 각 물고기는 자신이 가지고 있는 이동 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다. 
			// 만약, 이동할 수 있는 칸이 없으면 이동을 하지 않는다. 그 외의 경우에는 그 칸으로 이동을 한다
			moveFish(fishArr, copyArr, smallArr, shark);
			
			// 3.상어가 연속해서 3칸 이동한다. 
			// 상어는 현재 칸에서 상하좌우로 인접한 칸으로 이동할 수 있다. 
			// 연속해서 이동하는 칸 중에 격자의 범위를 벗어나는 칸이 있으면, 그 방법은 불가능한 이동 방법이다. 
			// 연속해서 이동하는 중에 상어가 물고기가 있는 같은 칸으로 이동하게 된다면, 그 칸에 있는 모든 물고기는 격자에서 제외되며, 제외되는 모든 물고기는 물고기 냄새를 남긴다. 
			// 가능한 이동 방법 중에서 제외되는 물고기의 수가 가장 많은 방법으로 이동하며, 그러한 방법이 여러가지인 경우 사전 순으로 가장 앞서는 방법을 이용한다. 사전 순에 대한 문제의 하단 노트에 있다.
			max = 0; direction = Integer.MAX_VALUE;
			dfs(shark.x, shark.y, 0, 0, 0, 0);
			moveShark(shark, direction, copyArr, smallArr);
			
			// 4. 두 번 전 연습에서 생긴 물고기의 냄새가 격자에서 사라진다.
			removeSmall(smallArr);
			
			// 5. 1에서 사용한 복제 마법이 완료된다. 모든 복제된 물고기는 1에서의 위치와 방향을 그대로 갖게 된다.
			mergeFish(fishArr, copyArr);
		}
		
		return fishCnt(fishArr);
	}

	private static void clearCopyArr(List<Fish>[][] copyArr) {
		for(int i = 0; i < 4; ++i)
			for(int j = 0; j< 4; ++j)
				copyArr[i][j].clear();
	}


	private static int fishCnt(List<Fish>[][] fishArr) {
		int cnt = 0;
		for(int i = 0; i < 4; ++i)
			for(int j = 0; j < 4; ++j)
				cnt += fishArr[i][j].size();
		return cnt;
	}


	private static void mergeFish(List<Fish>[][] fishArr, List<Fish>[][] copyArr) {
		for(int i = 0; i < 4; ++i) {
			for(int j = 0; j < 4; ++j)
				fishArr[i][j].addAll(copyArr[i][j]);
		}
	}
	
	private static void removeSmall(int[][] smallArr) {
		for(int i = 0; i < 4; ++i) {
			for(int j = 0; j < 4; ++j)
				if(smallArr[i][j] != 0) --smallArr[i][j];
		}
		
	}
	
	private static void moveShark(Shark shark, int direction, List<Fish>[][] copyArr, int[][] smallArr) {
		int dir, nx, ny;
		nx = ny = 0;
		for(int d = 2; d >= 0; --d) {
			// direction에 저장된 상어 이동 순서에 맞게 상어를 이동시킨다.
			dir = (direction>> 2*d) & 3;
			
			nx = shark.x + sdx[dir];
			ny = shark.y + sdy[dir];
			
			// 이동하려는 곳에 물고기가 있는 경우 물고기 냄새 시간을 설정한다.
			if(!copyArr[nx][ny].isEmpty()) smallArr[nx][ny] = 3;
			// 해당 위치의 물고기를 없앤다.
			copyArr[nx][ny].clear();
			
			shark.x = nx;
			shark.y = ny;
		}
		
	}
	
	private static void dfs(int x, int y, int cnt, int flag, int visit, int ate) {
		// 상어가 3번 이동했을 경우 최대 물고기를 많이 먹을 수 있는 경로 구하기
		if(cnt == 3) {
			if(ate > max) {
				direction = flag;
				max = ate;
			}
			else if(ate == max) {
				if(flag < direction)
					direction = flag;
			}
			return;
		}
		
		int nx, ny;
		for(int d = 0; d < 4; ++d) {
			nx = x + sdx[d];
			ny = y + sdy[d];
			// 격자 밖으로 벗어난 경우
			if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
			
			// 이미 방문한 지역인 경우 이동하려는 곳의 물고기의 카운트를 저장하지 않는다.
			if((visit & (1<<(nx*4 +ny))) != 0) 
				dfs(nx, ny, cnt + 1, flag<<2 | d, visit| 1<<(nx*4 + ny) , ate);
			else
				dfs(nx, ny, cnt + 1, flag<<2 | d, visit| 1<<(nx*4 + ny) , ate + copyArr[nx][ny].size());
		}
		
	}

	private static void moveFish(List<Fish>[][] fishArr, List<Fish>[][] copyArr, int[][] smallArr, Shark shark) throws CloneNotSupportedException {
		
		int nx, ny, dir, d;
		Fish fish;
		// 모든 좌표에 대해서 물고기가 있는 경우 물고기를 이동시킨다.
		for(int i = 0; i < 4; ++i) {
			for(int j = 0; j < 4; ++j) {
				
				// 현재 좌표에 물고기가 없는 경우
				if(fishArr[i][j].isEmpty()) continue;
				
				// 모든 물고기에 대해서 이동
				for(Fish f : fishArr[i][j]) {
					fish = f.clone();
					d = 0;
					for(; d < 8; ++d) {
						nx = fish.x + fdx[fish.d];
						ny = fish.y + fdy[fish.d];
						
						if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4 
								&& (nx != shark.x || ny != shark.y)
								&& smallArr[nx][ny] == 0) {
							fish.x = nx; fish.y = ny;
							copyArr[nx][ny].add(fish);
							break;
							
						}
						fish.rotate();
						
					}
					// 이동할 수 없는 경우, 원위치에 물고기 저장
					if(d == 8) copyArr[fish.x][fish.y].add(fish);
				}
				
			}
		}
		
	}

}
