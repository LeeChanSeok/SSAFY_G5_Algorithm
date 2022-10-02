import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Integer>[] list = new ArrayList[N];
		for(int i = 0; i < N; ++i)
			list[i] = new ArrayList<>(N);
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i)
			list[0].add(Integer.parseInt(st.nextToken()));
		
		int time = 1;
		int min, max;
		int row, col;
		int[][] origin, move;
		int d;
		
		while(true) {
			min = Integer.MAX_VALUE;

			for(int i : list[0])
				min = Math.min(min, i);
			
			// 1. 물고기의 수가 가장 적은 어항에 물고기를 한 마리 넣는다. 
			for(int i = 0; i < N; ++i)
				if(list[0].get(i) == min) list[0].set(i, min + 1);
			
			list[1].add(list[0].get(0)); list[0].remove(0);
			
			// 2. 어항을 쌓는다. 
			while(true) {
				row = 1;col = 0;
				for(; row < N; ++row) {
					if(list[row].isEmpty()) break;
				}			
				col = list[row-1].size();
				//  공중 부양시킨 어항 중 가장 오른쪽에 있는 어항의 아래에 바닥에 있는 어항이 있을때까지 반복한다.
				if(row > list[0].size() - col) break;
				for(int j = col; j > 0; --j) {
					for(int i = 0; i < row; ++i) {
						list[j].add(list[i].get(0));
						list[i].remove(0);
					}
				}
			}
	
			// 3. 어항에 있는 물고기의 수를 조절한다.
			// 모든 인접한 두 어항에 대해서, 물고기 수의 차이를 구한다. 
			// 이 차이를 5로 나눈 몫을 d라고 하자. d가 0보다 크면, 두 어항 중 물고기의 수가 많은 곳에 있는 물고기 d 마리를 적은 곳에 있는 곳으로 보낸다. 
			row = 0; col = 0;
			while(!list[row++].isEmpty()) {}
			--row;
			col = list[0].size();
			
			// list의 값들을 배열에 저장한다.
			origin = new int[row][col];
			move = new int[row][col];
			for(int i = 0; i < row; ++i) {
				for(int j = 0,cSize = list[i].size(); j < cSize; ++j) {
					origin[i][j] = list[i].get(j);
					move[i][j] = list[i].get(j);
				}
			}
			
			for(int i = 0; i < row; ++i) {
				for(int j = 0; j < col; ++j) {
					if(origin[i][j] == 0) continue;
					
					if(j + 1 < col && origin[i][j+1] != 0) {
						d = Math.abs(origin[i][j] - origin[i][j+1])/5;
						if(origin[i][j] > origin[i][j+1]) {
							move[i][j] -= d;
							move[i][j+1] += d;
						}else {
							move[i][j] += d;
							move[i][j+1] -= d;
						}
					}
					if(i + 1 < row  && origin[i+1][j] != 0) {
						d = Math.abs(origin[i][j] - origin[i+1][j])/5;
						if(origin[i][j] > origin[i+1][j]) {
							move[i][j] -= d;
							move[i+1][j] += d;
						}else {
							move[i][j] += d;
							move[i+1][j] -= d;
						}
					}
					
				}
			}
			
			// 배열에 계산한 결과를 리스트에 저정한다.
			for(int i = 0; i < row; ++i) {
				for(int j = 0,cSize = list[i].size(); j < cSize; ++j) {
					list[i].set(j, move[i][j]);
				}
			}
			
			// 4. 다시 어항을 바닥에 일렬로 놓아야 한다. 
			// 가장 왼쪽에 있는 어항부터, 그리고 아래에 있는 어항부터 가장 위에 있는 어항까지 순서대로 바닥에 놓아야 한다.
			for(int j = 0; j < col; ++j) {
				for(int i = 0; i < row; ++i) {
					if(list[i].isEmpty()) break;
					list[0].add(list[i].get(0)); 
					list[i].remove(0);
				}
			}
			
			// 5. 가운데를 중심으로 왼쪽 N/2개를 공중 부양시켜 전체를 시계 방향으로 180도 회전 시킨 다음, 오른쪽 N/2개의 위에 놓아야 한다.
			for(int i = 0; i < N/2; ++i) {
				list[1].add(0, list[0].get(0)); 
				list[0].remove(0);
			}
			
			// 이 작업은 두 번 반복해야한다. 두 번 반복하면 바닥에 있는 어항의 수는 N/4개가 된다
			for(int j = 0; j < 2; ++j) {
				for(int i = 0; i < N/4; ++i) {
					list[3-j].add(0, list[j].get(0)); 
					list[j].remove(0);
				}
			}
			
			// 6. 물고기 조절 작업을 수행
			// 모든 인접한 두 어항에 대해서, 물고기 수의 차이를 구한다. 
			// 이 차이를 5로 나눈 몫을 d라고 하자. d가 0보다 크면, 두 어항 중 물고기의 수가 많은 곳에 있는 물고기 d 마리를 적은 곳에 있는 곳으로 보낸다. 
			row = 4; col = N/4;
			origin = new int[row][col];
			move = new int[row][col];
			
			// list의 값들을 배열에 저장한다.
			for(int i = 0; i < row; ++i) {
				for(int j = 0; j < col; ++j) {
					origin[i][j] = list[i].get(j);
					move[i][j] = list[i].get(j);
				}
			}
			
			for(int i = 0; i < row; ++i) {
				for(int j = 0; j < col; ++j) {
					if(origin[i][j] == 0) continue;
					
					if(j + 1 < col && origin[i][j+1] != 0) {
						d = Math.abs(origin[i][j] - origin[i][j+1])/5;
						if(origin[i][j] > origin[i][j+1]) {
							move[i][j] -= d;
							move[i][j+1] += d;
						}else {
							move[i][j] += d;
							move[i][j+1] -= d;
						}
					}
					if(i + 1 < row  && origin[i+1][j] != 0) {
						d = Math.abs(origin[i][j] - origin[i+1][j])/5;
						if(origin[i][j] > origin[i+1][j]) {
							move[i][j] -= d;
							move[i+1][j] += d;
						}else {
							move[i][j] += d;
							move[i+1][j] -= d;
						}
					}
					
				}
			}
			
			for(int i = 0; i < row; ++i) {
				for(int j = 0; j < col; ++j) {
					list[i].set(j, move[i][j]);
				}
			}
			
			// 7. 바닥에 일렬로 놓는다.
			for(int j = 0; j < col; ++j) {
				for(int i = 0; i < row; ++i) {
					list[0].add(list[i].get(0));
					list[i].remove(0);
				}
			}
			
			// 배열에 계산한 결과를 리스트에 저정한다.
			min = max = list[0].get(0);
			for(int i : list[0]) {
				min = Math.min(min, i);
				max = Math.max(max, i);
			}
			
			// 8. 물고기가 가장 많이 들어있는 어항과 가장 적게 들어있는 어항의 물고기 수 차이가 K 이하인지 확인한다.
			if(Math.abs(min - max) <= K) break;
			++time;
		}
		
		System.out.println(time);
	}

}
