import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static final int[] dx = {1, 0, -1, 0};
	static final int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int x, y, d, g;
		LinkedList<Integer> direction = new LinkedList<>();
		LinkedList<Integer> temp = new LinkedList<>();
		boolean[][] visit = new boolean[101][101];
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());

			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			
			direction.clear();
			direction.add(d);
			
			// 1. g에따른 시작위치에서부터 전체 이동 방향 구하기
			findDirection(direction, temp, g);
			
			// 2. 해당방향으로 이동하면서 방문 처리
			move(x, y, visit, direction);
			
		}// tc 종료
		
		// 3. 사각형 개수 구하기.
		System.out.println(Count(visit));
	}

	private static int Count(boolean[][] visit) {
		int cnt = 0;
		
		for(int i = 0; i < 100; ++i) {
			for(int j = 0; j < 100; ++j) {
				if(visit[i][j] && visit[i+1][j] && visit[i][j+1] && visit[i+1][j+1]) ++cnt;
			}
		}
		
		return cnt;
	}

	private static void move(int x, int y, boolean[][] visit, LinkedList<Integer> direction) {
		
		visit[x][y] = true;
		for(int d : direction) {
			x += dx[d];
			y += dy[d];
			visit[x][y] = true;
		}
		
	}

	private static void findDirection(LinkedList<Integer> direction, LinkedList<Integer> temp, int g) {
		Iterator<Integer> iter;
		while(g-- > 0) {
			iter = direction.descendingIterator();
			temp.clear();
			while(iter.hasNext()) {
				temp.add((iter.next() + 1)%4);
			}
			direction.addAll(temp);
		}
		
	}

}
