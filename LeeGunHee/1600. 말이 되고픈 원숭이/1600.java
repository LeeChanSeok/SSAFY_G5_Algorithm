import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main { 
    public static int W, H, K, answer = -1;
    
    public static int[][] map;
    public static boolean[][][] visited;
    
    public static int[] hr = { 1,-1,2,-2,2,-2,1,-1 };
    public static int[] hc = { 2,2,1,1,-1,-1,-2,-2 };

    public static int[] dr = {0, 1, 0, -1};
    public static int[] dc = {1, 0, -1, 0};
       
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        K = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());        
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
               
        map = new int[H][W];
        visited = new boolean[H][W][32];
        
        for(int r = 0; r < H; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < W; c++) {
        		map [r][c] = Integer.parseInt(st.nextToken());
        	}
        }
       
        BFS(0, 0, 0, 0);
        
        System.out.println(answer);
    }
    
    
    public static void BFS(int row, int col, int cnt, int time) { 
        Queue<mySet> q = new LinkedList<>();

        q.add(new mySet(row, col, cnt, time));      
        visited[row][col][cnt] = true; 
        
        while(!q.isEmpty()) {
        	int cr = q.peek().row;
        	int cc = q.peek().col;
        	int cn = q.peek().cnt;
        	int ct = q.peek().time;
        	
        	q.poll();
        	
        	if (cr == H - 1 && cc == W - 1) {
        		answer = ct;
        		return;
        	}
        	
        	if(cn < K) {
        		for (int i = 0; i < 8; i++) {
    				int nr = cr + hr[i];
    				int nc = cc + hc[i];

    				if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
    					continue;
    				}
    				if (map[nr][nc] == 0 && !visited[nr][nc][cn + 1]) {
    					q.add(new mySet(nr, nc, cn + 1, ct + 1));
    					visited[nr][nc][cn + 1] = true;
    				}
    			}
        	}
        	
        	for(int i = 0; i < 4; i++) {
        		int nr = cr + dr[i];
        		int nc = cc + dc[i];
        		
        		// 배열 범위초과
        		if (!(0 <= nr && nr < H && 0 <= nc && nc < W)) 
        			continue;    			
        	        		
    			if (!visited[nr][nc][cn] && map[nr][nc] == 0) { 
        			visited[nr][nc][cn] = true;
        			q.add(new mySet(nr, nc, cn, ct + 1));
        		}
        		
        	}
        }
    }

    static class mySet {
    	int row;
    	int col;
    	int cnt;
    	int time;
    	
    	public mySet(int row, int col, int cnt, int time) {
    		this.row = row;
    		this.col = col;
    		this.cnt = cnt;
    		this.time = time;
    	}
    }
}
