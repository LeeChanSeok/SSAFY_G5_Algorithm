package com.swea.모의SW역량테스트.N4014;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_4014_활주로건설 {
	static int N, X;
	static int ans;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/com/swea/모의SW역량테스트/N4014/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			ans = solution();
			sb.append("#" + tc + " " + ans + "\n");

		} // test-case 종료

		bw.write(sb.toString());
		bw.close();

	}

	private static int solution() {
		int res = 0;
		for (int i = 0; i < N; i++) {
			int num = map[i][0];
			int cnt = 1;
			boolean isBuild = true;	// 첫 시작
			boolean isUp = false, isDown = true;
			for (int j = 1; j < N; j++) {
				if(map[i][j] == num) {
					cnt++;
					if(cnt >= X &&!isDown && !isUp) {
						isDown = true;
						cnt = 0;
						isBuild = true;
					}else if(cnt >= X && isDown && !isUp) {
						isUp = true;		
						isBuild = true;
					}
					
					
				}else if(map[i][j] > num){ // 높이가 높아지는 경우
					
					if(map[i][j] - num > 1 || !isUp) {
						isBuild = false;
						break;
					}
					isDown = true;
					isUp = false;
					
					cnt = 1;
					num = map[i][j];
					isBuild = true;		
					
				}else {	// 높이가 낮아지는 경우
					if(num - map[i][j] > 1 ||!isDown) {
						isBuild = false;
						break;
					}
					isDown = false;
					isUp = false;
					
					cnt = 1;
					num = map[i][j];
					isBuild = false;
				}
				
			}
			
			if(isBuild) res++;
		}
		
		for (int j = 0; j < N; j++) {
			int num = map[0][j];
			int cnt = 1;
			boolean isBuild = true;	// 첫 시작
			boolean isUp = false, isDown = true;
			for (int i = 1; i < N; i++) {
				if(map[i][j] == num) {
					cnt++;
					if(cnt >= X &&!isDown && !isUp) {
						isDown = true;
						cnt = 0;
						isBuild = true;
					}else if(cnt >= X && isDown && !isUp) {
						isUp = true;		
						isBuild = true;
					}
					
					
				}else if(map[i][j] > num){ // 높이가 높아지는 경우
					if(map[i][j] - num > 1 || !isUp) {
						isBuild = false;
						break;
					}
					isDown = true;
					isUp = false;
					
					cnt = 1;
					num = map[i][j];
					isBuild = true;		
					
				}else {	// 높이가 낮아지는 경우
					if(num - map[i][j] > 1 ||!isDown) {
						isBuild = false;
						break;
					}
					isDown = false;
					isUp = false;
					
					cnt = 1;
					num = map[i][j];
					isBuild = false;
				}
				
			}
			
			if(isBuild) res++;
		}
		
		
		return res;
	}

}
