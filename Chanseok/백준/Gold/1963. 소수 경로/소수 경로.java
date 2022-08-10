package com.baekjoon.G.G4.N1963;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Prime {
	int prime;
	int cnt;

	public Prime(int prime, int cnt) {
		this.prime = prime;
		this.cnt = cnt;
	}
}

public class BOJ_1963_G4_소수경로 {

	public static boolean isPrime(int n) {
		for (int i = 2; i < Math.sqrt(n) + 1; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static int bfs(int A, int B) {
		int res = -1;
		int idx;
		int temp, pre, cur;
		
		Queue<Prime> q = new LinkedList<>();
		List<Integer> isUsed = new ArrayList<>();
		
		q.add(new Prime(A, 0)); // Queue에 현재 소수값과  변경 횟수를 저장한다.

		while (!q.isEmpty()) {
			Prime p = q.poll();
			if(p.prime == B) return p.cnt;
			
			idx = 1;	// 몇번째 자리수를 변경할 것인지 표시
			pre = 0;	// 이전 자리의 나머지 값
			
			for (int i = 0; i < 4; i++) {
				temp = p.prime;		// 현재 소수 값
				cur = temp % (idx * 10);	// 현재위치를 포함한 아래 값 
				temp -= cur - pre; // 현재 위치의 값만을 0으로 만들어 주기 위해
				
				for(int j = 0; j < 10; j++, temp += idx) {	// j는  현재 자리의 변경할 값을 나타냄
					if(i == 3 && j == 0) continue;	// 천의 자리의 경우 0이 오는 경우 확인
					if(isUsed.contains(temp)) continue;	// 이미 사용된 소수의 중복을 막기 위해 리스트에 저장하여 중복 판별
					
					if(isPrime(temp)) {
						isUsed.add(temp);	
						q.add(new Prime(temp, p.cnt+1)); // Queue에 변경한 소수 값을 입력
					}
					
				}
				
				idx *= 10;
				pre = cur;
			}
		}

		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		
		int A, B, ans;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			ans = bfs(A, B);
			if (ans == -1)
				System.out.println("Impossible");
			else
				System.out.println(ans);

		}
	}

}
