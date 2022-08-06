import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[][] player;
    public static int[] order;
    public static int max;
    public static boolean[] base;

    public static void printOrder(int[] order) {
    	for(int i : order)
    		System.out.print(i + " ");
    	System.out.println();
    		
    }
    
    public static int playgeme(int[] order){
        int score = 0;
        int out = 0;
        int idx = 0;
        for(int n = 0; n < N; n++) {// 이닝
        	out = 0;
        	base = new boolean[]{false, false, false, false};
        	while(out <3) {
        		int hit = player[n][order[idx]-1];
        		if(hit == 0) out++;
        		else {
        			base[0] = true;
        			for(int i = base.length-1; i >=0; i--) {
        				if(base[i]) {
        					base[i] = false;
        					if(i + hit > 3) {        						
        						score++;        						
        					}else {
        						base[i+hit] = true;
        					}
        				}
        			}
        		}
        		
        		idx = (++idx)%9;        		
        	}
        	
        }

        return score;
    }

    public static void dfs(int[] order, int player){
        if(player == 10){
            max = Math.max(max, playgeme(order));
            return;
        }

        for(int i = 0; i < order.length; i++){
            if(order[i] == 0){
                order[i] = player;
                dfs(order, player+1);
                order[i] = 0;                
            }
        }
    }

    public static void main(String[] args) throws IOException {
    	//System.setIn(new FileInputStream("src/com/baekjoon/G4/N17281/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //int T = Integer.parseInt(br.readLine());
        int T = 1;
        for(int tc = 1; tc <= T; tc++) {
        	
        	N = Integer.parseInt(br.readLine());
        	player = new int[N][9];
        	
        	for(int i = 0; i < N; i++){
        		st = new StringTokenizer(br.readLine(), " ");
        		for(int j = 0; j < 9; j++)
        			player[i][j] = Integer.parseInt(st.nextToken());
        	}
        	
        	max = 0;
        	order = new int[9];
        	order[3] = 1;
        	dfs(order, 2);
        	
        	System.out.println(max);
        }
    }
    
}