import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static Map<Integer, List<Integer>> graph;
	public static int ans;
	public static int[] population;
	
	public static void printArr(List<Integer> arr) {
		for (int a : arr)
			System.out.print(a + " ");
		System.out.println();
	}
	
	public static int sumElement(List<Integer> arr) {
		int sum = 0;
		for(int i = 0; i<arr.size(); i++) {
			sum+= population[arr.get(i)];
		}
		return sum;
	}

	public static boolean checkLink(List<Integer> nodes) {
		if (nodes.size() == 1)
			return true;
		List<Integer> copyArray = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 0; i < nodes.size(); i++)
			copyArray.add(nodes.get(i));
		q.add(copyArray.remove(0));
		

		while (!q.isEmpty()) {			
			int from = q.poll();
			List<Integer> toList = graph.get(from);
			for (int i = 0; i < copyArray.size(); i++) {
				
				if (toList.contains(copyArray.get(i))) {
					q.add(copyArray.get(i));					
					copyArray.remove(i);
					i--;
				}
				
			}
		}
		if(copyArray.isEmpty())	return true;
		else return false;
	}

	public static void dfs(List<Integer> cities, List<Integer> A, int index, int r) {
		if (r == 0) {
			//printArr(A);
			if(checkLink(A)){
				List<Integer> B = new ArrayList<>();
				for(int i = 1; i <=N; i++) {
					if(!A.contains(i)) B.add(i);
				}
				if(checkLink(B)) {
					ans = Math.min(ans, Math.abs(sumElement(A) - sumElement(B)));
				}
				
			}
			return ;
		}

		for (int i = index; i <= N; i++) {

			A.add(cities.get(i-1));
			dfs(cities, A, i + 1, r - 1);
			A.remove(A.indexOf(cities.get(i-1)));

		}	

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = 1;
		for (int tc = 1; tc <= T; ++tc) {

			N = Integer.parseInt(br.readLine());
			population = new int[N+1];

			// 도시별 인구 수
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; ++i)
				population[i] = Integer.parseInt(st.nextToken());

			// 각 도시와 연결되어 있는 도시 정보를 Map타입을 저장
			graph = new HashMap<>();
			for (int i = 1; i <= N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				int M = Integer.parseInt(st.nextToken());
				List<Integer> city = new ArrayList<Integer>();
				for (int j = 0; j < M; ++j) {
					city.add(Integer.parseInt(st.nextToken()));
				}
				graph.put(i, city);
			}

			// A의 도시에 포함할 수 있는 모든 경우의 수 구하기
			// 1개 ~ N/2개 까지
			List<Integer> cities = new ArrayList<>();
			for (int i = 1; i <= N; i++)
				cities.add(i);

			ans = Integer.MAX_VALUE;
			List<Integer> A = new ArrayList<>();
			for (int i = 1; i <= N / 2; ++i) {
				dfs(cities, A, 1, i);
			}

			if (ans == Integer.MAX_VALUE)
				System.out.println(-1);
			else
				System.out.println(ans);

		}
	}

}