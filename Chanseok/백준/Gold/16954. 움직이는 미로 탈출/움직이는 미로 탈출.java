package com.baekjoon.G.G4.N16954;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pair{
	int x, y, move;
	public Pair(int N){
		x = N-1;
		y = 0;
		move = 0;
	}

	public Pair(int x, int y, int move){
		this.x = x;
		this.y = y;
		this.move = move;
	}

}
public class BOJ_16954_G4_움직이는미로탈출 {

	public static void moveWall(char[][] map, int N) {
		for(int i = N-1; i > 0; i--)
			for(int j = 0; j < N; j++)
				map[i][j] = map[i-1][j];
		
		for(int j = 0; j < N; j++)
			map[0][j] = '\u0000';
				
	}

	public static int bfs(char[][] map, int N){
		int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1, 0};
		int[] dy = {0, 1, 1, 1, 0, -1, -1, -1, 0};

		int nx, ny;
		int move;
		
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(N));
		move = 0;
		
		//2. BFS를 이용하여 이동가능한 모든 방면으로 이동 또는 정지한다.
		while(!q.isEmpty()) {			
			Pair cur = q.poll();
			// 이동이 끝난 후 벽을 이동시킨다.
			if(move != cur.move) {
				moveWall(map, N);
				move = cur.move;
			}
			
			// 벽과 만나면 종료한다.
			if(map[cur.x][cur.y] == '#') continue;
			
			// 목적지에 도착하면 종료한다.
			if(cur.x == 0 && cur.y == N-1) return 1;
			
			// 캐릭터의 행의 위치가 벽의 행 위치보다 같거나 작으면 목적지에 도착 가능하다.
			if(cur.x <= cur.move) return 1;
			
			
			for(int d = 0; d < dx.length; d++) {
				nx = cur.x + dx[d];
				ny = cur.y + dy[d];
				if(nx < 0 || nx >=N || ny < 0 || ny >= N || map[nx][ny] == '#') continue;
				
				q.add(new Pair(nx, ny, cur.move+1));
				
			}
		}

		return 0;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/com/baekjoon/G4/N16954/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			
			int N = 8; // 8*8
			char[][] map = new char[N][N];
			String row;
			//1. 맵 정보를 입력 받는다.
			for(int i = 0; i < N; i++){
				row = br.readLine();
				for(int j = 0; j <N; j++)
					map[i][j] = row.charAt(j);
			}
			
			int ans = bfs(map, N);
			System.out.println(ans);
		}

	}

}