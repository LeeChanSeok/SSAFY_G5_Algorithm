import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
public class Main { 
    public static int W, H, answer = 0;
    public static char[][] map;
    public static int[] dr = { -1,0,1,1,1,0,-1,-1,0 };
    public static int[] dc = { 1,1,1,0,-1,-1,-1,0,0 };
    public static boolean[][][] visited = new boolean[8][8][9];
       
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    
        map = new char[8][8];
        
        for(int r = 0; r < 8; r++) {
        	String str = br.readLine();
        	for(int c = 0; c < 8; c++) {
        		map[r][c] = str.charAt(c);        		
        	}
        }
        
        BFS(7, 0, 0);
        
        System.out.println(answer);
    }
    
    
    public static void BFS(int row, int col, int time) { 
        Queue<mySet> q = new LinkedList<>();

        q.add(new mySet(row, col, time));
        
        
        visited[row][col][time] = true; 
        
        while(!q.isEmpty()) {
        	int cr = q.peek().row;
        	int cc = q.peek().col;
        	int ct = q.peek().time;
        	
        	q.poll();
        	
        	if (cr == 0 && cc == 7) {
        		answer = 1;
        		return;
        	}
        	
        	for(int i = 0; i < 9; i++) {
        		int nr = cr + dr[i];
        		int nc = cc + dc[i];
        		int nt = Math.min(8, ct + 1);
        		
        		// 배열 범위초과
        		if (!(0 <= nr && nr < 8 && 0 <= nc && nc < 8)) 
        			continue;    			
        		// 벽을 만남
    			if (nr - ct >= 0 && map[nr - ct][nc] == '#') 
    				continue;
    			// 내려온 벽이 닿음
    			if (nr - ct - 1 >= 0 && map[nr - ct - 1][nc] == '#') 
    				continue;
        		
    			if (!visited[nr][nc][nt]) {
        			visited[nr][nc][nt] = true;
        			q.add(new mySet(nr, nc, nt));
        		}
        		
        	}
        }
    }

    static class mySet {
    	int row;
    	int col;
    	int time;
    	
    	public mySet(int row, int col, int time) {
    		this.row = row;
    		this.col = col;
    		this.time = time;
    	}
    }
}
