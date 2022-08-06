import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean isTest = false;
	
	static class Pair{
		int x,y;
		Pair(int x, int y){
			this.x=x;
			this.y=y;
		}
		
	}
	static int calc_dist(int[][] map, Pair pa, Pair pb) {
		int dist = Integer.MAX_VALUE;
		if(pa.x == pb.x) {
			dist = Math.abs(pa.y-pb.y)-1;
			if (dist >= 2) {				
				int start = Math.min(pa.y, pb.y);
				int end = Math.max(pa.y, pb.y);
				
				for(int i = start+1; i < end; i++) {
					if(map[pa.x][i] != 0) {
						return Integer.MAX_VALUE;
					}
				}
				return dist;
			}
		}else if(pa.y == pb.y) {
			dist = Math.abs(pa.x-pb.x)-1;
			if (dist >= 2){
	
				int start = Math.min(pa.x, pb.x);
				int end = Math.max(pa.x, pb.x);
				
				for(int i = start+1; i < end; i++) {
					if(map[i][pa.y] != 0) {
						return Integer.MAX_VALUE;
					}
				}
				
				return dist;
			}
			
		}
		
		return Integer.MAX_VALUE;
	}
	
	static int dfs(int[][] map, Map<Integer, List<Pair>> graph, int len) {
		if(graph.size() == 1) {
			return len;
		}
		
		List<Pair> A = graph.get(1);
		int min_group_dist = Integer.MAX_VALUE;
		int min_group_idx = -1;
		
		for(int i : graph.keySet()) {
			if( i == 1) continue;
			List<Pair> B = graph.get(i);
			
			int min_dist = Integer.MAX_VALUE;
			for(Pair Pa : A) {
				for(Pair Pb : B) {
					min_dist = Math.min(min_dist, calc_dist(map, Pa, Pb));									
				}
			}
			
			if(min_group_dist > min_dist) {
				min_group_dist = min_dist;
				min_group_idx = i;
			}			
		}
		
		if(min_group_dist == Integer.MAX_VALUE) return -1;
		A.addAll(graph.get(min_group_idx));
		graph.put(1, A);
		graph.remove(min_group_idx);
		
		return dfs(map, graph, len + min_group_dist);
	}

	public static void main(String[] args) throws IOException {
		if(isTest) System.setIn(new FileInputStream("./src/com/baekjoon/G/G1/N17472/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0 ,1, 0, -1};
		
		int T;
		if(isTest) T = Integer.parseInt(br.readLine());
		else T = 1;
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 1. N, M value input
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			

			// 2. N x M map init
			int[][] map = new int[N][M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			// 3. 섬 정보 입력하기
			// Map<섬 번호, 섬에 속한 좌표 리스트>
			// bfs를 통해 섬에 속해 있는 좌표들 모두 탐색
			Map<Integer, List<Pair>> graph = new HashMap<>();
			Queue<Pair> q = new ArrayDeque<>();
			boolean[][] visit = new boolean[N][M];
			Pair p, cur;
			int group = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 0 || visit[i][j]) continue;
					
					// bfs를 이용하여 연결된 모든 좌표 탐색
					group++;					
					List<Pair> list = new ArrayList<>();
					
					q.add(new Pair(i, j));
					list.add(new Pair(i, j));
					
					visit[i][j] = true;
					map[i][j] = group;
					while(!q.isEmpty()) {
						int nx,ny;
						cur = q.poll();
						
						for(int d = 0; d < 4; d++) {
							nx = cur.x + dx[d]; ny = cur.y + dy[d];
							
							if(nx < 0 || nx >= N || ny < 0 || ny >=M || visit[nx][ny]) continue;
							if(map[nx][ny] == 1) {
								visit[nx][ny] = true;
								q.add(new Pair(nx, ny));
								list.add(new Pair(nx, ny));
								map[nx][ny] = group;
							}
						}
					}
					graph.put(group, list);					
					
				}
			}// 정보 입력 종료
			
			// 4. 섬 연결하기		
			int ans = dfs(map, graph, 0);
			System.out.println(ans);
		}// tc 종료
    }
}