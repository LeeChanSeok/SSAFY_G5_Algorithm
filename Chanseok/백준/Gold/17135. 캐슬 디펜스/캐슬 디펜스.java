import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Person {
	public int id;
	public int x, y;

	public Person(int x, int y, int id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", x=" + x + ", y=" + y + "]";
	}
}

public class Main {

	public static List<Person> Enermys = new ArrayList<>();
	public static List<Person> Archers = new ArrayList<>();
	public static int max;
	public static int N, M, D;
	public static int nEnermy;
	public static int res;
	public static int INF = 987654321;

	public static int playGame(boolean[] isArcher) {
		int cnt = 0;

		Archers.clear();
		for (int j = 0; j < M; j++) {
			if (isArcher[j]) {
				Archers.add(new Person(N, j, 0));
			}
		}
		
		List<Person> temp_Enermys = new ArrayList<>(Enermys.size());
		for(Person e : Enermys)
			temp_Enermys.add(new Person(e.x, e.y, e.id));

		for (int i = 0; i < N; i++) {			
			if(temp_Enermys.size() == 0) break;			

			List<Person> candis = new ArrayList<>();
			for (int a = 0; a < Archers.size(); a++) {
				Person archer = Archers.get(a);
				Person candi = new Person(INF, INF, -1);

				int min_dist = D;

				for (int e = 0; e < temp_Enermys.size(); e++) {
					Person enermy = temp_Enermys.get(e);
					Math.abs(archer.x - enermy.x);

					int diff = Math.abs(archer.x - enermy.x) + Math.abs(archer.y - enermy.y);
					if (diff == min_dist && enermy.y < candi.y) {
						min_dist = diff;
						candi = enermy;

					} else if (diff < min_dist) {
						min_dist = diff;
						candi = enermy;
					}

				}
				if(candi.id == -1) continue;
				candis.add(candi);
			}

			// 적 지우기
			for (Person p : candis) {
				for (int e = 0; e < temp_Enermys.size(); e++) {
					if (p.id == temp_Enermys.get(e).id) {
						temp_Enermys.remove(e);
						cnt++;
						break;
					}
				}
			}

			// 적 이동
			for (int e = 0; e < temp_Enermys.size(); e++) {
				int nx = temp_Enermys.get(e).x + 1;
				if (nx >= N) {
					temp_Enermys.remove(e);
					e--;
				}else {
					temp_Enermys.get(e).x = temp_Enermys.get(e).x + 1;					
				}
			}

		}

		return cnt;
	}

	public static void recursive(boolean[] isArcher, int idx, int count) {
		// 마지막에 도착한 경우
		if (idx == M && count != 3)
			return;

		// 궁수 배치가 모드 끝난 경우
		if (count == 3) {
			max = Math.max(max, playGame(isArcher));
			return;
		}

		isArcher[idx] = true;
		recursive(isArcher, idx + 1, count + 1);
		isArcher[idx] = false;
		recursive(isArcher, idx + 1, count);

	}

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("src/day3/input.txt"));
		Scanner sc = new Scanner(System.in);

		//int T = sc.nextInt();
		int T=1;
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			D = sc.nextInt();

			int[][] map = new int[N][M];

			max = 0;
			nEnermy = 0;
			Enermys.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 1) {
						Enermys.add(new Person(i, j, ++nEnermy));
					}
				}
			}

			boolean[] isArcher = new boolean[M];
			recursive(isArcher, 0, 0);

			System.out.println(max);
		}

	}

}
