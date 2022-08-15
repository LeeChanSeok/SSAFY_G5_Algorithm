import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main { 
    public static int W, H;
    public static char[][] map;
    public static int[][] visited;
    public static int[] dr = {0, 1, 0, -1};
    public static int[] dc = {1, 0, -1, 0};
    public static Pair start, end;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        map = new char[H][W];
        visited = new int[H][W];
         
        int start_end_check = 0;
        
        for(int r = 0; r < H; r++) {
        	String str = br.readLine();
        	for(int c = 0; c < W; c++) {
        		map[r][c] = str.charAt(c);
        		
        		if(map[r][c] == 'C') {
        			if(start_end_check == 0) {
        				start = new Pair(r, c);
        				start_end_check = 1;
        			}
        			else
        				end = new Pair(r, c);
        		}
        		visited[r][c] = Integer.MAX_VALUE;
        	}
        }
        
        System.out.println(BFS(start.row, start.col));
    }
    
    
    public static int BFS(int row, int col) { 
        Queue<mySet> q = new LinkedList<>();
        
        for(int i = 0; i < 4; i++) {
        	q.add(new mySet(row, col, i, 0));
        }
        
        visited[row][col] = 0;
        
        while(!q.isEmpty()) {
        	int cr = q.peek().row;
        	int cc = q.peek().col;
        	int cd = q.peek().dir;
        	int cm = q.peek().mirror;
        	
        	q.poll();
        	
        	for(int i = 0; i < 4; i++) {
        		int nr = cr + dr[i];
        		int nc = cc + dc[i];
        		int nm = cm;
        		
        		if(nc < 0 || nr < 0 || H <= nr || W <= nc) {
        			continue;
        		}
        		if(map[nr][nc] == '*') {
        			continue;
        		}
        		if(cd !=  i) {
        			nm += 1;
        		}
        		
        		if(visited[nr][nc] >= nm) {
        			visited[nr][nc] = nm;
        			q.add(new mySet(nr, nc, i, nm));
        		}
        		
        	}
        }
        
        return visited[end.row][end.col];
    }
 
    static class Pair {
    	int row;
    	int col;
    	
    	public Pair(int row, int col) {
    		this.row = row;
    		this.col = col;
    	}
    }
    
    static class mySet {
    	int row;
    	int col;
    	int dir;
    	int mirror;
    	
    	public mySet(int row, int col, int dir, int mirror) {
    		this.row = row;
    		this.col = col;
    		this.dir = dir;
    		this.mirror = mirror;
    	}
    }
}