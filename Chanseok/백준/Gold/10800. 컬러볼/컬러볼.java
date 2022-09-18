import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static class Ball implements Comparable<Ball>{
		int color, size, score;

		public Ball(int color, int size, int score) {
			super();
			this.color = color;
			this.size = size;
			this.score = score;
		}

		@Override
		public int compareTo(Ball o) {
			// TODO Auto-generated method stub
			return this.size - o.size;
		}

	}

	static int N;
	static int maxColor = 200_001;
	static int maxSize = 2_001;
	static int[] sizeSubSum;
	static Ball[] balls;
	static Map<Integer, Integer> hashMap;
	static List<Ball>[] colorList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		balls = new Ball[N + 1]; // 입력받은 ball의 정보를 순서대로 배열에 저장
		hashMap = new HashMap<>(); // 동일한 ball에 대한 중복 처리
		colorList = new ArrayList[N + 1]; // 각 color에 대한 ball List

		for (int i = 0; i < N + 1; i++) {
			colorList[i] = new ArrayList<>();
		}

		int color, size;
		int pCnt = 0;
		sizeSubSum = new int[maxSize];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			color = Integer.parseInt(st.nextToken());
			size = Integer.parseInt(st.nextToken());

			Ball ball = new Ball(color, size, size);
			int key = ball.color << 12 | size;	// ball의 color와 size를 하나의 key(ex. hash)로 index 설정
			if (hashMap.containsKey(key)) { // 동일한 ball이 존재하명 이미 존재하는 ball의 자료를 balls에 저장
				balls[++pCnt] = balls[hashMap.get(key)];
				balls[pCnt].score += size; // 중복되는 ball을 하나로 처리하기 위해
			} else {
				balls[++pCnt] = ball;
				hashMap.put(key, pCnt);
				colorList[color].add(balls[pCnt]); // 해당 ball이 존재하지 않으면 List에 저장
			}
			
			// 크기별 total size 구하기
			sizeSubSum[size] += size; // size에 대한 구간합을 구하기 위해 배열에 저장

		} // 입력 완료

		// 구간 합 구하기
		for (int i = 1; i < maxSize; i++)
			sizeSubSum[i] += sizeSubSum[i - 1];

		// color별로 저장된 ball을 오름차순으로 정렬
		for (int i = 0; i < N + 1; i++) {
			Collections.sort(colorList[i]);
			
			// 해당 ball보다 작은 크기의 ball에 대한 size의 합을 저장
			int temp1 = 0; int temp2 = 0;
			for(Ball ball : colorList[i]) {
				temp2 = ball.score;
				ball.score = temp1;
				temp1 += temp2;
			}
		}

		// 입력받은 ball에 대해서 순차적으로 처리
		for(Ball ball : balls) {
			if(ball != null)
				// 해당 ball의 size보다 작은 ball의  총 score에서 해당 ball의 size보다 작은 같은 색의 ball의 score 차를 구함
				sb.append(sizeSubSum[ball.size-1] - ball.score).append("\n");
		}
		
		bw.write(sb.toString());
		bw.close();

	}

}