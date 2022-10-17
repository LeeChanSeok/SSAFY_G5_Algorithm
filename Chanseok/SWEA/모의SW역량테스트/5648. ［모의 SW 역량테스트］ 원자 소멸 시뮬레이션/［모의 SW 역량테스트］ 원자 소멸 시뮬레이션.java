import java.io.*;
import java.util.*;
import java.util.Map.*;

public class Solution {
	
	static class Point{
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
	}
	
	static class Atom{
		int x, y, d, k;

		public Atom(int x, int y, int d, int k) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.k = k;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N, x, y, d, k, score;
		Atom atom;
		Point point;
		List<Atom> atomList, list;
		atomList  = new LinkedList<>();
		Map<Point, List<Atom>> atomMap = new HashMap<>();
		Iterator<Atom> iter;
		
		// 2. 원자는 각자 고유의 움직이는 방향을 가지고 있다. (상하좌우 4방향)
		// - 상: y 가 증가하는 방향
		// - 하: y 가 감소하는 방향
		// - 좌: x 가 감소하는 방향
		// - 우: x 가 증가하는 방향
		final int[] dx = {0, 0, -1, 1};
		final int[] dy = {1, -1, 0, 0};
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
		
			// 원자들의 정보가 담긴 List
			atomList.clear();
			for(int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				
				// 0.5에 충돌하는 경우를 정수 시간에 충돌하도록 좌표를 2배 증가
				x = Integer.parseInt(st.nextToken())*2;
				y = Integer.parseInt(st.nextToken())*2;
				d = Integer.parseInt(st.nextToken());
				k = Integer.parseInt(st.nextToken());
				
				atomList.add(new Atom(x, y, d, k));
			}
			
			score = 0;
			while(atomList.size() != 0) {
				atomMap.clear();
				// 원자 이동
				iter = atomList.iterator();
				while(iter.hasNext()) {
					atom = iter.next();
					// 총돌해서 사라진 원소 처리
					if(atom.k == -1) iter.remove();
					else {
						//3. 모든 원자들의 이동속도는 동일하다. 즉, 1초에 1만큼의 거리를 이동한다.
						atom.x += dx[atom.d];
						atom.y += dy[atom.d];
						
						// 원소가 격자 밖을 나가는 경우, 다른 원소와 충돌하지 않으므로 list에서 제거
						if(atom.x < -2000 || atom.x > 2000 || atom.y < -2000 || atom.y > 2000) {
							iter.remove();
							continue;
						}
						
						// 이동하려는 위치를 key값으로 하여 원소 저장
						point = new Point(atom.x, atom.y);
						
						list = atomMap.getOrDefault(point, new LinkedList<>());
						list.add(atom);
						atomMap.put(point, list);
					}
				}
				
				// 충돌 처리
				for(Entry<Point, List<Atom>> entry : atomMap.entrySet()) {
					list = entry.getValue();
					// 5. 두 개 이상의 원자가 동시에 충돌 할 경우 
					if(list.size() >= 2) {
						iter = list.iterator();
						while(iter.hasNext()) {
							atom = iter.next();
							
							// 충돌한 원자들은 모두 보유한 에너지를 방출하고 소멸된다.
							score += atom.k;
							atom.k = -1;
							iter.remove();
						}
					}
				}
			}
			
			System.out.printf("#%d %d\n", tc, score);
		} // tc 종료
	
	}
	
}
