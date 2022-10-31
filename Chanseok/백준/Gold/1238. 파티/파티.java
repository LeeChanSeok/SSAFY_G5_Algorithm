import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node{
		int v, t;
		Node next;
		
		public Node(int v, int t, Node next) {
			super();
			this.v = v;
			this.t = t;
			this.next = next;
		}
		
	}
	static class Edge implements Comparable<Edge>{
		int v, t;

		public Edge(int v, int t) {
			super();
			this.v = v;
			this.t = t;
		}

		@Override
		public int compareTo(Edge o) {
			return this.t - o.t;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		int INF = Integer.MAX_VALUE;
		
		int[] N2X = new int[N+1];
		int[] X2N = new int[N+1];
		
		Arrays.fill(N2X, INF);
		Arrays.fill(X2N, INF);
		N2X[X] = X2N[X] = 0;
		
		Node[] adjList = new Node[N+1];
		
		int from, to, t;
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, t, adjList[from]);
		}
		
		// 1. N2X
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visit = new boolean[N+1]; 
		Edge cur;
		int min;
		for(int i = 1; i <= N; ++i) {
			pq.clear();
			pq.offer(new Edge(i, 0));
			Arrays.fill(visit, false);
			min = INF;
			while(!pq.isEmpty()) {
				cur = pq.poll();
				if(cur.t >= min) continue;
				visit[cur.v] = true;
				
				if(cur.v == X) {
					min = cur.t;
					continue;
				}
				
				for(Node temp = adjList[cur.v]; temp != null; temp = temp.next) {
					if(!visit[temp.v])
						pq.offer(new Edge(temp.v, cur.t + temp.t));
				}
			}
			
			N2X[i] = min;
		}
		
		// 2. X2N
		pq.clear();
		pq.offer(new Edge(X, 0));
		Arrays.fill(visit, false);
		while(!pq.isEmpty()) {
			
			cur = pq.poll();
			if(X2N[cur.v] < cur.t) continue;
			visit[cur.v] = true; 
			
			
			for(Node temp = adjList[cur.v]; temp != null; temp = temp.next) {
				if(!visit[temp.v] && X2N[temp.v] > cur.t + temp.t) {
					X2N[temp.v] = cur.t + temp.t;
					pq.offer(new Edge(temp.v, cur.t + temp.t));
				}
			}
		}
		
		// 3. max
		int max = 0;
		for(int i = 1; i <= N; ++i)
			max = Math.max(max, N2X[i] + X2N[i]);
		
		System.out.println(max);
	}

}
