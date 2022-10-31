import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringBuffer sb = new StringBuffer();
	    StringTokenizer st;
	    
	    int N = Integer.parseInt(br.readLine());

	    int[] height = new int[N];
	    st = new StringTokenizer(br.readLine());
	    for(int i = 0; i < N; ++i)
	        height[i] = Integer.parseInt(st.nextToken());

	    int[][] ascending = new int[N][2];
	    int[][] descending = new int[N][2];

	    Stack<int[]> left = new Stack<>();
	    Stack<int[]> right = new Stack<>();

	    left.add(new int[]{height[0], 0});
	    right.add(new int[]{height[N-1], N-1});
	    int h;
	    for(int i = 1; i < N; ++i){
	        h = height[i];
	        while(!left.isEmpty() && left.peek()[0] <= h) left.pop();
	        if(!left.isEmpty()) {
	        	descending[i][0] = left.size();
	        	descending[i][1] = left.peek()[1];
	        }
	        left.push(new int[]{h, i});
	    }

	    for(int i = N - 2; i >= 0; --i){
	        h = height[i];
	        while(!right.isEmpty() && right.peek()[0] <= h) right.pop();
	        if(!right.isEmpty()) {
	        	ascending[i][0] = right.size();
	        	ascending[i][1] = right.peek()[1];
	        }
	        right.push(new int[]{h, i});
	    }

	    int ld, rd, cnt;
	    for(int i = 0; i < N; ++i){

	        cnt = descending[i][0] + ascending[i][0];
	        sb.append(cnt + " ");
	        if(cnt == 0) {}
	        else if(descending[i][0] == 0) {
	        	sb.append(ascending[i][1]+1);
	        }else if(ascending[i][0] == 0) {
	        	sb.append(descending[i][1]+1);
	        }else {
		        ld = i - descending[i][1];
		        rd = ascending[i][1] - i;
		        
		        if(rd < ld)
		        	sb.append(ascending[i][1]+1);
		        else
		        	sb.append(descending[i][1]+1);
	        }
	        
	        sb.append("\n");
	        	
	    }

	    bw.write(sb.toString());
	    bw.close();
	  
	}

}
