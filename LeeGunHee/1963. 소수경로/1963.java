import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
    public static int n, first, last; 
    public static boolean[] prime = new boolean[10000]; 
    public static int div[] = { 1000, 100, 10, 1 };
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(br.readLine());
 
        prime[0] = prime[1] = true;
 
        for (int i = 2; i < 10000; i++) {
            if (!prime[i]) {// 만약 prime[i]가 소수라면
                for (int j = 2; j * i < 10000; j++) {
                    prime[j * i] = true;
                }
            }
        }
 
        for (int k = 0; k < n; k++) {
            st = new StringTokenizer(br.readLine());
            first = Integer.parseInt(st.nextToken());
            last = Integer.parseInt(st.nextToken());
 
            int ans = BFS(first);
            
            if (ans != -1) {
                System.out.println(ans);
 
            } 
            else {
                System.out.println("Impossible");
            } 
        } 
    } 
 
     public static int BFS(int n) {
        int cnt = -1; 
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[10000];// visited도 초기화
 
        queue.add(n);
        visited[n] = true;
        while (!queue.isEmpty()) { 
            cnt++;
 
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                
                if (node == last) { 
                    return cnt;
                }
                
                int[] arr = new int[4];
                
                for (int j = 0; j < 4; j++) {
                    arr[j] = node / div[j];
                    node %= div[j];
                }
 
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 10; k++) {
                        if (arr[j] + 1 > 9) {
                            arr[j] = 0;
                        } 
                        else {
                            arr[j]++;
                        }
                        
                        int num = 0;
                        
                        for (int l = 0; l < 4; l++) {
                            num += arr[l] * div[l];
                        }
 
                        if (num >= 1000 && !visited[num] && !prime[num]) { 
                            queue.add(num);
                            visited[num] = true;
                        }
                    } 
                } 
            } 
        } 
        return -1;
    }
}
