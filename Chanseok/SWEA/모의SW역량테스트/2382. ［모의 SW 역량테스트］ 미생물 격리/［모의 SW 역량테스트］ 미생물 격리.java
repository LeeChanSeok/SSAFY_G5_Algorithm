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
	
	static class Microbe{
		int x, y, cnt, dir;

		public Microbe(int x, int y, int cnt, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}
		
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		final int[] dx = {-1, 1, 0, 0};
		final int[] dy = {0, 0, -1, 1};
		
		int N, M, K;
		int x, y, cnt, dir;
		int maxCnt, maxDir, totalCnt;
		List<Microbe> microbeList, list;
		microbeList = new LinkedList<>();
		Map<Point, List<Microbe>> microbeMap = new HashMap<>();
		Iterator<Microbe> iter;
		Microbe microbe;
		Point point;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			microbeList.clear();
			// 1. 최초 각 미생물 군집의 위치와 군집 내 미생물의 수, 이동 방향이 주어진다. 
			// 약품이 칠해진 부분에는 미생물이 배치되어 있지 않다. 
			// 이동방향은 상, 하, 좌, 우 네 방향 중 하나이다.
			for(int i = 0; i < K; ++i) {
				st = new StringTokenizer(br.readLine());
				
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				cnt = Integer.parseInt(st.nextToken());
				dir = Integer.parseInt(st.nextToken())-1;
				
				microbeList.add(new Microbe(x, y, cnt, dir));
			}
			
			while(M-- > 0) {
				microbeMap.clear();
				
				// 2. 각 군집들은 1시간마다 이동방향에 있는 다음 셀로 이동한다.
				iter = microbeList.iterator();
				while(iter.hasNext()) {
					microbe = iter.next();
					
					if(microbe.cnt == 0) {
						iter.remove();
						continue;
					}
					
					microbe.x += dx[microbe.dir];
					microbe.y += dy[microbe.dir];
					
					// 3. 미생물 군집이 이동 후 약품이 칠해진 셀에 도착하면 군집 내 미생물의 절반이 죽고, 이동방향이 반대로 바뀐다. 
					if(microbe.x == 0 || microbe.x == N-1 ||microbe.y == 0 || microbe.y == N-1) {
						if((microbe.dir & 1) == 0) microbe.dir += 1;
						else microbe.dir -=1;
						microbe.cnt /= 2;
						
						if(microbe.cnt == 0) {
							iter.remove();
							continue;
						}
					}
					
					point = new Point(microbe.x, microbe.y);
					
					list = microbeMap.getOrDefault(point, new LinkedList<>());
					list.add(microbe);
					microbeMap.put(point, list);
					
				}
				
				// 4. 이동 후 두 개 이상의 군집이 한 셀에 모이는 경우 군집들이 합쳐지게 된다.
				microbeList.clear();
				for(Entry<Point, List<Microbe>> entry : microbeMap.entrySet()) {
					point = entry.getKey();
					list = entry.getValue();
					if(list.size() == 1) {
						microbeList.add(list.get(0));
						continue;
					}
					
					// 합쳐 진 군집의 미생물 수는 군집들의 미생물 수의 합이며, 이동 방향은 군집들 중 미생물 수가 가장 많은 군집의 이동방향이 된다. 
					maxCnt = maxDir = totalCnt = 0;
					iter = list.iterator();
					while(iter.hasNext()) {
						microbe = iter.next();
						
						totalCnt += microbe.cnt;
						
						if(microbe.cnt > maxCnt) {
							maxCnt = microbe.cnt;
							maxDir = microbe.dir;
						}
						microbe.cnt = 0;
						iter.remove();
					}
					
					microbeList.add(new Microbe(point.x, point.y, totalCnt, maxDir));
				}
				
			}
			
			System.out.printf("#%d %d\n", tc, getMicrobeCnt(microbeList));
		} // tc 종료
		
	}

	private static int getMicrobeCnt(List<Microbe> microbeList) {
		int cnt = 0;
		for(Microbe microbe : microbeList)
			cnt += microbe.cnt;
		return cnt;
	}
	
}
